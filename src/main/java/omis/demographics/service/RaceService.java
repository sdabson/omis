package omis.demographics.service;

import java.util.List;

import omis.demographics.domain.Race;

/**
 * Service for races.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 7, 2014)
 * @since OMIS 3.0
 */
public interface RaceService {

	/**
	 * Returns all races.
	 * 
	 * @return all races
	 */
	List<Race> findAll();
}