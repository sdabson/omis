/**
 * 
 */
package omis.facility.domain.impl;

import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

/**
 * Level implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public class LevelImpl implements Level {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Facility facility;
	
	private Complex complex;
	
	private Unit unit;
	
	private Section section;
	
	private String abbreviation;
	
	private Boolean active;
	
	/**Instantiates a default level implementation. */
	public LevelImpl() {
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
	public Unit getUnit() {
		return this.unit;
	}

	/** {@inheritDoc} */
	@Override
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

	/** {@inheritDoc} */
	@Override
	public Section getSection() {
		return this.section;
	}

	/** {@inheritDoc} */
	@Override
	public void setSection(final Section section) {
		this.section = section;
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

		if (!(o instanceof Level)) {
			return false;
		}
		
		Level that = (Level) o;
		
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
		if (this.getComplex() != null) {
			if (!this.getComplex().equals(that.getComplex())) {
				return false;
			}
		} else if (that.getComplex() != null) {
			return false;
		}
		if (this.getUnit() != null) {
			if (!this.getUnit().equals(that.getUnit())) {
				return false;
			}
		} else if (that.getUnit() != null) {
			return false;
		}
		if (this.getSection() != null) {
			if (!this.getSection().equals(that.getSection())) {
				return false;
			}
		} else if (that.getSection() != null) {
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
		if (this.getComplex() != null) {
			hashCode = 29 * hashCode + this.getComplex().hashCode();
		}
		if (this.getUnit() != null) {
			hashCode = 29 * hashCode + this.getUnit().hashCode();
		}
		if (this.getSection() != null) {
			hashCode = 29 * hashCode + this.getSection().hashCode();
		}
		return hashCode;
	}
}
