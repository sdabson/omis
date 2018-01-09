package omis.contact.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.contact.report.ContactReportService;
import omis.contact.report.ContactSummary;
import omis.person.domain.Person;

/**
 * Hibernate implementation of service to report contacts.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 12, 2016)
 * @since OMIS 3.0
 */
public class ContactReportServiceHibernateImpl
		implements ContactReportService {

	/* Query names. */
	
	private static final String SUMMARIZE_BY_PERSON_QUERY_NAME
		= "summarizeContactByPerson";

	/* Parameter names. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	/* Session factories. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of service to report contacts.
	 * 
	 * @param sessionFactory session factory
	 */
	public ContactReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public ContactSummary summarizeByPerson(final Person person) {
		ContactSummary summary = (ContactSummary) this.sessionFactory
				.getCurrentSession().getNamedQuery(
						SUMMARIZE_BY_PERSON_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.uniqueResult();
		return summary;
	}
}