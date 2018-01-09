package omis.userpreference.web.form;

import java.io.Serializable;

/**
 * User preference form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0
 */
public class UserPreferenceForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Short backgroundHue;
	
	private Short backgroundSaturation;
	
	private Short foregroundHue;
	
	private Short foregroundSaturation;
	
	private Short accentHue;
	
	private Short accentSaturation;
	
	private Boolean whiteBackground;
	
	/**
	 * Instantiates a default instance of user preference form.
	 */
	public UserPreferenceForm() {
		//Default constructor.
	}

	/**
	 * Returns the background hue.
	 * 
	 * @return background hue
	 */
	public Short getBackgroundHue() {
		return this.backgroundHue;
	}

	/**
	 * Sets the background hue.
	 * 
	 * @param backgroundHue background hue
	 */
	public void setBackgroundHue(final Short backgroundHue) {
		this.backgroundHue = backgroundHue;
	}

	/**
	 * Returns the background saturation.
	 * 
	 * @return background saturation
	 */
	public Short getBackgroundSaturation() {
		return this.backgroundSaturation;
	}

	/**
	 * Sets the background saturation.
	 * 
	 * @param backgroundSaturation background saturation
	 */
	public void setBackgroundSaturation(final Short backgroundSaturation) {
		this.backgroundSaturation = backgroundSaturation;
	}

	/**
	 * Returns the foreground hue.
	 * 
	 * @return foreground hue
	 */
	public Short getForegroundHue() {
		return this.foregroundHue;
	}

	/**
	 * Sets the foreground hue.
	 * 
	 * @param foregroundHue foreground hue
	 */
	public void setForegroundHue(final Short foregroundHue) {
		this.foregroundHue = foregroundHue;
	}

	/**
	 * Returns the foreground saturation.
	 * 
	 * @return foreground saturation
	 */
	public Short getForegroundSaturation() {
		return this.foregroundSaturation;
	}

	/**
	 * Sets the foreground saturation.
	 * 
	 * @param foregroundSaturation foreground saturation
	 */
	public void setForegroundSaturation(final Short foregroundSaturation) {
		this.foregroundSaturation = foregroundSaturation;
	}

	/**
	 * Returns the accent hue.
	 * 
	 * @return accent hue
	 */
	public Short getAccentHue() {
		return this.accentHue;
	}

	/**
	 * Sets the accent hue.
	 * 
	 * @param accentHue accent hue
	 */
	public void setAccentHue(final Short accentHue) {
		this.accentHue = accentHue;
	}

	/**
	 * Returns the accent saturation.
	 * 
	 * @return accent saturation
	 */
	public Short getAccentSaturation() {
		return this.accentSaturation;
	}

	/**
	 * Sets the accent saturation.
	 * 
	 * @param accentSaturation accent saturation
	 */
	public void setAccentSaturation(final Short accentSaturation) {
		this.accentSaturation = accentSaturation;
	}

	/**
	 * Returns the white background.
	 * 
	 * @return white background
	 */
	public Boolean getWhiteBackground() {
		return this.whiteBackground;
	}

	/**
	 * Sets the white background.
	 * 
	 * @param whiteBackground white background
	 */
	public void setWhiteBackground(final Boolean whiteBackground) {
		this.whiteBackground = whiteBackground;
	}
}