package omis.grievance.domain.impl;

import omis.grievance.domain.GrievanceUnit;

/**
 * Implementation of grievance unit.
 *
 * @author Yidong Li
 * @version 0.0.1 (May 18, 2015)
 * @since OMIS 3.0
 */
public class GrievanceUnitImpl implements GrievanceUnit {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Short sortOrder;
	private Boolean valid;

	/** Constructor. */
	public GrievanceUnitImpl() {
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
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GrievanceUnit)) {
			return false;
		}
		GrievanceUnit that = (GrievanceUnit) obj;
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getSortOrder()==null) {
			throw new IllegalStateException("Sort order required");
		}	
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		
		if (this.getName().equals(that.getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getSortOrder()==null) {
			throw new IllegalStateException("Sort order required");
		}	
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		hashCode += 29 * this.getName().hashCode();
		hashCode += 29 * this.getSortOrder().hashCode();
		hashCode += 29 * this.getValid().hashCode();
		return hashCode;
	}
}