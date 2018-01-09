package omis.location.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.location.domain.Location;
import omis.organization.domain.Organization;
import omis.region.domain.State;

/**
 * Data access object for locations.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Nov 28, 2017)
 * @since OMIS 3.0
 */
public interface LocationDao
		extends GenericDao<Location> {

	/**
	 * Returns locations from which an organization operated during a
	 * specified date ordered by organization name and date range.
	 * 
	 * @param organization organization the locations of which to return
	 * @param date date during which organization operated from returned
	 * locations
	 * @return ordered locations from which an organization operated during
	 * specified date
	 */
	List<Location> findByOrganizationOnDate(Organization organization,
			Date date);
	
	/**
	 * Returns locations for a specified organization ordered by organization
	 * name ordered by organization name and date range.
	 * 
	 * @param organization organization the locations of which to return
	 * @return ordered locations for specified organization ordered
	 */
	List<Location> findByOrganization(Organization organization);
	
	/**
	 * Returns locations available on date.
	 * 
	 * @param date date
	 * @return locations available on date
	 */
	List<Location> findOnDate(Date date);

	/**
	 * Returns locations by organizations.
	 * 
	 * @param organizations organizations
	 * @return locations by organizations
	 */
	List<Location> findByOrganizations(Organization... organizations);

	/**
	 * Returns locations by organizations in State.
	 * 
	 * @param state State
	 * @param organizations organizations
	 * @return locations by organizations
	 */
	List<Location> findByOrganizationsInState(
			State state, Organization... organizations);

	/**
	 * Returns locations associated with jails.
	 * 
	 * @return locations associated with jails
	 */
	List<Location> findJailLocations();

	/**
	 * Returns locations associated with prerelease locations.
	 * 
	 * @return locations associated with prerelease locations
	 */
	List<Location> findPrereleaseLocations();

	/**
	 * Returns locations associated with facilities.
	 * 
	 * @return locations associated with facilities
	 */
	List<Location> findFacilityLocations();

	/**
	 * Returns locations associated with community supervision offices.
	 * 
	 * @return locations associated with community supervision offices
	 */
	List<Location> findCommunitySupervisionOfficeLocations();

	/**
	 * Returns locations associated with treatment and sanction centers.
	 * 
	 * @return locations associated with treatment and sanction centers
	 */
	List<Location> findTreatmentAndSactionCenterLocations();

	/**
	 * Returns all board itinerary locations.
	 * 
	 * @return all board itinerary locations
	 */
	List<Location> findAllBoardItineraryLocations();
}