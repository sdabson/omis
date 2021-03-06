package omis.health.domain;

import java.io.Serializable;

/**
 * Lab work referral association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 21, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkReferralAssociation extends Serializable {

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
	 * Returns the lab work referral.
	 * 
	 * @return lab work referral
	 */
	LabWorkReferral getReferral();
	
	/**
	 * Sets the lab work referral.
	 * 
	 * @param referral lab work referral
	 */
	void setReferral(LabWorkReferral referral);
	
	/**
	 * Returns the lab work.
	 * 
	 * @return lab work
	 */
	LabWork getLabWork();
	
	/**
	 * Sets the lab work.
	 * 
	 * @param labWork lab work
	 */
	void setLabWork(LabWork labWork);
	
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