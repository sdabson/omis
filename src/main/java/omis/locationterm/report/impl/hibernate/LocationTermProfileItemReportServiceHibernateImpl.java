package omis.locationterm.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.locationterm.report.LocationTermProfileItemReportService;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of report service for profile item for location
 * term.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class LocationTermProfileItemReportServiceHibernateImpl
		implements LocationTermProfileItemReportService {

	/* Query names. */
	
	private final static String FIND_FOR_OFFENDER_ON_DATE_QUERY_NAME
		= "findLocationTermByOffenderOnDate";
	
	/* Parameter names. */
	
	private final static String OFFENDER_PARAM_NAME = "offender";
	
	private final static String DATE_PARAM_NAME = "date";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for profile item
	 * for location term.
	 * 
	 * @param sessionFactory session factory
	 */
	public LocationTermProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public boolean findLocationExistenceByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		return sessionFactory.getCurrentSession().getNamedQuery(
				FIND_FOR_OFFENDER_ON_DATE_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setTimestamp(DATE_PARAM_NAME, effectiveDate)
			.uniqueResult() != null;
	}
}