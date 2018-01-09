package omis.employment.web.form;

import java.io.Serializable;

import omis.address.domain.Address;
import omis.address.web.form.AddressFields;
import omis.employment.domain.Employer;
import omis.employment.domain.EmploymentTerm;

/**
 * Form to change employer.
 *
 * @author Yidong Li
 * @version 0.0.1 (Aug 19, 2015)
 * @since OMIS 3.0
 */
public class EmployerChangeForm
		implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean newAddress;
	private Address addressSearchResult;
	private String addressQuery;
	private Boolean newEmployer;
	private Employer existingEmployer;
	private String newEmployerName;
	private AddressFields addressFields;
	private EmploymentTerm employmentTerm;
	private String telephoneNumber;
	
	/** Instantiates employer change form. */
	public EmployerChangeForm() {
		// Default instantiation
	}

	/**
	 * Returns new employer.
	 * 
	 * @return new employer
	 */
	public Boolean getNewEmployer() {
		return this.newEmployer;
	}
	
	/**
	 * Sets if new employer is created.
	 * 
	 * @param new employer new employer which is to be create
	 */
	public void setNewEmployer(final Boolean newEmployer) {
		this.newEmployer = newEmployer;
	}
	
	/**
	 * Returns if it is new address.
	 * 
	 * @return if it is new address
	 */
	public Boolean getNewAddress() {
		return this.newAddress;
	}
	
	/**
	 * Sets if it is new address.
	 * 
	 * @param new address
	 */
	public void setNewAddress(final Boolean newAddress) {
		this.newAddress = newAddress;
	}
	
	/**
	 * Returns address search result.
	 * 
	 * @return address search result
	 */
	public Address getAddressSearchResult() {
		return this.addressSearchResult;
	}
	
	/**
	 * Sets address search result.
	 * 
	 * @param address search result
	 */
	public void setAddressSearchResult(
		final Address addressSearchResult) {
		this.addressSearchResult = addressSearchResult;
	}
	
	/**
	 * Returns address query.
	 * 
	 * @return address query
	 */
	public String getAddressQuery() {
		return this.addressQuery;
	}
	
	/**
	 * Sets address query.
	 * 
	 * @param address query
	 */
	public void setAddressQuery(
		final String addressQuery) {
		this.addressQuery = addressQuery;
	}
	
	/**
	 * Returns existing employer.
	 * 
	 * @return existing employer
	 */
	public Employer getExistingEmployer() {
		return this.existingEmployer;
	}
	
	/**
	 * Sets existing employer.
	 * 
	 * @param existing employer
	 */
	public void setExistingEmployer(final Employer existingEmployer) {
		this.existingEmployer = existingEmployer;
	}
	
	/**
	 * Returns new employer name.
	 * 
	 * @return new employer name
	 */
	public String getNewEmployerName() {
		return this.newEmployerName;
	}
	
	/**
	 * Sets new employer name.
	 * 
	 * @param new employer name
	 */
	public void setNewEmployerName(final String newEmployerName) {
		this.newEmployerName = newEmployerName;
	}
		
	/**
	 * Returns address fields.
	 * 
	 * @return address fields
	 */
	public AddressFields getAddressFields() {
		return this.addressFields;
	}
	
	/**
	 * Sets address fields.
	 * 
	 * @param address fields
	 */
	public void setAddressFields(final AddressFields addressFields) {
		this.addressFields = addressFields;
	}
	
	/**
	 * Returns employment term.
	 * 
	 * @return employment term
	 */
	public EmploymentTerm getEmploymentTerm() {
		return this.employmentTerm;
	}
	
	/**
	 * Sets employment term.
	 * 
	 * @param employment term
	 */
	public void setEmploymentTerm(final EmploymentTerm employmentTerm) {
		this.employmentTerm = employmentTerm;
	}
	
	/**
	 * Returns telephone number.
	 * 
	 * @return telephone number
	 */
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}
	
	/**
	 * Sets telephone number.
	 * 
	 * @param telephone number
	 */
	public void setTelephoneNumber(final String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
}