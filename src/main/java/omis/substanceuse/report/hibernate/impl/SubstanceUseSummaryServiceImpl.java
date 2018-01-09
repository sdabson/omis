package omis.substanceuse.report.hibernate.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.report.SubstanceUseSummary;
import omis.substanceuse.report.SubstanceUseSummaryService;
import omis.substanceuse.report.UseAffirmationSummary;
import omis.substanceuse.report.UseTermSummary;

/**
 * Substance use summary service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */
public class SubstanceUseSummaryServiceImpl 
	implements SubstanceUseSummaryService {

	/* Query names. */
	
	private static final String FIND_USE_SUMMARIES_QUERY_NAME 
		= "findSubstanceUseSummariesByOffender";
	
	private static final String FIND_USE_TERM_SUMMARIES_QUERY_NAME
		= "findUseTermSummariesByUse";
	
	private static final String FIND_USE_AFFIRMATIONS_SUMMARIES_QUERY_NAME
		= "findUseAffirmationsByUse";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String SUBSTANCE_USE_PARAM_NAME = "substanceUse";
	
	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates an instance of substance use summary with the specified
	 * session factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public SubstanceUseSummaryServiceImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SubstanceUseSummary> findUseSummaries(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<SubstanceUseSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_USE_SUMMARIES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<UseTermSummary> findUseTermSummaries(
			final SubstanceUse substanceUse) {
		@SuppressWarnings("unchecked")
		List<UseTermSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_USE_TERM_SUMMARIES_QUERY_NAME)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, substanceUse)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<UseAffirmationSummary> findUseAffirmationSummaries(
			final SubstanceUse substanceUse) {
		@SuppressWarnings("unchecked")
		List<UseAffirmationSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_USE_AFFIRMATIONS_SUMMARIES_QUERY_NAME)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, substanceUse)
				.list();
		return summaries;
	}
}