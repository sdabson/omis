package omis.relationship.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.relationship.domain.RelationshipNoteCategory;
import omis.relationship.domain.RelationshipNoteCategoryDesignator;

/**
 * Data access object for relationship note category designator.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface RelationshipNoteCategoryDesignatorDao
		extends GenericDao<RelationshipNoteCategoryDesignator> {

	/**
	 * Returns designated categories.
	 * 
	 * @return designated categories
	 */
	List<RelationshipNoteCategory> findDesignatedCategories();
}