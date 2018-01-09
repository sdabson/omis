package omis.demographics.service;

import java.util.List;

import omis.demographics.domain.EyeColor;

/**
 * Service for eye colors.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public interface EyeColorService {
	
	/**
	 * Returns the eye color with the specified ID.
	 * 
	 * @param id ID of eye color to return
	 * @return eye color with specified ID
	 */
	EyeColor findById(Long id);
	
	/**
	 * Returns the eye color with the specified name.
	 * 
	 * @param name name of eye color to return
	 * @return eye color with specified name
	 */
	EyeColor findByName(String name);
	
	/**
	 * Returns all valid eye colors ordered by name.
	 * 
	 * @return all valid eye colors ordered by name
	 */
	List<EyeColor> findAll();
}