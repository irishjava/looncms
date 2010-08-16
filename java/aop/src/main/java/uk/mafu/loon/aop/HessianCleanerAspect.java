package uk.mafu.loon.aop;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.hibernate.LazyInitializationException;
import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentList;
import uk.mafu.loon.LoonExceptionTranslator;

public class HessianCleanerAspect {
	private LoonExceptionTranslator translator;

	public HessianCleanerAspect(LoonExceptionTranslator translator) {
		this.translator = translator;
	}

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(HessianCleanerAspect.class);

	private String appendArgs(final Object[] args) {
		final StringBuffer ret = new StringBuffer();
		for (final Object object : args) {
			ret.append(":");
			ret.append(object);
			ret.append(":");
		}
		return ret.toString();
	}

	public Object decorate(final ProceedingJoinPoint call) {
		if (translator == null) {
			throw new UnsupportedOperationException(
					"exception translator must be set");
		}
		//try {
			if (logger.isDebugEnabled()) {
				logger
						.debug("decorate(ProceedingJoinPoint) - from logging aspect: entering method ["
								+ call.toShortString()
								+ "] with params:"
								+ appendArgs(call.getArgs()));
			}
			Object point;
			try {
				point = call.proceed();
			} catch (Throwable e) {
				logger.error(e);
				throw new UnsupportedOperationException("internal server error");
			}
			if (!isIgnorable(point)) {
				point = descendThroughNode(point, new ArrayList<Object>());
			}
			if (logger.isDebugEnabled()) {
				logger
						.debug("decorate(ProceedingJoinPoint) - from logging aspect: exiting method ["
								+ call.toShortString()
								+ "with return as:"
								+ point);
			}
			return point;
//		} catch (final Throwable t) {
//			logger.error("exception calling:'" + call.toLongString() + "'");
//			logger.error(t);
//			RuntimeException translate = translator.translate(t);
//			throw translate;
//		}
	}

	/**
	 * 
	 * This method is going to presume a couple of things.
	 * <ol>
	 * <li>Presume that we are not using any kind of bags or collections which
	 * shall contain duplicate elements.</li>
	 * </ol>
	 * 
	 * @param node
	 * @param visited
	 *            a {@link List} of node references for the nodes that have
	 *            already been visited, this prevents getting stuck on, for
	 *            example, circular references.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Object descendThroughNode(final Object node, final List visited) {
		// Check to see if we have already visited this node.
		if (visited.contains(node)) {
			// If we have, then just return.
			return node;
		} else {
			// Otherwise add the node to our visited list
			visited.add(node);
		}
		try {
			if (node == null) {
				return null;
			}
			if (isIgnorable(node)){
				return node;
			}
			// if (node instanceof PersistentCollection) {
			// return null;
			// }
			// If you got a list back from Hibernate
			if (node instanceof List) {
				return handleList(node, visited);
			}
			final PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			final PropertyDescriptor[] propertyDescriptors = propertyUtilsBean
					.getPropertyDescriptors(node);
			for (final PropertyDescriptor p : propertyDescriptors) {
				final String name = p.getName();
				// if (logger.isDebugEnabled()) {
				// logger.debug("descendThroughPoint(Object) - " + name);
				// }
				if (!"class".equals(name)) {
					Object property = propertyUtilsBean.getProperty(node, name);
					if (property instanceof Set) {
						try {
							((Set) property).size();
							// Ok, didn't crash ... must be possible to get the
							// data from it...
							final Set s = new HashSet();
							for (final Object o : ((Set) property)) {
								s.add(descendThroughNode(o, visited));
							}
							property = s;
							propertyUtilsBean.setProperty(node, name, s);
						} catch (final LazyInitializationException l) {
							// if (logger.isDebugEnabled()) {
							// logger.debug("descendThroughPoint(Object) -
							// clearing the Set:" + name);
							// }
							propertyUtilsBean.setProperty(node, name,
									new HashSet());
						}
					} else if (property instanceof List) {
						try {
							if (property instanceof PersistentBag) {
								if (((PersistentBag) property).wasInitialized()) {
									((List) property).size();
									// Ok, didn't crash ... must be possible to
									// get the
									// data from it...
									final List s = new ArrayList();
									for (final Object o : ((List) property)) {
										s.add(descendThroughNode(o, visited));
									}
									propertyUtilsBean
											.setProperty(node, name, s);
								} else {
									propertyUtilsBean.setProperty(node, name,
											new ArrayList());
								}
							} else if (property instanceof PersistentList) {
								if (((PersistentList) property)
										.wasInitialized()) {
									((List) property).size();
									// Ok, didn't crash ... must be possible to
									// get the
									// data from it...
									final List s = new ArrayList();
									for (final Object o : ((List) property)) {
										s.add(descendThroughNode(o, visited));
									}
									propertyUtilsBean
											.setProperty(node, name, s);
								} else {
									propertyUtilsBean.setProperty(node, name,
											new ArrayList());
								}
							} else {
								// We must also add this handling to the
								// other collection types, otherwise anything
								// under a non-persistent collection will not
								// get checked, in the case of DTO objects a
								// List may contain uninitialized Domain
								// Objects.
								for (final Object o : ((List) property)) {
									descendThroughNode(o, visited);
								}
							}
						} catch (final Throwable l) {
							// if (logger.isDebugEnabled()) {
							// logger.debug("descendThroughPoint(Object) -
							// clearing the List:" + name);
							// }
							propertyUtilsBean.setProperty(node, name,
									new ArrayList());
						}
					} else if (property instanceof Map) {
						try {
							((Map) property).keySet().size();
							// Ok, didn't crash ... must be possible to get the
							// data from it...
							final Map s = new HashMap();
							for (final Object o : ((Map) property).keySet()) {
								s.put(o, ((Map) property)
										.get(descendThroughNode(o, visited)));
							}
							propertyUtilsBean.setProperty(node, name, s);
						} catch (final LazyInitializationException l) {
							// if (logger.isDebugEnabled()) {
							// logger.debug("descendThroughPoint(Object) -
							// clearing the Map:" + name);
							// }
							propertyUtilsBean.setProperty(node, name,
									new HashMap());
						}
					} else {
						// if (logger.isDebugEnabled()) {
						// logger
						// .debug("descendThroughPoint(Object) - have not
						// detected any collection membership .. lets recurse
						// ....");
						// }
						// If the property is living as a Timestamp we need to
						// cast it back to a date, the hard way...
						if (property instanceof Date) {
							// logger.debug("descendThroughNode(Object, List) -
							// property is instanceof Date", null);
							// //$NON-NLS-1$
							property = new Date(((Date) property).getTime());
							propertyUtilsBean.setProperty(node, name, property);
						}
						// If we can think of any other Simple objects that we
						// shouldn't descend upon .. add them here.
						if (!isIgnorable(property)) {
							// property = descendThroughNode(property, visited);
							propertyUtilsBean.setProperty(node, name,
									descendThroughNode(property, visited));
						}
					}
				}
			}
			return node;
		} catch (final Throwable t) {
			logger.error(t);
			throw new RuntimeException(t);
		}
	}

	@SuppressWarnings("unchecked")
	private Object handleList(final Object node, final List visited) {
		if (node instanceof PersistentBag) {
			if (!((PersistentBag) node).wasInitialized()) {
				return new ArrayList();
			}
		}
		if (node instanceof PersistentList) {
			if (!((PersistentList) node).wasInitialized()) {
				return new ArrayList();
			}
		}
		
		final List ret = new ArrayList();
		for (final Object o : (List) node) {
			ret.add(descendThroughNode(o, visited));
			continue;
		}
		return ret;
	}

	/**
	 * Can we ignore the property? This method should be modified to just check
	 * if the class is annotated with the &and; entity annotation.
	 * 
	 * @param property
	 * @return
	 */
	private boolean isIgnorable(Object property) {
		return (property == null) || (property instanceof String)
				|| (property instanceof Double)
				|| (property instanceof Integer) || (property instanceof Long)
				|| (property instanceof java.sql.Timestamp)
				|| (property instanceof Date)
				|| (property instanceof java.sql.Date) ||(property instanceof Class);
	}
}