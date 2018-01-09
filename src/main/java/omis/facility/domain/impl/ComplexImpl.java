/**
 * 
 */
package omis.facility.domain.impl;

import omis.facility.domain.Complex;
import omis.facility.domain.Facility;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public class ComplexImpl implements Complex {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Facility facility;
	
	private String abbreviation;
	
	private Boolean active;
	
	/** Instantiates a default complex implementation. */
	public ComplexImpl() {
		//Empty constructor
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
	public String getAbbreviation() {
		return this.abbreviation;
	}

	/** {@inheritDoc} */
	@Override
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getActive() {
		return this.active;
	}

	/** {@inheritDoc} */
	@Override
	public void setActive(final Boolean active) {
		this.active = active;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Complex)) {
			return false;
		}
		
		Complex that = (Complex) o;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
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
			throw new IllegalStateException("Name required");
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required");
		}
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getFacility().hashCode();
		
		return hashCode;
	}
}
