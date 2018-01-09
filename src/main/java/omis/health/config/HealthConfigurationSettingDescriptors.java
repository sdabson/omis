package omis.health.config;

import omis.config.datatype.ConfigurationSettingType;
import omis.config.metadata.ConfigurationSettingDescriptor;

/**
 * Configuration settings for health module.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 9, 2013)
 * @since OMIS 3.0
 */
public final class HealthConfigurationSettingDescriptors {

	private static final ConfigurationSettingDescriptor IMMEDIATE_RESPONSE_TIME
		= ConfigurationSettingDescriptor.createInstance(
			"healthImmediateResponseTime", ConfigurationSettingType.INTEGER);
	
	private static final ConfigurationSettingDescriptor ROUTINE_RESPONSE_TIME
		= ConfigurationSettingDescriptor.createInstance(
			"healthRoutingResponseTime", ConfigurationSettingType.INTEGER);
	
	// Prevent instantiation
	private HealthConfigurationSettingDescriptors() {
		throw new AssertionError("Can't instantiate settings class");
	}
	
	/**
	 * Returns the descriptor for the configuration setting that determines
	 * the maximum number of hours for an immediate response time.
	 * 
	 * @return descriptor for configuration setting that determines maximum
	 * number of hours for an immediate response time
	 */
	public static ConfigurationSettingDescriptor getImmediateResponseTime() {
		return HealthConfigurationSettingDescriptors.IMMEDIATE_RESPONSE_TIME;
	}
	
	/**
	 * Returns the descriptor for the configuration setting that determines
	 * the maximum number of hours for a routine response time.
	 * 
	 * @return descriptor for configuration setting that determines maximum
	 * number of hours for a routine response time
	 */
	public static ConfigurationSettingDescriptor getRoutineResponseTime() {
		return HealthConfigurationSettingDescriptors.ROUTINE_RESPONSE_TIME;
	}
}