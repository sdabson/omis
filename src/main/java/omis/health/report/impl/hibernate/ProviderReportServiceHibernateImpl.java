package omis.health.report.impl.hibernate;



import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.report.ProviderReportService;
import omis.health.report.ProviderSummary;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of the Health provider report service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 5, 2014)
 * @since OMIS 3.0 */
public class ProviderReportServiceHibernateImpl
		implements ProviderReportService {
	private static final String
	FIND_HEALTH_PROVIDERS_BY_FACILITY_DATERANGE_QUERY =
		"findHealthProvidersByFacilityDateRange";

	private static final String
	FIND_HEALTH_PROVIDERS_BY_FACILITY_DATE_QUERY =
		"findHealthProvidersByFacilityDate";

	private final SessionFactory sessionFactory;

	/** Constructor.
	 * @param sessionFactory session factory. */
	public ProviderReportServiceHibernateImpl(
			final SessionFactory sessionFactory)  {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProviderSummary> findHealthProviders(final Facility facility,
			final Date startDate, final Date endDate) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_HEALTH_PROVIDERS_BY_FACILITY_DATERANGE_QUERY);

		q.setParameter("facility", facility);
		q.setTimestamp("startDate", startDate);
		q.setTimestamp("endDate", endDate);

		return (List<ProviderSummary>) q.list();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProviderSummary> findHealthProviders(final Facility facility,
			final Date date) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_HEALTH_PROVIDERS_BY_FACILITY_DATE_QUERY);

		q.setParameter("facility", facility);
		q.setTimestamp("date", date);

		return (List<ProviderSummary>) q.list();
	}
}
