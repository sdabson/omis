package omis.config.dao.impl.hibernate;

import java.util.List;

import omis.config.dao.ConfigurationSettingDao;
import omis.config.domain.ConfigurationSetting;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for configuration settings.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 7, 2013)
 * @since OMIS 3.0
 */
public class ConfigurationSettingDaoHibernateImpl
		extends GenericHibernateDaoImpl<ConfigurationSetting>
		implements ConfigurationSettingDao {

	private static final String NAME_PARAM_NAME = "name";
	
	private static final String FIND_BY_NAME_QUERY_NAME
		= "findConfigurationSettingByName";
	
	private static final String FIND_ALL_QUERY_NAME
		= "findAllConfigurationSettings";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * configuration settings with specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ConfigurationSettingDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public ConfigurationSetting findByName(final String name) {
		ConfigurationSetting setting = (ConfigurationSetting)
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_BY_NAME_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.uniqueResult();
		return setting;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ConfigurationSetting> findAll() {
		@SuppressWarnings("unchecked")
		List<ConfigurationSetting> settings = getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return settings;
	}
}