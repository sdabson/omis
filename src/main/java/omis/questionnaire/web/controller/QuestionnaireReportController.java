package omis.questionnaire.web.controller;


import java.util.LinkedHashMap;
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
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.report.AnswerValueSummary;
import omis.questionnaire.report.QuestionSummary;
import omis.questionnaire.report.QuestionnaireReportService;
import omis.questionnaire.report.QuestionnaireSectionSummary;
import omis.questionnaire.report.QuestionnaireTypeSummary;
import omis.questionnaire.service.QuestionService;


/**
 * QuestionnaireReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 15, 2016)
 *@since OMIS 3.0
 *
 */

@Controller
@RequestMapping("/questionnaire/")
@PreAuthorize("hasRole('USER')")
public class QuestionnaireReportController {
	
	/* View Names */
	
	private static final String QUESTIONNAIRE_TYPE_LIST_VIEW_NAME = 
			"/questionnaire/questionnaireTypeList";
	
	private static final String QUESTIONNAIRE_SECTION_LIST_VIEW_NAME = 
			"/questionnaire/questionnaireSectionList";
	
	private static final String QUESTION_LIST_VIEW_NAME = 
			"/questionnaire/questionList";
	
	private static final String QUESTIONNAIRE_TYPES_ACTION_MENU_VIEW_NAME = 
			"/questionnaire/includes/questionnaireTypesActionMenu";
	
	private static final String QUESTIONNAIRE_TYPES_ROW_ACTION_MENU_VIEW_NAME =
			"/questionnaire/includes/questionnaireTypesRowActionMenu";
	
	private static final String QUESTIONNAIRE_SECTIONS_ACTION_MENU_VIEW_NAME =
			"/questionnaire/includes/questionnaireSectionsActionMenu";
	
	private static final String QUESTIONNAIRE_SECTIONS_ROW_ACTION_MENU_VIEW_NAME 
			= "/questionnaire/includes/questionnaireSectionsRowActionMenu";
	
	private static final String QUESTIONS_ACTION_MENU_VIEW_NAME = 
			"/questionnaire/includes/questionsActionMenu";
	
	private static final String QUESTIONS_ROW_ACTION_MENU_VIEW_NAME = 
			"/questionnaire/includes/questionsRowActionMenu";

	/* Model Keys */
	
	private static final String QUESTIONNAIRE_TYPE_MODEL_KEY = 
			"questionnaireType";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY =
			"administeredQuestionnaireCount";
	
	private static final String QUESTIONNAIRE_SECTION_MODEL_KEY =
			"questionnaireSection";
	
	private static final String ALLOWED_QUESTION_MODEL_KEY = "allowedQuestion";
	
	private static final String QUESTIONNAIRE_TYPE_SUMMARIES_MODEL_KEY =
			"questionnaireTypeSummaries";
	
	private static final String QUESTIONNAIRE_TYPE_SUMMARY_MODEL_KEY =
			"questionnaireTypeSummary";
	
	private static final String QUESTIONNAIRE_SECTION_SUMMARIES_MODEL_KEY =
			"questionnaireSectionSummaries";
	
	private static final String QUESTION_ANSWER_SUMMARIES_MODEL_KEY = 
			"questionAnswerSummaries";
	
	/* Services */
	
	@Autowired
	@Qualifier("questionnaireReportService")
	private QuestionnaireReportService questionnaireReportService;
	
	@Autowired
	@Qualifier("questionService")
	private QuestionService questionService;
	
	
	/* Helpers */
	/* Property Editors */
	
	@Autowired
	@Qualifier("questionnaireTypePropertyEditorFactory")
	private PropertyEditorFactory questionnaireTypePropertyEditorFactory;
	
	@Autowired
	@Qualifier("questionnaireSectionPropertyEditorFactory")
	private PropertyEditorFactory questionnaireSectionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("questionnaireCategoryPropertyEditorFactory")
	private PropertyEditorFactory questionnaireCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("allowedQuestionPropertyEditorFactory")
	private PropertyEditorFactory allowedQuestionPropertyEditorFactory;
	
	/**
	 * 
	 */
	public QuestionnaireReportController() {
	}
	
	/* Model And Views */
	
	/**
	 * Returns a model and view of the questionnaireTypes list screen
	 * @return ModelAndView - model and view of the questionnaireTypes list 
	 * screen
	 */
	@RequestMapping(value = "questionnaireTypeList.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypeList(){
		
		ModelMap map = new ModelMap();
		
		List<QuestionnaireTypeSummary> questionnaireTypeSummaries = 
				this.questionnaireReportService
				.findAllQuestionnaireTypeSummaries();
		
		map.addAttribute(QUESTIONNAIRE_TYPE_SUMMARIES_MODEL_KEY,
				questionnaireTypeSummaries);
		
		return new ModelAndView(QUESTIONNAIRE_TYPE_LIST_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view of the questionnaireSections list screen
	 * @param questionnaireType - questionnaireType of the sections being listed
	 * @return ModelAndView - model and view of the questionnaireSections list 
	 * screen
	 */
	@RequestMapping(value = "questionnaireSectionList.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionList(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType){
		
		ModelMap map = new ModelMap();
		
		QuestionnaireTypeSummary questionnaireTypeSummary = 
				this.questionnaireReportService.summarize(questionnaireType);
		List<QuestionnaireSectionSummary> questionnaireSectionSummaries =
				this.questionnaireReportService
				.findQuestionnaireSectionSummariesByQuestionnaireType(
						questionnaireType);
		
		map.addAttribute(QUESTIONNAIRE_TYPE_SUMMARY_MODEL_KEY,
				questionnaireTypeSummary);
		map.addAttribute(QUESTIONNAIRE_SECTION_SUMMARIES_MODEL_KEY,
				questionnaireSectionSummaries);
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY,
				questionnaireType);
		
		return new ModelAndView(QUESTIONNAIRE_SECTION_LIST_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view of the questions list screen
	 * @param questionnaireSection - QuestionnaireSection of the listed questions
	 * @return ModelAndView - model and view of the questions list screen
	 */
	@RequestMapping(value = "questionList.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionList(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection){
		
		List<AllowedQuestion> allowedQuestions =
				this.questionService
				.findAllAllowedQuestionsByQuestionnaireSection(
						questionnaireSection);
		LinkedHashMap<QuestionSummary, List<AnswerValueSummary>> 
				questionAnswerSummaries 
					= new LinkedHashMap<QuestionSummary, 
					List<AnswerValueSummary>>();
		
		for(AllowedQuestion allowedQuestion : allowedQuestions){
			questionAnswerSummaries.put(
					this.questionnaireReportService
						.summarize(allowedQuestion), 
					this.questionnaireReportService
						.findAnswerValueSummariesByAllowedQuestion(
								allowedQuestion));
		}
		QuestionnaireTypeSummary questionnaireTypeSummary = 
				this.questionnaireReportService
				.summarize(questionnaireSection.getQuestionnaireType());
		ModelMap map = new ModelMap();
		
		int count = this.questionnaireReportService
				.findAdministeredQuestionnaireCountByQuestionnaireType(
						questionnaireSection.getQuestionnaireType());
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY, count);
		map.addAttribute(QUESTION_ANSWER_SUMMARIES_MODEL_KEY,
				questionAnswerSummaries);
		map.addAttribute(QUESTIONNAIRE_TYPE_SUMMARY_MODEL_KEY,
				questionnaireTypeSummary);
		map.addAttribute(QUESTIONNAIRE_SECTION_MODEL_KEY, questionnaireSection);
		
		return new ModelAndView(QUESTION_LIST_VIEW_NAME, map);
	}
	
	/* Action Menus Displays */
	
	
	/**
	 * Displays the questionnaireTypes action menu
	 * @return ModelAndView - questionnaireTypes action menu
	 */
	@RequestMapping(value = "questionnaireTypesActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypesActionMenuDisplay(){
		ModelMap map = new ModelMap();
		
		return new ModelAndView(QUESTIONNAIRE_TYPES_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the questionnaireTypes row action menu
	 * @param questionnaireType - questionnaireType of current row
	 * @return ModelAndView - questionnaireTypes row action menu
	 */
	@RequestMapping(value = "questionnaireTypesRowActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypesRowActionMenuDisplay(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType){
		ModelMap map = new ModelMap();
		
		int count = this.questionnaireReportService
				.findAdministeredQuestionnaireCountByQuestionnaireType(
						questionnaireType);
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY, count);
		
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY, questionnaireType);
		
		return new ModelAndView(
				QUESTIONNAIRE_TYPES_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the questionnaireSections action menu
	 * @param questionnaireType - QuestionnaireType of the listed
	 * questionnaireSections
	 * @return ModelAndView - questionnaireSections action menu
	 */
	@RequestMapping(value = "questionnaireSectionsActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionsActionMenuDisplay(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType){
		ModelMap map = new ModelMap();
		
		int count = this.questionnaireReportService
				.findAdministeredQuestionnaireCountByQuestionnaireType(
						questionnaireType);
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY, count);
		
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY, questionnaireType);
		
		return new ModelAndView(
				QUESTIONNAIRE_SECTIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the questionnaireSections row action menu
	 * @param questionnaireSection - questionnaireSection of the current row
	 * @return ModelAndView - questionnaireSections row action menu
	 */
	@RequestMapping(value = "questionnaireSectionsRowActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionsRowActionMenuDisplay(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection){
		ModelMap map = new ModelMap();
		
		int count = this.questionnaireReportService
				.findAdministeredQuestionnaireCountByQuestionnaireType(
						questionnaireSection.getQuestionnaireType());
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY, count);
		map.addAttribute(QUESTIONNAIRE_SECTION_MODEL_KEY, questionnaireSection);
		
		return new ModelAndView(
				QUESTIONNAIRE_SECTIONS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the questions action menu
	 * @param questionnaireSection - QuestionnaireSection of the listed questions
	 * @return ModelAndView - questions action menu
	 */
	@RequestMapping(value = "questionsActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionsActionMenuDisplay(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection){
		ModelMap map = new ModelMap();
		
		int count = this.questionnaireReportService
				.findAdministeredQuestionnaireCountByQuestionnaireType(
						questionnaireSection.getQuestionnaireType());
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY, count);
		
		map.addAttribute(QUESTIONNAIRE_SECTION_MODEL_KEY, questionnaireSection);
		
		return new ModelAndView(
				QUESTIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the questions row action menu
	 * @param allowedQuestion - AllowedQuestion of the current row
	 * @return ModelAndView - questions row action menu
	 */
	@RequestMapping(value = "questionsRowActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionsRowActionMenuDisplay(
			@RequestParam(value="allowedQuestion", required=true)
			final AllowedQuestion allowedQuestion){
		ModelMap map = new ModelMap();
		int count = this.questionnaireReportService
				.findAdministeredQuestionnaireCountByQuestionnaireType(
						allowedQuestion
						.getQuestionnaireSection().getQuestionnaireType());
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_COUNT_MODEL_KEY, count);
		map.addAttribute(ALLOWED_QUESTION_MODEL_KEY, allowedQuestion);
		
		return new ModelAndView(
				QUESTIONS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(QuestionnaireType.class,
				this.questionnaireTypePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(QuestionnaireSection.class,
				this.questionnaireSectionPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(QuestionnaireCategory.class,
				this.questionnaireCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(AllowedQuestion.class,
				this.allowedQuestionPropertyEditorFactory
				.createPropertyEditor());
	}
}
