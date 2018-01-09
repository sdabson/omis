package omis.cache.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import omis.cache.CacheManager;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of application wide cache manager.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 24, 2013)
 * @since OMIS 3.0
 */
public class CacheManagerHibernateImpl
		implements CacheManager {
	
	private Boolean cachingSupported;
	
	private Boolean cachingByClassSupported;
	
	private Boolean cachingByRegionSupported;
	
	private SessionFactory sessionFactory;

	/** Instantiates a default cache manager. */
	public CacheManagerHibernateImpl() {
		// Default instantiation
	}
	
	/**
	 * Sets the session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Sets whether caching is supported.
	 * 
	 * @param cachingSupported whether caching is supported
	 */
	public void setCachingSupported(final boolean cachingSupported) {
		this.cachingSupported = cachingSupported;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean isCachingSupported() {
		if (this.cachingSupported != null) {
			return this.cachingSupported;
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public void removeAll() {
		if (this.cachingSupported != null && !this.cachingSupported) {
			throw new UnsupportedOperationException("Caching not supported");
		}
		this.sessionFactory.getCache().evictEntityRegions();
		this.sessionFactory.getCache().evictQueryRegions();
	}

	/**
	 * Sets whether caching by class is supported.
	 * 
	 * @param cachingByClassSupported whether caching by class is supported
	 */
	public void setCachingByClassSupported(
			final boolean cachingByClassSupported) {
		this.cachingByClassSupported = cachingByClassSupported;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean isCachingByClassSupported() {
		if (this.cachingByClassSupported != null) {
			return this.cachingByClassSupported;
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public void removeByClass(final String className) {
		if (this.cachingSupported != null && !this.cachingSupported) {
			throw new UnsupportedOperationException("Caching not supported");
		}
		if (this.cachingByClassSupported != null
				&& !this.cachingByClassSupported) {
			throw new UnsupportedOperationException(
					"Caching by class not supported");
		}
		this.sessionFactory.getCache().evictEntityRegion(className);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isCachingByRegionSupported() {
		if (this.cachingByRegionSupported != null) {
			return this.cachingByRegionSupported;
		}
		return false;
	}
	
	/**
	 * Sets whether caching by region is supported.
	 * 
	 * @param cachingByRegionSupported whether caching by region is supported
	 */
	public void setCachingByRegionSupported(
			final boolean cachingByRegionSupported) {
		this.cachingByRegionSupported = cachingByRegionSupported;
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeByRegion(final String region) {
		if (this.cachingSupported != null && !this.cachingSupported) {
			throw new UnsupportedOperationException("Caching not supported");
		}
		if (this.cachingByRegionSupported != null
				&& !this.cachingByRegionSupported) {
			throw new UnsupportedOperationException(
					"Caching by region not supported");
		}
		this.sessionFactory.getCache().evictQueryRegion(region);
	}

	/** {@inheritDoc} */
	@Override
	public List<String> getRegionNames() {
		String[] regionNames = this.sessionFactory.getStatistics()
				.getSecondLevelCacheRegionNames();
		List<String> results = new ArrayList<String>();
		for (String regionName : regionNames) {
			results.add(regionName);
		}
		return results;
	}

	/** {@inheritDoc} */
	@Override
	public List<String> getEntityNames() {
		String[] entityNames = this.sessionFactory.getStatistics()
				.getEntityNames();
		List<String> results = new ArrayList<String>();
		for (String entityName : entityNames) {
			results.add(entityName);
		}
		return results;
	}
}