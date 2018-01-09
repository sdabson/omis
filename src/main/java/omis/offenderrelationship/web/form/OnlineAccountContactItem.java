package omis.offenderrelationship.web.form;

import java.io.Serializable;

import omis.contact.domain.OnlineAccount;
import omis.contact.web.form.OnlineAccountContactItemOperation;
import omis.contact.web.form.OnlineAccountFields;

/**
 * Contact online account item.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @author Yidong Li
 * @version 0.1.0 (Jun 3, 2015)
 * @since OMIS 3.0
 */
public class OnlineAccountContactItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;	

	private OnlineAccountContactItemOperation operation;
	
	private OnlineAccountFields onlineAccountFields;
	
	private OnlineAccount onlineAccount;
	
	/**
	 * Instantiates a default instance of online account contact item.
	 */
	public OnlineAccountContactItem() {
		//Default constructor.
	}

	/**
	 * Return the id of the online account contact item.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Set the id of the online account contact item.
	 * 
	 * @param id id
	 */
	public void setId(final Long id) {
		this.id = id;
	}
	
	
	/**
	 * Returns online account contact item operation.
	 * 
	 * @return operation
	 */
	public OnlineAccountContactItemOperation getOperation() {
		return this.operation;
	}
	
	/**
	 * Sets the online account contact item operation.
	 * 	
	 * @param operation operation
	 */
	public void setOperation(
			final OnlineAccountContactItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns the online account fields.
	 * 
	 * @return the onlineAccountFields online account fields
	 */
	public OnlineAccountFields getOnlineAccountFields() {
		return this.onlineAccountFields;
	}

	/**
	 * Sets the online account fields.
	 * 
	 * @param onlineAccountFields online account fields
	 */
	public void setOnlineAccountFields(final OnlineAccountFields 
			onlineAccountFields) {
		this.onlineAccountFields = onlineAccountFields;
	}
	
	/**
	 * Returns the online account.
	 * 
	 * @return the onlineAccount online account
	 */
	public OnlineAccount getOnlineAccount() {
		return this.onlineAccount;
	}

	/**
	 * Sets the online account.
	 * 
	 * @param onlineAccount online account
	 */
	public void setOnlineAccount(final OnlineAccount onlineAccount) {
		this.onlineAccount = onlineAccount;
	}
}