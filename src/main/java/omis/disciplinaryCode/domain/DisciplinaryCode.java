package omis.disciplinaryCode.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * DisciplinaryCode.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Sep 1, 2017)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCode extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Disciplinary Code
	 * @return id - Long
	 */
	public Long getId();
	
	/**
	 * Returns the value of the Disciplinary Code
	 * @return value - String
	 */
	public String getValue();
	
	/**
	 * Returns the description of the Disciplinary Code
	 * @return description - String
	 */
	public String getDescription();
	
	/**
	 * Returns the extended description of the Disciplinary Code
	 * @return
	 */
	public String getExtendedDescription();
	
	/**
	 * Sets the ID of the Disciplinary Code
	 * @param id - id
	 */
	public void setId(Long id);
	
	/**
	 * Sets the value of the Disciplinary Code
	 * @param value - value
	 */
	public void setValue(String value);
	
	/**
	 * Sets the description of the Disciplinary Code
	 * @param description - description
	 */
	public void setDescription(String description);
	
	/**
	 * Sets the extended description of the Disciplinary Code
	 * @param extendedDescription - extended description
	 */
	public void setExtendedDescription(String extendedDescription);
	
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
	public boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode();
	
	
}
