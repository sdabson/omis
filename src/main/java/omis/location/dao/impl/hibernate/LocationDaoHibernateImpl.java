package omis.location.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.dao.LocationDao;
import omis.location.domain.Location;
import omis.organization.domain.Organization;
import omis.region.domain.State;

/**
 * Hibernate implementation of data access object for locations.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Nov 28, 2017)
 * @since OMIS 3.0
 */
public class LocationDaoHibernateImpl
		extends GenericHibernateDaoImpl<Location>
		implements LocationDao {

	/* Queries. */
	
	private static final String FIND_BY_ORGANIZATIONS_QUERY_NAME = 
			"findLocationsByOrganizations";

	private static final String FIND_BY_ORGANIZATION_IN_STATE_QUERY_NAME = 
			"findLocationsByOrganizationInState";

	private static final String FIND_JAIL_LOCATIONS_QUERY_NAME = 
			"findJailLocations";
	
	private static final String FIND_PRERELEASE_LOCATIONS_QUERY_NAME = 
			"findPrereleaseLocations";
	
	private static final String FIND_FACILITY_LOCATIONS_QUERY_NAME = 
			"findFacilityLocations";
	
	private static final String 
			FIND_COMMUNITY_SUPERVISION_OFFICE_LOCATIONS_QUERY_NAME = 
			"findCommunitySupervisionOfficeLocations";
	
	private static final String 
			FIND_TREATMENT_AND_SACTION_CENTER_LOCATIONS_QUERY_NAME = 
			"findTreatmentAndSactionCenterLocations";
	
	private static final String FIND_BOARD_ITINERARY_LOCATIONS_QUERY_NAME = 
			"findAllBoardItineraryLocations";
	
	/* Parameters. */
	
	private static final String ORGANIZATIONS_PARAM_NAME = "organizations";
	
	private static final String STATE_PARAM_NAME = "state";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * locations with specified resources.
	 * 
	 * @param sessionFactory sessionFactory
	 * @param entityName entity name
	 */
	public LocationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Location> findAll() {
		@SuppressWarnings("unchecked")
		List<Location> locations = getSessionFactory().getCurrentSession()
				.getNamedQuery("findAllLocationsOrderedByName").list();
		return locations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Location> findByOrganizationOnDate(
			final Organization organization, final Date date) {
		@SuppressWarnings("unchecked")
		List<Location> locations = getSessionFactory().getCurrentSession()
				.getNamedQuery(
						"findLocationByOrganizationOnDateOrderedByName")
				.setParameter("organization", organization)
				.setParameter("date", date)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findByOrganization(
			final Organization organization) {
		@SuppressWarnings("unchecked")
		List<Location> locations = getSessionFactory().getCurrentSession()
				.getNamedQuery("findLocationByOrganizationOrderedByName")
				.setParameter("organization", organization)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findOnDate(final Date date) {
		@SuppressWarnings("unchecked")
		List<Location> locations = getSessionFactory().getCurrentSession()
				.getNamedQuery(
						"findLocationOnDateOrderedByName")
				.setParameter("date", date)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findByOrganizations(
			final Organization... organizations) {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_ORGANIZATIONS_QUERY_NAME)
				.setParameterList(ORGANIZATIONS_PARAM_NAME, organizations)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findByOrganizationsInState(
			final State state, final Organization... organizations) {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_ORGANIZATION_IN_STATE_QUERY_NAME)
				.setParameterList(ORGANIZATIONS_PARAM_NAME, organizations)
				.setParameter(STATE_PARAM_NAME, state)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findJailLocations() {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_JAIL_LOCATIONS_QUERY_NAME)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findPrereleaseLocations() {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PRERELEASE_LOCATIONS_QUERY_NAME)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findFacilityLocations() {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_FACILITY_LOCATIONS_QUERY_NAME)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findCommunitySupervisionOfficeLocations() {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_COMMUNITY_SUPERVISION_OFFICE_LOCATIONS_QUERY_NAME)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findTreatmentAndSactionCenterLocations() {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_TREATMENT_AND_SACTION_CENTER_LOCATIONS_QUERY_NAME)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findAllBoardItineraryLocations() {
		@SuppressWarnings("unchecked")
		List<Location> locations = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BOARD_ITINERARY_LOCATIONS_QUERY_NAME)
				.list();
		return locations;
	}
}