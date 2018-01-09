package omis.prisonterm.report.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.prisonterm.report.PrisonTermSummary;
import omis.prisonterm.report.PrisonTermSummaryItemReportService;

/**
 * PrisonTermSummaryItemReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 6, 2017)
 *@since OMIS 3.0
 *
 */
public class PrisonTermSummaryItemReportServiceHibernateImpl
		implements PrisonTermSummaryItemReportService {
	
	private static final String
		FIND_PRISON_TERM_SUMMARY_BY_OFFENDER_AND_EFFECTIVE_DATE_QUERY_NAME =
			"findPrisonTermSummaryByOffenderAndEffectiveDate";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * 
	 */
	public PrisonTermSummaryItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**{@inheritDoc} */
	@Override
	public PrisonTermSummary findPrisonTermSummaryByOffender(
			final Offender offender) {
		PrisonTermSummary prisonTermSummary = (PrisonTermSummary)
				this.sessionFactory.getCurrentSession().getNamedQuery(
			FIND_PRISON_TERM_SUMMARY_BY_OFFENDER_AND_EFFECTIVE_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		
		return prisonTermSummary;
	}

}
