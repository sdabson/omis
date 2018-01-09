package omis.trackeddocument.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.trackeddocument.domain.TrackedDocumentCategory;

/**
 * Data access object for offender document category.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public interface TrackedDocumentCategoryDao
		extends GenericDao<TrackedDocumentCategory> {

	/**
	 * Returns categories.
	 * 
	 * @return categories tracked document category
	 */
	List<TrackedDocumentCategory> findCategories();
}