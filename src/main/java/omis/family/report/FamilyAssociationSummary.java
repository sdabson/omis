package omis.family.report;

import java.io.Serializable;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.report.TelephoneNumberSummarizable;
import omis.contact.report.delegate.TelephoneNumberSummaryDelegate;
import omis.family.domain.FamilyAssociation;
import omis.offender.domain.Offender;

/**
 * Family associations summary.
 * @author Yidong Li
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (June 7, 2017)
 * @since OMIS 3.0
 */

public class FamilyAssociationSummary
	implements AddressSummarizable, TelephoneNumberSummarizable, Serializable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String offenderLastName;
	private final String offenderFirstName;
	private final String offenderMiddleName;
	private final String offenderSuffix;
	private final Integer offenderNumber;
	private final String familyMemberLastName;
	private final String familyMemberFirstName;
	private final String familyMemberMiddleName;
	private final String familyMemberSuffix;
	private final Boolean familyMemberOffender;
	private final Integer familyMemberOffenderNumber;
	private final String categoryName;
	private final Boolean cohabitant;
	private final Boolean dependent;
	private final Boolean emergencyContact;
	private final AddressSummaryDelegate addressSummaryDelegate;
	private final TelephoneNumberSummaryDelegate telephoneNumberSummaryDelegate;
	private final Boolean hasAddress; 
	
	/**
	 * Instantiates family association summary.
	 * 
	 * @param familyAssociation family association
	 * @param address address
	 * @param telephoneNumber telephone number
	 * @param familyMember family member
	 */
	public FamilyAssociationSummary(
			final FamilyAssociation familyAssociation,
			final Address address,
			final TelephoneNumber telephoneNumber,
			final Offender familyMember) {
		this.id = familyAssociation.getId();
		this.offenderLastName = familyAssociation.getRelationship()
			.getFirstPerson().getName().getLastName();
		this.offenderFirstName = familyAssociation.getRelationship()
			.getFirstPerson().getName().getFirstName();
		this.offenderMiddleName = familyAssociation.getRelationship()
			.getFirstPerson().getName().getMiddleName();
		this.offenderSuffix = familyAssociation.getRelationship()
			.getFirstPerson().getName().getSuffix();
		Offender offender = (Offender) familyAssociation.getRelationship()
			.getFirstPerson();
		this.offenderNumber = offender.getOffenderNumber();
		this.familyMemberLastName = familyAssociation.getRelationship()
			.getSecondPerson().getName().getLastName();
		this.familyMemberFirstName = familyAssociation.getRelationship()
			.getSecondPerson().getName().getFirstName();
		this.familyMemberMiddleName = familyAssociation.getRelationship()
			.getSecondPerson().getName().getMiddleName();
		this.familyMemberSuffix = familyAssociation.getRelationship()
			.getSecondPerson().getName().getSuffix();
		
		if (familyMember != null) {
			this.familyMemberOffender = true;
			this.familyMemberOffenderNumber = familyMember.getOffenderNumber();
		} else {
			this.familyMemberOffender = false;
			this.familyMemberOffenderNumber = null;
		}
		this.categoryName = familyAssociation.getCategory().getName();
		if (familyAssociation.getFlags() != null) {
			this.cohabitant = familyAssociation.getFlags().getCohabitant();
			this.dependent = familyAssociation.getFlags().getDependent();
			this.emergencyContact = familyAssociation.getFlags()
				.getEmergencyContact();
		} else {
			this.cohabitant = null;
			this.dependent = null;
			this.emergencyContact = null;
		}
		this.addressSummaryDelegate = new AddressSummaryDelegate(address);
		this.telephoneNumberSummaryDelegate 
			= new TelephoneNumberSummaryDelegate(telephoneNumber);
		
		if (address != null) {
			hasAddress = true;
		} else {
			hasAddress = false;
		}
	}
	
	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns offender last name.
	 * 
	 * @return offenderLastName
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns offender first name.
	 * 
	 * @return offenderFirstName
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns offender middle name.
	 * 
	 * @return offenderMiddleName
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns offender suffix.
	 * 
	 * @return offenderSuffix offender suffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}
	
	/**
	 * Returns offender number.
	 * 
	 * @return offenderNumber offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns family member last name.
	 * 
	 * @return familyMemberLastName
	 */
	public String getFamilyMemberLastName() {
		return this.familyMemberLastName;
	}
	
	/**
	 * Returns family member first name.
	 * 
	 * @return familyMemberFirstName
	 */
	public String getFamilyMemberFirstName() {
		return this.familyMemberFirstName;
	}
	
	/**
	 * Returns family member middle name.
	 * 
	 * @return familyMemberMiddleName
	 */
	public String getFamilyMemberMiddleName() {
		return this.familyMemberMiddleName;
	}
	
	/**
	 * Returns family member suffix.
	 * 
	 * @return familyMemberSuffix
	 */
	public String getFamilyMemberSuffix() {
		return this.familyMemberSuffix;
	}
	
	/**
	 * Returns family member is offender or not.
	 * 
	 * @return familyMemberOffender
	 */
	public Boolean getFamilyMemberOffender() {
		return this.familyMemberOffender;
	}
	
	/**
	 * Returns family member offender number.
	 * 
	 * @return familyMemberOffenderNumber
	 */
	public Integer getFamilyMemberOffenderNumber() {
		return this.familyMemberOffenderNumber;
	}
	
	/**
	 * Returns family association category name.
	 * 
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * Returns cohabitant.
	 * 
	 * @return cohabitant
	 */
	public Boolean getCohabitant() {
		return this.cohabitant;
	}
	
	/**
	 * Returns dependent.
	 * 
	 * @return dependent
	 */
	public Boolean getDependent() {
		return this.dependent;
	}
	
	/**
	 * Returns emergency contact.
	 * 
	 * @return emergencyContact
	 */
	public Boolean getEmergencyContact() {
		return this.emergencyContact;
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

	/** {@inheritDoc} */
	@Override
	public Long getTelephoneNumberValue() {
		return this.telephoneNumberSummaryDelegate.getValue();
	}

	/** {@inheritDoc} */
	@Override
	public Integer getTelephoneNumberExtension() {
		return this.telephoneNumberSummaryDelegate.getExtension();
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getTelephoneNumberPrimary() {
		return this.telephoneNumberSummaryDelegate.getPrimary();
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getTelephoneNumberActive() {
		return this.telephoneNumberSummaryDelegate.getActive();
	}

	/** {@inheritDoc} */
	@Override
	public TelephoneNumberCategory getTelephoneNumberCategory() {
		return this.telephoneNumberSummaryDelegate.getCategory();
	}

	/**
	 * Gets whether or not family association has an address.
	 *
	 *
	 * @return boolean address
	 */
	public Boolean getHasAddress() {
		return this.hasAddress;
	}
}