package omis.facility.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.facility.report.FacilityStaffReportService;
import omis.person.domain.Person;

import org.hibernate.Query;
import org.hibernate.SessionFactory;


/** Implementation of staff assignment report service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0
 * @deprecated use module specific report service for facility/staff lookup;
 * consider removal - SA
 */
@Deprecated
public class FacilityStaffReportServiceHibernateImpl
	implements FacilityStaffReportService {
	private final SessionFactory sessionFactory;
	private final static String FIND_FACILITIES_BY_STAFF_QUERY =
			"findFacilitiesByStaffAndDate";
	private final static String FIND_ALL_FACILITIES =
			"FindAllFacilities";



	/** Constructor. */
	public FacilityStaffReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/** {@inheritDoc} */
	@Override
	public List<Facility> findFacilitiesByStaff(final Person staff,
			final Date date) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_FACILITIES_BY_STAFF_QUERY);
		q.setParameter("staff", staff);
		q.setDate("date", date);

		@SuppressWarnings("unchecked")
		final List<Facility> result = q.list();

		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findAllFacilities() {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_ALL_FACILITIES);

		@SuppressWarnings("unchecked")
		final List<Facility> result = q.list();

		return result;
	}

}
