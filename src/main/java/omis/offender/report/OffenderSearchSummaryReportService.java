package omis.offender.report;

import java.util.Date;
import java.util.List;

import omis.demographics.domain.Sex;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.person.report.AlternateNameSummary;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Summary service of offender searches.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 7, 2016)
 * @since OMIS 3.0
 */
public interface OffenderSearchSummaryReportService {

	/**
	 * Offender search summary list, to be used after location is in production.
	 * 
	 * @param offenderNumber offender number
	 * @param firstName first name
	 * @param middleName middle name
	 * @param lastName last name
	 * @param sex sex
	 * @param dateOfBirth date of birth
	 * @param socialSecurityNumber social security number
	 * @param effectiveDate effective date
	 * @param displayPhoto display photo
	 * @return summary of offender search
	 */
	List<OffenderSearchSummary> search(Integer offenderNumber, 
	  	String firstName, String middleName, String lastName, Sex sex, 
	  	Location location, Date dateOfBirth, Integer socialSecurityNumber, 
	  	Date effectiveDate, Boolean active);
	
	/**
	 * Offender search summary list.
	 * 
	 * @param offenderNumber offender number
	 * @param firstName first name
	 * @param middleName middle name
	 * @param lastName last name
	 * @param sex sex
	 * @param dateOfBirth date of birth
	 * @param socialSecurityNumber social security number
	 * @return summary of offenders
	 */
	List<OffenderSearchSummary> searchForOffender(Integer offenderNumber, 
	  	String firstName, String middleName, String lastName, Sex sex,
	  	Date dateOfBirth, Integer socialSecurityNumber);
	
	/**
	 * Returns alternate name summaries for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of alternate name summaries
	 */
	List<AlternateNameSummary> findAlternateNameSummariesByOffender(Offender offender);
	
	/**
	 * Returns supervisory organization for the specified offender on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return supervisory organization
	 */
	SupervisoryOrganization findSupervisoryOrganizationByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Returns correctional status for the specified offender on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return correctional status
	 */
	CorrectionalStatus findCorrectionalStatusByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Returns location for the specified offender on the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return location
	 */
	Location findLocationByOffenderOnDate(Offender offender, Date date);
}