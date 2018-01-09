package omis.stg.report;

import java.io.Serializable;
import java.util.Date;

import omis.person.domain.PersonName;
import omis.stg.domain.SecurityThreatGroupActivity;

/**
 * Summary of security threat group activity.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Dec 5, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivitySummary 
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final Date date;
	
	private final String reportedByLastName;
	
	private final String reportedByFirstName;
	
	private final String reportedByMiddleName;
	
	private final String reportedBySuffix;
	
	private final String summary;
	
	
	/**
	 * Summary of security threat group activity.
	 * 
	 * @param id - activity id
	 * @param activity - security threat group activity
	 * @param date - date
	 * @param reportedBy - name of person reporting the activity
	 * @param summary - summary of the activity
	 */
	public SecurityThreatGroupActivitySummary(
			final Long id,
			final SecurityThreatGroupActivity activity,
			final Date reportDate,
			final PersonName reportedBy,
			final String summary) {
		this.id = id;
		this.date = reportDate;
		this.reportedByLastName = reportedBy.getLastName();
		this.reportedByFirstName = reportedBy.getFirstName();
		this.reportedByMiddleName = reportedBy.getMiddleName();
		this.reportedBySuffix = reportedBy.getSuffix();
		if (activity != null) {
			this.summary = activity.getSummary();
		} else {
			this.summary = null;
		}
	}

	/**
	 * Returns narrative
	 * 
	 * @return narrative
	 */
	public String getSummary() {
		return this.summary;
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
	 * Returns date of report.
	 * 
	 * @return date of report
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Returns offender last name.
	 * 
	 * @return offender last name
	 */
	public String getReportedByLastName() {
		return this.reportedByLastName;
	}

	/**
	 * Returns offender first name.
	 * 
	 * @return offender first name
	 */
	public String getReportedByFirstName() {
		return this.reportedByFirstName;
	}

	/**
	 * Returns offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getReportedByMiddleName() {
		return this.reportedByMiddleName;
	}

	/**
	 * Returns offender suffix.
	 * 
	 * @return offender suffix
	 */
	public String getReportedBySuffix() {
		return this.reportedBySuffix;
	}
	
}
