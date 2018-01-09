package omis.disciplinaryCode.web.form;

import java.util.Date;

import omis.disciplinaryCode.domain.DisciplinaryCode;

/**
 * DisciplinaryCodeForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Sep 1, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeForm {
	
	private DisciplinaryCode disciplinaryCode;
	
	private String code;
	
	private String description;
	
	private String extendedDescription;
	
	private Date startDate;
	
	private Date endDate;
	
	private Boolean useExistingCode;
	
	
	/**
	 * Default Constructor
	 */
	public DisciplinaryCodeForm(){
		//Nothing
	}

	/**
	 * @return the disciplinaryCode
	 */
	public DisciplinaryCode getDisciplinaryCode() {
		return disciplinaryCode;
	}


	/**
	 * @param disciplinaryCode the disciplinaryCode to set
	 */
	public void setDisciplinaryCode(final DisciplinaryCode disciplinaryCode) {
		this.disciplinaryCode = disciplinaryCode;
	}

	/**
	 * Returns the code (disciplinary code value)
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * Sets the code (disciplinary code value)
	 * @param code the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}
	
	/**
	 * Returns the description (for disciplinary code)
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * Sets the description (for disciplinary code)
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Returns the extendedDescription
	 * @return extendedDescription - String
	 */
	public String getExtendedDescription() {
		return extendedDescription;
	}

	/**
	 * Sets the extendedDescription
	 * @param extendedDescription - String
	 */
	public void setExtendedDescription(final String extendedDescription) {
		this.extendedDescription = extendedDescription;
	}

	/**
	 * Returns the start date (for supervisory organization code's dateRange)
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date (for supervisory organization code's dateRange)
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date (for supervisory organization code's dateRange)
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date (for supervisory organization code's dateRange)
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns whether using existing code
	 * @return the useExistingCode
	 */
	public Boolean getUseExistingCode() {
		return useExistingCode;
	}

	/**
	 * Sets whether using existing code 
	 * @param useExistingCode the useExistingCode to set
	 */
	public void setUseExistingCode(final Boolean useExistingCode) {
		this.useExistingCode = useExistingCode;
	}
}
