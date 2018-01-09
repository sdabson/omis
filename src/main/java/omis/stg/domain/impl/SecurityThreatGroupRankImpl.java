package omis.stg.domain.impl;

import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupRank;

/**
 * Implementation of security threat group rank.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupRankImpl
		implements SecurityThreatGroupRank {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private SecurityThreatGroup securityThreatGroup;

	/** Instantiates an implementation of security threat group rank. */
	public SecurityThreatGroupRankImpl() {
		// Default instantiation
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
	public void setGroup(final SecurityThreatGroup 
			secuirtyThreatGroup) {
		this.securityThreatGroup = secuirtyThreatGroup;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroup getGroup() {
		return this.securityThreatGroup;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SecurityThreatGroupRank)) {
			return false;
		}
		SecurityThreatGroupRank that = (SecurityThreatGroupRank) obj;
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