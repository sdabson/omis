package omis.visitation.dao;

import omis.dao.GenericDao;
import omis.visitation.domain.VisitationAssociationCategory;

/**
 * Visitation association category data access object.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (May 8, 2015)
 * @since OMIS 3.0
 */
public interface VisitationAssociationCategoryDao
extends GenericDao<VisitationAssociationCategory>{
	/**
	 * Return a list of all visitors associated with the specified offender.
	 * 
	 * @param name name
	 * @return visitation asociation category
	 */
	VisitationAssociationCategory find(String name);
}