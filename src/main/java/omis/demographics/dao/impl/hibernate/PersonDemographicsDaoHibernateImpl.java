package omis.demographics.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.PersonDemographicsDao;
import omis.demographics.domain.PersonDemographics;
import omis.person.domain.Person;

/**
 * Hibernate implementation of data access object for person demographics.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 20, 2014)
 * @since OMIS 3.0
 */
public class PersonDemographicsDaoHibernateImpl
		extends GenericHibernateDaoImpl<PersonDemographics>
		implements PersonDemographicsDao {

	private static final String FIND_QUERY_NAME = "findPersonDemographics";
	
	private static final String FIND_BY_DEMOGRPHICS_QUERY_NAME 
		= "findByDemographics";
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String PERSON_DEMOGRAPHICS_PARAM_NAME = "demographics";

	/**
	 * Instantiates an Hibernate implementation of data access object for person
	 * demographics.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PersonDemographicsDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public PersonDemographics find(final Person person) {
		PersonDemographics personDemographics = (PersonDemographics)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.uniqueResult();
		return personDemographics;
	}
	
	/** {@inheritDoc} */
	@Override
	public PersonDemographics findByDemographics(
			final PersonDemographics demographics) {
		PersonDemographics personDemographics = (PersonDemographics)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_DEMOGRPHICS_QUERY_NAME)
				.setParameter(PERSON_DEMOGRAPHICS_PARAM_NAME, demographics)
				.uniqueResult();
		return personDemographics;
	}
}