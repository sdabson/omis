package omis.conviction.domain.component;

import java.io.Serializable;

/**
 * Time served credit associated with a conviction. 
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.0 (Jan 27, 2017)
 * @since OMIS 3.0
 */
public class ConvictionCredit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer jailTime;
	
	private Integer streetTime;
	
	/** Instantiates a default association of conviction credit. */
	public ConvictionCredit() {
		// Default instantiation
	}
	
	/**
	 * Instantiates association of conviction credit.
	 * 
	 * @param jailTime jail time
	 * @param streetTime street time
	 */
	public ConvictionCredit(
			final Integer jailTime, final Integer streetTime) {
		this.jailTime = jailTime;
		this.streetTime = streetTime;
	}

	/**
	 * Returns the jail time conviction credit.
	 * 
	 * @return jail time
	 */
	public Integer getJailTime() {
		return jailTime;
	}

	/**
	 * Sets the jail time conviction credit.
	 * 
	 * @param jailTime jail time
	 */
	public void setJailTime(Integer jailTime) {
		this.jailTime = jailTime;
	}

	/**
	 * Returns the street time conviction credit.
	 * 
	 * @return street time
	 */
	public Integer getStreetTime() {
		return streetTime;
	}

	/**
	 * Sets the street time conviction credit.
	 * 
	 * @param streetTime street time
	 */
	public void setStreetTime(Integer streetTime) {
		this.streetTime = streetTime;
	}
	
}
