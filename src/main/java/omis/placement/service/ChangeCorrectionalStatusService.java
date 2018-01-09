package omis.placement.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.exception.LocationNotAllowedException;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.exception.LocationTermConflictException;
import omis.offender.domain.Offender;
import omis.organization.domain.Organization;
import omis.program.domain.Program;
import omis.program.domain.ProgramPlacement;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.EndedPlacementTermException;
import omis.supervision.exception.IllegalCorrectionalStatusChangeException;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.exception.OffenderUnderSupervisionException;
import omis.supervision.exception.PlacementTermChangeReasonNotAllowedException;
import omis.supervision.exception.PlacementTermConflictException;

/**
 * Service to change correctional statuses for an offender.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Dec 19, 2014)
 * @since OMIS 3.0
 */
public interface ChangeCorrectionalStatusService {

	/**
	 * Changes the correctional status of an offender at the date.
	 * 
	 * <p>This method will end the placement term active at the specified
	 * date. A new placement term will be created starting at the specified
	 * date.
	 * 
	 * <p>The new placement term is returned.
	 * 
	 * @param offender offender the correctional status of which to change on
	 * date
	 * @param correctionalStatus correctional status to which to change for
	 * offender on date 
	 * @param changeReason change reason
	 * @param effectiveDate effective date
	 * @param endDate end date
	 * @return placement term reflecting change in correctional status
	 * @throws IllegalCorrectionalStatusChangeException if an attempt is made to
	 * make an illegal change in correctional status
	 * @throws OffenderNotUnderSupervisionException if the offender does
	 * not have a correctional status on effective date
	 * @throws PlacementTermConflictException if placement terms exist
	 * between effective date and end date
	 * @throws PlacementTermChangeReasonNotAllowedException if change
	 * reason is not allowed
	 * @throws EndedPlacementTermException if placement term on date is ended
	 */
	PlacementTerm change(Offender offender,
			CorrectionalStatus correctionalStatus,
			SupervisoryOrganization supervisoryOrganization,
			PlacementTermChangeReason changeReason,
			Date effectiveDate, Date endDate)
				throws IllegalCorrectionalStatusChangeException,
					OffenderNotUnderSupervisionException,
					PlacementTermConflictException,
					PlacementTermChangeReasonNotAllowedException,
					EndedPlacementTermException;
	
	/**
	 * Places an offender at a facility.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param effectiveDate effective date
	 * @param endDate end date
	 * @param reason reason
	 * @return location term of offender at facility
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision on date
	 * @throws LocationNotAllowedException if location placement is not allowed
	 * for offender on effective date
	 * @throws LocationTermConflictException if conflicting location terms
	 * exist
	 * @throws DuplicateEntityFoundException if offender is located at
	 * facility during dates
	 */
	LocationTerm placeAtLocation(Offender offender, Location location,
			Date effectiveDate, Date endDate, LocationReason reason)
				throws OffenderNotUnderSupervisionException,
					LocationNotAllowedException,
					LocationTermConflictException,
					DuplicateEntityFoundException;
	
	/**
	 * Returns allowed programs for offender on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return allowed programs for offender on date
	 */
	List<Program> findAllowedProgramsForOffenderOnDate(
			Offender offender, Date effectiveDate);
	
	/**
	 * Places offender on program.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @param endDate end date
	 * @param program program
	 * @return program placement
	 * @throws DuplicateEntityFoundException if program placement exists
	 */
	ProgramPlacement placeOnProgram(Offender offender, Date effectiveDate,
			Date endDate, Program program)
					throws DuplicateEntityFoundException;
	
	/**
	 * Ends location of offender on date.
	 * 
	 * <p>If the location if the offender on date is ended, overwrite the
	 * ended location term and reason (if one exists) with the effective date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return ended location if one exists for offender on date; otherwise
	 * {@code null}
	 */
	LocationTerm endLocation(Offender offender, Date effectiveDatez);
	
	/**
	 * Begins supervision of offender.
	 * 
	 * @param offender offender
	 * @param correctionalStatus correctional status
	 * @param supervisoryOrganization supervisory organization
	 * @param changeReason change reason
	 * @param effectiveDate effective (start) date
	 * @param endDate end date
	 * @return starting placement term for offender
	 * @throws IllegalCorrectionalStatusChangeException if an attempt is made to
	 * make an illegal change in correctional status
	 * @throws OffenderUnderSupervisionException if the offender is under
	 * supervision on the effective date
	 * @throws PlacementTermChangeReasonNotAllowedException if change
	 * reason is not allowed
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 */
	PlacementTerm beginSupervision(Offender offender,
			CorrectionalStatus correctionalStatus,
			SupervisoryOrganization supervisoryOrganization,
			PlacementTermChangeReason changeReason,
			Date effectiveDate, Date endDate)
				throws IllegalCorrectionalStatusChangeException,
					OffenderUnderSupervisionException,
					PlacementTermChangeReasonNotAllowedException,
					PlacementTermConflictException;
	
	/**
	 * Ends placement term for offender.
	 * 
	 * @param offender offender
	 * @param changeReason change reason
	 * @param effectiveDate effective date
	 * @return ending placement term for offender
	 * @throws IllegalCorrectionalStatusChangeException if an attempt is made to
	 * make an illegal change in correctional status
	 * @throws OffenderNotUnderSupervisionException if the is not under
	 * supervision on the effective date
	 * @throws PlacementTermChangeReasonNotAllowedException if change
	 * reason is not allowed
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws EndedPlacementTermException if placement term on date is
	 * ended
	 */
	PlacementTerm endSupervision(Offender offender,
			PlacementTermChangeReason changeReason, Date effectiveDate)
				throws IllegalCorrectionalStatusChangeException,
					OffenderNotUnderSupervisionException,
					PlacementTermChangeReasonNotAllowedException,
					PlacementTermConflictException,
					EndedPlacementTermException;
	
	/**
	 * Returns correctional status for offender on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective (end) date
	 * @return correctional status for offender on date
	 */
	CorrectionalStatus findCorrectionalStatus(Offender offender,
			Date effectiveDate);
	
	/**
	 * Returns supervisory organizations allowed for correctional status.
	 * 
	 * @param correctionalStatus correctional status
	 * @return supervisory organizations allowed for correctional status
	 */
	List<SupervisoryOrganization>
	findAllowedSupervisoryOrganizations(CorrectionalStatus correctionalStatus);
	
	/**
	 * Returns placement term change reasons for correctional status.
	 * 
	 * @param fromCorrectionalStatus correctional status from which to change
	 * @param toCorrectionalStatus correctional status to which to change
	 * @return placement term change reasons for correctional status
	 */
	List<PlacementTermChangeReason> findAllowedChangeReasons(
			CorrectionalStatus fromCorrectionalStatus,
			CorrectionalStatus toCorrectionalStatus);
	
	/**
	 * Returns locations from which an organization operated ordered by 
	 * organization name.
	 * 
	 * @param organization organization the locations of which to return
	 * @return ordered locations from which an organization operated
	 */
	List<Location> findLocationsForOrganization(Organization organization);
	
	/**
	 * Returns locations from which a correctional status ordered by 
	 * organization name.
	 * 
	 * @param correctionalStatus organization the locations of which to return
	 * @return ordered locations from which an organization operated
	 */
	List<Location> findLocationsForCorrectionalStatus(
			CorrectionalStatus correctionalStatus);
	
	/**
	 * Returns programs offered at location.
	 * 
	 * @param location location
	 * @return programs offered at location
	 */
	List<Program> findProgramsOfferedAtLocation(Location location);
	
	/**
	 * Returns programs offered by supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @return programs offered by supervisory organization
	 */
	List<Program> findProgramsOfferedBySupervisoryOrganization(
			SupervisoryOrganization supervisoryOrganization);
}