package omis.alert.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.alert.report.OffenderAlertProfileItemReportService;
import omis.alert.report.OffenderAlertProfileItemSummary;
import omis.offender.domain.Offender;

/** Hibernate implementation of offender alert profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 28, 2016)
 * @since OMIS 3.0 */
public class OffenderAlertProfileItemReportServiceHibernateImpl 
	implements OffenderAlertProfileItemReportService {
	private static final String 
		FIND_OFFENDER_ALERT_PROFILE_ITEM_SUMMARY_BY_OFFENDER_AND_DATE_QUERY_NAME 
			= "findOffenderAlertProfileItemSummaryByOffenderAndDate";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String EFFECTIVE_DATE_PARAM_NAME 
		= "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public OffenderAlertProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenderAlertProfileItemSummary 
		findOffenderAlertProfileItemSummaryByOffenderAndDate(
			final Offender offender, 
			final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_OFFENDER_ALERT_PROFILE_ITEM_SUMMARY_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return ((OffenderAlertProfileItemSummary) q.uniqueResult());
	}
}
