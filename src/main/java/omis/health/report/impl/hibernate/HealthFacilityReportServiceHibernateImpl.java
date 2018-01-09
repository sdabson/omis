package omis.health.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.report.HealthFacilityReportService;
import omis.person.domain.Person;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of report service for health facilities.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 9, 2016)
 * @since OMIS 3.0
 */
public class HealthFacilityReportServiceHibernateImpl
		implements HealthFacilityReportService {
	
	/* Query names. */
	
	private static final String FIND_FOR_STAFF_ON_DATE_QUERY_NAME
		= "findHealthFacilitiesForStaffMemberOnDate";
	
	private static final String FIND_ALL_QUERY_NAME
		= "findHealthFacilities";
	
	/* Parameter names. */
	private static final String STAFF_MEMBER_PARAM_NAME = "staffMember";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Instantiates Hibernate implementation of report service for health
	 * facilities.
	 * 
	 * @param sessionFactory session factory
	 */
	public HealthFacilityReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findHealthFacilitiesForStaff(
			final Person staffMember, final Date date) {
		@SuppressWarnings("unchecked")
		List<Facility> facilities = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_FOR_STAFF_ON_DATE_QUERY_NAME)
				.setParameter(STAFF_MEMBER_PARAM_NAME, staffMember)
				.setTimestamp(DATE_PARAM_NAME, date)
				.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findHealthFaciliites() {
		@SuppressWarnings("unchecked")
		List<Facility> facilities = this.sessionFactory
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return facilities;
	}

}