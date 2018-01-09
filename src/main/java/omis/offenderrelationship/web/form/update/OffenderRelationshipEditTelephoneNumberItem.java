package omis.offenderrelationship.web.form.update;

import java.io.Serializable;

import omis.contact.domain.TelephoneNumberCategory;

/**
 * Offender Relationship telephone number.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Feb 8, 2016)
 * @since OMIS 3.0
 */
public class OffenderRelationshipEditTelephoneNumberItem 
	implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long phoneNumber;
	private Integer extension;
	private TelephoneNumberCategory phoneType;
	private Boolean primary;
	private OffenderRelationshipTelephoneNumberItemOperation operation;
	
	/**
	 * Instantiates a default instance of offender relationship telephone number
	 * item.
	 */
	public OffenderRelationshipEditTelephoneNumberItem() {
		//Default constructor.
	}

	/**
	 * Get phone number.
	 * @return phoneNumber
	 */
	public Long getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set phone number.
	 * @param phoneNumber phone number
	 */
	public void setPhoneNumber(final Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get extension.
	 * @return extension
	 */
	public Integer getExtension() {
		return extension;
	}

	/**
	 * Set extension.
	 * @param extension extension
	 */
	public void setExtension(final Integer extension) {
		this.extension = extension;
	}

	/**
	 * Get phoneType.
	 * @return phoneType
	 */
	public TelephoneNumberCategory getPhoneType() {
		return phoneType;
	}

	/**
	 * Set phoneType.
	 * @param phoneType phoneType
	 */
	public void setPhoneType(final TelephoneNumberCategory phoneType) {
		this.phoneType = phoneType;
	}

	/**
	 * Get primary.
	 * @return primary
	 */
	public Boolean getPrimary() {
		return primary;
	}

	/**
	 * Set primary.
	 * @param primary primary
	 */
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}
	
	/**
	 * Get operation.
	 * @return operation
	 */
	public OffenderRelationshipTelephoneNumberItemOperation getOperation() {
		return operation;
	}

	/**
	 * Set operation.
	 * @param operation operation
	 */
	public void setOperation(
		final OffenderRelationshipTelephoneNumberItemOperation operation) {
		this.operation = operation;
	}
}