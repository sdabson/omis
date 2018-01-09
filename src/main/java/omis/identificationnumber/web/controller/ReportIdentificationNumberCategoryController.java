package omis.identificationnumber.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.report.IdentificationNumberCategorySummaryReportService;

/**
 * ReportIdentificationNumberCategoryController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 28, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/identificationNumber/category")
public class ReportIdentificationNumberCategoryController {
	
	/* View Names */
	
	private static final String LIST_VIEW_NAME =
			"/identificationNumber/category/list";
	
	private static final String
		IDENTIFICATION_NUMBER_CATEGORIES_ACTION_MENU_VIEW_NAME =
			"/identificationNumber/category/includes/" +
			"identificationNumberCategoriesActionMenu";
	
	private static final String
		IDENTIFICATION_NUMBER_CATEGORIES_ROW_ACTION_MENU_VIEW_NAME =
		"/identificationNumber/category/includes/" +
		"identificationNumberCategoriesRowActionMenu";
	
	/* Model Keys */

	private static final String SUMMARIES_MODEL_KEY =
			"identificationNumberCategorySummaries";

	private static final String IDENTIFICATION_NUMBER_CATEGORY_MODEL_KEY =
			"identificationNumberCategory";
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("identificationNumberCategoryPropertyEditorFactory")
	private PropertyEditorFactory
		identificationNumberCategoryPropertyEditorFactory;
	
	/* Service */
	
	@Autowired
	@Qualifier("identificationNumberCategorySummaryReportService")
	private IdentificationNumberCategorySummaryReportService
		identificationNumberCategorySummaryReportService;
	
	/**
	 * Default constructor for ReportIdentificationNumberCategoryController
	 */
	public ReportIdentificationNumberCategoryController() {
	}
	
	/**
	 * Returns the model and view for the Identification Number Categories 
	 * list screen
	 * @return ModelAndView - model and view for the Identification Number
	 * Categories list screen
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('IDENTIFICATION_NUMBER_CATEGORY_LIST')") 
	public ModelAndView list() {
		ModelMap map = new ModelMap();
		map.addAttribute(SUMMARIES_MODEL_KEY,
				this.identificationNumberCategorySummaryReportService
				.findAllIdentificationNumberCategorySummaries());
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Returns the model and view for Identification Number Categories Action
	 * Menu
	 * @return ModelAndView - model and view for Identification Number
	 * Categories Action Menu
	 */
	@RequestMapping(value = "identificationNumberCategoriesActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayIdentificationNumberCategoriesActionMenu() {
		return new ModelAndView(
				IDENTIFICATION_NUMBER_CATEGORIES_ACTION_MENU_VIEW_NAME);
	}
	
	/**
	 * Returns the model and view for the Identification Number Categories Row
	 * Action Menu
	 * @param identificationNumberCategory - IdentificationNumberCategory
	 * @return ModelAndView - model and view for the Identification Number
	 * Categories Row Action Menu
	 */
	@RequestMapping(value = "identificationNumberCategoriesRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayIdentificationNumberCategoriesRowActionMenu(
			@RequestParam(
					value = "identificationNumberCategory", required = true)
			final IdentificationNumberCategory identificationNumberCategory) {
		ModelMap map = new ModelMap();
		map.addAttribute(IDENTIFICATION_NUMBER_CATEGORY_MODEL_KEY,
				identificationNumberCategory);
		
		return new ModelAndView(
				IDENTIFICATION_NUMBER_CATEGORIES_ROW_ACTION_MENU_VIEW_NAME, map);
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
