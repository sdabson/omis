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
package omis.config.util;

/**
 * Repository of feature toggles.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Oct 3, 2018)
 * @since OMIS 3.0
 */
public interface FeatureToggles {

	/**
	 * Returns whether the feature is supported.
	 * 
	 * <p>Throws {@code IllegalArgumentException} if feature with supplied name
	 * is not found in module.
	 * 
	 * @param moduleName module name
	 * @param featureName feature name
	 * @return whether feature is supported
	 * @throws IllegalArgumentException if feature with supplied name is not
	 * found in module
	 */
	boolean get(String moduleName, String featureName);
}