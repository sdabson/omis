package omis.relationship.service.delegate;

import java.util.List;

import omis.relationship.dao.RelationshipNoteCategoryDesignatorDao;
import omis.relationship.domain.RelationshipNoteCategory;

/**
 * Delegate for designator for relationship note categories.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryDesignatorDelegate {
	
	/* Data access object. */
	
	private final RelationshipNoteCategoryDesignatorDao
	relationshipNoteCategoryDesignatorDao;
	
	/* Constructor. */
	
	/**
	 * Instantiates delegate for designator for relationship note categories.
	 * 
	 * @param relationshipNoteCategoryDesignatorDao data access object for
	 * relationship note categories
	 */
	public RelationshipNoteCategoryDesignatorDelegate(
			final RelationshipNoteCategoryDesignatorDao
				relationshipNoteCategoryDesignatorDao) {
		this.relationshipNoteCategoryDesignatorDao
			= relationshipNoteCategoryDesignatorDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns designated relationship note categories.
	 * 
	 * @return designated relationship note categories
	 */
	public List<RelationshipNoteCategory> findDesignatedCategories() {
		return this.relationshipNoteCategoryDesignatorDao
				.findDesignatedCategories();
	}
}