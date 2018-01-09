package omis.health.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Service to report unit information.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 21, 2014)
 * @since OMIS 3.0
 */
public interface UnitReportService {

	/**
	 * Returns the unit abbreviation for the offender.
	 * 
	 * @param offender offender
	 * @param date offender
	 * @return unit abbreviation for offender
	 */
	String findUnitAbbreviation(Offender offender, Date date);
}