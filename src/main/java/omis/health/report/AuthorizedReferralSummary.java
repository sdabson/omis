package omis.health.report;

import java.io.Serializable;

/**
 * Summary of referral authorized or review panel approved.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 15, 2014)
 * @since OMIS 3.0
 */
public class AuthorizedReferralSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;

	private final Integer offenderNumber;
	
	private final boolean primaryProviderExists;
	
	private final String primaryProviderLastName;
	
	private final String primaryProviderFirstName;
	
	private final String primaryProviderTitleName;
	
	private final String primaryProviderTitleAbbreviation;
	
	private final String reasonName;
	
	private final String reasonNotes;
	
	private final String medicalFacilityName;
	
	private final ReferralType type;
	
	/**
	 * Instantiates summary of referral authorized or review panel approved.
	 * 
	 * @param id ID
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param primaryProviderExists whether primary provider exists for referral
	 * @param primaryProviderLastName last name of primary provider
	 * @param primaryProviderFirstName first name of primary provider
	 * @param primaryProviderTitleName title of primary provider
	 * @param primaryProviderTitleAbbreviation abbreviation of primary provider
	 * title
	 * @param reasonName name of reason
	 * @param reasonNotes reason notes
	 * @param medicalFacilityName name of medical facility
	 * @param typeName name of type
	 */
	public AuthorizedReferralSummary(
			final Long id,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final boolean primaryProviderExists,
			final String primaryProviderLastName,
			final String primaryProviderFirstName,
			final String primaryProviderTitleName,
			final String primaryProviderTitleAbbreviation,
			final String reasonName,
			final String reasonNotes,
			final String medicalFacilityName,
			final String typeName) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.primaryProviderExists = primaryProviderExists;
		this.primaryProviderLastName = primaryProviderLastName;
		this.primaryProviderFirstName = primaryProviderFirstName;
		this.primaryProviderTitleName = primaryProviderTitleName;
		this.primaryProviderTitleAbbreviation
			= primaryProviderTitleAbbreviation;
		this.reasonName = reasonName;
		this.reasonNotes = reasonNotes;
		this.medicalFacilityName = medicalFacilityName;
		this.type = ReferralType.valueOf(typeName);
	}

	/**
	 * Returns the ID of authorization.
	 * 
	 * @return ID of authorization
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the last name of the offender.
	 * 
	 * @return last name of the offender
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns the first name of the offender.
	 * 
	 * @return first name of the offender
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns the middle name of the offender.
	 * 
	 * @return middle name of the offender
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
	 * Returns whether primary provider exists for referrals.
	 * 
	 * @return whether primary provider exists for referral
	 */
	public boolean getPrimaryProviderExists() {
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
	 * Returns the title of the primary provider.
	 * 
	 * @return title of primary provider
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
	 * Returns the name of the reason.
	 * 
	 * @return name of reason
	 */
	public String getReasonName() {
		return this.reasonName;
	}
	
	/**
	 * Returns the reason notes.
	 * 
	 * @return reason notes
	 */
	public String getReasonNotes() {
		return this.reasonNotes;
	}

	/**
	 * Returns the name of the medical facility.
	 * 
	 * @return name of medical facility
	 */
	public String getMedicalFacilityName() {
		return this.medicalFacilityName;
	}

	/**
	 * Returns the referral type.
	 * 
	 * @return referral type
	 */
	public ReferralType getType() {
		return this.type;
	}
}