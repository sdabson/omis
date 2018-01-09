package omis.commitstatus.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Commit status terms summary
 * @author Yidong 
 * @version 0.1.0 (June 5, 2017)
 * @since OMIS 3.0
 */

public class CommitStatusTermSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final Integer offenderNumber;
	private final String offenderLastName;
	private final String offenderFirstName;
	private final String offenderMiddleName;
	private final String offenderSuffix;
	private final Date startDate;
	private final Date endDate;
	private final String statusName;
	
	/**
	 * Instantiates a default instance of the commit status term.
	 */
	public CommitStatusTermSummary(
		final Long id,
		final Integer offenderNumber,
		final String offenderLastName,
		final String offenderFirstName,
		final String offenderMiddleName,
		final String offenderSuffix,
		final Date startDate,
		final Date endDate,
		final String statusName){
		this.id = id;
		this.endDate = endDate;
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.offenderSuffix = offenderSuffix;
		this.startDate = startDate;
		this.statusName = statusName;
	}
	
	/**
	 * Return the employment history term Id.
	 * 
	 * @return the employment history term Id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Return the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Return the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Return the offender number.
	 * 
	 * @return the offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Return offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Return offender first name.
	 * 
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Return offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Return status name.
	 * 
	 * @return status name
	 */
	public String getStatusName() {
		return this.statusName;
	}
	
	/**
	 * Return offender suffix.
	 * 
	 * @return offender suffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}
}
