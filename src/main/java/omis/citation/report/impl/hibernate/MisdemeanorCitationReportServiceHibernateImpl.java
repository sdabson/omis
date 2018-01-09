package omis.citation.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.citation.report.MisdemeanorCitationReportService;
import omis.citation.report.MisdemeanorCitationSummary;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of misdemeanor citation profile item.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0
 */
public class MisdemeanorCitationReportServiceHibernateImpl
	implements MisdemeanorCitationReportService {
	
	private static final String 
	FIND_MISDEMEANOR_CITATION_SUMMARY_BY_OFFENDER_QUERY_NAME 
		= "findMisdemeanorCitationSummaryByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/** Constructor
	 * @param sessionFactory - session factory.
	 */
	public MisdemeanorCitationReportServiceHibernateImpl (
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	public List<MisdemeanorCitationSummary> summarizeByOffender(
			Offender offender) {
		@SuppressWarnings("unchecked")
		List<MisdemeanorCitationSummary> summaries = this.sessionFactory
			.getCurrentSession().getNamedQuery(
				FIND_MISDEMEANOR_CITATION_SUMMARY_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		return summaries;
	}
}
