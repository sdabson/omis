package omis.placement.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.program.domain.Program;
import omis.program.domain.ProgramPlacement;

/**
 * Service to change program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 2, 2016)
 * @since OMIS 3.0
 */
public interface ChangeProgramPlacementService {

	/**
	 * Changes program placement.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @param endDate end date
	 * @param program program
	 * @return program placement
	 * @throws DuplicateEntityFoundException if program placement exists
	 */
	ProgramPlacement change(
			Offender offender,
			Date effectiveDate,
			Date endDate,
			Program program)
				throws DuplicateEntityFoundException;
	
	/**
	 * Returns programs offender can be placed on on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return programs offender can be placed on on date
	 */
	List<Program> findAllowedProgramsForOffenderOnDate(
			Offender offender, Date effectiveDate);
}