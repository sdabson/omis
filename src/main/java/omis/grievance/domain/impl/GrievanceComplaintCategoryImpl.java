package omis.grievance.domain.impl;

import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceSubject;

/**
 * Implementation of grievance complaint category.
 *
 * @author Yidong Li
 * @version 0.0.1 (May 19, 2015)
 * @since OMIS 3.0
 */
public class GrievanceComplaintCategoryImpl implements GrievanceComplaintCategory {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	private GrievanceSubject subject;

	/** Constructor. */
	public GrievanceComplaintCategoryImpl() {
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
	public GrievanceSubject getSubject() {
		return this.subject;
	}

	/** {@inheritDoc} */
	@Override
	public void setSubject(final GrievanceSubject subject) {
		this.subject = subject;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GrievanceComplaintCategory)) {
			return false;
		}
		GrievanceComplaintCategory that = (GrievanceComplaintCategory) obj;
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		if (this.getSubject()==null) {
			throw new IllegalStateException("Grievance subject required");
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
		if (this.getSubject()==null) {
			throw new IllegalStateException("Grievance subject required");
		}	
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		hashCode += 29 * this.getName().hashCode();
		hashCode += 29 * this.getSubject().hashCode();
		hashCode += 29 * this.getValid().hashCode();
		return hashCode;
	}
}