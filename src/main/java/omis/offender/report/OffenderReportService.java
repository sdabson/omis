package omis.offender.report;

import java.util.Date;

import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Report service for offenders.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 19, 2013)
 * @since OMIS 3.0
 */
public interface OffenderReportService {

	/**
	 * Returns a summary of the offender.
	 * 
	 * @param offender offender to summarize.
	 * @return summary of offender
	 */
	OffenderSummary summarizeOffender(Offender offender);

	/**
	 * Returns a summary of the offender on date.
	 * 
	 * @param offender offender to summarize.
	 * @param date date
	 * @return summary of offender on date
	 */
	OffenderSummary summarizeOffender(Offender offender, Date date);
	
	/**
	 * If the person is an offender, returns a summary of the offender.
	 * 
	 * @param person person to summarize as an offender if an offender
	 * @return summary of offender if the person is an offender; {@code null}
	 * if not
	 */
	OffenderSummary summarizeIfOffender(Person person);
}