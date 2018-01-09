package omis.offendercontact.web.form;

import java.io.Serializable;

import omis.contact.domain.OnlineAccount;
import omis.contact.web.form.OnlineAccountFields;

/**
 * Offender contact online account item.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenderContactOnlineAccountItem
		implements Serializable {

	private static final long serialVersionUID = 1;
	
	private OnlineAccountFields fields;
	
	private OffenderContactOnlineAccountOperation operation;
	
	private OnlineAccount onlineAccount;
	
	/** Instantiates victim online account item. */
	public OffenderContactOnlineAccountItem() {
		// Default instantiation
	}
	
	/**
	 * Sets fields.
	 * 
	 * @param fields fields
	 */
	public void setFields(final OnlineAccountFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns fields.
	 * 
	 * @return fields
	 */
	public OnlineAccountFields getFields() {
		return this.fields;
	}
	
	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final OffenderContactOnlineAccountOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public OffenderContactOnlineAccountOperation getOperation() {
		return this.operation;
	}
	
	/**
	 * Sets online account.
	 * 
	 * @param onlineAccount online account
	 */
	public void setOnlineAccount(final OnlineAccount onlineAccount) {
		this.onlineAccount = onlineAccount;
	}
	
	/**
	 * Returns online account.
	 * 
	 * @return online account
	 */
	public OnlineAccount getOnlineAccount() {
		return this.onlineAccount;
	}
}