package omis.placementscreening.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/** Extension for screening.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public interface ScreeningWindowExtension extends Creatable, Updatable {
	/** Gets id.
	 * @return id id. */
	Long getId();
	
	/** Gets request date.
	 * @return requestDate request date. */
	Date getRequestDate();
	
	/** Gets referral screening.
	 * @return referralScreening referral screening. */
	ReferralScreening getReferralScreening();
	
	/** Gets [is] approved.
	 * @return approved approved. */
	Boolean getApproved();
	
	/** Gets decision date.
	 * @return decisionDate decision date. */
	Date getDecisionDate();
	
	/** Sets id.
	 * @param id id. */
	void setId(Long id);
	
	/** Sets request date.
	 * @param requestDate request date. */
	void setRequestDate(Date requestDate);
	
	/** Sets referral screening.
	 * @param referralScreening referral screening. */
	void setReferralScreening(ReferralScreening referralScreening);
	
	/** Sets [is] approved.
	 * @param approved approved. */
	void setApproved(Boolean approved);
	
	/** Sets decision date.
	 * @param decisionDate decision date. */
	void setDecisionDate(Date decisionDate);
	
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
