package omis.config.service;

import java.io.Serializable;
import java.util.List;

import omis.config.domain.ConfigurationSetting;
import omis.config.metadata.ConfigurationSettingDescriptor;

/**
 * Service for configuration settings.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 7, 2013)
 * @since OMIS 3.0
 */
public interface ConfigurationSettingService {
	
	/**
	 * Retrieves a setting value according to descriptor.
	 * 
	 * @param <T> configuration setting value type
	 * @param descriptor descriptor
	 * @return property value
	 * @throws IllegalArgumentException if the type specified by the descriptor
	 * is not supported
	 */
	<T extends Serializable> T getValue(
			ConfigurationSettingDescriptor descriptor);
	
	/**
	 * Stores the setting value according to the descriptor.
	 * 
	 * <p>If a setting does not exist that is identifiable by the descriptor, a
	 * new one will be created.
	 * 
	 * @param <T> configuration setting value type
	 * @param descriptor descriptor
	 * @param value value
	 * @throws IllegalArgumentException if the type specified by the descriptor
	 * is not supported
	 */
	<T extends Serializable> void setValue(
			ConfigurationSettingDescriptor descriptor, T value);
	
	/**
	 * Returns the setting with the specified ID.
	 * 
	 * @param id ID of setting to return
	 * @return setting with specified ID
	 */
	ConfigurationSetting findById(Long id);
	
	/**
	 * Returns all configuration settings.
	 * 
	 * @return all configurations settings
	 */
	List<ConfigurationSetting> findAll();
	
	/**
	 * Saves a configuration setting.
	 * 
	 * @param setting configuration setting to save
	 * @return saved configuration setting
	 */
	ConfigurationSetting save(ConfigurationSetting setting);
	
	/**
	 * Removes a configuration setting.
	 * 
	 * @param setting configuration setting to remove
	 */
	void remove(ConfigurationSetting setting);
}