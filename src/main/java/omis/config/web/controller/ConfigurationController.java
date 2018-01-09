package omis.config.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.config.datatype.ConfigurationSettingType;
import omis.config.domain.ConfigurationSetting;
import omis.config.service.ConfigurationSettingService;
import omis.config.web.form.ConfigurationSettingForm;
import omis.instance.factory.InstanceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for system wide configuration.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 9, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/config")
@PreAuthorize("hasRole('USER')")
public class ConfigurationController {

	/* Views. */
	
	private static final String LIST_VIEW_NAME = "config/list";
	
	private static final String EDIT_FORM_NAME = "config/edit";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT = "redirect:list.html";
	
	/* Model keys. */
	
	private static final String SETTINGS_MODEL_KEY = "settings";
	
	private static final String TYPES_MODEL_KEY = "types";
	
	/* Command objects. */
	
	private static final String SETTING_FORM = "settingForm";
	
	/* Services. */
	
	@Autowired
	private ConfigurationSettingService configurationSettingService;
	
	/* Producers. */
	
	@Autowired
	private InstanceFactory<ConfigurationSetting>
			configurationSettingInstanceFactory;
	
	/* Property editor factories. */
	
	@Autowired
	private PropertyEditorFactory configurationSettingPropertyEditorFactory;
	
	/** Instantiates a default configuration controller. */
	public ConfigurationController() {
		// Default instantiation
	}
	
	/**
	 * Displays a list of configuration settings.
	 * 
	 * @return model and view for list of configuration settings
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<ConfigurationSetting> settings = this.configurationSettingService
					.findAll();
		mav.addObject(SETTINGS_MODEL_KEY, settings); 
		return mav;
	}
	
	/**
	 * Displays a form for the creation of a new configuration setting.
	 * 
	 * @return model and view for form to create new configuration setting
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView(EDIT_FORM_NAME);
		mav.addObject(SETTING_FORM, new ConfigurationSettingForm());
		mav.addObject(TYPES_MODEL_KEY, ConfigurationSettingType.values());
		return mav;
	}
	
	/**
	 * Displays a form for the editing of an existing configuration setting.
	 * 
	 * @param setting setting to update
	 * @return model and view for form to edit existing configuration setting
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "setting", required = true)	
				final ConfigurationSetting setting) {
		ConfigurationSettingForm settingForm = new ConfigurationSettingForm();
		settingForm.setName(setting.getName());
		settingForm.setType(setting.getType());
		settingForm.setValue(
				setting.getType().convert(setting.getValue()).toString());
		ModelAndView mav = new ModelAndView(EDIT_FORM_NAME);
		mav.addObject(SETTING_FORM, settingForm);
		mav.addObject(TYPES_MODEL_KEY, ConfigurationSettingType.values());
		return mav;
	}
	
	/**
	 * Saves a new configuration setting.
	 * 
	 * @param settingForm form from which to take new setting properties
	 * @return redirect to settings list
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(final ConfigurationSettingForm settingForm) {
		ConfigurationSetting setting
				= this.configurationSettingInstanceFactory.createInstance();
		setting.setName(settingForm.getName());
		setting.setType(settingForm.getType());
		setting.setValue(
				settingForm.getType().convert(settingForm.getValue())
					.toString());
		this.configurationSettingService.save(setting);
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Updates and existing configuration setting.
	 * 
	 * @param setting setting to update
	 * @param settingForm form from which to take updated setting properties
	 * @return redirect to settings list
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "setting", required = true)	
				final ConfigurationSetting setting,
				final ConfigurationSettingForm settingForm) {
		setting.setName(settingForm.getName());
		setting.setType(settingForm.getType());
		setting.setValue(
				settingForm.getType().convert(settingForm.getValue())
					.toString());
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Removes a configuration setting.
	 * 
	 * @param setting configuration setting to remove
	 * @return redirect to settings list
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "setting", required = true)	
				final ConfigurationSetting setting) {
		this.configurationSettingService.remove(setting);
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(ConfigurationSetting.class, 
				this.configurationSettingPropertyEditorFactory
					.createPropertyEditor());
	}
}