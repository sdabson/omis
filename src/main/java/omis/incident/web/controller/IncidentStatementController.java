package omis.incident.web.controller;

import java.util.ArrayList;
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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import omis.audit.domain.VerificationMethod;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.IncidentStatementNote;
import omis.incident.domain.IncidentStatementSubmissionCategory;
import omis.incident.domain.InformationSourceCategory;
import omis.incident.domain.InvolvedParty;
import omis.incident.domain.InvolvedPartyCategory;
import omis.incident.domain.Jurisdiction;
import omis.incident.domain.component.IncidentScene;
import omis.incident.report.IncidentStatementSummary;
import omis.incident.report.IncidentStatementSummaryService;
import omis.incident.report.InvolvedPartySummary;
import omis.incident.service.IncidentStatementService;
import omis.incident.web.form.IncidentStatementForm;
import omis.incident.web.form.IncidentStatementNoteItem;
import omis.incident.web.form.IncidentStatementNoteItemOperation;
import omis.incident.web.form.InvolvedPartyItem;
import omis.incident.web.form.InvolvedPartyItemOperation;
import omis.incident.web.form.InvolvedPersonItem;
import omis.incident.web.form.SearchIncidentStatementForm;
import omis.incident.web.validator.IncidentStatementFormValidator;
import omis.location.domain.Location;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.user.domain.UserAccount;
import omis.util.DateManipulator;

/**
 * Incident report controller.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Sep 6, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/incident/statement")
@PreAuthorize("hasRole('USER')")
public class IncidentStatementController {
	
	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL 
	= "redirect:/incident/statement/list.html";
	
	/* View names. */
	
	private static final String BOOLEAN_VIEW_NAME = "common/json/booleanValue";
	
	private static final String EDIT_VIEW_NAME = "/incident/statement/edit";
	
	private static final String INVOLVED_PARTIES_ACTION_MENU_VIEW_NAME 
		= "/incident/statement/includes/involvedPartiesActionMenu";
	
	private static final String INCIDENT_STATEMENT_NOTES_ACTION_MENU_VIEW_NAME 
		= "/incident/statement/includes/incidentStatementNotesActionMenu";
	
	private static final String INCIDENT_STATEMENT_NOTE_ITEM_TABLE_ROW_VIEW_NAME
		= "/incident/statement/includes/incidentStatementNoteItemsTableRow";
	
	private static final String INVOLVED_PARTY_ITEM_TABLE_ROW_VIEW_NAME
		= "/incident/statement/includes/involvedPartyItemTableRow";
	
	private static final String INCIDENT_STATEMENT_ACTION_MENU_VIEW_NAME
		= "/incident/statement/includes/incidentStatementActionMenu";
	
	private static final String COMPLEX_OPTIONS_VIEW_NAME 
		= "/incident/statement/includes/complexOptions";
	
	private static final String UNIT_OPTIONS_VIEW_NAME
		= "/incident/statement/includes/unitOptions";
	
	private static final String LEVEL_OPTIONS_VIEW_NAME
		= "/incident/statement/includes/levelOptions";
	
	private static final String SECTION_OPTIONS_VIEW_NAME
		= "/incident/statement/includes/sectionOptions";
	
	private static final String ROOM_OPTIONS_VIEW_NAME
		= "/incident/statement/includes/roomOptions";
	
	private static final String FACILITY_AREA_OPTIONS_VIEW_NAME
		= "/incident/statement/includes/facilityAreaOptions";
	
	private static final String ADD_PERSON_ACTION_MENU_VIEW_NAME
		= "/incident/statement/includes/addPersonActionMenu";
	
	private static final String INVOLVED_PERSON_ITEM_VIEW_NAME
		= "/incident/statement/includes/involvedPersonItem";
	
	private static final String LIST_VIEW_NAME
		= "incident/list";
	
	private static final String INCIDENT_ACTION_MENU_VIEW_NAME
		= "incident/includes/incidentActionMenu";
	
	private static final String INFORMANT_SEARCH_VIEW_NAME
		= "incident/statement/includes/informantSearch";
	
	private static final String INCIDENT_STATEMENT_ROW_ACTION_MENU_VIEW_NAME
		= "incident/statement/includes/incidentStatementRowActionMenu";
	
	/* Model keys. */

	private static final String BOOLEAN_VALUE_MODEL_KEY = "booleanValue";
	
	private static final String INCIDENT_STATEMENT_MODEL_KEY
		= "incidentStatement";
	
	private static final String JURISDICTIONS_MODEL_KEY
		= "jurisdictions";
	
	private static final String ROOMS_MODEL_KEY = "rooms";
	
	private static final String SECTIONS_MODEL_KEY = "sections";
	
	private static final String LEVELS_MODEL_KEY = "levels";
	
	private static final String UNITS_MODEL_KEY = "units";
	
	private static final String COMPLEXES_MODEL_KEY = "complexes";

	private static final String FACILITIES_MODEL_KEY = "facilities";
	
	private static final String EDIT_FORM_MODEL_KEY = "incidentStatementForm";
	
	private static final String INVOLVED_PARTY_ITEM_MODEL_KEY
		= "involvedPartyItem";
	
	private static final String INVOLVED_PARTY_ITEM_INDEX_MODEL_KEY
		= "involvedPartyItemIndex";
	
	private static final String INVOLVED_PERSON_ITEM_MODEL_KEY
		= "involvedPersonItem";
	
	private static final String INVOLVED_PERSON_ITEM_INDEX_MODEL_KEY
		= "involvedPersonItemIndex";
	
	private static final String INCIDENT_STATEMENT_NOTE_ITEM_MODEL_KEY
		= "incidentStatementNoteItem";
	
	private static final String INCIDENT_STATEMENT_NOTE_ITEM_INDEX_MODEL_KEY
		= "incidentStatementNoteItemIndex";
	
	private static final String INVOLVED_PARTY_CATEGORIES_MODEL_KEY
		= "involvedPartyCategories";
	
	private static final String INVOLVED_PARTY_CATEGORY_MODEL_KEY
		= "involvedPartyCategory";
	
	private static final String INCIDENT_REPORT_NUMBER_MODEL_KEY
		= "incidentReportNumber";
	
	private static final String SEARCH_INCIDENT_REPORT_FORM_MODEL_KEY
		= "searchIncidentReportForm";
	
	private static final String SUMMARY_MAP_MODEL_KEY
		= "summaryMap";
	
	private static final String INCIDENT_STATEMENT_SUMMARIES_MODEL_KEY 
		= "incidentStatementSummaries";
	
	private static final String USER_ACCOUNT_MODEL_KEY
		= "AuditComponentRetrieverSpringMvcImpl#auditUserAccount";
	
	private static final String INFORMATION_SOURCE_CATEGORIES_MODEL_KEY
		= "informationSourceCategories";
	
	private static final String INFORMATION_SOURCE_CATEGORY_MODEL_KEY
		= "informationSourceCategory";
	
	private static final String INCIDENT_CATEGORIES_MODEL_KEY = "categories";
	
//	private static final String
//	INCIDENT_STATEMENT_SUBMISSION_CATEGORIES_MODEL_KEY = "submissionCategories";
	
	private static final String FACILITY_AREAS_MODEL_KEY = "facilityAreas";
	
	/* Report names. */
	private static final String STATEMENT_OF_INCIDENT_REPORT_NAME
		= "/Safety/Incidents/Statement_of_Incident";
	
	/* Report parameter names. */
	
	private static final String INCIDENT_STATEMENT_ID_REPORT_PARAM_NAME
		= "IS_ID";
	
	/* Services. */
	
	@Autowired
	@Qualifier("incidentStatementService")
	private IncidentStatementService incidentStatementService;
	
	@Autowired
	@Qualifier("incidentStatementSummaryService")
	private IncidentStatementSummaryService incidentStatementSummaryService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("facilityAreaPropertyEditorFactory")
	private PropertyEditorFactory facilityAreaPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	
	@Autowired
	@Qualifier("complexPropertyEditorFactory")
	private PropertyEditorFactory complexPropertyEditorFactory;
	
	@Autowired
	@Qualifier("levelPropertyEditorFactory")
	private PropertyEditorFactory levelPropertyEditorFactory;
	
	@Autowired
	@Qualifier("sectionPropertyEditorFactory")
	private PropertyEditorFactory sectionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("unitPropertyEditorFactory")
	private PropertyEditorFactory unitPropertyEditorFactory;
	
	@Autowired
	@Qualifier("roomPropertyEditorFactory")
	private PropertyEditorFactory roomPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("verificationMethodPropertyEditorFactory")
	private PropertyEditorFactory verificationMethodPropertyEditorFactory;
	
	@Autowired
	@Qualifier("incidentStatementPropertyEditorFactory")
	private PropertyEditorFactory incidentStatementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("involvedPartyPropertyEditorFactory")
	private PropertyEditorFactory involvedPartyPropertyEditorFactory;
	
	@Autowired
	@Qualifier("incidentStatementNotePropertyEditorFactory")
	private PropertyEditorFactory incidentStatementNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("locationPropertyEditorFactory")
	private PropertyEditorFactory locationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("jurisdictionPropertyEditorFactory")
	private PropertyEditorFactory jurisdictionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("incidentStatementCategoryPropertyEditorFactory")
	private PropertyEditorFactory incidentStatementCategoryPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("incidentStatementFormValidator")
	private IncidentStatementFormValidator incidentStatementFormValidator;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of incident statement controller.
	 */
	public IncidentStatementController() {
		//Default constructor.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Returns a model and view for creating a new incident statement.
	 * 
	 * @return model and view to create incident statement
	 */
	@RequestMapping(value = "create.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INCIDENT_STATEMENT_CREATE')")
	public ModelAndView create() {
		ModelMap map = new ModelMap();
		IncidentStatementForm form = new IncidentStatementForm();
		form.setDocumenter(this.retrieveUserAccount());
		return this.prepareEditMav(map, form);
	}

	/**
	 * Saves a new incident statement with information from the specified incident
	 * statement form.
	 * 
	 * @param form incident statement form
	 * @param result binding result
	 * @return model and view for list screen redirect
	 * @throws DuplicateEntityFoundException thrown when a duplicate incident
	 * statement, involved party, or incident statement note is found
	 */
	@RequestMapping(value = "create.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INCIDENT_STATEMENT_CREATE')")
	public ModelAndView save(IncidentStatementForm form, BindingResult result)
		throws DuplicateEntityFoundException {
		this.incidentStatementFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ EDIT_FORM_MODEL_KEY, result);
			return this.prepareEditMav(map, form);
		}
		IncidentScene scene = this.createScene(form);
		final IncidentStatementSubmissionCategory submissionCategory;
		if (form.getSubmissionCategory() == null) {
			submissionCategory = IncidentStatementSubmissionCategory.DRAFT;
		} else {
			submissionCategory = form.getSubmissionCategory();
		}
		IncidentStatement statement = this.incidentStatementService.create(
				form.getInformant(), form.getInformationSourceCategory(),
				form.getReporter(), form.getDocumenter(), form.getStatementDate(),
				this.combineDateAndTime(
						form.getIncidentDate(), form.getIncidentTime()),
						form.getSummary(), scene, form.getTitle(),
						form.getJurisdiction(),
						form.getConfidentialInformant(),
						form.getInformationSourceName(),
						submissionCategory,
						form.getCategory());
		this.processNoteItems(form.getIncidentStatementNoteItems(), statement);
		this.processInvolvedParties(form.getInvolvedPartyItems(), statement);
		return new ModelAndView(LIST_REDIRECT_URL);
	}	
	
	/**
	 * Returns a model and view for editing the specified incident statement.
	 * 
	 * @param incidentStatement incident statement
	 * @return model and view to edit incident statement
	 */
	@RequestMapping(value = "edit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INCIDENT_STATEMENT_VIEW')")
	public ModelAndView edit(@RequestParam(value = "incidentStatement",
			required = true)final IncidentStatement incidentStatement) {
		ModelMap map = new ModelMap();
		map.addAttribute(INCIDENT_STATEMENT_MODEL_KEY, incidentStatement);
		IncidentStatementForm form = new IncidentStatementForm();
		List<IncidentStatementNote> notes 
			= this.incidentStatementService.findNotes(incidentStatement);
		List<InvolvedParty> involvedParties = this.incidentStatementService
				.findInvolvedParties(incidentStatement);
		this.prepareEditForm(form, incidentStatement, notes, involvedParties);
		if (incidentStatement.getNumber() != null) {
			map.addAttribute(INCIDENT_REPORT_NUMBER_MODEL_KEY,
					incidentStatement.getNumber());
		}
		return this.prepareEditMav(map, form);
	}
	
	/**
	 * Updates the specified incident statement with information from the specified
	 * incident statement form.
	 * 
	 * @param incidentStatement incident statement
	 * @param form incident statement form
	 * @param result binding result
	 * @return model and view for list screen redirect
	 * @throws DuplicateEntityFoundException thrown when a duplicate incident
	 * statement, involved party, or incident statement note is found
	 */
	@RequestMapping(value = "edit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INCIDENT_STATEMENT_EDIT')")
	public ModelAndView update(@RequestParam(value = "incidentStatement",
			required = true)final IncidentStatement incidentStatement,
			final IncidentStatementForm form, final BindingResult result)
		throws DuplicateEntityFoundException {
		this.incidentStatementFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ EDIT_FORM_MODEL_KEY, result);
			if (incidentStatement.getNumber() != null) {
				map.addAttribute(INCIDENT_REPORT_NUMBER_MODEL_KEY,
						incidentStatement.getNumber());
			}
			map.addAttribute(INCIDENT_STATEMENT_MODEL_KEY, incidentStatement);
			return this.prepareEditMav(map, form);
		}
		if(IncidentStatementSubmissionCategory.DRAFT
				.equals(incidentStatement.getSubmissionCategory())) {
			IncidentScene scene = this.createScene(form);
			final IncidentStatementSubmissionCategory submissionCategory;
			if (form.getSubmissionCategory() == null) {
				submissionCategory = IncidentStatementSubmissionCategory.DRAFT;
			} else {
				submissionCategory = form.getSubmissionCategory();
			}
			this.incidentStatementService.update(incidentStatement,
					form.getInformant(), form.getInformationSourceCategory(),
					form.getReporter(), form.getDocumenter(),
					form.getStatementDate(),
					this.combineDateAndTime(form.getIncidentDate(),
							form.getIncidentTime()), form.getSummary(),
					scene, form.getTitle(), form.getConfidentialInformant(),
					form.getJurisdiction(),
					form.getInformationSourceName(),
					submissionCategory,
					form.getCategory());
			this.processNoteItems(form.getIncidentStatementNoteItems(),
					incidentStatement);
			this.processInvolvedParties(form.getInvolvedPartyItems(),
					incidentStatement);
		} else {
			throw new IllegalStateException(
					"Incident statement must be in draft state to update");
		}
		return new ModelAndView(LIST_REDIRECT_URL);
	}
	
	/**
	 * Removes the specified incident statement, any associated involved parties,
	 * and any associated incident statement notes.
	 * 
	 * @param incidentStatement incident statement
	 * @return redirect to list
	 */
	@RequestMapping(value = "remove.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INCIDENT_STATEMENT_REMOVE')")
	public ModelAndView remove(@RequestParam(value = "incidentStatement",
			required = true) final IncidentStatement statement) {
		List<InvolvedParty> involvedParties = this.incidentStatementService.
				findInvolvedParties(statement);
		for (InvolvedParty party : involvedParties) {
			this.incidentStatementService.removeInvolvedParty(party);
		}
		List<IncidentStatementNote> notes = this.incidentStatementService
				.findNotes(statement);
		for (IncidentStatementNote note : notes) {
			this.incidentStatementService.removeNote(note);
		}
		this.incidentStatementService.remove(statement);
		return new ModelAndView(LIST_REDIRECT_URL);
	}
	
	/**
	 * Displays the action menu for separation need.
	 * 
	 * @return model and view for separation need action menu
	 */
	@RequestMapping(value = "involvedPartyItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayInvolvedPartyItemsActionMenu() {
		ModelMap map = new ModelMap();
		map.addAttribute(INVOLVED_PARTY_CATEGORIES_MODEL_KEY,
				InvolvedPartyCategory.values());
		return new ModelAndView(INVOLVED_PARTIES_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for separation need.
	 * 
	 * @return model and view for separation need action menu
	 */
	@RequestMapping(value = "incidentStatementNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayIncidentStatementNoteItemsActionMenu() {
		return new ModelAndView(INCIDENT_STATEMENT_NOTES_ACTION_MENU_VIEW_NAME);
	}
	
	/**
	 * Returns a model and view for a new involved party item row.
	 * 
	 * @param index involved party item index
	 * @return model and view for involved party item row
	 */
	@RequestMapping(value = "createInvolvedPartyItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayInvolvedPartyItemRow(@RequestParam(
			value = "involvedPartyItemIndex", required = true)
			final Integer index, @RequestParam(value = "category",
			required = true) final  InvolvedPartyCategory category) {
		ModelMap map = new ModelMap();
		InvolvedPartyItem item = new InvolvedPartyItem();
		item.setOperation(InvolvedPartyItemOperation.CREATE);
		map.addAttribute(INVOLVED_PARTY_ITEM_MODEL_KEY, item);
		map.addAttribute(INVOLVED_PARTY_ITEM_INDEX_MODEL_KEY, index);
		map.addAttribute(INVOLVED_PARTY_CATEGORY_MODEL_KEY, category);
		return new ModelAndView(INVOLVED_PARTY_ITEM_TABLE_ROW_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the involved person item view name.
	 * 
	 * @param index involved person item index
	 * @param category involved party category
	 * @return model and view for involved person item view name
	 */
	@RequestMapping(value = "displayInvolvedPersonItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayInvolvedPersonItem(@RequestParam(
			value = "involvedPersonItemIndex")final Integer index,
			@RequestParam(value = "category", required = true)
			final InvolvedPartyCategory category) {
		ModelMap map = new ModelMap();
		InvolvedPersonItem item = new InvolvedPersonItem();
		item.setCategory(category);
		map.addAttribute(INVOLVED_PERSON_ITEM_MODEL_KEY, item);
		map.addAttribute(INVOLVED_PARTY_CATEGORY_MODEL_KEY, category);
		map.addAttribute(INVOLVED_PERSON_ITEM_INDEX_MODEL_KEY, index);
		return new ModelAndView(INVOLVED_PERSON_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view for a new incident statement note item.
	 *  
	 * @param index incident statement note item index
	 * @return model and view for incident statement note item row
	 */
	@RequestMapping(value = "createIncidentStatementNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayIncidentStatementNoteItemRow(@RequestParam(
			value = "incidentStatementNoteItemIndex", required = true)
			final Integer index) {
		ModelMap map = new ModelMap();
		IncidentStatementNoteItem item = new IncidentStatementNoteItem();
		item.setOperation(IncidentStatementNoteItemOperation.CREATE);
		map.addAttribute(INCIDENT_STATEMENT_NOTE_ITEM_MODEL_KEY, item);
		map.addAttribute(INCIDENT_STATEMENT_NOTE_ITEM_INDEX_MODEL_KEY, index);
		return new ModelAndView(INCIDENT_STATEMENT_NOTE_ITEM_TABLE_ROW_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the incident statement action menu.
	 * 
	 * @return model and view for incident statement action menu
	 */
	@RequestMapping(value = "incidentStatementActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayIncidentStatementActionMenu() {
		return new ModelAndView(INCIDENT_STATEMENT_ACTION_MENU_VIEW_NAME);
	}
	
	/**
	 * Displays the add person action menu.
	 * 
	 * @return model and view for add person action menu
	 */
	@RequestMapping(value = "addInvolvedPersonMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayAddPersonActionMenu() {
		ModelMap map = new ModelMap();
		map.addAttribute(INVOLVED_PARTY_CATEGORIES_MODEL_KEY,
				InvolvedPartyCategory.values());
		return new ModelAndView(ADD_PERSON_ACTION_MENU_VIEW_NAME, map);
	}
	
	@RequestMapping(value = "informantSearch.html",
			method = RequestMethod.GET)
	public ModelAndView displayInformantSearch(
			@RequestParam(value = "category", required = true)
				final InformationSourceCategory category) {
		ModelMap map = new ModelMap();
		map.addAttribute(INFORMATION_SOURCE_CATEGORY_MODEL_KEY, category);
		return new ModelAndView(INFORMANT_SEARCH_VIEW_NAME, map);
	}
	
	/**
	 * Returns complex options for the specified facility.
	 * 
	 * @param facility facility
	 * @return model and view of complex options
	 */
	@RequestMapping(value = "complexOptions.html", method = RequestMethod.GET)
	public ModelAndView showComplexOptions(
			@RequestParam(value = "facility", required = true)
			final Facility facility) {
		ModelMap map = new ModelMap();
		map.addAttribute(COMPLEXES_MODEL_KEY, this.incidentStatementService
				.findComplexesByFacility(facility));
		return new ModelAndView(COMPLEX_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns unit options for the specified facility and complex.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @return model and view of unit options
	 */
	@RequestMapping(value = "unitOptions.html", method = RequestMethod.GET)
	public ModelAndView showUnitOptions(
			@RequestParam(value = "facility", required = true)
			final Facility facility, @RequestParam(value = "complex",
			required = false) final Complex complex) {
		ModelMap map = new ModelMap();
		map.addAttribute(UNITS_MODEL_KEY, this.incidentStatementService
				.findUnits(facility, complex));
		return new ModelAndView(UNIT_OPTIONS_VIEW_NAME, map);
	}
	
	@RequestMapping(value= "facilityAreaOptions.html",
			method = RequestMethod.GET)
	public ModelAndView showFacilityAreaOptions(
			@RequestParam(value = "facility", required = true)
			final Facility facility, @RequestParam(value = "complex",
			required = false) final Complex complex) {
		ModelMap map = new ModelMap();
		map.addAttribute(FACILITY_AREAS_MODEL_KEY, this.incidentStatementService
				.findFacilityAreas(facility, complex));
		return new ModelAndView(FACILITY_AREA_OPTIONS_VIEW_NAME, map);
	}
	/**
	 * Returns section options for the specified facility, complex, level, and
	 * unit.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param level level
	 * @param unit unit
	 * @return model and view of section options
	 */
	@RequestMapping(value = "sectionOptions.html", method = RequestMethod.GET)
	public ModelAndView showSectionOptions(@RequestParam(value = "facility",
						required = true) final Facility facility,
				@RequestParam(value = "complex", required = false)
					final Complex complex,
			@RequestParam(value = "level", required = false)
				final Level level, @RequestParam(value = "unit",
					required = false) final Unit unit) {
		ModelMap map = new ModelMap();
		map.addAttribute(SECTIONS_MODEL_KEY, this.incidentStatementService.findSections(
				facility, complex, unit, level));
		return new ModelAndView(SECTION_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns level options for the specified facility, complex, section, and
	 * unit.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param section section
	 * @param unit unit
	 * @return model and view of level options
	 */
	@RequestMapping(value = "levelOptions.html", method = RequestMethod.GET)
	public ModelAndView showLevelOptions(
			@RequestParam(value = "facility", required = true)
				final Facility facility, @RequestParam(value = "complex",
				required = false) final Complex complex,
			@RequestParam(value = "section", required = false)
				final Section section, @RequestParam(value = "unit",
					required = false) final Unit unit) {
		ModelMap map = new ModelMap();
		map.addAttribute(LEVELS_MODEL_KEY, this.incidentStatementService
				.findLevels(facility, complex, unit, section));
		return new ModelAndView(LEVEL_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns room options for the specified facility, complex, section, unit
	 * and level.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param section section
	 * @param unit unit
	 * @param level level
	 * @return model and view of room options
	 */
	@RequestMapping(value = "roomOptions.html", method = RequestMethod.GET)
	public ModelAndView showRoomOptions(@RequestParam(value = "facility",
			required = true) final Facility facility,
			@RequestParam(value = "complex", required = false)
				final Complex complex,
		@RequestParam(value = "section", required = false)
			final Section section, @RequestParam(value = "unit",
				required = false) final Unit unit,
		@RequestParam(value = "level", required = false) final Level level) {
		ModelMap map = new ModelMap();
		map.addAttribute(ROOMS_MODEL_KEY, this.incidentStatementService.findRooms(
				facility, complex, unit, section, level));
		return new ModelAndView(ROOM_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Displays an incident search form.
	 * 
	 * @return view to display the incident search form
	 */
	@RequestMapping(value="/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('INCIDENT_STATEMENT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "jurisdiction",
		required = false) final Jurisdiction jurisdiction){
		SearchIncidentStatementForm searchIncidentReportForm 
			= new SearchIncidentStatementForm();
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(SEARCH_INCIDENT_REPORT_FORM_MODEL_KEY, 
			searchIncidentReportForm);
		mav.addObject(JURISDICTIONS_MODEL_KEY,
				this.incidentStatementService.findJurisdictions());
		mav.addObject(INCIDENT_STATEMENT_SUMMARIES_MODEL_KEY,
				this.incidentStatementSummaryService.findByCurrentUser());
		mav.addObject(INVOLVED_PERSON_ITEM_INDEX_MODEL_KEY, 0);
		return mav;
	}
	
	/**
	 * Returns the incident statement row action menu.
	 * 
	 * @param incidentStatement incident statement
	 * @return model and view for incident statement row action menu 
	 */
	@RequestMapping(value = "/incidentStatementRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayIncidentStatementRowActionMenu(
			@RequestParam(value = "incidentStatement", required = true)
				final IncidentStatement incidentStatement) {
		ModelMap map = new ModelMap();
		map.addAttribute(INCIDENT_STATEMENT_MODEL_KEY, incidentStatement);
		return new ModelAndView(INCIDENT_STATEMENT_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Search for incident with the specified criteria.
	 * 
	 * @return view to display the incident search form
	 */
	@RequestMapping(value="/list.html", method = RequestMethod.POST)
	public ModelAndView searchResult(
		final SearchIncidentStatementForm form) {
		Map<IncidentStatementSummary, List<InvolvedPartySummary>> summaryMap 
			= new HashMap<IncidentStatementSummary, List<InvolvedPartySummary>>();
		final List<IncidentStatementSummary> incidentStatementSummaries;
		final List<Person> involvedPeople = new ArrayList<Person>();
		if (form.getItems() != null && !form.getItems().isEmpty()) {
			for (int i = 0; i < form.getItems().size(); i++) {
				InvolvedPersonItem item = form.getItems().get(i);
				if(item.getPerson() != null) {
					involvedPeople.add(item.getPerson());
				} else {
					form.getItems().remove(i);
				}
			}
		}
		if (!involvedPeople.isEmpty()) {
			incidentStatementSummaries = this.incidentStatementSummaryService
					.findByInvolvedPeople(form.getStartDate(),
							form.getEndDate(), form.getJurisdiction(),
							form.getLocation(),
							involvedPeople);
		} else {
			incidentStatementSummaries = this.incidentStatementSummaryService
					.findByLocation(form.getStartDate(), form.getEndDate(),
							form.getJurisdiction(), form.getLocation());
		}
		List<InvolvedPartySummary> involvedPartySummaries;
		for(IncidentStatementSummary incidentStatementSummary
				: incidentStatementSummaries){
			involvedPartySummaries = this.incidentStatementSummaryService
				.findInvolvedParties(incidentStatementSummary.getId());
			summaryMap.put(incidentStatementSummary, involvedPartySummaries);
		}
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(SUMMARY_MAP_MODEL_KEY, summaryMap);
		mav.addObject(INCIDENT_STATEMENT_SUMMARIES_MODEL_KEY,
				incidentStatementSummaries);
		mav.addObject(SEARCH_INCIDENT_REPORT_FORM_MODEL_KEY, form);
		final int index;
		if (form.getItems() != null) {
			index=form.getItems().size();
		} else {
			index = 0;
		}
		mav.addObject(JURISDICTIONS_MODEL_KEY, this.incidentStatementService
				.findJurisdictions());
		mav.addObject(INVOLVED_PERSON_ITEM_INDEX_MODEL_KEY, index);
		return mav;
	}
	
	/**
	 * Returns a view for an incident statement action menu pertaining to the 
	 * specified offender.
	 * 
	 * @param offender offender
	 * @return view for incident statement action menu
	 */
	@RequestMapping(value = "/incidentActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView incidentActionMenu() {
		return new ModelAndView(INCIDENT_ACTION_MENU_VIEW_NAME);
	}
	
	/**
	 * Returns the report for the specified incident statement.
	 * 
	 * @param statement incident statement
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/incidentStatementReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('INCIDENT_STATEMENT_REPORT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportIncidentStatement(@RequestParam(
			value = "incidentStatement", required = true)
			final IncidentStatement statement,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(INCIDENT_STATEMENT_ID_REPORT_PARAM_NAME,
				Long.toString(statement.getId()));
		byte[] doc = this.reportRunner.runReport(
				STATEMENT_OF_INCIDENT_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* TODO: Figure this out */
	
	/**
	 * Reserved for extending session.
	 * 
	 * @return boolean
	 */
	@RequestMapping(value = "/extendSession.html", method = RequestMethod.GET)
	public ModelAndView extendSession() {
		ModelMap map = new ModelMap();
		map.addAttribute(BOOLEAN_VALUE_MODEL_KEY, true);
		return new ModelAndView(BOOLEAN_VIEW_NAME, map);
	}
	
	/* Helper methods. */
	
	/*
	 * Retrieves the current user account.
	 * 
	 * @return user account
	 */
	private UserAccount retrieveUserAccount() {
		UserAccount userAccount = (UserAccount) RequestContextHolder
				.getRequestAttributes()
					.getAttribute(USER_ACCOUNT_MODEL_KEY,
						RequestAttributes.SCOPE_REQUEST);
		if (userAccount == null) {
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			userAccount = 
					this.incidentStatementService
					.findUserAccountByUserName(username);
			RequestContextHolder.getRequestAttributes()
				.setAttribute(USER_ACCOUNT_MODEL_KEY, userAccount,
						RequestAttributes.SCOPE_REQUEST);
		}
		return userAccount;
	}
	
	/*
	 * Processes the specified list of incident statement note items according
	 * to their specified operation.
	 * 
	 * @param noteItems incident statement note items
	 * @param statement incident statement
	 * @throws DuplicateEntityFoundException thrown when a duplicate incident
	 * statement note is found
	 */
	private void processNoteItems(
			final List<IncidentStatementNoteItem> noteItems,
			final IncidentStatement statement)
		throws DuplicateEntityFoundException {
		for (IncidentStatementNoteItem noteItem : noteItems) {
			if (IncidentStatementNoteItemOperation.CREATE.equals(
					noteItem.getOperation())) {
				this.incidentStatementService.createNote(statement,
						noteItem.getValue(), noteItem.getDate());
			} else if (IncidentStatementNoteItemOperation.UPDATE.equals(
					noteItem.getOperation())) {
				if(isNoteChanged(noteItem.getNote(), noteItem.getDate(),
						noteItem.getValue())) {
					this.incidentStatementService.updateNote(noteItem.getNote(),
							noteItem.getValue(), noteItem.getDate());
				}
			} else if (IncidentStatementNoteItemOperation.REMOVE.equals(
					noteItem.getOperation())) {
				this.incidentStatementService.removeNote(noteItem.getNote());
			}
		}
	}
	
	/*
	 * Processes the specified list of involved party items according to their
	 * specified operation.
	 * 
	 * @param items involved party items
	 * @param statement incident statement
	 * @throws DuplicateEntityFoundException thrown when a duplicate involved
	 * party is found
	 */
	private void processInvolvedParties(final List<InvolvedPartyItem> items,
			final IncidentStatement statement) throws DuplicateEntityFoundException {
		for (InvolvedPartyItem item : items) {
			if (InvolvedPartyItemOperation.CREATE.equals(
					item.getOperation())) {
				this.incidentStatementService.createInvolvedParty(statement,
						item.getPerson(), null, item.getCategory(),
						item.getNarrative(), item.getName());
			} else if (InvolvedPartyItemOperation.REMOVE.equals(
					item.getOperation())) {
				this.incidentStatementService.removeInvolvedParty(
						item.getInvolvedParty());
			}
		}
	}
	
	/*
	 * Returns a model and view for presenting an incident statement form.
	 * 
	 * @param map model map
	 * @param form incident statement form
	 * @return model and view
	 */
	private ModelAndView prepareEditMav(final ModelMap map,
			final IncidentStatementForm form) {
		map.addAttribute(JURISDICTIONS_MODEL_KEY,
				this.incidentStatementService.findJurisdictions());
		map.addAttribute(FACILITIES_MODEL_KEY, this.incidentStatementService
				.findFacilities());
		map.addAttribute(INFORMATION_SOURCE_CATEGORIES_MODEL_KEY,
				InformationSourceCategory.values());
		map.addAttribute(INCIDENT_CATEGORIES_MODEL_KEY,
				this.incidentStatementService.findCategories());
		if (form.getFacility() != null) {
			map.addAttribute(FACILITY_AREAS_MODEL_KEY,
					this.incidentStatementService.findFacilityAreas(
							form.getFacility(), form.getComplex()));
			map.addAttribute(COMPLEXES_MODEL_KEY, this.incidentStatementService
					.findComplexesByFacility(form.getFacility()));
			map.addAttribute(UNITS_MODEL_KEY, this.incidentStatementService
					.findUnits(form.getFacility(), form.getComplex()));
			map.addAttribute(LEVELS_MODEL_KEY, this.incidentStatementService
					.findLevels(form.getFacility(), form.getComplex(),
							form.getUnit(), form.getSection()));
			map.addAttribute(SECTIONS_MODEL_KEY, this.incidentStatementService
					.findSections(form.getFacility(), form.getComplex(),
							form.getUnit(), form.getLevel()));
			map.addAttribute(ROOMS_MODEL_KEY, this.incidentStatementService
					.findRooms(form.getFacility(), form.getComplex(),
							form.getUnit(), form.getSection(),
							form.getLevel()));
		}
		final int involvedPartyItemsIndex;
		if (form.getInvolvedPartyItems() != null) {
			involvedPartyItemsIndex = form.getInvolvedPartyItems().size();
		} else {
			involvedPartyItemsIndex = 0;
		}
		final int incidentReportNoteItemsIndex;
		if (form.getIncidentStatementNoteItems() != null) {
			incidentReportNoteItemsIndex 
				= form.getIncidentStatementNoteItems().size();
		} else {
			incidentReportNoteItemsIndex = 0;
		}
		map.addAttribute(INVOLVED_PARTY_ITEM_INDEX_MODEL_KEY,
				involvedPartyItemsIndex);
		map.addAttribute(INCIDENT_STATEMENT_NOTE_ITEM_INDEX_MODEL_KEY,
				incidentReportNoteItemsIndex);
//		map.addAttribute(INCIDENT_STATEMENT_SUBMISSION_CATEGORIES_MODEL_KEY,
//				IncidentStatementSubmissionCategory.values());
		map.addAttribute(EDIT_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Prepares the specified form for editing with values from the specified
	 * incident statement.
	 * 
	 * @param form incident statement form
	 * @param incidentReport incident statement
	 * @param notes incident statement notes
	 * @param involvedParties involved parties
	 * @return prepared incident statement form
	 */
	private IncidentStatementForm prepareEditForm(final IncidentStatementForm form,
			final IncidentStatement incidentStatement, 
			final List<IncidentStatementNote> notes,
			final List<InvolvedParty> involvedParties) {
		IncidentScene scene = incidentStatement.getScene();
		form.setStatementDate(incidentStatement.getStatementDate());
		form.setIncidentDate(incidentStatement.getIncidentDate());
		form.setIncidentTime(incidentStatement.getIncidentDate());
		form.setDocumenter(incidentStatement.getDocumenter());
		form.setTitle(incidentStatement.getTitle());
		form.setIncidentReportNumber(incidentStatement.getNumber());
		form.setSummary(incidentStatement.getSummary());
		form.setJurisdiction(incidentStatement.getJurisdiction());
		form.setReporter(incidentStatement.getReporter());
		form.setConfidentialInformant(
				incidentStatement.getConfidentialInformant());
		form.setSubmissionCategory(incidentStatement.getSubmissionCategory());
		form.setCategory(incidentStatement.getCategory());
		if(incidentStatement.getInformationSource() != null) {
			form.setInformant(incidentStatement.getInformationSource()
					.getInformant());
			form.setInformationSourceCategory(incidentStatement
					.getInformationSource().getCategory());
			form.setInformationSourceName(incidentStatement.getInformationSource()
					.getName());
		}
		if (scene != null) {
			if (scene.getFacility() != null) {
				form.setFacilityScene(true);
			}
			form.setFacility(scene.getFacility());
			form.setComplex(scene.getComplex());
			form.setUnit(scene.getUnit());
			form.setSection(scene.getSection());
			form.setLevel(scene.getLevel());
			form.setRoom(scene.getRoom());
			form.setFacilityArea(scene.getFacilityArea());
			if (form.getUnit() != null || form.getSection() != null
					|| form.getLevel() != null || form.getRoom() != null) {
				form.setHousingScene(true);
			} else {
				form.setHousingScene(false);
			}
			form.setFacilityArea(scene.getFacilityArea());
			form.setLocation(scene.getLocation());
		}
		List<IncidentStatementNoteItem> noteItems 
			= new ArrayList<IncidentStatementNoteItem>();
		for (IncidentStatementNote note : notes) {
			IncidentStatementNoteItem item = new IncidentStatementNoteItem(
					note.getNote(), note.getDate(), note,
					IncidentStatementNoteItemOperation.UPDATE);
			noteItems.add(item);
		}
		form.setIncidentStatementNoteItems(noteItems);
		List<InvolvedPartyItem> partyItems = new ArrayList<InvolvedPartyItem>();
		for (InvolvedParty party : involvedParties) {
			InvolvedPartyItem item = new InvolvedPartyItem();
			item.setPerson(party.getPerson());
			item.setInvolvedParty(party);
			if (party.getVerificationSignature() != null) {
				item.setVerificationMethod(party.getVerificationSignature()
						.getMethod());
				item.setVerified(party.getVerificationSignature().getResult());
			}
			item.setOperation(InvolvedPartyItemOperation.UPDATE);
			item.setCategory(party.getCategory());
			item.setNarrative(party.getNarrative());
			item.setName(party.getName());
			partyItems.add(item);
		}
		form.setInvolvedPartyItems(partyItems);
		return form;
	}
	
	/*
	 * Creates an incident scene.
	 * 
	 * @param form incident statement form
	 * @return new incident scene
	 */
	private IncidentScene createScene(final IncidentStatementForm form) {
		IncidentScene scene = new IncidentScene();
		if(form.getFacilityScene()) {
			scene.setFacility(form.getFacility());
			scene.setComplex(form.getComplex());
			if (form.getHousingScene()) {
				scene.setUnit(form.getUnit());
				scene.setSection(form.getSection());
				scene.setLevel(form.getLevel());
				scene.setRoom(form.getRoom());
			} else {
				scene.setFacilityArea(form.getFacilityArea());
			}
		} 
		scene.setLocation(form.getLocation());
		return scene;
	}
	
	/*
	 * Combine a date and time into a single date object by adding the time
	 * to the date.
	 * 
	 * @param date date
	 * @param time time
	 */
	private Date combineDateAndTime(final Date date, final Date time) {
		return DateManipulator.getDateAtTimeOfDay(date, time);
	}
	
	/*
	 * Returns whether the note has different values than the supplied date
	 * and value.
	 * 
	 * @param note incident statement note
	 * @param date date
	 * @param value value
	 * @return whether note information is changes
	 */
	private boolean isNoteChanged(final IncidentStatementNote note,
			final Date date, final String value) {
		if(!note.getNote().equals(value)) {
			return true;
		}
		if (note.getDate().getTime() != date.getTime()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Complex.class,
				this.complexPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Unit.class,
				this.unitPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Level.class,
				this.levelPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Section.class,
				this.sectionPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Room.class,
				this.roomPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		
		binder.registerCustomEditor(
				Date.class,
				"incidentTime", 
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		
		binder.registerCustomEditor(
				VerificationMethod.class,
				this.verificationMethodPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				IncidentStatement.class,
				this.incidentStatementPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				IncidentStatementNote.class,
				this.incidentStatementNotePropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				InvolvedParty.class,
				this.involvedPartyPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				UserAccount.class,
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(Date.class,
				this.datePropertyEditorFactory
				.createCustomDateOnlyEditor(true));
		
		binder.registerCustomEditor(Location.class,
				this.locationPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Jurisdiction.class,
				this.jurisdictionPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(IncidentStatementCategory.class,
				this.incidentStatementCategoryPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				FacilityArea.class,
				this.facilityAreaPropertyEditorFactory
				.createPropertyEditor());
	}
}