package omis.person.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.dao.PersonNameDao;
import omis.person.domain.Person;
import omis.person.domain.PersonName;

/**
 * Hibernate implementation of data access object for person names.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (Sept 24, 2014)
 * @since OMIS 3.0
 */
public class PersonNameDaoHibernateImpl
		extends GenericHibernateDaoImpl<PersonName>
		implements PersonNameDao {

	/* Query names. */
	
	private static final String FIND_ALTERNATIVE_NAMES_QUERY_NAME
			= "findAlternativePersonNames";
	
	private static final String FIND_PERSON_NAME_QUERY_NAME
		= "findPersonName";
	
	private static final String FIND_PERSON_NAME_EXCLUDING_QUERY_NAME
		= "findPersonNameExcluding";
	
	/* Parameter names. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String FIRST_NAME_PARAMETER_NAME = "firstName";
	
	private static final String LAST_NAME_PARAMETER_NAME = "lastName";
	
	private static final String MIDDLE_NAME_PARAMETER_NAME = "middleName";
	
	private static final String SUFFIX_PARAMETER_NAME = "suffix";
	
	private static final String PERSON_NAME_PARAM_NAME = "name";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * person names.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PersonNameDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<PersonName> findAlternativeNames(final Person person) {
		@SuppressWarnings("unchecked")
		List<PersonName> personNames = (List<PersonName>)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_ALTERNATIVE_NAMES_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.list();
		return personNames;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean find(final Person person, final String firstName, 
			final String lastName, final String middleName, 
			final String suffix) {
		boolean nameFound = (boolean) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PERSON_NAME_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(FIRST_NAME_PARAMETER_NAME, firstName)
				.setParameter(LAST_NAME_PARAMETER_NAME, lastName)
				.setParameter(MIDDLE_NAME_PARAMETER_NAME, middleName)
				.setParameter(SUFFIX_PARAMETER_NAME, suffix)
				.uniqueResult();
		return nameFound;
	}

	@Override
	public boolean findExcluding(PersonName name, String firstName,
			String lastName, String middleName, String suffix) {
		boolean nameFound = (boolean) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PERSON_NAME_EXCLUDING_QUERY_NAME)
				.setParameter(PERSON_NAME_PARAM_NAME, name)
				.setParameter(PERSON_PARAM_NAME, name.getPerson())
				.setParameter(FIRST_NAME_PARAMETER_NAME, firstName)
				.setParameter(LAST_NAME_PARAMETER_NAME, lastName)
				.setParameter(MIDDLE_NAME_PARAMETER_NAME, middleName)
				.setParameter(SUFFIX_PARAMETER_NAME, suffix)
				.uniqueResult();
		return nameFound;
	}
}