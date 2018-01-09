package omis.victim.domain.component;

import java.io.Serializable;

/**
 * Victim association flags.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 20, 2015)
 * @since OMIS 3.0
 */
public class VictimAssociationFlags
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Boolean victim;
	
	private Boolean docRegistered;
	
	private Boolean business;
	
	private Boolean vineRegistered;
	
	/** Instantiates default victim association flags. */
	public VictimAssociationFlags() {
		// Default instantiation
	}
	
	/**
	 * Instantiates victim association flags.
	 * 
	 * @param victim whether associate is a victim
	 * @param docRegistered whether registered with DOC
	 * @param business whether business
	 * @param vineRegistered whether registered with VINE
	 */
	public VictimAssociationFlags(
			final Boolean victim, final Boolean docRegistered,
			final Boolean business, final Boolean vineRegistered) {
		this.victim  = victim;
		this.docRegistered = docRegistered;
		this.business = business;
		this.vineRegistered = vineRegistered;
	}

	/**
	 * Returns whether associate is a victim.
	 * 
	 * @return whether associate is a victim
	 */
	public Boolean getVictim() {
		return this.victim;
	}

	/**
	 * Sets whether associate is a victim.
	 * 
	 * @param victim whether associate is a victim
	 */
	public void setVictim(final Boolean victim) {
		this.victim = victim;
	}

	/**
	 * Returns whether registered with DOC.
	 * 
	 * @return whether registered with DOC
	 */
	public Boolean getDocRegistered() {
		return this.docRegistered;
	}

	/**
	 * Sets whether registered with DOC.
	 * 
	 * @param docRegistered whether registered with DOC
	 */
	public void setDocRegistered(final Boolean docRegistered) {
		this.docRegistered = docRegistered;
	}

	/**
	 * Returns whether business.
	 * 
	 * @return whether business
	 */
	public Boolean getBusiness() {
		return this.business;
	}

	/**
	 * Sets whether business.
	 * 
	 * @param business whether business
	 */
	public void setBusiness(final Boolean business) {
		this.business = business;
	}

	/**
	 * Returns whether registered with VINE.
	 * 
	 * @return whether registered with VINE
	 */
	public Boolean getVineRegistered() {
		return this.vineRegistered;
	}

	/**
	 * Sets whether registered with VINE. 
	 * 
	 * @param vineRegistered whether registered with VINE
	 */
	public void setVineRegistered(final Boolean vineRegistered) {
		this.vineRegistered = vineRegistered;
	}
}