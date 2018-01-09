package omis.presentenceinvestigation.web.form;

import omis.offender.domain.Offender;
import omis.person.domain.Suffix;

/**
 * PresentenceInvestigationOffenderForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 3, 2016)
 *@since OMIS 3.0
 *
 */
//TODO remove
public class PresentenceInvestigationAddOffenderForm {
	
	private Offender offender;
	
	private String lastName;
	
	private String firstName;
	
	private String middleName;
	
	private Suffix suffix;
	
	private Boolean newOffender;
	
	/**
	 * Default Constructor for {@link PresentenceInvestigationRequestForm} 
	 */
	public PresentenceInvestigationAddOffenderForm() {
	}

	/**
	 * @return the offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * @param offender the offender to set
	 */
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the suffix
	 */
	public Suffix getSuffix() {
		return this.suffix;
	}

	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(Suffix suffix) {
		this.suffix = suffix;
	}

	/**
	 * @return the newOffender
	 */
	public Boolean getNewOffender() {
		return this.newOffender;
	}

	/**
	 * @param newOffender the newOffender to set
	 */
	public void setNewOffender(Boolean newOffender) {
		this.newOffender = newOffender;
	}
	
	

}
