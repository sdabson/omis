package omis.criminalassociation.domain.component;

import java.io.Serializable;


/**
 * Criminal Association Flags.
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (April 14, 2015)
 * @since: OMIS 3.0
 */
public class CriminalAssociationFlags implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean witness;
	
	/** Instantiates a default wage. */
	public CriminalAssociationFlags() {
		// Do nothing
	}
	
	/**
	 * Sets the value of witness.
	 * 
	 * @param witness witness
	 */
	public void setWitness(final Boolean witness) {
		this.witness = witness;
	}
	
	/**
	 * Gets the value of witness.
	 * 
	 * @return witness witness
	 */
	public Boolean getWitness() {
		return this.witness;
	}
}