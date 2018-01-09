package omis.probationterm.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.probationterm.report.ProbationTermSummary;
import omis.probationterm.report.ProbationTermSummaryItemReportService;

/**
 * ProbationTermSummaryItemReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 2, 2017)
 *@since OMIS 3.0
 *
 */
public class ProbationTermSummaryItemReportServiceHibernateImpl
		implements ProbationTermSummaryItemReportService {
	
	private static final String FIND_PROBATION_TERM_SUMMARY_BY_OFFENDER_QUERY =
			"findProbationTermSummaryItemByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public ProbationTermSummaryItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public ProbationTermSummary findMaxProbationTermExpirationDate(
			final Offender offender, final Date effectiveDate) {
		ProbationTermSummary probationTermSummary = (ProbationTermSummary)
				this.sessionFactory.getCurrentSession().getNamedQuery(
						FIND_PROBATION_TERM_SUMMARY_BY_OFFENDER_QUERY)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.uniqueResult();
				
		return probationTermSummary;
	}

}
