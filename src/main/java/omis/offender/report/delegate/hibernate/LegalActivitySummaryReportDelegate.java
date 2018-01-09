package omis.offender.report.delegate.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offender.report.ActivitySummary;

/**
 * Legal Activity Summary Report Delegate.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 7, 2017)
 *@since OMIS 3.0
 *
 */
public class LegalActivitySummaryReportDelegate {

	/*private static final String FIND_BY_OFFENDER_QUERY_NAME =
			"findLegalActivitySummariesByOffender";*/
	
	private static final String
		FIND_COURT_CASE_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findCourtCaseActivitySummariesByOffender";
	
	private static final String
		FIND_CHARGES_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findChargesActivitySummariesByOffender";

	private static final String
		FIND_COMMIT_STATUS_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findCommitStatusActivitySummariesByOffender";
	
	private static final String
		FIND_OFFENSE_TERM_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findOffenseTermActivitySummariesByOffender";
	
	private static final String
		FIND_DETAINER_NOTIFICATION_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findDetainerNotificationActivitySummariesByOffender";
	
	private static final String FIND_CITATION_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findCitationActivitySummariesByOffender";
	
	private static final String
		FIND_PRSNTC_INVSTGTN_RQST_ACTVTY_BY_OFFENDER_QUERY_NAME =
			"findPresentenceInvestigationRequestActivitySummariesByOffender";
	
	private static final String
		FIND_PRISON_TERM_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findPrisonTermActivitySummariesByOffender";
	
	private static final String
		FIND_COURT_CASE_CONDITION_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findCourtCaseConditionActivitySummariesByOffender";
	
	private static final String FIND_TIER_DESIGNATIONS_BY_OFFENDER_QUERY_NAME =
			"findTierDesignationActivitySummariesByOffender";
	
	private static final String
		FIND_COURT_DOCUMENT_ACTIVITY_BY_OFFENDER_QUERY_NAME =
			"findCourtDocumentActivitySummariesByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory - Session Factory
	 */
	public LegalActivitySummaryReportDelegate(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Returns a list of Legal Activity Summaries found by the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return List of Legal Activity Summaries found by the
	 * specified Offender
	 */
	@SuppressWarnings("unchecked")
	public List<ActivitySummary> findByOffender(final Offender offender) {
		List<ActivitySummary> summaries = new ArrayList<ActivitySummary>();
		
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_COURT_CASE_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_PRSNTC_INVSTGTN_RQST_ACTVTY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_PRISON_TERM_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
					FIND_DETAINER_NOTIFICATION_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_CITATION_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_COMMIT_STATUS_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
					FIND_COURT_CASE_CONDITION_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_TIER_DESIGNATIONS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_OFFENSE_TERM_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_CHARGES_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.addAll(this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_COURT_DOCUMENT_ACTIVITY_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list());
		summaries.sort(null);
		
		return summaries.subList(0, 9);
	}
}
