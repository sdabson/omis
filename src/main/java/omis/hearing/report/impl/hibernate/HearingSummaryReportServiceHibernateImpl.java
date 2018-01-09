package omis.hearing.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.hearing.domain.Hearing;
import omis.hearing.report.HearingSummary;
import omis.hearing.report.HearingSummaryReportService;
import omis.offender.domain.Offender;

/**
 * HearingSummaryReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (April 18, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingSummaryReportServiceHibernateImpl
	implements HearingSummaryReportService {
	
	private static final String FIND_HEARING_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findHearingSummariesByOffender";
	
	private static final String SUMMARIZE_HEARING_QUERY_NAME = "summarizeHearing";
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	private static final String HEARING_PARAMETER_NAME = "hearing";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public HearingSummaryReportServiceHibernateImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingSummary> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<HearingSummary> summaries = this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_HEARING_SUMMARIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.list();
		return summaries;
	}

	/**{@inheritDoc} */
	@Override
	public HearingSummary summarize(final Hearing hearing) {
		HearingSummary summary = (HearingSummary) this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_HEARING_QUERY_NAME)
				.setParameter(HEARING_PARAMETER_NAME, hearing)
				.uniqueResult();
		
		return summary;
	}

}
