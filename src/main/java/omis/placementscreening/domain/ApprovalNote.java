package omis.placementscreening.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/** Approval note.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public interface ApprovalNote extends Creatable, Updatable {
	/** Gets id.
	 * @return id id. */
	Long getId();
	
	/** Gets approval category.
	 * @return approvalCategory approval category. */
	ApprovalCategory getApprovalCategory();

	/** Gets referral screening decision.
	 * @return referralScreeningDecision referral screening decision. */
	ReferralScreeningDecision getReferralScreeningDecision();
	
	/** Gets notes.
	 * @return notes notes. */
	String getNotes();
	
	/** Sets id. 
	 * @param id id. */
	void setId(Long id);
	
	/** Sets approval category.
	 * @param approvalCategory approval category. */
	void setApprovalCategory(ApprovalCategory approvalCategory);
	
	/** Sets referral screening decision.
	 * @param referralScreeningDecision referral screening decision. */
	void setReferralScreeningDecision(
			ReferralScreeningDecision referralScreeningDecision);
	
	/** Sets notes.
	 * @param notes notes. */
	void setNotes(String notes);
	
	 /** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
