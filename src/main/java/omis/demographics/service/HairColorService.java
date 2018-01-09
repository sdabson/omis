package omis.demographics.service;

import java.util.List;

import omis.demographics.domain.HairColor;

/**
 * Service for hair colors.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public interface HairColorService {
	
	/**
	 * Returns the hair color with the specified ID.
	 * 
	 * @param id ID of hair color to return
	 * @return hair color with specified ID
	 */
	HairColor findById(Long id);
	
	/**
	 * Returns the hair color with the specified name.
	 * 
	 * @param name name of hair color to return
	 * @return hair color with specified name
	 */
	HairColor findByName(String name);
	
	/**
	 * Returns all valid hair colors ordered by name.
	 * 
	 * @return all valid hair colors ordered by name
	 */
	List<HairColor> findAll();
}