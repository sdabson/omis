package omis.substanceuse.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.Offender;
import omis.substance.domain.Substance;

/**
 * Substance use.
 * @author Joel Norris
 * @version 0.1.0 (Nov 16, 2016)
 * @since OMIS 3.0
 */
public interface SubstanceUse extends Creatable, Updatable{
	
	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 *  Sets the id.
	 * 
	 *  @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the offender.
	 * 
	 * @param offender offender
	 */
	void setOffender(Offender offender);
	
	/**
	 * Returns the substance.
	 * 
	 * @return substance
	 */
	Substance getSubstance();
	
	/**
	 * Sets the substance.
	 * 
	 * @param substance substance
	 */
	void setSubstance(Substance substance);
	
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