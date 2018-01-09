package omis.location.report.impl.hibernate;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.location.report.LocationProfileItemReportService;
import omis.offender.domain.Offender;

/** Hibernate implementation for location profile item report service.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public class LocationProfileItemReportServiceHibernateImpl 
	implements LocationProfileItemReportService {
	private static final String FIND_LOCATION_TERM_BY_OFFENDER_ON_DATE
		= "findLocationTermByOffenderOnDate";
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String DATE_PARAM_NAME = "date";
	
	private final SessionFactory sessionFactory;
	
	/** constructor.
	 * @param sessionFactory - session factory. */
	public LocationProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean findLocationTermExistenceByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_LOCATION_TERM_BY_OFFENDER_ON_DATE);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setDate(DATE_PARAM_NAME, effectiveDate);
		return (q.uniqueResult() != null);
	}
}
