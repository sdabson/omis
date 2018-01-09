package omis.cache.web.controller;

import java.util.Collections;
import java.util.List;

import omis.cache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for cache management.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 13, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/cache")
@PreAuthorize("hasRole('USER')")
public class CacheManagerController {
	
	/* View names. */
	
	private static final String MANAGE_VIEW_NAME = "cache/manage";
	
	private static final String CLEAR_REGION_SUCCESS_VIEW_NAME
		= "cache/includes/clearRegionSuccess";

	private static final String CLEAR_ENTITY_SUCCESS_VIEW_NAME
		= "cache/includes/clearEntitySuccess";
	
	private static final String CLEAR_SUCCESS_VIEW_NAME
		= "cache/includes/clearSuccess";
	
	/* Model keys. */
	
	private static final String REGION_NAMES_MODEL_KEY = "regionNames";

	private static final String ENTITY_NAMES_MODEL_KEY = "entityNames";
	
	/* Cache manager. */
	
	@Autowired
	private CacheManager cacheManager;

	/* Constructor. */
	
	/** Instantiates a controller for cache management. */
	public CacheManagerController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays cache management screen.
	 * 
	 * @return model and view to cache management screen
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('CACHE_MANAGER')")
	@RequestMapping("/manage.html")
	public ModelAndView manage() {
		ModelAndView mav = new ModelAndView(MANAGE_VIEW_NAME);
		List<String> regionNames = this.cacheManager.getRegionNames();
		Collections.sort(regionNames);
		mav.addObject(REGION_NAMES_MODEL_KEY, regionNames);
		List<String> entityNames = this.cacheManager.getEntityNames();
		Collections.sort(entityNames);
		mav.addObject(ENTITY_NAMES_MODEL_KEY, entityNames);
		return mav;
	}
	
	/* AJAX methods. */
	
	/**
	 * Clears a cache region.
	 * 
	 * @param regionName region name
	 * @return result message
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('CACHE_MANAGER')")
	@RequestMapping("/clearRegion.html")
	public ModelAndView clearRegion(
			@RequestParam(value = "regionName", required = true)
				final String regionName) {
		this.cacheManager.removeByRegion(regionName);
		return new ModelAndView(CLEAR_REGION_SUCCESS_VIEW_NAME);
	}
	
	/**
	 * Clears cache by entity.
	 * 
	 * @param entityName entity name
	 * @return result message
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('CACHE_MANAGER')")
	@RequestMapping("/clearEntity.html")
	public ModelAndView clearEntity(
			@RequestParam(value = "entityName", required = true)
				final String entityName) {
		this.cacheManager.removeByClass(entityName);
		return new ModelAndView(CLEAR_ENTITY_SUCCESS_VIEW_NAME);
	}
	
	/**
	 * Clears cache.
	 *
	 * @return result message
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('CACHE_MANAGER')")
	@RequestMapping("/clear.html")
	public ModelAndView clear() {
		this.cacheManager.removeAll();
		return new ModelAndView(CLEAR_SUCCESS_VIEW_NAME);
	}
}