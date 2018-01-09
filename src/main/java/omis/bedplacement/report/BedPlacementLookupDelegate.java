package omis.bedplacement.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Bed placement lookup delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 24, 2017)
 * @since OMIS 3.0
 */
public interface BedPlacementLookupDelegate {

	/**
	 * Returns the unit name of the housing unit for the specified offender on the specified date.
	 *  
	 * @param offender offender
	 * @param date date
	 * @return unit name
	 */
	public String findUnitNameByOffender(Offender offender, Date date);
}