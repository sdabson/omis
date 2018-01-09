package omis.relationship.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.relationship.dao.RelationshipNoteCategoryDesignatorDao;
import omis.relationship.domain.RelationshipNoteCategory;
import omis.relationship.domain.RelationshipNoteCategoryDesignator;

/**
 * Hibernate implementation of designator for relationship category notes.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 13, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryDesignatorDaoHibernateImpl
		extends GenericHibernateDaoImpl<RelationshipNoteCategoryDesignator>
		implements RelationshipNoteCategoryDesignatorDao {

	/* Query names. */
	
	private static final String FIND_DESIGNATED_NOTE_CATEGORIES_QUERY_NAME
			= "findDesignatedRelationshipNoteCategories";
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of designator for relationship
	 * category notes.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public RelationshipNoteCategoryDesignatorDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public List<RelationshipNoteCategory> findDesignatedCategories() {
		@SuppressWarnings("unchecked")
		List<RelationshipNoteCategory> categories
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_DESIGNATED_NOTE_CATEGORIES_QUERY_NAME)
				.list();
		return categories;
	}
}