package omis.detainernotification.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.detainernotification.domain.DetainerAgency;
import omis.detainernotification.report.DetainerAgencySummary;
import omis.detainernotification.report.DetainerSummary;
import omis.detainernotification.report.DetainerSummaryReportService;
import omis.offender.domain.Offender;

/**
 * 
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */
public class DetainerSummaryReportServiceHibernateImpl 
	implements DetainerSummaryReportService {
	
	/* Queries. */
	
	private static final String FIND_DETAINERS_BY_OFFENDER =
			"findDetainersByOffender";
	
	private static final String SUMMARIZE_DETAINER_AGENCY_QUERY_NAME =
			"summarizeDetainerAgency";
	
	/* Parameters. */
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	private static final String DETAINER_AGENCY_PARAM_NAME = "detainerAgency";
	
	/* Members. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public DetainerSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<DetainerSummary> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<DetainerSummary> detainerSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_DETAINERS_BY_OFFENDER)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.list();
		return detainerSummaries;
	}

	/**{@inheritDoc} */
	@Override
	public DetainerAgencySummary summarizeDetainerAgency(
			final DetainerAgency detainerAgency) {
		DetainerAgencySummary detainerAgencySummary = (DetainerAgencySummary)
				this.sessionFactory.getCurrentSession().getNamedQuery(
						SUMMARIZE_DETAINER_AGENCY_QUERY_NAME)
				.setParameter(DETAINER_AGENCY_PARAM_NAME, detainerAgency)
				.uniqueResult();
		
		return detainerAgencySummary;
	}
}