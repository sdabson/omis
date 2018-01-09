package omis.offendercontact.web.form;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumber;
import omis.contact.web.form.TelephoneNumberFields;

/**
 * Offender contact telephone number item.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenderContactTelephoneNumberItem
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private TelephoneNumberFields fields;
	
	private OffenderContactTelephoneNumberOperation operation;
	
	private TelephoneNumber telephoneNumber;
	
	/** Instantiates victim telephone number item. */
	public OffenderContactTelephoneNumberItem() {
		// Default instantiation
	}
	
	/**
	 * Sets telephone number fields.
	 * 
	 * @param fields telephone number fields
	 */
	public void setFields(final TelephoneNumberFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns telephone number fields.
	 * 
	 * @return telephone number fields
	 */
	public TelephoneNumberFields getFields() {
		return this.fields;
	}
	
	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final OffenderContactTelephoneNumberOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public OffenderContactTelephoneNumberOperation getOperation() {
		return this.operation;
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
	 * Returns telephone number.
	 * 
	 * @return telephone number
	 */
	public TelephoneNumber getTelephoneNumber() {
		return this.telephoneNumber;
	}
}