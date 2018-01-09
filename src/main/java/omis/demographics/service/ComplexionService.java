package omis.demographics.service;

import java.util.List;

import omis.demographics.domain.Complexion;

/**
 * Service for complexions.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public interface ComplexionService {

	/**
	 * Returns all complexions.
	 * 
	 * @return all complexions
	 */
	List<Complexion> findAll();	
}