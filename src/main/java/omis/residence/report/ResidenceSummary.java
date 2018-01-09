/**
 * 
 */
package omis.residence.report;

import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;
import omis.datatype.DateRange;
import omis.residence.domain.ResidenceCategory;
import omis.residence.domain.ResidenceStatus;

/**
 * Residence summary.
 * 
 * @author Sheronda Vaughn
 * @author Annie Wahl
 * @version 0.1.0 (Feb 20, 2015)
 * @since  OMIS 3.0
 */
public class ResidenceSummary 
	implements AddressSummarizable {

	private static final long serialVersionUID = 1L;

	private final Long id;
	
	private final ResidenceType type;
	
	private final String offenderFirstName;
	
	private final String offenderLastName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	private final ResidenceStatus residenceStatus;
	
	private final String locationName;
	
	private final ResidenceCategory category;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final Boolean active;
	
	private final AddressSummaryDelegate addressSummaryDelegate;
	
	private final Boolean location;
	
	private final Boolean address;
	
	/**
	 * Residence term constructor.
	 * 
	 * @param id id
	 * @param typeName type name
	 * @param offenderFirstName offender first name
	 * @param offenderLastName offender last name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param residenceStatus residence status
	 * @param locationName location name
	 * @param category category
	 * @param startDate start date
	 * @param endDate end date
	 * @param address address
	 * @param effectiveDate effective date
	 */
	public ResidenceSummary(
			final Long id, final String typeName,			
			final String offenderFirstName, final String offenderLastName,
			final String offenderMiddleName, final Integer offenderNumber,
			final ResidenceStatus residenceStatus, final String locationName,
			final ResidenceCategory category, final Date startDate,
			final Date endDate, final Address address, 
			final Date effectiveDate) {
		this.id = id;
		this.type = ResidenceType.valueOf(typeName);
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.residenceStatus = residenceStatus;
		this.locationName = locationName;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.active = new DateRange(startDate, endDate).isActive(effectiveDate);
		this.address = null;
		this.location = null;
	}
	
	/**
	 * Residence term constructor.
	 * 
	 * @param id id
	 * @param typeName type name
	 * @param offenderFirstName offender first name
	 * @param offenderLastName offender last name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param residenceStatus residence status
	 * @param category category
	 * @param startDate start date
	 * @param endDate end date
	 * @param address address
	 */
	public ResidenceSummary(
			final Long id, final String typeName,			
			final String offenderFirstName, final String offenderLastName,
			final String offenderMiddleName, final Integer offenderNumber,
			final ResidenceStatus residenceStatus,
			final ResidenceCategory category, final Date startDate,
			final Date endDate, final Address address) {
		this.id = id;
		this.type = ResidenceType.valueOf(typeName);
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.residenceStatus = residenceStatus;
		this.locationName = null;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.active = null;
		if (locationName != null) { 
			this.location = true;			
		} else {
			this.location = false;			
		}
		if (address == null) {
			this.address = false;
		} else {
			this.address = true;
		}
	}
	
	/**
	 * Non residence term constructor.
	 * 
	 * @param id id
	 * @param typeName type name
	 * @param offenderFirstName offender first name
	 * @param offenderLastName offender last name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param residenceStatus residence status
	 * @param locationName location name
	 * @param startDate start date
	 * @param endDate end date
	 * @param address address
	 * @param effectiveDate effective date
	 */
	public ResidenceSummary(
			final Long id, final String typeName,			
			final String offenderFirstName, final String offenderLastName,
			final String offenderMiddleName, final Integer offenderNumber,
			final ResidenceStatus residenceStatus, final String locationName,
			final Date startDate, final Date endDate, final Address address,
			final Date effectiveDate) {
		this.id = id;
		this.type = ResidenceType.valueOf(typeName);
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.residenceStatus = residenceStatus;	
		this.locationName = locationName;
		this.category = null;
		this.startDate = startDate;
		this.endDate = endDate;	
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.active = new DateRange(startDate, endDate).isActive(effectiveDate);
		if (locationName != null) { 
			this.location = true;			
		} else {
			this.location = false;			
		}
		if (addressSummaryDelegate.getValue() == null) {
			this.address = false;
		} else {
			this.address = true;
		}
	}
	
	/**
	 * Residence Summary Constructor
	 * 
	 * @param id
	 * @param residenceStatus
	 * @param locationName
	 * @param startDate
	 * @param endDate
	 * @param address
	 */
	public ResidenceSummary(
			final Long id,
			final ResidenceStatus residenceStatus, final String locationName,
			final Date startDate, final Date endDate, final Address address) {
		this.id = id;
		this.type = null;
		this.offenderFirstName = null;
		this.offenderLastName = null;
		this.offenderMiddleName = null;
		this.offenderNumber = null;
		this.residenceStatus = residenceStatus;
		this.locationName = locationName;
		this.category = null;
		this.startDate = startDate;
		this.endDate = endDate;
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.active = null;
		this.address = null;
		this.location = null;
	}
	
	/**
	 * Gets the id of the term.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Gets the residence type.
	 * 
	 * @return type
	 */
	public ResidenceType getType() {
		return this.type;
	}
	
	/**
	 * Gets the offender first name.
	 * 
	 * @return the offender first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Gets the offender last name.
	 * 
	 * @return the offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;		
	}
	
	/**
	 * Gets the offender middle name.
	 * 
	 * @return the offender middle name
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Gets the offender number.
	 * 
	 * @return the offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Gets the status of the residence.
	 * 
	 * @return the residence status
	 */
	public ResidenceStatus getResidenceStatus() {
		return this.residenceStatus;
	}
	
	/**
	 * Gets the location name.
	 * 
	 * @return the location name
	 */
	public String getLocationName() {
		return this.locationName;
	}
			
	/**
	 * Gets the residence category.
	 * 
	 * @return the category
	 */
	public ResidenceCategory getCategory() {
		return this.category;
	}
	
	/**
	 * Gets the start date.
	 * 
	 * @return the start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Gets the end date.
	 * 
	 * @return the end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Gets the active status.
	 * 
	 * @return active
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
	 * Gets the location status.
	 *
	 * @return the location
	 */
	public Boolean getLocation() {
		return this.location;
	}

	/**
	 * Gets the address status.
	 *
	 * @return the address
	 */
	public Boolean getAddress() {
		return this.address;
	}
}