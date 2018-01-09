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
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.exception.PlacementTermExistsException;
import omis.supervision.exception.SupervisoryOrganizationTermConflictException;
import omis.supervision.service.SupervisoryOrganizationTermService;
import omis.supervision.web.form.SupervisoryOrganizationTermForm;
import omis.supervision.web.validator.SupervisoryOrganizationTermFormValidator;
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
 * Controller for supervisory organization terms.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 10, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/supervision/supervisoryOrganizationTerm")
@PreAuthorize("hasRole('USER')")
public class SupervisoryOrganizationTermController {
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME
		= "supervision/supervisoryOrganizationTerm/list";
	
	private static final String EDIT_VIEW_NAME
		= "supervision/supervisoryOrganizationTerm/edit";
	
	private static final String LIST_REDIRECT
		= "redirect:/supervision/supervisoryOrganizationTerm/list.html"
				+ "?offender=%d";
	
	/* Action menu view names. */
	
	private static final String
	SUPERVISORY_ORGANIZATION_TERM_ACTION_MENU_VIEW_NAME
		= "supervision/supervisoryOrganizationTerm/includes"
				+ "/supervisoryOrganizationTermActionMenu";

	private static final String
	SUPERVISORY_ORGANZIZATION_TERMS_ACTION_MENU_VIEW_NAME
	= "supervision/supervisoryOrganizationTerm/includes"
			+ "/supervisoryOrganizationTermsActionMenu";

	/* Model keys. */
	
	private static final String SUPERVISORY_ORGANIZATION_TERMS_MODEL_KEY
		= "supervisoryOrganizationTerms";
	
	private static final String SUPERVISORY_ORGANIZATIONS_MODEL_KEY
		= "supervisoryOrganizations";

	private static final String SUPERVISORY_ORGNAIZATION_TERM_MODEL_KEY
		= "supervisoryOrganizationTerm";

	private static final String SUPERVISORY_ORGANIZATION_TERM_FORM_MODEL_KEY
		= "supervisoryOrganizationTermForm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String SUPERVISORY_ORGANIZATION_TERM_MODEL_KEY
		= "supervisoryOrganizationTerm";

	/* Message bundle names. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.supervision.msgs.form";

	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_MESSAGE_KEY
		= "supervisoryOrganizationTerm.exists";

	private static final String
	PLACEMENT_TERM_EXISTS_FOR_SUPERVISORY_ORGANIZATION_TERM_MESSAGE_KEY
		= "supervisoryOrganizationTerm.placementTermForWhichExists";

	private static final String
	CONFLICTING_SUPERVISORY_ORGANIZATION_TERM_EXISTS_MESSAGE_KEY
		= "supervisoryOrganizationTerm.conflictsExist";
	
	/* Services. */
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermService")
	private SupervisoryOrganizationTermService
	supervisoryOrganizationTermService;

	/* Report services. */
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisoryOrganizationPropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationPropertyEditorFactory;

	@Autowired
	@Qualifier("supervisoryOrganizationTermPropertyEditorFactory")
	private PropertyEditorFactory
	supervisoryOrganizationTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermFormValidator")
	private SupervisoryOrganizationTermFormValidator
	supervisoryOrganizationTermFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor. */
	
	/** Instantiates a default controller for supervisory organizations. */
	public SupervisoryOrganizationTermController() {
		// Default instantiation
	}

	/* URL invokable methods. */
	
	/**
	 * Lists supervisory organization terms for an offender.
	 * 
	 * @param offender offender
	 * @return screen listing supervisory organization terms for offender
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize(" hasRole('SUPERVISORY_ORGANIZATION_TERM_LIST')"
			+ " or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		List<SupervisoryOrganizationTerm> supervisoryOrganizationTerms
			= this.supervisoryOrganizationTermService.findByOffender(offender);
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(SUPERVISORY_ORGANIZATION_TERMS_MODEL_KEY,
				supervisoryOrganizationTerms);
		addOffenderSummary(mav, offender);
		return mav;
	}
	
	/**
	 * Shows form to create a new placement term for offender.
	 * 
	 * @param offender offender for whom to create new supervisory organization
	 * term
	 * @return form to create new supervisory organization term for offender
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize(" hasRole('SUPERVISORY_ORGANIZATION_TERM_CREATE')"
			+ " or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		return this.prepareEditMav(
				new SupervisoryOrganizationTermForm(), offender);
	}
	
	/**
	 * Shows form to update an existing supervisory organization term.
	 * 
	 * @param supervisoryOrganizationTerm supervisory organization term to
	 * update
	 * @return form to update an existing supervisory organization term
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISORY_ORGANIZATION_TERM_VIEW')"
			+ " or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "supervisoryOrganizationTerm",
					required = true)
				final SupervisoryOrganizationTerm supervisoryOrganizationTerm) {
		SupervisoryOrganizationTermForm supervisoryOrganizationTermForm
			= new SupervisoryOrganizationTermForm();
		supervisoryOrganizationTermForm.setSupervisoryOrganization(
				supervisoryOrganizationTerm.getSupervisoryOrganization());
		if (supervisoryOrganizationTerm.getDateRange() != null) {
			supervisoryOrganizationTermForm.setStartDate(
					supervisoryOrganizationTerm.getDateRange().getStartDate());
			supervisoryOrganizationTermForm.setStartTime(
					supervisoryOrganizationTerm.getDateRange().getStartDate());
			supervisoryOrganizationTermForm.setEndDate(
					supervisoryOrganizationTerm.getDateRange().getEndDate());
			supervisoryOrganizationTermForm.setEndTime(
					supervisoryOrganizationTerm.getDateRange().getEndDate());
		}
		ModelAndView mav = this.prepareEditMav(supervisoryOrganizationTermForm,
				supervisoryOrganizationTerm.getOffender());
		mav.addObject(SUPERVISORY_ORGNAIZATION_TERM_MODEL_KEY,
				supervisoryOrganizationTerm);
		return mav;
	}
	
	// Prepares edit model and view
	private ModelAndView prepareEditMav(
			final SupervisoryOrganizationTermForm
				supervisoryOrganizationTermForm,
			final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(SUPERVISORY_ORGANIZATION_TERM_FORM_MODEL_KEY,
				supervisoryOrganizationTermForm);
		List<SupervisoryOrganization> supervisoryOrganizations
			= this.supervisoryOrganizationTermService
				.findSupervisoryOrganizations();
		mav.addObject(SUPERVISORY_ORGANIZATIONS_MODEL_KEY,
				supervisoryOrganizations);
		addOffenderSummary(mav, offender);
		return mav;
	}
	
	/**
	 * Creates a new supervisory organization term for offender.
	 * 
	 * @param offender offender 
	 * @param supervisoryOrganizationTermForm supervisory organization term form
	 * @param result binding result
	 * @return redirect to list supervisory organization terms
	 * @throws DuplicateEntityFoundException if the supervisory organization
	 * term exists
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize(" hasRole('SUPERVISORY_ORGANIZATION_TERM_CREATE')"
			+ " or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final SupervisoryOrganizationTermForm
				supervisoryOrganizationTermForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
						SupervisoryOrganizationTermConflictException {
		this.supervisoryOrganizationTermFormValidator.validate(
				supervisoryOrganizationTermForm, result);
		if (result.hasErrors()) {
			return this.prepareRedispayEditMav(offender,
					supervisoryOrganizationTermForm, result);
		}
		this.supervisoryOrganizationTermService.create(offender,
				supervisoryOrganizationTermForm.getSupervisoryOrganization(),
					this.createDateRange(
							supervisoryOrganizationTermForm.getStartDate(),
							supervisoryOrganizationTermForm.getStartTime(),
							supervisoryOrganizationTermForm.getEndDate(),
							supervisoryOrganizationTermForm.getEndTime()));
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Updates an existing supervisory organization term.
	 * 
	 * @param supervisoryOrganizationTerm supervisory organization term
	 * @param supervisoryOrganizationTermForm supervisory organization term form
	 * @param result binding result
	 * @return redirect to list supervisory organization terms
	 * @throws DuplicateEntityFoundException if the supervisory organization
	 * term exists
	 * @throws DateRangeOutOfBoundsException if the associated placement terms
	 * are not within the new date range
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize(" hasRole('SUPERVISORY_ORGANIZATION_TERM_EDIT')"
			+ " or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "supervisoryOrganizationTerm",
					required = true)
				final SupervisoryOrganizationTerm supervisoryOrganizationTerm,
			final SupervisoryOrganizationTermForm
				supervisoryOrganizationTermForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
					DateRangeOutOfBoundsException,
					SupervisoryOrganizationTermConflictException {
		this.supervisoryOrganizationTermFormValidator.validate(
				supervisoryOrganizationTermForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedispayEditMav(
					supervisoryOrganizationTerm.getOffender(),
					supervisoryOrganizationTermForm, result);
			mav.addObject(SUPERVISORY_ORGNAIZATION_TERM_MODEL_KEY,
					supervisoryOrganizationTerm);
			return mav;
		}
		this.supervisoryOrganizationTermService.update(
				supervisoryOrganizationTerm,
				supervisoryOrganizationTermForm.getSupervisoryOrganization(),
					this.createDateRange(
							supervisoryOrganizationTermForm.getStartDate(),
							supervisoryOrganizationTermForm.getStartTime(),
							supervisoryOrganizationTermForm.getEndDate(),
							supervisoryOrganizationTermForm.getEndTime()));
		return new ModelAndView(String.format(LIST_REDIRECT,
				supervisoryOrganizationTerm.getOffender().getId()));
	}
	
	// Prepares redisplay edit model and view
	private ModelAndView prepareRedispayEditMav(final Offender offender,
			final SupervisoryOrganizationTermForm
				supervisoryOrganizationTermForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(supervisoryOrganizationTermForm,
				offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ SUPERVISORY_ORGANIZATION_TERM_FORM_MODEL_KEY, result);
		return mav;
	}

	// Adds offender summary to a model and view
	private void addOffenderSummary(
			final ModelAndView mav, final Offender offender) {
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
	}
	
	/**
	 * Removes a supervisory organization term.
	 * 
	 * @param supervisoryOrganizationTerm supervisory organization term to
	 * remove
	 * @return redirect to list supervisory organization terms
	 * @throws PlacementTermExistsException if a placement term uses the
	 * supervisory organization term
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISORY_ORGANIZATION_TERM_REMOVE')"
			+ " or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "supervisoryOrganizationTerm",
					required = true)
				final SupervisoryOrganizationTerm supervisoryOrganizationTerm)
						throws PlacementTermExistsException {
		Offender offender = supervisoryOrganizationTerm.getOffender();
		this.supervisoryOrganizationTermService
			.remove(supervisoryOrganizationTerm);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
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
		PLACEMENT_TERM_EXISTS_FOR_SUPERVISORY_ORGANIZATION_TERM_MESSAGE_KEY,
				ERROR_BUNDLE_NAME, placementTermExistsException);
	}
	
	/**
	 * Handles {@code SupervisoryOrganizationTermConflictException}.
	 * 
	 * @param supervisoryOrganizationTermConflictException exception thrown
	 * @return screen to handle
	 * {@code SupervisoryOrganizationTermConflictException}
	 */
	@ExceptionHandler(SupervisoryOrganizationTermConflictException.class)
	public ModelAndView handleSupervisoryOrganizationTermConflictException(
			final SupervisoryOrganizationTermConflictException
				supervisoryOrganizationTermConflictException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				CONFLICTING_SUPERVISORY_ORGANIZATION_TERM_EXISTS_MESSAGE_KEY,
				ERROR_BUNDLE_NAME,
				supervisoryOrganizationTermConflictException);
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for supervisory organization term.
	 * 
	 * @param offender offender
	 * @return action menu for supervisory organization term
	 */
	@RequestMapping(
			value = "/supervisoryOrganizationTermActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showSupervisoryOrganizaionTermActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(
				SUPERVISORY_ORGANIZATION_TERM_ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/**
	 * Shows action menu for supervisory organization terms.
	 * 
	 * @param offender offender
	 * @param supervisoryOrganizationTerm supervisory organization term
	 * @return action menu for supervisory organization terms
	 */
	@RequestMapping(
			value = "/supervisoryOrganizationTermsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showSupervisoryOrganizationsTermActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(
					value = "supervisoryOrganizationTerm", required = false)
				final SupervisoryOrganizationTerm supervisoryOrganizationTerm) {
		ModelAndView mav = new ModelAndView(
				SUPERVISORY_ORGANZIZATION_TERMS_ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(SUPERVISORY_ORGANIZATION_TERM_MODEL_KEY,
				supervisoryOrganizationTerm);
		return mav;
	}
	
	/* Helper methods .*/
	
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
		binder.registerCustomEditor(SupervisoryOrganization.class,
				this.supervisoryOrganizationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SupervisoryOrganizationTerm.class,
				this.supervisoryOrganizationTermPropertyEditorFactory
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