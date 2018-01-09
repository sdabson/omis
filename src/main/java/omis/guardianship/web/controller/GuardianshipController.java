package omis.guardianship.web.controller;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.guardianship.domain.Guardianship;
import omis.guardianship.service.GuardianshipService;
import omis.guardianship.validator.GuardianshipFormValidator;
import omis.guardianship.web.form.GuardianshipForm;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.user.service.UserAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.servlet.ModelAndView;

/**
 * Guardianship Controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 4, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/guardianship")
@PreAuthorize("hasRole('ADMIN') or hasRole('APP_DEV') or (hasRole('USER') "
		+ "and hasRole('GUARDIANSHIP_MODULE'))")
public class GuardianshipController {
	
	/* Redirect URLs. */
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "guardianship/edit";
	
	/* Model Keys. */
	
	private static final String GUARDIANSHIP_FORM_MODEL_KEY = 
			"guardianshipForm";
	private static final String GUARDIANSHIP_MODEL_KEY = "guardianship";
	
	@Autowired
	@Qualifier("guardianshipService")
	private GuardianshipService guardianshipService;
	
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("guardianshipPropertyEditorFactory")
	private PropertyEditorFactory guardianshipPropertyEditorFactory; 
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("guardianshipInstanceFactory")
	private InstanceFactory<Guardianship> guardianshipInstanceFactory;
	
	@Autowired
	@Qualifier("guardianshipFormValidator")
	private GuardianshipFormValidator guardianshipFormValidator;
	
	/**
	 * Instantiates a default instance of guardianship controller.
	 */
	public GuardianshipController() {
		//Default constructor.
	}
	
	/**
	 * Display the guardianship form for the creation of a new guardianship.
	 * 
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "guardianshipCreateScreenName",
			descriptionKey = "guardianshipCreateScreenDescription",
			messageBundle = "omis.guardianship.msgs.guardianship",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('GUARDIANSHIP_CREATE') or hasRole('ADMIN')")
	public ModelAndView create() {
		ModelMap map = new ModelMap();
		GuardianshipForm form = new GuardianshipForm();
		map.addAttribute(GUARDIANSHIP_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Submits the creation of a new guardianship.
	 * 
	 * @param form guardianship form
	 * @param result binding result
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "guardianshipSaveName",
			descriptionKey = "guardianshipSaveDescription",
			messageBundle = "omis.guardianship.msgs.guardianship",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('GUARDIANSHIP_SAVE') or hasRole('ADMIN')")
	public ModelAndView save(final GuardianshipForm form, 
			final BindingResult result) {
		this.guardianshipFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(GUARDIANSHIP_FORM_MODEL_KEY, form);
			return new ModelAndView(EDIT_VIEW_NAME, map);
		}
		Guardianship guardianship = this.guardianshipInstanceFactory
				.createInstance();
		prepareGuardianship(form, guardianship);
		guardianship.setCreationSignature(new CreationSignature(
				this.userAccountService.findByUsername(SecurityContextHolder
						.getContext().getAuthentication().getName())
				, new Date()));
		this.guardianshipService.save(guardianship);
		return new ModelAndView();
	}
	
	/**
	 * Displays the guardianship form with values from the specified
	 * guardianship for the purpose of editing the specified guardianship.
	 * 
	 * @param guardianship guardianship
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "guardianshipEditScreenName",
			descriptionKey = "guardianshipEditScreenDescription",
			messageBundle = "omis.guardianship.msgs.guardianship",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('GUARDIANSHIP_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "guardianship", 
			required = true)final Guardianship guardianship) {
		ModelMap map = new ModelMap();
		GuardianshipForm form = new GuardianshipForm();
		prepareGuardianshipForm(guardianship, form);
		return prepareEditMav(map, guardianship, form);
	}
	
	/**
	 * Submits the update of a specified guardianship with the values from the
	 * specified guardianship form.
	 * 
	 * @param form guardianship form
	 * @param guardianship guardianship
	 * @param result binding result
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "guardianshipUpdateName",
			descriptionKey = "guardianshipUpdateDescription",
			messageBundle = "omis.guardianship.msgs.guardianship",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('GUARDIANSHIP_UPDATE') or hasRole('ADMIN')")
	public ModelAndView update(final GuardianshipForm form, @RequestParam(
			value = "guardianship", required = true)
			final Guardianship guardianship, final BindingResult result) {
		this.guardianshipFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			return prepareEditMav(map, guardianship, form);
		}
		prepareGuardianship(form, guardianship);
		this.guardianshipService.save(guardianship);
		return new ModelAndView();
	}
	
	/*
	 * Prepares a model and view object with attributes necessary to edit an
	 * existing guardianship.
	 */
	private ModelAndView prepareEditMav(final ModelMap map, 
			final Guardianship guardianship, final GuardianshipForm form) {
		map.addAttribute(GUARDIANSHIP_FORM_MODEL_KEY, form);
		map.addAttribute(GUARDIANSHIP_MODEL_KEY, guardianship);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Prepare a guardianship object from the values of the specified form.
	 */
	private Guardianship prepareGuardianship(final GuardianshipForm form, 
			final Guardianship guardianship) {
		//TODO: It is commentted out temperorily. Y.L. 
	//	guardianship.setRelationship(this.guardianshipService.associate(
	//			guardianship, form.getDependent(), 
	//			form.getGuardian(), new Date(), this.userAccountService
	//			.findByUsername(SecurityContextHolder.getContext().
	//			getAuthentication().getName())));
		guardianship.setDateRange(form.getDateRange());
		guardianship.setUpdateSignature(new UpdateSignature(
				this.userAccountService
				.findByUsername(SecurityContextHolder.getContext().
				getAuthentication().getName()), new Date()));
		return guardianship;
	}
	
	/*
	 * Prepare the specified guardianship form with values from the specified 
	 * guardianship.
	 */
	private GuardianshipForm prepareGuardianshipForm(
			final Guardianship guardianship, final GuardianshipForm form) {
		form.setDateRange(guardianship.getDateRange());
		form.setDependent(guardianship.getRelationship().getFirstPerson());
		form.setGuardian(guardianship.getRelationship().getSecondPerson());
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
				Guardianship.class,
				this.guardianshipPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}