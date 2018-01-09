package omis.visitation.service;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.locationterm.domain.LocationTerm;
import omis.offender.domain.Offender;
import omis.visitation.domain.Visit;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.component.VisitFlags;

/**
 * Visit Service.
 * 
 * @author Joel Norris
 * @version 0.1.1 (December 2, 2016)
 * @since OMIS 3.0
 */
public interface VisitService {
	
	/**
	 * Logs a visit for the specified visitation association.
	 * 
	 * @param visitationAssociation visitation association
	 * @param date date
	 * @param startTime start time
	 * @param endTime end time
	 * @param badgeNumber badge number
	 * @param flags visit flags
	 * @param notes notes
	 * @param location location
	 * @return visit
	 * @throws DuplicateEntityFoundException thrown when a duplicate visit is 
	 * found
	 * @throws DateConflictException date conflict exception
	 */
	Visit log(VisitationAssociation visitationAssociation, Date date, 
			Date startTime, Date endTime, String badgeNumber, VisitFlags flags,
			String notes, Location location)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates the specified visit.
	 * 
	 * @param visit visit
	 * @param date date
	 * @param startTime start time
	 * @param endTime end time
	 * @param badgeNumber badge number
	 * @param flags visit flags
	 * @param location location
	 * @return updated visit
	 */
	Visit update(Visit visit, Date date, Date startTime, Date endTime,
			String badgeNumber, VisitFlags flags, String notes,
			Location location)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Removes the specified visit.
	 * 
	 * @param visit visit
	 */
	void remove(Visit visit);
	
	/**
	 * Find visit by offender.
	 * 
	 * @param offender offender
	 * @return list of visit
	 */
	List<Visit> findByOffender(Offender offender);
	
	/**
	 * Ends the specified visit at the specified time.
	 * 
	 * @param visit visit
	 * @param endTime end time
	 * @return ended visit
	 * @throws DuplicateEntityFoundException found when a duplicate visit is
	 * found
	 * @throws DateConflictException found a visit is found within the
	 * specified date range for that visit's visitation association
	 */
	Visit endVisit(Visit visit, Date endTime)
		throws DuplicateEntityFoundException, DateConflictException;

	/**
	 * Returns all visitation associations for the specified offender on the
	 * specified date that are considered special visit.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return list of special visit visitation associations
	 */
	List<VisitationAssociation> findSpecialVisitAssociationsByOffender(
			Offender offender, Date date);

	/**
	 * Returns all visitation associations for the specified offender on the
	 * specified date that are approved.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return list of approved visitation associations
	 */
	List<VisitationAssociation> findApprovedVisitationAssociaitonsByOffender(
			Offender offender, Date date);
	
	/**
	 * Returns location term for the specified offender on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return location term
	 */
	LocationTerm findLocationTermByOffenderOnDate(Offender offender, Date date);
}