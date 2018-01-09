package omis.facility.domain.impl;

import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;

/**
 * Facility area implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 14, 2016)
 * @since OMIS 3.0
 */
public class FacilityAreaImpl implements FacilityArea {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Facility facility;
	
	private Complex complex;
	
	/* Constructors. */
	
	/**
	 * Instantiates a default instance of facility area.
	 */
	public FacilityAreaImpl() {
		//Default constructor.
	}

	/* Getters and setters. */
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
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
	public Facility getFacility() {
		return this.facility;
	}

	/** {@inheritDoc} */
	@Override
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/** {@inheritDoc} */
	@Override
	public Complex getComplex() {
		return this.complex;
	}

	/** {@inheritDoc} */
	@Override
	public void setComplex(final Complex complex) {
		this.complex = complex;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof FacilityArea)) {
			return false;
		}
		
		FacilityArea that = (FacilityArea) o;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required");
		}
		if (!this.getFacility().equals(that.getFacility())) {
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
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}