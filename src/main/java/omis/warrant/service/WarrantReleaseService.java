package omis.warrant.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.jail.domain.Jail;
import omis.person.domain.Person;
import omis.region.domain.County;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantRelease;

/**
 * WarrantReleaseService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantReleaseService {
	
	/**
	 * Creates a WarrantRelease with the specified properties
	 * @param warrant - Warrant
	 * @param instructions - String
	 * @param county - Country
	 * @param facility - Facility
	 * @param releaseTimestamp - Date
	 * @param addressee - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Newly created WarrantRelease
	 * @throws DuplicateEntityFoundException - When a WarrantRelease already
	 * exists with the specified Warrant
	 */
	public WarrantRelease create(Warrant warrant,
			String instructions, County county,
			Facility facility, Date releaseTimestamp,
			String addressee, Person clearedBy,
			Date clearedByDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a WarrantRelease with the specified properties
	 * @param warrantRelease - WarrantRelease to update
	 * @param instructions - String
	 * @param county - Country
	 * @param facility - Facility
	 * @param releaseTimestamp - Date
	 * @param addressee - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Updated WarrantRelease
	 * @throws DuplicateEntityFoundException - When a WarrantRelease already
	 * exists with the specified WarrantRelease's Warrant
	 */
	public WarrantRelease update(WarrantRelease warrantRelease,
			String instructions, County county,
			Facility facility, Date releaseTimestamp,
			String addressee, Person clearedBy,
			Date clearedByDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a WarrantRelease
	 * @param warrantRelease - WarrantRelease to remove
	 */
	public void remove(WarrantRelease warrantRelease);
	
	/**
	 * Returns a WarrantRelease by specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantRelease found by specified Warrant
	 */
	public WarrantRelease findByWarrant(Warrant warrant);
	
	/**
	 * Returns a list of all Counties
	 * @return List of all Counties
	 */
	public List<County> findAllCounties();
	
	/**
	 * Returns a list of all Jails
	 * @return List of all Jails
	 */
	public List<Jail> findAllJails();
	
	/**
	 * Returns a list of all Facilities
	 * @return List of all Facilities
	 */
	public List<Facility> findAllFacilities();
	
}
