package omis.workassignment.domain.impl;

import omis.workassignment.domain.WorkAssignmentGroup;

/**
 * Implementation of work assignment group.
 * @author Yidong Li
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0 
 */
public class WorkAssignmentGroupImpl implements WorkAssignmentGroup {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	
	/** Constructor. */
	public WorkAssignmentGroupImpl() {
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

		if (!(obj instanceof WorkAssignmentGroup)) {
			return false;
		}
		
		WorkAssignmentGroup that = (WorkAssignmentGroup) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required.");
		}
		if (this.getName().equals(that.getName())) {
			return true;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required.");
		}
		
		int hashCode = 14;
		
		hashCode += 29 * this.getName().hashCode();
		hashCode += 29 * this.getValid().hashCode();
		
		return hashCode;
	}
}