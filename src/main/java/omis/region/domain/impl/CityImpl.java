package omis.region.domain.impl;

import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Implementation of city.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 3, 2013)
 * @since OMIS 3.0
 */
public class CityImpl
		implements City {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private Boolean valid;

	private State state;

	private Country country;
	
	/** Instantiates a default implementation of city. */
	public CityImpl() {
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
	public void setState(final State state) {
		this.state = state;
	}

	/** {@inheritDoc} */
	@Override
	public State getState() {
		return this.state;
	}

	/** {@inheritDoc} */
	@Override
	public void setCountry(final Country country) {
		this.country = country;
	}

	/** {@inheritDoc} */
	@Override
	public Country getCountry() {
		return this.country;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof City)) {
			return false;
		}
		City that = (City) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("City required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getCountry() == null) {
			throw new IllegalStateException("Country required");
		}
		if (!this.getCountry().equals(that.getCountry())) {
			return false;
		}
		if (this.getState() != null) {
			if (!this.getState().equals(that.getState())) {
				return false;
			}
		} else if (that.getState() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("City required");
		}
		if (this.getCountry() == null) {
			throw new IllegalStateException("Country required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getCountry().hashCode();
		if (this.getState() != null) {
			hashCode = 29 * hashCode + this.getState().hashCode();
		}
		return hashCode;
	}
}