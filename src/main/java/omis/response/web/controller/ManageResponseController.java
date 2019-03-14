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
package omis.response.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.response.domain.Grid;
import omis.response.domain.Response;
import omis.response.domain.ResponseLevel;
import omis.response.exception.ResponseExistsException;
import omis.response.service.ResponseService;
import omis.response.web.form.ResponseForm;
import omis.response.web.validator.ResponseFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for managing responses.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Mar 5, 2019)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/response")
public class ManageResponseController {

	/* View names. */
	
	private static final String VIEW_NAME = "response/edit";

	/* Action menus. */

	private static final String ACTION_MENU_VIEW_NAME = 
			"response/includes/responseActionMenu";
	
	/* Redirects. */
	
	private static final String REDIRECT_URL = 
			"redirect:/response/list.html";
	
	/* Model keys. */
	
	private static final String RESPONSE_FORM_MODEL_KEY = "responseForm";
	
	private static final String RESPONSE_MODEL_KEY = "response";
	
	private static final String GRIDS_MODEL_KEY = "grids";
	
	private static final String RESPONSE_CATEGORIES_MODEL_KEY = 
			"responseCategories";
	
	private static final String RESPONSE_LEVELS_MODEL_KEY = "responseLevels";
	
	/* Message keys. */

	private static final String RESPONSE_EXISTS_MESSAGE_KEY = 
			"response.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME = 
			"omis.response.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("responseService")
	private ResponseService responseService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("responsePropertyEditorFactory")
	private PropertyEditorFactory responsePropertyEditorFactory;
	
	@Autowired
	@Qualifier("gridPropertyEditorFactory")
	private PropertyEditorFactory gridPropertyEditorFactory;
	
	@Autowired
	@Qualifier("responseLevelPropertyEditorFactory")
	private PropertyEditorFactory responseLevelPropertyEditorFactory;
	
	/* Validators. */

	@Autowired
	@Qualifier("responseFormValidator")
	private ResponseFormValidator responseFormValidator;
	
	/* Helpers. */

	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	/* Constructors. */
	
	/** Instantiates controller for response. */
	public ManageResponseController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to create a response.
	 * 
	 * @return screen to create response
	 */
	@PreAuthorize("hasRole('RESPONSE_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		ResponseForm responseForm = 
				new ResponseForm();
		ModelAndView mav = this.prepareMav(responseForm);
		return mav;
	}
	
	/**
	 * Shows screen to edit a response.
	 * 
	 * @param response response
	 * @return screen to edit a response
	 */
	@PreAuthorize("hasRole('RESPONSE_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "response", required = true)
				final Response response) {
		ResponseForm responseForm = new ResponseForm();
		responseForm.setCategory(response.getCategory());
		responseForm.setDescription(response.getDescription());
		responseForm.setGrid(response.getGrid());
		responseForm.setLevel(response.getLevel());
		responseForm.setValid(response.getValid());
		ModelAndView mav = this.prepareMav(responseForm);
		mav.addObject(RESPONSE_MODEL_KEY, response);
		return mav;
	}
	
	/**
	 * Saves a response.
	 * 
	 * @param responseForm response form
	 * @param bindingResult binding result
	 * @return redirect to response listing screen
	 * @throws ResponseExistsException if response already exists
	 */
	@PreAuthorize("hasRole('RESPONSE_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(final ResponseForm responseForm,
			final BindingResult bindingResult) 
					throws ResponseExistsException {
		this.responseFormValidator.validate(responseForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return this.prepareRedisplay(responseForm, bindingResult);
			}
		this.responseService.createResponse(responseForm.getDescription(), 
				responseForm.getGrid(), responseForm.getCategory(), 
				responseForm.getLevel(), responseForm.getValid());
		return new ModelAndView(REDIRECT_URL);
	}
	
	/**
	 * Updates a response.
	 * 
	 * @param response response
	 * @param responseForm response form
	 * @param bindingResult binding result
	 * @return redirect to response listing screen
	 * @throws ResponseExistsException if response already exists
	 */
	@PreAuthorize("hasRole('RESPONSE_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "response", required = true)
				final Response response,
			final ResponseForm responseForm, final BindingResult bindingResult) 
					throws ResponseExistsException {
		this.responseFormValidator.validate(responseForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return this.prepareRedisplay(responseForm, bindingResult);
		}
		this.responseService.updateResponse(response, 
				responseForm.getDescription(), 
				responseForm.getLevel(),
				responseForm.getValid());
		return new ModelAndView(REDIRECT_URL);
	}
	
	/**
	 * Removes a response.
	 * 
	 * @param response response
	 * @return redirect to response listing screen
	 */
	@PreAuthorize("hasRole('RESPONSE_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "response", required = true)
				final Response response) {
		this.responseService.removeResponse(response);
		return new ModelAndView(REDIRECT_URL); 
	}
	
	/* Action menus. */
	
	/**
	 * Displays action menu for screen to create/edit a response.
	 * 
	 * @return action menu for screen to create/edit a response
	 */
	@RequestMapping(value = "/responseActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showActionMenu() {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		return mav;
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles duplicate entity exists exceptions.
	 * 
	 * @param exception duplicate entity exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(ResponseExistsException.class)
	public ModelAndView handleResponseExistsException(
			final ResponseExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				RESPONSE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, exception);
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareMav(final ResponseForm responseForm) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(RESPONSE_FORM_MODEL_KEY, responseForm);
		mav.addObject(GRIDS_MODEL_KEY, this.responseService.findGrids());
		mav.addObject(RESPONSE_CATEGORIES_MODEL_KEY, 
				this.responseService.findResponseCategories());
		mav.addObject(RESPONSE_LEVELS_MODEL_KEY, 
				this.responseService.findResponseLevels());
		return mav;
	}
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedisplay(
			final ResponseForm responseForm,
			final BindingResult bindingResult) {
		ModelAndView mav = this.prepareMav(responseForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ RESPONSE_FORM_MODEL_KEY, bindingResult);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Response.class,
				this.responsePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Grid.class, this.gridPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ResponseLevel.class, 
				this.responseLevelPropertyEditorFactory
				.createPropertyEditor());
	}
}