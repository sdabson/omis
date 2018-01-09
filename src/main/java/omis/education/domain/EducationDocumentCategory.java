package omis.education.domain;

import omis.audit.domain.Creatable;

/**
 * EducationDocumentCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface EducationDocumentCategory extends Creatable {
	
	/**
	 * Returns the ID of the EducationDocumentCategory
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the EducationDocumentCategory
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the String for the EducationDocumentCategory
	 * @return name - String
	 */
	public String getName();
	
	/**
	 * Sets the String for the EducationDocumentCategory
	 * @param name - String
	 */
	public void setName(String name);
	
	/**
	 * Returns the Boolean for the EducationDocumentCategory
	 * @return valid - Boolean
	 */
	public Boolean getValid();
	
	/**
	 * Sets the Boolean for the EducationDocumentCategory
	 * @param valid - Boolean
	 */
	public void setValid(Boolean valid);
	
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
