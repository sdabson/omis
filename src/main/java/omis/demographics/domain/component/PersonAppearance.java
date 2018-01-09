package omis.demographics.domain.component;

import java.io.Serializable;

import omis.demographics.domain.Complexion;
import omis.demographics.domain.EyeColor;
import omis.demographics.domain.HairColor;

/**
 * The appearance of a person.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 31, 2013)
 * @since OMIS 3.0
 */
public class PersonAppearance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EyeColor eyeColor;
	
	private HairColor hairColor;
	
	private Complexion complexion;

	/** Instantiates a default appearance. */
	public PersonAppearance() {
		// Default instance
	}
	
	/**
	 * Instantiates a person appearance with the specified eye color and hair
	 * color.
	 * 
	 * @param eyeColor eye color
	 * @param hairColor hair color
	 * @param complexion complexion
	 */
	public PersonAppearance(final EyeColor eyeColor,
			final HairColor hairColor, final Complexion complexion) {
		this.eyeColor = eyeColor;
		this.hairColor = hairColor;
		this.complexion = complexion;
	}
	
	/**
	 * Sets the eye color.
	 * 
	 * @param eyeColor eye color
	 */
	public void setEyeColor(final EyeColor eyeColor) {
		this.eyeColor = eyeColor;
	}
	
	/**
	 * Returns eye color.
	 * 
	 * @return eye color
	 */
	public EyeColor getEyeColor() {
		return this.eyeColor;
	}

	/**
	 * Sets the hair color.
	 * 
	 * @param hairColor hair color
	 */
	public void setHairColor(final HairColor hairColor) {
		this.hairColor = hairColor;
	}
	
	/**
	 * Returns the hair color.
	 * 
	 * @return hair color
	 */
	public HairColor getHairColor() {
		return this.hairColor;
	}

	/**
	 * Returns the complexion.
	 * 
	 * @return complexion
	 */
	public Complexion getComplexion() {
		return this.complexion;
	}

	/**
	 * Sets the complexion.
	 * 
	 * @param complexion complexion
	 */
	public void setComplexion(final Complexion complexion) {
		this.complexion = complexion;
	}
}