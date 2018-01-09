package omis.locationterm.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.locationterm.report.LocationTermReportService;
import omis.locationterm.report.LocationTermSummary;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of location term report service.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class LocationTermReportServiceHibernateImpl
		implements LocationTermReportService {
	
	/* Query names. */
	
	private static final String SUMMARIZE_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "summarizeLocationTermsByOffenderOnDate";
	
	/* Model keys. */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String EFFECTIVE_DATE_MODEL_KEY = "effectiveDate";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of location term report service.
	 * 
	 * @param sessionFactory session factory
	 */
	public LocationTermReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<LocationTermSummary> summarizeByOffenderOnDate(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<LocationTermSummary> summaries
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_MODEL_KEY, offender)
				.setTimestamp(EFFECTIVE_DATE_MODEL_KEY, effectiveDate)
				.list();
		return summaries;
	}
}