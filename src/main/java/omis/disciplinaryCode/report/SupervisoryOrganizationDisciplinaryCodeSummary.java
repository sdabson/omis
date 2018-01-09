package omis.disciplinaryCode.report;

import java.io.Serializable;
import java.util.Date;

import omis.datatype.DateRange;

/**
 * SupervisoryOrganizationDisciplinaryCodeSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class SupervisoryOrganizationDisciplinaryCodeSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long disciplinaryCodeId;
	
	private final Long supervisoryOrganizationCodeId;
	
	private final String disciplinaryCodeValue;
	
	private final String disciplinaryCodeDescription;
	
	private final String disciplinaryCodeExtendedDescription;
	
	private final DateRange supervisoryOrganizationCodeDateRange;
	
	private final Boolean active;

	/**
	 * Constructor
	 * @param disciplinaryCodeId - disciplinary code ID
	 * @param supervisoryOrganizationCodeId - supervisory organization code ID
	 * @param disciplinaryCodeValue - disciplinary code value
	 * @param disciplinaryCodeDescription - disciplinary code description
	 * @param disciplinaryCodeExtendedDescription - disciplinary code extended
	 * description
	 * @param supervisoryOrganizationCodeDateRange - supervisory organization
	 * code date range
	 * @param supervisoryOrganizationCodeEndDate - supervisory organization
	 * code end date
	 * @param effectiveDate - effective Date
	 */
	public SupervisoryOrganizationDisciplinaryCodeSummary(
			final Long disciplinaryCodeId, final Long supervisoryOrganizationCodeId,
			final String disciplinaryCodeValue,
			final String disciplinaryCodeDescription,
			final String disciplinaryCodeExtendedDescription,
			final DateRange supervisoryOrganizationCodeDateRange, 
			final Date effectiveDate) {
		this.disciplinaryCodeId = disciplinaryCodeId;
		this.supervisoryOrganizationCodeId = supervisoryOrganizationCodeId;
		this.disciplinaryCodeValue = disciplinaryCodeValue;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.disciplinaryCodeExtendedDescription =
				disciplinaryCodeExtendedDescription;
		this.supervisoryOrganizationCodeDateRange 
			= supervisoryOrganizationCodeDateRange;
		if(supervisoryOrganizationCodeDateRange != null){
			if(effectiveDate != null){
				this.active = supervisoryOrganizationCodeDateRange
						.isActive(effectiveDate);
			}
			else{
				this.active = false;
			}
		}
		else{
			if (effectiveDate != null) {
				this.active = true;
			} 
			else {
				this.active = null;
			}
		}
	}

	/**
	 * Returns the disciplinary code's id
	 * @return the disciplinaryCodeId
	 */
	public Long getDisciplinaryCodeId() {
		return this.disciplinaryCodeId;
	}

	/**
	 * @return the supervisoryOrganizationCodeId
	 */
	public Long getSupervisoryOrganizationCodeId() {
		return supervisoryOrganizationCodeId;
	}

	/**
	 * Returns the disciplinary code's value
	 * @return the disciplinaryCodeValue
	 */
	public String getDisciplinaryCodeValue() {
		return this.disciplinaryCodeValue;
	}

	/**
	 * Returns the disciplinary code's description
	 * @return the disciplinaryCodeDescription
	 */
	public String getDisciplinaryCodeDescription() {
		return this.disciplinaryCodeDescription;
	}

	/**
	 * Returns the disciplinaryCodeExtendedDescription
	 * @return disciplinaryCodeExtendedDescription - String
	 */
	public String getDisciplinaryCodeExtendedDescription() {
		return disciplinaryCodeExtendedDescription;
	}

	/**
	 * Returns the supervisory organization code's date range
	 * @return the supervisoryOrganizationCodeDateRange
	 */
	public DateRange getSupervisoryOrganizationCodeDateRange(){
		return this.supervisoryOrganizationCodeDateRange;
	}

	/**
	 * Returns active
	 * @return the active
	 */
	public Boolean getActive() {
		return this.active;
	}
	
	
	
	

}
