package omis.detainernotification.web.form;

import omis.address.web.form.AddressFields;
import omis.contact.domain.TelephoneNumber;

/**
 * DetainerAgencyFields.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 14, 2016)
 *@since OMIS 3.0
 *
 */
public class DetainerAgencyFields {
	private String agencyName;
	private TelephoneNumber telephoneNumber;
	private AddressFields addressFields;
	
	public DetainerAgencyFields(){
		//nothing
	}

	/**
	 * @param agencyName
	 * @param agencyContact
	 * @param addressFields
	 */
	public DetainerAgencyFields(String agencyName, TelephoneNumber telephoneNumber, AddressFields addressFields) {
		this.agencyName = agencyName;
		this.telephoneNumber = telephoneNumber;
		this.addressFields = addressFields;
	}

	/**
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 * @param agencyName the agencyName to set
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	/**
	 * @return the agencyContact
	 */
	public TelephoneNumber getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param agencyContact the agencyContact to set
	 */
	public void setAgencyContact(TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the addressFields
	 */
	public AddressFields getAddressFields() {
		return addressFields;
	}

	/**
	 * @param addressFields the addressFields to set
	 */
	public void setAddressFields(AddressFields addressFields) {
		this.addressFields = addressFields;
	}
	
	
}
