package omis.identificationnumber.dao;

import omis.dao.GenericDao;
import omis.identificationnumber.domain.IdentificationNumberCategory;

/**
 * Data access object for identification number categories.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public interface IdentificationNumberCategoryDao
		extends GenericDao<IdentificationNumberCategory> {

	/**
	 * Returns identification number category.
	 * 
	 * @param name name
	 * @return identification number category
	 */
	IdentificationNumberCategory find(String name);
	
	/**
	 * Returns identification number category with the given name 
	 * excluding specified identification number category
	 * 
	 * @param name name
	 * @param identificationNumberCategoryExcluded - identification number
	 * category to exclude
	 * @return identification number category with the given name 
	 * excluding specified identification number category
	 */
	IdentificationNumberCategory findExcluding(String name,
			IdentificationNumberCategory...
				identificationNumberCategoriesExcluded);
}