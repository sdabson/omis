package omis.adaaccommodation.dao;

import java.util.List;

import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.dao.GenericDao;

/**
 * Data access object for authorization source category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public interface AuthorizationSourceCategoryDao 
	extends GenericDao<AuthorizationSourceCategory> {

	/**
	 * List of authorization source category.
	 * 
	 * @return authorization source category list
	 */
	List<AuthorizationSourceCategory> findCategories();
}