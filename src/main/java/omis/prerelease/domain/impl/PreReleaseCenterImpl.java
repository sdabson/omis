package omis.prerelease.domain.impl;

import omis.location.domain.Location;
import omis.prerelease.domain.PreReleaseCenter;

/**
 * Implementation of prerelease center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public class PreReleaseCenterImpl implements PreReleaseCenter {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Location location;
	
	private String name;

	private Long telephoneNumber;
	
	/** Instantiates a default implementation of prerelease center. */
	public PreReleaseCenterImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setLocation(Location location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return this.location;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setTelephoneNumber(Long telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/** {@inheritDoc} */
	@Override
	public Long getTelephoneNumber() {
		return this.telephoneNumber;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PreReleaseCenter)) {
			return false;
		}
		PreReleaseCenter that = (PreReleaseCenter) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
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
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getLocation().hashCode();
		return hashCode;
	}
}
