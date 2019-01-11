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
package omis.staff.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for staff module.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 6, 2014)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/staff")
public class StaffModuleController {

	private static final String INDEX_VIEW_NAME = "staff/index";

	/** Instantiates a controller for staff module. */
	public StaffModuleController() {
		// Default instantiation
	}
	
	/**
	 * Returns index screen.
	 * 
	 * @return index screen
	 */
	@RequestMapping("/index.html")
	public ModelAndView index() {
		return new ModelAndView(INDEX_VIEW_NAME);
	}
}