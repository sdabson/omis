package omis.victim.web.form;

import java.io.Serializable;
import java.util.List;

import omis.address.domain.Address;
import omis.address.web.form.AddressFields;
import omis.contact.web.form.PoBoxFields;
import omis.person.web.form.PersonFields;

/**
 * Form to edit victim associations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 2, 2015)
 * @since OMIS 3.0
 */
public class VictimAssociationForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private VictimFields victimFields;
	
	private List<VictimNoteItem> noteItems;
	
	private Boolean showPersonFields;

	private PersonFields personFields;
	
	private Boolean showMailingAddressFields;
	
	private Boolean enterMailingAddressFields;
	
	private VictimMailingAddressOperation mailingAddressOperation;
	
	private String existingMailingAddressQuery;
	
	private Address existingMailingAddress;
	
	private AddressFields mailingAddressFields;
	
	private Boolean showPoBoxFields;
	
	private Boolean enterPoBoxFields;
	
	private PoBoxFields poBoxFields;
	
	private Boolean showTelephoneNumberItems;
	
	private List<VictimTelephoneNumberItem> telephoneNumberItems;
	
	private Boolean showOnlineAccountItems;
	
	private List<VictimOnlineAccountItem> onlineAccountItems;
	
	/** Instantiates a default form to edit victim associations. */
	public VictimAssociationForm() {
		// Default instantiation
	}
	
	/**
	 * Returns victim fields.
	 * 
	 * @return victim fields
	 */
	public VictimFields getVictimFields() {
		return this.victimFields;
	}

	/**
	 * Sets victim fields.
	 * 
	 * @param victimFields victim fields
	 */
	public void setVictimFields(final VictimFields victimFields) {
		this.victimFields = victimFields;
	}

	/**
	 * Returns note items.
	 * 
	 * @return note items
	 */
	public List<VictimNoteItem> getNoteItems() {
		return this.noteItems;
	}

	/**
	 * Sets note items.
	 * 
	 * @param noteItems note items
	 */
	public void setNoteItems(final List<VictimNoteItem> noteItems) {
		this.noteItems = noteItems;
	}

	/**
	 * Returns whether to show person fields.
	 * 
	 * @return whether to show person fields
	 */
	public Boolean getShowPersonFields() {
		return this.showPersonFields;
	}

	/**
	 * Sets whether to show person fields.
	 * 
	 * @param showPersonFields whether to show person fields
	 */
	public void setShowPersonFields(final Boolean showPersonFields) {
		this.showPersonFields = showPersonFields;
	}
	
	/**
	 * Returns person fields.
	 * 
	 * @return person fields
	 */
	public PersonFields getPersonFields() {
		return this.personFields;
	}

	/**
	 * Sets person fields.
	 * 
	 * @param personFields person fields
	 */
	public void setPersonFields(final PersonFields personFields) {
		this.personFields = personFields;
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
			final VictimMailingAddressOperation mailingAddressOperation) {
		this.mailingAddressOperation = mailingAddressOperation;
	}
	
	/**
	 * Returns mailing address operation.
	 * 
	 * @return mailing address operation
	 */
	public VictimMailingAddressOperation getMailingAddressOperation() {
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
			final List<VictimTelephoneNumberItem> telephoneNumberItems) {
		this.telephoneNumberItems = telephoneNumberItems;
	}
	
	/**
	 * Returns telephone number items.
	 * 
	 * @return telephone number items
	 */
	public List<VictimTelephoneNumberItem> getTelephoneNumberItems() {
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
			final List<VictimOnlineAccountItem> onlineAccountItems) {
		this.onlineAccountItems = onlineAccountItems;
	}
	
	/**
	 * Returns online account items.
	 * 
	 * @return online account items
	 */
	public List<VictimOnlineAccountItem> getOnlineAccountItems() {
		return this.onlineAccountItems;
	}
}