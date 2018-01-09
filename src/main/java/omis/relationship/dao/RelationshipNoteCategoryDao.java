package omis.relationship.dao;

import omis.dao.GenericDao;
import omis.relationship.domain.RelationshipNoteCategory;

/**
 * Data access object for relationship note categories.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 7, 2017)
 * @since OMIS 3.0
 */
public interface RelationshipNoteCategoryDao
		extends GenericDao<RelationshipNoteCategory> {

	/**
	 * Returns relationship note category.
	 * 
	 * @param name name
	 * @return relationship note category
	 */
	RelationshipNoteCategory find(String name);
}