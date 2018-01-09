package omis.offendercontact.web.form;

import java.io.Serializable;
import java.util.List;

import omis.address.domain.Address;
import omis.address.web.form.AddressFields;
import omis.contact.web.form.PoBoxFields;

/**
 * Form to edit an offender contact.
 *
 * @author Josh Divine
 * @version 0.0.1 (Dec 13, 2016)
 * @since OMIS 3.0
 */
public class OffenderContactForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Boolean showMailingAddressFields;
	
	private Boolean enterMailingAddressFields;
	
	private OffenderContactMailingAddressOperation mailingAddressOperation;
	
	private String existingMailingAddressQuery;
	
	private Address existingMailingAddress;
	
	private AddressFields mailingAddressFields;
	
	private Boolean showPoBoxFields;
	
	private Boolean enterPoBoxFields;
	
	private PoBoxFields poBoxFields;
	
	private Boolean showTelephoneNumberItems;
	
	private List<OffenderContactTelephoneNumberItem> telephoneNumberItems;
	
	private Boolean showOnlineAccountItems;
	
	private List<OffenderContactOnlineAccountItem> onlineAccountItems;

	/** Instantiates a default form to edit an offender contact. */
	public OffenderContactForm() {
		// Default instantiation
	}

	/**
	 * Sets whether to show mailing address fields.
	 * 
	 * @param showMailingAddressFields whether to show mailing address fields
	 */
	public void setShowMailingAddressFields(
			final Boolean showMailingAddressFields) {
		this.showMailingAddressFields = showMailingAddressFields;
	}
	
	/**
	 * Returns whether to show mailing address fields.
	 * 
	 * @return whether to show mailing address fields
	 */
	public Boolean getShowMailingAddressFields() {
		return this.showMailingAddressFields;
	}
	
	/**
	 * Sets whether to enter mailing address fields.
	 * 
	 * @param enterMailingAddressFields whether to enter mailing address fields
	 */
	public void setEnterMailingAddressFields(
			final Boolean enterMailingAddressFields) {
		this.enterMailingAddressFields = enterMailingAddressFields;
	}
	
	/**
	 * Returns whether to enter mailing address fields.
	 * 
	 * @return whether to enter mailing address fields
	 */
	public Boolean getEnterMailingAddressFields() {
		return this.enterMailingAddressFields;
	}
	
	/**
	 * Sets mailing address operation.
	 * 
	 * @param mailingAddressOperation mailing address operation
	 */
	public void setMailingAddressOperation(
			final OffenderContactMailingAddressOperation 
			mailingAddressOperation) {
		this.mailingAddressOperation = mailingAddressOperation;
	}
	
	/**
	 * Returns mailing address operation.
	 * 
	 * @return mailing address operation
	 */
	public OffenderContactMailingAddressOperation getMailingAddressOperation() {
		return this.mailingAddressOperation;
	}

	/**
	 * Sets existing mailing address query.
	 * 
	 * @param existingMailingAddressQuery existing mailing address query
	 */
	public void setExistingMailingAddressQuery(
			final String existingMailingAddressQuery) {
		this.existingMailingAddressQuery = existingMailingAddressQuery;
	}
	
	/**
	 * Returns existing mailing address query.
	 * 
	 * @return existing mailing address query
	 */
	public String getExistingMailingAddressQuery() {
		return this.existingMailingAddressQuery;
	}

	/**
	 * Sets existing mailing address.
	 * 
	 * @param existingMailingAddress existing mailing address
	 */
	public void setExistingMailingAddress(
			final Address existingMailingAddress) {
		this.existingMailingAddress = existingMailingAddress;
	}
	
	/**
	 * Returns existing mailing address.
	 * 
	 * @return existing mailing address
	 */
	public Address getExistingMailingAddress() {
		return this.existingMailingAddress;
	}
	
	/**
	 * Sets mailing address fields.
	 * 
	 * @param mailingAddressFields mailing address fields
	 */
	public void setMailingAddressFields(
			final AddressFields mailingAddressFields) {
		this.mailingAddressFields = mailingAddressFields;
	}
	
	/**
	 * Returns mailing address fields.
	 * 
	 * @return mailing address fields
	 */
	public AddressFields getMailingAddressFields() {
		return this.mailingAddressFields;
	}
	
	/**
	 * Sets whether to show PO box fields.
	 * 
	 * @param showPoBoxFields whether to show PO box fields
	 */
	public void setShowPoBoxFields(final Boolean showPoBoxFields) {
		this.showPoBoxFields = showPoBoxFields;
	}
	
	/**
	 * Returns whether to show PO box fields.
	 * 
	 * @return whether to show PO box fields
	 */
	public Boolean getShowPoBoxFields() {
		return this.showPoBoxFields;
	}
	
	/**
	 * Sets whether to enter PO box fields.
	 * 
	 * @param enterPoBoxFields whether to enter PO box fields
	 */
	public void setEnterPoBoxFields(final Boolean enterPoBoxFields) {
		this.enterPoBoxFields = enterPoBoxFields;
	}
	
	/**
	 * Returns whether to enter PO box fields.
	 * 
	 * @return whether to enter PO box fields
	 */
	public Boolean getEnterPoBoxFields() {
		return this.enterPoBoxFields;
	}
	
	/**
	 * Sets PO box fields.
	 * 
	 * @param poBoxFields PO box fields
	 */
	public void setPoBoxFields(final PoBoxFields poBoxFields) {
		this.poBoxFields = poBoxFields;
	}
	
	/**
	 * Returns PO box fields.
	 * 
	 * @return PO box fields
	 */
	public PoBoxFields getPoBoxFields() {
		return this.poBoxFields;
	}
	
	/**
	 * Sets whether to show telephone number items.
	 * 
	 * @param showTelephoneNumberItems show telephone number items
	 */
	public void setShowTelephoneNumberItems(
			final Boolean showTelephoneNumberItems) {
		this.showTelephoneNumberItems = showTelephoneNumberItems;
	}
	
	/**
	 * Returns whether to show telephone number items.
	 * 
	 * @return whether to show telephone number items
	 */
	public Boolean getShowTelephoneNumberItems() {
		return this.showTelephoneNumberItems;
	}
	
	/**
	 * Sets telephone number items.
	 * 
	 * @param telephoneNumberItems telephone number items
	 */
	public void setTelephoneNumberItems(
			final List<OffenderContactTelephoneNumberItem> telephoneNumberItems)
	{
		this.telephoneNumberItems = telephoneNumberItems;
	}
	
	/**
	 * Returns telephone number items.
	 * 
	 * @return telephone number items
	 */
	public List<OffenderContactTelephoneNumberItem> getTelephoneNumberItems() {
		return this.telephoneNumberItems;
	}
	
	/**
	 * Sets whether to show online account items.
	 * 
	 * @param showOnlineAccountItems whether to show online account items
	 */
	public void setShowOnlineAccountItems(
			final Boolean showOnlineAccountItems) {
		this.showOnlineAccountItems = showOnlineAccountItems;
	}
	
	/**
	 * Returns whether to show online account items.
	 * 
	 * @return whether to show online account items
	 */
	public Boolean getShowOnlineAccountItems() {
		return this.showOnlineAccountItems;
	}
	
	/**
	 * Sets online account items.
	 * 
	 * @param onlineAccountItems online account items
	 */
	public void setOnlineAccountItems(
			final List<OffenderContactOnlineAccountItem> onlineAccountItems) {
		this.onlineAccountItems = onlineAccountItems;
	}
	
	/**
	 * Returns online account items.
	 * 
	 * @return online account items
	 */
	public List<OffenderContactOnlineAccountItem> getOnlineAccountItems() {
		return this.onlineAccountItems;
	}

}
