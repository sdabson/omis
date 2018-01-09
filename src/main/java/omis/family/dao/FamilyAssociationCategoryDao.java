package omis.family.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.family.domain.FamilyAssociationCategory;

/**
 * Data access object for family association categories.
 *
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.1 (June 2, 2015)
 * @since OMIS 3.0
 */
public interface FamilyAssociationCategoryDao 
	extends GenericDao<FamilyAssociationCategory> {
	/**
	 * Returns a list of all family associations that the specified person is
	 * involved in.
	 * 
	 * @return list of family association categories
	 */
	List<FamilyAssociationCategory> findAll();
	
	/**
	 * Finds a family association category.
	 *  
	 * @param name name
	 * @return found family association
	 */
	FamilyAssociationCategory find(String name);
}