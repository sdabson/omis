package omis.contact.domain.impl;

import omis.address.domain.Address;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.contact.domain.Contact;
import omis.contact.domain.component.PoBox;
import omis.person.domain.Person;

/** Implementation of contact.
 * @author Yidong Li
 * @version 0.1.0 (April 1, 2015)
 * @since OMIS 3.0 */
public class ContactImpl implements Contact {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Address mailingAddress;
	private Person person;
	private PoBox poBox;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;

	/** Constructor. */
	public ContactImpl() {
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
	public Address getMailingAddress() {
		return this.mailingAddress;
	}

	/** {@inheritDoc} */
	@Override
	public void setMailingAddress(Address mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
	/** {@inheritDoc} */
	@Override
	public Person getPerson() {
		return this.person;
	}

	/** {@inheritDoc} */
	@Override
	public void setPerson(Person person) {
		this.person = person;
	}
	
	/** {@inheritDoc} */
	@Override
	public PoBox getPoBox() {
		return this.poBox;
	}

	/** {@inheritDoc} */
	@Override
	public void setPoBox(PoBox poBox) {
		this.poBox = poBox;
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
		if (!(obj instanceof Contact)) {
			return false;
		}
		
		Contact that = (Contact) obj;
		if(this.getPerson() == null){
			throw new IllegalStateException("Person required");
		}
		if(this.getPerson().equals(that.getPerson())){
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		
		if(this.getPerson()==null){
			throw new IllegalStateException("Person required");
		}
		hashCode += 29 * this.getPerson().hashCode();
		return hashCode;
	}
}
