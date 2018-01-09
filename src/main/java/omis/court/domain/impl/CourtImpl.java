package omis.court.domain.impl;

import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.location.domain.Location;

/**
 * Implementation of court.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (Oct 30, 2014)
 * @since OMIS 3.0
 */
public class CourtImpl
		implements Court {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;

	private Location location;

	private CourtCategory category;
	
	/** Instantiates a default implementation of court. */
	public CourtImpl() {
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
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(final Location location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final CourtCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof Court)) {
			return false;
		}
		
		Court that = (Court) o;
		
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