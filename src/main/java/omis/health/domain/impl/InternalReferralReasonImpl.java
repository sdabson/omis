package omis.health.domain.impl;

import omis.health.domain.InternalReferralReason;

/**
 * Implementation of reason for internal referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 12, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralReasonImpl
		implements InternalReferralReason {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Short sortOrder;
	
	private Boolean valid;
	
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
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof InternalReferralReason)) {
			return false;
		}
		InternalReferralReason that = (InternalReferralReason) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		hashCode = hashCode * 29 + this.getName().hashCode();
		return hashCode;
	}
}