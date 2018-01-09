package omis.paroleeligibility.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.Updatable;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.paroleeligibility.domain.component.ParoleEligibilityStatus;

/**
 * Parole Eligibility
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public interface ParoleEligibility extends Creatable, Updatable {
	
	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender
	 * 
	 * @param offender
	 */
	void setOffender(Offender offender);
	
	/**
	 * Returns the hearing eligibility date
	 * 
	 * @return hearingEligibilityDate
	 */
	Date getHearingEligibilityDate();
	
	/**
	 * Sets the hearing eligibility date
	 * 
	 * @param hearingEligibilityDate hearing eligibility date
	 */
	void setHearingEligibilityDate(
			Date hearingEligibilityDate);
	
	/**
	 * Returns the hearing eligibility date.
	 * 
	 * @return reviewDate
	 */
	Date getReviewDate();
	
	/**
	 * Sets the hearing eligibility date.
	 * 
	 * @param reviewDate
	 */
	void setReviewDate(Date reviewDate);
	
	/**
	 * Returns the parole eligibility status.
	 * 
	 * @return paroleElibilityStatus
	 */
	ParoleEligibilityStatus getStatus();
	
	/**
	 * Sets the parole eligibility status.
	 * 
	 * @param paroleEligibilityStatus
	 */
	void setStatus(ParoleEligibilityStatus status);
	
	/**
	 * Returns the appearance category.
	 * 
	 * @return appearanceCateogry
	 */
	AppearanceCategory getAppearanceCategory();
	
	/**
	 * Sets the appearance category.
	 * 
	 * @param appearanceCategory
	 */
	void setAppearanceCategory(AppearanceCategory appearanceCategory);
	
	/**
	 * Returns creationSignature.
	 * 
	 * @return creationSignature
	 */
	CreationSignature getCreationSignature();
	
	/**
	 * Sets creationSignature.
	 * 
	 * @return creationSignature
	 */
	void setCreationSignature(CreationSignature creationSignature);
	
	/**
	 * Returns updateSignature.
	 * 
	 * @return updateSignature
	 */
	UpdateSignature getUpdateSignature();
	
	/**
	 * Sets updateSignature.
	 * 
	 * @return updateSignature
	 */
	void setUpdateSignature(UpdateSignature updateSignature);
		
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();

}
