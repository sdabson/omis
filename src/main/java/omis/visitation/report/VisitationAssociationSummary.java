package omis.visitation.report;

import java.io.Serializable;
import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.report.TelephoneNumberSummarizable;
import omis.contact.report.delegate.TelephoneNumberSummaryDelegate;
import omis.offender.domain.Offender;
import omis.visitation.domain.VisitationAssociation;

/**
 * Visitor List Summary.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Oct 31, 2017)
 * @since OMIS 3.0
 */
public class VisitationAssociationSummary implements TelephoneNumberSummarizable, 
	AddressSummarizable, Serializable {
	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String offenderLastName;
	private final String offenderFirstName;
	private final String offenderMiddleName;
	private final String offenderSuffix;
	private final Integer offenderNumber;
	private final Long visitorId;
	private final String visitorLastName;
	private final String visitorFirstName;
	private final String visitorMiddleName;
	private final String visitorSuffix;
	private final Boolean visitorOffender;
	private final Date visitorBirthDate;
	private final Integer visitorOffenderNumber;
	private final Integer visitorSocialSecurityNumber;
	private final Boolean address;
	private final Boolean telephoneNumber;
	private final String categoryName;
	private final Boolean approved;
	private final Date decisionDate;
	private final Boolean money;
	private final Boolean nonContact;
	private final Boolean courtOrder;
	private final Boolean specialVisit;
	private final Long alternativeNameCount;
	private final TelephoneNumberSummaryDelegate telephoneNumberSummaryDelegate;
	private final AddressSummaryDelegate addressSummaryDelegate;
	private final Boolean currentlyVisiting;
	private final Long visitId;
	
	
	
	
//	private final Person secondPerson;
	
	/**
	 * Instantiates an instance of visitor list summary.
	 * 
	 * @param visitationAssociation visitation association
	 * @param address address
	 * @param telephoneNumberField telephoneNumber
	 * @param visitor who is offender
	 * @param alternativeNameCount the total number of alternative names
	 */
	public VisitationAssociationSummary(
		final VisitationAssociation visitationAssociation,
		final Address addressField, final TelephoneNumber telephoneNumberField,
		final Offender visitor, final Long alternativeNameCount,
		final Boolean currentlyVisiting, final Long visitId) {
		this.id = visitationAssociation.getId();
		this.offenderLastName = visitationAssociation.getRelationship()
			.getFirstPerson().getName().getLastName();
		this.offenderFirstName = visitationAssociation.getRelationship()
			.getFirstPerson().getName().getFirstName();
		this.offenderMiddleName = visitationAssociation.getRelationship()
			.getFirstPerson().getName().getMiddleName();
		this.offenderSuffix = visitationAssociation.getRelationship()
			.getFirstPerson().getName().getSuffix();
		Offender offender = (Offender)visitationAssociation.getRelationship()
			.getFirstPerson(); 
		this.offenderNumber = offender.getOffenderNumber();
		this.visitorFirstName = visitationAssociation.getRelationship()
			.getSecondPerson().getName().getFirstName();
		this.visitorLastName = visitationAssociation.getRelationship()
			.getSecondPerson().getName().getLastName();
		this.visitorMiddleName = visitationAssociation.getRelationship()
			.getSecondPerson().getName().getMiddleName();
		this.visitorSuffix = visitationAssociation.getRelationship()
			.getSecondPerson().getName().getSuffix();
		
		if(visitationAssociation.getRelationship().getSecondPerson()
			.getIdentity()!=null){
			this.visitorSocialSecurityNumber = visitationAssociation
				.getRelationship().getSecondPerson().getIdentity()
				.getSocialSecurityNumber();
			this.visitorBirthDate = visitationAssociation
				.getRelationship().getSecondPerson().getIdentity().getBirthDate();
		} else {
			this.visitorSocialSecurityNumber = null;
			this.visitorBirthDate = null;
		}
		
		this.categoryName = visitationAssociation.getCategory().getName();
		if(visitationAssociation.getApproval()!=null){
			this.approved = visitationAssociation.getApproval().getApproved();
			this.decisionDate = visitationAssociation.getApproval()
				.getDecisionDate();
		} else {
			this.approved = null;
			this.decisionDate = null;
		}
		this.money = visitationAssociation.getFlags().getMoney();
		this.nonContact = visitationAssociation.getFlags().getNonContact();
		this.courtOrder = visitationAssociation.getFlags().getCourtOrder();
		this.specialVisit = visitationAssociation.getFlags().getSpecialVisit();
		
		if(visitor != null) {
			this.visitorOffender = true;
			this.visitorOffenderNumber = visitor.getOffenderNumber();
		} else {
			this.visitorOffender = false;
			this.visitorOffenderNumber = null;
		}
		
		if(addressField!=null){
			this.address = true;
		} else {
			this.address = false;
		}
		if(telephoneNumberField!=null&&telephoneNumberField.getPrimary()==true){
			this.telephoneNumber = true;
		} else {
			this.telephoneNumber = false;
		}
		
		this.alternativeNameCount = alternativeNameCount;
		this.addressSummaryDelegate = new AddressSummaryDelegate(addressField);
		this.telephoneNumberSummaryDelegate 
			= new TelephoneNumberSummaryDelegate(telephoneNumberField);
		this.visitId = visitId;
		this.currentlyVisiting = currentlyVisiting;
		this.visitorId = visitationAssociation.getRelationship()
			.getSecondPerson().getId();
		
		
		
		
		/*this.secondPerson = visitationAssociation.getRelationship()
				.getSecondPerson();*/
	}
	
	/**
	 * Return the visitation association id of the visitor summary.
	 * @return visitation association id
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns the offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns the offender first name.
	 * 
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns the offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns the offender suffix.
	 * 
	 * @return offender suffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
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
	 * Returns the visitor ID.
	 * 
	 * @return visitorId
	 */
	public Long getVisitorId() {
		return this.visitorId;
	}
	
	/**
	 * Returns the visitor last name.
	 * 
	 * @return visitor last name
	 */
	public String getVisitorLastName() {
		return this.visitorLastName;
	}

	/**
	 * Returns the visitor first name.
	 * 
	 * @return visitor first name
	 */
	public String getVisitorFirstName() {
		return this.visitorFirstName;
	}
	
	/**
	 * Returns the visitor middle name.
	 * 
	 * @return visitor middle name
	 */
	public String getVisitorMiddleName() {
		return this.visitorMiddleName;
	}
	
	/**
	 * Returns the visitor suffix.
	 * 
	 * @return visitor suffix
	 */
	public String getVisitorSuffix() {
		return this.visitorSuffix;
	}
	
	/**
	 * Returns if the visitor is an offender.
	 * 
	 * @return if the visitor is an offender
	 */
	public Boolean getVisitorOffender() {
		return this.visitorOffender;
	}
	
	/**
	 * Returns the visitor birth date.
	 * 
	 * @return the visitor birth date
	 */
	public Date getVisitorBirthDate() {
		return this.visitorBirthDate;
	}
	
	/**
	 * Returns the visitor offender number.
	 * 
	 * @return the visitor offender number
	 */
	public Integer getVisitorOffenderNumber() {
		return this.visitorOffenderNumber;
	}
	
	/**
	 * Returns the visitor social security number.
	 * 
	 * @return the visitor social security number
	 */
	public Integer getVisitorSocialSecurityNumber() {
		return this.visitorSocialSecurityNumber;
	}
	
	/**
	 * Returns the address.
	 * 
	 * @return the address
	 */
	public Boolean getAddress() {
		return this.address;
	}
	
	/**
	 * Returns the telephone number.
	 * 
	 * @return the telephone number
	 */
	public Boolean getTelephoneNumber() {
		return this.telephoneNumber;
	}
	
	/**
	 * Returns the category name.
	 * 
	 * @return the category name
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * Returns the approved.
	 * 
	 * @return the approved
	 */
	public Boolean getApproved() {
		return this.approved;
	}
	
	/**
	 * Returns the decision date.
	 * 
	 * @return the decision date
	 */
	public Date getDecisionDate() {
		return this.decisionDate;
	}
	
	/**
	 * Returns the money.
	 * 
	 * @return the money
	 */
	public Boolean getMoney() {
		return this.money;
	}
	
	/**
	 * Returns the nonContact.
	 * 
	 * @return the nonContact
	 */
	public Boolean getNonContact() {
		return this.nonContact;
	}
	
	/**
	 * Returns the courtOrder.
	 * 
	 * @return the courtOrder
	 */
	public Boolean getCourtORder() {
		return this.courtOrder;
	}
	
	/**
	 * Returns the specialVisit.
	 * 
	 * @return the specialVisit
	 */
	public Boolean getSpecialVisit() {
		return this.specialVisit;
	}
	
	/**
	 * Returns the alternativeNameCount.
	 * 
	 * @return the alternativeNameCount
	 */
	public Long getAlternativeNameCount() {
		return this.alternativeNameCount;
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
	
	public Boolean getCurrentlyVisiting() {
		return this.currentlyVisiting;
	}

	public Long getVisitId() {
		return this.visitId;
	}
	
	
	
	
	/*public Person getSecondPerson() {
		return this.secondPerson;
	}*/
}