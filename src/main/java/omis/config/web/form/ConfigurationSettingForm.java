package omis.config.web.form;

import java.io.Serializable;

import omis.config.datatype.ConfigurationSettingType;

/**
 * Form for configuration settings.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 10, 2013)
 * @since OMIS 3.0
 */
public class ConfigurationSettingForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private ConfigurationSettingType type;
	
	private String value;
	
	/** Instantiates a default configuration setting form. */
	public ConfigurationSettingForm() {
		// Default instantiation
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the type.
	 * 
	 * @return type
	 */
	public ConfigurationSettingType getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type type
	 */
	public void setType(final ConfigurationSettingType type) {
		this.type = type;
	}

	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
}