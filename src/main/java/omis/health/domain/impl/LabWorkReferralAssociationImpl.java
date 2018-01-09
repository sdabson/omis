package omis.health.domain.impl;

import omis.health.domain.LabWork;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralAssociation;

/**
 * Implementation for lab work referral association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 21, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralAssociationImpl 
	implements LabWorkReferralAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
		
	private LabWorkReferral referral;
	
	private LabWork labWork;
	
	/**
	 * Instantiates a default instance of lab work referral association.
	 */
	public LabWorkReferralAssociationImpl() {
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
	public LabWorkReferral getReferral() {
		return this.referral;
	}

	/** {@inheritDoc} */
	@Override
	public void setReferral(final LabWorkReferral referral) {
		this.referral = referral;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof LabWorkReferralAssociation)) {
			return false;
		}
		
		LabWorkReferralAssociation that = (LabWorkReferralAssociation) o;
		
		if (this.getReferral() == null) {
			throw new IllegalStateException("Referral required.");
		}
		if (!this.getReferral().equals(that.getReferral())) {
			return false;
		}
		if (this.getLabWork() == null) {
			throw new IllegalStateException("Lab work required.");
		}
		if (!this.getLabWork().equals(that.getLabWork())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getReferral() == null) {
			throw new IllegalStateException("Referral required.");
		}
		if (this.getLabWork() == null) {
			throw new IllegalStateException("Lab work required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getReferral().hashCode();
		hashCode = 29 * hashCode + this.getLabWork().hashCode();
		
		return hashCode;
	}
}