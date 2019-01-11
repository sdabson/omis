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
package omis.caseload.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.service.OfficerCaseAssignmentService;
import omis.caseload.web.form.OfficerCaseAssignmentTransferForm;
import omis.caseload.web.validator.OfficerCaseAssignmentTransferFormValidator;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for managing officer case assignment transfers.
 * 
 * @author Josh Divine
 * @version 0.1.1 (Aug 13, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/caseload/officerCaseAssignment/transfer")
@PreAuthorize("hasRole('USER')")
public class ManageOfficerCaseAssignmentTransferController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME = 
			"caseload/officerCaseAssignment/transfer/edit";
	
	private static final String ACTION_MENU_VIEW_NAME = 
			"caseload/officerCaseAssignment/transfer/includes/"
			+ "officerCaseAssignmentTransferActionMenu";
	
	/* Model keys. */
	
	private static final String OFFICER_CASE_ASSIGNMENT_TRANSFER_FORM_MODEL_KEY =
			"officerCaseAssignmentTransferForm";
	
	/* Redirects. */
	
	private static final String REDIRECT_URL = 
			"redirect:/caseload/officerCaseAssignment/list.html?"
			+ "userAccount=%d&&effectiveDate=%2$tm/%2$td/%2$tY";
	
	/* Services. */
	
	@Autowired
	@Qualifier("officerCaseAssignmentService")
	private OfficerCaseAssignmentService officerCaseAssignmentService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("officerCaseAssignmentTransferFormValidator")
	private OfficerCaseAssignmentTransferFormValidator 
			officerCaseAssignmentTransferFormValidator;
	
	/* Helpers.	 */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Message Keys */
	
	private static final String OFFICER_CASE_ASSIGNMENT_EXISTS_MESSAGE_KEY =
			"officerCaseAssignment.exists";
	
	private static final String 
			OFFICER_CASE_ASSIGNMENT_EXISTS_WITHIN_DATE_RANGE_MESSAGE_KEY =
			"officerCaseAssignment.existsWithinDateRange";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME = "omis.caseload.msgs.form";
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired	
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Constructors. */
	
	/** 
	 * Instantiates an implementation of manage officer case assignment transfer
	 * controller. 
	 */
	public ManageOfficerCaseAssignmentTransferController() {
		// Default constructor.
	}
	
	/* Screens. */
	
	/**
	 * Shows a screen to transfer officer case assignments from one user to 
	 * another on a specified date.
	 * 
	 * @return model and view
	 */
	@RequestMapping(value = "/transfer.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFICER_CASE_ASSIGNMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit() {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		OfficerCaseAssignmentTransferForm form = 
				new OfficerCaseAssignmentTransferForm();
		mav.addObject(OFFICER_CASE_ASSIGNMENT_TRANSFER_FORM_MODEL_KEY, form);
		return mav;
	}
	
	/**
	 * Shows a screen to transfer officer case assignments from one user to 
	 * another on a specified date.
	 * 
	 * @return model and view
	 * @throws DateConflictException if date conflict exists
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@RequestMapping(value = "/transfer.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('OFFICER_CASE_ASSIGNMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView save(
			final OfficerCaseAssignmentTransferForm 
					officerCaseAssignmentTransferForm,
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		this.officerCaseAssignmentTransferFormValidator.validate(
				officerCaseAssignmentTransferForm, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView(
					EDIT_VIEW_NAME);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ OFFICER_CASE_ASSIGNMENT_TRANSFER_FORM_MODEL_KEY, 
					bindingResult);
			mav.addObject(OFFICER_CASE_ASSIGNMENT_TRANSFER_FORM_MODEL_KEY, 
					officerCaseAssignmentTransferForm);
			return mav;
		}
		List<OfficerCaseAssignment> secondOfficerAssignments = null;
		List<OfficerCaseAssignment> officerCaseAssignments = this
				.officerCaseAssignmentService
				.findOfficerCaseAssignmentsByOfficerAndDate(
						officerCaseAssignmentTransferForm.getUserAccountFrom(),
						officerCaseAssignmentTransferForm.getDate());
		if (officerCaseAssignmentTransferForm.getSwapCaseloads()) {
			secondOfficerAssignments = this.officerCaseAssignmentService
					.findOfficerCaseAssignmentsByOfficerAndDate(
							officerCaseAssignmentTransferForm.getUserAccountTo(),
							officerCaseAssignmentTransferForm.getDate());
		}
		for (OfficerCaseAssignment caseAssignment : officerCaseAssignments) {
			this.officerCaseAssignmentService.createOfficerCaseAssignment(
					caseAssignment.getOffender(), 
					officerCaseAssignmentTransferForm.getUserAccountTo(), 
					officerCaseAssignmentTransferForm.getDate(), 
					caseAssignment.getDateRange().getEndDate(), 
					caseAssignment.getSupervisionOffice(), 
					caseAssignment.getSupervisionLevel());
		}
		if (secondOfficerAssignments != null) {
			for (OfficerCaseAssignment caseAssignment : 
				secondOfficerAssignments) {
				this.officerCaseAssignmentService.createOfficerCaseAssignment(
						caseAssignment.getOffender(), 
						officerCaseAssignmentTransferForm.getUserAccountFrom(), 
						officerCaseAssignmentTransferForm.getDate(), 
						caseAssignment.getDateRange().getEndDate(), 
						caseAssignment.getSupervisionOffice(), 
						caseAssignment.getSupervisionLevel());
			}
		}
		return new ModelAndView(String.format(REDIRECT_URL, 
				officerCaseAssignmentTransferForm.getUserAccountTo().getId(), 
				officerCaseAssignmentTransferForm.getDate()));
	}
	
	/* Action menus. */
	
	/**
	 * Displays action menu for officer case assignments.
	 * 
	 * @param userAccount user account
	 * @param offender offender
	 * @return action menu
	 */
	@RequestMapping(value = "/officerCaseAssignmentTransferActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showActionMenu() {
		final ModelMap modelMap = new ModelMap();
		return new ModelAndView(ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/* Exception Handlers. */
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				OFFICER_CASE_ASSIGNMENT_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
					exception);
	}
	
	/**
	 * Handles date conflict exceptions.
	 * 
	 * @param exception date conflict exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DateConflictException.class)
	public ModelAndView handleDateConflictException(
			final DateConflictException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				OFFICER_CASE_ASSIGNMENT_EXISTS_WITHIN_DATE_RANGE_MESSAGE_KEY, 
				ERROR_BUNDLE_NAME, exception);
	}
	
	/* Init binder. */
	
	/**
	 * Sets up init binder.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(UserAccount.class,
				this.userAccountPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}