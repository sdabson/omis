package omis.employment.report;

import java.io.Serializable;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;

/**
 * Employer search summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 4, 2016)
 * @since OMIS 3.0
 */
public class EmployerSearchSummary implements Serializable,
	AddressSummarizable {
	
	private static final long serialVersionUID = 1L;
	
	private final String employerLocationOrganizationName;
	
	private final AddressSummaryDelegate addressSummaryDelegate;

	private final Boolean address;
	
	private final Long offenderEmploymentCount;

	/**
	 * Constructor.
	 * 
	 * @param employerLocationOrganizationName employer location 
	 * organization name
	 * @param address address
	 */
	public EmployerSearchSummary(final String employerLocationOrganizationName,
			final Address address, final Long offenderEmploymentCount) {
		this.employerLocationOrganizationName 
			= employerLocationOrganizationName;
		if (address != null) {		
			this.address = true;
		} else {
			this.address = false;
		}
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.offenderEmploymentCount = offenderEmploymentCount;
	}

	/**
	 * Gets the employer location organization name.
	 * 
	 * @return the employerLocationOrganizationName
	 */
	public String getEmployerLocationOrganizationName() {
		return this.employerLocationOrganizationName;
	}

	/**
	 * Gets the address boolean.
	 * 
	 * @return the address
	 */
	public Boolean getAddress() {
		return this.address;
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
	 * Returns the offender employment count.
	 * 
	 * @return the offender employment count
	 */
	public Long getOffenderEmploymentCount() {
		return this.offenderEmploymentCount;
	}
}