package omis.workrestriction.report.impl.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.workrestriction.report.WorkRestrictionProfileSummaryReportService;

/** Hibernate implementation of work restriction profile summary report 
 * service.
 * @author Ryan Johns
 * @version 0.1.0 (Sep 2, 2016)
 * @since OMIS 3.0 */
public class WorkRestrictionProfileSummaryReportServiceHibernateImpl
		implements WorkRestrictionProfileSummaryReportService {
	private static final String FIND_COUNT_BY_OFFENDER_QUERY_NAME 
		= "findWorkRestrictionCountByOffender";
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor.
	 * @param sessionFactory - session factory. */
	public WorkRestrictionProfileSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer findCountByOffender(final Offender offender) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_COUNT_BY_OFFENDER_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		return Integer.valueOf(((Long) q.uniqueResult()).intValue());
	}
}