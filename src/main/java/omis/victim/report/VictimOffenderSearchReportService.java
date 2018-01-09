package omis.victim.report;

import java.util.Date;
import java.util.List;

import omis.offender.report.OffenderSummary;

/**
 * Report service to search for offenders for victims.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 12, 2015)
 * @since OMIS 3.0
 */
public interface VictimOffenderSearchReportService {

	/**
	 * Returns summary of offenders by name.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @return summary of offenders by name
	 */
	List<OffenderSummary> summarizeByName(String lastName, String firstName);
	
	/**
	 * Returns summary of offenders by offender number.
	 * 
	 * @param offenderNumber offender number
	 * @return summary of offenders by offender number
	 */
	List<OffenderSummary> summarizeByOffenderNumber(Integer offenderNumber);
	
	/**
	 * Returns summary of offenders by social security number.
	 * 
	 * @param socialSecurityNumber social security number
	 * @return summary of offenders by social security number
	 */
	List<OffenderSummary> summarizeBySocialSecurityNumber(
			Integer socialSecurityNumber);
	
	/**
	 * Returns summary of offenders by birth date.
	 * 
	 * @param birthDate birth date
	 * @return summary of offenders by birth date
	 */
	List<OffenderSummary> summarizeByBirthDate(Date birthDate);
}