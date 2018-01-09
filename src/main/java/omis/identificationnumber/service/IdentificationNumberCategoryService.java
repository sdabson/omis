package omis.identificationnumber.service;

import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.Multitude;

/**
 * IdentificationNumberCategoryService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Nov 1, 2017)
 *@since OMIS 3.0
 *
 */
public interface IdentificationNumberCategoryService {
	
	/**
	 * Creates identification number category.
	 * 
	 * @param name name
	 * @param multitude multitude
	 * @param valid whether valid
	 * @return created identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 */
	public IdentificationNumberCategory create(
			String name, Multitude multitude, Boolean valid)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates identification number category.
	 * 
	 * @param category identification number category being updated
	 * @param name name
	 * @param multitude multitude
	 * @param valid whether valid
	 * @return updated identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 */
	public IdentificationNumberCategory update(
			IdentificationNumberCategory category,
			String name, Multitude multitude, Boolean valid)
				throws DuplicateEntityFoundException;
	
	/**
	 * Removes identification number category
	 * 
	 * @param category identification number category to remove
	 */
	public void remove(IdentificationNumberCategory category);
}
