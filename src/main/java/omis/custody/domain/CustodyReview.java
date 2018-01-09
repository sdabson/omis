package omis.custody.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Custody Review.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public interface CustodyReview 
		extends OffenderAssociable, Creatable, Updatable {
	
	/**
	 * Returns the id of the custody review.
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the custody review.
	 * @param id the id to set
	 */
	void setId(final Long id);
	
	/**
	 * Returns the custody level of the custody review.
	 * @return the custodyLevel
	 */
	CustodyLevel getCustodyLevel();

	/**
	 * Sets the custody level of the custody review.
	 * @param custodyLevel the custodyLevel to set
	 */
	void setCustodyLevel(final CustodyLevel custodyLevel);
	
	/**
	 * Returns the change reason of the custody review.
	 * @return the changeReason
	 */
	CustodyChangeReason getChangeReason();

	/**
	 * Sets the change reason of the custody review.
	 * @param changeReason the changeReason to set
	 */
	void setChangeReason(final CustodyChangeReason changeReason);
	
	/**
	 * Returns the action date of the custody review.
	 * @return action date
	 */
	Date getActionDate();

	/**
	 * Sets the action date of the custody review.
	 * @param actionDate action date
	 */
	void setActionDate(Date actionDate);

	/**
	 * Returns the next review date of the custody review.
	 * @return next review date
	 */
	Date getNextReviewDate();

	/**
	 * Sets the next review date of the custody review.
	 * @param nextReviewDate next review date
	 */
	void setNextReviewDate(Date nextReviewDate);
	
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