package omis.offender.report;

import java.io.Serializable;
import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;
import omis.demographics.domain.Sex;
import omis.offender.domain.Offender;

/**
 * Offender search summary.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 07, 2016)
 * @since OMIS 3.0
 */
public class OffenderSearchSummary implements Serializable, 
	AddressSummarizable {

	private static final long serialVersionUID = 1L;
	
	private final Offender offender;
	
	private final String lastName;
	
	private final String firstName;
	
	private final String middleName;
	
	private final String suffixName;
		
	private final String supervisoryOrganizationName;
	
	private final String correctionalStatusName;
	
	private final Date dateOfBirth;
	
	private final Sex sexLabel;
	
	private final Boolean active;
		
	private final AddressSummaryDelegate addressSummaryDelegate;
	
	private final Boolean address;	
	
	private final String locationName;

	/* Constructors. */
	
	/**
	 * Instantiates an instance of offender search summary with the specified values.
	 * 
	 * @param offender offender
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param supervisoryOrganizationName supervisory organization 
	 * description
	 * @param correctionalStatusName correctional status description
	 * @param dateOfBirth date of birth
	 * @param sexLabel sex label
	 * @param address address
	 * @param active active
	 */
	public OffenderSearchSummary(
			final Offender offender, final String lastName, 
			final String firstName, final String middleName, 
			final String suffixName,
			final String supervisoryOrganizationName, 
			final String correctionalStatusName, final Date dateOfBirth,
			final Sex sexLabel, final Address address,  final String locationName,
			final Boolean active) {
		this.offender = offender;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffixName = suffixName;
		this.supervisoryOrganizationName 
			= supervisoryOrganizationName;
		this.correctionalStatusName = correctionalStatusName;
		this.dateOfBirth = dateOfBirth;
		this.sexLabel = sexLabel;	
		if (address != null) {		
			this.address = true;
		} else {
			this.address = false;
		}
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.locationName = locationName;
		this.active = active;		
	}	
	
	/**
	 * Instantiates an instance of offender search summary with the specified, providing null for address,
	 * address summary delegate, and active.
	 * 
	 * @param offender offender
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffixName suffix name
	 * @param dateOfBirth date of birth
	 * @param sexLabel sex label
	 */
	public OffenderSearchSummary(
			final Offender offender, final String lastName, 
			final String firstName, final String middleName, 
			final String suffixName,
			final Date dateOfBirth, final Sex sexLabel) {
		this.offender = offender;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.suffixName = suffixName;
		this.supervisoryOrganizationName = null;
		this.correctionalStatusName = null;
		this.dateOfBirth = dateOfBirth;
		this.sexLabel = sexLabel;	
		this.address = null;
		this.addressSummaryDelegate = null;
		this.locationName = null;
		this.active = null;
	}

	/**
	 * Gets the offender.
	 * 
	 * @return the offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * Gets last name.
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Gets the middle name.
	 * 
	 * @return the middleName
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Gets the suffix name.
	 *
	 * @return the suffixName
	 */
	public String getSuffixName() {
		return this.suffixName;
	}

	/**
	 * Gets the supervisory organization description.
	 * 
	 * @return the supervisory organization description
	 */
	public String getSupervisoryOrganizationName() {
		return this.supervisoryOrganizationName;
	}

	/**
	 * Gets the correctional status description.
	 * 
	 * @return the correctional status description
	 */
	public String getCorrectionalStatusName() {
		return this.correctionalStatusName;
	}

	/**
	 * Gets the date of birth.
	 * 
	 * @return the date of birth
	 */
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	/**
	 * Gets the sex label.
	 * 
	 * @return the sex label
	 */
	public Sex getSexLabel() {
		return this.sexLabel;
	}

	/**
	 * Gets the active offenders.
	 * 
	 * @return the active
	 */
	public Boolean getActive() {		
		return this.active;
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressValue() {
		return this.addressSummaryDelegate.getValue();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressDesignator() {
		return this.addressSummaryDelegate.getDesignator();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressCoordinates() {
		return this.addressSummaryDelegate.getCoordinates();
	}

	/** {@inheritDoc} */
	@Override
	public BuildingCategory getAddressCategory() {
		return this.addressSummaryDelegate.getCategory();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressCityName() {
		return this.addressSummaryDelegate.getCityName();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressStateName() {
		return this.addressSummaryDelegate.getStateName();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressStateAbbreviation() {
		return this.addressSummaryDelegate.getStateAbbreviation();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressZipCodeValue() {
		return this.addressSummaryDelegate.getZipCodeValue();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressZipCodeExtension() {
		return this.addressSummaryDelegate.getZipCodeExtension();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressCountryName() {
		return this.addressSummaryDelegate.getCountryName();
	}

	/** {@inheritDoc} */
	@Override
	public String getAddressCountryAbbreviation() {
		return this.addressSummaryDelegate.getCountryAbbreviation();
	}

	/**
	 * Gets the address boolean.
	 * 
	 * @return the address
	 */
	public Boolean getAddress() {
		return this.address;
	}

	/**
	 * Gets the location name.
	 * 
	 * @return the locagion name
	 */
	public String getLocationName() {
		return this.locationName;
	}
}