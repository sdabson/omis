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
package omis.caution.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.caution.domain.CautionDescription;
import omis.caution.domain.CautionSource;
import omis.caution.domain.OffenderCaution;
import omis.caution.service.OffenderCautionService;
import omis.caution.web.form.CautionForm;
import omis.caution.web.validator.CautionFormValidator;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Cautions controller.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.5 (February 24, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/caution")
@PreAuthorize("hasRole('USER')")
public class CautionController {
	
	/* Model keys. */
	
	private static final String CAUTION_MODEL_KEY = "caution";

	private static final String CAUTIONS_MODEL_KEY = "cautions";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String CAUTION_FORM_MODEL_KEY = "cautionForm";

	private static final String SOURCES_MODEL_KEY = "sources";

	private static final String DESCRIPTIONS_MODEL_KEY = "descriptions";
	
	private static final String CURRENT_DATE_MODEL_KEY = "currentDate";

	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "caution/edit";

	private static final String LIST_VIEW_NAME = "caution/list";
	
	private static final String CAUTIONS_ACTION_MENU_VIEW_NAME
		= "caution/includes/cautionsActionMenu";
	
	private static final String CAUTION_ACTION_MENU_VIEW_NAME
		= "caution/includes/cautionActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT_VIEW_NAME
		= "redirect:/caution/list.html?offender=%d";
	
	/* Message keys. */
	
	private static final String CAUTION_EXISTS_MESSAGE_KEY
		= "caution.exists";
	
	/* Report names. */
	
	private static final String CAUTION_LISTING_REPORT_NAME 
		= "/Safety/Cautions/Caution_Listing";

	private static final String CAUTION_DETAILS_REPORT_NAME 
		= "/Safety/Cautions/Caution_Details";

	/* Report parameter names. */
	
	private static final String CAUTION_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String CAUTION_DETAILS_ID_REPORT_PARAM_NAME 
		= "CAUTION_ID";
	
	/* Message bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.caution.msgs.form";

	/* Services. */	
	
	@Autowired
	@Qualifier("offenderCautionService")
	private OffenderCautionService offenderCautionService;

	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("cautionDescriptionPropertyEditorFactory")
	private PropertyEditorFactory cautionDescriptionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("cautionSourcePropertyEditorFactory")
	private PropertyEditorFactory cautionSourcePropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderCautionPropertyEditorFactory")
	private PropertyEditorFactory offenderCautionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("cautionFormValidator")
	private CautionFormValidator cautionFormValidator;
	
	/* Constructor. */
	
	/** Instantiates a default controller for cautions. */
	public CautionController() {
		// Default instantiation
	}
	
	/* Screens. */
	
	/**
	 * Displays a list of cautions for an offender.
	 * 
	 * @param offender offender whose cautions to list
	 * @return model and view to a list of cautions
	 */
	@RequestContentMapping(nameKey = "cautionListingScreenName",
			descriptionKey = "cautionListingScreenDescription",
			messageBundle = "omis.caution.msgs.caution",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CAUTION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<OffenderCaution> cautions = this.offenderCautionService
													.findByOffender(offender);
		mav.addObject(CAUTIONS_MODEL_KEY, cautions);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		Date currentDate = new Date();
		mav.addObject(CURRENT_DATE_MODEL_KEY, currentDate);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays a form allowing a new caution to be created.
	 * 
	 * @param offender offender from whom to create a new caution
	 * @return model and view to screen that allows new caution to be created
	 */
	@RequestContentMapping(nameKey = "cautionCreateScreenName",
			descriptionKey = "cautionCreateScreenDescription",
			messageBundle = "omis.caution.msgs.caution",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CAUTION_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		CautionForm cautionForm = new CautionForm();
		return this.prepareEditMav(cautionForm, offender);
	}
	
	/**
	 * Displays a form allowing an existing caution to be edited.
	 * 
	 * @param caution caution to allow to be edited
	 * @return model and view to screen that allows existing caution to be
	 * edited
	 */
	@RequestContentMapping(nameKey = "cautionEditScreenName",
			descriptionKey = "cautionEditScreenDescription",
			messageBundle = "omis.caution.msgs.caution",
			screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("hasRole('CAUTION_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html",
		method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "caution", required = true)
				final OffenderCaution caution) {
		CautionForm cautionForm = new CautionForm();
		cautionForm.setSource(caution.getSource());
		cautionForm.setSourceComment(caution.getSourceComment());
		cautionForm.setDescription(caution.getDescription());
		cautionForm.setComment(caution.getComment());
		cautionForm.setStartDate(caution.getDateRange().getStartDate());
		cautionForm.setEndDate(caution.getDateRange().getEndDate());
		ModelAndView mav = this.prepareEditMav(
				cautionForm, caution.getOffender());
		mav.addObject(CAUTION_MODEL_KEY, caution);
		return mav;
	}
	
	// Returns a model and view suitable for editing the specified caution
	private ModelAndView prepareEditMav(
			final CautionForm cautionForm,
			final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(CAUTION_FORM_MODEL_KEY, cautionForm);
		mav.addObject(SOURCES_MODEL_KEY,
				this.offenderCautionService.findSources());
		mav.addObject(DESCRIPTIONS_MODEL_KEY,
				this.offenderCautionService.findDescriptions());
		mav.addObject(OFFENDER_MODEL_KEY , offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Saves a new caution.
	 * 
	 * @param offender offender for whom to create the new caution
	 * @param cautionForm form containing caution information
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DuplicateEntityFoundException if an attempt to save a duplicate
	 * caution is attempted  
	 */
	@RequestContentMapping(nameKey = "cautionCreateSubmitName",
			descriptionKey = "cautionCreateSubmitDescription",
			messageBundle = "omis.caution.msgs.caution",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('CAUTION_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			final CautionForm cautionForm,
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.cautionFormValidator.validate(cautionForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(cautionForm, offender);
			mav.addObject(
						BindingResult.MODEL_KEY_PREFIX
							+ CAUTION_FORM_MODEL_KEY, result);
			return mav;
		}
		OffenderCaution caution = this.offenderCautionService
				.save(offender, cautionForm.getDescription(),
						cautionForm.getSource(),
						new DateRange(cautionForm.getStartDate(),
								cautionForm.getEndDate()),
						cautionForm.getComment(),
						cautionForm.getSourceComment());
		return this.prepareListRedirect(caution.getOffender());
	}
	
	/**
	 * Updates an existing caution.
	 * 
	 * @param caution caution to update
	 * @param cautionForm form containing caution information
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DuplicateEntityFoundException if an attempt to save a duplicate
	 * caution is attempted
	 */
	@RequestContentMapping(nameKey = "cautionEditSubmitName",
			descriptionKey = "cautionEditSubmitDescription",
			messageBundle = "omis.caution.msgs.caution",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CAUTION_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "caution", required = true)
				final OffenderCaution caution,
			final CautionForm cautionForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.cautionFormValidator.validate(cautionForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(cautionForm,
					caution.getOffender());
			mav.addObject(
					BindingResult.MODEL_KEY_PREFIX + CAUTION_FORM_MODEL_KEY,
						result);
			mav.addObject(CAUTION_MODEL_KEY, caution);
			return mav;
		}
		OffenderCaution updatedCaution = this.offenderCautionService
				.update(caution, cautionForm.getDescription(),
						cautionForm.getSource(),
						new DateRange(cautionForm.getStartDate(),
								cautionForm.getEndDate()),
						cautionForm.getComment(),
						cautionForm.getSourceComment());
		return this.prepareListRedirect(updatedCaution.getOffender());
	}
	
	// Prepares cautions screen redirect
	private ModelAndView prepareListRedirect(
			final Offender offender) {
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME,
				offender.getId()));
	}
	
	/**
	 * Removes a caution.
	 * 
	 * @param caution caution to remove
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "cautionRemoveName",
			descriptionKey = "cautionRemoveDescription",
			messageBundle = "omis.caution.msgs.caution",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html")
	@PreAuthorize("hasRole('CAUTION_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "caution", required = true)
				final OffenderCaution caution) {
		Offender offender = caution.getOffender();
		this.offenderCautionService.remove(caution);
		return this.prepareListRedirect(offender);
	}
	
	/**
	 * Returns the content for the cautions action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the cautions action menu
	 */
	@RequestMapping(value = "cautionsActionMenu.html")
	public ModelAndView cautionsActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "caution", required = false)
				final OffenderCaution caution) {
		ModelMap map = new ModelMap();
		if (offender != null) {
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
		}
		if (caution != null) {
			map.addAttribute(CAUTION_MODEL_KEY, caution);
		}
		return new ModelAndView(CAUTIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the content for the caution action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the caution action menu
	 */
	@RequestMapping(value = "cautionActionMenu.html")
	public ModelAndView cautionActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(CAUTION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the report for the specified offenders cautions.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/cautionListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CAUTION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCautionListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CAUTION_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				CAUTION_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified caution.
	 * 
	 * @param offenderCaution offender caution
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/cautionDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CAUTION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCautionDetails(@RequestParam(
			value = "caution", required = true)
			final OffenderCaution offenderCaution,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CAUTION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(offenderCaution.getId()));
		byte[] doc = this.reportRunner.runReport(
				CAUTION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Exception handler methods. */
	
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
				CAUTION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Init binder. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(CautionSource.class,
					this.cautionSourcePropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(CautionDescription.class,
					this.cautionDescriptionPropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(OffenderCaution.class,
					this.offenderCautionPropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
					this.customDateEditorFactory
						.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Offender.class,
					this.offenderPropertyEditorFactory
						.createOffenderPropertyEditor());
	}
}