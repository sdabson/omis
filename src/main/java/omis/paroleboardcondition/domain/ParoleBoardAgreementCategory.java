package omis.paroleboardcondition.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Parole Board Agreement Category.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ParoleBoardAgreementCategory extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Parole Board Agreement Category.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ID of the Parole Board Agreement Category.
	 * @param id - Long
	 */
	void setId(Long id);
	
	/**
	 * Returns the Name for the Parole Board Agreement Category.
	 * @return name - String
	 */
	String getName();
	
	/**
	 * Sets the Name for the Parole Board Agreement Category.
	 * @param name - String
	 */
	void setName(String name);
	
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
