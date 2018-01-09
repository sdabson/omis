package omis.disciplinaryCode.web.controller;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.disciplinaryCode.report.DisciplinaryCodeReportService;
import omis.disciplinaryCode.report.SupervisoryOrganizationDisciplinaryCodeSummary;
import omis.disciplinaryCode.report.SupervisoryOrganizationDisciplinarySummary;
import omis.disciplinaryCode.web.form.CodeDateForm;
import omis.disciplinaryCode.web.validator.CodeDateFormValidator;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/disciplinaryCode/")
@PreAuthorize("hasRole('USER')")
public class DisciplinaryCodeReportController {
	
	/* View Names */
	
	private static final String SUPERVISORY_ORGANIZATION_LIST 
		= "/disciplinaryCode/supervisoryOrganizationSelection";
	
	private static final String DISCIPLINARY_CODE_LIST
		= "/disciplinaryCode/list";
	
	private static final String DISCIPLINARY_CODES_ROW_ACTION_MENU_VIEW_NAME 
		= "/disciplinaryCode/includes/disciplinaryCodesRowActionMenu";
	
	private static final String ORGANIZATIONS_ROW_ACTION_MENU_VIEW_NAME 
		= "/disciplinaryCode/includes/organizationsRowActionMenu";

	private static final String DISCIPLINARY_CODES_ACTION_MENU_VIEW_NAME 
		= "/disciplinaryCode/includes/disciplinaryCodesActionMenu";
	
	/* Model Keys */
	
	private static final String SUPERVISORY_ORGANIZATION_SUMMARIES_MODEL_KEY 
		= "supervisoryOrganizationSummaries";
	
	private static final String DISCIPLINARY_CODE_SUMMARIES_MODEL_KEY 
		= "disciplinaryCodeSummaries";
	
	private static final String SUPERVISORY_ORGANIZATION_MODEL_KEY 
		= "supervisoryOrganization";

	private static final String SUPERVISORY_ORGANIZATION_CODE_MODEL_KEY 
		= "supervisoryOrganizationCode";
	
	private static final String FORM_MODEL_KEY = "codeDateForm";
	
	
	/* Services */
	
	@Autowired
	@Qualifier("disciplinaryCodeReportService")
	private DisciplinaryCodeReportService disciplinaryCodeReportService;
	
	/* Property editors */
	
	@Autowired
	@Qualifier("supervisoryOrganizationPropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisoryOrganizationCodePropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationCodePropertyEditorFactory;
	
	@Autowired
	@Qualifier("disciplinaryCodePropertyEditorFactory")
	private PropertyEditorFactory disciplinaryCodePropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validator */
	
	@Autowired
	CodeDateFormValidator formValidator;
	
	/* Constructor */
	
	public DisciplinaryCodeReportController(){
		//Nothing at all!
	}
	
	
	/* Model and Views */
	
	/**
	 * Returns a model and view listing all supervisory organizations
	 * @return model and view
	 */
	@RequestMapping(value = "supervisoryOrganizationSelection.html", 
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('DISCIPLINARY_CODE_LIST')")
	public ModelAndView listOrganizations(){
		List<SupervisoryOrganizationDisciplinarySummary> organizationSummaries
			= this.disciplinaryCodeReportService
			.findAllSupervisoryOrganizationSummaries();
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(SUPERVISORY_ORGANIZATION_SUMMARIES_MODEL_KEY, 
				organizationSummaries);
		
		return new ModelAndView(SUPERVISORY_ORGANIZATION_LIST, map);
	}
	
	/**
	 * Returns a model and view of disciplinary codes associated with specified
	 * supervisory organization
	 * @param supervisoryOrganization - supervisory organization
	 * @return model and view
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('DISCIPLINARY_CODE_LIST')")
	public ModelAndView listCodes(@RequestParam(
		value="supervisoryOrganization", required = true) 
			final SupervisoryOrganization supervisoryOrganization){
		List<SupervisoryOrganizationDisciplinaryCodeSummary> codeSummaries
			= this.disciplinaryCodeReportService
			.findDisciplinaryCodeSummariesBySupervisoryOrganization(
					supervisoryOrganization, new Date());
		CodeDateForm form = new CodeDateForm();
		ModelMap map = new ModelMap();
		
		form.setEffectiveDate(new Date());
		
		map.addAttribute(DISCIPLINARY_CODE_SUMMARIES_MODEL_KEY, 
				codeSummaries);
		map.addAttribute(SUPERVISORY_ORGANIZATION_MODEL_KEY, 
				supervisoryOrganization);
		map.addAttribute(FORM_MODEL_KEY, form);
		
		return new ModelAndView(DISCIPLINARY_CODE_LIST, map);
	}
	

	/**
	 * Returns a model and view of disciplinary codes associated with specified
	 * supervisory organization by specified effective date or date range
	 * provided by the form
	 * @param supervisoryOrganization - supervisory organization
	 * @param form - code date form to provide specified effective date or date range
	 * @param bindingResult - binding result
	 * @return ModelAndView - list of disciplinary codes
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('DISCIPLINARY_CODE_LIST')")
	public ModelAndView listCodesWithParameters(@RequestParam(
		value="supervisoryOrganization", required = true) 
			final SupervisoryOrganization supervisoryOrganization, 
			final CodeDateForm form,  final BindingResult bindingResult){
		
		this.formValidator.validate(form, bindingResult);
		
		List<SupervisoryOrganizationDisciplinaryCodeSummary> codeSummaries;
		if(bindingResult.hasErrors()){
			codeSummaries = this.disciplinaryCodeReportService
					.findDisciplinaryCodeSummariesBySupervisoryOrganization(
							supervisoryOrganization, new Date());
		}
		else{
			if(form.getUsingEffectiveDate() == true){
				codeSummaries = this.disciplinaryCodeReportService
				.findDisciplinaryCodeSummariesBySupervisoryOrganization(
						supervisoryOrganization, form.getEffectiveDate());
			}
			else{
				form.setEffectiveDate(new Date());
				codeSummaries = this.disciplinaryCodeReportService
						.findDisciplinaryCodeSummariesBySupervisoryOrganization(
								supervisoryOrganization, new Date(), 
								form.getFromDate(), form.getToDate());
			}
		}
		ModelMap map = new ModelMap();
		
		map.addAttribute(DISCIPLINARY_CODE_SUMMARIES_MODEL_KEY, 
				codeSummaries);
		map.addAttribute(SUPERVISORY_ORGANIZATION_MODEL_KEY, 
				supervisoryOrganization);
		map.addAttribute(FORM_MODEL_KEY, form);
		
		return new ModelAndView(DISCIPLINARY_CODE_LIST, map);
	}
	
	/* Action Menus */
	
	/**
	 * Displays action menu for disciplinary codes
	 * @param supervisoryOrganization - supervisory organization
	 * @return model and view
	 */
	@RequestMapping(value="/disciplinaryCodesActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayDisciplinaryCodesActionMenu(@RequestParam(
			value = "supervisoryOrganization", required = true) 
			final SupervisoryOrganization supervisoryOrganization){
		
		ModelMap map = new ModelMap();
		map.addAttribute(SUPERVISORY_ORGANIZATION_MODEL_KEY, 
				supervisoryOrganization);
		
		return new ModelAndView(DISCIPLINARY_CODES_ACTION_MENU_VIEW_NAME, 
				map);
	}
	

	/**
	 * Displays action menu for disciplinary codes rows
	 * @param disciplinaryCode - disciplinary code
	 * @return model and view
	 */
	@RequestMapping(value="/disciplinaryCodesRowActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayDisciplinaryCodesRowActionMenu(
			@RequestParam(value = "supervisoryOrganizationCode", 
					required = true) final SupervisoryOrganizationCode 
					supervisoryOrganizationCode){
		
		ModelMap map = new ModelMap();
		map.addAttribute(SUPERVISORY_ORGANIZATION_CODE_MODEL_KEY, 
				supervisoryOrganizationCode);
		
		return new ModelAndView(DISCIPLINARY_CODES_ROW_ACTION_MENU_VIEW_NAME, 
				map);
	}
	

	/**
	 * Displays action menu for supervisory organizations rows
	 * @param supervisoryOrganization - supervisory organization
	 * @return model and view
	 */
	@RequestMapping(value="/organizationsRowActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayOrganizationsRowActionMenu(@RequestParam(
			value = "supervisoryOrganization", required = true)
			final SupervisoryOrganization supervisoryOrganization){
		
		ModelMap map = new ModelMap();
		map.addAttribute(SUPERVISORY_ORGANIZATION_MODEL_KEY, 
				supervisoryOrganization);
		
		return new ModelAndView(ORGANIZATIONS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(SupervisoryOrganization.class, 
				this.supervisoryOrganizationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(SupervisoryOrganizationCode.class, 
				this.supervisoryOrganizationCodePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(DisciplinaryCode.class, 
				this.disciplinaryCodePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}
