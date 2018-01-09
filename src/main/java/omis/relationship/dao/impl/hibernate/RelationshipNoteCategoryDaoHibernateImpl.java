package omis.relationship.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.relationship.dao.RelationshipNoteCategoryDao;
import omis.relationship.domain.RelationshipNoteCategory;

/**
 * Hibernate implementation of data access object for relationship note
 * categories.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 13, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<RelationshipNoteCategory>
		implements RelationshipNoteCategoryDao {
	
	/* Queries. */
	
	private static final String FIND_QUERY_NAME
			= "findRelationshipNoteCategory";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for
	 * relationship note categories.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public RelationshipNoteCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public RelationshipNoteCategory find(final String name) {
		RelationshipNoteCategory category = (RelationshipNoteCategory)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return category;
	}
}