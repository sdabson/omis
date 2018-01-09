package omis.warrant.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.jail.domain.Jail;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.region.domain.County;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;
import omis.warrant.domain.WarrantCancellation;
import omis.warrant.domain.WarrantCauseViolation;
import omis.warrant.domain.WarrantNote;
import omis.warrant.domain.WarrantReasonCategory;
import omis.warrant.domain.WarrantRelease;
import omis.warrant.service.WarrantCancellationService;
import omis.warrant.service.WarrantReleaseService;
import omis.warrant.service.WarrantService;
import omis.warrant.web.form.WarrantCancellationForm;
import omis.warrant.web.form.WarrantCauseViolationItem;
import omis.warrant.web.form.WarrantForm;
import omis.warrant.web.form.WarrantItemOperation;
import omis.warrant.web.form.WarrantNoteItem;
import omis.warrant.web.form.WarrantReleaseForm;
import omis.warrant.web.validator.WarrantCancellationFormValidator;
import omis.warrant.web.validator.WarrantFormValidator;
import omis.warrant.web.validator.WarrantReleaseFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * WarrantController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/warrant/")
@PreAuthorize("hasRole('USER')")
public class WarrantController {
	
	/* Views Names */
	
	private static final String EDIT_VIEW_NAME = "/warrant/edit";
	
	private static final String CANCEL_VIEW_NAME = "/warrant/cancel";
	
	private static final String RELEASE_VIEW_NAME = "/warrant/release";
	
	private static final String WARRANT_ACTION_MENU_VIEW_NAME =
			"/warrant/includes/warrantActionMenu";
	
	private static final String WARRANT_RELEASE_ACTION_MENU_VIEW_NAME =
			"/warrant/includes/warrantReleaseActionMenu";
	
	private static final String WARRANT_CANCELLATION_ACTION_MENU_VIEW_NAME =
			"/warrant/includes/warrantCancellationActionMenu";
	
	private static final String WARRANT_NOTES_ACTION_MENU_VIEW_NAME =
			"/warrant/includes/warrantNoteItemsActionMenu";
	
	private static final String WARRANT_CAUSE_VIOLATIONS_ACTION_MENU_VIEW_NAME =
			"/warrant/includes/warrantCauseViolationItemsActionMenu";
	
	private static final String WARRANT_NOTE_ITEM_ROW_VIEW_NAME =
			"/warrant/includes/warrantNoteTableRow";
	
	private static final String WARRANT_CAUSE_VIOLATION_ITEM_ROW_VIEW_NAME =
			"warrant/includes/warrantCauseViolationTableRow";
	
	private static final String CONDITION_OPTIONS_VIEW_NAME =
			"warrant/includes/conditionOptions";
	
	/* Model Keys */
	
	private static final String WARRANT_FORM_MODEL_KEY = "warrantForm";
	
	private static final String WARRANT_RELEASE_FORM_MODEL_KEY =
			"warrantReleaseForm";
	
	private static final String WARRANT_CANCELLATION_FORM_MODEL_KEY =
			"warrantCancellationForm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String WARRANT_MODEL_KEY = "warrant";
	
	private static final String WARRANT_ARREST_MODEL_KEY = "warrantArrest";
	
	private static final String WARRANT_RELEASE_MODEL_KEY = "warrantRelease";
	
	private static final String WARRANT_CANCELLATION_MODEL_KEY =
			"warrantCancellation";
	
	private static final String WARRANT_REASON_CATEGORY_MODEL_KEY =
			"warrantReasonCategory";
	
	private static final String COURT_CASES_MODEL_KEY = "courtCases";
	
	private static final String CONDITIONS_MODEL_KEY = "conditions";
	
	private static final String CONDITION_MODEL_KEY = "condition";
	
	private static final String JAILS_MODEL_KEY = "jails";
	
	private static final String FACILITIES_MODEL_KEY = "facilities";
	
	private static final String COUNTIES_MODEL_KEY = "counties";
	
	private static final String WARRANT_NOTE_ITEM_MODEL_KEY = "warrantNoteItem";
	
	private static final String WARRANT_CAUSE_VIOLATION_ITEM_MODEL_KEY =
			"warrantCauseViolationItem";
	
	private static final String WARRANT_NOTE_ITEM_INDEX_MODEL_KEY =
			"warrantNoteItemIndex";
	
	private static final String WARRANT_CAUSE_VIOLATION_ITEM_INDEX_MODEL_KEY =
			"warrantCauseViolationItemIndex";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String LIST_REDIRECT =
			"redirect:/warrant/list.html?offender=%d";
	
	/* Message Keys */
		
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"entity.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.warrant.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("warrantService")
	private WarrantService warrantService;
	
	@Autowired
	@Qualifier("warrantReleaseService")
	private WarrantReleaseService warrantReleaseService;

	@Autowired
	@Qualifier("warrantCancellationService")
	private WarrantCancellationService warrantCancellationService;
	
	/* Property editor factories */
	
	@Autowired
	@Qualifier("warrantPropertyEditorFactory")
	private PropertyEditorFactory warrantPropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantReleasePropertyEditorFactory")
	private PropertyEditorFactory warrantReleasePropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantCancellationPropertyEditorFactory")
	private PropertyEditorFactory warrantCancellationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantArrestPropertyEditorFactory")
	private PropertyEditorFactory warrantArrestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantNotePropertyEditorFactory")
	private PropertyEditorFactory warrantNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantCauseViolationPropertyEditorFactory")
	private PropertyEditorFactory warrantCauseViolationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("courtCasePropertyEditorFactory")
	private PropertyEditorFactory courtCasePropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionPropertyEditorFactory")
	private PropertyEditorFactory conditionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("jailPropertyEditorFactory")
	private PropertyEditorFactory jailPropertyEditorFactory;
	
	@Autowired
	@Qualifier("countyPropertyEditorFactory")
	private PropertyEditorFactory countyPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Validators */
	
	@Autowired
	@Qualifier("warrantFormValidator")
	private WarrantFormValidator warrantFormValidator;
	
	@Autowired
	@Qualifier("warrantReleaseFormValidator")
	private WarrantReleaseFormValidator warrantReleaseFormValidator;
	
	@Autowired
	@Qualifier("warrantCancellationFormValidator")
	private WarrantCancellationFormValidator warrantCancellationFormValidator;
	
	/**
	 * Default constructor for WarrantController
	 */
	public WarrantController() {
	}
	
	/* Warrant */
	
	/**
	 * Displays the Warrant creation screen
	 * @param offender - Offender the Warrant is for
	 * @param category - WarrantReasonCategory
	 * @return ModelAndView - Warrant creation screen
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('WARRANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value="warrantReasonCategory", required=true)
				final WarrantReasonCategory category){
		return this.prepareWarrantMav(offender, category,
				new WarrantForm(), new ModelMap());
	}
	
	/**
	 * Creates a Warrant and returns to the Warrant list screen
	 * @param offender - Offender the Warrant is for
	 * @param category - WarrantReasonCategory
	 * @param form - WarrantForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - Back to the Warrant create screen on form error,
	 * or to the Warrant list screen on successful save
	 * @throws DuplicateEntityFoundException - When a WarrantCauseViolation 
	 * already exists with the given Condition and CourtCase, or a WarrantNote
	 * already exists with the given Note and Date, for the specified Warrant
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('WARRANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value="warrantReasonCategory", required=true)
				final WarrantReasonCategory category,
			final WarrantForm form, final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.warrantFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareWarrantMav(offender, category,
				form, new ModelMap());
		}
		else{
			Warrant warrant = this.warrantService.create(offender,
					form.getDate(), form.getAddressee(), form.getIssuedBy(),
					form.getBondRecommended(), (form.getBondRecommended()
							? new BigDecimal(form.getBondRecommendation())
									: null), category);
			
			if(form.getArrested()){
				this.warrantService
						.createArrest(warrant, form.getArrestDate(),
								form.getArrestJail(), form.getContactBy());
			}
			
			this.processWarrantItems(warrant,
					form.getWarrantNoteItems(),
					form.getWarrantCauseViolationItems());
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					offender.getId()));
		}
	}
	
	/**
	 * Displays the Warrant Edit screen
	 * @param warrant - Warrant being edited
	 * @return ModelAndView - Warrant edit screen
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('WARRANT_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "warrant", required = true)
				final Warrant warrant){
		ModelMap map = new ModelMap();
		map.addAttribute(WARRANT_MODEL_KEY, warrant);
		WarrantArrest arrest = this.warrantService
				.findWarrantArrestByWarrant(warrant);
		if(arrest != null){
			map.addAttribute(WARRANT_ARREST_MODEL_KEY, arrest);
		}
		return this.prepareWarrantMav(
				warrant.getOffender(),
				warrant.getWarrantReason(),
				this.prepareWarrantForm(warrant), map);
	}
	
	/**
	 * Updates the specified Warrant and returns to the Warrant list screen
	 * @param warrant - Warrant to update
	 * @param form - WarrantForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - Back to the Warrant Edit screen on form error,
	 * or to the Warrant list screen on successful update
	 * @throws DuplicateEntityFoundException - When a WarrantCauseViolation 
	 * already exists with the given Condition and CourtCase, or a WarrantNote
	 * already exists with the given Note and Date, for the specified Warrant
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('WARRANT_VIEW') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "warrant", required = true)
				final Warrant warrant,
			final WarrantForm form, final BindingResult bindingResult)
						throws DuplicateEntityFoundException{
		
		this.warrantFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
		
			ModelMap map = new ModelMap();
			map.addAttribute(WARRANT_MODEL_KEY, warrant);
			WarrantArrest arrest = this.warrantService
					.findWarrantArrestByWarrant(warrant);
			if(arrest != null){
				map.addAttribute(WARRANT_ARREST_MODEL_KEY, arrest);
			}
			
			return this.prepareWarrantMav(
					warrant.getOffender(),
					warrant.getWarrantReason(),
					form, map);
		}
		else{
			this.warrantService.update(warrant,
					form.getDate(), form.getAddressee(), form.getIssuedBy(),
					form.getBondRecommended(), (form.getBondRecommended()
							? new BigDecimal(form.getBondRecommendation())
									: null),
					warrant.getWarrantReason());
			
			if(form.getArrested()){
				WarrantArrest warrantArrest =
						this.warrantService.findWarrantArrestByWarrant(warrant);
				if(warrantArrest == null){
					this.warrantService.createArrest(
							warrant, form.getArrestDate(),
							form.getArrestJail(), form.getContactBy());
				}
				else{
					this.warrantService.updateArrest(warrantArrest,
							form.getArrestDate(), form.getArrestJail(),
							form.getContactBy());
				}
			}
			
			this.processWarrantItems(warrant,
					form.getWarrantNoteItems(),
					form.getWarrantCauseViolationItems());
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					warrant.getOffender().getId()));
		}
	}
	
	/**
	 * Displays a WarrantNoteItem row view
	 * @param warrantNoteItemIndex - current item index
	 * @return ModelAndView - WarrantNoteItem row
	 */
	@RequestMapping(value = "createWarrantNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayWarrantNoteItemRow(@RequestParam(
			value = "warrantNoteItemIndex", required = true)
				final Integer warrantNoteItemIndex) {
		ModelMap map = new ModelMap();
		WarrantNoteItem noteItem = new WarrantNoteItem();
		noteItem.setItemOperation(WarrantItemOperation.CREATE);
		map.addAttribute(WARRANT_NOTE_ITEM_MODEL_KEY, noteItem);
		map.addAttribute(WARRANT_NOTE_ITEM_INDEX_MODEL_KEY,
				warrantNoteItemIndex);
		return new ModelAndView(WARRANT_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays a WarrantCauseViolationItem row view
	 * @param warrantCauseViolationItemIndex - current item index
	 * @param courtCase - CourtCase
	 * @return ModelAndView - WarrantCauseViolationItem row
	 */
	@RequestMapping(value = "createWarrantCauseViolationItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayWarrantCauseViolationItemRow(
			@RequestParam(value = "warrantCauseViolationItemIndex",
			required = true) final Integer warrantCauseViolationItemIndex,
			@RequestParam(value = "courtCase",
			required = true) final CourtCase courtCase) {
		ModelMap map = new ModelMap();
		WarrantCauseViolationItem violationItem =
				new WarrantCauseViolationItem();
		violationItem.setCourtCase(courtCase);
		violationItem.setItemOperation(WarrantItemOperation.CREATE);
		
		List<Condition> conditions = this.warrantService
				.findConditionsByCourtCase(courtCase);
		map.addAttribute(CONDITIONS_MODEL_KEY, conditions);
		map.addAttribute(WARRANT_CAUSE_VIOLATION_ITEM_MODEL_KEY, violationItem);
		map.addAttribute(WARRANT_CAUSE_VIOLATION_ITEM_INDEX_MODEL_KEY,
				warrantCauseViolationItemIndex);
		return new ModelAndView(WARRANT_CAUSE_VIOLATION_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays the Condition options for a WarrantCauseViolationItem
	 * @param courtCase - CourtCase of the WarrantCauseViolationItem
	 * @param condition - Currently selected Condition of the
	 * WarrantCauseViolationItem
	 * @return ModelAndView - Condition options
	 */
	@RequestMapping(value = "displayConditions.html",
			method = RequestMethod.GET)
	public ModelAndView displayConditions(
			@RequestParam(value = "courtCase",
			required = true) final CourtCase courtCase,
			@RequestParam(value = "condition", required=false)
			final Condition condition) {
		List<Condition> conditions = this.warrantService
				.findConditionsByCourtCase(courtCase);
		
		ModelMap map = new ModelMap();
		map.addAttribute(CONDITIONS_MODEL_KEY, conditions);
		//currently selected condition:
		map.addAttribute(CONDITION_MODEL_KEY, condition);
		
		return new ModelAndView(CONDITION_OPTIONS_VIEW_NAME, map); 
	}
	
	/* Warrant Release */
	
	/**
	 * Displays the WarrantRelease edit screen
	 * @param warrant - Warrant the WarrantRelease is for
	 * @return ModelAndView - WarrantRelease edit screen
	 */
	@RequestMapping(value = "/release.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('WARRANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView editWarrantRelease(
			@RequestParam(value = "warrant", required = true)
				final Warrant warrant){
		return this.prepareWarrantReleaseMav(warrant,
				this.prepareWarrantReleaseForm(warrant));
	}
	
	/**
	 * Creates or Updates a WarrantRelease and returns to the Warrant list screen
	 * @param warrant - Warrant
	 * @param form - WarrantReleaseForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - Back to the WarrantRelease edit screen on form
	 * error, or to the Warrant list screen on successful save/update
	 * @throws DuplicateEntityFoundException - When another WarrantRelease
	 * already exists for the specified Warrant (shouldn't happen from this spot)
	 */
	@RequestMapping(value = "/release.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('WARRANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView updateWarrantRelease(
			@RequestParam(value = "warrant", required = true)
				final Warrant warrant,
			final WarrantReleaseForm form, final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.warrantReleaseFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareWarrantReleaseMav(warrant, form);
		}
		else{
			WarrantRelease warrantRelease = this.warrantReleaseService
					.findByWarrant(warrant);
			
			if(warrantRelease == null){
				this.warrantReleaseService.create(warrant,
						form.getInstructions(), form.getCounty(),
						form.getFacility(), form.getReleaseDate(),
						form.getAddressee(), form.getClearedBy(),
						form.getClearedByDate());
			}
			else{
				this.warrantReleaseService.update(warrantRelease,
						form.getInstructions(), form.getCounty(),
						form.getFacility(), form.getReleaseDate(),
						form.getAddressee(), form.getClearedBy(),
						form.getClearedByDate());
			}
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					warrant.getOffender().getId()));
		}
	}
	
	
	/* Warrant Cancellation */
	
	/**
	 * Displays the WarrantCancellation edit screen
	 * @param warrant - Warrant the WarrantCancellation is for
	 * @return ModelAndView - WarrantCancellation edit screen
	 */
	@RequestMapping(value = "/cancel.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('WARRANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView editWarrantCancellation(
			@RequestParam(value = "warrant", required = true)
				final Warrant warrant){
		return this.prepareWarrantCancellationMav(warrant,
				this.prepareWarrantCancellationForm(warrant));
	}
	
	/**
	 * Creates or Updates a WarrantCancellation and returns to the Warrant list
	 * screen
	 * @param warrant - Warrant
	 * @param form - WarrantCancellationForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - back to the WarrantCancellation edit screen on
	 * form error, or to the Warrant list screen on successful save/update
	 * @throws DuplicateEntityFoundException - When another WarrantCancellation 
	 * already exists for the specified Warrant (shouldn't happen from this spot)
	 */
	@RequestMapping(value = "/cancel.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('WARRANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView updateWarrantCancellation(
			@RequestParam(value = "warrant", required = true)
				final Warrant warrant,
			final WarrantCancellationForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.warrantCancellationFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareWarrantCancellationMav(warrant, form);
		}
		else{
			WarrantCancellation warrantCancellation =
					this.warrantCancellationService.findByWarrant(warrant);
			
			if(warrantCancellation == null){
				this.warrantCancellationService.create(warrant, form.getDate(),
						form.getComment(), form.getClearedBy(),
						form.getClearedByDate());
			}
			else{
				this.warrantCancellationService.update(warrantCancellation,
						form.getDate(), form.getComment(), form.getClearedBy(),
						form.getClearedByDate());
			}
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					warrant.getOffender().getId()));
		}
	}
	
	/* Action Menus */
	
	/**
	 * Displays the Warrant action menu
	 * @param offender - Offender
	 * @return ModelAndView - Warrant action menu
	 */
	@RequestMapping(value = "/warrantActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayWarrantActionMenu(@RequestParam
			(value = "offender", required = true) final Offender offender){
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(WARRANT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the WarrantCauseViolationItems action menu
	 * @param offender - Offender
	 * @return ModelAndView - WarrantCauseViolationItems action menu
	 */
	@RequestMapping(value = "/warrantCauseViolationItemsActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayWarrantCauseViolationItemsActionMenu(@RequestParam
			(value = "offender", required = true) final Offender offender){
		ModelMap map = new ModelMap();
		List<CourtCase> courtCases = this.warrantService
				.findCourtCasesByDefendant(offender);
		
		map.addAttribute(COURT_CASES_MODEL_KEY, courtCases);
		
		return new ModelAndView(
				WARRANT_CAUSE_VIOLATIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the WarrantNoteItems action menu
	 * @return ModelAndView - WarrantNoteItems action menu
	 */
	@RequestMapping(value = "/warrantNoteItemsActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayWarrantNoteItemsActionMenu(){
		ModelMap map = new ModelMap();
		return new ModelAndView(
				WARRANT_NOTES_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the WarrantRelease action menu
	 * @param offender - Offender
	 * @return ModelAndView - WarrantRelease action menu
	 */
	@RequestMapping(value = "/warrantReleaseActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayWarrantReleaseActionMenu(@RequestParam
			(value = "offender", required = true) final Offender offender){
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(WARRANT_RELEASE_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the WarrantCancellation action menu
	 * @param offender - Offender
	 * @return ModelAndView - WarrantCancellation action menu
	 */
	@RequestMapping(value = "/warrantCancellationActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayWarrantCancellationActionMenu(@RequestParam
			(value = "offender", required = true) final Offender offender){
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(WARRANT_CANCELLATION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Helper Methods */
	
	/**
	 * Prepares a ModelAndView for Warrant creation/editing
	 * @param offender - Offender
	 * @param category - WarrantReasonCategory
	 * @param form - WarrantForm
	 * @param map - ModelMap
	 * @return ModelAndView for Warrant creation/editing
	 */
	private ModelAndView prepareWarrantMav(
			final Offender offender, final WarrantReasonCategory category,
			final WarrantForm form, final ModelMap map){
		
		if(!(WarrantReasonCategory.AUTHORIZATION_TO_PICKUP_AND_HOLD
				.equals(category))){
			form.setBondRecommended(false);
		}
		map.addAttribute(WARRANT_FORM_MODEL_KEY, form);
		map.addAttribute(WARRANT_REASON_CATEGORY_MODEL_KEY, category);
		map.addAttribute(COURT_CASES_MODEL_KEY, this.warrantService
				.findCourtCasesByDefendant(offender));
		map.addAttribute(JAILS_MODEL_KEY, this.warrantService.findAllJails());
		map.addAttribute(WARRANT_NOTE_ITEM_INDEX_MODEL_KEY,
				(form.getWarrantNoteItems() != null
				? form.getWarrantNoteItems().size() : 0));
		map.addAttribute(WARRANT_CAUSE_VIOLATION_ITEM_INDEX_MODEL_KEY, 
				(form.getWarrantCauseViolationItems() != null
				? form.getWarrantCauseViolationItems().size() : 0));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a WarrantForm with the properties of the specified Warrant
	 * @param warrant - Warrant
	 * @return Prepared WarrantForm
	 */
	private WarrantForm prepareWarrantForm(final Warrant warrant){
		WarrantForm form = new WarrantForm();
		
		form.setAddressee(warrant.getAddressee());
		form.setDate(warrant.getDate());
		form.setIssuedBy(warrant.getIssuedBy());
		
		if(warrant.getBondable()){
			form.setBondRecommended(true);
			if(warrant.getBondRecommendation() != null){
				form.setBondRecommendation(
						warrant.getBondRecommendation().toPlainString());
			}
		}
		else{
			form.setBondRecommended(false);
		}
		
		WarrantArrest arrest = this.warrantService
				.findWarrantArrestByWarrant(warrant);
		
		if(arrest != null){
			form.setArrested(true);
			form.setArrestDate(arrest.getDate());
			form.setArrestJail(arrest.getJail());
			form.setContactBy(arrest.getContactByDate());
		}
		
		List<WarrantNote> warrantNotes = this.warrantService
				.findWarrantNotesByWarrant(warrant);
		List<WarrantCauseViolation> warrantCauseViolations = this.warrantService
				.findWarrantCauseViolationsByWarrant(warrant);
		List<WarrantCauseViolationItem> warrantCauseViolationItems =
				new ArrayList<WarrantCauseViolationItem>();
		List<WarrantNoteItem> warrantNoteItems = new ArrayList<WarrantNoteItem>();
		for(WarrantNote note : warrantNotes){
			WarrantNoteItem noteItem = new WarrantNoteItem();
			noteItem.setWarrantNote(note);
			noteItem.setNote(note.getNote());
			noteItem.setDate(note.getDate());
			noteItem.setItemOperation(WarrantItemOperation.UPDATE);
			
			warrantNoteItems.add(noteItem);
		}
		
		for(WarrantCauseViolation violation : warrantCauseViolations){
			WarrantCauseViolationItem violationItem =
					new WarrantCauseViolationItem();
			violationItem.setWarrantCauseViolation(violation);
			violationItem.setCondition(violation.getCondition());
			violationItem.setCourtCase(violation.getCause());
			violationItem.setDescription(violation.getDescription());
			violationItem.setItemOperation(WarrantItemOperation.UPDATE);
			
			warrantCauseViolationItems.add(violationItem);
		}
		
		form.setWarrantNoteItems(warrantNoteItems);
		form.setWarrantCauseViolationItems(warrantCauseViolationItems);
		
		return form;
	}
	
	/**
	 * Prepares a ModelAndView for the WarrantRelease edit screen
	 * @param warrant - Warrant the WarrantRelease is for
	 * @param form - WarrantRelease
	 * @return ModelAndView for the WarrantRelease edit screen
	 */
	private ModelAndView prepareWarrantReleaseMav(final Warrant warrant,
			final WarrantReleaseForm form){
		ModelMap map = new ModelMap();
		
		map.addAttribute(WARRANT_RELEASE_FORM_MODEL_KEY, form);
		map.addAttribute(WARRANT_RELEASE_MODEL_KEY,
				this.warrantReleaseService.findByWarrant(warrant));
		map.addAttribute(WARRANT_REASON_CATEGORY_MODEL_KEY,
				warrant.getWarrantReason());
		map.addAttribute(FACILITIES_MODEL_KEY, this.warrantReleaseService
				.findAllFacilities());
		map.addAttribute(COUNTIES_MODEL_KEY, this.warrantReleaseService
				.findAllCounties());
		map.addAttribute(OFFENDER_MODEL_KEY, warrant.getOffender());
		this.offenderSummaryModelDelegate.add(map, warrant.getOffender());
		
		return new ModelAndView(RELEASE_VIEW_NAME, map);
	}
	
	/**
	 * Populates a WarrantReleaseForm with the properties of a WarrantRelease
	 * found with the specified Warrant
	 * @param warrant - Warrant the WarrantRelease is for
	 * @return Prepared WarrantReleaseForm
	 */
	private WarrantReleaseForm prepareWarrantReleaseForm(final Warrant warrant){
		WarrantRelease warrantRelease = this.warrantReleaseService
				.findByWarrant(warrant);
		WarrantReleaseForm form = new WarrantReleaseForm();
		
		if(warrantRelease != null){
			form.setAddressee(warrantRelease.getAddressee());
			form.setFacility(warrantRelease.getFacility());
			form.setCounty(warrantRelease.getCounty());
			form.setReleaseDate(warrantRelease.getReleaseTimestamp());
			form.setInstructions(warrantRelease.getInstructions());
			form.setClearedBy(warrantRelease.getClearSignature().getPerson());
			form.setClearedByDate(warrantRelease.getClearSignature().getDate());
		}
		
		return form;
	}
	
	/**
	 * Prepares a ModelAndView for the WarrantCancellation edit screen
	 * @param warrant - Warrant the WarrantCancellation is for
	 * @param form - WarrantCancellationForm
	 * @return ModelAndView for the WarrantCancellation edit screen
	 */
	private ModelAndView prepareWarrantCancellationMav(final Warrant warrant,
			final WarrantCancellationForm form){
		ModelMap map = new ModelMap();
		
		map.addAttribute(WARRANT_CANCELLATION_FORM_MODEL_KEY, form);
		map.addAttribute(WARRANT_CANCELLATION_MODEL_KEY,
				this.warrantCancellationService.findByWarrant(warrant));
		map.addAttribute(WARRANT_REASON_CATEGORY_MODEL_KEY,
				warrant.getWarrantReason());
		map.addAttribute(OFFENDER_MODEL_KEY, warrant.getOffender());
		this.offenderSummaryModelDelegate.add(map, warrant.getOffender());
		
		return new ModelAndView(CANCEL_VIEW_NAME, map);
	}
	
	/**
	 * Populates a WarrantCancellationForm with the properties of the
	 * WarrantCancellation found with the specified Warrant
	 * @param warrant - Warrant the WarrantCancellation is for
	 * @return Prepared WarrantCancellationForm
	 */
	private WarrantCancellationForm prepareWarrantCancellationForm(
			final Warrant warrant){
		WarrantCancellation warrantCancellation =
				this.warrantCancellationService.findByWarrant(warrant);
		WarrantCancellationForm form = new WarrantCancellationForm();
		
		if(warrantCancellation != null){
			form.setDate(warrantCancellation.getDate());
			form.setComment(warrantCancellation.getComment());
			form.setClearedBy(warrantCancellation.getClearSignature()
					.getPerson());
			form.setClearedByDate(warrantCancellation.getClearSignature()
					.getDate());
		}
		
		return form;
	}
	
	/**
	 * Processes WarrantNoteItems and WarrantCauseViolationItems for the
	 * specified Warrant
	 * @param warrant - Warrant
	 * @param warrantNoteItems - WarrantNoteItems to process
	 * @param warrantCauseViolationItems - WarrantCauseViolationItems to process
	 * @throws DuplicateEntityFoundException - When a WarrantCauseViolation 
	 * already exists with the given Condition and CourtCase, or a WarrantNote
	 * already exists with the given Note and Date, for the specified Warrant
	 */
	private void processWarrantItems(final Warrant warrant,
			final List<WarrantNoteItem> warrantNoteItems,
			final List<WarrantCauseViolationItem> warrantCauseViolationItems)
					throws DuplicateEntityFoundException{
		if(warrantNoteItems != null){
			for(WarrantNoteItem item : warrantNoteItems){
				if(WarrantItemOperation.CREATE.equals(
						item.getItemOperation())){
					this.warrantService.createWarrantNote(warrant,
							item.getNote(), item.getDate());
				}
				else if(WarrantItemOperation.UPDATE.equals(
						item.getItemOperation())){
					if(this.isNoteChanged(item.getWarrantNote(), item.getDate(),
							item.getNote())) {
						this.warrantService.updateWarrantNote(item.getWarrantNote(),
								item.getNote(), item.getDate());
					}
				}
				else if(WarrantItemOperation.REMOVE.equals(
						item.getItemOperation())){
					this.warrantService.removeWarrantNote(item.getWarrantNote());
				}
			}
		}
		
		if(warrantCauseViolationItems != null){
			for(WarrantCauseViolationItem item : warrantCauseViolationItems){
				if(WarrantItemOperation.CREATE.equals(
						item.getItemOperation())){
					this.warrantService.createWarrantCauseViolation(warrant,
							item.getCourtCase(), item.getCondition(),
							item.getDescription());
				}
				else if(WarrantItemOperation.UPDATE.equals(
						item.getItemOperation())){
					this.warrantService.updateWarrantCauseViolation(
							item.getWarrantCauseViolation(), item.getCourtCase(),
							item.getCondition(), item.getDescription());
				}
				else if(WarrantItemOperation.REMOVE.equals(
						item.getItemOperation())){
					this.warrantService.removeWarrantCauseViolation(
							item.getWarrantCauseViolation());
				}
			}
		}
	}
	
	/**
	 * Checks if a Warrant Note has been changed and returns true if it has
	 * @param note - Warrant Note to check for change
	 * @param date - Note Date from Form
	 * @param value - Note Value from Form
	 * @return Boolean - true if either the form's note date or value is different
	 * from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(final WarrantNote note,
			final Date date, final String value) {
		if(!note.getNote().equals(value)) {
			return true;
		}
		if(!(note.getDate().getTime() == date.getTime())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView
		(ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
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
		binder.registerCustomEditor(Warrant.class,
				this.warrantPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(WarrantRelease.class,
				this.warrantReleasePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(WarrantCancellation.class,
				this.warrantCancellationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(WarrantArrest.class,
				this.warrantArrestPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(WarrantNote.class,
				this.warrantNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(WarrantCauseViolation.class,
				this.warrantCauseViolationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				CourtCase.class,
				this.courtCasePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Condition.class,
				this.conditionPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Jail.class,
				this.jailPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				County.class,
				this.countyPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
	
}
