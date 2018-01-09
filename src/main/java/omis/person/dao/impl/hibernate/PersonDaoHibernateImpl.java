package omis.person.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.dao.PersonDao;
import omis.person.domain.Person;

/**
 * Hibernate entity configurable implementation of data access object for
 * persons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 7, 2012)
 * @since OMIS 3.0
 */
public class PersonDaoHibernateImpl
		extends GenericHibernateDaoImpl<Person>
		implements PersonDao {

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * persons.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PersonDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
}
