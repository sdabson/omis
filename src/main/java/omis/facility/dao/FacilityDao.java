package omis.facility.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Facility;
import omis.location.domain.Location;
import omis.organization.domain.Organization;

/**
 * Data access object for facilities.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Aug 3, 2017)
 * @since OMIS 3.0
 */
public interface FacilityDao extends GenericDao<Facility> {

	/**
	 * Finds the facility with the specified location.
	 * 
	 * @param location location
	 * @return facility
	 */
	Facility findFacilityByLocation(Location location);

	/**
	 * Returns facilities for organization.
	 * 
	 * @param organization organization
	 * @param startDate start date
	 * @param endDate end date
	 * @return facilities for organization
	 */
	List<Facility> findByOrganizationBetweenDates(Organization organization,
			Date startDate, Date endDate);

	/**
	 * Returns facilities by organization.
	 * 
	 * @param organization organization
	 * @return facilities by organization
	 */
	List<Facility> findByOrganization(Organization organization);
	
	/**
	 * Returns the facility that matches the specified name and location.
	 *  
	 * @param name name
	 * @param location location
	 * @return room
	 */
	Facility find(String name, Location location);
	
	/**
	 * Returns the facility that matches the specified name and location 
	 * excluding the specified facility.
	 *  
	 * @param name name
	 * @param location location
	 * @param excludedFacility excluded facility
	 * @return facility
	 */
	Facility findExcluding(String name, Location location, 
			Facility excludedFacility);
}