package omis.religion.web.controller;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.BusinessException;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.exception.OperationNotAuthorizedException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousPreference;
import omis.religion.service.ReligiousPreferenceService;
import omis.religion.web.form.ReligiousAccommodationAuthorizationItem;
import omis.religion.web.form.ReligiousPreferenceForm;
import omis.religion.web.validator.ReligiousPreferenceFormValidator;
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
 * Controller for managing religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/religion/religiousPreference")
@PreAuthorize("hasRole('USER')")
public class ManageReligiousPreferencesController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "religion/religiousPreference/edit";
	
	/* Action menu view names. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "religion/religiousPreference/includes/religiousPreferenceActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/religion/religiousPreference/list.html?offender=%d";
	
	/* Model keys. */

	private static final String RELIGIOUS_PREFERENCE_FORM_MODEL_KEY
		= "religiousPreferenceForm";

	private static final String ACTIVE_EXIST_MODEL_KEY = "activeExist";

	private static final String RELIGIONS_MODEL_KEY = "religions";

	private static final String PREFERENCE_MODEL_KEY = "preference";

	private static final String VERIFICATION_METHODS_MODEL_KEY
		= "verificationMethods";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Field formats. */
	
	private static final String USER_ACCOUNT_LABEL_FORMAT = "%s, %s (%s)";
	
	/* Services. */

	@Autowired
	@Qualifier("religiousPreferenceService")
	private ReligiousPreferenceService religiousPreferenceService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("religiousPreferencePropertyEditorFactory")
	private PropertyEditorFactory religiousPreferencePropertyEditorFactory;
	
	@Autowired
	@Qualifier("religionPropertyEditorFactory")
	private PropertyEditorFactory religionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("religiousAccommodationPropertyEditorFactory")
	private PropertyEditorFactory religiousAccommodationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("verificationMethodPropertyEditorFactory")
	private PropertyEditorFactory verificationMethodPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("religiousPreferenceFormValidator")
	private ReligiousPreferenceFormValidator religiousPreferenceFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Constructor. */
	
	/** Instantiates a default religious preference controller. */
	public ManageReligiousPreferencesController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen to create new religious preference.
	 * 
	 * @param offender offender
	 * @return model and view to create religious preference
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ReligiousPreferenceForm religiousPreferenceForm
			= new ReligiousPreferenceForm();
		List<ReligiousAccommodation> accommodations
			= this.religiousPreferenceService.findAccommodations();
		for (ReligiousAccommodation accommodation : accommodations) {
			ReligiousAccommodationAuthorizationItem item
				= new ReligiousAccommodationAuthorizationItem();
			item.setAccommodation(accommodation);
			religiousPreferenceForm.getAccommodationAuthorizationItems()
				.add(item);
		}
		return this.prepareEditMav(offender, religiousPreferenceForm,
			new Date());
	}
	
	/**
	 * Displays screen to update existing religious preference. 
	 * 
	 * @param preference preference
	 * @return model and view to edit religious preference
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "preference", required = true)
				final ReligiousPreference preference) {
		ReligiousPreferenceForm religiousPreferenceForm
			= new ReligiousPreferenceForm();
		List<ReligiousAccommodation> accommodations
			= this.religiousPreferenceService.findAccommodations();
		for (ReligiousAccommodation accommodation : accommodations) {
			ReligiousAccommodationAuthorizationItem item
				= new ReligiousAccommodationAuthorizationItem();
			item.setAccommodation(accommodation);
			item.setAuthorized(this.religiousPreferenceService
				.isAccommodationAuthorized(preference, accommodation));
			religiousPreferenceForm.getAccommodationAuthorizationItems()
				.add(item);
		}
		religiousPreferenceForm.setReligion(preference.getReligion());
		if (preference.getDateRange() != null) {
			religiousPreferenceForm.setStartDate(
					preference.getDateRange().getStartDate());
			religiousPreferenceForm.setEndDate(
					preference.getDateRange().getEndDate());
		}
		religiousPreferenceForm.setComment(preference.getComment());
		if (preference.getVerificationSignature() != null) {
			religiousPreferenceForm.setVerificationUserAccount(preference
					.getVerificationSignature().getUserAccount());
			if (preference.getVerificationSignature()
					.getUserAccount() != null) {
				UserAccount userAccount = preference.getVerificationSignature()
						.getUserAccount(); 
				religiousPreferenceForm.setVerificationUserAccountLabel(
						String.format(USER_ACCOUNT_LABEL_FORMAT,
								userAccount.getUser().getName().getLastName(),
								userAccount.getUser().getName().getFirstName(),
								userAccount.getUsername()));
			}
			religiousPreferenceForm.setVerificationDate(
					preference.getVerificationSignature().getDate());
			religiousPreferenceForm.setVerificationMethod(
					preference.getVerificationSignature().getMethod());
			religiousPreferenceForm.setVerificationResult(
					preference.getVerificationSignature().getResult());
		}
		ModelAndView mav = this.prepareEditMav(preference.getOffender(),
				religiousPreferenceForm, new Date());
		mav.addObject(PREFERENCE_MODEL_KEY, preference);
		return mav;
	}
	
	/**
	 * Saves a new religious preference.
	 * 
	 * @param offender offender
	 * @param religiousPreferenceForm religious preference form
	 * @param result binding result
	 * @return redirect to list religious preferences
	 * @throws OperationNotAuthorizedException if the religious preference
	 * or accommodation is not authorized 
	 * @throws DateConflictException if date conflict
	 * @throws DuplicateEntityFoundException  if the religious preference
	 * exists
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final ReligiousPreferenceForm religiousPreferenceForm,
			final BindingResult result) throws DuplicateEntityFoundException,
			DateConflictException, OperationNotAuthorizedException {
		Date currentDate = new Date();
		this.religiousPreferenceFormValidator.validate(
				religiousPreferenceForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayEditMav(
					offender, religiousPreferenceForm, currentDate, result);
		}
		VerificationSignature verificationSignature = new
				VerificationSignature(
						religiousPreferenceForm.getVerificationUserAccount(),
						religiousPreferenceForm.getVerificationDate(),
						religiousPreferenceForm.getVerificationResult(),
						religiousPreferenceForm.getVerificationMethod());
		ReligiousPreference preference = this.religiousPreferenceService
				.save(offender, religiousPreferenceForm.getReligion(),
						new DateRange(religiousPreferenceForm.getStartDate(),
								religiousPreferenceForm.getEndDate()),
								religiousPreferenceForm.getComment(),
								verificationSignature);
		for (ReligiousAccommodationAuthorizationItem item
				: religiousPreferenceForm
					.getAccommodationAuthorizationItems()) {
			if (item.getAuthorized()) {
				this.religiousPreferenceService.authorizeAccommodation(
						preference, item.getAccommodation());
			}
		}
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	
	/**
	 * Updates an existing religious preference.
	 * 
	 * @param preference religious preference to update
	 * @param religiousPreferenceForm religious preference form
	 * @param result binding result
	 * @return redirect to list religious preferences
	 * @throws OperationNotAuthorizedException if the religious preference
	 * or accommodation is not authorized 
	 * @throws DateConflictException if date conflict
	 * @throws DuplicateEntityFoundException  if the religious preference
	 * exists
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_EDIT') or  hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "preference", required = true)
				final ReligiousPreference preference,
			final ReligiousPreferenceForm religiousPreferenceForm,
			final BindingResult result)
					throws DuplicateEntityFoundException, DateConflictException, OperationNotAuthorizedException {
		Date currentDate = new Date();
		this.religiousPreferenceFormValidator.validate(
				religiousPreferenceForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayEditMav(
					preference.getOffender(), religiousPreferenceForm,
					currentDate, result);
			mav.addObject(PREFERENCE_MODEL_KEY, preference);
			return mav;
		}
		VerificationSignature verificationSignature =
				new VerificationSignature(
				religiousPreferenceForm.getVerificationUserAccount(),
				religiousPreferenceForm.getVerificationDate(),
				religiousPreferenceForm.getVerificationResult(),
				religiousPreferenceForm.getVerificationMethod());
		this.religiousPreferenceService.update(preference,
				religiousPreferenceForm.getReligion(),
				new DateRange(religiousPreferenceForm.getStartDate(),
						religiousPreferenceForm.getEndDate()),
						religiousPreferenceForm.getComment(),
						verificationSignature);
		for (ReligiousAccommodationAuthorizationItem item
				: religiousPreferenceForm
					.getAccommodationAuthorizationItems()) {
			if (item.getAuthorized()) {
				if (!this.religiousPreferenceService
						.isAccommodationAuthorized(
								preference, item.getAccommodation())) {
					this.religiousPreferenceService.authorizeAccommodation(
							preference, item.getAccommodation());
				}
			} else if (this.religiousPreferenceService
					.isAccommodationAuthorized(
							preference, item.getAccommodation())) {
				this.religiousPreferenceService
					.removeAccommodationAuthorization(
							preference, item.getAccommodation());
			}
		}
		return new ModelAndView(String.format(LIST_REDIRECT,
				preference.getOffender().getId()));
	}
	
	/**
	 * Removes a religious preference.
	 * 
	 * @param preference religious preference to remove
	 * @return redirect to list religious preferences
	 */
	@RequestMapping("/remove.html")
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "preference", required = true)
				final ReligiousPreference preference) {
		Offender offender = preference.getOffender();
		this.religiousPreferenceService.remove(preference);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles model and view for when religious preference exists.
	 * 
	 * @param duplicateEntityFoundException duplicate entity found exception
	 * @return model and view for when religious preference exists
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException duplicateEntityFoundException) {
		return this.prepareExceptionModelAndView("religiousPreference.exists",
				duplicateEntityFoundException);
	}
	
	/**
	 * Handles model and view for when religious preference dates conflict.
	 * 
	 * @param dateConflictException date conflict exception
	 * @return model and view for when religious preference dates conflict
	 */
	@ExceptionHandler(DateConflictException.class)
	public ModelAndView handleDateConflictException(
			final DateConflictException dateConflictException) {
		return this.prepareExceptionModelAndView(
				"religiousPreference.datesConflict", dateConflictException);
	}
	
	/**
	 * Handles model and view for when religious preference or an accommodation
	 * is not authorized.
	 * 
	 * @return model and view for when religious preference or an accommodation
	 * is not authorized
	 */
	@ExceptionHandler(OperationNotAuthorizedException.class)
	public ModelAndView handleOperationNotAuthorizedException(
			final OperationNotAuthorizedException
				operationNotAuthorizedException) {
		return this.prepareExceptionModelAndView(
				"religiousPreference.notAuthorized",
				operationNotAuthorizedException);
	}
	
	/* Action menus. */
	
	/**
	 * Returns action menu.
	 * 
	 * @param offender offender
	 * @return action menu
	 */
	@RequestMapping(value = "/preferenceActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* Helper methods. */
	
	// Prepares edit model and view
	private ModelAndView prepareEditMav(final Offender offender,
			final ReligiousPreferenceForm religiousPreferenceForm,
			final Date date) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(RELIGIOUS_PREFERENCE_FORM_MODEL_KEY,
				religiousPreferenceForm);
		List<Religion> religions = this.religiousPreferenceService
				.findReligions();
		mav.addObject(RELIGIONS_MODEL_KEY, religions);
		List<VerificationMethod> verificationMethods
			= this.religiousPreferenceService.findVerificationMethods();
		mav.addObject(VERIFICATION_METHODS_MODEL_KEY, verificationMethods);
		if (religiousPreferenceService.existByOffenderOnDate(offender, date)) {
			mav.addObject(ACTIVE_EXIST_MODEL_KEY, true);
		}
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares redisplay edit model and view
	private ModelAndView prepareRedisplayEditMav(
			final Offender offender,
			final ReligiousPreferenceForm religiousPreferenceForm,
			final Date date,
			final BindingResult result) {
		UserAccount userAccount = religiousPreferenceForm
				.getVerificationUserAccount(); 
		if (userAccount != null) {
			religiousPreferenceForm.setVerificationUserAccountLabel(
					String.format(USER_ACCOUNT_LABEL_FORMAT,
							userAccount.getUser().getName().getLastName(),
							userAccount.getUser().getName().getFirstName(),
							userAccount.getUsername()));
		}
		ModelAndView mav = this.prepareEditMav(
				offender, religiousPreferenceForm, date);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ RELIGIOUS_PREFERENCE_FORM_MODEL_KEY, result);
		return mav;
	}
	
	// Returns exception model and view
	private ModelAndView prepareExceptionModelAndView(final String messageKey,
			final BusinessException businessException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(messageKey, "omis.religion.msgs.form",
						businessException);
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(UserAccount.class,
				this.userAccountPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(ReligiousPreference.class,
				this.religiousPreferencePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Religion.class,
				this.religionPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ReligiousAccommodation.class,
				this.religiousAccommodationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(VerificationMethod.class,
				this.verificationMethodPropertyEditorFactory
					.createPropertyEditor());
	}
}