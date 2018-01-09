package omis.victim.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.docket.domain.Docket;
import omis.person.domain.Person;

/**
 * Victim docket association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public interface VictimDocketAssociation
extends Creatable, Updatable {

	/**
	 * Returns the ID.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the victim.
	 * 
	 * @return victim
	 */
	Person getVictim();
	
	/**
	 * Sets the victim.
	 * 
	 * @param victim victim
	 */
	void setVictim(Person victim);
	
	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	Docket getDocket();
	
	/**
	 * Sets the docket.
	 * 
	 * @param docket docket
	 */
	void setDocket(Docket docket);
	
	/**
	 * Returns the victim impact summary.
	 * 
	 * @return victim impact summary
	 */
	String getVictimImpactSummary();
	
	/**
	 * Sets victim impact summary.
	 * 
	 * @param victimImpactSummary victim impact summary
	 */
	void setVictimImpactSummary(String victimImpactSummary);
	
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