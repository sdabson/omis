package omis.custody.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.custody.domain.CustodyChangeReason;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyReview;
import omis.offender.domain.Offender;

/**
 * Custody Review Implementation.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public class CustodyReviewImpl implements CustodyReview {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private CustodyLevel custodyLevel;
	
	private CustodyChangeReason changeReason;

	private Date actionDate;
	
	private Date nextReviewDate;
	
	/**Instantiates a default Custody Review.*/
	public CustodyReviewImpl() {
		//empty constructor
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
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CustodyLevel getCustodyLevel() {
		return this.custodyLevel;
	}

	/** {@inheritDoc} */
	@Override
	public void setCustodyLevel(final CustodyLevel custodyLevel) {
		this.custodyLevel = custodyLevel;
	}
	
	/** {@inheritDoc} */
	@Override
	public CustodyChangeReason getChangeReason() {
		return this.changeReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setChangeReason(final CustodyChangeReason changeReason) {
		this.changeReason = changeReason;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getActionDate() {
		return this.actionDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setActionDate(final Date actionDate) {
		this.actionDate = actionDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getNextReviewDate() {
		return this.nextReviewDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setNextReviewDate(final Date nextReviewDate) {
		this.nextReviewDate = nextReviewDate;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof CustodyReview)) {
			return false;
		}
		
		CustodyReview that = (CustodyReview) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		
		if (this.getCustodyLevel() == null) {
			throw new IllegalStateException("Custody Level required");
		}
		if (!this.getCustodyLevel().equals(that.getCustodyLevel())) {
			return false;
		}
		
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Change reason required");
		}
		if (!this.getChangeReason().equals(that.getChangeReason())) {
			return false;
		}
		
		if (this.getActionDate() == null) {
			throw new IllegalStateException("Action date required");
		}
		if (!this.getActionDate().equals(that.getActionDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getCustodyLevel() == null) {
			throw new IllegalStateException("Custody Level required");
		}
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Change reason required");
		}
		if (this.getActionDate() == null) {
			throw new IllegalStateException("Action date required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getCustodyLevel().hashCode();
		hashCode = 29 * hashCode + this.getChangeReason().hashCode();
		hashCode = 29 * hashCode + this.getActionDate().hashCode();
		
		return hashCode;	
	}
}