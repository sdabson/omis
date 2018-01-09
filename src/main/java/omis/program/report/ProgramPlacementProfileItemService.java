package omis.program.report;

import java.util.Date;

import omis.offender.domain.Offender;

/** 
 * Service for program placement profile related operations.
 * 
 * @author Josh Divine
 * @version 0.1.0 (June 8, 2017)
 * @since OMIS 3.0 
 */
public interface ProgramPlacementProfileItemService {
	
	/**
	 * Returns whether a program placement exists for the offender on the 
	 * specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return whether a program placement exists
	 */
	Boolean findProgramPlacementExistenceByOffenderOnDate(Offender offender, 
			Date date);
}
