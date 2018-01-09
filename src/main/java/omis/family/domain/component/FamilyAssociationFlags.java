package omis.family.domain.component;

import java.io.Serializable;

/**
 * Family association flags.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 10, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationFlags implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean cohabitant;
	
	private Boolean dependent;
	
	private Boolean emergencyContact;
	
	/**
	 * Instantiates a default instance of family association flags.
	 */
	public FamilyAssociationFlags() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of family association flags with the specified
	 * flags.
	 * 
	 * @param cohabitant cohabitant flag
	 * @param dependent dependent flag
	 * @param emergencyContact emergency contact flag
	 * @param  
	 */
	public FamilyAssociationFlags(final Boolean cohabitant, 
			final Boolean dependent, 
			final Boolean emergencyContact) {
		this.cohabitant = cohabitant;
		this.dependent = dependent;
		this.emergencyContact = emergencyContact;
	}

	/**
	 * Returns cohabitant flag.
	 * 
	 * @return cohabitant
	 */
	public Boolean getCohabitant() {
		return this.cohabitant;
	}

	/**
	 * Sets cohabitant flag.
	 * 
	 * @param cohabitant cohabitant
	 */
	public void setCohabitant(final Boolean cohabitant) {
		this.cohabitant = cohabitant;
	}

	/**
	 * Returns dependant flag.
	 * 
	 * @return dependant
	 */
	public Boolean getDependent() {
		return this.dependent;
	}

	/**
	 * Sets the dependant flag.
	 * 
	 * @param dependent dependent
	 */
	public void setDependent(final Boolean dependent) {
		this.dependent = dependent;
	}

	/**
	 * Returns the emergency contact flag.
	 * 
	 * @return emergency flag
	 */
	public Boolean getEmergencyContact() {
		return this.emergencyContact;
	}

	/**
	 * Sets the emergency contact flag.
	 * 
	 * @param emergencyContact emergency contact
	 */
	public void setEmergencyContact(final Boolean emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
}