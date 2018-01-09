package omis.offender.domain;

import java.io.Serializable;

/**
 * Can be associated with an offender.
 * <p>
 * In order to be able to be associated with an offender, the type
 * must support an {@code offender} property.
 * @author Stephen Abson
 * @version 0.1.0 (Sep 20, 2012)
 * @since OMIS 3.0
 */
public interface OffenderAssociable extends Serializable {

	/**
	 * Sets the associated offender.
	 * @param offender associated offender
	 */
	void setOffender(Offender offender);
	
	/**
	 * Returns the associated offender.
	 * @return associated offender
	 */
	Offender getOffender();	
}