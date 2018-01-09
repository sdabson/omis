package omis.substance.report.impl.hibernate;

import java.util.Collections;
import java.util.List;

import omis.offender.domain.Offender;
import omis.substance.report.SubstanceTestSummary;
import omis.substance.report.SubstanceSummaryService;

import org.hibernate.SessionFactory;

/**
 * Substance Summary Service Hibernate Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 24, 2013)
 * @since OMIS 3.0
 */
public class SubstanceSummaryServiceHibernateImpl 
	implements SubstanceSummaryService {

	/* Query names. */
	
	private static final String SUMMARIZE_BY_OFFENDER_QUERY_NAME 
		= "summarizeSubstanceByOffender";
	
	private static final String SUMMARIZE_REQUESTS_BY_OFFENDER_QUERY_NAME
		= "summarizeSubstanceSampleRequestsByOffender";
	
	private static final String FIND_NON_SAMPLE_SUMMARIES_QUERY_NAME 
		= "summarizeFulfilledRequestsWithoutSample";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	/* Helpers */
	
	private SessionFactory sessionFactory;

	/**
	 * Instantiates a default instance of substance summary service
	 * hibernate implementation.
	 */
	public SubstanceSummaryServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubstanceTestSummary> summarizeSubstanceTestsByOffender(
			final Offender offender) {
		List<SubstanceTestSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		List<SubstanceTestSummary> requestSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_REQUESTS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		summaries.addAll(requestSummaries);
		List<SubstanceTestSummary> nonSampleSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_NON_SAMPLE_SUMMARIES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		summaries.addAll(nonSampleSummaries);
		Collections.sort(summaries, Collections.reverseOrder());
		return summaries;
	}
}