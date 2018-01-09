package omis.contact.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import omis.address.web.form.AddressFields;
import omis.offenderrelationship.web.form.OnlineAccountContactItem;
import omis.offenderrelationship.web.form.TelephoneNumberItem;

/**
 * Contact fields.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.1 (Jan 04, 2016)
 * @since OMIS 3.0
 */
public class ContactFields implements Serializable {

	private static final long serialVersionUID = 1L;

	private AddressFields mailingAddressFields;
	
	private PoBoxFields poBoxFields;
	
	private List<TelephoneNumberItem> telephoneNumbers 
		= new ArrayList<TelephoneNumberItem>();
	
	private List<OnlineAccountContactItem> onlineAccounts
		= new ArrayList<OnlineAccountContactItem>();
	
	/**
	 * Instantiates a default instance of contact fields.
	 */
	public ContactFields() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of contact fields with the specified properties.
	 * 
	 * @param mailingAddressFields mailing address fields
	 * @param poBoxFields post office box fields
	 * @param telephoneNumbers telephone numbers
	 * @param onlineAccounts online accounts
	 */
	public ContactFields(final AddressFields mailingAddressFields,
			final PoBoxFields poBoxFields, 
			final List<TelephoneNumberItem> telephoneNumbers,
			final List<OnlineAccountContactItem> onlineAccounts) {
		this.mailingAddressFields = mailingAddressFields;
		this.poBoxFields = poBoxFields;
		this.telephoneNumbers = telephoneNumbers;
		this.onlineAccounts = onlineAccounts;
	}
	
	/**
	 * Returns the address fields.
	 * 
	 * @return address fields
	 */
	public AddressFields getMailingAddressFields() {
		return this.mailingAddressFields;
	}

	/**
	 * Sets the address fields.
	 * 
	 * @param addressFields address fields
	 */
	public void setMailingAddressFields(final AddressFields mailingAddressFields) {
		this.mailingAddressFields = mailingAddressFields;
	}

	/**
	 * Returns post office box fields.
	 * 
	 * @return post office box fields
	 */
	public PoBoxFields getPoBoxFields() {
		return this.poBoxFields;
	}

	/**
	 * Sets post office box fields.
	 * 
	 * @param poBoxFields post office box fields
	 */
	public void setPoBoxFields(final PoBoxFields poBoxFields) {
		this.poBoxFields = poBoxFields;
	}

	/**
	 * Returns collection of telephone numbers.
	 * 
	 * @return telephone numbers 
	 */
	public List<TelephoneNumberItem> getTelephoneNumbers() {
		return this.telephoneNumbers;
	}
	
	/**
	 * Sets collection of telephone numbers.
	 * 
	 * @param telephoneNumbers telephone numbers
	 */
	public void setTelephoneNumbers(
			final List<TelephoneNumberItem> telephoneNumbers) {
		this.telephoneNumbers = telephoneNumbers;
	}

	/**
	 * Returns collection of online accounts.
	 * 
	 * @return online accounts
	 */
	public List<OnlineAccountContactItem> getOnlineAccounts() {
		return this.onlineAccounts;
	}

	/**
	 * Sets collection of online accounts.
	 * 
	 * @param onlineAccounts online accounts
	 */
	public void setOnlineAccounts(
			final List<OnlineAccountContactItem> onlineAccounts) {
		this.onlineAccounts = onlineAccounts;
	}
}