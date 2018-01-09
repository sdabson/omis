package omis.contact.report;

import java.util.Date;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.report.AddressSummarizable;
import omis.address.report.delegate.AddressSummaryDelegate;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountCategory;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.contact.report.delegate.OnlineAccountSummaryDelegate;
import omis.contact.report.delegate.PoBoxSummaryDelegate;
import omis.contact.report.delegate.TelephoneNumberSummaryDelegate;
import omis.demographics.domain.Sex;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.domain.PersonName;
import omis.person.report.PersonSummarizable;
import omis.person.report.delegate.PersonSummaryDelegate;

/**
 * Summary of contact.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 12, 2016)
 * @since OMIS 3.0
 */
public class ContactSummary
		implements PersonSummarizable, AddressSummarizable,
			PoBoxSummarizable, TelephoneNumberSummarizable,
			OnlineAccountSummarizable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final PersonSummaryDelegate personSummaryDelegate;
	
	private final AddressSummaryDelegate addressSummaryDelegate;
	
	private final PoBoxSummaryDelegate poBoxSummaryDelegate;
	
	private final TelephoneNumberSummaryDelegate telephoneNumberSummaryDelegate;
	
	private final OnlineAccountSummaryDelegate onlineAccountSummaryDelegate;

	private final Boolean personIdentity;
	
	private final Boolean address;
	
	private final Boolean telephoneNumber;
	
	private final Boolean onlineAccount;
	
	private final Boolean poBox;
	
	/**
	 * Instantiates summary of contact.
	 * 
	 * @param contact contact
	 * @param person person
	 * @param personName person name
	 * @param personIdentity person identity
	 * @param mailingAddress mailing address
	 * @param poBox PO box
	 * @param primaryTelephoneNumber primary telephone number
	 * @param primaryOnlineAccount primary online account
	 */
	public ContactSummary(
			final Contact contact,
			final Person person,
			final PersonName personName,
			final PersonIdentity personIdentity,
			final Address mailingAddress,
			final PoBox poBox,
			final TelephoneNumber primaryTelephoneNumber,
			final OnlineAccount primaryOnlineAccount) {
		if (contact != null) {
			this.id = contact.getId();
		} else {
			this.id = null;
		}
		if (personIdentity != null) {
			this.personIdentity = true;
		} else {
			this.personIdentity = false;
		}
		if (mailingAddress != null) {
			this.address = true;
		} else {
			this.address = false;
		}
		if (primaryTelephoneNumber != null) {
			this.telephoneNumber = true;
		} else {
			this.telephoneNumber = false;
		}
		if (primaryOnlineAccount != null) {
			this.onlineAccount = true;
		} else {
			this.onlineAccount = false;
		}
		if (poBox != null) {
			this.poBox = true;
		} else {
			this.poBox = false;
		}
		this.personSummaryDelegate = new PersonSummaryDelegate(
				person, personName, personIdentity);
		this.addressSummaryDelegate = new AddressSummaryDelegate(
				mailingAddress);
		this.poBoxSummaryDelegate = new PoBoxSummaryDelegate(poBox);
		this.telephoneNumberSummaryDelegate
			= new TelephoneNumberSummaryDelegate(primaryTelephoneNumber);
		this.onlineAccountSummaryDelegate
			= new OnlineAccountSummaryDelegate(primaryOnlineAccount);
	}

	/**
	 * Returns ID of contact.
	 * 
	 * @return ID of contact
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns whether online account exists.
	 * 
	 * @return whether online account exists
	 */
	public Boolean getOnlineAccount() {
		return this.onlineAccount;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getOnlineAccountName() {
		return this.onlineAccountSummaryDelegate.getName();
	}

	/** {@inheritDoc} */
	@Override
	public String getOnlineAccountHostName() {
		return this.onlineAccountSummaryDelegate.getHostName();
	}

	/** {@inheritDoc} */
	@Override
	public OnlineAccountCategory getOnlineAccountCategory() {
		return this.onlineAccountSummaryDelegate.getCategory();
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getOnlineAccountActive() {
		return this.onlineAccountSummaryDelegate.getActive();
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getOnlineAccountPrimary() {
		return this.onlineAccountSummaryDelegate.getPrimary();
	}

	/**
	 * Returns whether telephone number exists.
	 * 
	 * @return whether telephone number exists
	 */
	public Boolean getTelephoneNumber() {
		return this.telephoneNumber;
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
	 * Returns whether PO box exists.
	 * 
	 * @return whether PO box exists
	 */
	public Boolean getPoBox() {
		return this.poBox;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getPoBoxValue() {
		return this.poBoxSummaryDelegate.getValue();
	}

	/** {@inheritDoc} */
	@Override
	public String getPoBoxCityName() {
		return this.poBoxSummaryDelegate.getCityName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPoBoxStateName() {
		return this.poBoxSummaryDelegate.getStateName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPoBoxStateAbbreviation() {
		return this.poBoxSummaryDelegate.getStateAbbreviation();
	}

	/** {@inheritDoc} */
	@Override
	public String getPoBoxZipCodeValue() {
		return this.poBoxSummaryDelegate.getZipCodeValue();
	}

	/** {@inheritDoc} */
	@Override
	public String getPoBoxZipCodeExtension() {
		return this.poBoxSummaryDelegate.getZipCodeExtension();
	}

	/** {@inheritDoc} */
	@Override
	public String getPoBoxCountryName() {
		return this.poBoxSummaryDelegate.getCountryName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPoBoxCountryAbbreviation() {
		return this.poBoxSummaryDelegate.getCountryAbbreviation();
	}

	/**
	 * Returns whether address exists.
	 * 
	 * @return whether address exists
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

	/** {@inheritDoc} */
	@Override
	public Long getPersonId() {
		return this.personSummaryDelegate.getId();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonLastName() {
		return this.personSummaryDelegate.getLastName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonFirstName() {
		return this.personSummaryDelegate.getFirstName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonMiddleName() {
		return this.personSummaryDelegate.getMiddleName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonSuffix() {
		return this.personSummaryDelegate.getSuffix();
	}

	/**
	 * Returns whether person has an identity.
	 * 
	 * @return whether person has an identity
	 */
	public Boolean getPersonIdentity() {
		return this.personIdentity;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getPersonSocialSecurityNumber() {
		return this.personSummaryDelegate.getSocialSecurityNumber();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonStateIdNumber() {
		return this.personSummaryDelegate.getStateIdNumber();
	}

	/** {@inheritDoc} */
	@Override
	public Date getPersonBirthDate() {
		return this.personSummaryDelegate.getBirthDate();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonBirthPlaceName() {
		return this.personSummaryDelegate.getBirthPlaceName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonBirthStateName() {
		return this.personSummaryDelegate.getBirthStateName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonBirthStateAbbreviation() {
		return this.personSummaryDelegate.getBirthStateAbbreviation();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonBirthCountryName() {
		return this.personSummaryDelegate.getBirthCountryName();
	}

	/** {@inheritDoc} */
	@Override
	public String getPersonBirthCountryAbbreviation() {
		return this.personSummaryDelegate.getBirthCountryAbbreviation();
	}

	/** {@inheritDoc} */
	@Override
	public Sex getPersonSex() {
		return this.personSummaryDelegate.getSex();
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getPersonDeceased() {
		return this.personSummaryDelegate.getDeceased();
	}

	/** {@inheritDoc} */
	@Override
	public Date getPersonDeathDate() {
		return this.personSummaryDelegate.getDeathDate();
	}
}