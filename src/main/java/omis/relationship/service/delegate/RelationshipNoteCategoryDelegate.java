package omis.relationship.service.delegate;

import omis.instance.factory.InstanceFactory;
import omis.relationship.dao.RelationshipNoteCategoryDao;
import omis.relationship.domain.RelationshipNoteCategory;
import omis.relationship.exception.RelationshipNoteCategoryExistsException;

/**
 * Delegate for relationship note categories.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 7, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryDelegate {

	/* Instance factory. */
	
	private final InstanceFactory<RelationshipNoteCategory>
	relationshipNoteCategoryInstanceFactory;
	
	/* Data access object. */
	
	private final RelationshipNoteCategoryDao relationshipNoteCategoryDao;
	
	/* Constructor. */
	
	/**
	 * Instantiates delegate for relationship note categories.
	 * 	 * @param relationshipNoteCategoryInstanceFactory relationship note
	 * category instance factory
	 * @param relationshipNoteCategoryDao data access object for relationship
	 * note category
	 */
	public RelationshipNoteCategoryDelegate(
			final InstanceFactory<RelationshipNoteCategory>
			relationshipNoteCategoryInstanceFactory,
			final RelationshipNoteCategoryDao relationshipNoteCategoryDao) {
		this.relationshipNoteCategoryInstanceFactory
			= relationshipNoteCategoryInstanceFactory;
		this.relationshipNoteCategoryDao = relationshipNoteCategoryDao;
	}
	
	/* Methods. */
	
	/**
	 * Creates relationship note category.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @return newly created relationship note category
	 * @throws RelationshipNoteCategoryExistsException if relationship note
	 * category exists
	 */
	public RelationshipNoteCategory create(
			final String name, final Short sortOrder)
				throws RelationshipNoteCategoryExistsException {
		if (this.relationshipNoteCategoryDao.find(name) != null) {
			throw new RelationshipNoteCategoryExistsException(
					"Relationship note category exists");
		}
		RelationshipNoteCategory category
			= this.relationshipNoteCategoryInstanceFactory.createInstance();
		category.setName(name);
		category.setSortOrder(sortOrder);
		return this.relationshipNoteCategoryDao.makePersistent(category);
	}
}