package omis.paroleboardcondition.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementImpl implements ParoleBoardAgreement {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Agreement agreement;
	
	private ParoleBoardAgreementCategory category;
	
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
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
	
	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
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
	public Agreement getAgreement() {
		return this.agreement;
	}

	/**{@inheritDoc} */
	@Override
	public void setAgreement(final Agreement agreement) {
		this.agreement = agreement;
	}

	/**{@inheritDoc} */
	@Override
	public ParoleBoardAgreementCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final ParoleBoardAgreementCategory category) {
		this.category = category;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleBoardAgreement)) {
			return false;
		}
		
		ParoleBoardAgreement that = (ParoleBoardAgreement) obj;
		
		if (this.getAgreement() == null) {
			throw new IllegalStateException("Agreement required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required.");
		}
		
		if (!this.getAgreement().equals(that.getAgreement())) {
			return false;
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAgreement() == null) {
			throw new IllegalStateException("Agreement required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getAgreement().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}
}
