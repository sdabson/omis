package omis.contact.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.contact.domain.Contact;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;

/** Implementation of online account host.
 * @author Yidong Li
 * @version 0.1.0 (April 1, 2015)
 * @since OMIS 3.0 */
public class TelephoneNumberImpl implements TelephoneNumber {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Contact contact;
	private Long value;
	private Integer extension;
	private Boolean primary;
	private Boolean active;
	private TelephoneNumberCategory category;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;

	/** Constructor. */
	public TelephoneNumberImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public TelephoneNumberCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(TelephoneNumberCategory 
			category) {
			this.category = category;
		}
	
	/** {@inheritDoc} */
	@Override
	public Contact getContact() {
		return this.contact;
	}

	/** {@inheritDoc} */
	@Override
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(Long value) {
		this.value = value;
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer getExtension() {
		return this.extension;
	}

	/** {@inheritDoc} */
	@Override
	public void setExtension(Integer extension) {
		this.extension = extension;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getPrimary() {
		return this.primary;
	}

	/** {@inheritDoc} */
	@Override
	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getActive() {
		return this.active;
	}

	/** {@inheritDoc} */
	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	/**{@inheritDoc}*/
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setCreationSignature(
		final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TelephoneNumber)) {
			return false;
		}
		
		TelephoneNumber that = (TelephoneNumber) obj;
		
		if(this.getContact() == null){
			throw new IllegalStateException("Contact required");
		}
		if(this.getValue() == null){
			throw new IllegalStateException("Value required");
		}
		if(this.getPrimary() == null){
			throw new IllegalStateException("Primary required");
		}
		if(this.getActive() == null){
			throw new IllegalStateException("Active required");
		}
		if(this.getContact().equals(that.getContact())&&
			this.getValue().equals(that.getValue())){
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		
		if(this.getContact() == null){
			throw new IllegalStateException("Contact required");
		}
		if(this.getValue() == null){
			throw new IllegalStateException("Value required");
		}
		if(this.getPrimary() == null){
			throw new IllegalStateException("Primary required");
		}
		if(this.getActive() == null){
			throw new IllegalStateException("Active required");
		}
		hashCode += 29 * this.getContact().hashCode();
		hashCode += 29 * this.getValue().hashCode();
		hashCode += 29 * this.getPrimary().hashCode();
		hashCode += 29 * this.getActive().hashCode();
		return hashCode;
	}
}
