package omis.education.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.education.domain.EducationAssociableDocument;
import omis.education.domain.EducationTerm;
import omis.education.report.EducationDocumentSummary;
import omis.education.report.EducationSummary;
import omis.education.report.EducationSummaryReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * EducationReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */

@Controller
@RequestMapping("/education/")
@PreAuthorize("hasRole('USER')")
public class EducationReportController {
	
	/* View Names */
	
	private static final String LIST_VIEW_NAME = "/education/list";
	
	private static final String EDUCATIONS_ACTION_MENU_VIEW_NAME 
		= "/education/includes/educationsActionMenu";
	
	private static final String EDUCATIONS_ROW_ACTION_MENU_VIEW_NAME 
		= "/education/includes/educationsRowActionMenu";
	
	private static final String EDUCATION_DOCUMENTS_ACTION_MENU_VIEW_NAME 
	= "/education/includes/educationDocumentsActionMenu";

	private static final String EDUCATION_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME 
	= "/education/includes/educationDocumentsRowActionMenu";
	
	
	/* Model keys */
	
	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String SUMMARIES_MODEL_KEY = "summaries";
	
	private static final String DOCUMENT_SUMMARIES_MODEL_KEY =
			"educationDocumentSummaries";

	private static final String EDUCATION_TERM_MODEL_KEY = "educationTerm";
	
	private static final String EDUCATION_ASSOCIABLE_DOCUMENT_MODEL_KEY =
			"educationAssociableDocument";
	
	
	/* Services */
	
	@Autowired
	@Qualifier("educationSummaryReportService")
	private EducationSummaryReportService educationSummaryReportService;
	
	/* Property editors */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("educationTermPropertyEditorFactory")
	private PropertyEditorFactory educationTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("educationAssociableDocumentPropertyEditorFactory")
	private PropertyEditorFactory educationAssociableDocumentPropertyEditorFactory;
	
	/* Helpers */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
/* Report names. */
	
	private static final String EDUCATION_LISTING_REPORT_NAME 
		= "/CaseManagement/Education/Education_Listing";

	private static final String EDUCATION_DETAILS_REPORT_NAME 
		= "/CaseManagement/Education/Education_Details";
	
	private static final String TRANSCRIPT_REQUEST_RELEASE_FORM_NAME 
		= "/CaseManagement/Education/Transcript_Request_Release";

	private static final String TRANSCRIPT_REQUEST_RELEASE_REDACTED_FORM_NAME 
		= "/CaseManagement/Education/Transcript_Request_Release_Redacted";
	
	/* Report parameter names. */
	
	private static final String EDUCATION_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String EDUCATION_DETAILS_ID_REPORT_PARAM_NAME 
		= "ED_TERM_ID";
	
	private static final String TRANSCRIPT_REQUEST_RELEASE_ID_FORM_PARAM_NAME 
		= "ED_TERM_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructor */
	
	/**
	 * Default constructor for Education Report Controller
	 */
	public EducationReportController(){
		//Default
	}
	
	/* Model And View */
	
	/**
	 * Returns a model and view to list the education terms of the specified offender
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('EDUCATION_LIST')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
		final Offender offender){
		ModelMap map = new ModelMap();
		
		List<EducationSummary> summaries = this.educationSummaryReportService
				.findByOffender(offender);
		List<EducationDocumentSummary> educationDocumentSummaries =
				this.educationSummaryReportService
				.findEducationDocumentSummariesByOffender(offender);
		
		map.addAttribute(SUMMARIES_MODEL_KEY, summaries);
		map.addAttribute(DOCUMENT_SUMMARIES_MODEL_KEY,
				educationDocumentSummaries);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/* Reports */
	
	/**
	 * Returns the report for the specified offenders education.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/educationListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportEducationListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(EDUCATION_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				EDUCATION_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified education term.
	 * 
	 * @param educationTerm offender education term
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/educationDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportEducationDetails(@RequestParam(
			value = "educationTerm", required = true)
			final EducationTerm educationTerm,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(EDUCATION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(educationTerm.getId()));
		byte[] doc = this.reportRunner.runReport(
				EDUCATION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the form for the specified education term transcript 
	 * request/release.
	 * 
	 * @param educationTerm education term
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/transcriptRequestReleaseForm.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> formTranscriptRequestRelease(@RequestParam(
			value = "educationTerm", required = true)
	final EducationTerm educationTerm,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(TRANSCRIPT_REQUEST_RELEASE_ID_FORM_PARAM_NAME,
				Long.toString(educationTerm.getId()));
		byte[] doc = this.reportRunner.runReport(
				TRANSCRIPT_REQUEST_RELEASE_FORM_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the form for the specified education term transcript 
	 * request/release.
	 * 
	 * @param educationTerm education term
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/transcriptRequestReleaseRedactedForm.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> formTranscriptRequestReleaseRedacted(@RequestParam(
			value = "educationTerm", required = true)
	final EducationTerm educationTerm,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(TRANSCRIPT_REQUEST_RELEASE_ID_FORM_PARAM_NAME,
				Long.toString(educationTerm.getId()));
		byte[] doc = this.reportRunner.runReport(
				TRANSCRIPT_REQUEST_RELEASE_REDACTED_FORM_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Action Menus */
	
	/**
	 * Returns model and view for educations action menu
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/educationsActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayEducationsActionMenu(@RequestParam(
			value = "offender", required = true) final Offender offender){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(EDUCATIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns model and view for educations row action menu
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/educationsRowActionMenu.html",
			method=RequestMethod.GET)
	public ModelAndView displayEducationsRowActionMenu(@RequestParam(
			value = "offender", required = true)
			final Offender offender, @RequestParam(value = "educationTerm", 
			required = true) final EducationTerm educationTerm){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(EDUCATION_TERM_MODEL_KEY, educationTerm);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				EDUCATIONS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns model and view for education documents action menu
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/educationDocumentsActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayEducationDocumentsActionMenu(@RequestParam(
			value = "offender", required = true) final Offender offender){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(EDUCATION_DOCUMENTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns model and view for education documents row action menu
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/educationDocumentsRowActionMenu.html",
			method=RequestMethod.GET)
	public ModelAndView displayEducationDocumentsRowActionMenu(
			@RequestParam(value = "educationAssociableDocument", 
			required = true) final EducationAssociableDocument
				educationAssociableDocument){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(EDUCATION_ASSOCIABLE_DOCUMENT_MODEL_KEY, educationAssociableDocument);
		return new ModelAndView(
				EDUCATION_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(EducationTerm.class, 
				this.educationTermPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(EducationAssociableDocument.class, 
				this.educationAssociableDocumentPropertyEditorFactory
				.createPropertyEditor());
	}
}
