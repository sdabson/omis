package omis.questionnaire.web.controller;

import java.util.List;

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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.report.AdministeredQuestionnaireSummary;
import omis.questionnaire.report.AdministeredQuestionnaireReportService;

/**
 * AdministeredQuestionnaireReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 18, 2016)
 *@since OMIS 3.0
 *
 */

@Controller
@RequestMapping("/questionnaire/")
@PreAuthorize("hasRole('USER')")
public class AdministeredQuestionnaireReportController {
	/* View Names */
	
	private static final String ADMINISTERED_QUESTIONNAIRE_LIST_VIEW_NAME = 
			"/questionnaire/administeredQuestionnaireList";
	
	private static final String 
		ADMINISTERED_QUESTIONNAIRES_ROW_ACTION_MENU_VIEW_NAME =
			"/questionnaire/includes/administeredQuestionnairesRowActionMenu";
	
	private static final String ADMINISTERED_QUESTIONNAIRES_ACTION_MENU_VIEW_NAME =
			"questionnaire/includes/administeredQuestionnairesActionMenu";
	
	
	/* Model Keys */
	
	private static final String ADMINISTERED_QUESTIONNAIRE_SUMMARIES_MODEL_KEY =
			"administeredQuestionnaireSummaries";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_MODEL_KEY =
			"administeredQuestionnaire";
	
	private static final String QUESTIONNAIRE_TYPE_MODEL_KEY = 
			"questionnaireType";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Service */
	
	@Autowired
	@Qualifier("administeredQuestionnaireReportService")
	private AdministeredQuestionnaireReportService
		administeredQuestionnaireReportService;
	
	/* Helpers */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property Editors */
	
	@Autowired
	@Qualifier("administeredQuestionnairePropertyEditorFactory")
	private PropertyEditorFactory administeredQuestionnairePropertyEditorFactory;
	
	@Autowired
	@Qualifier("questionnaireTypePropertyEditorFactory")
	private PropertyEditorFactory questionnaireTypePropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	
	/**
	 * Default Constructor
	 */
	public AdministeredQuestionnaireReportController() {
	}
	
	/**
	 * Returns a view of the administered questionnaire list screen by 
	 * specified offender
	 * @param offender - Offender
	 * @return ModelAndView - view of the administered questionnaire list screen
	 */
	@RequestMapping(value = "administeredQuestionnaireList.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView administeredQuestionnaireList(
			@RequestParam(value="offender", required=true)
				final Offender offender,
			@RequestParam(value="questionnaireType", required=true)
				final QuestionnaireType questionnaireType){
		
		ModelMap map = new ModelMap();
		
		List<AdministeredQuestionnaireSummary> questionnaireSummaries =
				this.administeredQuestionnaireReportService
				.findQuestionnairesByAnswererAndQuestionnaireType(
						offender, questionnaireType);
		
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY, questionnaireType);
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_SUMMARIES_MODEL_KEY, 
				questionnaireSummaries);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(ADMINISTERED_QUESTIONNAIRE_LIST_VIEW_NAME, map);
	}
	
	
	/* Action Menu Model And Views */
	
	/**
	 * Displays the action menu for the administered questionnaire rows
	 * @param offender - Offender
	 * @param administeredQuestionnaire - administered questionnaire
	 * @return ModelAndView - view that displays the action menu for the 
	 * administered questionnaire rows
	 */
	@RequestMapping(value = "/administeredQuestionnairesRowActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayAdministeredQuestionnairesRowActionMenu(
			@RequestParam(value="offender", 
			required=true) final Offender offender,
			@RequestParam(value="administeredQuestionnaire", required=true)
			final AdministeredQuestionnaire administeredQuestionnaire){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_MODEL_KEY,
				administeredQuestionnaire);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(
				ADMINISTERED_QUESTIONNAIRES_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for the administered questionnaires screen
	 * @param offender - Offender
	 * @param questionnaireType - Questionnaire Type
	 * @return ModelAndView - view that displays the action menu for the 
	 * administered questionnaires screen
	 */
	@RequestMapping(value = "/administeredQuestionnairesActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayAdministeredQuestionnairesActionMenu(
			@RequestParam(value="offender", 
			required=true) final Offender offender,
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY, questionnaireType);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(
				ADMINISTERED_QUESTIONNAIRES_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(AdministeredQuestionnaire.class, 
				this.administeredQuestionnairePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(QuestionnaireType.class, 
				this.questionnaireTypePropertyEditorFactory
				.createPropertyEditor());
		
	}
}
