package omis.offenderrelationship.web.form;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumber;
import omis.contact.web.form.TelephoneNumberFields;
import omis.contact.web.form.TelephoneNumberItemOperation;

/**
 * Telephone number item.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @author Yidong Li
 * @version 0.1.1 (Jan 05, 2016)
 * @since OMIS 3.0
 */
public class TelephoneNumberItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;	
	
	private TelephoneNumberItemOperation operation;
	
	private TelephoneNumberFields telephoneNumberFields;
	
	private TelephoneNumber telephoneNumber;
	
	/**
	 * Instantiates a default instance of contact telephone number item.
	 */
	public TelephoneNumberItem() {
		//Default constructor.
	}

	/**
	 * Return the id.
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Returns the telephone number item operation.
	 * 
	 * @return operation
	 */
	public TelephoneNumberItemOperation getOperation() {
		return this.operation;
	}
	
	/**
	 * Sets the telephone number operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final TelephoneNumberItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns the telephone number fields.
	 * 
	 * @return the telephone number fields
	 */
	public TelephoneNumberFields getTelephoneNumberFields() {
		return this.telephoneNumberFields;
	}

	/**
	 * Sets the telephone number fields.
	 * 
	 * @param telephoneNumberFields telephone number fields
	 */
	public void setTelephoneNumberFields(final TelephoneNumberFields 
			telephoneNumberFields) {
		this.telephoneNumberFields = telephoneNumberFields;
	}	
	
	/**
	 * Returns the telephone number.
	 * 
	 * @return the telephone number
	 */
	public TelephoneNumber getTelephoneNumber() {
		return this.telephoneNumber;
	}

	/**
	 * Sets the telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 */
	public void setTelephoneNumber(final TelephoneNumber 
			telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}	
}