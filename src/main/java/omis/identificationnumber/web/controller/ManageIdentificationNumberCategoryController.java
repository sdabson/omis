package omis.identificationnumber.web.controller;

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
import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.Multitude;
import omis.identificationnumber.service.IdentificationNumberCategoryService;
import omis.identificationnumber.web.form.IdentificationNumberCategoryForm;
import omis.identificationnumber.web.validator.IdentificationNumberCategoryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * ManageIdentificationNumberCategoryController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Nov 1, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/identificationNumber/category")
public class ManageIdentificationNumberCategoryController {
	
	/* View names */
	
	private static final String EDIT_VIEW_NAME =
			"/identificationNumber/category/edit";
	
	private static final String CATEGORY_ACTION_MENU_VIEW_NAME =
			"/identificationNumber/category/includes/" +
			"identificationNumberCategoryActionMenu";
	
	private static final String LIST_REDIRECT =
			"redirect:/identificationNumber/category/list.html";
	
	/* Model Keys */
	
	private static final String IDENTIFICATION_NUMBER_CATEGORY_MODEL_KEY =
			"identificationNumberCategory";

	private static final String FORM_MODEL_KEY =
			"identificationNumberCategoryForm";
	
	private static final String MULTITUDE_VALUES_MODEL_KEY = "multitudeValues";
	
	/* Message Keys */
		
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"identificationNumberCategory.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.identificationnumber.msgs.form";
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("identificationNumberCategoryPropertyEditorFactory")
	private PropertyEditorFactory
		identificationNumberCategoryPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Service */
	
	@Autowired
	@Qualifier("identificationNumberCategoryService")
	private IdentificationNumberCategoryService
						identificationNumberCategoryService;
	
	/* Validator */
	
	@Autowired
	@Qualifier("identificationNumberCategoryFormValidator")
	private IdentificationNumberCategoryFormValidator
						identificationNumberCategoryFormValidator;
	
	/**
	 * Default Constructor for ManageIdentificationNumberCategoryController
	 */
	public ManageIdentificationNumberCategoryController() {
	}
	
	/**
	 * Returns a model and view for creating an Identification Number Category
	 * @return ModelAndView - model and view for creating an Identification
	 * Number Category
	 */
	@RequestMapping(value = "create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('IDENTIFICATION_NUMBER_CATEGORY_CREATE')") 
	public ModelAndView create() {
		return this.prepareEditMav(new IdentificationNumberCategoryForm(),
				new ModelMap());
	}
	
	/**
	 * Saves a new Identification Number Category and returns to the
	 * Identification Number Category List Screen
	 * @param form - IdentificationNumberCategoryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - redirects to the List screen on successful
	 * Category creation or returns to the create screen on form validation error
	 * @throws DuplicateEntityFoundException - When an
	 * IdentificationNumberCategory already exists with the given name
	 */
	@RequestMapping(value = "create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('IDENTIFICATION_NUMBER_CATEGORY_CREATE')") 
	public ModelAndView save(final IdentificationNumberCategoryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.identificationNumberCategoryFormValidator.validate(form,
				bindingResult);
		
		if(bindingResult.hasErrors()) {
			return this.prepareEditMav(form, new ModelMap());
		}
		else {
			this.identificationNumberCategoryService.create(
					form.getName(), form.getMultitude(), form.getValid());
			
			return new ModelAndView(LIST_REDIRECT);
		}
	}
	
	/**
	 * Returns a model and view for editing an Identification Number Category
	 * @param category - IndentificationNumberCategory being edited
	 * @return ModelAndView - model and view for editing an Identification
	 * Number Category
	 */
	@RequestMapping(value = "edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('IDENTIFICATION_NUMBER_CATEGORY_EDIT')") 
	public ModelAndView edit(
			@RequestParam(value = "identificationNumberCategory",
			required = true) final IdentificationNumberCategory category) {
		ModelMap map = new ModelMap();
		map.addAttribute(IDENTIFICATION_NUMBER_CATEGORY_MODEL_KEY, category);
		return this.prepareEditMav(this.prepareForm(category), map);
	}
	
	/**
	 * Updates an Identification Number Category and returns to the
	 * Identification Number Category List Screen
	 * @param category - IdentificationNumberCategory being updated
	 * @param form - IdentificationNumberCategoryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - redirects to the List screen on successful
	 * Category update or returns to the create screen on form validation error
	 * @throws DuplicateEntityFoundException - When an
	 * IdentificationNumberCategory already exists with the given name
	 */
	@RequestMapping(value = "edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('IDENTIFICATION_NUMBER_CATEGORY_EDIT')") 
	public ModelAndView update(
			@RequestParam(value = "identificationNumberCategory",
			required = true) final IdentificationNumberCategory category,
			final IdentificationNumberCategoryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.identificationNumberCategoryFormValidator.validate(form,
				bindingResult);
		
		if(bindingResult.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(IDENTIFICATION_NUMBER_CATEGORY_MODEL_KEY, category);
			return this.prepareEditMav(form, map);
		}
		else {
			this.identificationNumberCategoryService.update(
					category, form.getName(), form.getMultitude(),
					form.getValid());
			
			return new ModelAndView(LIST_REDIRECT);
		}
	}
	
	/**
	 * Removes the specified IdentificationNumberCategory
	 * @param category - IdentificationNumberCategory to remove
	 * @return ModelAndView - Redirects back to the IdentificationNumberCategory
	 * list screen
	 */
	@RequestMapping(value = "remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('IDENTIFICATION_NUMBER_CATEGORY_EDIT')") 
	public ModelAndView remove(
			@RequestParam(value = "identificationNumberCategory",
			required = true) final IdentificationNumberCategory category) {
		this.identificationNumberCategoryService.remove(category);
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Returns the model and  view for the Identification Number Category
	 * Action Menu
	 * @return ModelAndView - model and  view for the Identification Number
	 * Category Action Menu
	 */
	@RequestMapping(value = "identificationNumberCategoryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayIdentificationNumberCategoryActionMenu() {
		return new ModelAndView(CATEGORY_ACTION_MENU_VIEW_NAME);
	}
	
	/* Helper Methods */
	
	/**
	 * Prepares a ModelAndView for creating/editing an IdentificationNumberCategory
	 * @param form - IdentificationNumberCategoryForm
	 * @param map - ModelMap
	 * @return ModelAndView - prepared ModelAndView for creating/editing an
	 * IdentificationNumberCategory
	 */
	private ModelAndView prepareEditMav(
			final IdentificationNumberCategoryForm form, final ModelMap map) {
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(MULTITUDE_VALUES_MODEL_KEY, Multitude.values());
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Prepares an IdentificationNumberCategoryForm from specified
	 * IdentificationNumberCategory
	 * @param category - IdentificationNumberCategory
	 * @return Prepared IdentificationNumberCategoryForm
	 */
	private IdentificationNumberCategoryForm prepareForm(
			final IdentificationNumberCategory category) {
		IdentificationNumberCategoryForm form =
				new IdentificationNumberCategoryForm();
		form.setName(category.getName());
		form.setValid(category.getValid());
		form.setMultitude(category.getMultitude());
		return form;
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
	
	/* initBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				IdentificationNumberCategory.class,
				this.identificationNumberCategoryPropertyEditorFactory
				.createPropertyEditor());
	}
}
