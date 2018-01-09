package omis.conviction.domain.component;

import java.io.Serializable;

/**
 * Flags associated with a conviction. 
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.0 (Mar 24, 2017)
 * @since OMIS 3.0
 */
public class ConvictionFlags
	implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean violentOffense;
	
	private Boolean sexualOffense;
	
	private Boolean paroleIneligible;
	
	private Boolean supervisedReleaseIneligible;
	
	/** Instantiates a default association of conviction flags. */
	public ConvictionFlags() {
		// Default instantiation
	}

	/**
	 * Instantiates association of conviction flags.
	 * 
	 * @param violentOffense whether a violent offense
	 * @param sexualOffense whether a sexual offense
	 * @param paroleIneligible whether parole ineligible
	 * @param supervisedReleaseIneligible whether supervised release ineligible
	 */
	public ConvictionFlags(
			final Boolean violentOffense,
			final Boolean sexualOffense,
			final Boolean paroleIneligible,
			final Boolean supervisedReleaseIneligible) {
		this.violentOffense = violentOffense;
		this.sexualOffense = sexualOffense;
		this.paroleIneligible = paroleIneligible;
		this.supervisedReleaseIneligible = supervisedReleaseIneligible;
	}

	/**
	 * Returns whether the conviction was a violent offense.
	 * 
	 * @return whether the conviction was a violent offense
	 */
	public Boolean getViolentOffense() {
		return violentOffense;
	}

	/**
	 * Sets whether the conviction was a violent offense.
	 * 
	 * @param violentOffense violent offense
	 */
	public void setViolentOffense(Boolean violentOffense) {
		this.violentOffense = violentOffense;
	}

	/**
	 * Returns whether the conviction was a sexual offense.
	 * 
	 * @return whether the conviction was a sexual offense
	 */
	public Boolean getSexualOffense() {
		return sexualOffense;
	}

	/**
	 * Sets whether the conviction was a sexual offense.
	 * 
	 * @param sexualOffense sexual offense
	 */
	public void setSexualOffense(Boolean sexualOffense) {
		this.sexualOffense = sexualOffense;
	}

	/**
	 * Returns whether the conviction is parole ineligible.
	 * 
	 * @return whether the conviction is parole ineligible
	 */
	public Boolean getParoleIneligible() {
		return paroleIneligible;
	}

	/**
	 * Sets whether the conviction is parole ineligible.
	 * 
	 * @param paroleIneligible parole ineligible
	 */
	public void setParoleIneligible(Boolean paroleIneligible) {
		this.paroleIneligible = paroleIneligible;
	}

	/**
	 * Returns whether the conviction is supervised release ineligible.
	 * 
	 * @return whether the conviction is supervised release ineligible
	 */
	public Boolean getSupervisedReleaseIneligible() {
		return supervisedReleaseIneligible;
	}

	/**
	 * Sets whether the conviction is supervised release ineligible.
	 * 
	 * @param supervisedReleaseIneligible supervised release ineligible
	 */
	public void setSupervisedReleaseIneligible(Boolean supervisedReleaseIneligible) {
		this.supervisedReleaseIneligible = supervisedReleaseIneligible;
	}

}
