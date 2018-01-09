package omis.bedplacement.service;

import java.util.Date;
import java.util.List;

import omis.bedplacement.domain.BedPlacement;
import omis.bedplacement.domain.BedPlacementReason;
import omis.bedplacement.exception.BedOccupiedException;
import omis.bedplacement.exception.BedPlacementDateConflictException;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Bed;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Service for bed placement.
 *  
 * @author Joel Norris 
 * @version 0.1.0 (Apr, 04 2013)
 * @since OMIS 3.0
 */
public interface BedPlacementService {
	/**
	 * Returns a list of bed placements for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of bed placements
	 */
	List<BedPlacement> findByOffender(Offender offender);
	
	/**
	 * Confirms the specified bed placement.
	 * 
	 * @param bedPlacement bed placement
	 * @param date date
	 * @return confirmed bed placement
	 * @throws BedPlacementDateConflictException thrown when a bed placement,
	 * that is confirmed, already exists during the specified date for the
	 * specified bedplacement's offender 
	 * @throws BedOccupiedException thrown when the specified bedplacement's bed
	 * is already occupied on the specified date
	 */
	BedPlacement confirm(BedPlacement bedPlacement, Date date) 
			throws BedPlacementDateConflictException, BedOccupiedException;
	
	/**
	 * Creates a new bed placement for the specified offender.
	 * 
	 * @param offender offender
	 * @param bed bed
	 * @param confirmed confirmed
	 * @param dateRange date range
	 * @param description description
	 * @param reason bed placement reason
	 * @return created bed placement
	 * @throws DuplicateEntityFoundException thrown when a duplicate bed
	 * placement is found
	 * @throws BedPlacementDateConflictException thrown when a bed placement, 
	 * that is confirmed, has an overlapping date range
	 * @throws BedOccupiedException thrown when the specified bed is already
	 * occupied by another bed placement
	 */
	BedPlacement create(Offender offender, Bed bed, Boolean confirmed, 
			DateRange dateRange, String description, 
			BedPlacementReason reason) 
		throws DuplicateEntityFoundException, BedPlacementDateConflictException, 
			BedOccupiedException;
	
	/**
	 * Updates the specified bed placement.
	 * 
	 * @param bedPlacement bed placement
	 * @param bed bed
	 * @param confirmed confirmed
	 * @param dateRange date range
	 * @param description description
	 * @param reason bed placement reason
	 * @return updated bed placement
	 * @throws DuplicateEntityFoundException thrown when a duplicate bed
	 * placement is found
	 * @throws BedPlacementDateConflictException thrown when a bed placement, 
	 * that is confirmed, has an overlapping date range
	 * @throws BedOccupiedException thrown when the specified bed is already
	 * occupied by another bed placement
	 */
	BedPlacement update(BedPlacement bedPlacement, Bed bed, Boolean confirmed, 
			DateRange dateRange, String description, 
			BedPlacementReason reason) throws DuplicateEntityFoundException,
			BedPlacementDateConflictException, BedOccupiedException;
	
	/**
	 * Removes the specified bed placement.
	 * 
	 * @param bedPlacement bed placement
	 */
	void remove(BedPlacement bedPlacement);	

	/**
	 * Returns the facility for the specified offender on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return facility
	 */
	Facility findFacility(Offender offender, Date date);
	
	/**
	 * Returns a list of available complexes for use as placement of the
	 * offender. If no complexes are found, an empty list is returned.
	 * 
	 * @param facility facility
	 * @return list of complexes
	 */
	List<Complex> findComplexes(Facility facility);

	/**
	 * Returns a list of rooms with the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @param level level
	 * @return list of rooms
	 */
	List<Room> findRooms(Facility facility, Complex complex, Unit unit, 
			Section section, Level level);
	
	/**
	 * Returns a list of levels with the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @return list of levels
	 */
	List<Level> findLevels(Facility facility, Complex complex, Unit unit, 
			Section section);
	
	/**
	 * Returns a list of sections with the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @return list of sections
	 */
	List<Section> findSections(Facility facility, Complex complex, Unit unit,
			Level level);
	
	/**
	 * Returns a list of all units with the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @return list of units
	 */
	List<Unit> findUnits(Facility facility, Complex complex);
	
	/**
	 * Returns a list of available beds for use as placement of the
	 * offender. If no beds are found, an empty list is returned.
	 * 
	 * @param room room
	 * @return list of beds
	 */
	List<Bed> findBeds(Room room);
	
	/**
	 * Returns a list of all bed placement reasons.
	 * 
	 * @return list of bed placement reasons
	 */
	List<BedPlacementReason> findBedPlacementReasons();
	
	/**
	 * Returns a list of beds that are occupied according to a list of bed
	 * placements.
	 * 
	 * @param bedPlacements bed placements
	 * @param beds beds
	 * @return list of beds
	 */
	List<Bed> findOccupiedBeds(List<BedPlacement> bedPlacements,
			List<Bed> beds);
	
	/**
	 * Returns a list of bed placements that are valid for the specified
	 * facility on the specified date.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of bed placements
	 */
	List<BedPlacement> findSpecificBedPlacementsByFacility(Facility facility, 
			Date date);

	/**
	 * Takes the specified dates and compares them with a bed placement.
	 * 
	 * @param bedPlacement bed placement
	 * @param currentDate current date
	 * @param endDate end date
	 * @param userAccount user account
	 * @param updateDate update date
	 */
	void endBedPlacementAtDate(BedPlacement bedPlacement,
			Date currentDate, Date endDate,
			UserAccount userAccount, Date updateDate);

	/**
	 * Returns a list of bed placements that are planned for the specified
	 * date in the specified facility.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of bed placements
	 */
	List<BedPlacement> findSpecifiedPlannedBedPlacements(Facility facility,
			Date date);

	/**
	 * Returns a list of bed placements that are complete for the specified date
	 * in the specified facility.
	 * 
	 * @param facility facility
	 * @param date date
	 * @return list of bed placements
	 */
	List<BedPlacement> findSpecifiedCompletedBedPlacements(Facility facility,
			Date date);
	
	//TODO: Delete this method, it is a placeholder while rewriting the 
	//module. - JN
	/**
	 * This is a placeholder method while the module is being reworked. It
	 * should be removed at the soonest available time.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return bed placement
	 */
	BedPlacement findBedPlacementByOffenderOnDate(Offender offender,
			Date date);

	/**
	 * Ends the specified bed placement on the specified date.
	 * 
	 * @param bedPlacement bed placement
	 * @param endDate end date
	 * @return ended bed placement
	 */
	BedPlacement endBedPlacement(BedPlacement bedPlacement, Date endDate);
}