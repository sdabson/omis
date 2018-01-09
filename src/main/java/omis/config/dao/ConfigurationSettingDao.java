package omis.config.dao;

import omis.config.domain.ConfigurationSetting;
import omis.dao.GenericDao;

/**
 * Data access object for configuration settings.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 7, 2013)
 * @since OMIS 3.0
 */
public interface ConfigurationSettingDao
		extends GenericDao<ConfigurationSetting> {

	/**
	 * Returns the configuration setting with the specified name.
	 * 
	 * @param name name
	 * @return configuration setting with specified name; {@code null}
	 * if no setting with was found with the specified name
	 */
	ConfigurationSetting findByName(String name);
}