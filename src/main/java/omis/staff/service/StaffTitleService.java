package omis.staff.service;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.staff.domain.StaffTitle;

/**
 * Service for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 6, 2014)
 * @since OMIS 3.0
 */
public interface StaffTitleService {

	/**
	 * Returns staff titles.
	 * 
	 * @return staff titles
	 */
	List<StaffTitle> findAll();
	
	/**
	 * Creates a new staff title.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid whether staff title is valid
	 * @return new staff title
	 * @throws DuplicateEntityFoundException if the staff titles exists
	 */
	StaffTitle create(String name, Short sortOrder, Boolean valid)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates a staff title,
	 * 
	 * @param staffTitle staff title to update
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid whether staff title is valid
	 * @return updated staff title
	 * @throws DuplicateEntityFoundException if the staff titles exists
	 */
	StaffTitle update(StaffTitle staffTitle, String name, Short sortOrder,
			Boolean valid)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a staff title.
	 * 
	 * @param staffTitle staff title to remove
	 */
	void remove(StaffTitle staffTitle);
	
	/**
	 * Returns the highest sort order.
	 * 
	 * @return highest sort order
	 */
	short findHighestSortOrder();
}