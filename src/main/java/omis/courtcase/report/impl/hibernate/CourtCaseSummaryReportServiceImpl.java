package omis.courtcase.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtcase.report.CourtCaseSummary;
import omis.courtcase.report.CourtCaseSummaryReportService;
import omis.person.domain.Person;

/**
 * Implementation of report service for court case summary.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.2 (Aug 16, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseSummaryReportServiceImpl 
	implements CourtCaseSummaryReportService {

	/* Query names. */
	
	private static final String FIND_COURT_CASE_BY_DEFENDANT_QUERY_NAME
		= "findCourtCaseByDefendant";
	
	/* Parameters. */
	
	private static final String DEFENDANT_PARAM_NAME = "defendant";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Instantiates an instance of court case summary report service with the
	 * specified session factory.
	 * @param sessionFactory - session factory.
	 */
	CourtCaseSummaryReportServiceImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CourtCaseSummary> summarizeByPerson(final Person offender) {
		@SuppressWarnings("unchecked")
		List<CourtCaseSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_COURT_CASE_BY_DEFENDANT_QUERY_NAME)
				.setParameter(DEFENDANT_PARAM_NAME, offender)
				.list();
		return summaries;
	}
}