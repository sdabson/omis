package omis.criminalassociation.dao;

import java.util.List;

import omis.criminalassociation.domain.CriminalAssociationCategory;
import omis.dao.GenericDao;

/**
 * Data access object for criminal association category.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (Apr, 14, 2015)
 * @since OMIS 3.0
 *
 */
public interface CriminalAssociationCategoryDao 
	extends GenericDao<CriminalAssociationCategory> {
	
	/**
	 * Returns the list of criminal association categories 
	 * returns {@code null}.
	 * 
	 * @return list of criminal association category
	 */
	List<CriminalAssociationCategory> findCriminalAssociationCategories();
}