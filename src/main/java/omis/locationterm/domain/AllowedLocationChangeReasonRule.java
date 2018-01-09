package omis.locationterm.domain;

import java.io.Serializable;

/**
 * Allowed location change reason rule.
 * 
 * <p>Given a location term change action, defines allowed reasons.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface AllowedLocationChangeReasonRule
		extends Serializable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets change action for which to allow reason.
	 * 
	 * @param changeAction change action for which to allow reason
	 */
	void setChangeAction(LocationTermChangeAction changeAction);
	
	/**
	 * Returns change action for which to allow reason.
	 * 
	 * @return change action for which to allow reason
	 */
	LocationTermChangeAction getChangeAction();
	
	/**
	 * Sets allowed reason for change action.
	 * 
	 * @param reason allowed reason for change action.
	 */
	void setReason(LocationReason reason);
	
	/**
	 * Returns allowed reason for change action.
	 * 
	 * @return allowed reason for change action
	 */
	LocationReason getReason();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}