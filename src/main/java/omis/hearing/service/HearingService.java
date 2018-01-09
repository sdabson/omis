package omis.hearing.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingCategory;
import omis.hearing.domain.HearingNote;
import omis.hearing.domain.HearingStatus;
import omis.hearing.domain.HearingStatusCategory;
import omis.hearing.domain.ImposedSanction;
import omis.hearing.domain.Infraction;
import omis.hearing.domain.StaffAttendance;
import omis.hearing.domain.component.Resolution;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.staff.domain.StaffAssignment;
import omis.supervision.domain.SupervisoryOrganization;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * HearingService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface HearingService {
	
	
	/**
	 * Creates a hearing with the specified parameters
	 * @param location
	 * @param offender
	 * @param inAttendance - Boolean
	 * @param date
	 * @param category - HearingCategory
	 * @param officer - StaffAssignment
	 * @return Newly Created Hearing
	 * @throws DuplicateEntityFoundException - when Hearing exists with
	 * specified offender, location, date, and officer
	 */
	public Hearing createHearing(Location location, Offender offender,
			Boolean inAttendance, Date date, HearingCategory category,
			StaffAssignment officer)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates a hearing with the specified parameters
	 * @param hearing - Hearing to update
	 * @param location
	 * @param inAttendance - Boolean
	 * @param date
	 * @param category - HearingCategory
	 * @param officer - StaffAssignment
	 * @return Updated Hearing
	 * @throws DuplicateEntityFoundException - when Hearing exists with
	 * specified offender, location, date, and officer
	 */
	public Hearing updateHearing(Hearing hearing, Location location,
			Boolean inAttendance, Date date, HearingCategory category,
			StaffAssignment officer)
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes a hearing
	 * @param hearing - Hearing to remove
	 */
	public void removeHearing(Hearing hearing);
	
	/**
	 * Creates a hearing note with specified parameters
	 * @param hearing
	 * @param description - String
	 * @param date
	 * @return newly created hearing note
	 * @throws DuplicateEntityFoundException - when Hearing Note already exists
	 * for specified hearing with given date and description
	 */
	public HearingNote createHearingNote(Hearing hearing, String description,
			Date date) throws DuplicateEntityFoundException;
	
	/**
	 * Updates a hearing note with specified parameters
	 * @param hearingNote - HearingNote to update
	 * @param description - String
	 * @param date
	 * @return updated hearing note
	 * @throws DuplicateEntityFoundException - when Hearing Note already exists
	 * for specified hearing with given date and description
	 */
	public HearingNote updateHearingNote(HearingNote hearingNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a HearingNote
	 * @param hearingNote - HearingNote to remove
	 */
	public void removeHearingNote(final HearingNote hearingNote);
	
	/**
	 * Returns a list of all HearingNotes by hearing
	 * @param hearing
	 * @return list of all HearingNotes by hearing
	 */
	public List<HearingNote> findHearingNotesByHearing(Hearing hearing);
	
	/**
	 * Creates a staffAttendance with specified properties
	 * @param hearing
	 * @param staff - StaffAssignment
	 * @return newly created StaffAttendance
	 * @throws DuplicateEntityFoundException - when Staff Attendance exists for
	 * specified hearing with given staff assignment
	 */
	public StaffAttendance createStaffAttendance(Hearing hearing,
			StaffAssignment staff)throws DuplicateEntityFoundException;
	
	/**
	 * Updates a staffAttendance with specified properties
	 * @param staffAttendance - staffAttendance to update
	 * @param staff - StaffAssignment
	 * @return updated StaffAttendance
	 * @throws DuplicateEntityFoundException - when Staff Attendance exists for
	 * specified hearing with given staff assignment
	 */
	public StaffAttendance updateStaffAttendance(StaffAttendance staffAttendance,
			StaffAssignment staff)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a staffAttendance
	 * @param staffAttendance - StaffAttendance to remove
	 */
	public void removeStaffAttendance(StaffAttendance staffAttendance);
	
	/**
	 * Creates a HearingStatus with the specified properties
	 * @param hearing - Hearing
	 * @param description - String
	 * @param date - Date
	 * @param category - HearingStatusCategory
	 * @return Newly Created HearingStatus
	 * @throws DuplicateEntityFoundException - When a HearingStatus already
	 * exists with specified Date and Category for given Hearing
	 */
	public HearingStatus createHearingStatus(Hearing hearing, String description,
			Date date, HearingStatusCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a HearingStatus with the specified properties
	 * @param hearingStatus - HearingStatus to update
	 * @param description - String
	 * @param date - Date
	 * @param category - HearingStatusCategory
	 * @return Updated HearingStatus
	 * @throws DuplicateEntityFoundException - When a HearingStatus already
	 * exists with specified Date and Category for given Hearing
	 */
	public HearingStatus updateHearingStatus(HearingStatus hearingStatus,
			String description, Date date,
			HearingStatusCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a HearingStatus
	 * @param hearingStatus - HearingStatus to remove
	 */
	public void removeHearingStatus(HearingStatus hearingStatus);
	
	/**
	 * Creates a new Infraction with the specified properties
	 * @param hearing - Hearing
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @param resolution - Resolution
	 * @return Newly created Infraction
	 * @throws DuplicateEntityFoundException - When an Infraction already exists
	 * with given Hearing, ConditionViolation, and DisciplinaryCodeViolation.
	 * @throws DuplicateEntityFoundException
	 */
	public Infraction createInfraction(Hearing hearing,
			ConditionViolation conditionViolation,
			DisciplinaryCodeViolation disciplinaryCodeViolation,
			Resolution resolution) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an Infraction with the specified properties
	 * @param infraction - Infraction to update
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @param resolution - Resolution
	 * @return Updated Infraction
	 * @throws DuplicateEntityFoundException - When an Infraction already exists
	 * with given Hearing, ConditionViolation, and DisciplinaryCodeViolation.
	 */
	public Infraction updateInfraction(Infraction infraction,
			ConditionViolation conditionViolation,
			DisciplinaryCodeViolation disciplinaryCodeViolation,
			Resolution resolution) throws DuplicateEntityFoundException;
	
	/**
	 * Removes an Infraction
	 * @param infraction - Infraction to remove
	 */
	public void removeInfraction(Infraction infraction);
	
	/**
	 * Removes an ImposedSanction
	 * @param imposedSanction - ImposedSanction to remove
	 */
	public void removeImposedSanction(ImposedSanction imposedSanction);
	
	/**
	 * Returns a list of Infractions by specified Hearing
	 * @param hearing - Hearing
	 * @return List of Infractions by specified Hearing
	 */
	public List<Infraction> findInfractionsByHearing(Hearing hearing);
	
	/**
	 * Returns an ImposedSanction found by specified Infraction
	 * @param infraction - Infraction
	 * @return ImposedSanction found by specified Infraction
	 */
	public ImposedSanction findImposedSanctionByInfraction(
			Infraction infraction);
	
	/**
	 * Returns a list of StaffAttendance by specified Hearing
	 * @param hearing - Hearing
	 * @return list of StaffAttendance by specified Hearing
	 */
	public List<StaffAttendance> findStaffAttendedByHearing(Hearing hearing);
	
	/**
	 * Returns a list of facility locations
	 * @return list of facility locations
	 */
	public List<Location> findFacilityLocations();
	
	/**
	 * Returns a list of jail locations
	 * @return list of jail locations
	 */
	public List<Location> findJailLocations();
	
	/**
	 * Returns a list of Treatment Center Locations
	 * @return List of Locations
	 */
	public List<Location>
			findTreatmentCenterLocations();
	
	/**
	 * Returns a list of PreRelease Center Locations
	 * @return List of Locations
	 */
	public List<Location>
			findPreReleaseCenterLocations();
	
	/**
	 * Returns a list of Community Supervision Office Locations
	 * @return List of Locations
	 */
	public List<Location>
			findCommunitySupervisionOfficeLocations();
	
	/**
	 * Returns a list of Supervisory Organizations
	 * @return List of Supervisory Organizations
	 */
	public List<SupervisoryOrganization> findSupervisoryOrganization();
	
	/**
	 * Returns the HearingStatus with the most recent Date for specified Hearing
	 * @param hearing - Hearing
	 * @return HearingStatus with the most recent Date for specified Hearing
	 */
	public HearingStatus findLatestHearingStatus(Hearing hearing);
	
	/**
	 * Returns a list of unresolved ViolationEvents by Offender
	 * @param offender - Offender
	 * @return List of unresolved ViolationEvents by Offender
	 */
	public List<ViolationEvent> findUnresolvedViolationEventsByOffender(
			Offender offender);
	
	/**
	 * Returns a list of unresolved ConditionViolations by specified ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of unresolved ConditionViolations by specified ViolationEvent
	 */
	public List<ConditionViolation>
		findUnresolvedConditionViolationsByViolationEvent(
				ViolationEvent violationEvent);
	
	/**
	 * Returns a list of unresolved DisciplinaryCodeViolations by specified
	 * ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of unresolved DisciplinaryCodeViolations by specified
	 * ViolationEvent
	 */
	public List<DisciplinaryCodeViolation>
		findUnresolvedDisciplinaryCodeViolationsByViolationEvent(
				ViolationEvent violationEvent);
	
	/**
	 * Returns a list of all HearingStatuses for specified Hearing
	 * @param hearing - Hearing
	 * @return List of all HearingStatuses for specified Hearing
	 */
	public List<HearingStatus> findHearingStatusesByHearing(Hearing hearing);
}
