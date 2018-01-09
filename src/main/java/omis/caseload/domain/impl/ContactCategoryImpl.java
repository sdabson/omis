package omis.caseload.domain.impl;

import java.io.Serializable;

import omis.audit.domain.CreationSignature;
import omis.caseload.domain.ContactCategory;

/**
 * CaseloadContact category implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2016)
 * @since OMIS 3.0
 */
public class ContactCategoryImpl implements ContactCategory, Serializable {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private Long id;
	
	private String name;

	/** Instantiates an implementation of ContactCategoryImpl */
	public ContactCategoryImpl() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ContactCategory)) {
			return false;
		}
		
		ContactCategory that = (ContactCategory) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
				
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}