package omis.financial.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.financial.report.FinancialProfileItemReportService;
import omis.financial.report.FinancialProfileItemSummary;
import omis.offender.domain.Offender;

/** Hibernate implementation of financial profile item report.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 10, 2017)
 * @since OMIS 3.0 */ 
public class FinancialProfileItemReportServiceHibernateImpl 
	implements FinancialProfileItemReportService {
	private static final String 
		FIND_PROFILE_ITEM_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findFinancialProfileItemSummayByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public FinancialProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public FinancialProfileItemSummary findProfileItemSummaryByOffender(
		final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession()
			.getNamedQuery(FIND_PROFILE_ITEM_SUMMARY_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		
		return (FinancialProfileItemSummary) q.uniqueResult();
	}
}
