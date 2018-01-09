package omis.warrant.service;

import java.util.Date;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;

/**
 * WarrantCancellationService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantCancellationService {
	
	/**
	 * Creates a WarrantCancellation with the specified properties
	 * @param warrant - Warrant
	 * @param date - Date
	 * @param comment - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Newly created WarrantCancellation
	 * @throws DuplicateEntityFoundException - When a WarrantCancellation already
	 * exists for the specified Warrant
	 */
	public WarrantCancellation create(Warrant warrant, Date date,
			String comment, Person clearedBy, Date clearedByDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a WarrantCancellation with the specified properties
	 * @param warrantCancellation - WarrantCancellation to update
	 * @param date - Date
	 * @param comment - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Updated WarrantCancellation
	 * @throws DuplicateEntityFoundException - When another WarrantCancellation
	 * exists with specified WarrantCancellation's warrant
	 */
	public WarrantCancellation update(
			WarrantCancellation warrantCancellation,
			Date date, String comment, Person clearedBy,
			Date clearedByDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a WarrantCancellation
	 * @param warrantCancellation - WarrantCancellation to remove
	 */
	public void remove(WarrantCancellation warrantCancellation);
	
	/**
	 * Returns a WarrantCancellation by specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantCancellation by specified Warrant
	 */
	public WarrantCancellation findByWarrant(Warrant warrant);
	
}
