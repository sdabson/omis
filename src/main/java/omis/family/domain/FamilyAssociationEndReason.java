package omis.family.domain;

import java.io.Serializable;

/** Reason association is ended.
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.1 (June 1, 2013) */
public interface FamilyAssociationEndReason extends Serializable {
	
	/** Sets the id of the family association end reason.
	 * @param id */
	void setId(Long id);
	
	/** Sets the name of the family association end reason.
	 * @param name */
	void setName(String name);
	
	/** Returns the id of the family association end reason.
	 * @return id */
	Long getId();
	
	/** Returns the name of the family association end reason.
	 * @return name */
	String getName();
	
	/**
	 * Returns whether the family association end reason is valid.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets whether the family association end reason is valid.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns the sort order.
	 * 
	 * @return sort order
	 */
	Short getSortOrder();
	
	/**
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);
	
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