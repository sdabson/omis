package omis.substancetest.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.substance.validator.SubstanceTestSampleFormValidator;
import omis.substance.web.form.SubstanceTestSampleForm;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SampleRequest;
import omis.substancetest.domain.SampleRequestStatusReason;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestReason;
import omis.substancetest.domain.SubstanceTestSample;
import omis.substancetest.domain.component.SampleRequestResolution;
import omis.substancetest.service.SubstanceTestSampleService;
import omis.util.DateManipulator;
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
 * Substance test sample controller.
 * 
 * @author Joel Norris
 * @author Sierra Haynes
 * @version 0.1.0 (Apr 16, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/substanceTest/sample")
@PreAuthorize("(hasRole('USER'))")
public class SubstanceTestSampleController {

	/* Redirect URLs. */
	
	private static final String SUBSTANCE_TEST_LIST_REDIRECT_URL
		= "redirect:../list.html?offender=";
	
	private static final String CREATE_SUBSTANCE_TEST_FORM_REDIRECT
		= "redirect:../create.html?substanceTestSample=";
	
	/* View names. */
	
	private static final String SUBSTANCE_SAMPLE_EDIT_VIEW_NAME 
		= "substanceTest/sample/edit";
	
	private static final String SAMPLE_STATUS_REASON_OPTIONS_VIEW_NAME
		= "substanceTest/sample/includes/sampleStatusReasonOptions";
	
	private static final String SAMPLE_ACTION_MENU_VIEW_NAME
		= "substanceTest/sample/includes/substanceTestSampleActionMenu";
	
	/* Model keys. */
	
	private static final String SAMPLE_COLLECTION_METHODS_MODEL_KEY 
		= "sampleCollectionMethods";
	
	private static final String SUBSTANCE_TEST_REASONS_MODEL_KEY 
		= "substanceTestReasons";
	
	private static final String SUBSTANCE_TEST_SAMPLE_FORM_MODEL_KEY 
		= "substanceTestSampleForm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String SAMPLE_STATUS_REASONS = "sampleStatusReasons";
	
	private static final String SUBSTANCE_TEST_SAMPLE_MODEL_KEY
		= "substanceTestSample";
	
	/* Message keys. */
	
	private static final String SUBSTANCE_TEST_SAMPLE_EXISTS_MESSAGE_KEY
		= "substanceTestSample.exists";
	
	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.substancetest.sample.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("substanceTestSampleService")
	SubstanceTestSampleService substanceTestSampleService;
	
	@Autowired
	@Qualifier("offenderReportService")
    private OffenderReportService offenderReportService;
	
	/* Property editor factories. */
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("substanceTestSamplePropertyEditorFactory")
	private PropertyEditorFactory substanceTestSamplePropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("substanceTestReasonPropertyEditorFactory")
	private PropertyEditorFactory substanceTestReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("sampleCollectionMethodPropertyEditorFactory")
	private PropertyEditorFactory sampleCollectionMethodPropertyEditorFactory;
	
	@Autowired
	@Qualifier("sampleRequestStatusReasonPropertyEditorFactory")
	private PropertyEditorFactory
	sampleRequestStatusReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("sampleRequestPropertyEditorFactory")
	private PropertyEditorFactory sampleRequestPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("substanceTestSampleFormValidator")
	private SubstanceTestSampleFormValidator substanceTestSampleFormValidator;
	
	/* Report names. */
	
	private static final String SUBSTANCE_TEST_LISTING_REPORT_NAME 
		= "/Compliance/SubstanceTests/Substance_Testing_Listing";

	private static final String SUBSTANCE_TEST_DETAILS_REPORT_NAME 
		= "/Compliance/SubstanceTests/Substance_Testing_Details";
	
	private static final String DRUG_ALCOHOL_SCREENING_REPORT_NAME 
		= "/Compliance/SubstanceTests/Drug_Alcohol_Screening_Information_Form";
	
	private static final String SAMPLE_TAKERS_CHECKLIST_REPORT_NAME 
		= "/Compliance/SubstanceTests/Sample_Takers_Checklist_Chain_of_Evidence";
	
	private static final String SUBSTANCE_ABUSE_ADMISSION_FORM_REPORT_NAME 
		= "/Compliance/SubstanceTests/Substance_Abuse_Admission_Form";

	/* Report parameter names. */
	
	private static final String SUBSTANCE_TEST_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String SUBSTANCE_TEST_DETAILS_ID_REPORT_PARAM_NAME 
		= "SAMPLE_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/**
	 * Instantiates a default instance of substance test sample controller.
	 */
	public SubstanceTestSampleController() {
		//Default constructor.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Detail screen for presenting the substance test sample form for the
	 * purpose of creating a new substance test sample.
	 * 
	 * @param offender offender
	 * @param random whether random applies
	 * @return model and view for creating a new substance test sample
	 */
	@RequestContentMapping(nameKey = "substanceTestSampleCreateEditScreenName",
			descriptionKey = "substanceTestSampleCreateEditScreenDescription",
			messageBundle = "omis.substance.msgs.substance",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_SAMPLE_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(value = "offender",
			required = true)final Offender offender, @RequestParam(
					value = "random", required = true)final Boolean random,
			@RequestParam(value="request", required = false)
				final SampleRequest request) {
		ModelMap map = new ModelMap();
		SubstanceTestSampleForm form = new SubstanceTestSampleForm();
		form.setRequest(request);
		form.setRandom(random);
		if (request != null) {
			if (request.getResolution() != null) {
				form.setTaken(false);
				form.setStatusReason(request.getResolution().getStatusReason());
			}
		}
		return this.prepareEditMav(map, offender, form);
	}
	
	/**
	 * Creates a substance test sample form the specified form.
	 * 
	 * @param form substance test sample form
	 * @param offender offender
	 * @param result binding results
	 * @return model and view to redirect
	 * @throws DuplicateEntityFoundException thrown when a duplicate
	 * substance test sample is found. 
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_SAMPLE_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(@RequestParam(value = "offender", required = true)
			final Offender offender, final SubstanceTestSampleForm form,
			final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.substanceTestSampleFormValidator.validate(form, result);
		if (result.hasErrors()) {
			return this.prepareEditMav(new ModelMap(), offender, form);
		}
		if (form.getRequest() != null) {
			final SampleRequestResolution resolution
				= new SampleRequestResolution();
			final SubstanceTestSample sample;
			if (form.getTaken()) {
				sample = this.substanceTestSampleService
						.create(offender, combineDateAndTime(
							form.getCollectionDate(), form.getCollectionTime()),
						form.getCollectionEmployee(), 
						form.getSampleCollectionMethod(),
						form.getSubstanceTestReason(), form.getRandom());
				resolution.setSample(sample);
			} else {
				resolution.setStatusReason(form.getStatusReason());
				sample = null;
			}
			this.substanceTestSampleService.resolveRequest(form.getRequest(),
					resolution);
			if (sample != null && form.getSaveAndContinue()) {
				return new ModelAndView(CREATE_SUBSTANCE_TEST_FORM_REDIRECT
						+ sample.getId());
			}
		} else {
			SubstanceTestSample sample = this.substanceTestSampleService
					.create(offender, combineDateAndTime(
						form.getCollectionDate(), form.getCollectionTime()),
					form.getCollectionEmployee(), 
					form.getSampleCollectionMethod(),
					form.getSubstanceTestReason(), form.getRandom());
			if (form.getSaveAndContinue()) {
				return new ModelAndView(CREATE_SUBSTANCE_TEST_FORM_REDIRECT
						+ sample.getId());
			}
		}
		return new ModelAndView(SUBSTANCE_TEST_LIST_REDIRECT_URL 
				+ offender.getId());
	}
	
	/**
	 * Detail screen for presenting the substance test sample form for the
	 * purpose of creating a new substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @return 
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_SAMPLE_EDIT')"
			+ " or hasRole('SUBSTANCE_TEST_SAMPLE_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "substanceTestSample", required = true)
				final SubstanceTestSample substanceTestSample,
			@RequestParam(value = "request", required = false)
				final SampleRequest request) {
		ModelMap map = new ModelMap();
		SubstanceTestSampleForm form = new SubstanceTestSampleForm();
		final SampleRequest sampleRequest;
		if (request == null) {
			sampleRequest = this.substanceTestSampleService
				.findRequestBySample(substanceTestSample);
		} else {
			sampleRequest = request;
		}
		this.populateSubstanceTestSampleForm(form, substanceTestSample,
				sampleRequest);
		map.addAttribute(SUBSTANCE_TEST_SAMPLE_MODEL_KEY, substanceTestSample);
		return this.prepareEditMav(map, substanceTestSample.getOffender(),
				form);
	}
	
	/**
	 * Updates the specified substance test sample with information from the
	 * specified substance test sample form.
	 * 
	 * @param form substance test sample form
	 * @param substanceTestSample substance test sample
	 * @param result binding result
	 * @return model and view to redirect
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * test sample is found
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_SAMPLE_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(final SubstanceTestSampleForm form, 
			@RequestParam(value = "substanceTestSample", required = true)
				final SubstanceTestSample substanceTestSample, 
				final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.substanceTestSampleFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(SUBSTANCE_TEST_SAMPLE_MODEL_KEY,
					substanceTestSample);
			return this.prepareEditMav(map, substanceTestSample.getOffender(),
					form);
		}
		this.substanceTestSampleService.update(substanceTestSample, 
				substanceTestSample.getOffender(), combineDateAndTime(
						form.getCollectionDate(), form.getCollectionTime()),
				form.getCollectionEmployee(), form.getSampleCollectionMethod(),
				form.getSubstanceTestReason(), form.getRandom(), 
				form.getStatusReason());
		return new ModelAndView(SUBSTANCE_TEST_LIST_REDIRECT_URL 
				+ substanceTestSample.getOffender().getId());
	}
	
	/**
	 * Removes the specified substance test sample and, if applicable, 
	 * associated substance test.
	 * 
	 * @param substanceTestSample substance test sample
	 * @return model and view to redirect
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_SAMPLE_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(@RequestParam(value = "substanceTestSample", 
			required = true)final SubstanceTestSample substanceTestSample) {
		SubstanceTest substanceTest = this.substanceTestSampleService
				.findSubstanceTestBySample(substanceTestSample);
		if (substanceTest != null) {
			this.substanceTestSampleService.removeSubstanceTest(substanceTest);
		}
		Offender offender = substanceTestSample.getOffender();
		this.substanceTestSampleService.remove(substanceTestSample);
		return new ModelAndView(SUBSTANCE_TEST_LIST_REDIRECT_URL 
				+ offender.getId());
	}
	
	/**
	 * Returns sample status reasons for the specified taken value.
	 * 
	 * @param taken whether taken applies
	 * @return 
	 */
	@RequestMapping(value = "/sampleStatusReasonOptions.html",
			method = RequestMethod.GET)
	public ModelAndView showStatusReasonOptions() {
		ModelMap map = new ModelMap();
		map.addAttribute(SAMPLE_STATUS_REASONS,
				this.substanceTestSampleService.findStatusReasons());
		return new ModelAndView(SAMPLE_STATUS_REASON_OPTIONS_VIEW_NAME, map);
	}
	
	@RequestMapping(value = "/substanceTestSampleActionMenu",
			method = RequestMethod.GET)
	public ModelAndView showSubstanceTestSampleActionMenu(
			@RequestParam(value = "offender")final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(SAMPLE_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	/**
	 * Returns the report for the specified offenders substance tests.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/substanceTestListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reporSubstanceTestListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUBSTANCE_TEST_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				SUBSTANCE_TEST_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}

	/**
	 * Returns the report for the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/substanceTestDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSubstanceTestDetailsDetails(@RequestParam(
			value = "substanceTestSample", required = true)
			final SubstanceTestSample substanceTestSample,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUBSTANCE_TEST_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(substanceTestSample.getId()));
		byte[] doc = this.reportRunner.runReport(
				SUBSTANCE_TEST_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	

	/**
	 * Returns the drug alcohol screening report for the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/drugAlcoholScreeningReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportDrugAlcoholScreening(@RequestParam(
			value = "substanceTestSample", required = true)
			final SubstanceTestSample substanceTestSample,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUBSTANCE_TEST_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(substanceTestSample.getId()));
		byte[] doc = this.reportRunner.runReport(
				DRUG_ALCOHOL_SCREENING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	

	/**
	 * Returns the sample takers checklist report for the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/sampleTakersChecklistReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSampleTakersChecklist(@RequestParam(
			value = "substanceTestSample", required = true)
			final SubstanceTestSample substanceTestSample,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUBSTANCE_TEST_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(substanceTestSample.getId()));
		byte[] doc = this.reportRunner.runReport(
				SAMPLE_TAKERS_CHECKLIST_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	

	/**
	 * Returns the substance abuse admission report for the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/substanceAbuseAdmissionReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUBSTANCE_TEST_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSubstanceAbuseAdmission(@RequestParam(
			value = "substanceTestSample", required = true)
			final SubstanceTestSample substanceTestSample,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SUBSTANCE_TEST_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(substanceTestSample.getId()));
		byte[] doc = this.reportRunner.runReport(
				SUBSTANCE_ABUSE_ADMISSION_FORM_REPORT_NAME,
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
				SUBSTANCE_TEST_SAMPLE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}
	
	/* Helper methods. */
	
	/*
	 * Prepares a model and view with information needed in the substance test
	 * sample form.
	 * 
	 * @param map model map
	 * @param offender offender
	 * @param substanceTestSampleForm substance test sample form
	 * @return prepared model and view
	 */
	private ModelAndView prepareEditMav(final ModelMap map, 
			final Offender offender,
			final SubstanceTestSampleForm form) {
		map.addAttribute(SAMPLE_COLLECTION_METHODS_MODEL_KEY, 
				this.substanceTestSampleService.findCollectionMethods());
		map.addAttribute(SUBSTANCE_TEST_REASONS_MODEL_KEY, this
				.substanceTestSampleService.findSubstanceTestReasons());
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(SUBSTANCE_TEST_SAMPLE_FORM_MODEL_KEY, form);
		map.addAttribute(SAMPLE_STATUS_REASONS,
				this.substanceTestSampleService.findStatusReasons());
		return new ModelAndView(SUBSTANCE_SAMPLE_EDIT_VIEW_NAME, map);
	}	
	
	/*
	 * Populates the specified substance test sample form from the specified
	 * substance test sample.
	 * 
	 * @param form substance test sample form
	 * @param sample substance test sample
	 * @param request substance test sample request
	 * @return populated substance test sample form
	 */
	private SubstanceTestSampleForm populateSubstanceTestSampleForm(
			final SubstanceTestSampleForm form, 
			final SubstanceTestSample sample,
			final SampleRequest request) {
		form.setCollectionDate(sample.getCollectionDate());
		form.setCollectionTime(sample.getCollectionDate());
		form.setCollectionEmployee(sample.getCollectionEmployee());
		form.setRandom(sample.getRandom());
		form.setSampleCollectionMethod(sample.getSampleCollectionMethod());
		form.setSubstanceTestReason(sample.getSubstanceTestReason());
		if(request != null) {
			if (request.getResolution() != null) {
				if (request.getResolution().getSample() != null) {
					form.setTaken(true);
				} else {
					form.setTaken(false);
				}
				form.setStatusReason(request.getResolution().getStatusReason());
			}
		}
		return form;
	}
	
	/**
	 * Combine a date and time into a single date object by adding the time
	 * to the date.
	 * 
	 * @param date date
	 * @param time time
	 */
	private Date combineDateAndTime(final Date date, final Date time) {
		return DateManipulator.getDateAtTimeOfDay(date, time);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				Date.class,
				"collectionTime", 
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				SubstanceTestReason.class,
				this.substanceTestReasonPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				SampleCollectionMethod.class,
				this.sampleCollectionMethodPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				SubstanceTestSample.class,
				this.substanceTestSamplePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				SampleRequestStatusReason.class,
				this.sampleRequestStatusReasonPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				SampleRequest.class,
				this.sampleRequestPropertyEditorFactory
				.createPropertyEditor());
	}
}