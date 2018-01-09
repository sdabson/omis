package omis.separationneed.report.delegate;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Legacy location lookup delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 23, 2016)
 * @since OMIS 3.0
 */
public interface LegacyLocationLookupDelegate {

	/**
	 * Finds the location for the specified offender.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return location of the specified offender
	 */
	String findLocationForOffender(Offender offender, Date date);
}