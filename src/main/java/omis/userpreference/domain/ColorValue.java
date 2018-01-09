package omis.userpreference.domain;

/**
 * Color value.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 19, 2015)
 * @since OMIS 3.0
 */
public class ColorValue {

	private Short hue;
	
	private Short saturation;
	
	/**
	 * Instantiates a default instance of color value.
	 */
	public ColorValue() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of color value with the specified hue and
	 * saturation.
	 * 
	 * @param hue hue
	 * @param saturation saturation
	 */
	public ColorValue(final Short hue, final Short saturation) {
		this.hue = hue;
		this.saturation = saturation;
	}

	/**
	 * Returns the hue.
	 * 
	 * @return hue
	 */
	public Short getHue() {
		return this.hue;
	}

	/**
	 * Sets the hue.
	 * 
	 * @param hue hue
	 */
	public void setHue(Short hue) {
		this.hue = hue;
	}

	/**
	 * Returns the saturation.
	 * 
	 * @return saturation
	 */
	public Short getSaturation() {
		return this.saturation;
	}

	/**
	 * Sets the saturation.
	 * 
	 * @param saturation saturation
	 */
	public void setSaturation(Short saturation) {
		this.saturation = saturation;
	}
}