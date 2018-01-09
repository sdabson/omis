package omis.offenderflag.web.controller;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.offenderflag.domain.FlagUsage;
import omis.offenderflag.domain.OffenderFlagCategory;
import omis.offenderflag.report.OffenderFlagCategorySummary;
import omis.offenderflag.report.OffenderFlagCategorySummaryReportService;
import omis.offenderflag.service.OffenderFlagService;
import omis.offenderflag.web.form.OffenderFlagCategoryForm;
import omis.offenderflag.web.validator.OffenderFlagCategoryFormValidator;

/**
 * Offender flag category controller.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/offenderFlagCategory/")
@PreAuthorize("hasRole('USER')")
public class OffenderFlagCategoryController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME 
		= "offenderFlag/offenderFlagCategory/edit";
	
	private static final String LIST_VIEW_NAME 
		= "offenderFlag/offenderFlagCategory/list";
	
	private static final String OFFENDER_FLAG_CATEGORIES_ACTION_MENU_VIEW_NAME
	= "offenderFlag/offenderFlagCategory/includes/offenderFlagCategoriesActionMenu";

	private static final String OFFENDER_FLAG_CATEGORY_ACTION_MENU_VIEW_NAME
	= "offenderFlag/offenderFlagCategory/includes/offenderFlagCategoryActionMenu";
	
	private static final String 
	OFFENDER_FLAG_CATEGORIES_ROW_ACTION_MENU_VIEW_NAME
		= "offenderFlag/offenderFlagCategory/includes/offenderFlagCategoriesRowActionMenu";
	
	
	/* Redirect view names. */
	
	private static final String LIST_REDIRECT_VIEW_NAME
		= "redirect:/offenderFlagCategory/list.html";
		
	/* Model keys. */
		
	private static final String FORM_MODEL_KEY = "offenderFlagCategoryForm";
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	private static final String OFFENDER_FLAG_CATEGORY_MODEL_KEY 
		= "offenderFlagCategory";
	
	private static final String FLAG_USAGE_MODEL_KEY = "usages";
	
	/* Message keys. */
	
//	private static final String ENTITY_EXISTS_MESSAGE_KEY
//		= "entity.exists";
	
	/* Bundles. */
	
//	private static final String ERROR_BUNDLE_NAME
//		= "omis.offenderFlagCategory.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenderFlagService")
	private OffenderFlagService offenderFlagService;
	
	@Autowired
	@Qualifier("offendeFlagCategorySummaryReportService")
	private OffenderFlagCategorySummaryReportService 
		offendeFlagCategorySummaryReportService;
	
	
	/* Property editor factories. */
	@Autowired
	@Qualifier("offenderFlagCategoryPropertyEditorFactory")
	private PropertyEditorFactory offenderFlagCategoryPropertyEditorFactory;
	
	/* Validators. */
	@Autowired
	@Qualifier("offenderFlagCategoryFormValidator")
	private OffenderFlagCategoryFormValidator offenderFlagCategoryFormValidator;
	
	/* Constructor. */
	
	/** Instantiates an implementation of OffenderFlagCategoryController */
	public OffenderFlagCategoryController() {
		// Default constructor.
	}

	/* URL invokable methods. */
	
	@RequestMapping(value = "list.html", method = RequestMethod.GET) 
	@PreAuthorize("hasRole('OFFENDER_FLAG_CATEGORY_LIST') "
			+ "or hasRole('ADMIN')")
	public ModelAndView list() {
		List<OffenderFlagCategorySummary> categories 
			= this.offendeFlagCategorySummaryReportService
			.findAllOffenderFlagCategorySummaries();
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(CATEGORIES_MODEL_KEY, categories);
		return mav;		
	}
	
	@RequestMapping(value = "create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_FLAG_CATEGORY_CREATE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView create() {
		OffenderFlagCategoryForm form = new OffenderFlagCategoryForm();
		ModelAndView mav = this.prepareMav(null, form);
		mav.addObject(FORM_MODEL_KEY, form);
		return mav;
	}
	
	@RequestMapping(value = "create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('OFFENDER_FLAG_CATEGORY_CREATE') "
			+ "or hasRole('ADMIN')")
	public ModelAndView save(final OffenderFlagCategoryForm form, 
			final BindingResult result) throws DuplicateEntityFoundException {
		this.offenderFlagCategoryFormValidator.validate(form, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(form, result);
		}		
		this.offenderFlagService.createOffenderFlagCategory(form.getName(), 
				form.getDescription(), form.getRequired(), form.getSortOrder(), 
				form.getUsage());
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME));
	}
	
	@RequestMapping(value = "edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('OFFENDER_FLAG_CATEGORY_VIEW')")
	public ModelAndView edit(@RequestParam(
			value = "offenderFlagCategory", required = true) 
		final OffenderFlagCategory category) {
		
		OffenderFlagCategoryForm form = new OffenderFlagCategoryForm();
		form.setName(category.getName());
		form.setDescription(category.getDescription());
		form.setRequired(category.getRequired());
		form.setSortOrder(category.getSortOrder());
		form.setUsage(category.getUsage());
		ModelAndView mav = this.prepareMav(category, form);
		mav.addObject("category", category);
		mav.addObject(FORM_MODEL_KEY, form);
		return mav;
	}
	
	@RequestMapping(value = "edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('OFFENDER_FLAG_CATEGORY_EDIT')")
	public ModelAndView update(@RequestParam(
			value = "offenderFlagCategory", required = true)
		final OffenderFlagCategory category,
		final OffenderFlagCategoryForm form, final BindingResult result) 
				throws DuplicateEntityFoundException {
		this.offenderFlagCategoryFormValidator.validate(form, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(form, result);
		}
	
		this.offenderFlagService.updateOffenderFlagCategory(
				category, form.getName(), form.getRequired(), 
				form.getSortOrder(), form.getUsage());
		
		return new ModelAndView(LIST_REDIRECT_VIEW_NAME);
	}
	
	@RequestMapping(value = "remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('OFFENDER_FLAG_CATEGORY_REMOVE')")
	public ModelAndView remove(@RequestParam(value = "offenderFlagCategory", 
		required = true) final OffenderFlagCategory category) {
		
		this.offenderFlagService.removeOffenderFlagCategory(category);
		return new ModelAndView(LIST_REDIRECT_VIEW_NAME);
	}
	
	@RequestMapping(value = "/offenderFlagCategoriesActionMenu", 
			method = RequestMethod.GET)
	public ModelAndView offenderFlagCategoriesActionMenu() {
		return new ModelAndView(OFFENDER_FLAG_CATEGORIES_ACTION_MENU_VIEW_NAME);
	}
	
	@RequestMapping(value = "/offenderFlagCategoryActionMenu", 
			method = RequestMethod.GET)
	public ModelAndView offenderFlagCategoryActionMenu() {
		return new ModelAndView(OFFENDER_FLAG_CATEGORY_ACTION_MENU_VIEW_NAME);
	}
	
	@RequestMapping(value = "/offenderFlagCategoriesRowActionMenu",
			method = RequestMethod.GET)
	public ModelAndView offenderFlagCategoriesRowActionMenu(
			@RequestParam(value = "offenderFlagCategory", required = true)
				final OffenderFlagCategory category) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_FLAG_CATEGORY_MODEL_KEY, category);
		return new ModelAndView(
				OFFENDER_FLAG_CATEGORIES_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Helper methods. */
	
	private ModelAndView prepareMav(final OffenderFlagCategory category, 
			final OffenderFlagCategoryForm form) {

		ModelAndView mav = new ModelAndView();
		mav.addObject(FORM_MODEL_KEY, form);
		mav.addObject(OFFENDER_FLAG_CATEGORY_MODEL_KEY, category);
		mav.addObject("required", Boolean.FALSE);
		mav.addObject(FLAG_USAGE_MODEL_KEY, FlagUsage.values());
		return new ModelAndView(EDIT_VIEW_NAME);
	}

	private ModelAndView prepareRedisplayMav( 
			final OffenderFlagCategoryForm form, final BindingResult result) {
		ModelAndView mav = this.prepareMav(form.getCategory(), form);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY, result);
		return mav;
	}
	
	/* InitBinder */
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(OffenderFlagCategory.class, 
				this.offenderFlagCategoryPropertyEditorFactory
				.createPropertyEditor());
	}
}