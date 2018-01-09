package omis.trackeddocument.report.impl.hibernate;

import java.util.List;

import omis.person.domain.Person;
import omis.trackeddocument.report.DocketDocumentReceivalSummary;
import omis.trackeddocument.report.DocketDocumentTrackingReportService;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of report service for docket document tracking.
 *
 * @author Yidong Li
 * @version 0.0.1 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class DocketDocumentTrackingReportServiceHibernateImpl
		implements DocketDocumentTrackingReportService {
	
	/* Query names. */
	
	private static final String SUMMARIZE_BY_DEFENDANT_QUERY_NAME
		= "summarizeDocketDocumentReceivalByDefendant";
	
	/* Parameter names. */
	
	private static final String DEFENDANT_PARAM_NAME = "defendant";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Instantiates an implementation of report service.
	 * 
	 * @param sessionFactory session factory
	 */
	public DocketDocumentTrackingReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<DocketDocumentReceivalSummary> summarizedByDefendant(final
			Person defendant) {
		@SuppressWarnings("unchecked")
		List<DocketDocumentReceivalSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(SUMMARIZE_BY_DEFENDANT_QUERY_NAME)
			.setParameter(DEFENDANT_PARAM_NAME, defendant)
			.list();
		return summaries;
	}
}