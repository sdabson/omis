package omis.workassignment.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentNote;

/**
 * Implementation of work assignment note.
 * @author Yidong Li
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0 
 */
public class WorkAssignmentNoteImpl implements WorkAssignmentNote {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date date;
	private String value;
	private WorkAssignment assignment;
	private UpdateSignature updateSignature;
	private CreationSignature creationSignature;
	
	/** Constructor. */
	public WorkAssignmentNoteImpl() {
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
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignment getAssignment() {
		return this.assignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setAssignment(final WorkAssignment assignment) {
		this.assignment = assignment;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkAssignmentNote)) {
			return false;
		}
		
		WorkAssignmentNote that = (WorkAssignmentNote) obj;
		
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		if (this.getAssignment() == null) {
			throw new IllegalStateException("Work assignment required.");
		}
		if (this.getAssignment().equals(that.getAssignment())
			&&this.getDate().equals(that.getDate())
			&&this.getValue().equals(that.getValue())) {
			return true;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required.");
		}
		if (this.getAssignment() == null) {
			throw new IllegalStateException("Work assignment required.");
		}
		
		int hashCode = 14;
		
		hashCode += 29 * this.getValue().hashCode();
		hashCode += 29 * this.getDate().hashCode();
		hashCode += 29 * this.getAssignment().hashCode();
		
		return hashCode;
	}

	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
}