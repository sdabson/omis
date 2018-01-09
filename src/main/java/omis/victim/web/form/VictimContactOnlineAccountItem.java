package omis.victim.web.form;

import java.io.Serializable;

import omis.contact.domain.OnlineAccount;
import omis.contact.web.form.OnlineAccountFields;

/**
 * Victim online account form item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 10, 2015)
 * @since OMIS 3.0
 */
public class VictimContactOnlineAccountItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private VictimContactOnlineAccountItemOperation operation;
	
	private OnlineAccount onlineAccount;
	
	private OnlineAccountFields fields;	
	
	/** Instantiates victim online account form item. */
	public VictimContactOnlineAccountItem() {
		// Default instantiation
	}

	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public VictimContactOnlineAccountItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(
			final VictimContactOnlineAccountItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns online account.
	 * 
	 * @return online account
	 */
	public OnlineAccount getOnlineAccount() {
		return this.onlineAccount;
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
}