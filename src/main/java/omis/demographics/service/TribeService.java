package omis.demographics.service;

import java.util.List;

import omis.demographics.domain.Tribe;

/**
 * Service for tribes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 7, 2014)
 * @since OMIS 3.0
 */
public interface TribeService {

	/**
	 * Returns all tribes.
	 * 
	 * @return all tribes
	 */
	List<Tribe> findAll();
}