package omis.detainernotification.domain;

import java.io.Serializable;

/**
 * DetainerActivityCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public interface DetainerActivityCategory extends Serializable {
	
	/**
	 * Returns the ID of the DetainerActivityCategory
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the DetainerActivityCategory
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Name for the DetainerActivityCategory
	 * @return name - String
	 */
	public String getName();
	
	/**
	 * Sets the Name for the DetainerActivityCategory
	 * @param name - String
	 */
	public void setName(String name);
	
	/**
	 * Returns the Description for the DetainerActivityCategory
	 * @return description - String
	 */
	public String getDescription();
	
	/**
	 * Sets the Description for the DetainerActivityCategory
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the Receivable for the DetainerActivityCategory
	 * @return receivable - Boolean
	 */
	public Boolean getReceivable();
	
	/**
	 * Sets the Receivable for the DetainerActivityCategory
	 * @param receivable - Boolean
	 */
	public void setReceivable(Boolean receivable);
	
	/**
	 * Returns the Sendable for the DetainerActivityCategory
	 * @return sendable - Boolean
	 */
	public Boolean getSendable();
	
	/**
	 * Sets the Sendable for the DetainerActivityCategory
	 * @param sendable - Boolean
	 */
	public void setSendable(Boolean sendable);
	
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
