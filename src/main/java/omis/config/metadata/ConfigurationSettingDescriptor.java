package omis.config.metadata;

import omis.config.datatype.ConfigurationSettingType;

/**
 * Describes a configuration setting.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 7, 2013)
 * @since OMIS 3.0
 */
public final class ConfigurationSettingDescriptor {

	private final String name;
	
	private final ConfigurationSettingType type;
	
	// Instantiates a configuration property resolver
	private ConfigurationSettingDescriptor(final String name,
			final ConfigurationSettingType type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Instantiates a descriptor.
	 * 
	 * @param name name
	 * @param type type
	 * @return newly instantiated descriptor
	 */
	public static ConfigurationSettingDescriptor createInstance(
			final String name, final ConfigurationSettingType type) {
		return new ConfigurationSettingDescriptor(name, type);
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
	 * Returns the type.
	 * 
	 * @return type
	 */
	public ConfigurationSettingType getType() {
		return this.type;
	}
}