package omis.stg.web.controller;

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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.stg.service.SecurityThreatGroupAffiliationService;
import omis.stg.web.form.SecurityThreatGroupAffiliationForm;
import omis.stg.web.form.SecurityThreatGroupAffiliationNoteItem;
import omis.stg.web.form.SecurityThreatGroupAffiliationNoteItemOperation;
import omis.stg.web.validator.SecurityThreatGroupAffiliationFormValidator;
import omis.user.domain.UserAccount;

/**
 * Controller for managing security threat group affiliations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/stg/affiliation")
@PreAuthorize("hasRole('USER')")
public class ManageSecurityThreatGroupAffiliationController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "stg/affiliation/edit";
	
	private static final String AFFILIATION_NOTE_ITEM_ROW_VIEW_NAME
	= "stg/affiliation/includes/affiliationNoteItemTableRow";

	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/stg/list.html?offender=%d";
	
	/* Action menu view names. */
	
	private static final String AFFILIATION_ACTION_MENU_VIEW_NAME
		= "stg/includes/stgActionMenu";
	
	/* Model keys. */

	private static final String AFFILIATION_MODEL_KEY = "affiliation";
	
	private static final String SECURITY_THREAT_GROUP_AFFILIATION_FORM_MODEL_KEY
		= "securityThreatGroupAffiliationForm";

	private static final String SECURITY_THREAT_GROUPS_MODEL_KEY = "groups";

	private static final String SECURITY_THREAT_GROUP_ACTIVITY_LEVELS_MODEL_KEY
		= "activityLevels";

	private static final String SECURITY_THREAT_GROUP_CHAPTERS_MODEL_KEY
		= "chapters";

	private static final String SECURITY_THREAT_GROUP_RANKS_MODEL_KEY
		= "ranks";

	private static final String STATES_MODEL_KEY = "states";
	
	private static final String VERIFICATION_METHOD_MODEL_KEY
		= "verificationMethods";

	private static final String CITIES_MODEL_KEY = "cities";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String AFFILIATION_NOTE_ITEM_INDEX_MODEL_KEY
	= "affiliationNoteItemIndex";

	private static final String AFFILIATION_NOTE_ITEM_MODEL_KEY
	= "affiliationNoteItem";
	
	/* Field formats. */
	
	private static final String USER_ACCOUNT_LABEL_FORMAT = "%s, %s (%s)";
	
	/* Services. */
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationService")
	private SecurityThreatGroupAffiliationService
	securityThreatGroupAffiliationService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;

	@Autowired
	@Qualifier("securityThreatGroupPropertyEditorFactory")
	private PropertyEditorFactory securityThreatGroupPropertyEditorFactory;

	@Autowired
	@Qualifier("securityThreatGroupActivityLevelPropertyEditorFactory")
	private PropertyEditorFactory
	securityThreatGroupActivityLevelPropertyEditorFactory;

	@Autowired
	@Qualifier("securityThreatGroupAffiliationPropertyEditorFactory")
	private PropertyEditorFactory
	securityThreatGroupAffiliationPropertyEditorFactory;

	@Autowired
	@Qualifier("securityThreatGroupAffiliationNotePropertyEditorFactory")
	private PropertyEditorFactory
	securityThreatGroupAffiliationNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("securityThreatGroupChapterPropertyEditorFactory")
	private PropertyEditorFactory
	securityThreatGroupChapterPropertyEditorFactory;

	@Autowired
	@Qualifier("securityThreatGroupRankPropertyEditorFactory")
	private PropertyEditorFactory securityThreatGroupRankPropertyEditorFactory;
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("cityPropertyEditorFactory")
	private PropertyEditorFactory cityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("verificationMethodPropertyEditorFactory")
	private PropertyEditorFactory verificationMethodPropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationFormValidator")
	private SecurityThreatGroupAffiliationFormValidator
	securityThreatGroupAffiliationFormValidator;

	/* Helpers. */
	
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor. */
	
	/**
	 * Instantiation controller to manage security threat group affiliations.
	 */
	public ManageSecurityThreatGroupAffiliationController() {
		// Default instantiation
	}

	/**
	 * Displays form to create a new security group affiliation for offender.
	 * 
	 * @param offender offender
	 * @return screen to display form to create new security group affiliation
	 * for offender
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('STG_AFFILIATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		return prepareEditMav(offender,
				new SecurityThreatGroupAffiliationForm());
	}
	
	/**
	 * Displays form to update existing security group affiliation. 
	 * 
	 * @param affiliation security group affiliation to update
	 * @return screen for display form to update existing security group
	 * affiliation
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('STG_AFFILIATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "affiliation", required = true)
				final SecurityThreatGroupAffiliation affiliation) {
		SecurityThreatGroupAffiliationForm securityThreatGroupAffiliationForm =
				new SecurityThreatGroupAffiliationForm();
		if (affiliation.getDateRange() != null) {
			securityThreatGroupAffiliationForm.setStartDate(
					affiliation.getDateRange().getStartDate());
			securityThreatGroupAffiliationForm.setEndDate(
					affiliation.getDateRange().getEndDate());
		}
		securityThreatGroupAffiliationForm.setActivityLevel(
				affiliation.getActivityLevel());
		securityThreatGroupAffiliationForm.setChapter(affiliation.getChapter());
		securityThreatGroupAffiliationForm.setComment(affiliation.getComment());
		securityThreatGroupAffiliationForm.setGroup(affiliation.getGroup());
		securityThreatGroupAffiliationForm.setRank(affiliation.getRank());
		securityThreatGroupAffiliationForm.setMoniker(affiliation.getMoniker());
		securityThreatGroupAffiliationForm.setState(affiliation.getState());
		securityThreatGroupAffiliationForm.setCity(affiliation.getCity());
		if (affiliation.getVerificationSignature() != null) {
			securityThreatGroupAffiliationForm.setVerificationUserAccount(
					affiliation.getVerificationSignature().getUserAccount());
			if (affiliation.getVerificationSignature()
					.getUserAccount() != null) {
				UserAccount verificationUserAccount = 
						affiliation.getVerificationSignature()
							.getUserAccount();
				securityThreatGroupAffiliationForm
					.setVerificationUserAccountLabel(String.format(
							USER_ACCOUNT_LABEL_FORMAT,
					verificationUserAccount.getUser().getName().getLastName(),
					verificationUserAccount.getUser().getName().getFirstName(),
					verificationUserAccount.getUsername()));
			}
			securityThreatGroupAffiliationForm.setVerificationDate(
					affiliation.getVerificationSignature().getDate());
			securityThreatGroupAffiliationForm.setVerificationResult(
					affiliation.getVerificationSignature().getResult());
			securityThreatGroupAffiliationForm.setVerificationMethod(
					affiliation.getVerificationSignature().getMethod());
		}
		List<SecurityThreatGroupAffiliationNote> affiliationNotes = 
				this.securityThreatGroupAffiliationService.
				findAffiliationNotesByAffiliation(affiliation);
		List<SecurityThreatGroupAffiliationNoteItem> items 
			= new ArrayList<SecurityThreatGroupAffiliationNoteItem>(); 
		for (SecurityThreatGroupAffiliationNote affiliationNote 
				: affiliationNotes) {
			SecurityThreatGroupAffiliationNoteItem item = 
					new SecurityThreatGroupAffiliationNoteItem();
			item.setDate(affiliationNote.getDate());
			item.setNote(affiliationNote.getNote());
			item.setAffiliationNote(affiliationNote);
			item.setOperation(SecurityThreatGroupAffiliationNoteItemOperation
					.UPDATE);
			items.add(item);
		}
		securityThreatGroupAffiliationForm.setAffiliationNoteItems(items);
		ModelAndView mav = prepareEditMav(affiliation.getOffender(),
				securityThreatGroupAffiliationForm);
		mav.addObject(AFFILIATION_MODEL_KEY, affiliation);
		return mav;
	}
	
	/**
	 * Saves a new security threat group affiliation for the offender.
	 * 
	 * @param offender offender
	 * @param securityThreatGroupAffiliationForm form for security threat group
	 * affiliation
	 * @param result binding result
	 * @return redirect to list security threat group by offender
	 * @throws DuplicateEntityFoundException if the affiliation exists
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('STG_AFFILIATION_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final SecurityThreatGroupAffiliationForm
				securityThreatGroupAffiliationForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.securityThreatGroupAffiliationFormValidator.validate(
				securityThreatGroupAffiliationForm, result);
		if (result.hasErrors()) {
			return prepareRedisplayEditMav(offender,
					securityThreatGroupAffiliationForm,
					result);
		}
		SecurityThreatGroupChapter chapter;
		if (securityThreatGroupAffiliationForm.getCreateNewChapter()) {
			chapter = this.securityThreatGroupAffiliationService
					.createChapter(securityThreatGroupAffiliationForm
							.getChapterName(), 
							securityThreatGroupAffiliationForm.getGroup());
		} else {
			chapter = securityThreatGroupAffiliationForm.getChapter();
		}
		SecurityThreatGroupRank rank;
		if (securityThreatGroupAffiliationForm.getCreateNewRank()) {
			rank = this.securityThreatGroupAffiliationService.createRank(
					securityThreatGroupAffiliationForm.getRankName(),
					securityThreatGroupAffiliationForm.getGroup());
		} else {
			rank = securityThreatGroupAffiliationForm.getRank();
		}
		SecurityThreatGroupAffiliation affiliation =
		this.securityThreatGroupAffiliationService.create(
				offender,
				new DateRange(
						securityThreatGroupAffiliationForm.getStartDate(),
						securityThreatGroupAffiliationForm.getEndDate()),
				securityThreatGroupAffiliationForm.getGroup(),
				securityThreatGroupAffiliationForm.getActivityLevel(),
				chapter, rank,
				securityThreatGroupAffiliationForm.getState(),
				securityThreatGroupAffiliationForm.getCity(),
				securityThreatGroupAffiliationForm.getMoniker(),
				securityThreatGroupAffiliationForm.getComment(),
				new VerificationSignature(
						securityThreatGroupAffiliationForm
							.getVerificationUserAccount(),
						securityThreatGroupAffiliationForm
							.getVerificationDate(),
						securityThreatGroupAffiliationForm
							.getVerificationResult(),
						securityThreatGroupAffiliationForm
							.getVerificationMethod()));
		this.processSecurityThreatGroupAffiliationNoteItems(
				securityThreatGroupAffiliationForm.getAffiliationNoteItems(), 
				affiliation);		
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Displays a service term note item row.
	 * 
	 * @return model and view for security threat group affiliation note item row
	 */
	@RequestMapping(value = "createAffiliationNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayAffiliationNoteItemRow(@RequestParam(
			value = "affiliationNoteItemIndex", required = true)
			final Integer affiliationNoteItemIndex) {
		ModelMap map = new ModelMap();
		SecurityThreatGroupAffiliationNoteItem affiliationNoteItem
			= new SecurityThreatGroupAffiliationNoteItem();
		affiliationNoteItem.setOperation(
				SecurityThreatGroupAffiliationNoteItemOperation.CREATE);
		map.addAttribute(AFFILIATION_NOTE_ITEM_MODEL_KEY, affiliationNoteItem);
		map.addAttribute(AFFILIATION_NOTE_ITEM_INDEX_MODEL_KEY,
				affiliationNoteItemIndex);
		return new ModelAndView(AFFILIATION_NOTE_ITEM_ROW_VIEW_NAME, map);
	}

	/**
	 * Updates an existing security threat group affiliation.
	 * 
	 * @param affiliation security threat group affiliation
	 * @param securityThreatGroupAffiliationForm form for security threat group
	 * affiliation
	 * @param result binding result
	 * @return redirect to list security threat group by offender
	 * @throws DuplicateEntityFoundException if the affiliation exists
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('STG_AFFILIATION_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "affiliation", required = true)
				final SecurityThreatGroupAffiliation affiliation,
			final SecurityThreatGroupAffiliationForm
				securityThreatGroupAffiliationForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.securityThreatGroupAffiliationFormValidator.validate(
				securityThreatGroupAffiliationForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = prepareRedisplayEditMav(
					affiliation.getOffender(),
					securityThreatGroupAffiliationForm,
					result);
			mav.addObject(AFFILIATION_MODEL_KEY, affiliation);
			return mav;
		}
		SecurityThreatGroupChapter chapter;
		if (securityThreatGroupAffiliationForm.getCreateNewChapter()) {
			chapter = this.securityThreatGroupAffiliationService
					.createChapter(securityThreatGroupAffiliationForm
							.getChapterName(), 
							securityThreatGroupAffiliationForm.getGroup());
		} else {
			chapter = securityThreatGroupAffiliationForm.getChapter();
		}
		SecurityThreatGroupRank rank;
		if (securityThreatGroupAffiliationForm.getCreateNewRank()) {
			rank = this.securityThreatGroupAffiliationService.createRank(
					securityThreatGroupAffiliationForm.getRankName(),
					securityThreatGroupAffiliationForm.getGroup());
		} else {
			rank = securityThreatGroupAffiliationForm.getRank();
		}
		SecurityThreatGroupAffiliation stgAffiliation =
		this.securityThreatGroupAffiliationService.update(
				affiliation,
				new DateRange(
						securityThreatGroupAffiliationForm.getStartDate(),
						securityThreatGroupAffiliationForm.getEndDate()),
				securityThreatGroupAffiliationForm.getGroup(),
				securityThreatGroupAffiliationForm.getActivityLevel(),
				chapter, rank,
				securityThreatGroupAffiliationForm.getState(),
				securityThreatGroupAffiliationForm.getCity(),
				securityThreatGroupAffiliationForm.getMoniker(),
				securityThreatGroupAffiliationForm.getComment(),
				new VerificationSignature(
						securityThreatGroupAffiliationForm
							.getVerificationUserAccount(),
						securityThreatGroupAffiliationForm
							.getVerificationDate(),
						securityThreatGroupAffiliationForm
							.getVerificationResult(),
						securityThreatGroupAffiliationForm
							.getVerificationMethod()));
		this.processSecurityThreatGroupAffiliationNoteItems(
				securityThreatGroupAffiliationForm.getAffiliationNoteItems(), 
				stgAffiliation);	
		return new ModelAndView(String.format(LIST_REDIRECT,
				affiliation.getOffender().getId()));
	}
	
	/**
	 * Removes a security threat group affiliation.
	 * 
	 * @param affiliation security threat group affiliation to remove
	 * @return redirect to list security threat group by offender
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('STG_AFFILIATION_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "affiliation", required = true)
				final SecurityThreatGroupAffiliation affiliation) {
		List<SecurityThreatGroupAffiliationNote> affiliationNotes =
				this.securityThreatGroupAffiliationService
				.findAffiliationNotesByAffiliation(affiliation);
		for(SecurityThreatGroupAffiliationNote affiliationNote 
				: affiliationNotes) {
			this.securityThreatGroupAffiliationService
				.removeNote(affiliationNote);
		}
		Offender offender = affiliation.getOffender();
		this.securityThreatGroupAffiliationService.remove(affiliation);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/* Helper methods. */
	
	// Adds offender summary to model and view
	private void addOffenderSummary(final ModelAndView mav,
			final Offender offender) {
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
	}
	
	// Prepares edit model and view
	private ModelAndView prepareEditMav(final Offender offender,
			final SecurityThreatGroupAffiliationForm
			securityThreatGroupAffiliationForm) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(SECURITY_THREAT_GROUP_AFFILIATION_FORM_MODEL_KEY,
				securityThreatGroupAffiliationForm);
		List<SecurityThreatGroup> groups
			= this.securityThreatGroupAffiliationService.findGroups();
		mav.addObject(SECURITY_THREAT_GROUPS_MODEL_KEY, groups);
		List<SecurityThreatGroupActivityLevel> activityLevels
			= this.securityThreatGroupAffiliationService.findActivityLevels();
		mav.addObject(SECURITY_THREAT_GROUP_ACTIVITY_LEVELS_MODEL_KEY,
				activityLevels);
		if (securityThreatGroupAffiliationForm.getGroup() != null) {
			List<SecurityThreatGroupChapter> chapters 
				= this.securityThreatGroupAffiliationService
				.findChaptersByGroup(securityThreatGroupAffiliationForm
						.getGroup());
			mav.addObject(SECURITY_THREAT_GROUP_CHAPTERS_MODEL_KEY, chapters);
			List<SecurityThreatGroupRank> ranks
				= this.securityThreatGroupAffiliationService
				.findRanksByGroup(securityThreatGroupAffiliationForm
						.getGroup());
			mav.addObject(SECURITY_THREAT_GROUP_RANKS_MODEL_KEY, ranks);
		}
		List<State> states = this.securityThreatGroupAffiliationService
				.findStates();
		mav.addObject(STATES_MODEL_KEY, states);
		List<VerificationMethod> verificationMethods
			= this.securityThreatGroupAffiliationService
				.findVerificationMethods();
		mav.addObject(VERIFICATION_METHOD_MODEL_KEY, verificationMethods);
		if (securityThreatGroupAffiliationForm.getState() != null) {
			List<City> cities = this.securityThreatGroupAffiliationService
					.findCitiesByState(
							securityThreatGroupAffiliationForm.getState());
			mav.addObject(CITIES_MODEL_KEY, cities);
		}
		final Integer affiliationNoteIndex;
		if (securityThreatGroupAffiliationForm.getAffiliationNoteItems() 
				!= null) {
			affiliationNoteIndex = securityThreatGroupAffiliationForm
					.getAffiliationNoteItems().size(); 
		} else {
			affiliationNoteIndex = 0;
		}
		mav.addObject(AFFILIATION_NOTE_ITEM_INDEX_MODEL_KEY, 
				affiliationNoteIndex);
		addOffenderSummary(mav, offender);
		return mav;
	}
	
	// Prepares redisplay edit model and view
	private ModelAndView prepareRedisplayEditMav(
			final Offender offender,
			final SecurityThreatGroupAffiliationForm
				securityThreatGroupAffiliationForm,
			final BindingResult result) {
		if (securityThreatGroupAffiliationForm
				.getVerificationUserAccount() != null) {
			UserAccount verificationUserAccount = 
					securityThreatGroupAffiliationForm
						.getVerificationUserAccount();
			securityThreatGroupAffiliationForm
				.setVerificationUserAccountLabel(String.format(
						USER_ACCOUNT_LABEL_FORMAT,
				verificationUserAccount.getUser().getName().getLastName(),
				verificationUserAccount.getUser().getName().getFirstName(),
				verificationUserAccount.getUsername()));
		}
		ModelAndView mav = prepareEditMav(offender,
				securityThreatGroupAffiliationForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ SECURITY_THREAT_GROUP_AFFILIATION_FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Returns affiliation action menu. 
	 * 
	 * @param offender offender
	 * @return affiliation action menu
	 */
	@RequestMapping(
			value = "/affiliationActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showAffiliationActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(AFFILIATION_ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/*
	 * Processes the specified list of security threat group affiliation note 
	 * items according to their specified operation values.
	 * 
	 * @param items security threat group affiliation note items
	 * @param affiliation security threat group affiliation
	 * @throws DuplicateEntityFoundException thrown when a duplicate security
	 * threat group affiliation note is found
	 */
	private void processSecurityThreatGroupAffiliationNoteItems(
			final List<SecurityThreatGroupAffiliationNoteItem> items,
			final SecurityThreatGroupAffiliation affiliation)
		throws DuplicateEntityFoundException {
		if (items != null) {
			for (SecurityThreatGroupAffiliationNoteItem item 
					: items) {
				if (SecurityThreatGroupAffiliationNoteItemOperation.CREATE
						.equals(item.getOperation())) {
					this.securityThreatGroupAffiliationService
						.createNote(affiliation, item.getDate(), 
								item.getNote());
				} else if (SecurityThreatGroupAffiliationNoteItemOperation
						.UPDATE.equals(item.getOperation())) {
					this.securityThreatGroupAffiliationService.updateNote(
							item.getAffiliationNote(), item.getDate(),
							item.getNote());
				} else if (SecurityThreatGroupAffiliationNoteItemOperation
						.REMOVE.equals(item.getOperation())) {
					this.securityThreatGroupAffiliationService.removeNote(
							item.getAffiliationNote());
				}
			}
		}
	}
	
	/* Init binder. */
	
	/**
	 * Sets up init binder.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(SecurityThreatGroup.class,
				this.securityThreatGroupPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SecurityThreatGroupActivityLevel.class,
				this.securityThreatGroupActivityLevelPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SecurityThreatGroupAffiliation.class,
				this.securityThreatGroupAffiliationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SecurityThreatGroupChapter.class,
				this.securityThreatGroupChapterPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SecurityThreatGroupRank.class,
				this.securityThreatGroupRankPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(State.class,
				this.statePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(City.class,
				this.cityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(VerificationMethod.class,
				this.verificationMethodPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(UserAccount.class,
				this.userAccountPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SecurityThreatGroupAffiliationNote.class, 
				this.securityThreatGroupAffiliationNotePropertyEditorFactory
				.createPropertyEditor());
	}
}