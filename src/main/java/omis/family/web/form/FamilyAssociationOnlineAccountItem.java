package omis.family.web.form;

import java.io.Serializable;

import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.web.form.OnlineAccountFields;

/**
 * Family association email item.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.2 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public class FamilyAssociationOnlineAccountItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private OnlineAccountHost onlineAccountHost;
	private Boolean primary;
	private Boolean active;
	private FamilyAssociationOnlineAccountItemOperation operation;
	private OnlineAccountFields onlineAccountFields;
	private OnlineAccount onlineAccount;
	
	/**
	 * Instantiates a default instance of family association notes.
	 */
	public FamilyAssociationOnlineAccountItem() {
		//Default constructor.
	}

	/**
	 * Gets ID.
	 *
	 *
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the ID.
	 *
	 *
	 * @param id ID
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the email.
	 *
	 *
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 *
	 * @param email email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Gets the online account host.
	 *
	 *
	 * @return online account host
	 */
	public OnlineAccountHost getOnlineAccountHost() {
		return onlineAccountHost;
	}

	/**
	 * Sets the online account host.
	 *
	 *
	 * @param onlineAccountHost online account host.
	 */
	public void setOnlineAccountHost(
			final OnlineAccountHost onlineAccountHost) {
		this.onlineAccountHost = onlineAccountHost;
	}
	
	/**
	 * Gets primary.
	 *
	 *
	 * @return primary
	 */
	public Boolean getPrimary() {
		return primary;
	}

	/**
	 * Sets primary.
	 *
	 *
	 * @param primary primary
	 */
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}
	
	/**
	 * Gets active.
	 *
	 *
	 * @return active
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets active.
	 *
	 *
	 * @param active active
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}
	
	/**
	 * Gets the family association online account item operation.
	 *
	 *
	 * @return operation
	 */
	public FamilyAssociationOnlineAccountItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the family association online account item operation.
	 *
	 *
	 * @param operation operation
	 */
	public void setOperation(
		final FamilyAssociationOnlineAccountItemOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Gets the online account fields.
	 *
	 *
	 * @return online account fields
	 */
	public OnlineAccountFields getOnlineAccountFields() {
		return onlineAccountFields;
	}

	/**
	 * Sets the online account fields.
	 *
	 *
	 * @param onlineAccountFields online account fields
	 */
	public void setOnlineAccountFields(
		final OnlineAccountFields onlineAccountFields) {
		this.onlineAccountFields = onlineAccountFields;
	}

	/**
	 * Returns the online account.
	 * 
	 * @return online account
	 */
	public OnlineAccount getOnlineAccount() {
		return onlineAccount;
	}

	/**
	 * Sets the online account.
	 * 
	 * @param onlineAccount online account
	 */
	public void setOnlineAccount(OnlineAccount onlineAccount) {
		this.onlineAccount = onlineAccount;
	}
}