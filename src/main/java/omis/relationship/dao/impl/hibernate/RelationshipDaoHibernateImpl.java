package omis.relationship.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;
import omis.relationship.dao.RelationshipDao;
import omis.relationship.domain.Relationship;

/**
 * Hibernate implementation of data access object for relationships.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 21, 2013)
 * @since OMIS 3.0
 */
public class RelationshipDaoHibernateImpl
		extends GenericHibernateDaoImpl<Relationship>
		implements RelationshipDao {

	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * relationships with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public RelationshipDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public Relationship findByPeople(final Person firstPerson,
			final Person secondPerson) {
		Relationship relationship = (Relationship) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findRelationshipByPeople")
				.setParameter("firstPerson", firstPerson)
				.setParameter("secondPerson", secondPerson)
				.uniqueResult();
		return relationship;
	}
}