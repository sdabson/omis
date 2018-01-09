package omis.vehicle.report.impl.hibernate;

import java.util.List;
import java.util.ArrayList;

import omis.vehicle.report.OffenderVehicleSummaryReportService;
import omis.vehicle.report.OffenderVehicleSummary;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of offender vehicle summary report service.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 7, 2014)
 * @since OMIS 3.0
 */
public class OffenderVehicleSummaryReportServiceHibernateImpl
		implements OffenderVehicleSummaryReportService {

	/* Queries */
	private static final String FIND_VEHICLE_SUMMARIES_BY_OFFENDER_QUERY_NAME 
		= "findVehicleSummariesByOffender";
	
	/* Parameters */
	private final static String OFFENDER_PARAM_NAME = "offender";
	
	private SessionFactory sessionFactory;
		
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of offender vehicle summary 
	 * report service
	 * @param sessionFactory session factory
	 */

	public OffenderVehicleSummaryReportServiceHibernateImpl(final SessionFactory
		sessionFactory) {
			this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	/** {@inheritDoc} */

	@Override 
	public List<OffenderVehicleSummary> findByOffender(
		final Offender offender) {
		List<OffenderVehicleSummary> summaries
			= new ArrayList<OffenderVehicleSummary>();
		@SuppressWarnings("unchecked")
		List<OffenderVehicleSummary> internalSummaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_VEHICLE_SUMMARIES_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		summaries.addAll(internalSummaries); 
		return summaries;
	}

}