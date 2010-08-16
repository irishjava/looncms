package uk.mafu.loon.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceException;
import javax.persistence.Version;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.LoonAudio;
import uk.mafu.loon.domain.data.LoonImage;
import uk.mafu.loon.domain.data.LoonPdf;
import uk.mafu.loon.domain.data.LoonVideo;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;
import uk.mafu.loon.dto.ImageThumb;
import uk.mafu.loon.dto.ManyToManyResult;
import uk.mafu.loon.dto.OneToManyResult;
import uk.mafu.loon.dto.OneToOneResult;
import uk.mafu.loon.util.AnnotationMatcher;
import uk.mafu.loon.util.EntityUtil;
import uk.mafu.loon.util.ImageUtil;

public class AdminServiceImpl extends JpaDaoSupport implements AdminService {

	@SuppressWarnings("unchecked")
	private static final Class<? extends Annotation>[] COMPLEX_ANNOTATIONS = new Class[] {
			ManyToMany.class, ManyToOne.class, OneToMany.class, Version.class,
			OneToOne.class };

	public boolean login(String username, String md5password) {
		return true;
	}

	public String nonce() {
		return "salt";
	}

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(AdminServiceImpl.class);

	@SuppressWarnings("unchecked")
	public void remove(final String clazz, final Object id) {
		try {
			final Class<? extends Object> cl = ClassUtils.getClass(clazz);
			getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(final EntityManager em)
						throws PersistenceException {
					final Object find = em.find(cl, id);
					if (find == null) {
						return null;
					}
					em.remove(find);
					em.flush();
					return null;
				}
			});
		} catch (final ClassNotFoundException e) {
			throw new UnsupportedOperationException("Cannot load class '"
					+ clazz + "'");
		}
	}

	@SuppressWarnings("unchecked")
	public Object load(final String clazz, final Object id) {
		Assert.notNull(clazz, "class to load cannot be null");
		Assert.notNull(id, "id cannot be null");
		try {
			final Class cl = ClassUtils.getClass(clazz);
			return getJpaTemplate().execute(new JpaCallback() {
				public Object doInJpa(EntityManager em)
						throws PersistenceException {
					final Object find = em.find(cl, id);
					em.clear();
					return find;
				}
			});
		} catch (final ClassNotFoundException e) {
			throw new UnsupportedOperationException("Cannot load class '"
					+ clazz + "'");
		}
	}

	public OneToOneResult loadOneToOne(String parent_clazz,
			final String child_clazz, final String relationship_name,
			final Object parentId, final String[] fields,
			final boolean loadOptions) {
		Assert.notNull(parentId, "parentId:cannot be null");
		final Class<? extends Object> child = loadClass(child_clazz);
		final Class<? extends Object> parent = loadClass(parent_clazz);
		return (OneToOneResult) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				Object ret = null;
				final Object find = em.find(parent, parentId);
				ret = EntityUtil.getProperty(find, relationship_name);
				// The one to one relationship is null
				if (ret != null && !(child.isInstance(ret))) {
					throw new UnsupportedOperationException(
							"return value is not of expected class'"
									+ child_clazz + "'");
				}

				OneToOneResult result = new OneToOneResult(ret,
						(loadOptions == true) ? getAll(child_clazz, fields)
								: new ArrayList<Object>(), parentId,
						relationship_name);
				em.clear();
				return result;
			}
		});
	}

	private Object convertToObject(Object[] objs, Class<Object> loadClass,
			String[] fields) {
		try {
			Object newInstance = loadClass.newInstance();
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i];
				try {
					Field declaredField = EntityUtil.findField(newInstance
							.getClass(), fieldName);
					if (declaredField == null) {
						throw new NoSuchFieldException(fieldName);
					}
					declaredField.setAccessible(true);
					declaredField.set(newInstance, objs[i]);
					EntityUtil.setProperty(newInstance, fieldName, objs[i]);
				} catch (SecurityException e) {
					throw new UnsupportedOperationException(e);
				} catch (NoSuchFieldException e) {
					throw new UnsupportedOperationException(e);
				}
			}
			return newInstance;
		} catch (InstantiationException e) {
			throw new UnsupportedOperationException(e);
		} catch (IllegalAccessException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private List<Object> getAll(final String clazz, final String[] fields,
			final Class<Object> loadClass, final EntityManager em) {
		validateFields(loadClass, fields);
		String query = "select " + StringUtils.join(fields, ',') + " from "
				+ clazz;
		final List<Object[]> find = em.createQuery(query).getResultList();
		final List<Object> ret = new ArrayList<Object>();
		for (Object[] objs : find) {
			ret.add(convertToObject(objs, loadClass, fields));
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getAll(final String clazz, final String[] fields) {
		final Class<Object> loadClass = loadClass(clazz);
		return (List<Object>) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				final List<Object> ret = getAll(clazz, fields, loadClass, em);
				em.clear();
				return ret;
			}
		});
	}

	private void validateFields(Class<Object> loadClass, String[] fields) {
		outer: for (String field : fields) {
			try {
				Field declaredField = EntityUtil.findField(loadClass, field);
				if (declaredField == null) {
					throw new NoSuchFieldException(field);
				}
				declaredField.setAccessible(true);
				Annotation[] annotations = declaredField.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation.annotationType().equals(Id.class)) {
						continue outer;
					}
					if (annotation.annotationType().equals(Column.class)) {
						continue outer;
					}
				}
				throw new UnsupportedOperationException("Class ["
						+ loadClass.getName() + "] invalid field" + fields);
			} catch (SecurityException e) {
				throw new UnsupportedOperationException(SecurityException.class
						.getName()
						+ ":Class ["
						+ loadClass.getName()
						+ "]"
						+ e.getMessage());
			} catch (NoSuchFieldException e) {
				throw new UnsupportedOperationException(
						NoSuchFieldException.class.getName() + ":Class ["
								+ loadClass.getName() + "]" + e.getMessage());
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Class<Object> loadClass(final String clazz) {
		try {
			// Make sure they passed a valid entity class name into the method..
			// Don't let them instantiate arbitary shit!
			Class ret = ClassUtils.getClass(clazz);
			if (ret.getAnnotation(Entity.class) == null) {
				throw new UnsupportedOperationException(
						"Only allowed to request entity classes!!!" + clazz);
			}
			return ret;
		} catch (final ClassNotFoundException e) {
			throw new UnsupportedOperationException("Cannot load class '"
					+ clazz + "'");
		}
	}

	/**
	 * This method could do with some extra handling so that i can dynamically
	 * detect what field is the primary key field.
	 */
	public Object save(final Object modified) {
		return (Object) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				// final PropertyUtilsBean propertyUtilsBean = new
				// PropertyUtilsBean();
				try {
					// We will need to add extra handling to account for the
					// situation whereby the Object has never been saved to the
					// database.
					Field findField = EntityUtil.findField(modified.getClass(),
							"pk");
					findField.setAccessible(true);
					Object pk = findField.get(modified);
					Class<?> type = findField.getType();
					if (type.equals(String.class)) {
						if (isTransientPk(pk)) {
							findField.set(modified, UUID.randomUUID()
									.toString());
						}
					}
					Class<? extends Object> classType = modified.getClass();
					final Object original = (pk == null ? null : em.find(
							classType, pk));
					// The entity is not an update, it is an initial save.
					if (original == null) {
						em.persist(modified);
						em.flush();
						em.clear();
						return modified;
					}
					// The entity is an update.
					else {
						EntityUtil.doWithFields(modified,
								new EntityUtil.EnhancedFieldCallback() {
									public void doWith(Field field,
											Object target)
											throws IllegalArgumentException,
											IllegalAccessException {
										if (!isComplex(field) && !isId(field)) {
											field.setAccessible(true);
											field.set(original, field
													.get(target));
										}
									}
								}, new ReflectionUtils.FieldFilter() {
									public boolean matches(Field field) {
										return !Modifier.isStatic(field
												.getModifiers());
									}
								});
						em.merge(original);
					}
					em.flush();
					em.clear();
					return original;
				} catch (IllegalAccessException e) {
					logger.error(e);
					throw new UnsupportedOperationException(e);
				} catch (SecurityException e) {
					logger.error(e);
					throw new UnsupportedOperationException(e);
				} catch (NullPointerException e) {
					logger.error(e);
					throw new UnsupportedOperationException(e);
				}
			}
		});
	}

	private final boolean isId(Field field) {
		return new AnnotationMatcher(field, Id.class).isMatch();
	}

	private final boolean isComplex(Field field) {
		AnnotationMatcher annotationMatcher = new AnnotationMatcher(field,
				COMPLEX_ANNOTATIONS);
		return annotationMatcher.isMatch();
	}

	protected boolean isSimple(Object property) {
		return !(property instanceof Collection) && isNotEntity(property);
	}

	private boolean isNotEntity(Object property) {
		return property.getClass().getAnnotation(Entity.class) == null;
	}

	public void saveOneToOne(String parent_clazz, String child_clazz,
			final Object parent_pk, final Object child_pk,
			final String relationship_name) {
		Assert.notNull(parent_clazz, "parent_class");
		Assert.notNull(child_clazz, "child_class");
		Assert.notNull(parent_pk, "parent_pk");
		Assert.notNull(child_pk, "child_pk");
		Assert.notNull(relationship_name, "relationship_name");
		if (logger.isDebugEnabled()) {
			logger.debug("parent_class=" + parent_clazz);
			logger.debug("child_class=" + child_clazz);
			logger.debug("child_pk=" + child_pk);
			logger.debug("relationship_name=" + relationship_name);
		}
		final Class<Object> parent_clazz_instance = loadClass(parent_clazz);
		final Class<Object> child_clazz_instance = loadClass(child_clazz);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				Object parent = em.find(parent_clazz_instance, parent_pk);
				Object child = null;
				if (!isTransientPk(child_pk)) {
					child = em.find(child_clazz_instance, child_pk);
				}
				try {
					Field findField = EntityUtil.findField(parent.getClass(),
							relationship_name);
					findField.set(parent, child);
				} catch (IllegalAccessException e) {
					throw new UnsupportedOperationException(e);
				}
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	public OneToManyResult loadOneToMany(String parent_clazz,
			final String child_clazz, final String relationship_name,
			final Object parentId, final String[] fields) {
		final Class<? extends Object> parent;
		Assert.notNull(parentId, "parentId");
		try {
			// Make sure that the child_clazz is a valid class, for example by
			// getting it's class definition.
			loadClass(child_clazz);
			parent = loadClass(parent_clazz);
		} catch (Throwable t) {
			throw new UnsupportedOperationException(t);
		}
		return (OneToManyResult) getJpaTemplate().execute(new JpaCallback() {
			@SuppressWarnings("unchecked")
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				try {
					List<Object> ret = new ArrayList<Object>();
					final Object find = em.find(parent, parentId);
					// get the relationship
					List<Object> originals = (List) EntityUtil.getProperty(
							find, relationship_name);
					// Copy the requested fields onto the return objects
					for (Object entity : originals) {
						Object slimObject = entity.getClass().newInstance();
						for (String fieldName : fields) {
							EntityUtil.setProperty(slimObject, fieldName,
									EntityUtil.getProperty(entity, fieldName));
						}
						ret.add(slimObject);
					}
					OneToManyResult result = new OneToManyResult((List) ret,
							parentId, relationship_name);
					return result;
				} catch (Throwable t) {
					throw new UnsupportedOperationException(t);
				}
			}
		});
	}

	public void saveOneToMany(String parent_class, final Object parentId,
			final String relationship_name, final List<Object> child_pks) {
		if (logger.isDebugEnabled()) {
			logger.debug("List<Object> child_pks=" + child_pks);
			logger.debug("String relationship_name=" + relationship_name);
			logger.debug("Object parentId=" + parentId);
			logger.debug("String parent_class=" + parent_class);
		}
		Assert.notNull(parent_class, "parent_class");
		Assert.notNull(relationship_name, "relationship_name");
		Assert.notNull(parentId, "parentId");
		Assert.notNull(child_pks, "child_pks");
		final Class<? extends Object> parent_clazz = loadClass(parent_class);
		final Class<? extends Object> child_clazz = getRelationshipType(
				parent_clazz, relationship_name);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				Object parent = em.find(parent_clazz, parentId);
				List<Object> newCollection = new ArrayList<Object>();
				for (Object id : child_pks) {
					// For fuck sake, the numbers are coming over the wire as
					// Object, not fucking longs....
					if (!(id instanceof Object)) {
						id = NumberUtils.toInt(id.toString());
						logger
								.error("received a Object, should only be receiving Objects here...:"
										+ id);
					}
					Object find = em.find(child_clazz, id);
					newCollection.add(find);
				}

				try {
					Field findField = EntityUtil.findField(parent.getClass(),
							relationship_name);
					findField.set(parent, newCollection);
					em.merge(parent);
				} catch (IllegalAccessException e) {
					throw new UnsupportedOperationException(e);
				}
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	public void saveManyToMany(String parent_class, final Object parentId,
			final String relationship_name, final List<Object> child_pks) {
		if (logger.isDebugEnabled()) {
			logger.debug("List<Object> child_pks=" + child_pks);
			logger.debug("String relationship_name=" + relationship_name);
			logger.debug("Object parentId=" + parentId);
			logger.debug("String parent_class=" + parent_class);
		}
		Assert.notNull(parent_class, "parent_class");
		Assert.notNull(relationship_name, "relationship_name");
		Assert.notNull(parentId, "parentId");
		Assert.notNull(child_pks, "child_pks");
		final Class<? extends Object> parent_clazz = loadClass(parent_class);
		final Class<? extends Object> child_clazz = getRelationshipType(
				parent_clazz, relationship_name);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				Object parent = em.find(parent_clazz, parentId);
				List<Object> newCollection = new ArrayList<Object>();
				for (Object id : child_pks) {
					// For fuck sake, the numbers are coming over the wire as
					// Object, not fucking longs....
					if (!(id instanceof Object)) {
						id = NumberUtils.toInt(id.toString());
						logger
								.error("received a Object, should only be receiving Objects here...:"
										+ id);
					}
					Object find = em.find(child_clazz, id);
					newCollection.add(find);
				}
				try {
					Field findField = EntityUtil.findField(parent.getClass(),
							relationship_name);
					findField.set(parent, newCollection);
					em.merge(parent);
				} catch (IllegalAccessException e) {
					throw new UnsupportedOperationException(e);
				}
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Object> getRelationshipType(
			Class<? extends Object> parent, String relationship_name) {
		try {
			Type type = parent.getDeclaredField(relationship_name)
					.getGenericType();
			if (type instanceof ParameterizedType) {
				Type[] actualTypeArguments = ((ParameterizedType) type)
						.getActualTypeArguments();
				return (Class<? extends Object>) actualTypeArguments[0];
			} else {
				throw new UnsupportedOperationException("Field '"
						+ relationship_name + "' of parent '"
						+ parent.getName() + "' is not an instance of '"
						+ ParameterizedType.class.getName() + "'");
			}
		} catch (SecurityException e) {
			throw new UnsupportedOperationException(e);
		} catch (NoSuchFieldException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public void removeImage(Object id) {
		remove(LoonImage.class.getName(), id);
	}

	public void removePdf(Object id) {
		remove(LoonPdf.class.getName(), id);
	}

	public void removeVideo(Object id) {
		remove(LoonVideo.class.getName(), id);
	}

	public LoonImage loadImage(Object id) {
		return (LoonImage) load(LoonImage.class.getName(), id);
	}

	public void saveSingleLink(Object child, String child_classname,
			String parent_classname, final Object parentPk,
			final String relationship) {
		Assert.notNull(child, "child");
		Assert.notNull(child_classname, "child_classname");
		Assert.notNull(parent_classname, "parent_classname");
		Assert.notNull(parentPk, "parentPk");
		Assert.notNull(relationship, "relationship");
		final Object savedChild = save(child);
		final Class<? extends Object> parent_clazz = loadClass(parent_classname);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				Object parent = em.find(parent_clazz, parentPk);
				try {
					Field findField = EntityUtil.findField(parent.getClass(),
							relationship);
					findField.set(parent, savedChild);
				} catch (IllegalAccessException e) {
					throw new UnsupportedOperationException(e);
				}
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	public void deleteSingleLink(String parent_clazz, final Object parentId,
			final String relationship) {
		Assert.notNull(parent_clazz, "parent_clazz");
		Assert.notNull(parentId, "parentId");
		Assert.notNull(relationship, "relationship");
		final Class<? extends Object> parentClass = loadClass(parent_clazz);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				Object parent = em.find(parentClass, parentId);
				try {
					Field findField = EntityUtil.findField(parent.getClass(),
							relationship);
					findField.set(parent, null);
				} catch (IllegalAccessException e) {
					throw new UnsupportedOperationException(e);
				}
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	public void saveOneToManyImages(String parent_class, final Object parentId,
			final String relationship_name, final List<ImageLink> children) {
		if (logger.isDebugEnabled()) {
			logger.debug("List<Object> child_pks=" + children);
			logger.debug("String relationship_name=" + relationship_name);
			logger.debug("Object parentId=" + parentId);
			logger.debug("String parent_class=" + parent_class);
		}
		Assert.notNull(parent_class, "parent_class");
		Assert.notNull(relationship_name, "relationship_name");
		Assert.notNull(parentId, "parentId");
		Assert.notNull(children, "child_pks");
		final Class<? extends Object> parent_clazz = loadClass(parent_class);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				for (ImageLink imageLink : children) { 
					Assert.notNull(imageLink.getPk(), "images cannot have null primary key");
					if (imageLink.getPk() < 1) {
						em.persist(imageLink);
					} else {
						em.merge(imageLink);
					}
				}
				Object parent = em.find(parent_clazz, parentId);
				EntityUtil.setProperty(parent, relationship_name, children);
				em.merge(parent);
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	public LoonPdf loadPdf(Object id) {
		return (LoonPdf) load(LoonPdf.class.getName(), id);
	}

	public LoonVideo loadVideo(Object id) {
		return (LoonVideo) load(LoonVideo.class.getName(), id);
	}

	public void saveOneToManyPdfs(String parent_class, final Object parentId,
			final String relationship_name, final List<PdfLink> children) {
		if (logger.isDebugEnabled()) {
			logger.debug("List<Object> child_pks=" + children);
			logger.debug("String relationship_name=" + relationship_name);
			logger.debug("Object parentId=" + parentId);
			logger.debug("String parent_class=" + parent_class);
		}
		Assert.notNull(parent_class, "parent_class");
		Assert.notNull(relationship_name, "relationship_name");
		Assert.notNull(parentId, "parentId");
		Assert.notNull(children, "child_pks");
		final Class<? extends Object> parent_clazz = loadClass(parent_class);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				for (PdfLink imageLink : children) {
					em.merge(imageLink);
				}
				Object parent = em.find(parent_clazz, parentId);
				EntityUtil.setProperty(parent, relationship_name, children);
				em.merge(parent);
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	public void saveOneToManyVideos(String parent_class, final Object parentId,
			final String relationship_name, final List<VideoLink> children) {
		if (logger.isDebugEnabled()) {
			logger.debug("List<Object> child_pks=" + children);
			logger.debug("String relationship_name=" + relationship_name);
			logger.debug("Object parentId=" + parentId);
			logger.debug("String parent_class=" + parent_class);
		}
		Assert.notNull(parent_class, "parent_class");
		Assert.notNull(relationship_name, "relationship_name");
		Assert.notNull(parentId, "parentId");
		Assert.notNull(children, "child_pks");
		final Class<? extends Object> parent_clazz = loadClass(parent_class);
		getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				for (VideoLink imageLink : children) {
					em.merge(imageLink);
				}
				Object parent = em.find(parent_clazz, parentId);
				EntityUtil.setProperty(parent, relationship_name, children);
				em.merge(parent);
				em.flush();
				em.clear();
				return null;
			}
		});
	}

	/**
	 * This method could do with some extra handling so that i can dynamically
	 * detect what field is the primary key field.
	 */
	public Object uploadPdf(final LoonPdf modified) {
		return (Object) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				// saving ....
				if (isTransientPk(modified.getPk())) {
					em.persist(modified);
					em.flush();
					return modified.getPk();
				}
				// updating ...
				else {
					em.merge(modified);
					em.flush();
					return modified.getPk();
				}
			}
		});
	}

	public Object uploadVideo(final LoonVideo modified) {
		return (Object) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				// saving ....
				if (isTransientPk(modified.getPk())) {
					em.persist(modified);
					em.flush();
					return modified.getPk();
				}
				// updating ...
				else {
					em.merge(modified);
					em.flush();
					return modified.getPk();
				}
			}
		});
	}

	public Object uploadAudio(final LoonAudio modified) {
		return (Object) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				// saving ....
				if (isTransientPk(modified.getPk())) {
					em.persist(modified);
					em.flush();
					return modified.getPk();
				}
				// updating ...
				else {
					em.merge(modified);
					em.flush();
					return modified.getPk();
				}
			}
		});
	}

	public ImageThumb loadImageThumb(final Object pk) {
		return (ImageThumb) getJpaTemplate().execute(new JpaCallback() {
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				LoonImage orig = em.find(LoonImage.class, pk);
				if (orig == null) {
					return new ImageThumb();
				}
				em.clear();
				try {
					ImageThumb t = new ImageThumb();
					BufferedImage bi;
					bi = ImageIO.read(new ByteArrayInputStream(orig.getData()));
					bi = ImageUtil.createResizedCopy(bi, 50, 50);
					ByteArrayOutputStream output = new ByteArrayOutputStream();
					ImageIO.write(bi, "jpeg", output);
					byte[] byteArray = output.toByteArray();
					t.setData(byteArray);
					t.setMimetype(orig.getMimetype());
					t.setLinkPk(pk);
					return t;
				} catch (IOException e) {
					logger.error(e);
					return new ImageThumb();
				}
			}
		});
	}

	public ManyToManyResult loadManyToMany(String parent_clazz,
			final String child_clazz, final String relationship_name,
			final Object parentId, final String[] fields) {
		final Class<? extends Object> parent;
		try {
			// Ensure the child_clazz is a valid classname
			loadClass(child_clazz);
			parent = loadClass(parent_clazz);
		} catch (Throwable t) {
			throw new UnsupportedOperationException(t);
		}
		return (ManyToManyResult) getJpaTemplate().execute(new JpaCallback() {
			@SuppressWarnings("unchecked")
			public Object doInJpa(final EntityManager em)
					throws PersistenceException {
				try {
					List<Object> ret = new ArrayList<Object>();
					if (!isTransientPk(parentId)) {
						final Object find = em.find(parent, parentId);
						// get the relationship
						List<Object> originals = (List) EntityUtil.getProperty(
								find, relationship_name);
						// Copy the requested fields onto the return objects
						for (Object entity : originals) {
							Object slimObject = entity.getClass().newInstance();
							for (String fieldName : fields) {
								EntityUtil.setProperty(slimObject, fieldName,
										EntityUtil.getProperty(entity,
												fieldName));
							}
							ret.add(slimObject);
						}
					}
					final List<Object> options = getAll(child_clazz, fields);
					em.clear();
					// Now that we have cleared the entity manager, it is now
					// time to remove duplicates.
					ManyToManyResult result = new ManyToManyResult((List) ret,
							(List) CollectionUtils.subtract(options, ret),
							parentId, relationship_name);
					return result;
				} catch (Throwable t) {
					throw new UnsupportedOperationException(t);
				}
			}
		});
	}

	/*
	 * Determine whether a primary key is saved/unsaved.
	 */
	protected boolean isTransientPk(Object parentId) {
		if (parentId == null) {
			return true;
		}
		if (parentId instanceof String) {
			return ((String) parentId).length() <= 0;
		}

		if (parentId instanceof Long) {
			return ((Long) parentId) <= 0;
		}
		if (parentId instanceof Integer) {
			return ((Integer) parentId) <= 0;
		}
		throw new UnsupportedOperationException("unsupported primary key type");
	}
}