package omis.contact.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.contact.report.ContactSummary;
import omis.contact.report.ContactSummaryItemReportService;
import omis.offender.domain.Offender;

/**
 * Contact Summary Item Report Service Hibernate Implementation.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 26, 2017)
 *@since OMIS 3.0
 *
 */
public class ContactSummaryItemReportServiceHibernateImpl
		implements ContactSummaryItemReportService {
	
	private static final String FIND_CONTACT_SUMMARY_QUERY_NAME =
			"summarizeContactByPerson";
	
	private static final String OFFENDER_PARAM_NAME = "person";
	
	private SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory - Session Factory
	 */
	public ContactSummaryItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**{@inheritDoc} */
	@Override
	public ContactSummary findContactSummaryByOffender(
			final Offender offender) {
		ContactSummary contactSummary = (ContactSummary)
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_CONTACT_SUMMARY_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		
		return contactSummary;
	}

}
