package omis.config.datatype;

import java.io.Serializable;

/**
 * Type of configuration setting.
 * 
 * <p>Handles conversion of configuration setting type to Java type.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 9, 2013)
 * @since OMIS 3.0
 */
public enum ConfigurationSettingType
		implements Serializable {

	/** String type. */
	STRING("string", String.class, new Converter() {

		/** {@inheritDoc} */
		@Override
		public <T extends Serializable> T convert(final String value) {
			@SuppressWarnings("unchecked")
			T result = (T) value;
			return result;
		}

		/** {@inheritDoc} */
		@Override
		public <T extends Serializable> String asString(final T value) {
			return value.toString();
		}
	}),
	
	/** Integer type. */
	INTEGER("integer", Integer.class, new Converter() {

		/** {@inheritDoc} */
		@Override
		public <T extends Serializable> T convert(final String value) {
			@SuppressWarnings("unchecked")
			T result = (T) Integer.valueOf(value);
			return result;
		}

		/** {@inheritDoc} */
		@Override
		public <T extends Serializable> String asString(final T value) {
			return Integer.valueOf(value.toString()).toString();
		}
	}),
	
	/** Boolean type. */
	BOOLEAN("boolean", Boolean.class, new Converter() {

		/** {@inheritDoc} */
		@Override
		public <T extends Serializable> T convert(final String value) {
			@SuppressWarnings("unchecked")
			T result = (T) Boolean.valueOf(value);
			return result;
		}

		/** {@inheritDoc} */
		@Override
		public <T extends Serializable> String asString(final T value) {
			return Boolean.valueOf(value.toString()).toString();
		}
	});
	
	private final String name;
	
	private final Class<? extends Serializable> type;
	
	private final Converter converter;
	
	// Instantiates a configuration setting type
	private ConfigurationSettingType(final String name,
			final Class<? extends Serializable> type,
			final Converter converter) {
		this.name = name;
		this.type = type;
		this.converter = converter;
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
	public Class<? extends Serializable> getType() {
		return this.type;
	}
	
	/**
	 * Converts the specified value to the type.
	 * 
	 * @param <T> configuration setting value type
	 * @param value value
	 * @return value converted to type
	 */
	public <T extends Serializable> T convert(final String value) {
		return this.converter.convert(value);
	}
	
	/**
	 * Converts the specified value to a string.
	 * 
	 * @param <T> configuration setting value type
	 * @param value value
	 * @return value as string
	 */
	public <T extends Serializable> String asString(final T value) {
		return this.converter.asString(value);
	}

	/**
	 * Returns the configuration setting type instance for the specified type.
	 * 
	 * @param type type
	 * @return instance for type; {@code null} if the type is not supported
	 */
	public static ConfigurationSettingType findByType(
			final Class<? extends Serializable> type) {
		for (ConfigurationSettingType setting
				: ConfigurationSettingType.values()) {
			if (setting.getType().isAssignableFrom(type)) {
				return setting;
			}
		}
		return null;
	}
	
	/** Converts a string to the type. */
	private interface Converter {
		
		/**
		 * Converts the value to the type and returns it.
		 * 
		 * @param value value to convert
		 * @return value converted to type
		 */
		<T extends Serializable> T convert(String value);
		
		/**
		 * Converts the value to a string and returns it.
		 * 
		 * @param value value to convert to string
		 * @return value converted to string
		 */
		<T extends Serializable> String asString(T value);
	}
}