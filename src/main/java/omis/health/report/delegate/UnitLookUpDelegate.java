package omis.health.report.delegate;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Delegate to look up unit information.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 21, 2014)
 * @since OMIS 3.0
 */
public interface UnitLookUpDelegate {

	/**
	 * Returns the unit abbreviation for the offender on the date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return unit abbreviation for offender on date
	 */
	String findUnitAbbreviationForOffender(Offender offender, Date date);
	
	/**
	 * Returns the unit abbreviation for the offender on the date.
	 * 
	 * @param offenderNumber offender number
	 * @param date date
	 * @return unit abbreviation for offender on date
	 */
	// TODO: Remove this method - SA
	String findUnitAbbreviationByOffenderNumber(
			Integer offenderNumber, Date date);
}