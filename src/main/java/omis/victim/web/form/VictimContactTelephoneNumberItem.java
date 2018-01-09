package omis.victim.web.form;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumber;
import omis.contact.web.form.TelephoneNumberFields;

/**
 * Victim contact telephone number item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 28, 2015)
 * @since OMIS 3.0
 */
public class VictimContactTelephoneNumberItem
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private VictimContactTelephoneNumberItemOperation operation;
	
	private TelephoneNumber telephoneNumber;
	
	private TelephoneNumberFields fields;
	
	/** Instantiates victim contact telephone number item. */
	public VictimContactTelephoneNumberItem() {
		// Default instantiation
	}

	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public VictimContactTelephoneNumberItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(
			final VictimContactTelephoneNumberItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns telephone number.
	 * 
	 * @return telephone number
	 */
	public TelephoneNumber getTelephoneNumber() {
		return this.telephoneNumber;
	}
	
	/**
	 * Sets telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 */
	public void setTelephoneNumber(final TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	/**
	 * Sets fields.
	 * 
	 * @param fields fields
	 */
	public void setFields(final TelephoneNumberFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns fields.
	 * 
	 * @return fields
	 */
	public TelephoneNumberFields getFields() {
		return this.fields;
	}
}