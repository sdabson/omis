package omis.dna.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.dna.domain.DnaSample;
import omis.dna.service.DnaSampleService;
import omis.dna.validator.DnaSampleFormValidator;
import omis.dna.web.form.DnaSampleForm;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for offender DNA sample web requests.
 * 
 * @author Jason Nelson
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @version 0.1.1 (Oct 28, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/dna")
@PreAuthorize("hasRole('USER')")
public class DnaSampleController   {

	/* Redirect URLs. */
	private static final String DNA_LIST_REDIRECT_URL = 
		"redirect:list.html?offender=";
	
	private static final String LIST_REDIRECT
		= "redirect:/dna/list.html?offender=%d";
	
	/* View names. */
	
	private static final String DNA_LIST_VIEW_NAME = "dna/list";
	
	private static final String DNA_EDIT_VIEW_NAME = "dna/edit";
	
	private static final String DNA_SAMPLES_ACTION_MENU_VIEW_NAME 
		= "dna/includes/dnaSamplesActionMenu";
	
	private static final String DNA_SAMPLE_ACTION_MENU_VIEW_NAME 
	= "dna/includes/dnaSampleActionMenu";
	
	/* Model Keys. */
	
	private static final String DNA_SAMPLES_MODEL_KEY = "dnaSamples";
	
	private static final String OFFENDER_NUMBER_MODEL_KEY = "offenderNumber";
	
	private static final String DNA_SAMPLE_FORM_MODEL_KEY = "dnaSampleForm";
	
	private static final String DNA_SAMPLE_MODEL_KEY = "dnaSample";
	
	private static final String OFFNDER_MODEL_KEY = "offender";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Message Bundles. */
	
	private static final String ERROR_BUNDLE_NAME = "omis.dna.msgs.form";
	
	/* Message keys. */
	
	private static final String DNA_SAMPLE_EXISTS_MESSAGE_KEY
		= "dnaSample.exists";
	
	/* Services. */
	
	@Autowired
	@Qualifier("dnaSampleService")
	private DnaSampleService dnaSampleService;
	
	/* Property editor factories. */

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("dnaSamplePropertyEditorFactory")
	private PropertyEditorFactory dnaSamplePropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	/* Report names. */
	
	private static final String DNA_LISTING_REPORT_NAME 
		= "/BasicInformation/DNA/DNA_Sample_Listing";

	private static final String DNA_DETAILS_REPORT_NAME 
		= "/BasicInformation/DNA/DNA_Sample_Details";
	
	private static final String DOC_DNA_SAMPLE_COLL_REPORT_NAME 
		= "/BasicInformation/DNA/Documentation_of_DNA_Sample_Collection";
	
	private static final String DOC_DNA_SAMPLE_COLL_REDACTED_REPORT_NAME 
		= "/BasicInformation/DNA/Documentation_of_DNA_Sample_Collection_Redacted";
	
	private static final String BIOLOGICAL_SAMPLE_REPORT_NAME 
		= "/BasicInformation/DNA/Requirement_to_Provide_Biological_Sample_Form";
	
	/* Report parameter names. */
	
	private static final String DNA_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String DNA_DETAILS_ID_REPORT_PARAM_NAME 
		= "DNA_ID";

	private static final String DNA_SAMPLE_ID_REPORT_PARAM_NAME 
		= "DNA_SAMPLE_ID";
	
	private static final String BIOLOGICAL_SAMPLE_ID_REPORT_PARAM_NAME 
		= "DOC_ID";
	
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
	@Qualifier("dnaSampleFormValidator")
	private DnaSampleFormValidator dnaSampleFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/**
	 * Instantiates a default instance of DNA sample controller.
	 */
	public DnaSampleController() {
		//Default constructor.
	}
	
	/**
	 * Shows a list of dna samples for the specified offender.
	 * @param offender offender whose dna samples to show
	 * @return model and view to a list of offender dna samples
	 */
	@RequestContentMapping(nameKey = "dnaListingScreenName",
			descriptionKey = "dnaListingScreenDescription",
			messageBundle = "omis.dna.msgs.dna",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DNA_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		List<DnaSample> dnaSamples = this.dnaSampleService 
				.findByOffender(offender);
		map.addAttribute(DNA_SAMPLES_MODEL_KEY, dnaSamples);
		map.addAttribute(OFFNDER_MODEL_KEY, offender);
		map.addAttribute(OFFENDER_NUMBER_MODEL_KEY, String.valueOf(offender
				.getOffenderNumber()));
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(DNA_LIST_VIEW_NAME, map);
	}

	/**
	 * Shows a screen that allows a new dna sample to be created.
	 * 
	 * @param offender offender
	 * @return model and view to screen that allows new dna sample to be created
	 */
	@RequestContentMapping(nameKey = "dnaCreateScreenName",
			descriptionKey = "dnaCreateScreenDescription",
			messageBundle = "omis.dna.msgs.dna",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DNA_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(value = "offender", 
			required = true)final Offender offender) {
		ModelMap map = new ModelMap();
		DnaSampleForm form = new DnaSampleForm();
		map.addAttribute(DNA_SAMPLE_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_NUMBER_MODEL_KEY,  String.valueOf(offender
				.getOffenderNumber()));
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(DNA_EDIT_VIEW_NAME, map);
	}

	/**
	 * Shows a screen that allows an existing dna sample to be edited.
	 * @param dnaSample DNA sample to edit
	 * @return model and view to screen that allows existing DNA sample
	 * to be edited
	 */
	@RequestContentMapping(nameKey = "dnaEditScreenName",
			descriptionKey = "dnaEditScreenDescription",
			messageBundle = "omis.dna.msgs.dna",
			screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("hasRole('DNA_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "dnaSample", 
			required = true)final DnaSample dnaSample) {
		ModelMap map = new ModelMap();
		DnaSampleForm form = new DnaSampleForm();
		form.setComment(dnaSample.getComment());
		form.setLocation(dnaSample.getLocation());
		form.setCollectionEmployee(dnaSample.getCollectionEmployee());
		form.setDate(dnaSample.getDate());
		form.setWitness(dnaSample.getWitness());
		form.setTime(dnaSample.getTime());
		map.addAttribute(DNA_SAMPLE_MODEL_KEY, dnaSample);
		map.addAttribute(DNA_SAMPLE_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_NUMBER_MODEL_KEY, String.valueOf(dnaSample
				.getOffender().getOffenderNumber()));
		this.offenderSummaryModelDelegate.add(map, dnaSample.getOffender());
		map.addAttribute(OFFENDER_MODEL_KEY, dnaSample.getOffender());
		return new ModelAndView(DNA_EDIT_VIEW_NAME, map);
	}

	/**
	 * Saves changes to an existing offender DNA sample.
	 * @param form DNA sample form
	 * @param dnaSample DNA sample to update
	 * @param result binding result
	 * @return model and view redirect to list screen for offender of
	 * the specified DNA sample
	 * @throws DuplicateEntityFoundException thrown when a duplicate DNA sample 
	 * is found
	 */
	@RequestContentMapping(nameKey = "dnaEditSubmitName",
			descriptionKey = "dnaEditSubmitDescription",
			messageBundle = "omis.dna.msgs.dna",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('DNA_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(@RequestParam(
			value = "dnaSample", required = true) 
			final DnaSample dnaSample, final DnaSampleForm  form, 
			final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.dnaSampleFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(DNA_SAMPLE_MODEL_KEY, dnaSample);
			map.addAttribute(DNA_SAMPLE_FORM_MODEL_KEY, form);
			map.addAttribute(OFFENDER_NUMBER_MODEL_KEY, String.valueOf(
					dnaSample.getOffender().getOffenderNumber()));
			this.offenderSummaryModelDelegate.add(map, dnaSample
							.getOffender());
			map.addAttribute(OFFENDER_MODEL_KEY, dnaSample.getOffender());
			return new ModelAndView(DNA_EDIT_VIEW_NAME, map);
		}
		this.dnaSampleService.update(dnaSample, form.getCollectionEmployee(), 
				form.getComment(), form.getLocation(), form.getWitness(), 
				form.getDate(), form.getTime());
		return new ModelAndView(DNA_LIST_REDIRECT_URL 
				+ dnaSample.getOffender().getId());
	}

	/**
	 * Saves a new offender DNA sample.
	 * 
	 * @param form DNA sample form
	 * @param offender offender
	 * @param result binding result
	 * @return model and view redirect to list screen for the offender of the
	 * specified DNA sample.
	 * @throws DuplicateEntityFoundException thrown when a duplicate dna sample
	 * is found
	 */
	@RequestContentMapping(nameKey = "dnaCreateSubmitName",
			descriptionKey = "dnaCreateSubmitDescription",
			messageBundle = "omis.dna.msgs.dna",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('DNA_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
			final Offender offender, final DnaSampleForm  form,
			final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.dnaSampleFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(DNA_SAMPLE_FORM_MODEL_KEY, form);
			map.addAttribute(OFFENDER_NUMBER_MODEL_KEY,  String.valueOf(
					offender.getOffenderNumber()));
			this.offenderSummaryModelDelegate.add(map, offender);
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
			return new ModelAndView(DNA_EDIT_VIEW_NAME, map);
		}
		this.dnaSampleService.create(offender, form.getCollectionEmployee(), 
				form.getComment(), form.getLocation(), form.getWitness(), 
				form.getDate(), form.getTime());
		return new ModelAndView(DNA_LIST_REDIRECT_URL 
				+ offender.getId());
	}

	/**
	 * Removes an offender DNA sample.
	 * 
	 * @param offenderDnaSample DNA sample to remove
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "dnaRemoveName",
			descriptionKey = "dnaRemoveDescription",
			messageBundle = "omis.dna.msgs.dna",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DNA_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(@RequestParam(value = "dnaSample", 
			required = true) final DnaSample  offenderDnaSample) {
		Offender offender = offenderDnaSample.getOffender();
		this.dnaSampleService.remove(offenderDnaSample);
		return new ModelAndView(String.format(LIST_REDIRECT,
				offender.getId()));
	}
	
	/**
	 * Returns the content for the DNA samples action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the DNA samples action menu
	 */
	@RequestMapping(value = "dnaSamplesActionMenu.html")
	public ModelAndView dnaSamplesActionMenu(
			@RequestParam(value = "offender", required = false)
			final Offender offender,
			@RequestParam(value = "dnaSample", required = false)
			final DnaSample dnaSample) {
		ModelMap map = new ModelMap();
		if (offender != null) {
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
		}
		if(dnaSample!=null){
			map.addAttribute(DNA_SAMPLE_MODEL_KEY, dnaSample);
		}
		return new ModelAndView(DNA_SAMPLES_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the content for the DNA sample action menu.
	 * 
	 * @param offender offender
	 * @return model and view to show the DNA samples action menu
	 */
	@RequestMapping(value = "dnaSampleActionMenu.html")
	public ModelAndView dnaSampleActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(DNA_SAMPLE_ACTION_MENU_VIEW_NAME, map);
	}

	/**
	 * Returns the report for the specified offenders dna samples.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/dnaListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('DNA_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportDnaSampleListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DNA_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				DNA_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified dna sample.
	 * 
	 * @param dnaSample offender dna sample
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/dnaDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('DNA_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportDnaSampleDetails(@RequestParam(
			value = "dnaSample", required = true)
			final DnaSample dnaSample,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DNA_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(dnaSample.getId()));
		byte[] doc = this.reportRunner.runReport(
				DNA_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified dna sample.
	 * 
	 * @param dnaSample offender dna sample
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/dnaCollectionReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DNA_VIEW') and hasRole('OFFENDER_SSN_VIEW')) or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportDnaSampleCollection(@RequestParam(
			value = "dnaSample", required = true)
			final DnaSample dnaSample,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DNA_SAMPLE_ID_REPORT_PARAM_NAME,
				Long.toString(dnaSample.getId()));
		byte[] doc = this.reportRunner.runReport(
				DOC_DNA_SAMPLE_COLL_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified dna sample.
	 * 
	 * @param dnaSample offender dna sample
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/dnaCollectionRedactedReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('DNA_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportDnaSampleCollectionRedacted(
			@RequestParam(value = "dnaSample", required = true)
			final DnaSample dnaSample,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DNA_SAMPLE_ID_REPORT_PARAM_NAME,
				Long.toString(dnaSample.getId()));
		byte[] doc = this.reportRunner.runReport(
				DOC_DNA_SAMPLE_COLL_REDACTED_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}

	/**
	 * Returns the report for requesting the specified offenders biological samples.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/biologicalSampleReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('DNA_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportBiologicalSampleListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(BIOLOGICAL_SAMPLE_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BIOLOGICAL_SAMPLE_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Exception handler methods. */
	
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundExceptiojn(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				DNA_SAMPLE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}

	/**
	 * Sets up and registers property editors.
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				DnaSample.class, 
				this.dnaSamplePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class, 
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				Date.class, "time",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
	}
}