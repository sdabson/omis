package omis.placementscreening.web.controller;

import java.util.Collections;
import java.util.Date;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;
import omis.placementscreening.exception.ReferralDateCategoryConflictException;
import omis.placementscreening.web.form.PlacementReferralForm;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** Controller for program referral related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Jan 12, 2015)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/placementScreening/referral")
@PreAuthorize("(hasRole('USER') and hasRole('PLACMENT_SCREENING_MODULE')"
		+ " and hasRole('PLACEMENT_REFERRAL_EDIT')) or hasRole('ADMIN')")
public class PlacementReferralController {
	private static final String FACILITY_OPTIONS_VIEW_NAME =
			"placementscreening/referral/includes/facilityOptions";
	private static final String INSIDE_RE_SCREENING_WINDOW_MESSAGE_KEY =
			"placementReferral.insideScreeningWindow";
	private static final String PRERELEASE_LIST_ACTION_MENU_VIEW_NAME =
			"placementscreening/referral/includes/prereleaseListActionMenu";
	private static final String PRERELEASE_SCREENING_TABLE_CONTENT_VIEW_NAME =
			"placementscreening/referral/includes/preReleaseTableBodyContent";
	private static final String PRERELEASE_SCREENING_TABLE_VIEW_NAME =
			"placementscreening/referral/includes/preReleaseTableBody";
	private static final String TREATMENT_LIST_ACTION_MENU_VIEW_NAME =
			"placementscreening/referral/includes/treatmentListActionMenu";
	/*private static final String TREATMENT_SCREENING_TABLE_CONTENT_VIEW_NAME =
			"placementscreening/referral/includes/treatmentTableBodyContent";*/
	private static final String TREATMENT_SCREENING_TABLE_VIEW_NAME =
			"placementscreening/referral/includes/treatmentTableBody";
	private static final String TREATMENT_TABLE_ROWS_VIEW_NAME = 
			"placementscreening/referral/includes/treatmentTableRows";
	private static final String REFER_TO_PLACEMENT_VIEW_NAME = 
			"placementscreening/referral/edit";
	//private static final String SCREENING_CENTER_REDIRECT = "Redirect: ....";
	private static final String PROGRAM_REFERRAL_ACTION_MENU_VIEW_NAME = 
			"placementscreening/referral/includes/actionMenu";
	private static final String PLACEMENT_REFERRAL_EXISTS_MESSAGE_KEY 
		= "placementReferral.exists";
	private static final String ERROR_BUNDLE_NAME = 
			"omis.placementscreening.msgs.form";
	
	//Delegates
	@Autowired
	private PlacementReferralControllerDelegate 
		placementReferralControllerDelegate;
	
	//Property Editors
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory facilityPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory 
		referralScreeningCenterPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;

	@Autowired
	private PropertyEditorFactory referralScreeningPropertyEditorFactory;
	
	//Validators
	@Autowired
	private Validator placementReferralFormValidator;

	//Business Exception handlers.
	@Autowired
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/** Creates program referral for screening.
	 * @param offender offender.
	 * @param sourceFacility source facility.
	 * @param programCategory program category.
	 * @return program referral create view. */
	@RequestContentMapping(nameKey = "placementReferralEditScreenName",
			descriptionKey = "placementReferralEditScreenDescription",
			messageBundle = "omis.placementScreening.msgs.programScreening",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(value = "offender", required = false)
			final Offender offender,
			@RequestParam(value = "facility", required = false)
			final Facility sourceFacility,
			@RequestParam(value = "programCategory", required = false)
			final ProgramCategory programCategory) {
		PlacementReferralForm form = new PlacementReferralForm();
		form.setReferralDate(new Date());
		form.setReferringFacility(sourceFacility);
		form.setProgramCategory(programCategory);
		form.setReferringFacility(sourceFacility);
		return new ModelAndView(REFER_TO_PLACEMENT_VIEW_NAME, 
				this.placementReferralControllerDelegate.prepareEditMap(
				offender, form));
	}
	
	/** Saves placement referral for screening.
	 * @param offender offender.
	 * @param facility facility.
	 * @param programCategory program category.
	 * @param form placement referral form.
	 * @param bindingResult binding result.
	 * @return redirect to screen tracking center.
	 * @throws DuplicateEntityFoundException when a current or open placement
	 * referral for placement exists for the categories.
	 * @throws ReferralDateCategoryConflictException when a referral is being 
	 * made inside the minimum rescreening date for a placement.*/
	@RequestContentMapping(nameKey = "placementReferralSaveScreenName",
			descriptionKey = "placementReferralSaveScreenDescription",
			messageBundle = "omis.placementScreening.msgs.placementScreening",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
			@RequestParam(value = "offender", required = false)
			final Offender offender,
			@RequestParam(value = "facility", required = false)
			final Facility facility,
			@RequestParam(value = "programCategory", required = false)
			final ProgramCategory programCategory,
			final PlacementReferralForm form,
			final BindingResult bindingResult) 
		throws DuplicateEntityFoundException,
		ReferralDateCategoryConflictException {
		
		ModelAndView result;
		if (form.getPrereleaseScreeningItems() != null) {
			Collections.sort(form.getPrereleaseScreeningItems());
		}
		if (form.getTreatmentScreeningItems() != null) {
			Collections.sort(form.getTreatmentScreeningItems());
		}
		
		this.placementReferralFormValidator.validate(form, 
				bindingResult);
		
		if (bindingResult.hasErrors()) {
			result = new ModelAndView(REFER_TO_PLACEMENT_VIEW_NAME, 
					this.placementReferralControllerDelegate.prepareEditMap(
							offender, form));
		} else {
			this.placementReferralControllerDelegate.setUpPlacementScreening(
					offender, form);
			result = null;
		}
		
		return result;
	}
	
	
	/** Gets action menu for placement referral.
	 * @return action menu content. */
	@RequestContentMapping(nameKey = "placementReferralActionMenuName",
			descriptionKey = "placementReferralActionMenuDescription",
			messageBundle = "omis.placementscreening.msgs.programScreening",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/referralActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView referralActionMenu() {
		return new ModelAndView(PROGRAM_REFERRAL_ACTION_MENU_VIEW_NAME);
	}
	
	/** Gets action menu for treatment list.
	 * @return action menu content. */
	@RequestContentMapping(nameKey = "treatmentListActionMenuName",
			descriptionKey = "treatementListActionMenuDescription",
			messageBundle = "omis.placementscreening.msgs.programScreening",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/treatmentListActionMenu.html",
		method = RequestMethod.GET)
	public ModelAndView treatmentListActionMenu() {
		return new ModelAndView(TREATMENT_LIST_ACTION_MENU_VIEW_NAME,
				this.placementReferralControllerDelegate
				.prepareTreatmentActionMenu());
	}
	
	/** Gets action menu for prerelease list.
	 * @return action menu content. */
	@RequestContentMapping(nameKey = "prereleaseListActionMenuName",
			descriptionKey = "prereleaseListActionzzzzzMenuDescription",
			messageBundle = "omis.placementscreening.msgs.programScreening",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/prereleaseListActionMenu.html",
		method = RequestMethod.GET)
	public ModelAndView prereleaseListActionMenu() {
		return new ModelAndView(PRERELEASE_LIST_ACTION_MENU_VIEW_NAME);
	}
	
	/** Gets facility list by program category and offender. 
	 * @param programCategory program category.
	 * @param offender offender. 
	 * @return facility list. */
	@RequestContentMapping(nameKey = "facilitiesByProgramCategoryName",
			descriptionKey = "facilitiesByProgramCategoryDescription",
			messageBundle = "omis.placementscreening.msgs.programScreening",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/facilitiesByProgramCategory.html",
			method = RequestMethod.GET)
	public ModelAndView lookupFacilityByCategory(
			@RequestParam(value = "programCategory", required = true)
			final ProgramCategory programCategory,
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		
		return new ModelAndView(FACILITY_OPTIONS_VIEW_NAME, 
				this.placementReferralControllerDelegate
				.prepareFacilityOptionsMap(programCategory, 
						offender.getIdentity().getSex()));
	}
	
	/** Queues screening by referralScreening Center and offender.
	 * @param referralScreeningCenter referral screening center.
	 * @param offender offender. 
	 * @return returns initial screening. */
	@RequestContentMapping(nameKey = "queueScreeningName",
			descriptionKey = "queuScreeningDescription",
			messageBundle = "omis.programscreening.msgs.programScreening",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "queueScreening.html", method = RequestMethod.GET)
	public ModelAndView queueScreening(
			@RequestParam(value = "referralScreeningCenter", required = true) 
			final ReferralScreeningCenter referralScreeningCenter, 
			@RequestParam(value = "offender", required = true) 
			final Offender offender) {
		ModelAndView mav;
		
		if (referralScreeningCenter.getProgramCategory().getTreatmentFlag()) {
			mav = new ModelAndView(TREATMENT_SCREENING_TABLE_VIEW_NAME, 
					this.placementReferralControllerDelegate
					.prepareScreeningQueue(referralScreeningCenter, 
							offender.getIdentity().getSex()));
		} else {
			mav = new ModelAndView(PRERELEASE_SCREENING_TABLE_VIEW_NAME,
					this.placementReferralControllerDelegate
					.prepareScreeningQueue(referralScreeningCenter, 
							offender.getIdentity().getSex()));
		}
		
		return mav;
	}
	/** Adds prerelease screening item.
	 * @param index index;
	 * @param offender offender;
	 * @return prerelease screening item. */
	@RequestContentMapping(nameKey = "addPrereleaseScreeningName",
			descriptionKey = "addPrereleaseScreeningDescription",
			messageBundle = "omis.placementscreening.msgs.PlacementScreening",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "addPrereleaseScreening.html", 
		method = RequestMethod.GET)
	public ModelAndView addPrereleaseScreening(
			@RequestParam(value = "index", required = true)
			final Integer index,
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		return new ModelAndView(PRERELEASE_SCREENING_TABLE_CONTENT_VIEW_NAME,
				this.placementReferralControllerDelegate
				.preparePrereleaseScreeningItemMap(index, 
						offender.getIdentity().getSex()));
	}
	
	/** Adds treatment screening item.
	 * @param index index,
	 * @param offender offender,
	 * @param programCategory program category.
	 * @return treatment screening item. */
	@RequestContentMapping(nameKey = "addTreatmentScreeningName",
			descriptionKey = "addTreatmentScreeningDescription",
			messageBundle = "omis.placementscreening.msgs.PlacementScreening",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "addTreatmentScreening.html",
		method = RequestMethod.GET)
	public ModelAndView addTreatmentScreening(
			@RequestParam(value = "index", required = true)
			final Integer index,
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "category", required = true)
			final ProgramCategory programCategory) {
		return new ModelAndView(TREATMENT_TABLE_ROWS_VIEW_NAME,
						this.placementReferralControllerDelegate
						.prepareTreatmentScreeningItemMap(index, 
								programCategory, 
								offender.getIdentity().getSex()));
	}
	
	/** Handles duplicate entity found exceptions.
	 * @param duplicateEntityFoundException duplicate entity found exception.
	 * @return view to handle duplicate entity found exception. */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoudnException(
			final DuplicateEntityFoundException duplicateEntityFoundException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PLACEMENT_REFERRAL_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				duplicateEntityFoundException);
	}
	
	/** Handles referral date category conflict exceptions, when a referral for 
	 * screening is made inside the designated time range.
	 * @param referralDateCategoryConflictException date category 
	 * conflict exception.
	 * @return view to handle duplicate entity found exception. */ 
	@ExceptionHandler(ReferralDateCategoryConflictException.class)
	public ModelAndView handleReferralDateCategoryConflictException(
			final ReferralDateCategoryConflictException 
			referralDateCategoryConflictException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				INSIDE_RE_SCREENING_WINDOW_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				referralDateCategoryConflictException);
	}
	
	/** Init binder binds property editors.
	 * @param binder binder. */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(Facility.class, 
				this.facilityPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(ReferralScreeningCenter.class,
				this.referralScreeningCenterPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(UserAccount.class,
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(false));
		
		binder.registerCustomEditor(ReferralScreening.class,
				this.referralScreeningPropertyEditorFactory
				.createPropertyEditor());
	}
}
