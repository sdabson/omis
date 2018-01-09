package omis.config.util;

/**
 * Holds property.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class PropertyHolder {

	private final String propertyValue;
	
	/**
	 * Instantiates property holder.
	 * 
	 * @param propertyValue value of property
	 */
	public PropertyHolder(final String propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * Returns value of property.
	 * 
	 * @return value of property
	 */
	public String getPropertyValue() {
		return this.propertyValue;
	}
}