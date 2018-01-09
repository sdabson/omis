package omis.detainernotification.report;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;

/**
 * DetainerAgencySummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 17, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerAgencySummary implements AddressSummarizable {
	
	private static final long serialVersionUID = 1L;
	
	private final AddressSummaryDelegate addressSummaryDelegate;
	
	private final String telephoneNumber;
	
	/**
	 * @param addressSummaryDelegate
	 * @param telephoneNumber
	 */
	public DetainerAgencySummary(
			final Address address,
			final String telephoneNumber) {
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.telephoneNumber = telephoneNumber;
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
	 * Returns the telephone number
	 * @return telephoneNumber - String
	 */
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}
}
