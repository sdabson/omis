package omis.family.web.form;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.web.form.TelephoneNumberFields;

/**
 * Family association telephone number.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.1 (Dec 11, 2017)
 * @since OMIS 3.0
 */
public class FamilyAssociationTelephoneNumberItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long phoneNumber;
	private Integer extension;
	private TelephoneNumberCategory phoneType;
	private Boolean primary;
	private FamilyAssociationTelephoneNumberItemOperation operation;
	private TelephoneNumberFields telephoneNumberFields;
	private Boolean active;
	private TelephoneNumber telephoneNumber;
	
	/**
	 * Instantiates a default instance of family association notes.
	 */
	public FamilyAssociationTelephoneNumberItem() {
		//Default constructor.
	}

	/**
	 * Gets the ID.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the ID.
	 *
	 * @param id id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phoneNumber
	 */
	public Long getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber phone number
	 */
	public void setPhoneNumber(final Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public Integer getExtension() {
		return this.extension;
	}

	/**
	 * Sets the extension.
	 *
	 * @param extension extension
	 */
	public void setExtension(final Integer extension) {
		this.extension = extension;
	}

	/**
	 * Gets the phone type.
	 *
	 * @return the phoneType
	 */
	public TelephoneNumberCategory getPhoneType() {
		return this.phoneType;
	}

	/**
	 * Sets the phone type.
	 *
	 * @param phoneType phone type
	 */
	public void setPhoneType(final TelephoneNumberCategory phoneType) {
		this.phoneType = phoneType;
	}

	/**
	 * Gets the primary.
	 *
	 * @return the primary
	 */
	public Boolean getPrimary() {
		return this.primary;
	}

	/**
	 * Sets the primary.
	 *
	 * @param primary primary
	 */
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}

	/**
	 * Gets the family association telephone number item operation.
	 *
	 * @return the operation
	 */
	public FamilyAssociationTelephoneNumberItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the family association telephone number item operation.
	 *
	 * @param operation operation
	 */
	public void setOperation(
			final FamilyAssociationTelephoneNumberItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Gets the telephone number fields.
	 *
	 * @return the telephoneNumberFields
	 */
	public TelephoneNumberFields getTelephoneNumberFields() {
		return this.telephoneNumberFields;
	}

	/**
	 * Sets the telephone number fields.
	 *
	 * @param telephoneNumberFields telephone number fields
	 */
	public void setTelephoneNumberFields(
			final TelephoneNumberFields telephoneNumberFields) {
		this.telephoneNumberFields = telephoneNumberFields;
	}

	/**
	 * Gets active.
	 *
	 * @return the active
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Sets active.
	 *
	 * @param active active
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}

	/**
	 * Returns the telephone number.
	 * 
	 * @return telephone number
	 */
	public TelephoneNumber getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * Sets the telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 */
	public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
}