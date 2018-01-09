package omis.religion.web.form;

import java.io.Serializable;

import omis.religion.domain.ReligiousAccommodation;

/**
 * Religious accommodation authorization item.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 24, 2014)
 * @since OMIS 3.0
 */
public class ReligiousAccommodationAuthorizationItem
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private ReligiousAccommodation accommodation;
	
	private boolean authorized;
	
	/** Instantiates an accommodation authorization item. */
	public ReligiousAccommodationAuthorizationItem() {
		// Default instantiation
	}

	/**
	 * Returns the accommodation.
	 * 
	 * @return accommodation
	 */
	public ReligiousAccommodation getAccommodation() {
		return this.accommodation;
	}

	/**
	 * Sets the accommodation.
	 * 
	 * @param accommodation accommodation
	 */
	public void setAccommodation(
			final ReligiousAccommodation accommodation) {
		this.accommodation = accommodation;
	}

	/**
	 * Returns whether the accommodation is authorized.
	 * 
	 * @return whether accommodation is authorized
	 */
	public boolean getAuthorized() {
		return this.authorized;
	}

	/**
	 * Sets whether the accommodation is authorized.
	 * 
	 * @param authorized whether accommodation is authorized
	 */
	public void setAuthorized(final boolean authorized) {
		this.authorized = authorized;
	}
}