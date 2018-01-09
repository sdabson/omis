package omis.criminalassociation.domain;

import java.io.Serializable;

/**
 * Criminal Association Category.
 * 
 * @author Joel Norris 
 * @author Yidong Li
 * @version 0.1.1 (Apr, 14 2015)
 * @since OMIS 3.0
 */
public interface CriminalAssociationCategory extends Serializable {
	
	/**
	 * Return the id of the criminal association category.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the criminal association category.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the name of the criminal association category.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name of the criminal association category.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Return the sort order.
	 * 
	 * @return the sort order
	 */
	Short getSortOrder();

	/**
	 * Set the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);

	/**
	 * Return the valid value of the record.
	 * 
	 * @return the valid
	 */
	Boolean getValid();

	/**
	 * Set the valid value of the record.
	 * 
	 * @param valid the valid to set
	 */
	void setValid(Boolean valid);

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