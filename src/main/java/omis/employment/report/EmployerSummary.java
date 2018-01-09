package omis.employment.report;

import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;

/**
 * Employment summary.
 * 
 * @author Yidong
 * @author Joel Norris 
 * @version 0.1.0 (June 7, 2017)
 * @since OMIS 3.0
 */

public class EmployerSummary implements AddressSummarizable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String organizationName;
	private final Date locationStartDate;
	private final Date locationEndDate;
	private final AddressSummaryDelegate addressSummaryDelegate;
	private final Long telephoneNumber;
	
	/**
	 * Instantiates a default instance of the employer item.
	 */
	public EmployerSummary(
		final Long id, final String organizationName,
		final Date locationStartDate, final Date locationEndDate,
		final Address address, final Long telephoneNumber){
		this.id = id;
		this.organizationName = organizationName;
		this.locationStartDate = locationStartDate;
		this.locationEndDate = locationEndDate;
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.telephoneNumber = telephoneNumber;
	}
	
	/**
	 * Return the employer Id.
	 * 
	 * @return the employer Id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Return the organization name.
	 * 
	 * @return the organization name
	 */
	public String getOrganizationName() {
		return this.organizationName;
	}
	
	/**
	 * Return the location start date.
	 * 
	 * @return the location start date
	 */
	public Date getLocationStartDate() {
		return this.locationStartDate;
	}
	
	/**
	 * Return the location end date.
	 * 
	 * @return the location end date
	 */
	public Date getLocationEndDate() {
		return this.locationEndDate;
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
		
	public Long getTelephoneNumber() {
		return this.telephoneNumber;
	}
}