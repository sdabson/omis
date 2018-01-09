package omis.paroleeligibility.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.paroleeligibility.domain.AppearanceCategory;
import omis.paroleeligibility.domain.ParoleEligibility;
import omis.paroleeligibility.domain.component.ParoleEligibilityStatus;

/**
 * Parole eligibility implementation
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 7, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityImpl implements ParoleEligibility {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;

	private Date hearingEligibilityDate;
	
	private Date reviewDate;
	
	private ParoleEligibilityStatus status;
	
	private AppearanceCategory appearanceCategory;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates an implementation of parole eligibility.
	 */
	public ParoleEligibilityImpl() {
		// Default constructor
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id; 
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Date getHearingEligibilityDate() {
		return this.hearingEligibilityDate; 
	}

	/** {@inheritDoc} */
	@Override
	public void setHearingEligibilityDate(Date hearingEligibilityDate) {
		this.hearingEligibilityDate = hearingEligibilityDate; 
	}

	/** {@inheritDoc} */
	@Override
	public Date getReviewDate() {
		return this.reviewDate; 
	}

	/** {@inheritDoc} */
	@Override
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate; 
	}

	/** {@inheritDoc} */
	@Override
	public ParoleEligibilityStatus getStatus() {
		return this.status; 
	}

	/** {@inheritDoc} */
	@Override
	public void setStatus(ParoleEligibilityStatus status) {
		this.status = status; 
	}

	/** {@inheritDoc} */
	@Override
	public AppearanceCategory getAppearanceCategory() {
		return this.appearanceCategory; 
	}

	/** {@inheritDoc} */
	@Override
	public void setAppearanceCategory(AppearanceCategory appearanceCategory) {
		this.appearanceCategory = appearanceCategory; 
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature; 
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature; 
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature; 
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature; 
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleEligibility)) {
			return false;
		}
		ParoleEligibility that = (ParoleEligibility) obj;
		if (this.getId() == null) {
			throw new IllegalStateException("Id required");
		}
		if (!this.getId().equals(that.getId())) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getHearingEligibilityDate() == null) {
			throw new IllegalStateException("Hearing eligibility date required");
		}
		if (!this.getHearingEligibilityDate().equals(
				that.getHearingEligibilityDate())) {
			return false;
		}
		if (this.getReviewDate() == null) {
			throw new IllegalStateException("Review date required");
		}
		if (!this.getReviewDate().equals(that.getReviewDate())) {
			return false;
		}
		if (this.getStatus() == null) {
			throw new IllegalStateException(
				"Parole eligibility status required");
		}
		if (!this.getStatus().equals(that.getStatus())) {
			return false;
		}
		if (this.getAppearanceCategory() == null) {
			throw new IllegalStateException("Appearance category required");
		}
		if (!this.getAppearanceCategory().equals(that.getAppearanceCategory())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getId() == null) {
			throw new IllegalStateException("Id required.");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getHearingEligibilityDate() == null) {
			throw new IllegalStateException(
				"Hearing eligibility date required.");
		}
		if (this.getReviewDate() == null) {
			throw new IllegalStateException("Review date required.");
		}
		if (this.getStatus() == null) {
			throw new IllegalStateException(
				"Parole eligibility status required.");
		}
		if (this.getAppearanceCategory() == null) {
			throw new IllegalStateException("Appearance category required.");
		}
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getId().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getHearingEligibilityDate().hashCode();
		hashCode = 29 * hashCode + this.getReviewDate().hashCode();
		hashCode = 29 * hashCode + this.getStatus().hashCode();
		hashCode = 29 * hashCode + this.getAppearanceCategory().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format(
				"Id: %s, Offender: %s, Hearing Eligibility Date: %s, "
					+ "Review Date: %s, Parole Eligibility Status: %s, "
					+ "Appearance Category: %s",
				this.getId(),
				this.getOffender(),
				this.getHearingEligibilityDate(),
				this.getReviewDate(),
				this.getStatus(),
				this.getAppearanceCategory());
	}

}
