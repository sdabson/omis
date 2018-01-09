package omis.placement.report;

import java.io.Serializable;
import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;
import omis.locationterm.domain.LocationReasonTerm;
import omis.locationterm.domain.LocationTerm;
import omis.offender.domain.Offender;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.domain.SupervisoryOrganizationTerm;

/**
 * Summary of placement. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Nov 24, 2014)
 * @since OMIS 3.0
 */
public class PlacementSummary
		implements Serializable, AddressSummarizable {

	private static final long serialVersionUID = 1;

	private static final int MS_IN_DAY = 24 * 60 * 60 * 1000;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	private final Long correctionalStatusId;
	
	private final String correctionalStatusName;
	
	private final Date correctionalStatusStartDate;
	
	private final Date correctionalStatusEndDate;
	
	private final Long correctionalStatusDayCount;
	
	private final Long supervisoryOrganizationId;
	
	private final String supervisoryOrganizationName;
	
	private final Date supervisoryOrganizationStartDate;
	
	private final Date supervisoryOrganizationEndDate;
	
	private final Long supervisoryOrganizationDayCount;
	
	private final Boolean location;
	
	private final String locationName;
	
	private final String locationReasonName;
	
	private final Date locationStartDate;
	
	private final Date locationEndDate;
	
	private final Long locationDayCount;
	
	private final Boolean address;
	
	private final AddressSummaryDelegate addressSummaryDelegate;
	
	/**
	 * Instantiates a placement summary.
	 * 
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param correctionalStatusId ID of correctional status
	 * @param correctionalStatusName name of correctional status
	 * @param correctionalStatusStartDate correctional status start date
	 * @param correctionalStatusEndDate correctional status end date
	 * @param supervisoryOrganizationId ID of supervisory organization
	 * @param supervisoryOrganizationName name of supervisory organization
	 * @param supervisoryOrganizationStartDate supervisory organization start
	 * date
	 * @param supervisoryOrganizationEndDate supervisory organization end date
	 * @param locationName name of location
	 * @param locationReasonName name of location reason
	 * @param locationStartDate location start date
	 * @param locationEndDate location end date
	 * @param effectiveDate effective date; used to calculate day counts
	 */
	public PlacementSummary(
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final Long correctionalStatusId,
			final String correctionalStatusName,
			final Date correctionalStatusStartDate,
			final Date correctionalStatusEndDate,
			final Long supervisoryOrganizationId,
			final String supervisoryOrganizationName,
			final Date supervisoryOrganizationStartDate,
			final Date supervisoryOrganizationEndDate,
			final Boolean location,
			final String locationName,
			final String locationReasonName,
			final Date locationStartDate,
			final Date locationEndDate,
			final Date effectiveDate) {
		
		// TODO - Remove this constructor - SA
		throw new UnsupportedOperationException("Use other constructor");
	}
	
	/**
	 * Instantiates a placement summary.
	 * 
	 * @param offender offender
	 * @param correctionalStatusTerm correctional status term
	 * @param supervisoryOrganizationTerm supervisory organization term
	 * @param locationTerm location term
	 * @param locationReasonTerm location reason term
	 * @param address address
	 * @param effectiveDate effective date; used to calculate day counts
	 */
	public PlacementSummary(
			final Offender offender,
			final CorrectionalStatusTerm correctionalStatusTerm,
			final SupervisoryOrganizationTerm supervisoryOrganizationTerm,
			final LocationTerm locationTerm,
			final LocationReasonTerm locationReasonTerm,
			final Address address,
			final Date effectiveDate) {
		this.offenderLastName = offender.getName().getLastName();
		this.offenderFirstName = offender.getName().getFirstName();
		this.offenderMiddleName = offender.getName().getMiddleName();
		this.offenderNumber = offender.getOffenderNumber();
		if (correctionalStatusTerm != null) {
			this.correctionalStatusId = correctionalStatusTerm
					.getCorrectionalStatus().getId();
			this.correctionalStatusName
				= correctionalStatusTerm.getCorrectionalStatus().getName();
			if (correctionalStatusTerm.getDateRange() != null) {
				this.correctionalStatusStartDate
					= correctionalStatusTerm.getDateRange().getStartDate();
				this.correctionalStatusEndDate
					= correctionalStatusTerm.getDateRange().getEndDate();
			} else {
				this.correctionalStatusStartDate = null;
				this.correctionalStatusEndDate = null;
			}
			this.correctionalStatusDayCount
				= PlacementSummary.countDays(
						this.correctionalStatusStartDate,
						this.correctionalStatusEndDate,
						effectiveDate);
		} else {
			this.correctionalStatusId = null;
			this.correctionalStatusName = null;
			this.correctionalStatusStartDate = null;
			this.correctionalStatusEndDate = null;
			this.correctionalStatusDayCount = null;
		}
		if (supervisoryOrganizationTerm != null) {
			this.supervisoryOrganizationId = supervisoryOrganizationTerm
					.getSupervisoryOrganization().getId();
			this.supervisoryOrganizationName = supervisoryOrganizationTerm
					.getSupervisoryOrganization().getName();
			if (supervisoryOrganizationTerm.getDateRange() != null) {
				this.supervisoryOrganizationStartDate
					= supervisoryOrganizationTerm.getDateRange().getStartDate();
				this.supervisoryOrganizationEndDate
					= supervisoryOrganizationTerm.getDateRange().getEndDate();
			} else {
				this.supervisoryOrganizationStartDate = null;
				this.supervisoryOrganizationEndDate = null;
			}
			this.supervisoryOrganizationDayCount
				= PlacementSummary.countDays(
						this.supervisoryOrganizationStartDate,
						this.supervisoryOrganizationEndDate,
						effectiveDate);
		} else {
			this.supervisoryOrganizationId = null;
			this.supervisoryOrganizationName = null;
			this.supervisoryOrganizationStartDate = null;
			this.supervisoryOrganizationEndDate = null;
			this.supervisoryOrganizationDayCount = null;
		}
		if (locationTerm != null) {
			this.location = true;
			this.locationName
				= locationTerm.getLocation().getOrganization().getName();
			if (locationTerm.getDateRange() != null) {
				this.locationStartDate
					= locationTerm.getDateRange().getStartDate();
				this.locationEndDate
					= locationTerm.getDateRange().getEndDate();
			} else {
				this.locationStartDate = null;
				this.locationEndDate = null;
			}
			this.locationDayCount
				= PlacementSummary.countDays(
					this.locationStartDate,
					this.locationEndDate,
					effectiveDate);
		} else {
			this.location = null;
			this.locationName = null;
			this.locationStartDate = null;
			this.locationEndDate = null;
			this.locationDayCount = null;
		}
		if (locationReasonTerm != null) {
			this.locationReasonName = locationReasonTerm.getReason().getName();
		} else {
			this.locationReasonName = null;
		}
		if (address != null) {
			this.address = true;
		} else {
			this.address = null;
		}
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
	}
	
	/**
	 * Returns last name of offender.
	 * 
	 * @return last name of offender
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns first name of offender.
	 * 
	 * @return first name of offender
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns middle name of offender.
	 * 
	 * @return middle name of offender
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns ID of correctional status.
	 * 
	 * @return ID of correctional status
	 */
	public Long getCorrectionalStatusId() {
		return this.correctionalStatusId;
	}
	
	/**
	 * Returns name of correctional status.
	 * 
	 * @return name of correctional status
	 */
	public String getCorrectionalStatusName() {
		return this.correctionalStatusName;
	}
	
	/**
	 * Returns correctional status start date.
	 * 
	 * @return correctional status start date
	 */
	public Date getCorrectionalStatusStartDate() {
		return this.correctionalStatusStartDate;
	}
	
	/**
	 * Returns correctional status end date.
	 * 
	 * @return correctional status end date.
	 */
	public Date getCorrectionalStatusEndDate() {
		return this.correctionalStatusEndDate;
	}
	
	/**
	 * Returns count of days of correctional status.
	 * 
	 * @return count of days of correctional status
	 */
	public Long getCorrectionalStatusDayCount() {
		return this.correctionalStatusDayCount;
	}
	
	/**
	 * Returns ID of supervisory organization.
	 * 
	 * @return ID of supervisory organization
	 */
	public Long getSupervisoryOrganizationId() {
		return this.supervisoryOrganizationId;
	}
	
	/**
	 * Returns name of supervisory organization.
	 * 
	 * @return name of supervisory organization
	 */
	public String getSupervisoryOrganizationName() {
		return this.supervisoryOrganizationName;
	}
	
	/**
	 * Returns start date of term of supervisory organization.
	 * 
	 * @return start date of term of supervisory organization
	 */
	public Date getSupervisoryOrganizationStartDate() {
		return this.supervisoryOrganizationStartDate;
	}
	
	/**
	 * Returns end date of term of supervisory organization.
	 * 
	 * @return end date of term of supervisory organization
	 */
	public Date getSupervisoryOrganizationEndDate() {
		return this.supervisoryOrganizationEndDate;
	}
	
	/**
	 * Returns count of days of term of supervisory organization.
	 * 
	 * @return count of days of term of supervisory organization
	 */
	public Long getSupervisoryOrganizationDayCount() {
		return this.supervisoryOrganizationDayCount;
	}
	
	/**
	 * Returns whether offender resides at a location during the placement.
	 * 
	 * @return whether offender resides at a location during placement
	 */
	public Boolean getLocation() {
		return this.location;
	}
	
	/**
	 * Returns name of location.
	 * 
	 * @return name of location
	 */
	public String getLocationName() {
		return this.locationName;
	}
	
	/**
	 * Returns reason name of location.
	 * 
	 * @return reason name of location
	 */
	public String getLocationReasonName() {
		return this.locationReasonName;
	}
	
	/**
	 * Returns start date of term of location.
	 * 
	 * @return start date of term of location
	 */
	public Date getLocationStartDate() {
		return this.locationStartDate;
	}
	
	/**
	 * Returns end date of term of location.
	 * 
	 * @return end date of term of location
	 */
	public Date getLocationEndDate() {
		return this.locationEndDate;
	}
	
	/**
	 * Returns count of days of term of location.
	 * 
	 * @return count of days of term of location
	 */
	public Long getLocationDayCount() {
		return this.locationDayCount;
	}
	
	/**
	 * Returns whether address.
	 * 
	 * @return whether address
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
	
	// Returns number of days between start date and end date using a default
	// if one of the dates is missing. If both dates are missing, returns null.
	// If either start or end date and the default date is missing, returns
	// null.
	private static Long countDays(final Date startDate, final Date endDate,
			final Date defaultDate) {
		 final long startDiffDate;
		 if (startDate != null) {
			 startDiffDate = startDate.getTime();
		 } else {
			 if (defaultDate != null) {
				 startDiffDate = defaultDate.getTime();
			 } else {
				 return null;
			 }
		 }
		 final long endDiffDate;
		 if (endDate != null) {
			 endDiffDate = endDate.getTime();
		 } else {
			 if (defaultDate != null) {
				 endDiffDate = defaultDate.getTime();
			 } else {
				 return null;
			 }
		 }
		 return (endDiffDate - startDiffDate) / PlacementSummary.MS_IN_DAY;
	}
}