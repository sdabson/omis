package omis.incident.report;

import java.io.Serializable;
import java.util.Date;

import omis.incident.domain.IncidentStatementSubmissionCategory;

/**
 * Incident summary
 * @author Yidong 
 * @author Joel Norris
 * @version 0.1.0 (July 5, 2015)
 * @since OMIS 3.0
 */

public class IncidentStatementSummary implements Serializable {
	private static final long serialVersionUID = 1L;
//	private final Long id;
//	private final String reportNumber;
//	private final Date incidentDate;
//	private final String reportTitle;
//	private final String reportingStaffLastName;
//	private final String reportingStaffFirstName;
//	private final String locationName;
//	private final String facilityName;
//	private final String complexName;
//	private final String unitName;
//	private final String levelName;
//	private final String sectionName;
//	private final String roomName;
//	private final IncidentStatementSubmissionCategory submissionCategory;
	
	private Long id;
	private String reportNumber;
	private Date incidentDate;
	private String reportTitle;
	private String reportingStaffLastName;
	private String reportingStaffFirstName;
	private String locationName;
	private String facilityName;
	private String complexName;
	private String unitName;
	private String levelName;
	private String sectionName;
	private String roomName;
	private IncidentStatementSubmissionCategory submissionCategory;
	
	/**
	 * Instantiates a default instance of the incident summary.
	 */
	
	public IncidentStatementSummary() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of incident statement summary with the specified properties.
	 * 
	 * @param id id
	 * @param reportNumber report number
	 * @param incidentDate incident date
	 * @param reportTitle report title
	 * @param reportingStaffLastName reporting staff last name
	 * @param reportingStaffFirstName reporting staff first name
	 * @param locationName location name
	 * @param facilityName facility name
	 * @param complexName complex name
	 * @param unitName unit name
	 * @param levelName level name
	 * @param sectionName section name
	 * @param roomName room name
	 * @param submissionCategory submission category
	 */
	public IncidentStatementSummary(
			final Long id,
			final String reportNumber,
			final Date incidentDate,
			final String reportTitle,
			final String reportingStaffLastName,
			final String reportingStaffFirstName,
			final String locationName,
			final String facilityName,
			final String complexName,
			final String unitName,
			final String levelName,
			final String sectionName,
			final String roomName,
			final IncidentStatementSubmissionCategory submissionCategory) {
		this.id = id;
		this.reportNumber = reportNumber;
		 this.incidentDate = incidentDate;
		this.reportTitle = reportTitle;
		this.reportingStaffLastName = reportingStaffLastName;
		this.reportingStaffFirstName = reportingStaffFirstName;
		 this.locationName = locationName;
		this.facilityName = facilityName;
		this.complexName = complexName;
		this.unitName = unitName;
		this.levelName = levelName;
		this.sectionName = sectionName;
		this.roomName = roomName;
		this.submissionCategory = submissionCategory;
	}
	
	/**
	 * Return the incident statement Id.
	 * 
	 * @return the incident statement Id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Return the report number.
	 * 
	 * @return the report number
	 */
	public String getReportNumber() {
		return this.reportNumber;
	}
	
	/**
	 * Return the incident date.
	 * 
	 * @return the incident date
	 */
	public Date getIncidentDate() {
		return this.incidentDate;
	}
	
	/**
	 * Return the report title.
	 * 
	 * @return the report title
	 */
	public String getReportTitle() {
		return this.reportTitle;
	}
	
	/**
	 * Return the reporting staff last name.
	 * 
	 * @return the reporting staff last name
	 */
	public String getReportingStaffLastName() {
		return this.reportingStaffLastName;
	}
	
	/**
	 * Return the reporting staff first name.
	 * 
	 * @return the reporting staff first name
	 */
	public String getReportingStaffFirstName() {
		return this.reportingStaffFirstName;
	}
	
	/**
	 * Return the location name.
	 * 
	 * @return the location name
	 */
	 public String getLocationName() {
		return this.locationName;
	}
	
	/**
	 * Return the facility name.
	 * 
	 * @return the facility name
	 */
	public String getFacilityName() {
		return this.facilityName;
	}
	
	/**
	 * Return the complex name.
	 * 
	 * @return the complex name
	 */
	public String getComplexName() {
		return this.complexName;
	}
	
	/**
	 * Return the unit name.
	 * 
	 * @return the unit name
	 */
	public String getUnitName() {
		return this.unitName;
	}
	
	/**
	 * Return the level name.
	 * 
	 * @return the level name
	 */
	public String getLevelName() {
		return this.levelName;
	}
	
	/**
	 * Return the section name.
	 * 
	 * @return the section name
	 */
	public String getSectionName() {
		return this.sectionName;
	}
	
	/**
	 * Return the room name.
	 * 
	 * @return the room name
	 */
	public String getRoomName() {
		return this.roomName;
	}

	/**
	 * Returns the incident statement submission category.
	 * @return incident statement submission category
	 */
	public IncidentStatementSubmissionCategory getSubmissionCategory() {
		return this.submissionCategory;
	}
}