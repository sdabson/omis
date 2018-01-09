package omis.health.report;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.ProviderAssignment;

/**
 * Summary of pending referral authorization.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 3, 2014)
 * @since OMIS 3.0
 */
public class PendingReferralAuthorizationSummary
		implements Serializable,
		Comparable<PendingReferralAuthorizationSummary> {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	private final String medicalFacilityName;
	
	private final Boolean primaryProviderExists;
	
	private final String primaryProviderLastName;
	
	private final String primaryProviderFirstName;
	
	private final String primaryProviderTitleName;
	
	private final String primaryProviderTitleAbbreviation;
	
	private final String reasonName;
	
	private final String reasonNotes;
	
	private final Boolean referredByProviderExists;
	
	private final String referredByProviderLastName;
	
	private final String referredByProviderFirstName;
	
	private final String referredByProviderTitleName;
	
	private final String referredByProviderTitleAbbreviation;
	
	private final Date referredDate;
	
	private final ReferralType type;

	/**
	 * Instantiates a summary of a pending referral authorization.
	 * 
	 * @param id ID
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param medicalFacilityName name of medical facility
	 * @param primaryProviderExists whether primary provider is set
	 * @param primaryProviderLastName last name of primary provider
	 * @param primaryProviderFirstName first name of primary provider
	 * @param primaryProviderTitleName name of title of primary provider
	 * @param primaryProviderTitleAbbreviation abbreviation of title of
	 * primary provider
	 * @param reasonName name of reason
	 * @param reasonNotes reason notes
	 * @param referredByProviderExists whether referred by provider exists
	 * @param referredByProviderLastName last name of referred by provider
	 * @param referredByProviderFirstName first name of referred by provider
	 * @param referredByProviderTitleName name of title of referred by provider
	 * @param referredByProviderTitleAbbreviation abbreviation of title of
	 * provider
	 * @param referredDate date referral was referred
	 * @param type name of type of referral pending authorization
	 */
	public PendingReferralAuthorizationSummary(
			final Long id, final String offenderLastName,
			final String offenderFirstName, final String offenderMiddleName,
			final Integer offenderNumber, final String medicalFacilityName,
			final Boolean primaryProviderExists,
			final String primaryProviderLastName,
			final String primaryProviderFirstName,
			final String primaryProviderTitleName,
			final String primaryProviderTitleAbbreviation,
			final String reasonName, final String reasonNotes,
			final Boolean referredByProviderExists,
			final String referredByProviderLastName,
			final String referredByProviderFirstName,
			final String referredByProviderTitleName,
			final String referredByProviderTitleAbbreviation,
			final Date referredDate, final String type) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.medicalFacilityName = medicalFacilityName;
		this.primaryProviderExists = primaryProviderExists;
		this.primaryProviderLastName = primaryProviderLastName;
		this.primaryProviderFirstName = primaryProviderFirstName;
		this.primaryProviderTitleName = primaryProviderTitleName;
		this.primaryProviderTitleAbbreviation
			= primaryProviderTitleAbbreviation;
		this.reasonName = reasonName;
		this.reasonNotes = reasonNotes;
		this.referredByProviderExists = referredByProviderExists;
		this.referredByProviderLastName = referredByProviderLastName;
		this.referredByProviderFirstName = referredByProviderFirstName;
		this.referredByProviderTitleName = referredByProviderTitleName;
		this.referredByProviderTitleAbbreviation
			= referredByProviderTitleAbbreviation;
		this.referredDate = referredDate;
		this.type = ReferralType.valueOf(type);
	}

	/**
	 * Instantiates a summary of a pending referral authorization.
	 * 
	 * @param id ID
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param medicalFacilityName name of medical facility
	 * @param primaryProviderAssignment assignment of primary provider
	 * primary provider
	 * @param reasonName name of reason
	 * @param reasonNotes reason notes
	 * @param referredByProviderAssignment assignment of provider that referred
	 * offender
	 * @param referredDate date referral was referred
	 * @param type name of type of referral pending authorization
	 */
	public PendingReferralAuthorizationSummary(
			final Long id, final String offenderLastName,
			final String offenderFirstName, final String offenderMiddleName,
			final Integer offenderNumber, final String medicalFacilityName,
			final ProviderAssignment primaryProviderAssignment,
			final String reasonName, final String reasonNotes,
			final Boolean referredByProviderExists,
			final ProviderAssignment referredByProviderAssignment,
			final Date referredDate, final String type) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.medicalFacilityName = medicalFacilityName;
		if (primaryProviderAssignment != null) {
			this.primaryProviderExists = true;
			this.primaryProviderLastName
				= primaryProviderAssignment.getProvider().getName()
					.getLastName();
			this.primaryProviderFirstName
				= primaryProviderAssignment.getProvider().getName()
					.getFirstName();
			this.primaryProviderTitleName
				= primaryProviderAssignment.getTitle().getName();
			this.primaryProviderTitleAbbreviation
				= primaryProviderAssignment.getTitle().getAbbreviation();
		} else {
			this.primaryProviderExists = false;
			this.primaryProviderLastName = null;
			this.primaryProviderFirstName = null;
			this.primaryProviderTitleName = null;
			this.primaryProviderTitleAbbreviation = null;
		}
		this.reasonName = reasonName;
		this.reasonNotes = reasonNotes;
		if (referredByProviderAssignment != null) {
			this.referredByProviderExists = true;
			this.referredByProviderLastName
				= referredByProviderAssignment.getProvider().getName()
					.getLastName();
			this.referredByProviderFirstName
				= referredByProviderAssignment.getProvider().getName()
					.getFirstName();
			this.referredByProviderTitleName
				= referredByProviderAssignment.getTitle().getName();
			this.referredByProviderTitleAbbreviation
				= referredByProviderAssignment.getTitle().getAbbreviation();
		} else {
			this.referredByProviderExists = true;
			this.referredByProviderLastName = null;
			this.referredByProviderFirstName = null;
			this.referredByProviderTitleName = null;
			this.referredByProviderTitleAbbreviation = null;
		}
		this.referredDate = referredDate;
		this.type = ReferralType.valueOf(type);
	}
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		 return this.id;
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
	 * Returns name of medical facility.
	 * 
	 * @return name of medical facility
	 */
	public String getMedicalFacilityName() {
		return this.medicalFacilityName;
	}
	
	/**
	 * Returns whether primary provider is set.
	 * 
	 * @return whether primary provider is set
	 */
	public Boolean getPrimaryProviderExists() {
		return this.primaryProviderExists;
	}
	
	/**
	 * Returns last name of primary provider.
	 * 
	 * @return last name of primary provider
	 */
	public String getPrimaryProviderLastName() {
		return this.primaryProviderLastName;
	}
	
	/**
	 * Returns first name of primary provider.
	 * 
	 * @return first name of primary provider
	 */
	public String getPrimaryProviderFirstName() {
		return this.primaryProviderFirstName;
	}
	
	/**
	 * Returns name of title of primary provider.
	 * 
	 * @return name of title of primary provider
	 */
	public String getPrimaryProviderTitleName() {
		return this.primaryProviderTitleName;
	}
	
	/**
	 * Returns abbreviation of title of primary provider.
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
	 * Returns notes for reason.
	 * 
	 * @return notes for reason
	 */
	public String getReasonNotes() {
		return this.reasonNotes;
	}
	
	/**
	 * Returns whether provider that referred offender is set.
	 * 
	 * @return whether provider that referred offender is set
	 */
	public Boolean getReferredByProviderExists() {
		return this.referredByProviderExists;
	}
	
	/**
	 * Returns last name of provider that referred offender.
	 * 
	 * @return last name of provider that referred offender
	 */
	public String getReferredByProviderLastName() {
		return this.referredByProviderLastName;
	}
	
	/**
	 * Returns first name of provider that referred offender.
	 * 
	 * @return first name of provider that referred offender
	 */
	public String getReferredByProviderFirstName() {
		return this.referredByProviderFirstName;
	}
	
	/**
	 * Returns name of title of provider that referred offender.
	 * 
	 * @return name of title of provider that referred offender
	 */
	public String getReferredByProviderTitleName() {
		return this.referredByProviderTitleName;
	}
	
	/**
	 * Returns abbreviation of title of provider that referred offender
	 * 
	 * @return abbreviation of title of provider that referred offender
	 */
	public String getReferredByProviderTitleAbbreviation() {
		return this.referredByProviderTitleAbbreviation;
	}
	
	/**
	 * Returns date that offender was referred.
	 * 
	 * @return date that offender was referred
	 */
	public Date getReferredDate() {
		return this.referredDate;
	}
	
	/**
	 * Returns the type.
	 * 
	 * @return type
	 */
	public ReferralType getType() {
		return this.type;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(final PendingReferralAuthorizationSummary o) {
		int result = 0;
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