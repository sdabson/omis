package omis.hearing.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.Offender;

/**
 * ImposedSanction.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public interface ImposedSanction extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the ImposedSanction
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the ImposedSanction
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Description for the ImposedSanction
	 * @return description - String
	 */
	public String getDescription();
	
	/**
	 * Sets the Description for the ImposedSanction
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the Offender for the ImposedSanction
	 * @return offender - Offender
	 */
	public Offender getOffender();
	
	/**
	 * Sets the Offender for the ImposedSanction
	 * @param offender - Offender
	 */
	public void setOffender(Offender offender);
	
	/**
	 * Returns the Infraction for the ImposedSanction
	 * @return infraction - Infraction
	 */
	public Infraction getInfraction();
	
	/**
	 * Sets the Infraction for the ImposedSanction
	 * @param infraction - Infraction
	 */
	public void setInfraction(Infraction infraction);
	
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
