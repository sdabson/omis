package omis.residence.report;

import java.io.Serializable;
import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;

/**
 * Residence search summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 25, 2016)
 * @since OMIS 3.0
 */

public class ResidenceSearchSummary implements Serializable, 
	AddressSummarizable {

	private static final long serialVersionUID = 1L;

	private String personFirstName;

	private Long personId;
	
	private Long residenceId;
	
	private String personLastName;
	
	private String personMiddleName;
	
	private Date termStartDate;
	
	private Date termEndDate;
	
	private final AddressSummaryDelegate addressSummaryDelegate;
	
	private Boolean confirmed;
	
	private Boolean address;
	
	/**
	 * Constructor.
	 * 
	 * @param personFirstName person first name
	 * @param personId person ID 
	 * @param residenceId residence ID 
	 * @param personLastName person last name
	 * @param personMiddleName person middle name
	 * @param termStartDate term start date
	 * @param termEndDate term end date
	 * @param address address
	 * @param confirmed confirmed
	 */
	public ResidenceSearchSummary(final String personFirstName, 
			final Long personId, final Long residenceId, 
			final String personLastName, final String personMiddleName, 
			final Date termStartDate, final Date termEndDate, 
			final Address address, final Boolean confirmed) {
		this.personFirstName = personFirstName;
		this.personId = personId;
		this.residenceId = residenceId;
		this.personLastName = personLastName;
		this.personMiddleName = personMiddleName;
		this.termStartDate = termStartDate;
		this.termEndDate = termEndDate;
		if (address != null) {		
			this.setAddress(true);
		} else {
			this.setAddress(false);
		}
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.confirmed = confirmed;
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
	 * Gets the person first name.
	 * 
	 * @return the person first name
	 */
	public String getPersonFirstName() {
		return this.personFirstName;
	}

	/**
	 * Gets the person ID.
	 * 
	 * @return the person ID
	 */
	public Long getPersonId() {
		return this.personId;
	}

	/**
	 * Gets the residence ID.
	 * 
	 * @return the residence ID
	 */
	public Long getResidenceId() {
		return this.residenceId;
	}

	/**
	 * Gets the person last name.
	 * 
	 * @return the person last name
	 */
	public String getPersonLastName() {
		return this.personLastName;
	}

	/**
	 * Gets the person middle name.
	 * 
	 * @return the person middle name
	 */
	public String getPersonMiddleName() {
		return this.personMiddleName;
	}

	/**
	 * Gets the residence term start date.
	 * 
	 * @return the term start date
	 */
	public Date getTermStartDate() {
		return this.termStartDate;
	}

	/**
	 * Gets the residence term end date.
	 * 
	 * @return the term end date
	 */
	public Date getTermEndDate() {
		return this.termEndDate;
	}

	/**
	 * Gets the residence confirmation.
	 * 
	 * @return the confirmed
	 */
	public Boolean getConfirmed() {
		return this.confirmed;
	}

	/**
	 * @return the address
	 */
	public Boolean getAddress() {
		return this.address;
	}

	/**
	 * @param address address
	 */
	public void setAddress(Boolean address) {
		this.address = address;
	}
}