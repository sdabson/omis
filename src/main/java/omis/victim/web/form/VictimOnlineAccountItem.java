package omis.victim.web.form;

import java.io.Serializable;

import omis.contact.domain.OnlineAccount;
import omis.contact.web.form.OnlineAccountFields;

/**
 * Victim online account item.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class VictimOnlineAccountItem
		implements Serializable {

	private static final long serialVersionUID = 1;
	
	private OnlineAccountFields fields;
	
	private VictimOnlineAccountOperation operation;
	
	private OnlineAccount onlineAccount;
	
	/** Instantiates victim online account item. */
	public VictimOnlineAccountItem() {
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
	public void setOperation(final VictimOnlineAccountOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public VictimOnlineAccountOperation getOperation() {
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