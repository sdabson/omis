/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.config.util.impl;

import java.util.Properties;

import omis.config.util.FeatureToggles;

/**
 * Implementation of feature toggle repository.
 * 
 * <p>Repository requires a profile name and properties for representing
 * features. The properties represent feature toggles are key/value pairs. The
 * key is the module and feature name separated by a period, for instance:
 * 
 * <pre>offendercontact.allowResidenceAtMailingAddress</pre>
 * 
 * The value of each key is a comma separated list of build profiles in which
 * the feature is supported. For instance:
 * 
 * <pre>standalone,dev,test</pre>
 * 
 * Read from a properties file, the full entry would be:
 * 
 * <pre>contact.allowResidenceAtMailingAddress=standalone,dev,test</pre>
 * 
 * <p>Implementation supports a {@code allowedProfileNames} constructor
 * argument. On construction, all feature toggles in the properties representing
 * features will be checked against the allowed profile names. If a feature is
 * enabled in a profile not listed in the {@code allowedProfileNames}, an
 * {@code IllegalArgumentException} will be thrown.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Oct 3, 2018)
 * @since OMIS 3.0
 */
public class FeatureTogglesImpl
		implements FeatureToggles {

	private final static String KEY_FORMAT = "%s.%s";
	
	private final String profileName;
	
	private final Properties featureToggles;
	
	/**
	 * Instantiates implementation of feature toggle repository.
	 * 
	 * @param profileName profile name
	 * @param allowedProfileNames comma separated list of allowed profile names
	 * @param featureToggles feature toggles
	 * @throws IllegalArgumentException if a feature toggle is enabled for a
	 * profile that is not allowed (specified in {@code allowedProfileNames})
	 */
	public FeatureTogglesImpl(
			final String profileName, final String allowedProfileNames,
			final Properties featureToggles) {
		for (String key : featureToggles.stringPropertyNames()) {
			String value = featureToggles.getProperty(key);
			for (String splitValue : value.split(",")) {
				if (!allowedProfileNames.contains(splitValue.trim())) {
					throw new IllegalArgumentException(String.format(
						"Profile \'%s\' not supported for feature toggle \'%s\'"
							+ "; supported profiles are \'%s\'",
						splitValue, key, allowedProfileNames));
				}
			}
		}
		this.profileName = profileName;
		this.featureToggles = featureToggles;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean get(
			final String moduleName, final String featureName) {
		String value = this.featureToggles.getProperty(
				String.format(KEY_FORMAT, moduleName, featureName));
		if (value != null) {
			return value.contains(this.profileName);
		} else {
			throw new IllegalArgumentException(
					String.format("Feature \'%s' not found in module \'%s\'",
							featureName, moduleName));
		}
	}
}