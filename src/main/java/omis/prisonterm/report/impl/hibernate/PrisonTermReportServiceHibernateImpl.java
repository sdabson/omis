package omis.prisonterm.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.prisonterm.report.PrisonTermReportService;
import omis.prisonterm.report.PrisonTermSummary;

/**
 * Hibernate implementation of prison term report.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (March 17, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermReportServiceHibernateImpl
	implements PrisonTermReportService {
	
	private static final String FIND_PRISON_TERM_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findPrisonTermSummaryByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;

	/** Constructor
	 * @param sessionFactory - session factory.
	 */
	public PrisonTermReportServiceHibernateImpl (
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PrisonTermSummary> summarizeByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<PrisonTermSummary> summaries = this.sessionFactory
			.getCurrentSession().getNamedQuery(
					FIND_PRISON_TERM_SUMMARY_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		return summaries;
	}
	
}
