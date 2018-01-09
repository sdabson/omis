package omis.violationevent.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.violationevent.report.ViolationEventSummary;
import omis.violationevent.report.ViolationEventSummaryReportService;

/**
 * ViolationEventSummaryReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventSummaryReportServiceHibernateImpl
	implements ViolationEventSummaryReportService {
	
	private static final String
		FIND_VIOLATION_EVENT_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findViolationEventSummariesByOffender";
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	private final SessionFactory sessionFactory;

	/**
	 * Constructor for ViolationEventSummaryReportServiceHibernateImpl
	 * @param sessionFactory - SessionFactory
	 */
	public ViolationEventSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public List<ViolationEventSummary> findByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<ViolationEventSummary> violationEventSummaries = 
			this.sessionFactory.getCurrentSession()
			.getNamedQuery(
					FIND_VIOLATION_EVENT_SUMMARIES_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAMETER_NAME, offender)
			.list();
		
		return violationEventSummaries;
	}

}
