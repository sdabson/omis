package omis.education.report;

import java.io.Serializable;
import java.util.Date;

/**
 * EducationSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long educationTermId;
	
	private final Date attendenceStartDate;
	
	private final Date attendenceEndDate;
	
	private final String educationTermDescription;
	
	private final String instituteName;
	
	private final String instituteCategory;

	private final Boolean graduated;
	
	/**
	 * Constructor 
	 * @param educationTermId - education term id
	 * @param attendenceStartDate - attendence start date
	 * @param attendenceEndDate - attendence end date
	 * @param educationTermDescription - education term description
	 * @param instituteName - institute name
	 * @param instituteCategory - institute category
	 */
	public EducationSummary(Long educationTermId, Date attendenceStartDate, Date attendenceEndDate,
			String educationTermDescription, String instituteName, String instituteCategory, Boolean graduated) {
		this.educationTermId = educationTermId;
		this.attendenceStartDate = attendenceStartDate;
		this.attendenceEndDate = attendenceEndDate;
		this.educationTermDescription = educationTermDescription;
		this.instituteName = instituteName;
		this.instituteCategory = instituteCategory;
		this.graduated = graduated;
	}

	/**
	 * Returns the education term ID
	 * @return the educationTermId
	 */
	public Long getEducationTermId() {
		return educationTermId;
	}

	/**
	 * Returns the attendence start date
	 * @return the attendenceStartDate
	 */
	public Date getAttendenceStartDate() {
		return attendenceStartDate;
	}

	/**
	 * Returns the attendence end date
	 * @return the attendenceEndDate
	 */
	public Date getAttendenceEndDate() {
		return attendenceEndDate;
	}

	/**
	 * Returns the education term description
	 * @return the educationTermDescription
	 */
	public String getEducationTermDescription() {
		return educationTermDescription;
	}

	/**
	 * Returns the institute's name
	 * @return the instituteName
	 */
	public String getInstituteName() {
		return instituteName;
	}

	/**
	 * Returns the institute's category
	 * @return the instituteCategory
	 */
	public String getInstituteCategory() {
		return instituteCategory;
	}
	
	/**
	 * Returns the graduated status
	 * @return Boolean the graduated status
	 */
	public Boolean getGraduated() {
		return graduated;
	}
	
	
}
