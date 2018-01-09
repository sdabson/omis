package omis.health.report;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.ProviderAssignment;
import omis.health.domain.ReferralLocationDesignator;

/**
 * Summary of referral.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (May 9, 2014)
 * @since OMIS 3.0
 */
public class ReferralSummary
		implements Serializable, Comparable<ReferralSummary> {

	private static final long serialVersionUID = 1;
	
	private final Long id;
	
	private final String schedulingNotes;
	
	private final String assessmentNotes;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;

	private final Integer offenderNumber;
	
	private final String facilityName;
	
	private final ReferralType type;
	
	private final Date appointmentDate;
	
	private final Date appointmentTime;
	
	private final String statusReasonName;
	
	private final Boolean primaryProviderExists;
	
	private final String primaryProviderLastName;
	
	private final String primaryProviderFirstName;
	
	private final String primaryProviderTitleName;
	
	private final String primaryProviderTitleAbbreviation;
	
	private final String reasonName;
	
	private final String reasonNotes;
	
	private final String providerLevelName;
	
	private final ReferralLocationDesignator locationDesignator;
	
	private final Date reportedDate;
	
	private final String unitName;
	
	/**
	 * Instantiates a referral summary with all properties.
	 * 
	 * @param id ID of referral
	 * @param schedulingNotes scheduling notes
	 * @param assessmentNotes assessment notes
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param facilityName name of facility at which referral is held
	 * @param type type of referral
	 * @param appointmentDate date of appointment
	 * @param appointmentTime time of appointment
	 * @param statusReasonName name of status reason
	 * @param primaryProviderExists whether primary provider exists for referral
	 * @param primaryProviderLastName last name of primary provider
	 * @param primaryProviderFirstName first name of primary provider
	 * @param primaryProviderTitleName title name of primary provider
	 * @param primaryProviderTitleAbbreviation abbreviation of title of primary
	 * provider
	 * @param reasonName name of reason
	 * @param reasonNotes reason notes
	 * @param providerLevelName name of provider level
	 * @param locationDesignator location designator
	 * @param reportedDate reported date
	 * @param unitName unit name
	 */
	public ReferralSummary(
			final Long id,
			final String schedulingNotes,
			final String assessmentNotes,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final String facilityName,
			final String typeName,
			final Date appointmentDate,
			final Date appointmentTime,
			final String statusReasonName,
			final Boolean primaryProviderExists,
			final String primaryProviderLastName,
			final String primaryProviderFirstName,
			final String primaryProviderTitleName,
			final String primaryProviderTitleAbbreviation,
			final String reasonName,
			final String reasonNotes,
			final String providerLevelName,
			final ReferralLocationDesignator locationDesignator,
			final String unitName) {
		this.id = id;
		this.schedulingNotes = schedulingNotes;
		this.assessmentNotes = assessmentNotes;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.facilityName = facilityName;
		this.type = omis.health.report.ReferralType.valueOf(typeName);
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.statusReasonName = statusReasonName;
		this.primaryProviderExists = primaryProviderExists;
		this.primaryProviderLastName = primaryProviderLastName;
		this.primaryProviderFirstName = primaryProviderFirstName;
		this.primaryProviderTitleName = primaryProviderTitleName;
		this.primaryProviderTitleAbbreviation
			= primaryProviderTitleAbbreviation;
		this.reasonName = reasonName;
		this.reasonNotes = reasonNotes;
		this.providerLevelName = providerLevelName;
		this.locationDesignator = locationDesignator;
		this.unitName = unitName;
		// Unused propertied
		this.reportedDate = null;
	}
	
	/**
	 * Instantiates a referral summary with all properties except
	 * location designator and provider level.
	 *  
	 * @param id ID of referral
	 * @param schedulingNotes scheduling notes
	 * @param assessmentNotes assessment notes
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param facilityName name of facility at which referral is held
	 * @param type type of referral
	 * @param appointmenDate date of appointment
	 * @param appointmentTime time of appointment
	 * @param statusReasonName name of status reason
	 * @param providerAssignment assignment of provider
	 * @param reasonName name of reason
	 * @param reasonNotes reason notes
	 * @param reportedDate reported date
	 * @param unitName unit name
	 */
	public ReferralSummary(
			final Long id,
			final String schedulingNotes,
			final String assessmentNotes,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final String facilityName,
			final String typeName,
			final Date appointmenDate,
			final Date appointmentTime,
			final String statusReasonName,
			final ProviderAssignment providerAssignment,
			final String reasonName,
			final String reasonNotes,
			final Date reportedDate,
			final String unitName) {
		this.id = id;
		this.schedulingNotes = schedulingNotes;
		this.assessmentNotes = assessmentNotes;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.facilityName = facilityName;
		this.type = omis.health.report.ReferralType.valueOf(typeName);
		this.appointmentDate = appointmenDate;
		this.appointmentTime = appointmentTime;
		this.statusReasonName = statusReasonName;
		if (providerAssignment != null) {
			this.primaryProviderLastName
				= providerAssignment.getProvider().getName().getLastName();
			this.primaryProviderFirstName
				= providerAssignment.getProvider().getName().getFirstName();
			this.primaryProviderTitleName
				= providerAssignment.getTitle().getName();
			this.primaryProviderTitleAbbreviation
				= providerAssignment.getTitle().getAbbreviation();
			this.primaryProviderExists = true;
		} else {
			this.primaryProviderLastName = null;
			this.primaryProviderFirstName = null;
			this.primaryProviderTitleName = null;
			this.primaryProviderTitleAbbreviation = null;
			this.primaryProviderExists = false;
		}
		this.reasonName = reasonName;
		this.reasonNotes = reasonNotes;
		this.providerLevelName = null;
		this.locationDesignator = null;
		this.reportedDate = reportedDate;
		this.unitName = unitName;
	}
	
	
	/**
	 * Constructs a referral summary with the specified properties.
	 * 
	 * @param id id
	 * @param schedulingNotes scheduling notes
	 * @param assessmentNotes assessment notes
	 * @param offenderLastName offender last name
	 * @param offenderFirstName offender first name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param typeName type name
	 * @param appointmentDate appointment date
	 * @param appointmentTime time of appointment
	 * @param statusReasonName status reason name
	 */
	public ReferralSummary(
			final Long id,
			final String schedulingNotes,
			final String assessmentNotes,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final String typeName,
			final Date appointmentDate,
			final Date appointmentTime,
			final String statusReasonName,
			final String facilityName,
			final String reasonName) {
		this.id = id;
		this.schedulingNotes = schedulingNotes;
		this.assessmentNotes = assessmentNotes;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.type = omis.health.report.ReferralType.valueOf(typeName);
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.statusReasonName = statusReasonName;
		this.facilityName = facilityName;
		this.reasonName = reasonName;
		// Unused properties
		this.unitName = null;
		this.reasonNotes = null;
		this.providerLevelName = null;
		this.primaryProviderExists = null;
		this.primaryProviderFirstName = null;
		this.primaryProviderLastName = null;
		this.primaryProviderTitleAbbreviation = null;
		this.primaryProviderTitleName = null;
		this.reportedDate = null;
		this.locationDesignator = null;
	}
	
	/**
	 * Constructs a referral summary with the specified properties.
	 * 
	 * @param id id
	 * @param schedulingNotes scheduling notes
	 * @param assessmentNotes assessment notes
	 * @param offenderLastName offender last name
	 * @param offenderFirstName offender first name
	 * @param offenderMiddleName offender middle name
	 * @param offenderNumber offender number
	 * @param typeName type name
	 * @param appointmentDate appointment date
	 * @param appointmentTime time of appointment
	 * @param statusReasonName status reason name
	 */
	public ReferralSummary(
			final Long id,
			final String schedulingNotes,
			final String assessmentNotes,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final String typeName,
			final Date appointmentDate,
			final Date appointmentTime,
			final String statusReasonName,
			final String facilityName,
			final String primaryProviderFirstName,
			final String primaryProviderLastName,
			final String primaryProviderTitleAbbreviation,
			final String reasonName) {
		this.id = id;
		this.schedulingNotes = schedulingNotes;
		this.assessmentNotes = assessmentNotes;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.type = omis.health.report.ReferralType.valueOf(typeName);
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.statusReasonName = statusReasonName;
		this.facilityName = facilityName;
		this.reasonName = reasonName;
		// Unused properties
		this.unitName = null;
		this.reasonNotes = null;
		this.providerLevelName = null;
		this.primaryProviderExists = null;
		this.primaryProviderFirstName = primaryProviderFirstName;
		this.primaryProviderLastName = primaryProviderLastName;
		this.primaryProviderTitleAbbreviation = primaryProviderTitleAbbreviation;
		this.primaryProviderTitleName = null;
		this.reportedDate = null;
		this.locationDesignator = null;
	}
	
	/**
	 * Returns the ID of the referral.
	 * 
	 * @return ID of referral
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns the referral scheduling notes.
	 * 
	 * @return referral scheduling notes
	 */
	public String getSchedulingNotes() {
		return this.schedulingNotes;
	}
	
	/**
	 * Returns the referral assessment notes.
	 * 
	 * @return referral assessment notes
	 */
	public String getAssessmentNotes() {
		return this.assessmentNotes;
	}
	
	/**
	 * Returns the last name of the offender.
	 * 
	 * @return last name of offender
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns the first name of the offender.
	 * 
	 * @return first name of offender
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns the middle name of the offender.
	 * 
	 * @return middle name of offender
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns the offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns the name of the facility at which the referral is held.
	 * 
	 * @return name of facility at which referral referral is held 
	 */
	public String getFacilityName() {
		return this.facilityName;
	}
	
	/**
	 * Returns the type of referral.
	 * 
	 * @return type of referral
	 */
	public ReferralType getType() {
		return this.type;
	}
	
	/**
	 * Returns the date of the appointment.
	 * 
	 * @return date of appointment
	 */
	public Date getAppointmentDate() {
		return this.appointmentDate;
	}
	
	/**
	 * Returns the time of the appointment.
	 * 
	 * @return time of appointment
	 */
	public Date getAppointmentTime() {
		return this.appointmentTime;
	}
	
	/**
	 * Returns the name of the status reason.
	 * 
	 * @return name of status reason
	 */
	public String getStatusReasonName() {
		return this.statusReasonName;
	}
	
	/**
	 * Returns whether primary provider exists for referral.
	 * 
	 * @return whether primary provider exists for referral
	 */
	public Boolean getPrimaryProviderExists() {
		return this.primaryProviderExists;
	}

	/**
	 * Returns the last name of the primary provider.
	 * 
	 * @return last name of primary provider
	 */
	public String getPrimaryProviderLastName() {
		return this.primaryProviderLastName;
	}
	
	/**
	 * Returns the first name of the primary provider.
	 * 
	 * @return first name of primary provider 
	 */
	public String getPrimaryProviderFirstName() {
		return this.primaryProviderFirstName;
	}
	
	/**
	 * Returns the title name of the primary provider.
	 * 
	 * @return title name of primary provider 
	 */
	public String getPrimaryProviderTitleName() {
		return this.primaryProviderTitleName;
	}
	
	/**
	 * Returns the abbreviation of the title of the primary provider.
	 * 
	 * @return abbreviation of title of primary provider
	 */
	public String getPrimaryProviderTitleAbbreviation() {
		return this.primaryProviderTitleAbbreviation;
	}
	
	/**
	 * Returns name of reason.
	 * 
	 * @return name of reason
	 */
	public String getReasonName() {
		return this.reasonName;
	}
	
	/**
	 * Returns reason notes.
	 * 
	 * @return reason notes
	 */
	public String getReasonNotes() {
		return this.reasonNotes;
	}
	
	/**
	 * Returns name of provider level.
	 * 
	 * @return name of provider level
	 */
	public String getProviderLevelName() {
		return this.providerLevelName;
	}

	/**
	 * Returns the location designator.
	 * 
	 * @return location designator
	 */
	public ReferralLocationDesignator getLocationDesignator() {
		return this.locationDesignator;
	}

	/**
	 * Returns the reported date.
	 * 
	 * @return reported date
	 */
	public Date getReportedDate() {
		return this.reportedDate;
	}
	
	/**
	 * Returns the unit name.
	 * 
	 * @return unit name
	 */
	public String getUnitName() {
		return this.unitName;
	}
	
	/** {@inheritDoc} */
	@Override
	public int compareTo(final ReferralSummary o) {
		int result = 0;
		if (this.getAppointmentDate() != null) {
			result = this.getAppointmentDate().compareTo(
					o.getAppointmentDate());
			if (result != 0) {
				return result;
			}
		}
		if (this.getOffenderLastName() != null) {
			result = this.getOffenderLastName()
					.compareTo(o.getOffenderLastName());
			if (result != 0) {
				return result;
			}
		}
		if (this.getOffenderFirstName() != null) {
			result = this.getOffenderFirstName()
					.compareTo(o.getOffenderFirstName());
			if (result != 0) {
				return result;
			}
		}
		if (this.getOffenderNumber() != null) {
			result = this.getOffenderNumber().compareTo(o.getOffenderNumber());
			if (result != 0) {
				return result;
			}
		}
		return result;
	}
}