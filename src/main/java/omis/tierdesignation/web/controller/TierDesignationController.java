package omis.tierdesignation.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;
import omis.tierdesignation.service.OffenderTierDesignationService;
import omis.tierdesignation.web.form.TierDesignationForm;
import omis.tierdesignation.web.validator.TierDesignationFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for offender tier designations.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.1 (Dec 12, 2012)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/tierDesignation")
@PreAuthorize("hasRole('USER')")
public class TierDesignationController  {
	
	/* Views. */
	
	private static final String EDIT_VIEW_NAME = "tierDesignation/edit";
	
	private static final String LIST_VIEW_NAME = "tierDesignation/list";
	
	/* Action menus. */
	
	private static final String TIER_DESIGNATIONS_ACTION_MENU_VIEW_NAME
		= "tierDesignation/includes/tierDesignationsActionMenu";
	
	private static final String TIER_DESIGNATION_ACTION_MENU_VIEW_NAME
		= "tierDesignation/includes/tierDesignationActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/tierDesignation/list.html?offender=%d";
	
	/* Model keys. */
	
	private static final String TIER_DESIGNATIONS_MODEL_KEY
		= "tierDesignations";
	
	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String TIER_DESIGNATION_FORM_MODEL_KEY
		= "tierDesignationForm";

	private static final String TIER_SOURCES_MODEL_KEY = "sources";

	private static final String TIER_CHANGE_REASONS_MODEL_KEY = "changeReasons";

	private static final String TIER_LEVELS_MODEL_KEY = "levels";

	private static final String TIER_DESIGNATION_MODEL_KEY = "tierDesignation";

	private static final String CURRENT_DATE_MODEL_KEY = "currentDate";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("offenderTierDesignationService")
	private OffenderTierDesignationService offenderTierDesignationService;
	
	/* Property editor factories. */
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderTierDesignationPropertyEditorFactory")
	private PropertyEditorFactory offenderTierDesignationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("tierChangeReasonPropertyEditorFactory")
	private PropertyEditorFactory tierChangeReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("tierLevelPropertyEditorFactory")
	private PropertyEditorFactory tierLevelPropertyEditorFactory;
	
	@Autowired
	@Qualifier("tierSourcePropertyEditorFactory")
	private PropertyEditorFactory tierSourcePropertyEditorFactory;
	
	/* Instance factories. */
	
	@Autowired
	@Qualifier("offenderTierDesignationInstanceFactory")
	private InstanceFactory<OffenderTierDesignation>
			offenderTierDesignationInstanceFactory;
	

	/* Report names. */
	
	private static final String TIER_DESIGNATION_LISTING_REPORT_NAME 
		= "/Legal/TierDesignations/Tier_Designation_Listing";

	private static final String TIER_DESIGNATION_DETAILS_REPORT_NAME 
		= "/Legal/TierDesignations/Tier_Designation_Details";

	/* Report parameter names. */
	
	private static final String TIER_DESIGNATION_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String TIER_DESIGNATION_DETAILS_ID_REPORT_PARAM_NAME 
		= "TD_ID";
	
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
	@Qualifier("tierDesignationFormValidator")
	private TierDesignationFormValidator tierDesignationFormValidator;
	
	/**
	 * Shows a list of tier designation for the offender.
	 * 
	 * @param offender offender
	 * @return model and view to a list of offender tier designations
	 */
	@RequestContentMapping(nameKey = "tierDesignationListingScreenName",
			descriptionKey = "tierDesignationListingScreenDescription",
			messageBundle = "omis.tierdesignation.msgs.tierdesignation",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping("/list.html")
	@PreAuthorize("hasRole('TIER_DESIGNATION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<OffenderTierDesignation> tierDesignations
			= this.offenderTierDesignationService.findByOffender(offender);
		mav.addObject(TIER_DESIGNATIONS_MODEL_KEY, tierDesignations);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		Date currentDate = new Date();
		mav.addObject(CURRENT_DATE_MODEL_KEY, currentDate);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Shows a screen that allows a new offender tier designation to be created.
	 * 
	 * @param offender offender
	 * @return model and view to screen that allows new offender tier
	 * designation to be created
	 */
	@RequestContentMapping(nameKey = "tierDesignationCreateScreenName",
			descriptionKey = "tierDesignationCreateScreenDescription",
			messageBundle = "omis.tierdesignation.msgs.tierdesignation",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('TIER_DESIGNATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		return prepareEditMav(new TierDesignationForm(), offender);
	}
	
	/**
	 * Shows a screen that allows an existing offender tier designation to be
	 * edited.
	 * 
	 * @param tierDesignation offender tier designation to edit
	 * @return model and view to screen that allows existing offender tier
	 * designation to be edited
	 */
	@RequestContentMapping(nameKey = "tierDesignationEditScreenName",
			descriptionKey = "tierDesignationEditScreenDescription",
			messageBundle = "omis.tierdesignation.msgs.tierdesignation",
			screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("hasRole('TIER_DESIGNATION_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "tierDesignation", required = true)
				final OffenderTierDesignation tierDesignation) {
		TierDesignationForm tierDesignationForm
			= new TierDesignationForm();
		tierDesignationForm.setComment(
				tierDesignation.getComment());
		if (tierDesignation.getDateRange() != null) {
			tierDesignationForm.setStartDate(
				tierDesignation.getDateRange().getStartDate());
			tierDesignationForm.setEndDate(
					tierDesignation.getDateRange().getEndDate());
		}
		tierDesignationForm.setChangeReason(
				tierDesignation.getChangeReason());
		tierDesignationForm.setLevel(
				tierDesignation.getLevel());
		tierDesignationForm.setSource(
				tierDesignation.getSource());
		ModelAndView mav = prepareEditMav(tierDesignationForm,
				tierDesignation.getOffender());
		mav.addObject(TIER_DESIGNATION_MODEL_KEY, tierDesignation);
		return mav;
	}
	
	// Prepares edit model and view
	private ModelAndView prepareEditMav(
			final TierDesignationForm form,
			final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(TIER_DESIGNATION_FORM_MODEL_KEY, form);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(TIER_SOURCES_MODEL_KEY,
				this.offenderTierDesignationService.findSources());
		mav.addObject(TIER_CHANGE_REASONS_MODEL_KEY,
				this.offenderTierDesignationService.findChangeReasons());
		mav.addObject(TIER_LEVELS_MODEL_KEY,
				this.offenderTierDesignationService.findLevels());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Saves a new offender tier designation.
	 * 
	 * @param offender offender
	 * @param tierDesignationForm form for tier designations
	 * @param result binding result
	 * @return redirect to list URL
	 * @throws DuplicateEntityFoundException if the tier designation already
	 * exists
	 */
	@RequestContentMapping(nameKey = "tierDesignationCreateSubmitName",
			descriptionKey = "tierDesignationCreateSubmitDescription",
			messageBundle = "omis.tierdesignation.msgs.tierdesignation",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('TIER_DESIGNATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final TierDesignationForm tierDesignationForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.tierDesignationFormValidator.validate(tierDesignationForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(tierDesignationForm, offender,
					result);
		}
		OffenderTierDesignation tierDesignation
			= this.offenderTierDesignationService
				.save(offender, tierDesignationForm.getLevel(),
						tierDesignationForm.getSource(),
						tierDesignationForm.getChangeReason(),
						new DateRange(
								tierDesignationForm.getStartDate(),
								tierDesignationForm.getEndDate()),
						tierDesignationForm.getComment());
		return prepareRedirectMav(tierDesignation.getOffender());
	}
	
	/**
	 * Saves changes to an existing offender tier designation.
	 * 
	 * @param tierDesignation offender tier designation to update
	 * @param tierDesignationForm form for tier designations
	 * @param result binding result
	 * @return redirect to list URL
	 * @throws DuplicateEntityFoundException if the tier designation already
	 * exists
	 */
	@RequestContentMapping(nameKey = "tierDesignationEditSubmitName",
			descriptionKey = "tierDesignationEditSubmitDescription",
			messageBundle = "omis.tierdesignation.msgs.tierdesignation",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('TIER_DESIGNATION_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "tierDesignation", required = true)
				final OffenderTierDesignation tierDesignation,
			final TierDesignationForm tierDesignationForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.tierDesignationFormValidator.validate(tierDesignationForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(tierDesignationForm,
					tierDesignation.getOffender(), result);
			mav.addObject(TIER_DESIGNATION_MODEL_KEY, tierDesignation);
			return mav;
		}
		this.offenderTierDesignationService
			.update(tierDesignation, tierDesignationForm.getLevel(),
				tierDesignationForm.getSource(),
				tierDesignationForm.getChangeReason(),
				new DateRange(
						tierDesignationForm.getStartDate(),
						tierDesignationForm.getEndDate()),
				tierDesignationForm.getComment());
		return prepareRedirectMav(tierDesignation.getOffender());
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for tier designations.
	 * 
	 * @param offender offender
	 * @param tierDesignation tier designation
	 * @return action menu for tier designation
	 */
	@RequestMapping(value = "/tierDesignationsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showTierDesignationsActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "tierDesignation", required = false)
				final OffenderTierDesignation tierDesignation) {
		ModelAndView mav = new ModelAndView(
				TIER_DESIGNATIONS_ACTION_MENU_VIEW_NAME);
		if (offender != null) {
			mav.addObject(OFFENDER_MODEL_KEY, offender);
		}
		if (tierDesignation != null) {
			mav.addObject(TIER_DESIGNATION_MODEL_KEY, tierDesignation);
		}
		return mav;
	}
	
	/**
	 * Shows action menu for tier designation.
	 * 
	 * @param offender offender
	 * @return action menu for tier designation
	 */
	@RequestMapping(value = "/tierDesignationActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showTierDesignationActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(
				TIER_DESIGNATION_ACTION_MENU_VIEW_NAME);
		if (offender != null) {
			mav.addObject(OFFENDER_MODEL_KEY, offender);
		}
		return mav;
	}
	
	// Prepares model and view to redisplay edit screen
	private ModelAndView prepareRedisplayMav(
			final TierDesignationForm tierDesignationForm,
			final Offender offender,
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(tierDesignationForm, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ TIER_DESIGNATION_FORM_MODEL_KEY, result);
		return mav;
	}
	
	// Returns model and view to redirect to listing screen
	private ModelAndView prepareRedirectMav(final Offender offender) {
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Removes an offender tier designation.
	 * 
	 * @param tierDesignation offender tier designation to remove
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "tierDesignationRemoveName",
			descriptionKey = "tierDesignationRemoveDescription",
			messageBundle = "omis.tierdesignation.msgs.tierdesignation",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html")
	@PreAuthorize("hasRole('TIER_DESIGNATION_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "tierDesignation", required = true)
				final OffenderTierDesignation tierDesignation) {
		Offender offender = tierDesignation.getOffender();
		this.offenderTierDesignationService.remove(tierDesignation);
		return this.prepareRedirectMav(offender);
	}
	
	/**
	 * Returns the report for the specified offenders tier designations.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/tierDesignationListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('TIER_DESIGNATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportTierDesignationListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(TIER_DESIGNATION_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				TIER_DESIGNATION_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified tier designation.
	 * 
	 * @param tierDesignation tier designation
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/tierDesignationDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('TIER_DESIGNATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportTierDesignationDetails(@RequestParam(
			value = "tierDesignation", required = true)
			final OffenderTierDesignation tierDesignation,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(TIER_DESIGNATION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(tierDesignation.getId()));
		byte[] doc = this.reportRunner.runReport(
				TIER_DESIGNATION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(OffenderTierDesignation.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(TierChangeReason.class,
				this.tierChangeReasonPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(TierLevel.class,
				this.tierLevelPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(TierSource.class,
				this.tierSourcePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(OffenderTierDesignation.class,
				this.offenderTierDesignationPropertyEditorFactory
					.createPropertyEditor());
	}
}