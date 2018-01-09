package omis.health.report;

import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;
import omis.health.domain.ProviderAssignmentCategory;



/** Health Provider Summary.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (Apr 04, 2014)
 * @since OMIS 3.0 */
public class ProviderSummary
	implements AddressSummarizable {
	
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String firstName;
	private final String lastName;
	private final String middleName;
	private final String providerAssignmentTitleName;
	private final String providerAssignmentTitleAbbreviation;
	private final ProviderAssignmentCategory providerAssignmentCategory;
	private final Date startDate;
	private final Date endDate;
	private final String medicalFacilityName;
	private final String facilityName;
	private final AddressSummaryDelegate addressSummaryDelegate;
	private final Boolean contracted;

	/** 
	 * Instantiates a provider summary with the specified properties.
	 * 
	 * @param id provider assignment id
	 * @param firstName first name
	 * @param lastName last name
	 * @param middleName middle name
	 * @param providerAssignmentTitleName name of provider assignment title
	 * @param providerAssignmentTitleAbbreviation abbreviation of provider
	 * assignment title
	 * @param providerAssignmentCategory provider assignment
	 * @param startDate start date
	 * @param endDate end date
	 * @param medicalFacilityName medical facility name
	 * @param facilityName facility name
	 * @param address address
	 * @param contracted contracted.
	 */
	public ProviderSummary(final Long id, final String firstName,
			final String lastName, final String middleName,
			final String providerAssignmentTitleName,
			final String providerAssignmentTitleAbbreviation,
			final ProviderAssignmentCategory providerAssignmentCategory,
			final Date startDate, final Date endDate,
			final String medicalFacilityName, final String facilityName,
			final Address address, final Boolean contracted) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.providerAssignmentTitleName = providerAssignmentTitleName;
		this.providerAssignmentTitleAbbreviation
			= providerAssignmentTitleAbbreviation;
		this.providerAssignmentCategory = providerAssignmentCategory;
		this.startDate = startDate;
		this.endDate = endDate;
		this.medicalFacilityName = medicalFacilityName;
		this.facilityName = facilityName;
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.contracted = contracted;
	}

	/** Gets provider id.
	 * 
	 * @return id id. 
	 */
	public Long getId() { return this.id; }

	/** 
	 * Gets first name.
	 * 
	 * @return firstName first name.
	 */
	public String getFirstName() { return this.firstName; }

	/** 
	 * Gets middle name.
	 * 
	 * @return middleName middle name. 
	 */
	public String getMiddleName() { return this.middleName; }

	/** 
	 * Gets last name.
	 * 
	 * @return lastName last name.
	 */
	public String getLastName() { return this.lastName; }

	/**
	 * Returns provider assignment title name.
	 * 
	 * @return provider assignment title name
	 */
	public String getProviderAssignmentTitleName() {
		return this.providerAssignmentTitleName;
	}
	
	/**
	 * Returns provider assignment title abbreviation.
	 * 
	 * @return provider assignment title abbreviation
	 */
	public String getProviderAssignmentTitleAbbreviation() {
		return this.providerAssignmentTitleAbbreviation;
	}
	
	/**
	 * Gets provider category.
	 * 
	 * @return providerCategory.
	 */
	public ProviderAssignmentCategory getProviderAssignmentCategory() {
		return this.providerAssignmentCategory;
	}

	/**
	 * Gets medical facility name.
	 * 
	 * @return medical facility name.
	 */
	public String getMedicalFacilityName() {
		return this.medicalFacilityName;
	}

	/**
	 * Gets start date.
	 * 
	 * @return startDate start date.
	 */
	public Date getStartDate() { return this.startDate; }

	/** 
	 * Gets end date.
	 * 
	 * @return endDate end date.
	 */
	public Date getEndDate() { return this.endDate; }

	/**
	 * Gets facility name.
	 * 
	 * @return facility name.
	 */
	public String getFacilityName() { return this.facilityName; }

	/**
	 * Returns the address summary delegate.
	 * 
	 * @return address summary delegate
	 */
	public AddressSummaryDelegate getAddressSummaryDelegate() {
		return this.addressSummaryDelegate;
	}

	/**
	 * Gets contracted flag.
	 * 
	 * @return contracted contracted.
	 * */
	public Boolean getContracted() {
		return this.contracted;
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
}