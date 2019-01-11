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

// Define config namespace
if (typeof config === 'undefined') {
	var config = new Object();
}

/**
 * Feature toggles.
 *
 * @author: Stephen Abson
 */
config.FeatureToggles = new function() {

	/**
	 * Returns whether feature in module is enabled.
	 *
	 * <p>Reports error if feature does not exist in module.
	 *
	 * @param moduleName name of module
	 * @param featureName name of feature
	 * @return whether feature is module is enabled
	 */
	this.get = function(moduleName, featureName) {
		var request = new XMLHttpRequest();
		var url = config.ServerConfig.getContextPath() + "/resources/common/featureToggles/get.json?moduleName=" + moduleName + "&featureName=" + featureName; 
		request.open("get", url, false);
		request.send(null);
		if (request.status == 200) {
			return eval(request.responseText);
		} else {
			alert("Error - status: " + request.status + "; text: " + request.statusText + "; url: " + url);
		}
	};
};