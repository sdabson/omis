package omis.program.report;

import java.io.Serializable;
import java.util.Date;

import omis.datatype.DateRange;

/**
 * Summary of program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final String supervisoryOrganizationName;
	
	private final String correctionalStatusName;
	
	private final Boolean location;
	
	private final String locationOrganizationName;
	
	private final String programName;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final Long dayCount;
	
	/**
	 * Instantiates summary of program placement.
	 * 
	 * @param id id
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderSuffix suffix of offender
	 * @param offenderNumber offender number
	 * @param supervisoryOrganizationName name of supervisory organization
	 * @param correctionalStatusName name of correctional status
	 * @param location whether at location
	 * @param locationOrganizationName name of organization of location 
	 * @param programName name of program
	 * @param startDate start date
	 * @param endDate end date
	 * @param effectiveDate effective date
	 */
	public ProgramPlacementSummary(
			final Long id,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final String offenderSuffix,
			final Integer offenderNumber,
			final String supervisoryOrganizationName,
			final String correctionalStatusName,
			final Boolean location,
			final String locationOrganizationName,
			final String programName,
			final Date startDate,
			final Date endDate,
			final Date effectiveDate) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.supervisoryOrganizationName = supervisoryOrganizationName;
		this.correctionalStatusName = correctionalStatusName;
		this.location = location;
		this.locationOrganizationName = locationOrganizationName;
		this.programName = programName;
		this.startDate = startDate;
		this.endDate = endDate;
		final Date diffDate;
		if (this.endDate != null) {
			diffDate = this.endDate;
		} else {
			diffDate = effectiveDate;
		}
		this.dayCount = DateRange.countDaysInclusively(startDate, diffDate);
	}
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns last name of offender.
	 * 
	 * @return last name of offender
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns first name of offender.
	 * 
	 * @return first name of offender
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns middle name of offender.
	 * 
	 * @return middle name of offender
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns suffix of offender.
	 * 
	 * @return suffix of offender
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}
	
	/**
	 * Returns offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns name of supervisory organization.
	 * 
	 * @return name of supervisory organization
	 */
	public String getSupervisoryOrganizationName() {
		return this.supervisoryOrganizationName;
	}
	
	/**
	 * Returns name of correctional status.
	 * 
	 * @return name of correctional status
	 */
	public String getCorrectionalStatusName() {
		return this.correctionalStatusName;
	}
	
	/**
	 * Returns whether at location.
	 * 
	 * @return whether at location
	 */
	public Boolean getLocation() {
		return this.location;
	}
	
	/**
	 * Returns name of organization of location.
	 * 
	 * @return name of organization of location
	 */
	public String getLocationOrganizationName() {
		return this.locationOrganizationName;
	}
	
	/**
	 * Returns name of program.
	 * 
	 * @return name of program
	 */
	public String getProgramName() {
		return this.programName;
	}
	
	/**
	 * Returns start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Returns end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Returns day count.
	 * 
	 * @return day count
	 */
	public Long getDayCount() {
		return this.dayCount;
	}
}