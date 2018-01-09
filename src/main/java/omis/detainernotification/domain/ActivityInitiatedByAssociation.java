package omis.detainernotification.domain;

import java.io.Serializable;

/**
 * ActivityInitiatedByAssociation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public interface ActivityInitiatedByAssociation extends Serializable {
	
	/**
	 * Returns the ID of the ActivityInitiatedByAssociation
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the ActivityInitiatedByAssociation
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the InitiatedBy for the ActivityInitiatedByAssociation
	 * @return initiatedBy - InterstateAgreementInitiatedBy
	 */
	public InterstateAgreementInitiatedByCategory getInitiatedBy();
	
	/**
	 * Sets the InitiatedBy for the ActivityInitiatedByAssociation
	 * @param initiatedBy - InterstateAgreementInitiatedBy
	 */
	public void setInitiatedBy(InterstateAgreementInitiatedByCategory initiatedBy);
	
	/**
	 * Returns the Activity for the ActivityInitiatedByAssociation
	 * @return activity - DetainerActivityCategory
	 */
	public DetainerActivityCategory getActivity();
	
	/**
	 * Sets the Activity for the ActivityInitiatedByAssociation
	 * @param activity - DetainerActivityCategory
	 */
	public void setActivity(DetainerActivityCategory activity);
	
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
