package omis.cache;

import java.util.List;

/**
 * Application wide cache manager.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 24, 2013)
 * @since OMIS 3.0
 */
public interface CacheManager {

	/**
	 * Returns whether caching is supported.
	 * 
	 * @return whether caching is supported
	 */
	boolean isCachingSupported();
	
	/**
	 * Removes all cached instances.
	 * 
	 * @throws UnsupportedOperationException if caching is not supported
	 */
	void removeAll();
	
	/**
	 * Returns whether caching by class is supported.
	 * 
	 * @return whether caching by class is supported
	 */
	boolean isCachingByClassSupported();
	
	/**
	 * Removes cached instances of the specified class.
	 * 
	 * @param className name of class whose instances to remove
	 * @throws UnsupportedOperationException if caching or caching by class
	 * name is not supported
	 */
	void removeByClass(String className);
	
	/**
	 * Removes cached instances by region.
	 * 
	 * @param region region the cached instances of which to remove
	 * @throws UnsupportedOperationException if caching or caching by region
	 * is not supported
	 */
	void removeByRegion(String region);
	
	/**
	 * Returns whether caching by region is supported.
	 * 
	 * @return whether caching by region is supported
	 */
	boolean isCachingByRegionSupported();
	
	/**
	 * Returns cache region names.
	 * 
	 * @return cache region names
	 */
	List<String> getRegionNames();
	
	/**
	 * Returns entity names.
	 * 
	 * @return entity names
	 */
	List<String> getEntityNames();
}