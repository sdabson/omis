package omis.grievance.web.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.grievance.service.GrievanceService;
import omis.grievance.web.form.GrievanceDispositionItem;
import omis.grievance.web.form.GrievanceForm;
import omis.grievance.web.validator.GrievanceFormValidator;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for managing grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/grievance")
@PreAuthorize("hasRole('USER')")
public class ManageGrievanceController {

	/* View names. */

	private static final String VIEW_NAME = "grievance/edit";
	
	private static final String BOOLEAN_VALUE_VIEW_NAME
		= "common/json/booleanValue";
	
	private static final String DATE_VALUE_VIEW_NAME
		= "common/json/dateValue";
	
	private static final String COMPLAINT_CATEGORIES_VIEW_NAME
		= "grievance/includes/complaintCategories";
	
	private static final String DISPOSITION_REASONS_VIEW_NAME
		= "grievance/includes/dispositionReasons";
	
	private static final String USER_ACCOUNTS_VIEW_NAME
		= "user/json/userAccounts";
	
	/* Action menu. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "grievance/includes/grievanceActionMenu";
	
	/* Model keys. */
	
	private static final String FORM_MODEL_KEY = "grievanceForm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String LOCATION_MODEL_KEY = "location";

	private static final String UNITS_MODEL_KEY = "units";

	private static final String SUBJECT_MODEL_KEY = "subject";

	private static final String COMPLAINT_CATEGORIES_MODEL_KEY
		= "complaintCategories";
	
	private static final String GRIEVANCE_MODEL_KEY = "grievance";

	private static final String LOCATIONS_MODEL_KEY = "locations";
	
	private static final String COORDINATOR_LEVEL_DISPOSITION_STATUSES_MODEL_KEY
		= "coordinatorLevelDispositionStatuses";

	private static final String WARDEN_FHA_LEVEL_DISPOSITION_STATUSES_MODEL_KEY
		= "wardenFhaLevelDispositionStatuses";

	private static final String DIRECTOR_LEVEL_DISPOSITION_STATUSES_MODEL_KEY
		= "directorLevelDispositionStatuses";
	
	private static final String USER_ACCOUNTS_MODEL_KEY = "userAccounts";
	
	private static final String COORDINATOR_LEVEL_DISPOSITION_REASONS_MODEL_KEY
		= "coordinatorLevelDispositionReasons";
	
	private static final String WARDEN_FHA_LEVEL_DISPOSITION_REASONS_MODEL_KEY
		= "wardenFhaLevelDispositionReasons";
	
	private static final String DIRECTOR_LEVEL_DISPOSITION_REASONS_MODEL_KEY
		= "directorLevelDispositionReasons";
	
	private static final String BOOLEAN_VALUE_MODEL_KEY = "booleanValue";
	
	private static final String DISPOSITION_REASONS_MODEL_KEY
		= "dispositionReasons";

	private static final String DEFAULT_DISPOSITION_REASON_MODEL_KEY
		= "defaultDispositionReason";
	
	/* Redirects. */
	
	private static final String LIST_BY_OFFENDER_REDIRECT
		= "redirect:/grievance/listByOffender.html?offender=%d";
	
	/* Message keys. */
	
	private static final String GRIEVANCE_EXISTS_MESSAGE_KEY
		= "grievance.exists";
	
	/* Message bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.grievance.msgs.form";

	private static final String DATE_VALUE_PARAM_NAME = "dateValue";
	
	/* Services. */
	
	@Autowired
	@Qualifier("grievanceService")
	private GrievanceService grievanceService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("grievancePropertyEditorFactory")
	private PropertyEditorFactory grievancePropertyEditorFactory;

	@Autowired
	@Qualifier("grievanceSubjectPropertyEditorFactory")
	private PropertyEditorFactory grievanceSubjectPropertyEditorFactory;
	
	@Autowired
	@Qualifier("grievanceComplaintCategoryPropertyEditorFactory")
	private PropertyEditorFactory
	grievanceComplaintCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("grievanceUnitPropertyEditorFactory")
	private PropertyEditorFactory grievanceUnitPropertyEditorFactory;
	
	@Autowired
	@Qualifier("grievanceLocationPropertyEditorFactory")
	private PropertyEditorFactory grievanceLocationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("grievanceDispositionPropertyEditorFactory")
	private PropertyEditorFactory grievanceDispositionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("grievanceDispositionStatusPropertyEditorFactory")
	private PropertyEditorFactory
	grievanceDispositionStatusPropertyEditorFactory;
	

	@Autowired
	@Qualifier("grievanceDispositionReasonPropertyEditorFactory")
	private PropertyEditorFactory
	grievanceDispositionReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory; 
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("grievanceFormValidator")
	private GrievanceFormValidator grievanceFormValidator;
	
	/* Constructors. */
	
	/** Instantiates controller for managing grievances. */
	public ManageGrievanceController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to create grievance.
	 * 
	 * @param offender offender
	 * @param subject subject
	 * @return screen to create grievance
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_CREATE')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "subject", required = true)
				final GrievanceSubject subject) {
		GrievanceForm grievanceForm = new GrievanceForm();
		grievanceForm.setEdit(true);
		grievanceForm.setClosed(false);
		GrievanceDispositionItem dispositionItem
			= new GrievanceDispositionItem();
		dispositionItem.setEdit(true);
		dispositionItem.setClosedDateAllowed(true);
		dispositionItem.setCurrentLevel(true);
		dispositionItem.setRequired(true);
		if (GrievanceDispositionLevel.COORDINATOR.equals(
				subject.getStartLevel())) {
			grievanceForm.setCoordinatorLevelDispositionItem(dispositionItem);
			dispositionItem.setStatus(this.grievanceService
					.findPendingDispositionStatusForLevel(
							GrievanceDispositionLevel.COORDINATOR));
			dispositionItem.setAllowAppealDate(true);
		} else if (GrievanceDispositionLevel.FHA.equals(
				subject.getStartLevel())) {
			grievanceForm.setWardenFhaLevelDispositionItem(dispositionItem);
			dispositionItem.setStatus(this.grievanceService
					.findPendingDispositionStatusForLevel(
							GrievanceDispositionLevel.FHA));
			dispositionItem.setAllowAppealDate(true);
		} else if (GrievanceDispositionLevel.WARDEN.equals(
				subject.getStartLevel())) {
			grievanceForm.setWardenFhaLevelDispositionItem(dispositionItem);
			dispositionItem.setStatus(this.grievanceService
					.findPendingDispositionStatusForLevel(
							GrievanceDispositionLevel.WARDEN));
			dispositionItem.setAllowAppealDate(true);
		} else if (GrievanceDispositionLevel.DIRECTOR.equals(
				subject.getStartLevel())) {
			grievanceForm.setDirectorLevelDispositionItem(dispositionItem);
			dispositionItem.setStatus(this.grievanceService
					.findPendingDispositionStatusForLevel(
							GrievanceDispositionLevel.DIRECTOR));
		} else {
			throw new UnsupportedOperationException(
					String.format("%s start level not supported",
							subject.getStartLevel()));
		}
		return this.prepareEditMav(offender, subject, grievanceForm);
	}
	
	/**
	 * Shows screen to edit grievance.
	 * 
	 * @param grievance grievance to edit
	 * @return screen to edit grievance
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_VIEW')")
	public ModelAndView edit(
			@RequestParam(value = "grievance", required = true)
				final Grievance grievance) {
		GrievanceForm grievanceForm = new GrievanceForm();
		grievanceForm.setOpenedDate(grievance.getOpenedDate());
		grievanceForm.setInformalFileDate(grievance.getInformalFileDate());
		grievanceForm.setDescription(grievance.getDescription());
		grievanceForm.setInitialComment(grievance.getInitialComment());
		grievanceForm.setLocation(grievance.getLocation());
		grievanceForm.setUnit(grievance.getUnit());
		grievanceForm.setComplaintCategory(grievance.getComplaintCategory());
		grievanceForm.setEdit(false);
		boolean closed;
		if (grievance.getClosedDate() != null) {
			closed = true;
		} else {
			closed = false;
		}
		grievanceForm.setClosed(closed);
		GrievanceDispositionLevel startLevel
			= grievance.getSubject().getStartLevel();
		GrievanceDispositionItem currentLevelDispositionItem;
		GrievanceDisposition coordinatorLevelDisposition
			= this.grievanceService.findCoordinatorLevelDisposition(grievance);
		if (coordinatorLevelDisposition == null
				&& GrievanceDispositionLevel.COORDINATOR.equals(startLevel)
				&& !closed) {
			currentLevelDispositionItem
				= new GrievanceDispositionItem();
			grievanceForm.setCoordinatorLevelDispositionItem(
					currentLevelDispositionItem);
			currentLevelDispositionItem.setStatus(this.grievanceService
					.findPendingDispositionStatusForLevel(
							GrievanceDispositionLevel.COORDINATOR));
		} else if (coordinatorLevelDisposition != null) {
			currentLevelDispositionItem =
				this.createDispositionItem(coordinatorLevelDisposition,
						false, false);
			grievanceForm.setCoordinatorLevelDispositionItem(
					currentLevelDispositionItem);
			if (coordinatorLevelDisposition.getStatus() != null
					&& coordinatorLevelDisposition.getStatus().getClosed()
					&& !closed) {
				closed = true;
			}
		} else {
			currentLevelDispositionItem = null;
		}
		GrievanceDisposition wardenFhaLevelDisposition
			= this.grievanceService.findWardenLevelDisposition(grievance);
		if (wardenFhaLevelDisposition == null) {
			wardenFhaLevelDisposition
				= this.grievanceService.findFhaLevelDisposition(grievance);
		}
		if (wardenFhaLevelDisposition == null
				&& (coordinatorLevelDisposition == null
						|| coordinatorLevelDisposition.getAppealDate() != null)
				&& (currentLevelDispositionItem == null
					|| currentLevelDispositionItem.getDisposition() != null)
				&& (GrievanceDispositionLevel.WARDEN.equals(startLevel)
					|| GrievanceDispositionLevel.WARDEN.isAfter(startLevel)	
					|| GrievanceDispositionLevel.FHA.equals(startLevel)
					|| GrievanceDispositionLevel.FHA.isAfter(startLevel))
				&& !closed) {
			currentLevelDispositionItem = new GrievanceDispositionItem();
			currentLevelDispositionItem.setAllowAppealDate(true);
			grievanceForm.setWardenFhaLevelDispositionItem(
					currentLevelDispositionItem);
			if (!grievance.getSubject().getMedical()) {
				currentLevelDispositionItem.setStatus(this.grievanceService
					.findPendingDispositionStatusForLevel(
							GrievanceDispositionLevel.WARDEN));
			} else {
				currentLevelDispositionItem.setStatus(this.grievanceService
						.findPendingDispositionStatusForLevel(
								GrievanceDispositionLevel.FHA));
			}
		} else if (wardenFhaLevelDisposition != null) {
			currentLevelDispositionItem =
					this.createDispositionItem(wardenFhaLevelDisposition,
							false, false);
			grievanceForm.setWardenFhaLevelDispositionItem(
					currentLevelDispositionItem);
			if (wardenFhaLevelDisposition.getStatus() != null
					&& wardenFhaLevelDisposition.getStatus().getClosed()
					&& !closed) {
				closed = true;
			}
		}
		GrievanceDisposition directorLevelDisposition
			= this.grievanceService.findDirectorLevelDisposition(grievance);
		if (directorLevelDisposition == null
				&& (coordinatorLevelDisposition == null
					|| coordinatorLevelDisposition.getAppealDate() != null)
				&& (wardenFhaLevelDisposition == null
					|| wardenFhaLevelDisposition.getAppealDate() != null)
				&& (currentLevelDispositionItem == null
					|| currentLevelDispositionItem.getDisposition() != null)
				&& (GrievanceDispositionLevel.DIRECTOR.equals(startLevel)
					|| GrievanceDispositionLevel.DIRECTOR.isAfter(startLevel))
				&& !closed) {
			currentLevelDispositionItem = new GrievanceDispositionItem();
			currentLevelDispositionItem.setAllowAppealDate(false);
			grievanceForm.setDirectorLevelDispositionItem(
					currentLevelDispositionItem);
			currentLevelDispositionItem.setStatus(this.grievanceService
					.findPendingDispositionStatusForLevel(
							GrievanceDispositionLevel.DIRECTOR));
		} else if (directorLevelDisposition != null) {
			currentLevelDispositionItem = 
					this.createDispositionItem(directorLevelDisposition,
							false, false);
			grievanceForm.setDirectorLevelDispositionItem(
					currentLevelDispositionItem);
			if (directorLevelDisposition.getStatus() != null
					&& directorLevelDisposition.getStatus().getClosed()
					&& !closed) {
				closed = true;
			}
		}
		if (currentLevelDispositionItem != null) {
			if (grievance.getSubject() != null
					&& currentLevelDispositionItem.getDisposition() != null
					&& grievance.getSubject().getStartLevel().equals(
							currentLevelDispositionItem.getDisposition()
								.getLevel())
					&& grievance.getOpenedDate() != null) {
				Date responseDueDate = this.grievanceService
					.calculateResponseDueDate(grievance.getOpenedDate());
				currentLevelDispositionItem.setResponseDueDate(responseDueDate);
			}
			currentLevelDispositionItem.setCurrentLevel(true);
			currentLevelDispositionItem.setEdit(true);
			currentLevelDispositionItem.setClosedDate(
				grievance.getClosedDate());
			currentLevelDispositionItem.setClosedDateAllowed(true);
			if (currentLevelDispositionItem.getDisposition() == null) {
				if (currentLevelDispositionItem
						== grievanceForm.getDirectorLevelDispositionItem()) {
					if (grievanceForm.getWardenFhaLevelDispositionItem()
							!= null) {
						grievanceForm.getWardenFhaLevelDispositionItem()
							.setClosedDateAllowed(true);
					}
				} else if (currentLevelDispositionItem
						== grievanceForm.getWardenFhaLevelDispositionItem()) {
					if (grievanceForm.getCoordinatorLevelDispositionItem()
							!= null) {
						grievanceForm.getCoordinatorLevelDispositionItem()
							.setClosedDateAllowed(true);
					}
				}
			}
		}
		ModelAndView mav
			= this.prepareEditMav(grievance.getOffender(),
					grievance.getSubject(), grievanceForm);
		mav.addObject(GRIEVANCE_MODEL_KEY, grievance);
		return mav;
	}
	
	/**
	 * Creates grievance.
	 * 
	 * @param offender offender
	 * @param subject subject
	 * @param grievanceForm grievance form
	 * @param result binding result
	 * @return redirect to list grievances for offender
	 * @throws DuplicateEntityFoundException if grievance exists
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_CREATE')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "subject", required = true)
				final GrievanceSubject subject,
			final GrievanceForm grievanceForm,
			final BindingResult result)
					throws DuplicateEntityFoundException {
		this.grievanceFormValidator.validate(grievanceForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(
					offender, subject, grievanceForm, result);
		}
		Grievance grievance = this.grievanceService.create(offender,
						grievanceForm.getLocation(),
						grievanceForm.getUnit(),
						grievanceForm.getOpenedDate(),
						grievanceForm.getInformalFileDate(),
						subject,
						grievanceForm.getComplaintCategory(),
						grievanceForm.getDescription(),
						grievanceForm.getInitialComment());
		if (GrievanceDispositionLevel.COORDINATOR.equals(
					subject.getStartLevel())
				&& grievanceForm.getCoordinatorLevelDispositionItem()
					.getEdit() != null
				&& grievanceForm.getCoordinatorLevelDispositionItem()
					.getEdit()) {
			this.grievanceService.createCoordinatorLevelDisposition(
					grievance,
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getStatus(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getClosedDate(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getReason(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getReceivedDate(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getResponseDueDate(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getResponseBy(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getResponseToOffenderDate(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getAppealDate(),
					grievanceForm.getCoordinatorLevelDispositionItem()
						.getNotes());
		} else if (GrievanceDispositionLevel.FHA.equals(
					subject.getStartLevel())
				&& grievanceForm.getWardenFhaLevelDispositionItem()
					.getEdit() != null
				&& grievanceForm.getWardenFhaLevelDispositionItem()
					.getEdit()) {
			this.grievanceService.createFhaLevelDisposition(
					grievance,
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getStatus(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getClosedDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getReason(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getReceivedDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getResponseDueDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getResponseBy(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getResponseToOffenderDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getAppealDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getNotes());
		} else if (GrievanceDispositionLevel.WARDEN.equals(
					subject.getStartLevel())
				&& grievanceForm.getWardenFhaLevelDispositionItem()
					.getEdit() != null
				&& grievanceForm.getWardenFhaLevelDispositionItem()
					.getEdit()) {
			this.grievanceService.createWardenLevelDisposition(
					grievance,
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getStatus(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getClosedDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getReason(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getReceivedDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getResponseDueDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getResponseBy(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getResponseToOffenderDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getAppealDate(),
					grievanceForm.getWardenFhaLevelDispositionItem()
						.getNotes());
		} else if (GrievanceDispositionLevel.DIRECTOR.equals(
					subject.getStartLevel())
				&& grievanceForm.getDirectorLevelDispositionItem()
					.getEdit() != null
				&& grievanceForm.getDirectorLevelDispositionItem()
					.getEdit()) {
			this.grievanceService.createDirectorLevelDisposition(grievance,
					grievanceForm.getDirectorLevelDispositionItem()
						.getStatus(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getClosedDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getReason(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getReceivedDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getResponseDueDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getResponseBy(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getResponseToOffenderDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getNotes());
		}
		return new ModelAndView(String.format(
				LIST_BY_OFFENDER_REDIRECT, offender.getId()));
	}
	
	/**
	 * Updates grievance.
	 * 
	 * @param grievance grievance to update
	 * @param grievanceForm form for grievance
	 * @param result result
	 * @return redirect to list grievances for offender
	 * @throws DuplicateEntityFoundException if grievance exists
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_EDIT')")
	public ModelAndView update(
			@RequestParam(value = "grievance", required = true)
				final Grievance grievance,
			final GrievanceForm grievanceForm,
			final BindingResult result)
					throws DuplicateEntityFoundException {
		this.grievanceFormValidator.validate(grievanceForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
					grievance.getOffender(), grievance.getSubject(),
					grievanceForm, result);
			mav.addObject(GRIEVANCE_MODEL_KEY, grievance);
			return mav;
		}
		if (grievanceForm.getEdit() != null && grievanceForm.getEdit()) {
			this.grievanceService.update(grievance,
					grievanceForm.getLocation(),
					grievanceForm.getUnit(),
					grievanceForm.getOpenedDate(),
					grievanceForm.getInformalFileDate(),
					grievanceForm.getComplaintCategory(),
					grievanceForm.getDescription(),
					grievanceForm.getInitialComment());
		}
		if (grievanceForm.getCoordinatorLevelDispositionItem() != null
				&& grievanceForm.getCoordinatorLevelDispositionItem()
					.getEdit() != null
				&& grievanceForm.getCoordinatorLevelDispositionItem()
					.getEdit()) {
			if (grievanceForm.getCoordinatorLevelDispositionItem()
					.getDisposition() != null) {
				this.grievanceService.updateCoordinatorLevelDisposition(
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getDisposition(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getStatus(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getClosedDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getReason(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getReceivedDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getResponseDueDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getResponseBy(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getResponseToOffenderDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getAppealDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getNotes());
			} else {
				this.grievanceService.createCoordinatorLevelDisposition(
						grievance,
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getStatus(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getClosedDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getReason(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getReceivedDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getResponseDueDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getResponseBy(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getResponseToOffenderDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getAppealDate(),
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getNotes());
			}
		}
		if (grievanceForm.getWardenFhaLevelDispositionItem() != null
				&& grievanceForm.getWardenFhaLevelDispositionItem()
					.getEdit() != null
				&& grievanceForm.getWardenFhaLevelDispositionItem().getEdit()) {
			if (grievanceForm.getWardenFhaLevelDispositionItem()
					.getDisposition() != null) {
				if (grievance.getSubject().getMedical()) {
					this.grievanceService.updateFhaLevelDisposition(
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getDisposition(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getStatus(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getClosedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReason(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReceivedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseDueDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseBy(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseToOffenderDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getAppealDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getNotes());
				} else {
					this.grievanceService.updateWardenLevelDisposition(
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getDisposition(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getStatus(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getClosedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReason(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReceivedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseDueDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseBy(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseToOffenderDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getAppealDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getNotes());
				}
			} else {
				if (grievance.getSubject().getMedical()) {
					this.grievanceService.createFhaLevelDisposition(
							grievance,
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getStatus(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getClosedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReason(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReceivedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseDueDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseBy(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseToOffenderDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getAppealDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getNotes());
				} else {
					this.grievanceService.createWardenLevelDisposition(
							grievance,
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getStatus(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getClosedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReason(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getReceivedDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseDueDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseBy(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getResponseToOffenderDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getAppealDate(),
							grievanceForm.getWardenFhaLevelDispositionItem()
								.getNotes());
				}
			}
		}
		if (grievanceForm.getDirectorLevelDispositionItem() != null
				&& grievanceForm.getDirectorLevelDispositionItem()
					.getEdit() != null
				&& grievanceForm.getDirectorLevelDispositionItem()
					.getEdit()) {
			if (grievanceForm.getDirectorLevelDispositionItem()
					.getDisposition() != null) {
				this.grievanceService.updateDirectorLevelDisposition(
						grievanceForm.getDirectorLevelDispositionItem()
							.getDisposition(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getStatus(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getClosedDate(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getReason(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getReceivedDate(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getResponseDueDate(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getResponseBy(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getResponseToOffenderDate(),
						grievanceForm.getDirectorLevelDispositionItem()
							.getNotes());
			} else {
				this.grievanceService.createDirectorLevelDisposition(grievance,
					grievanceForm.getDirectorLevelDispositionItem()
						.getStatus(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getClosedDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getReason(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getReceivedDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getResponseDueDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getResponseBy(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getResponseToOffenderDate(),
					grievanceForm.getDirectorLevelDispositionItem()
						.getNotes());
			}
		}
		return new ModelAndView(String.format(LIST_BY_OFFENDER_REDIRECT,
				grievance.getOffender().getId()));
	}
	
	/**
	 * Removes grievance.
	 * 
	 * @param grievance grievance
	 * @return redirect to listing screen
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_REMOVE')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "grievance", required = true)
				final Grievance grievance) {
		Offender offender = grievance.getOffender();
		this.grievanceService.remove(grievance);
		return new ModelAndView(String.format(LIST_BY_OFFENDER_REDIRECT,
				offender.getId()));
	}
	
	/* AJAX invokable methods. */
	

	/**
	 * Returns calculated response due date for opened date.
	 * 
	 * @param openedDate opened date from which to calculate response due date
	 * @return calculated response due date
	 */
	@RequestMapping(
			value = "/calculateResponseDueDate.json",
			method = RequestMethod.GET)
	public ModelAndView calculateResponseDueDate(
			@RequestParam(value = "openedDate", required = true)
				final Date openedDate) {
		Date responseDueDate = this.grievanceService
				.calculateResponseDueDate(openedDate);
		ModelAndView mav = new ModelAndView(DATE_VALUE_VIEW_NAME);
		mav.addObject(DATE_VALUE_PARAM_NAME, responseDueDate);
		return mav;
	}
	
	/**
	 * Shows grievance complaint categories by subject.
	 * 
	 * @param subject subject
	 * @return grievance complaint categories by subject
	 */
	@RequestMapping(
			value = "/findComplaintCategories.html", method = RequestMethod.GET)
	public ModelAndView findComplaintCategories(
			@RequestParam(value = "subject", required = true)
				final GrievanceSubject subject) {
		List<GrievanceComplaintCategory> complaintCategories;
		if (subject != null) {
			complaintCategories = this.grievanceService
					.findComplaintCategoriesBySubject(subject);
		} else {
			complaintCategories = Collections.emptyList();
		}
		ModelAndView mav = new ModelAndView(COMPLAINT_CATEGORIES_VIEW_NAME);
		mav.addObject(COMPLAINT_CATEGORIES_MODEL_KEY, complaintCategories);
		return mav;
	}
	
	/**
	 * Searches user accounts.
	 * 
	 * @param query query
	 * @return user accounts as JSON
	 */
	@RequestMapping(value = "/searchUserAccounts.json",
			method = RequestMethod.GET)
	public ModelAndView searchUserAccounts(
			@RequestParam(value = "term", required = true)
				final String query) {
		List<UserAccount> userAccounts
			= this.grievanceService.searchUserAccounts(query);
		ModelAndView mav = new ModelAndView(USER_ACCOUNTS_VIEW_NAME);
		mav.addObject(USER_ACCOUNTS_MODEL_KEY, userAccounts);
		return mav;
	}
	
	/**
	 * Returns whether disposition status is closed.
	 * 
	 * @param status status
	 * @return whether disposition status is closed
	 */
	@RequestMapping(
			value = "/isDispositionStatusClosed.html",
			method = RequestMethod.GET)
	public ModelAndView isDispositionStatusClosed(
			@RequestParam(value = "status", required = true)
				final GrievanceDispositionStatus status) {
		ModelAndView mav = new ModelAndView(BOOLEAN_VALUE_VIEW_NAME);
		if (status != null) {
			mav.addObject(BOOLEAN_VALUE_MODEL_KEY, status.getClosed());
		} else {
			mav.addObject(BOOLEAN_VALUE_MODEL_KEY, false);
		}
		return mav;
	}
	
	/**
	 * Returns disposition reason options by status.
	 * 
	 * @param status status
	 * @return disposition reason options by status
	 */
	@RequestMapping(
			value = "/findDispositionReasonsByStatus.html",
			method = RequestMethod.GET)
	public ModelAndView findDispositionReasonsByStatus(
			@RequestParam(value = "status", required = false)
				final GrievanceDispositionStatus status,
			@RequestParam(value = "defaultDispositionReason", required = false)
				final GrievanceDispositionReason defaultDispositionReason) {
		List<GrievanceDispositionReason> dispositionReasons
			= this.grievanceService.findDispositionReasonsByStatus(status);
		ModelAndView mav = new ModelAndView(DISPOSITION_REASONS_VIEW_NAME);
		mav.addObject(DISPOSITION_REASONS_MODEL_KEY, dispositionReasons);
		mav.addObject(DEFAULT_DISPOSITION_REASON_MODEL_KEY,
				defaultDispositionReason);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for grievance.
	 * 
	 * @param offender offender
	 * @param location location
	 * @return action menu for grievance
	 */
	@RequestMapping(
			value = "/grievanceActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "location", required = false)
				final GrievanceLocation location) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(LOCATION_MODEL_KEY, location);
		return mav;
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
				GRIEVANCE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Helper methods. */
	
	// Prepares model and view to edit grievances
	private ModelAndView prepareEditMav(
			final Offender offender,
			final GrievanceSubject subject,
			final GrievanceForm grievanceForm) {
		List<GrievanceUnit> units = this.grievanceService.findUnits();
		List<GrievanceComplaintCategory> complaintCategories;
		if (subject != null) {
			complaintCategories
				= this.grievanceService.findComplaintCategoriesBySubject(
						subject);
		} else {
			complaintCategories = Collections.emptyList();
		}
		List<GrievanceLocation> locations
			= this.grievanceService.findLocations();
		List<GrievanceDispositionStatus> coordinatorLevelDispositionStatuses;
		if (grievanceForm.getWardenFhaLevelDispositionItem() != null
				&& grievanceForm.getWardenFhaLevelDispositionItem()
					.getDisposition() != null) {
			coordinatorLevelDispositionStatuses
				= this.grievanceService.findOpenedDispositionStatusesByLevels(
					GrievanceDispositionLevel.COORDINATOR, null);
		} else {
			coordinatorLevelDispositionStatuses
				= this.grievanceService.findDispositionStatusesByLevels(
					GrievanceDispositionLevel.COORDINATOR, null);
		}
		List<GrievanceDispositionStatus> wardenFhaLevelDispositionStatuses;
		GrievanceDispositionLevel[] levels;
		if (subject != null) {
			if (subject.getMedical() != null && subject.getMedical()) {
				levels = new GrievanceDispositionLevel[] {
					GrievanceDispositionLevel.FHA,
					null
				};
			} else {
				levels = new GrievanceDispositionLevel[] {
					GrievanceDispositionLevel.WARDEN,
					null
				};
			}
		} else {
			levels = new GrievanceDispositionLevel[] {
				GrievanceDispositionLevel.WARDEN,
				GrievanceDispositionLevel.FHA,
				null
			};
		}
		if (grievanceForm.getDirectorLevelDispositionItem() != null
				&& grievanceForm.getDirectorLevelDispositionItem()
					.getDisposition() != null) {
			wardenFhaLevelDispositionStatuses
				= this.grievanceService.findOpenedDispositionStatusesByLevels(
						levels);
		} else {
			wardenFhaLevelDispositionStatuses
				= this.grievanceService.findDispositionStatusesByLevels(
						levels);
		}
		List<GrievanceDispositionStatus> directorLevelDispositionStatuses
			= this.grievanceService.findDispositionStatusesByLevels(
					GrievanceDispositionLevel.DIRECTOR, null);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(FORM_MODEL_KEY, grievanceForm);
		mav.addObject(SUBJECT_MODEL_KEY, subject);
		mav.addObject(UNITS_MODEL_KEY, units);
		mav.addObject(COMPLAINT_CATEGORIES_MODEL_KEY, complaintCategories);
		mav.addObject(LOCATIONS_MODEL_KEY, locations);
		mav.addObject(COORDINATOR_LEVEL_DISPOSITION_STATUSES_MODEL_KEY,
				coordinatorLevelDispositionStatuses);
		mav.addObject(WARDEN_FHA_LEVEL_DISPOSITION_STATUSES_MODEL_KEY,
				wardenFhaLevelDispositionStatuses);
		mav.addObject(DIRECTOR_LEVEL_DISPOSITION_STATUSES_MODEL_KEY,
				directorLevelDispositionStatuses);
		if (grievanceForm.getCoordinatorLevelDispositionItem() != null) {
			List<GrievanceDispositionReason> coordinatorLevelDispositionReasons
				= this.grievanceService.findDispositionReasonsByStatus(
						grievanceForm.getCoordinatorLevelDispositionItem()
							.getStatus());
			mav.addObject(COORDINATOR_LEVEL_DISPOSITION_REASONS_MODEL_KEY,
					coordinatorLevelDispositionReasons);
		}
		if (grievanceForm.getWardenFhaLevelDispositionItem() != null) {
			List<GrievanceDispositionReason> wardenFhaLevelDispositionReasons
				= this.grievanceService.findDispositionReasonsByStatus(
						grievanceForm.getWardenFhaLevelDispositionItem()
							.getStatus());
			mav.addObject(WARDEN_FHA_LEVEL_DISPOSITION_REASONS_MODEL_KEY,
					wardenFhaLevelDispositionReasons);
		}
		if (grievanceForm.getDirectorLevelDispositionItem() != null) {
			List<GrievanceDispositionReason> directorLevelDispositionReasons
				= this.grievanceService.findDispositionReasonsByStatus(
						grievanceForm.getDirectorLevelDispositionItem()
							.getStatus());
			mav.addObject(DIRECTOR_LEVEL_DISPOSITION_REASONS_MODEL_KEY,
					directorLevelDispositionReasons);
		}
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay form
	private ModelAndView prepareRedisplayMav(
			final Offender offender,
			final GrievanceSubject subject,
			final GrievanceForm grievanceForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(
				offender, subject, grievanceForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	// Returns populates grievance disposition item
	private GrievanceDispositionItem createDispositionItem(
			final GrievanceDisposition disposition,
			final Boolean currentLevel, final Boolean edit) {
		GrievanceDispositionItem dispositionItem
			= new GrievanceDispositionItem();
		dispositionItem.setDisposition(disposition);
		dispositionItem.setReceivedDate(disposition.getReceivedDate());
		dispositionItem.setResponseDueDate(disposition.getResponseDueDate());
		dispositionItem.setResponseBy(disposition.getResponseBy());
		dispositionItem.setResponseToOffenderDate(disposition
				.getResponseToOffenderDate());
		if (GrievanceDispositionLevel.DIRECTOR.equals(
				disposition.getLevel())) {
			dispositionItem.setAllowAppealDate(false);
		} else {
			dispositionItem.setAppealDate(disposition.getAppealDate());
			dispositionItem.setAllowAppealDate(true);
		}
		dispositionItem.setNotes(disposition.getNotes());
		dispositionItem.setReason(disposition.getReason());
		dispositionItem.setStatus(disposition.getStatus());
		dispositionItem.setCurrentLevel(currentLevel);
		dispositionItem.setEdit(edit);
		return dispositionItem;
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Grievance.class,
				this.grievancePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceSubject.class,
				this.grievanceSubjectPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceComplaintCategory.class,
				this.grievanceComplaintCategoryPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceUnit.class,
				this.grievanceUnitPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceLocation.class,
				this.grievanceLocationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceDispositionStatus.class,
				this.grievanceDispositionStatusPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceDisposition.class,
				this.grievanceDispositionPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceDispositionReason.class,
				this.grievanceDispositionReasonPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(UserAccount.class,
				this.userAccountPropertyEditorFactory
					.createPropertyEditor());
	}
}