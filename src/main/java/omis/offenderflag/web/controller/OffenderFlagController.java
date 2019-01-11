package omis.offenderflag.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.exception.BusinessException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offenderflag.domain.OffenderFlag;
import omis.offenderflag.domain.OffenderFlagCategory;
import omis.offenderflag.service.OffenderFlagService;
import omis.offenderflag.web.form.OffenderFlagForm;
import omis.offenderflag.web.form.OffenderFlagItem;
import omis.offenderflag.web.form.OffenderFlagItemValue;
import omis.offenderflag.web.validator.OffenderFlagFormValidator;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.UserAccountService;

/**
 * Controller for offender flags.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @author Sierra Haynes
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/offenderFlag")
@PreAuthorize("hasRole('USER')")
public class OffenderFlagController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "offenderFlag/edit";
	
	private static final String OFFENDER_FLAG_ACTION_MENU_VIEW_NAME
		= "offenderFlag/includes/offenderFlagActionMenu";
	
	/* Redirects. */
	
	private static final String EDIT_REDIRECT
		= "redirect:/offenderFlag/edit.html?offender=%d";
	
	/* Model keys. */
	
	private static final String OFFENDER_FLAG_FORM_MODEL_KEY
		= "offenderFlagForm";

	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenderFlagService")
	private OffenderFlagService offenderFlagService;
	
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderFlagCategoryPropertyEditorFactory")
	private PropertyEditorFactory offenderFlagCategoryPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("offenderFlagFormValidator")
	private OffenderFlagFormValidator offenderFlagFormValidator;

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
    /* Report names. */
	
	private static final String OFFENDER_FLAG_LISTING_REPORT_NAME
	    = "/BasicInformation/Flags/Offender_Flag_Details";
	
	private static final String SVO_REGISTRATION_REPORT_NAME
	    = "/BasicInformation/Flags/SVOR_Registration_Form";
	
	/* Report parameters. */
	
	private static final String OFFENDER_FLAG_LISTING_ID_REPORT_PARAM_NAME
	   = "DOC_ID";	
	
	/* Report runners. */	
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructor. */
	
	/** Instantiates a controller for offender flags. */
	public OffenderFlagController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays form to update flags for offender.
	 * 
	 * @param offender offender the flags of whom to update
	 * @return form to update flags for offender 
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_FLAG_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		OffenderFlagForm offenderFlagForm = new OffenderFlagForm();
		List<OffenderFlagCategory> categories
			= this.offenderFlagService.findCategories();
		for (OffenderFlagCategory category : categories) {
			OffenderFlag flag = this.offenderFlagService
				.find(offender, category);
			OffenderFlagItemValue value;
			if (flag != null) {
				if (flag.getValue() != null) {
					if (flag.getValue()) {
						value = OffenderFlagItemValue.YES;
					} else {
						value = OffenderFlagItemValue.NO;
					}
				} else {
					value = OffenderFlagItemValue.UNKNOWN;
				}
			} else {
				value = OffenderFlagItemValue.NOT_SET;
			}
			OffenderFlagItem offenderFlagItem = new OffenderFlagItem(
					category, value);
			offenderFlagForm.getFlagItems().add(offenderFlagItem);
		}
		ModelAndView mav = this.prepareEditMav(offender, offenderFlagForm);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}

	/**
	 * Update offender flags.
	 * 
	 * @param offender offender the flags of whom to update
	 * @param offenderFlagForm form of offender flags
	 * @param result binding result
	 * @return redirect to display form to update flags for offender
	 * @throws BusinessException if the flags are not valid
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('OFFENDER_FLAG_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final OffenderFlagForm offenderFlagForm,
			final BindingResult result)
				throws BusinessException {
		this.offenderFlagFormValidator.validate(offenderFlagForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(offender, offenderFlagForm, result);
		}
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext().getAuthentication()
					.getName());
		Date currentDate = new Date();
		for (OffenderFlagItem flagItem : offenderFlagForm.getFlagItems()) {
			if (OffenderFlagItemValue.YES.equals(flagItem.getValue())) {
				this.offenderFlagService.set(offender, flagItem.getCategory(),
						true, userAccount, currentDate);
						
			} else if (OffenderFlagItemValue.NO.equals(flagItem.getValue())) {
				this.offenderFlagService.set(offender, flagItem.getCategory(),
						false, userAccount, currentDate);
			} else if (OffenderFlagItemValue.UNKNOWN
					.equals(flagItem.getValue())) {
				this.offenderFlagService.set(offender, flagItem.getCategory(),
						null, userAccount, currentDate);
			} else if (OffenderFlagItemValue.NOT_SET
					.equals(flagItem.getValue())) {
				this.offenderFlagService.unset(offender,
						flagItem.getCategory());
			} else {
				throw new IllegalArgumentException(
						"Not supported or null value: " + flagItem.getValue());
			}
		}
		if (!this.offenderFlagService.hasRequiredFlags(offender)) {
			throw new BusinessException("Required flags missing");
		}
		return new ModelAndView(String.format(EDIT_REDIRECT, offender.getId()));
	}
	
	/* Action Menu methods. */
	
	@RequestMapping(value = "/offenderFlagActionMenu.html",
			method = RequestMethod.GET)
	private ModelAndView offenderFlagActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(OFFENDER_FLAG_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the report for the specified offenders flags
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/offenderFlagsListingReport.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_FLAG_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportOffenderFlagListing(@RequestParam(
		value = "offender", required = true)
		final Offender offender,
		@RequestParam(value = "reportFormat", required = true)
		final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(OFFENDER_FLAG_LISTING_ID_REPORT_PARAM_NAME,
			Long.toString(offender.getOffenderNumber()));
	byte[] doc = this.reportRunner.runReport(
			OFFENDER_FLAG_LISTING_REPORT_NAME,
			reportParamMap, reportFormat);
	return this.reportControllerDelegate.constructReportResponseEntity(
			doc, reportFormat);
}

	/**
	 * Returns the svo registration form for the specified offenders
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/sVORegistrationReport.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_FLAG_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSVORegistration(@RequestParam(
		value = "offender", required = true)
		final Offender offender,
		@RequestParam(value = "reportFormat", required = true)
		final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(OFFENDER_FLAG_LISTING_ID_REPORT_PARAM_NAME,
			Long.toString(offender.getOffenderNumber()));
	byte[] doc = this.reportRunner.runReport(
			SVO_REGISTRATION_REPORT_NAME,
			reportParamMap, reportFormat);
	return this.reportControllerDelegate.constructReportResponseEntity(
			doc, reportFormat);
}
	/* Helper methods. */
	
	// Prepares edit model and view
	private ModelAndView prepareEditMav(final Offender offender,
			final OffenderFlagForm offenderFlagForm) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(OFFENDER_FLAG_FORM_MODEL_KEY, offenderFlagForm);
		addOffenderSummary(mav, offender);
		return mav;
	}
	
	// Add offender summary
	private void addOffenderSummary(
			final ModelAndView mav, final Offender offender) {
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
	}
	
	// Prepares model and view to redisplay edit form
	private ModelAndView prepareRedisplayMav(final Offender offender,
			final OffenderFlagForm offenderFlagForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(offender, offenderFlagForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ OFFENDER_FLAG_FORM_MODEL_KEY, result);
		return mav;
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
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(OffenderFlagCategory.class,
				this.offenderFlagCategoryPropertyEditorFactory
					.createPropertyEditor());
	}
}