package omis.court.service;

import java.util.List;

import omis.court.domain.Court;

/**
 * Service for courts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 28, 2013)
 * @since OMIS 3.0
 */
public interface CourtService {
	
	/**
	 * Returns the court with the specified ID.
	 * 
	 * @param id ID of court to return
	 * @return court with specified ID
	 */
	Court findById(Long id);
	
	/**
	 * Returns all courts.
	 * 
	 * @return all courts
	 */
	List<Court> findAll();
	
	/**
	 * Saves a court.
	 * 
	 * @param court court to save
	 * @return saved court
	 */
	Court save(Court court);
	
	/**
	 * Removes a court.
	 * 
	 * @param court court to remove
	 */
	void remove(Court court);
}