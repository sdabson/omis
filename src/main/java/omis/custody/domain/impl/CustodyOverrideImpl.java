package omis.custody.domain.impl;

import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.CreationSignature;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;

/**
 * Custody Override Implementation.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 18 2013)
 * @since OMIS 3.0
 */
public class CustodyOverrideImpl implements CustodyOverride {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CustodyReview custodyReview;
	
	private CustodyLevel custodyLevel;
	
	private CreationSignature creationSignature;
	
	private AuthorizationSignature authorizationSignature;
	
	/** Instantiates a default instance of custody override implementation. */
	public CustodyOverrideImpl() {
		//empty constructor
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
	public CustodyReview getCustodyReview() {
		return this.custodyReview;
	}

	/** {@inheritDoc} */
	@Override
	public void setCustodyReview(final CustodyReview custodyReview) {
		this.custodyReview = custodyReview;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyLevel getCustodyLevel() {
		return this.custodyLevel;
	}

	/** {@inheritDoc} */
	@Override
	public void setCustodyLevel(final CustodyLevel custodyLevel) {
		this.custodyLevel = custodyLevel;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public AuthorizationSignature getAuthorizationSignature() {
		return this.authorizationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setAuthorizationSignature(
			final AuthorizationSignature authorizationSignature) {
		this.authorizationSignature = authorizationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof CustodyOverride)) {
			return false;
		}
		
		CustodyOverride that = (CustodyOverride) o;
		
		if (this.getCustodyReview() == null) {
			throw new IllegalStateException("Custody Review required");
		}
		if (!this.getCustodyReview().equals(that.getCustodyReview())) {
			return false;
		}
		
		if (this.getCustodyLevel() == null) {
			throw new IllegalStateException("Custody Level required");
		}
		if (!this.getCustodyLevel().equals(that.getCustodyLevel())) {
			return false;
		}
		
		if (this.getCreationSignature() == null) {
			throw new IllegalStateException("Creation Signature required");
		}
		if (!this.getCreationSignature().equals(that.getCreationSignature())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		
		if (this.getCustodyReview() == null) {
			throw new IllegalStateException("Custody Review required");
		}
		
		if (this.getCustodyLevel() == null) {
			throw new IllegalStateException("Custody Level required");
		}
		
		if (this.getCreationSignature() == null) {
			throw new IllegalStateException("Creation Signature required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getCustodyReview().hashCode();
		hashCode = 29 * hashCode + this.getCustodyLevel().hashCode();
		hashCode = 29 * hashCode + this.getCreationSignature().hashCode();
		
		return hashCode;
	}
}