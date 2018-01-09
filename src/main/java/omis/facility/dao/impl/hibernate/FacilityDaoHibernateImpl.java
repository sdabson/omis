package omis.facility.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.FacilityDao;
import omis.facility.domain.Facility;
import omis.location.domain.Location;
import omis.organization.domain.Organization;

import org.hibernate.SessionFactory;

/**
 * Facility DAO Hibernate Implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public class FacilityDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Facility> 
	implements FacilityDao {
	
	/* Query names. */
	
	private static final String FIND_FACILITY_BY_LOCATION_QUERY_NAME
		= "findFacilityByLocation";
	
	private static final String FIND_BY_ORGANIZATION_BETWEEN_DATES_QUERY_NAME
		= "findFacilitiesByOrganizationBetweenDates";
	
	private static final String FIND_FACILITIES_BY_ORGANIZATION_QUERY_NAME
		= "findFacilitiesByOrganization";
	
	private static final String FIND_ALL_FACILITIES_QUERY_NAME
		= "findAllFacilities";
	
	private static final String FIND_QUERY_NAME = "findFacility";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findFacilityExcluding";

	/* Parameter names. */
	
	private static final String LOCATION_PARAM_NAME = "location";

	private static final String ORGANIZATION_PARAM_NAME = "organization";

	private static final String START_DATE_PARAM_NAME = "startDate";

	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_FACILITY_PARAM_NAME 
		= "excludedFacility";
	
	/**
	 * Instantiates a data access object for facility with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FacilityDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Facility> findAll() {
		@SuppressWarnings("unchecked")
		List<Facility> facilities = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_FACILITIES_QUERY_NAME)
				.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public Facility findFacilityByLocation(final Location location) {
		Facility facility = (Facility) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FACILITY_BY_LOCATION_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.uniqueResult();
		return facility;
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findByOrganizationBetweenDates(
			final Organization organization, final Date startDate,
			final Date endDate) {
		@SuppressWarnings("unchecked")
		List<Facility> facilities = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_ORGANIZATION_BETWEEN_DATES_QUERY_NAME)
				.setParameter(ORGANIZATION_PARAM_NAME, organization)
				.setTimestamp(START_DATE_PARAM_NAME, startDate)
				.setTimestamp(END_DATE_PARAM_NAME, endDate)
				.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findByOrganization(
			final Organization organization) {
		@SuppressWarnings("unchecked")
		List<Facility> facilities = this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_FACILITIES_BY_ORGANIZATION_QUERY_NAME)
			.setParameter(ORGANIZATION_PARAM_NAME, organization)
			.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public Facility find(final String name, final Location location) {
		Facility facility = (Facility) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(LOCATION_PARAM_NAME, location)
				.uniqueResult();
		return facility;
	}

	/** {@inheritDoc} */
	@Override
	public Facility findExcluding(final String name, final Location location, 
			final Facility excludedFacility) {
		Facility facility = (Facility) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(EXCLUDED_FACILITY_PARAM_NAME, excludedFacility)
				.uniqueResult();
		return facility;
	}
}