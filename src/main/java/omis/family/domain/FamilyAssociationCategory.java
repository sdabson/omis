package omis.family.domain;

import java.io.Serializable;

/** 
 * Category of relationship association.
 * 
 * @author Ryan Johns
 * @author Jason Nelson
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.2 (June 2, 2015)
 * @since OMIS 3.0 */
public interface FamilyAssociationCategory extends Serializable {
	
	/** Sets the ID.
	 * @param id ID */
	void setId(Long id);
	
	/** Returns the ID.
	 * @return ID */
	Long getId();
	
	/** Sets name.
	 * @param name name*/
	void setName(String name);
	
	/** Returns name.
	 * @return name */
	String getName();
	
	/** Sets the classification.
	 * @param classification classification 	 */
	void setClassification(
			FamilyAssociationCategoryClassification classification);
	
	/** Returns the classification.
	 * @return classification
	 */
	FamilyAssociationCategoryClassification getClassification();
	
	/**
	 * Returns whether family association category is valid.
	 * 
	 * @return valid valid
	 */
	Boolean getValid();

	/**
	 * Set whether family association category is valid.
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