package omis.adaaccommodation.dao;

import java.util.List;

import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.dao.GenericDao;

/**
 * Data access object for disability classification category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 23, 2015)
 * @since OMIS 3.0
 */
public interface DisabilityClassificationCategoryDao 
						extends	GenericDao<DisabilityClassificationCategory> {

	/**
	 * List of disability classification category.
	 * 
	 * @return disability classification category list
	 */
	List<DisabilityClassificationCategory> findCategories();		
}