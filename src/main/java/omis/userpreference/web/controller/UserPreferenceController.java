package omis.userpreference.web.controller;

import javax.servlet.http.HttpServletRequest;

import omis.beans.factory.PropertyEditorFactory;
import omis.user.domain.UserAccount;
import omis.userpreference.domain.ColorValue;
import omis.userpreference.domain.UserPreference;
import omis.userpreference.service.UserPreferenceService;
import omis.userpreference.web.form.UserPreferenceForm;
import omis.userpreference.web.validator.UserPreferenceFormValidator;

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

/**
 * User preference controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/userPreference")
@PreAuthorize("hasRole('USER')")
public class UserPreferenceController {

	/* Redirects. */
	
	private static final String EDIT_REDIRECT_URL
		= "redirect:/userPreference/edit.html?userPreference=%d";
	
	private static final String USER_PREFERENCE_CSS_VIEW_NAME 
		= "userPreference/style/userPreference";
	
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "userPreference/edit";
	
	/* Session attribute keys. */
	
	private static final String USER_APPEARANCE_SESSION_ATTRIBUTE_KEY
		= "userAppearance";
	
	/* Model keys. */
	
	private static final String USER_ACCOUNT_MODEL_KEY = "userAccount";
	
	private static final String EDIT_FORM_MODEL_KEY = "userPreferenceForm";
	
	private static final String FOREGROUND_HUE_MODEL_KEY = "foregroundHue";
	
	private static final String FOREGROUND_SAT_MODEL_KEY
		= "foregroundSaturation";
	
	private static final String BACKGROUND_HUE_MODEL_KEY = "backgroundHue";
	
	private static final String BACKGROUND_SAT_MODEL_KEY
		= "backgroundSaturation";
	
	private static final String USER_PREFERENCE_MODEL_KEY
		= "userPreference";
	
	private static final String USER_PREFERENCE_FORM_MODEL_KEY
		= "userPreferenceForm";
	
	/* String formats. */
	
	private static final String SATURATION_FORMAT = "%d%%";
	
	/* Services. */
	
	@Autowired
	@Qualifier("userPreferenceService")
	private UserPreferenceService userPreferenceService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("userPreferenceFormValidator")
	private UserPreferenceFormValidator userPreferenceFormValidator;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("userPreferencePropertyEditorFactory")
	private PropertyEditorFactory userPreferencePropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of user preference controller.
	 */
	public UserPreferenceController() {
		//Default controller.
	}
	
	/* URL mapped methods. */
	
	/**
	 * Returns model and view to create user preferences.
	 * 
	 * @return edit view for user preferences
	 */
	@RequestMapping(value = "create.html",
			method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(value = "userAccount",
			required = true) final UserAccount userAccount) {
		ModelMap map = new ModelMap();
		map.addAttribute(USER_ACCOUNT_MODEL_KEY, userAccount);
		UserPreferenceForm form = new UserPreferenceForm();
		map.addAttribute(USER_PREFERENCE_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Saves user preferences for the specified user.
	 * 
	 * @return redirect to
	 */
	@RequestMapping(value = "create.html",
			method = RequestMethod.POST)
	public ModelAndView save(@RequestParam(value = "userAccount",
			required = true) final UserAccount userAccount,
			final UserPreferenceForm form, final BindingResult result,
			final HttpServletRequest request) {
		this.userPreferenceFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ EDIT_FORM_MODEL_KEY, result);
			map.addAttribute(USER_ACCOUNT_MODEL_KEY, userAccount);
			return new ModelAndView(EDIT_VIEW_NAME, map);
		}
		UserPreference preference = this.userPreferenceService.create(
				form.getForegroundHue(), form.getForegroundSaturation(),
				form.getBackgroundHue(), form.getBackgroundSaturation(),
				form.getAccentHue(), form.getAccentSaturation(),
				form.getWhiteBackground(), userAccount);
		request.getSession().removeAttribute(
				USER_APPEARANCE_SESSION_ATTRIBUTE_KEY);
		return new ModelAndView(String.format(
				EDIT_REDIRECT_URL, preference.getId()));
	}
	
	/**
	 * Returns model and view to edit user preference.
	 * 
	 * @param userPreference user preference
	 * @return model and view to edit user preference
	 */
	@RequestMapping(value = "edit.html",
			method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "userPreference",
			required = true)final UserPreference userPreference) {
		ModelMap map = new ModelMap();
		UserPreferenceForm form = new UserPreferenceForm();
		this.populateEditForm(form, userPreference);
		map.addAttribute(USER_PREFERENCE_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Updates the specified user preference.
	 * 
	 * @param userPreference user preference
	 * @param form user preference form
	 * @param result binding result
	 * @return redirect url
	 */
	@RequestMapping(value = "edit.html",
			method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(value = "userPreference",
			required = true)final UserPreference userPreference,
			final UserPreferenceForm form, final BindingResult result,
			final HttpServletRequest request) {
		this.userPreferenceFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ EDIT_FORM_MODEL_KEY, result);
			map.addAttribute(USER_PREFERENCE_MODEL_KEY, userPreference);
			return new ModelAndView(EDIT_VIEW_NAME, map);
		}
		this.userPreferenceService.update(userPreference,
				form.getForegroundHue(), form.getForegroundSaturation(),
				form.getBackgroundHue(), form.getBackgroundSaturation(),
				form.getAccentHue(), form.getAccentSaturation(),
				form.getWhiteBackground());
		request.getSession().removeAttribute(
				USER_APPEARANCE_SESSION_ATTRIBUTE_KEY);
		return new ModelAndView(String.format(
				EDIT_REDIRECT_URL, userPreference.getId()));
	}
	
	/**
	 * Returns the user preference style sheet.
	 * @return model and view for colors style sheet
	 */
	@RequestMapping(value = "/style/userPreference.css")
	public ModelAndView showUserPreferenceStylesheet() {
		ModelMap map = new ModelMap();
		UserPreference userPreference = this.userPreferenceService
				.findByUserAccount();
		final ColorValue backgroundColorValue;
		final ColorValue foregroundColorValue;
		if(userPreference != null) {
			backgroundColorValue = userPreference.getBackgroundColorValue();
			foregroundColorValue = userPreference.getForegroundColorValue();
		} else {
			Short defaultHue = 0;
			Short defaultSaturation = 0;
			backgroundColorValue = new ColorValue(defaultHue,
					defaultSaturation);
			foregroundColorValue = new ColorValue(defaultHue,
					defaultSaturation);
		}
		map.addAttribute(FOREGROUND_HUE_MODEL_KEY,
				foregroundColorValue.getHue());
		map.addAttribute(FOREGROUND_SAT_MODEL_KEY,
				String.format(SATURATION_FORMAT, 
						foregroundColorValue.getSaturation()));
		map.addAttribute(BACKGROUND_HUE_MODEL_KEY, 
				backgroundColorValue.getHue());
		map.addAttribute(BACKGROUND_SAT_MODEL_KEY,
				String.format(SATURATION_FORMAT,
						backgroundColorValue.getSaturation()));
		return new ModelAndView(USER_PREFERENCE_CSS_VIEW_NAME, map);
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the user preference form with values from the specified
	 * user preference.
	 * 
	 * @param form user preference form
	 * @param userPreference user preference
	 * @return populated user preference form
	 */
	private UserPreferenceForm populateEditForm(final UserPreferenceForm form,
			final UserPreference userPreference) {
		form.setForegroundHue(
				userPreference.getForegroundColorValue().getHue());
		form.setForegroundSaturation(
				userPreference.getForegroundColorValue().getSaturation());
		form.setBackgroundHue(
				userPreference.getBackgroundColorValue().getHue());
		form.setBackgroundSaturation(
				userPreference.getBackgroundColorValue().getSaturation());
		form.setAccentHue(userPreference.getAccentColorValue().getHue());
		form.setAccentSaturation(userPreference.getAccentColorValue()
				.getSaturation());
		form.setWhiteBackground(userPreference.getWhiteBackground());
		return form;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				UserPreference.class,
				this.userPreferencePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				UserAccount.class,
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
	}
}