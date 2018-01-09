package omis.contact.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.OnlineAccount;

/** Implementation of online account.
 * @author Yidong Li
 * @version 0.1.0 (April 1, 2015)
 * @since OMIS 3.0 */
public class OnlineAccountImpl implements OnlineAccount {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Contact contact;
	private OnlineAccountHost host;
	private String name;
	private Boolean active;
	private Boolean primary;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;

	/** Constructor. */
	public OnlineAccountImpl() {
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
	public OnlineAccountHost getHost() {
		return this.host;
	}

	/** {@inheritDoc} */
	@Override
	public void setHost(OnlineAccountHost host) {
		this.host = host;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
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
	
	/** {@inheritDoc} */
	@Override
	public Boolean getPrimary() {
		return this.primary;
	}

	/** {@inheritDoc} */
	@Override
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
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
		if (!(obj instanceof OnlineAccount)) {
			return false;
		}
		
		OnlineAccount that = (OnlineAccount) obj;
		
		if(this.getContact() == null){
			throw new IllegalStateException("Contact required");
		}
		if(this.getHost() == null){
			throw new IllegalStateException("Online Account Host required");
		}
		if(this.getName() == null){
			throw new IllegalStateException("Name required");
		}
		if(this.getActive() == null){
			throw new IllegalStateException("Active required");
		}
		if(this.getContact().equals(that.getContact())&&
			this.getHost().equals(that.getHost())&&
			this.getName().equals(that.getName())){
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
		if(this.getHost() == null){
			throw new IllegalStateException("Online Account Host required");
		}
		if(this.getName() == null){
			throw new IllegalStateException("Name required");
		}
		if(this.getActive() == null){
			throw new IllegalStateException("Active required");
		}	
		hashCode += 29 * this.getContact().hashCode();
		hashCode += 29 * this.getHost().hashCode();
		hashCode += 29 * this.getName().hashCode();
		return hashCode;
	}
}
