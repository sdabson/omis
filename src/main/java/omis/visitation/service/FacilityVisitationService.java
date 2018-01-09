package omis.visitation.service;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.visitation.domain.Visit;

/**
 * Facility visitation service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (March 3, 2013)
 * @since OMIS 3.0
 */
public interface FacilityVisitationService {

	/**
	 * Returns the facility for the specified location.
	 * 
	 * @param location location
	 * @return facility
	 */
	Facility findFacilityByLocation(Location location);

	/**
	 * Returns facilities.
	 * 
	 * @return list of facilities
	 */
	List<Facility> findFacilities();

	/**
	 * Ends the specified visit with the specified date.
	 * 
	 * @param visit visit
	 * @param date date
	 * @return visit
	 */
	Visit endVisit(Visit visit, Date date) throws DuplicateEntityFoundException,
	DateConflictException;

	/**
	 * Removes the specified visit.
	 * 
	 * @param visit visit
	 */
	void removeVisit(Visit visit);

	/**
	 * Checks out all visits on the specified date for the specified offender.
	 * 
	 * @param offender offender
	 * @param date date
	 */
	void checkOutAllOffenderVisits(Offender offender, Date date);

	/**
	 * Returns all offenders that have visits on the specified date at the
	 * specified facility.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of offenders with visits on the specified date
	 */
	List<Offender> findOffendersWithVisitsOnDate(Facility facility, Date date);
}