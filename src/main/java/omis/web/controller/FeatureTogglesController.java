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
package omis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.config.util.FeatureToggles;

/**
 * Feature toggles controller.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Oct 9, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/resources/common/featureToggles")
public class FeatureTogglesController {
	
	/* View names. */
	
	private static final String BOOLEAN_VALUE_VIEW_NAME
		= "common/json/booleanValue";
	
	/* Parameter names. */
	
	private static final String BOOLEAN_VALUE_PARAM_NAME = "booleanValue";

	/* Feature toggles. */
	
	@Autowired
	@Qualifier("featureToggles")
	private FeatureToggles featureToggles;
	
	/* URL invokable methods. */
	
	/**
	 * Returns whether feature in module with name is enabled.
	 * 
	 * @param moduleName name of module
	 * @param featureName name of feature
	 * @return whether feature in module with name is enabled
	 */
	@RequestMapping("/get.json")
	public ModelAndView get(
			@RequestParam(value = "moduleName", required = true)
				final String moduleName,
			@RequestParam(value = "featureName", required = true)
				final String featureName) {
		boolean enabled = this.featureToggles.get(moduleName, featureName);
		ModelAndView mav = new ModelAndView(BOOLEAN_VALUE_VIEW_NAME);
		mav.addObject(BOOLEAN_VALUE_PARAM_NAME, enabled);
		return mav;
	}
}