package omis.separationneed.domain.impl;

import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedReason;
import omis.separationneed.domain.SeparationNeedReasonAssociation;

/**
 * Separation need reason association implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedReasonAssociationImpl 
	implements SeparationNeedReasonAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private SeparationNeed separationNeed;
	
	private SeparationNeedReason reason;
	
	/**
	 * Instantiates a default instance of separation need reason association.
	 */
	public SeparationNeedReasonAssociationImpl() {
		//Default constructor
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
	public SeparationNeed getSeparationNeed() {
		return this.separationNeed;
	}

	/** {@inheritDoc} */
	@Override
	public void setSeparationNeed(final SeparationNeed separationNeed) {
		this.separationNeed = separationNeed;
	}
	
	/** {@inheritDoc} */
	@Override
	public SeparationNeedReason getReason() {
		return this.reason;
	}

	/** {@inheritDoc} */
	@Override
	public void setReason(final SeparationNeedReason reason) {
		this.reason = reason;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof SeparationNeedReasonAssociation)) {
			return false;
		}
		
		SeparationNeedReasonAssociation that = (SeparationNeedReasonAssociation) o;
		
		if (this.getSeparationNeed() == null) {
			throw new IllegalStateException("Separation need required");
		}
		if (!this.getSeparationNeed().equals(that.getSeparationNeed())) {
			return false;
		}
		if (this.getReason() == null) {
			throw new IllegalStateException("Reason required");
		}
		if (!this.getReason().equals(that.getReason())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getSeparationNeed() == null) {
			throw new IllegalStateException("Separation need required.");
		}
		if (this.getReason() == null) {
			throw new IllegalStateException("Reason required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getSeparationNeed().hashCode();
		hashCode = 29 * hashCode + this.getReason().hashCode();
		
		return hashCode;
	}
}