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
package omis.paroleboardmember.web.controller;

import java.util.Date;

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
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.paroleboardmember.service.ParoleBoardMemberService;
import omis.paroleboardmember.web.form.ParoleBoardMemberForm;
import omis.paroleboardmember.web.validator.ParoleBoardMemberFormValidator;
import omis.staff.domain.StaffAssignment;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for managing parole board members.
 *
 * @author Josh Divine
 * @version 0.1.1 (Aug 2, 2018)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/paroleBoardMember")
public class ManageParoleBoardMemberController {
	
	/* View names. */
	
	private static final String VIEW_NAME = "paroleBoardMember/edit";

	/* Action menus. */

	private static final String ACTION_MENU_VIEW_NAME = 
			"paroleBoardMember/includes/paroleBoardMemberActionMenu";
	
	/* Redirects. */
	
	private static final String REDIRECT_URL = 
			"redirect:/paroleBoardMember/list.html";
	
	/* Model keys. */
	
	private static final String PAROLE_BOARD_MEMBER_FORM_MODEL_KEY = 
			"paroleBoardMemberForm";
	
	private static final String PAROLE_BOARD_MEMBER_MODEL_KEY = 
			"paroleBoardMember";
	
	/* Message keys. */


	private static final String PAROLE_BOARD_MEMBER_EXISTS_MESSAGE_KEY = 
			"paroleBoardMember.exists";
	
	private static final String PAROLE_BOARD_MEMBER_DATE_CONFLICT_MESSAGE_KEY =
			"paroleBoardMember.dateRange.conflict";

	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME = 
			"omis.paroleboardmember.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("paroleBoardMemberService")
	private ParoleBoardMemberService paroleBoardMemberService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("paroleBoardMemberPropertyEditorFactory")
	private PropertyEditorFactory paroleBoardMemberPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	

	@Autowired
	@Qualifier("staffAssignmentPropertyEditorFactory")
	private PropertyEditorFactory staffAssignmentPropertyEditorFactory;
	
	
	/* Validators. */

	@Autowired
	@Qualifier("paroleBoardMemberFormValidator")
	private ParoleBoardMemberFormValidator paroleBoardMemberFormValidator;
	
	/* Helpers. */

	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	/* Constructors. */
	
	/** Instantiates controller for parole board members. */
	public ManageParoleBoardMemberController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to create a parole board member.
	 * 
	 * @return screen to create parole board member
	 */
	@PreAuthorize("hasRole('PAROLE_BOARD_MEMBER_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		ParoleBoardMemberForm paroleBoardMemberForm = 
				new ParoleBoardMemberForm();
		paroleBoardMemberForm.setStartDate(new Date());
		ModelAndView mav = this.prepareMav(paroleBoardMemberForm);
		return mav;
	}
	
	/**
	 * Shows screen to edit a parole board member.
	 * 
	 * @param paroleBoardMember parole board member
	 * @return screen to edit a parole board member
	 */
	@PreAuthorize("hasRole('PAROLE_BOARD_MEMBER_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "paroleBoardMember", required = true)
				final ParoleBoardMember paroleBoardMember) {
		ParoleBoardMemberForm paroleBoardMemberForm = 
				new ParoleBoardMemberForm();
		paroleBoardMemberForm.setStartDate(DateRange.getStartDate(
				paroleBoardMember.getDateRange()));
		paroleBoardMemberForm.setEndDate(DateRange.getEndDate(
				paroleBoardMember.getDateRange()));
		paroleBoardMemberForm.setStaffAssignment(paroleBoardMember
				.getStaffAssignment());
		ModelAndView mav = this.prepareMav(paroleBoardMemberForm);
		mav.addObject(PAROLE_BOARD_MEMBER_MODEL_KEY, paroleBoardMember);
		return mav;
	}
	
	/**
	 * Saves a parole board member.
	 * 
	 * @param paroleBoardMemberForm parole board member form
	 * @param bindingResult binding result
	 * @return redirect to parole board member listing screen
	 * @throws DuplicateEntityFoundException if duplicate entity exists 
	 * @throws DateConflictException if date range is not within staff 
	 * assignment date range
	 */
	@PreAuthorize("hasRole('PAROLE_BOARD_MEMBER_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(final ParoleBoardMemberForm paroleBoardMemberForm,
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		this.paroleBoardMemberFormValidator.validate(paroleBoardMemberForm, 
				bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(paroleBoardMemberForm, 
					bindingResult);
			return mav;
			}
		this.paroleBoardMemberService.create(
				paroleBoardMemberForm.getStaffAssignment(), 
				paroleBoardMemberForm.getStartDate(),
				paroleBoardMemberForm.getEndDate());

		return new ModelAndView(REDIRECT_URL);
	}
	
	/**
	 * Updates a parole board member.
	 * 
	 * @param paroleBoardMember parole board member
	 * @param paroleBoardMemberForm parole board member form
	 * @param bindingResult binding result
	 * @return redirect to parole board member listing screen
	 * @throws DuplicateEntityFoundException if duplicate entity exists 
	 * @throws DateConflictException if date range is not within staff 
	 * assignment date range
	 */
	@PreAuthorize("hasRole('PAROLE_BOARD_MEMBER_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "paroleBoardMember", required = true)
				final ParoleBoardMember paroleBoardMember,
			final ParoleBoardMemberForm paroleBoardMemberForm,
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		this.paroleBoardMemberFormValidator.validate(paroleBoardMemberForm, 
				bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(paroleBoardMemberForm, 
					bindingResult);
			return mav;
			}
		this.paroleBoardMemberService.update(paroleBoardMember, 
				paroleBoardMemberForm.getStartDate(), 
				paroleBoardMemberForm.getEndDate());
		return new ModelAndView(REDIRECT_URL);
	}
	
	/**
	 * Removes a parole board member.
	 * 
	 * @param paroleBoardMember parole board member
	 * @return redirect to parole board member listing screen
	 */
	@PreAuthorize("hasRole('PAROLE_BOARD_MEMBER_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "paroleBoardMember", required = true)
				final ParoleBoardMember paroleBoardMember) {
		this.paroleBoardMemberService.remove(paroleBoardMember);
		return new ModelAndView(REDIRECT_URL); 
	}
	
	/* Action menus. */
	
	/**
	 * Displays action menu for screen to create/edit a parole board member.
	 * 
	 * @return action menu for screen to create/edit a parole board member
	 */
	@RequestMapping(value = "/paroleBoardMemberActionMenu.html", 
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
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleParoleBoardMemberExistsException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PAROLE_BOARD_MEMBER_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Handles parole board member date conflict exceptions.
	 * 
	 * @param exception parole board member date conflict exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DateConflictException.class)
	public ModelAndView handleParoleBoardMemberDateConflictException(
			final DateConflictException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PAROLE_BOARD_MEMBER_DATE_CONFLICT_MESSAGE_KEY, 
				ERROR_BUNDLE_NAME, exception);
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareMav(
			final ParoleBoardMemberForm paroleBoardMemberForm) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(PAROLE_BOARD_MEMBER_FORM_MODEL_KEY, 
				paroleBoardMemberForm);
		return mav;
	}
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedisplay(
			final ParoleBoardMemberForm paroleBoardMemberForm,
			final BindingResult bindingResult) {
		ModelAndView mav = this.prepareMav(paroleBoardMemberForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ PAROLE_BOARD_MEMBER_FORM_MODEL_KEY, bindingResult);
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
		binder.registerCustomEditor(ParoleBoardMember.class,
				this.paroleBoardMemberPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(StaffAssignment.class,
				this.staffAssignmentPropertyEditorFactory
				.createPropertyEditor());
	}
}