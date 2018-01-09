package omis.paroleboardcondition.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement Category Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementCategoryImpl
	implements ParoleBoardAgreementCategory {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/**{@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleBoardAgreementCategory)) {
			return false;
		}
		
		ParoleBoardAgreementCategory that = (ParoleBoardAgreementCategory) obj;
		
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
