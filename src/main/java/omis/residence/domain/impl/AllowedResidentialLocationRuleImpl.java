package omis.residence.domain.impl;

import omis.location.domain.Location;
import omis.residence.domain.AllowedResidentialLocationRule;
import omis.residence.domain.ResidenceStatus;

/**
 * Implementation of allowed residential location rule.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 16, 2015)
 * @since  OMIS 3.0
 */
public class AllowedResidentialLocationRuleImpl 
		implements AllowedResidentialLocationRule {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ResidenceStatus status;
	
	private Location location;
	
	/** Instantiates an implementation of allowed residential location rule. */
	public AllowedResidentialLocationRuleImpl() {
		// Default instantiation
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
	public ResidenceStatus getStatus() {		
		return this.status;
	}

	/** {@inheritDoc} */
	@Override
	public void setStatus(final ResidenceStatus status) {
		this.status = status;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllowedResidentialLocationRule)) {
			return false;
		}
		AllowedResidentialLocationRule that 
			= (AllowedResidentialLocationRule) obj;
		if (this.getStatus() == null) {
			throw new IllegalStateException("Status required");
		}
		if (!this.getStatus().equals(that.getStatus())) {
			return false;
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if (!this.getLocation().equals(that.getLocation())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getStatus() == null) {
			throw new IllegalStateException("Status required");
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getStatus().hashCode();
		hashCode = 29 * hashCode + this.getLocation().hashCode();
		
		return hashCode;
	}
}
