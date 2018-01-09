package omis.person.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.person.dao.AlternativeNameAssociationDao;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.Person;
import omis.person.domain.PersonName;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for alternative name
 * associations.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (Sept 24, 2014)
 * @since OMIS 3.0
 */
public class AlternativeNameAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<AlternativeNameAssociation>
		implements AlternativeNameAssociationDao {

	/* Query names. */
	
	private static final String FIND_BY_PERSON_QUERY_NAME
			= "findAlternativeNameAssociationsByPerson";
	
	private static final String FIND_BY_PERSON_ON_DATE_QUERY_NAME
			= "findAlternativeNameAssociationsByPersonOnDate";

	private static final String FIND_ALTERNATIVE_NAME_ASSOCIATION_QUERY_NAME
		= "findAlternativeNameAssociation";
	
	private static final String 
	FIND_ALTERNATIVE_NAME_ASSOCIATION_EXCLUDING_QUERY_NAME
		= "findAlternativeNameAssociationExcluding";
	
	/* Parameter names. */
	
	private static final String PERSON_PARAM_NAME = "person";

	private static final String DATE_PARAM_NAME = "date";
	
	private static final String ALTERNATIVE_NAME_CATEGORY_PARAM_NAME
		= "alternativeNameCategory";

	private static final String PERSON_NAME_PARAM_NAME = "personName";
	
	private static final String DATE_RANGE_PARAM_NAME = "dateRange";
	
	private static final String ALTERNATIVE_NAME_ASSOCIATION_PARAM_NAME
		= "association";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * alternative name associations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AlternativeNameAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<AlternativeNameAssociation> findByPerson(
			final Person person) {
		@SuppressWarnings("unchecked")
		List<AlternativeNameAssociation> associations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_PERSON_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.list();
		return associations;
	}

	/** {@inheritDoc} */
	@Override
	public List<AlternativeNameAssociation> findByPersonOnDate(
			final Person person, final Date date) {
		@SuppressWarnings("unchecked")
		List<AlternativeNameAssociation> associations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_PERSON_ON_DATE_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(DATE_PARAM_NAME, date)
				.list();
		return associations;
	}

	/** {@inheritDoc} */
	@Override
	public boolean find(final PersonName name, 
			final AlternativeNameCategory category, final Person person,
			final DateRange dateRange) {
		boolean alternativeNameFound = (boolean) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALTERNATIVE_NAME_ASSOCIATION_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(ALTERNATIVE_NAME_CATEGORY_PARAM_NAME, category)
				.setParameter(PERSON_NAME_PARAM_NAME, name)
				.setParameter(DATE_RANGE_PARAM_NAME, dateRange)
				.uniqueResult();
		return alternativeNameFound;
	}

	/** {@inheritDoc} */
	@Override
	public boolean findExcluding(AlternativeNameAssociation association,
			PersonName name, AlternativeNameCategory category, Person person,
			DateRange dateRange) {
		boolean alternativeNameFound = (boolean) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_ALTERNATIVE_NAME_ASSOCIATION_EXCLUDING_QUERY_NAME)
				.setParameter(ALTERNATIVE_NAME_ASSOCIATION_PARAM_NAME,
						association)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(ALTERNATIVE_NAME_CATEGORY_PARAM_NAME, category)
				.setParameter(PERSON_NAME_PARAM_NAME, name)
				.setParameter(DATE_RANGE_PARAM_NAME, dateRange)
				.uniqueResult();
		return alternativeNameFound;
	}
}