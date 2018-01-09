package omis.offender.web.form;

import java.util.Date;

import omis.person.domain.AlternativeNameCategory;

/**
 * ChangeOffenderNameForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 8, 2017)
 *@since OMIS 3.0
 *
 */
public class ChangeOffenderNameForm {
	
	private String lastName;
	
	private String firstName;
	
	private String middleName;
	
	private String suffix;
	
	private Date effectiveDate;
	
	private AlternativeNameCategory alternativeNameCategory;
	
	/**
	 * 
	 */
	public ChangeOffenderNameForm() {
	}

	/**
	 * Returns the lastName
	 * @return lastName - String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the lastName
	 * @param lastName - String
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the firstName
	 * @return firstName - String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstName
	 * @param firstName - String
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the middleName
	 * @return middleName - String
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middleName
	 * @param middleName - String
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Returns the suffix
	 * @return suffix - String
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Sets the suffix
	 * @param suffix - String
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Returns the effectiveDate
	 * @return effectiveDate - Date
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the effectiveDate
	 * @param effectiveDate - Date
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the alternativeNameCategory
	 * @return alternativeNameCategory - AlternativeNameCategory
	 */
	public AlternativeNameCategory getAlternativeNameCategory() {
		return alternativeNameCategory;
	}

	/**
	 * Sets the alternativeNameCategory
	 * @param alternativeNameCategory - AlternativeNameCategory
	 */
	public void setAlternativeNameCategory(
			final AlternativeNameCategory alternativeNameCategory) {
		this.alternativeNameCategory = alternativeNameCategory;
	}
	
}
