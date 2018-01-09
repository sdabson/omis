package omis.locationterm.domain.impl;

import omis.locationterm.domain.AllowedLocationChangeReasonRule;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Implementation of allowed location change reason rule.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class AllowedLocationChangeReasonRuleImpl
		implements AllowedLocationChangeReasonRule {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private LocationTermChangeAction changeAction;
	
	private LocationReason reason;

	/** Instantiates implementation of allowed location change reason rule. */
	public AllowedLocationChangeReasonRuleImpl() {
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
	public void setChangeAction(
			final LocationTermChangeAction changeAction) {
		this.changeAction = changeAction;
	}

	/** {@inheritDoc} */
	@Override
	public LocationTermChangeAction getChangeAction() {
		return this.changeAction;
	}

	/** {@inheritDoc} */
	@Override
	public void setReason(final LocationReason reason) {
		this.reason = reason;
	}

	/** {@inheritDoc} */
	@Override
	public LocationReason getReason() {
		return this.reason;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllowedLocationChangeReasonRule)) {
			return false;
		}
		AllowedLocationChangeReasonRule that
			= (AllowedLocationChangeReasonRule) obj;
		if (this.getChangeAction() == null) {
			throw new IllegalStateException("Change action required");
		}
		if (!this.getChangeAction().equals(that.getChangeAction())) {
			return false;
		}
		if (this.getReason() == null) {
			throw new IllegalStateException("Reason required");
		}
		if (!this.getReason().equals(that.getReason())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getChangeAction() == null) {
			throw new IllegalStateException("Change action required");
		}
		if (this.getReason() == null) {
			throw new IllegalStateException("Reason required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getChangeAction().hashCode();
		hashCode = 29 * hashCode + this.getReason().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d - [changeAction: %s],[reason: %s]",
				this.getId(), this.getChangeAction(), this.getId());
	}
}