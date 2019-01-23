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
package omis.web.controller.delegate;

import omis.exception.BusinessException;

import org.springframework.web.servlet.ModelAndView;

/**
 * Delegate to handle business exceptions. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 10, 2014)
 * @since OMIS 3.0
 */
public class BusinessExceptionHandlerDelegate {

	private static final String VIEW_NAME = "web/businessExceptionHandler";
	
	private static final String MESSAGE_KEY_MODEL_KEY = "messageKey";
	
	private static final String MESSAGE_BUNDLE_MODEL_KEY = "messageBundle";
	
	private static final String BUSINESS_EXCEPTION_MODEL_KEY
		= "businessException";
	
	/** Instantiates delegate to handle business exceptions. */
	public BusinessExceptionHandlerDelegate() {
		// Default instantiation
	}
	
	/**
	 * Prepares and returns model and view with message key and bundle to
	 * handle business exception.
	 * 
	 * @param messageKey message key
	 * @param messageBundle message bundle
	 * @param businessException business exception
	 * @return model and view with message key and bundle to handle business
	 * exception 
	 */
	public ModelAndView prepareModelAndView(
			final String messageKey, final String messageBundle,
			final BusinessException businessException) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(MESSAGE_KEY_MODEL_KEY, messageKey);
		mav.addObject(MESSAGE_BUNDLE_MODEL_KEY, messageBundle);
		mav.addObject(BUSINESS_EXCEPTION_MODEL_KEY, businessException);
		return mav;
	}
}