package omis.location.web.form;

import java.util.Date;

import omis.address.domain.Address;
import omis.address.web.form.AddressFields;
import omis.location.web.controller.LocationAddressOperation;

/**
 * Location form.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 7, 2013)
 * @since OMIS 3.0
 */
public class LocationForm {
	
	private String organizationName;
	
	private Date startDate;
	
	private Date endDate;
	
	private LocationAddressOperation addressOperation;
	
	private AddressFields addressFields;
	
	private String addressQuery;
	
	private Address address;

	/** Instantiates a default location form. */
	public LocationForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the name of the organization.
	 * 
	 * @return name of organization
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}

	/**
	 * Sets the name of the organization.
	 * 
	 * @param organizationName name of organization
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns address operation.
	 * 
	 * @return address operation
	 */
	public LocationAddressOperation getAddressOperation() {
		return this.addressOperation;
	}

	/**
	 * Sets address operation.
	 * 
	 * @param addressOperation address operation
	 */
	public void setAddressOperation(
			final LocationAddressOperation addressOperation) {
		this.addressOperation = addressOperation;
	}

	/**
	 * Returns fields for address.
	 * 
	 * @return fields for address
	 */
	public AddressFields getAddressFields() {
		return this.addressFields;
	}

	/**
	 * Sets fields for address.
	 * 
	 * @param addressFields fields for address
	 */
	public void setAddressFields(final AddressFields addressFields) {
		this.addressFields = addressFields;
	}

	/**
	 * Returns query to search for address.
	 * 
	 * @return query to search for address
	 */
	public String getAddressQuery() {
		return this.addressQuery;
	}

	/**
	 * Sets query to search for address.
	 * 
	 * @param addressQuery query to search for address
	 */
	public void setAddressQuery(final String addressQuery) {
		this.addressQuery = addressQuery;
	}

	/**
	 * Returns address.
	 * 
	 * @return address
	 */
	public Address getAddress() {
		return this.address;
	}

	/**
	 * Sets address.
	 * 
	 * @param address address
	 */
	public void setAddress(final Address address) {
		this.address = address;
	}
}