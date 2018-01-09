package omis.caseload.dao;

import java.util.List;

import omis.caseload.domain.ContactCategory;
import omis.dao.GenericDao;

/**
 * Data access object for contact category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 1, 2016)
 * @since OMIS 3.0
 */
public interface ContactCategoryDao extends GenericDao<ContactCategory> {

	List<ContactCategory> findAllContactCategories();
}