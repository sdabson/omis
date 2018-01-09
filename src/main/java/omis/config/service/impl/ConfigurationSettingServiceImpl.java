package omis.config.service.impl;

import java.io.Serializable;
import java.util.List;

import omis.config.dao.ConfigurationSettingDao;
import omis.config.domain.ConfigurationSetting;
import omis.config.metadata.ConfigurationSettingDescriptor;
import omis.config.service.ConfigurationSettingService;
import omis.instance.factory.InstanceFactory;

/**
 * Implementation of service for configuration settings.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 7, 2013)
 * @since OMIS 3.0
 */
public class ConfigurationSettingServiceImpl
		implements ConfigurationSettingService {

	private final ConfigurationSettingDao configurationSettingDao;
	
	private final InstanceFactory<ConfigurationSetting>
			configurationSettingInstanceFactory;

	/**
	 * Instantiates a service for configuration settings with the specified
	 * resources.
	 *
	 * @param configurationSettingDao data access object for configuration
	 * settings
	 * @param configurationSettingInstanceFactory instance factory for
	 * configuration settings
	 */
	public ConfigurationSettingServiceImpl(
			final ConfigurationSettingDao configurationSettingDao,
			final InstanceFactory<ConfigurationSetting>
				configurationSettingInstanceFactory) {
		this.configurationSettingDao = configurationSettingDao;
		this.configurationSettingInstanceFactory
			= configurationSettingInstanceFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public <T extends Serializable> T getValue(
			final ConfigurationSettingDescriptor descriptor) {
		ConfigurationSetting setting =
				this.configurationSettingDao.findByName(descriptor.getName());
		if (setting == null) {
			return null;
		}
		return setting.getType().convert(setting.getValue());
	}

	/** {@inheritDoc} */
	@Override
	public <T extends Serializable> void setValue(
			final ConfigurationSettingDescriptor descriptor, final T value) {
		ConfigurationSetting setting = this.configurationSettingDao
				.findByName(descriptor.getName());
		if (setting == null) {
			setting = this.configurationSettingInstanceFactory.createInstance();
			setting.setName(descriptor.getName());
			setting.setType(descriptor.getType());
		}
		setting.setValue(setting.getType().asString(value));
		this.configurationSettingDao.makePersistent(setting);
	}

	/** {@inheritDoc} */
	@Override
	public ConfigurationSetting findById(final Long id) {
		return this.configurationSettingDao.findById(id, false);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ConfigurationSetting> findAll() {
		return this.configurationSettingDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public ConfigurationSetting save(final ConfigurationSetting setting) {
		return this.configurationSettingDao.makePersistent(setting);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final ConfigurationSetting setting) {
		this.configurationSettingDao.makeTransient(setting);
	}
}