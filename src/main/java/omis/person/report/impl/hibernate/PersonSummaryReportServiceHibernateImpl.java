package omis.person.report.impl.hibernate;

import omis.person.domain.Person;
import omis.person.report.PersonSummary;
import omis.person.report.PersonSummaryReportService;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of report service for person summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 16, 2016)
 * @since OMIS 3.0
 */
public class PersonSummaryReportServiceHibernateImpl 
	implements PersonSummaryReportService {
	
	/* Parameter names */
	
	private static final String PERSON_PARAM_NAME = "person";

	/* Query names */
	
	private static final String SUMMARIZE_PERSON_QUERY_NAME
		= "summarizePerson";
	
	/* Session Factory */
	
	private final SessionFactory sessionFactory;
	
	/* Constructor */
	
	/**
	 * Instantiates a person summary report service with the specified
	 * session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public PersonSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override
	public PersonSummary summarize(Person person) {
		PersonSummary summary = (PersonSummary) 
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_PERSON_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.uniqueResult();
		return summary;
	}
}