package omis.config.domain.impl;

import omis.config.datatype.ConfigurationSettingType;
import omis.config.domain.ConfigurationSetting;

/**
 * Implementation of configuration setting.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 7, 2013)
 * @since OMIS 3.0
 */
public class ConfigurationSettingImpl
		implements ConfigurationSetting {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String value;

	private ConfigurationSettingType type;
	
	/** Instantiates a default configuration setting. */
	public ConfigurationSettingImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setType(final ConfigurationSettingType type) {
		this.type = type;
	}

	/** {@inheritDoc} */
	@Override
	public ConfigurationSettingType getType() {
		return this.type;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ConfigurationSetting)) {
			return false;
		}
		ConfigurationSetting that = (ConfigurationSetting) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d: %s=\"%s\"", this.getId(), this.getName(),
				this.getValue());
	}
}