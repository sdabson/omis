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
package omis.supervision.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DateRangeOutOfBoundsException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.exception.CorrectionalStatusTermConflictException;
import omis.supervision.exception.PlacementTermExistsException;
import omis.supervision.service.CorrectionalStatusTermService;
import omis.supervision.web.form.CorrectionalStatusTermForm;
import omis.supervision.web.validator.CorrectionalStatusTermFormValidator;
import omis.util.DateManipulator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

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

/**
 * Controller for correctional status terms.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 10, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/supervision/correctionalStatusTerm")
@PreAuthorize("hasRole('USER')")
public class CorrectionalStatusTermController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "supervision/correctionalStatusTerm/edit";
	
	private static final String LIST_VIEW_NAME
		= "supervision/correctionalStatusTerm/list";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/supervision/correctionalStatusTerm/list.html?offender=%d";
	
	/* Model keys. */
	
	private static final String CORRECTIONAL_STATUS_TERMS_MODEL_KEY
		= "correctionalStatusTerms";
	
	private static final String CORRECTIONAL_STATUS_TERM_FORM_MODEL_KEY
		= "correctionalStatusTermForm";

	private static final String CORRECTIONAL_STATUSES_MODEL_KEY
		= "correctionalStatuses";

	private static final String CORRECTIONAL_STATUS_TERM_MODEL_KEY
		= "correctionalStatusTerm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";

	/* Message bundle names. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.supervision.msgs.form";

	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_MESSAGE_KEY
		= "correctionalStatusTerm.exists";

	
	private static final String
	PLACEMENT_TERM_EXISTS_FOR_CORRECTIONAL_STATUS_TERM_MESSAGE_KEY
		= "correctionalStatusTerm.placementTermForWhichExists";

	private static final String
	CONFLICTING_CORRECTIONAL_STATUS_TERM_EXISTS_MESSAGE_KEY
		= "correctionalStatusTerm.conflictsExist";
	
	/* Action menu. */

	private static final String CORRECTIONAL_STATUS_TERMS_ACTION_MENU_VIEW_NAME
		= "supervision/correctionalStatusTerm/includes"
				+ "/correctionalStatusTermsActionMenu";
	
	private static final String CORRECTIONAL_STATUS_TERM_ACTION_MENU_VIEW_NAME
		= "supervision/correctionalStatusTerm/includes"
				+ "/correctionalStatusTermActionMenu";
	
	/* Services. */
	
	@Autowired
	@Qualifier("correctionalStatusTermService")
	private CorrectionalStatusTermService correctionalStatusTermService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("correctionalStatusPropertyEditorFactory")
	private PropertyEditorFactory correctionalStatusPropertyEditorFactory;

	@Autowired
	@Qualifier("correctionalStatusTermPropertyEditorFactory")
	private PropertyEditorFactory correctionalStatusTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("correctionalStatusTermFormValidator")
	private CorrectionalStatusTermFormValidator
	correctionalStatusTermFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor. */
	
	/** Instantiates a controller for correctional status terms. */
	public CorrectionalStatusTermController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Lists correctional status terms for an offender.
	 * 
	 * @param offender offender
	 * @return screen listing correctional status terms
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize(" hasRole('CORRECTIONAL_STATUS_TERM_LIST')"
			+ " or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		List<CorrectionalStatusTerm> correctionalStatusTerms
			= this.correctionalStatusTermService.findByOffender(offender);
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(CORRECTIONAL_STATUS_TERMS_MODEL_KEY,
				correctionalStatusTerms);
		addOffenderSummary(mav, offender);
		return mav;
	}
	
	/**
	 * Shows form to create a new correctional status for offender.
	 * 
	 * @param offender offender for whom to create new correctional status term
	 * @return form to create new correctional status term for offender
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize(" hasRole('CORRECTIONAL_STATUS_TERM_CREATE')"
			+ " or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		return this.prepareEditMav(new CorrectionalStatusTermForm(), offender);
	}
	
	/**
	 * Shows form to update an existing correctional status term.
	 * 
	 * @param correctionalStatusTerm correctional status term to update
	 * @return form to update and existing correctional status term
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CORRECTIONAL_STATUS_TERM_VIEW')"
			+ " or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "correctionalStatusTerm", required = true)
				final CorrectionalStatusTerm correctionalStatusTerm) {
		CorrectionalStatusTermForm correctionalStatusTermForm
			= new CorrectionalStatusTermForm();
		correctionalStatusTermForm.setCorrectionalStatus(
				correctionalStatusTerm.getCorrectionalStatus());
		if (correctionalStatusTerm.getDateRange() != null) {
			correctionalStatusTermForm.setStartDate(
					correctionalStatusTerm.getDateRange().getStartDate());
			correctionalStatusTermForm.setStartTime(
					correctionalStatusTerm.getDateRange().getStartDate());
			correctionalStatusTermForm.setEndDate(
					correctionalStatusTerm.getDateRange().getEndDate());
			correctionalStatusTermForm.setEndTime(
					correctionalStatusTerm.getDateRange().getEndDate());
		}
		ModelAndView mav = this.prepareEditMav(correctionalStatusTermForm,
				correctionalStatusTerm.getOffender());
		mav.addObject(CORRECTIONAL_STATUS_TERM_MODEL_KEY,
				correctionalStatusTerm);
		return mav;
	}
	
	// Prepares edit model and view
	private ModelAndView prepareEditMav(
			final CorrectionalStatusTermForm correctionalStatusTermForm,
			final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(CORRECTIONAL_STATUS_TERM_FORM_MODEL_KEY,
				correctionalStatusTermForm);
		List<CorrectionalStatus> correctionalStatuses
			= this.correctionalStatusTermService.findCorrectionalStatuses();
		mav.addObject(CORRECTIONAL_STATUSES_MODEL_KEY,
				correctionalStatuses);
		addOffenderSummary(mav, offender);
		return mav;	
	}
	
	/**
	 * Creates a new correctional status term for offender.
	 * 
	 * @param offender offender
	 * @param correctionalStatusTermForm correctional status term form 
	 * @param result binding result
	 * @return redirect to list correctional status terms
	 * @throws DuplicateEntityFoundException if the correctional status term
	 * exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize(" hasRole('CORRECTIONAL_STATUS_TERM_CREATE')"
			+ " or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final CorrectionalStatusTermForm correctionalStatusTermForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
						CorrectionalStatusTermConflictException {
		this.correctionalStatusTermFormValidator.validate(
				correctionalStatusTermForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(offender,
					correctionalStatusTermForm, result);
		}
		this.correctionalStatusTermService.create(offender,
				correctionalStatusTermForm.getCorrectionalStatus(),
				this.createDateRange(
						correctionalStatusTermForm.getStartDate(),
						correctionalStatusTermForm.getStartTime(),
						correctionalStatusTermForm.getEndDate(),
						correctionalStatusTermForm.getEndTime()));
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Updates an existing correctional status term.
	 * 
	 * @param correctionalStatusTerm correctional status term to update
	 * @param correctionalStatusTermForm correctional status term form 
	 * @param result binding result
	 * @return redirect to list correctional status terms
	 * @throws DuplicateEntityFoundException if the correctional status term
	 * exists for the offender
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws DateRangeOutOfBoundsException if the associated placement terms
	 * are not within the new date range
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize(" hasRole('CORRECTIONAL_STATUS_TERM_EDIT')"
			+ " or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "correctionalStatusTerm", required = true)
				final CorrectionalStatusTerm correctionalStatusTerm,
			final CorrectionalStatusTermForm correctionalStatusTermForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
						CorrectionalStatusTermConflictException,
						DateRangeOutOfBoundsException {
		this.correctionalStatusTermFormValidator.validate(
				correctionalStatusTermForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
					correctionalStatusTerm.getOffender(),
					correctionalStatusTermForm, result);
			mav.addObject(CORRECTIONAL_STATUS_TERM_MODEL_KEY,
					correctionalStatusTerm); 
			return mav;
		}
		this.correctionalStatusTermService.update(correctionalStatusTerm,
				correctionalStatusTermForm.getCorrectionalStatus(),
				this.createDateRange(
						correctionalStatusTermForm.getStartDate(),
						correctionalStatusTermForm.getStartTime(),
						correctionalStatusTermForm.getEndDate(),
						correctionalStatusTermForm.getEndTime()));
		return new ModelAndView(String.format(LIST_REDIRECT,
				correctionalStatusTerm.getOffender().getId()));
	}
	
	// Prepares redisplay edit model and view
	private ModelAndView prepareRedisplayMav(final Offender offender,
			final CorrectionalStatusTermForm correctionalStatusTermForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(correctionalStatusTermForm,
				offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ CORRECTIONAL_STATUS_TERM_FORM_MODEL_KEY, result);
		return mav;
	}
	
	// Adds offender summary to a model and view
	private void addOffenderSummary(
			final ModelAndView mav, final Offender offender) {
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
	}
	
	/**
	 * Removes a correctional status term.
	 * 
	 * @param correctionalStatusTerm correctional status term to remove
	 * @return redirect to list correctional status terms
	 * @throws PlacementTermExistsException if a placement term uses the
	 * correctional status term
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CORRECTIONAL_STATUS_TERM_REMOVE')"
			+ " or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "correctionalStatusTerm", required = true)
				final CorrectionalStatusTerm correctionalStatusTerm)
						throws PlacementTermExistsException {
		Offender offender = correctionalStatusTerm.getOffender();
		this.correctionalStatusTermService.remove(correctionalStatusTerm);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/* Action menus. */
	
	/**
	 * Shows correctional status terms action menu.
	 * 
	 * @param offender offender
	 * @param correctionalStatusTerm correctional status term
	 * @return correctional status terms action menu
	 */
	@RequestMapping(value = "/correctionalStatusTermsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showCorrectionalStatusTermsActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "correctionalStatusTerm", required = false)
				final CorrectionalStatusTerm correctionalStatusTerm) {
		ModelAndView mav = new ModelAndView(
				CORRECTIONAL_STATUS_TERMS_ACTION_MENU_VIEW_NAME);
		mav.addObject(CORRECTIONAL_STATUS_TERM_MODEL_KEY,
				correctionalStatusTerm);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/**
	 * Shows correctional status term action menu.
	 * 
	 * @param offender offender
	 * @return correctional status term action menu
	 */
	@RequestMapping(value = "/correctionalStatusTermActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showCorrectionalStatusTermActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(
				CORRECTIONAL_STATUS_TERM_ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles {@code DuplicateEntityFoundException}.
	 * 
	 * @param duplicateEntityFoundException exception thrown
	 * @return screen to handle {@code DuplicateEntityFoundException}
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException duplicateEntityFoundException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				DUPLICATE_ENTITY_FOUND_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				duplicateEntityFoundException);
	}
	
	/**
	 * Handles {@code PlacementTermExistsException}.
	 * 
	 * @param placementTermExistsException exception thrown
	 * @return screen to handle {@code PlacementTermExistsException}
	 */
	@ExceptionHandler(PlacementTermExistsException.class)
	public ModelAndView handlePlacementTermExistsException(
			final PlacementTermExistsException
				placementTermExistsException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			PLACEMENT_TERM_EXISTS_FOR_CORRECTIONAL_STATUS_TERM_MESSAGE_KEY,
			ERROR_BUNDLE_NAME, placementTermExistsException);	
	}
	
	@ExceptionHandler(CorrectionalStatusTermConflictException.class)
	public ModelAndView handleCorrectionalStatusTermConflictException(
			final CorrectionalStatusTermConflictException
				correctionalStatusTermConflictException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				CONFLICTING_CORRECTIONAL_STATUS_TERM_EXISTS_MESSAGE_KEY,
				ERROR_BUNDLE_NAME, correctionalStatusTermConflictException);
	}
	
	/* Helper methods. */
	
	// Creates and returns date range
	private DateRange createDateRange(final Date startDate,
			final Date startTime, final Date endDate, final Date endTime) {
		return new DateRange(
				this.calculateDateTime(startDate, startTime),
				this.calculateDateTime(endDate, endTime));
	}
	
	// Adds time to date and returns date - if date is null return null (ignore
	// time) if time is null assume midnight
	private Date calculateDateTime(final Date date, final Date time) {
		if (date == null) {
			return null;
		}
		if (time != null) {
			return DateManipulator.getDateAtTimeOfDay(date, time);
		} else {
			return DateManipulator.getDateAtTimeOfDay(date, new Date(0L));
		}
	}
	
	/* Init binder. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(CorrectionalStatus.class,
				this.correctionalStatusPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(CorrectionalStatusTerm.class,
				this.correctionalStatusTermPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "startDate",
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "startTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "endDate",
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "endTime",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
	}
}