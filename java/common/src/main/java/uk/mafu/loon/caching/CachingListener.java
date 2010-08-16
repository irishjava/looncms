package uk.mafu.loon.caching;

import org.apache.log4j.Logger;
import java.io.Serializable;
import org.springmodules.cache.CachingModel;

public class CachingListener implements
		org.springmodules.cache.interceptor.caching.CachingListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CachingListener.class);

	public void onCaching(Serializable key, Object obj, CachingModel model) {
		if (logger.isInfoEnabled()) {
			
			logger.info("performing caching" + key.toString()); //$NON-NLS-1$
		}
	}
}
