package omis.workassignment.domain.impl;

import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentGroup;

/**
 * Implementation of work assignment category.
 * @author Yidong Li
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0 
 */
public class WorkAssignmentCategoryImpl implements WorkAssignmentCategory {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	private WorkAssignmentGroup group;
	
	/** Constructor. */
	public WorkAssignmentCategoryImpl() {
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
	public WorkAssignmentGroup getGroup() {
		return this.group;
	}

	/** {@inheritDoc} */
	@Override
	public void setGroup(final WorkAssignmentGroup group) {
		this.group = group;
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
		
		WorkAssignmentCategory that = (WorkAssignmentCategory) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required.");
		}
		if (this.getName().equals(that.getName())
			&&this.getValid().equals(that.getValid())){
			if((this.getGroup()==null&&that.getGroup()==null)
				||(this.getGroup().equals(that.getGroup()))){
				return true;
			}
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