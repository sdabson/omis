package omis.probationterm.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtcase.domain.CourtCase;
import omis.offender.domain.Offender;
import omis.probationterm.report.ProbationTermReportService;
import omis.probationterm.report.ProbationTermSummary;

/**
 * Hibernate implementation of probation term report service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (May 24, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermReportServiceHibernateImpl 
	implements ProbationTermReportService {
	
	/* Query names. */
	
	private static final String SUMMARIZE_BY_OFFENDER_QUERY_NAME 
		= "findProbationTermSummaryByOffender";

	private static final String SUMMARIZE_BY_COURT_CASE_QUERY_NAME 
		= "findProbationTermSummaryByCourtCase";
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private static final String COURT_CASE_PARAM_NAME = "courtCase";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public ProbationTermReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ProbationTermSummary> summarizeByOffender(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ProbationTermSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProbationTermSummary> summarizeByCourtCase(CourtCase courtCase) {
		@SuppressWarnings("unchecked")
		List<ProbationTermSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_COURT_CASE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.list();
		return summaries;
	}

}
