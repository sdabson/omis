package omis.offenderrelationship.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.family.domain.FamilyAssociation;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offenderrelationship.report.OffenderRelationReportService;
import omis.offenderrelationship.report.OffenderRelationSummary;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

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

/**
 * Report offender relationship controller.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Jan 13, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/offenderRelationship")
@PreAuthorize("hasRole('USER')")
public class ReportOffenderRelationshipController {
	/* Model keys. */
	private static final String OFFENDER_MODEL_KEY = "offender";
	private static final String OFFENDER_RELATION_SUMMARY_ITEMS_MODEL_KEY 
		= "offenderRelationSummaryItems";
	private static final String OFFENDER_YES_NO_MODEL_KEY 
		= "offenderYesNo";
	private static final String HAS_RELATIONSHIP_ASSOCIATIONS
		= "hasRelationshipAssociations";
	private static final String RELATIONSHIP_MODEL_KEY = "relationship";
	private static final String FAMILY_ASSOCIATION_MODEL_KEY 
		= "familyAssociation";
	
	/* View names. */
	private static final String LIST_VIEW_NAME = "offenderRelationship/list";
	private static final String 
		OFFENDER_RELATIONSHIPS_LIST_ACTION_MENU_VIEW_NAME
		= "offenderRelationship/includes/offenderRelationshipListActionMenu";
	private static final String OFFENDER_RELATIONSHIPS_ROW_ACTION_MENU_VIEW_NAME
		= "offenderRelationship/includes/update/"
				+ "offenderRelationshipsRowActionMenu";
	
	/* Report parameter names. */
	private static final String RELATIONSHIP_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";
	
	private static final String PERSON_DETAILS_REPORT_PARAM_NAME 
		= "PERSON_ID";
	
	private static final String PERSON_DETAILS_REDACTED_REPORT_PARAM_NAME 
		= "PERSON_ID";
	
	/* Report names. */
	private static final String RELATIONSHIP_LISTING_REPORT_NAME 
		= "/Relationships/Relationships_Listing";
	
	private static final String PERSON_DETAILS_REPORT_NAME 
		= "/Relationships/Person_Details";
	
	private static final String PERSON_DETAILS_REDACTED_REPORT_NAME 
		= "/Relationships/Person_Details_Redacted";
	
	/* Report runners. */
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Property editors. */
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	/* Services. */
	@Autowired
	@Qualifier("offenderRelationReportService")
	private OffenderRelationReportService offenderRelationReportService;
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
	@Autowired
	@Qualifier("relationshipPropertyEditorFactory")
	private PropertyEditorFactory relationshipPropertyEditorFactory;
	
	@Autowired
	@Qualifier("familyAssociationPropertyEditorFactory")
	private PropertyEditorFactory familyAssociationPropertyEditorFactory;
	
	/* Helpers. */
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/**
	 * Instantiates a default instance of report offender relationship
	 * controller.
	 */
	public ReportOffenderRelationshipController() {
		//Default constructor.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Lists relationships for the specified offender.
	 * 
	 * @param offender offender
	 * @return model and view to display offender relationships list screen
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('OFFENDER_RELATIONSHIP_LIST')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
		final Offender offender) {
		List<OffenderRelationSummary> offenderRelationSummaryItems
			= new ArrayList<OffenderRelationSummary>();
		Date effectiveDate = new Date();
		offenderRelationSummaryItems
			= this.offenderRelationReportService.summarizeByOffender(offender, 
			effectiveDate);
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(OFFENDER_RELATION_SUMMARY_ITEMS_MODEL_KEY,
			offenderRelationSummaryItems);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays the action menu for the specified offender's relationships 
	 * listing screen.
	 * 
	 * @param offender offender
	 * @return model and view for offender relationships action menu
	 */
	@RequestMapping(value = "offenderRelationshipsListActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayOffenderRelationshipsListActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
			OFFENDER_RELATIONSHIPS_LIST_ACTION_MENU_VIEW_NAME,
			map);
	}
	
	/**
	 * Returns offender relationship row action menu.
	 * @param offenderYesNo - is offender or not
	 * @param relationship - offender relationship
	 * @param hasRelationshipAssociations hasRelationshipAssociations
	 * @param familyAssociation familyAssociation
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/offenderRelationshipRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayOffenderRelationshipRowActionMenu(
			@RequestParam(value = "offenderYesNo", 
			required = true) final Boolean offenderYesNo,
			@RequestParam(value = "hasRelationshipAssociations", 
			required = true) final Boolean hasRelationshipAssociations,
			@RequestParam(value = "relationship", 
			required = true) final Relationship relationship,
			@RequestParam(value = "familyAssociation", 
			required = true) final FamilyAssociation familyAssociation
	) {
		ModelMap map = new ModelMap();
				
		if (offenderYesNo != null) {
			map.addAttribute(OFFENDER_YES_NO_MODEL_KEY, offenderYesNo);
		}
		map.addAttribute(HAS_RELATIONSHIP_ASSOCIATIONS, 
			hasRelationshipAssociations);
		map.addAttribute(RELATIONSHIP_MODEL_KEY, relationship);
		map.addAttribute(FAMILY_ASSOCIATION_MODEL_KEY, familyAssociation);
		ModelAndView mav = new ModelAndView(
			OFFENDER_RELATIONSHIPS_ROW_ACTION_MENU_VIEW_NAME, map);
		return mav;
	}

	/**
	 * Returns the report for the specified offenders relationship.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/relationshipListingReport.html",
			method = RequestMethod.GET)
	public ResponseEntity<byte []> reportRelationshipListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(RELATIONSHIP_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				RELATIONSHIP_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}	

	/**
	 * Returns the report for the specified related person.
	 * 
	 * @param person person
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/personDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('OFFENDER_RELATIONSHIP_VIEW') and "
			+ "hasRole('OFFENDER_SSN_VIEW')) or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPersonDetails(@RequestParam(
			value = "person", required = true)
			final Person person,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PERSON_DETAILS_REPORT_PARAM_NAME,
				Long.toString(person.getId()));
		byte[] doc = this.reportRunner.runReport(
				PERSON_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}

	/**
	 * Returns the redacted report for the specified related person.
	 * 
	 * @param person person
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/personDetailsRedactedReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_RELATIONSHIP_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPersonDetailsRedacted(@RequestParam(
			value = "person", required = true)
			final Person person,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PERSON_DETAILS_REDACTED_REPORT_PARAM_NAME,
				Long.toString(person.getId()));
		byte[] doc = this.reportRunner.runReport(
				PERSON_DETAILS_REDACTED_REPORT_NAME,
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
		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(Person.class,
			this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Relationship.class,
			this.relationshipPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(FamilyAssociation.class,
			this.familyAssociationPropertyEditorFactory.createPropertyEditor());
	}
}