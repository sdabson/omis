package omis.locationterm.domain.impl;

import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Implementation of location term change action.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 4, 2015)
 * @since OMIS 3.0
 */
public class LocationTermChangeActionImpl
		implements LocationTermChangeAction {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	/** Instantiates implementation of location term change action. */
	public LocationTermChangeActionImpl() {
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof LocationTermChangeAction)) {
			return false;
		}
		LocationTermChangeAction that = (LocationTermChangeAction) obj;
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