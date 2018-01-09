package omis.health.domain.impl;

import omis.health.domain.LabWork;
import omis.health.domain.LabWorkerAssignment;
import omis.health.domain.LabWorkerAssociation;

/**
 * Lab Worker Associatino Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public class LabWorkerAssociationImpl implements LabWorkerAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private LabWorkerAssignment assignment;
	
	private LabWork labWork;
	
	private Boolean primary;
	
	/**
	 * Instantiates a default implementation of lab worker association.
	 */
	public LabWorkerAssociationImpl() {
		//Default constructor.
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
	public LabWorkerAssignment getAssignment() {
		return this.assignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setAssignment(
			final LabWorkerAssignment assignment) {
		this.assignment = assignment;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork getLabWork() {
		return this.labWork;
	}

	/** {@inheritDoc} */
	@Override
	public void setLabWork(final LabWork labWork) {
		this.labWork = labWork;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getPrimary() {
		return this.primary;
	}

	/** {@inheritDoc} */
	@Override
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof LabWorkerAssociation)) {
			return false;
		}
		
		LabWorkerAssociation that = (LabWorkerAssociation) o;
		
		if (this.getAssignment() == null) {
			throw new IllegalStateException("Lab Worker Assignment required.");
		}
		if (!this.getAssignment().equals(
				that.getAssignment())) {
			return false;
		}
		if (this.getLabWork() == null) {
			throw new IllegalStateException("Lab Work required.");
		}
		if (!this.getLabWork().equals(that.getLabWork())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAssignment() == null) {
			throw new IllegalStateException("Lab Worker Assignment required.");
		}
		if (this.getLabWork() == null) {
			throw new IllegalStateException("Lab Work required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getAssignment().hashCode();
		hashCode = 29 * hashCode + this.getLabWork().hashCode();
		
		return hashCode;
	}
}