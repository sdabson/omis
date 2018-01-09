package omis.adaaccommodation.dao;

import java.util.List;

import omis.adaaccommodation.domain.AccommodationCategory;
import omis.dao.GenericDao;

/**
 * Data access object for accomodation category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationCategoryDao 
	extends GenericDao<AccommodationCategory> {

	/**
	 * Returns a list of all accommodation categories.
	 * 
	 * @return accommodation categories
	 */
	List<AccommodationCategory> findAllAccommodationCategories();
}
