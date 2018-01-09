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
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.report.AdministeredQuestionnaireSummary;
import omis.questionnaire.report.AdministeredQuestionnaireReportService;
import omis.questionnaire.report.AdministeredQuestionnaireSectionSummary;

/**
 * QuestionnaireReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 13, 2016)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/questionnaire/")
@PreAuthorize("hasRole('USER')")
public class QuestionnaireSectionReportController {
	
	/* View Names */
	
	private static final String SECTION_LIST_VIEW_NAME = 
			"/questionnaire/sectionList";
	
	private static final String QUESTIONNAIRE_SECTIONS_ACTION_MENU_VIEW_NAME
		= "/questionnaire/includes/administeredQuestionnaireSectionsActionMenu";
	
	private static final String QUESTIONNAIRE_SECTIONS_ROW_ACTION_MENU_VIEW_NAME
		= "/questionnaire/includes/administeredQuestionnaireSectionsRowActionMenu";
	
	/* Model Keys */
	
	private static final String QUESTIONNAIRE_SECTION_SUMMARIES_MODEL_KEY =
			"questionnaireSectionSummaries";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_SUMMARY_MODEL_KEY =
			"administeredQuestionnaireSummary";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_MODEL_KEY =
			"administeredQuestionnaire";
	
	private static final String QUESTIONNAIRE_TYPE_MODEL_KEY =
			"questionnaireType";
	
	private static final String
		ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_MODEL_KEY =
			"administeredQuestionnaireSectionStatus";
	
	private static final String OFFENDER_MODEL_KEY = "offender";//or answerer?
	
	/* Service */
	
	@Autowired
	@Qualifier("administeredQuestionnaireReportService")
	private AdministeredQuestionnaireReportService
		administeredQuestionnaireReportService;
	
	
	/* Property Editors */
	
	@Autowired
	@Qualifier("administeredQuestionnairePropertyEditorFactory")
	private PropertyEditorFactory administeredQuestionnairePropertyEditorFactory;
	
	@Autowired
	@Qualifier("administeredQuestionnaireSectionStatusPropertyEditorFactory")
	private PropertyEditorFactory 
		administeredQuestionnaireSectionStatusPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Helpers */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor */
	
	/**
	 * Default Constructor for QuestionnaireSectionReportController
	 */
	public QuestionnaireSectionReportController() {
	}
	
	/* List Model And Views */
	
	/**
	 * Returns a view of the questionnaire section list by 
	 * administeredQuestionnaire
	 * @param offender - offender
	 * @param questionnaire - administered questionnaire
	 * @return view of the questionnaire section list by 
	 * administeredQuestionnaire
	 */
	@RequestMapping(value = "sectionList.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView sectionList(@RequestParam(value="offender",
		required=true) final Offender offender, 
		@RequestParam(value="administeredQuestionnaire", required=true) 
		final AdministeredQuestionnaire questionnaire){
		
		ModelMap map = new ModelMap();
		
		AdministeredQuestionnaireSummary questionnaireSummary =
				this.administeredQuestionnaireReportService.summarize(questionnaire);
		
		List<AdministeredQuestionnaireSectionSummary> questionSectionSummaries =
				this.administeredQuestionnaireReportService
				.findAdministeredQuestionnaireSectionSummariesByAdministeredQuestionnaire(
						questionnaire);
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_SUMMARY_MODEL_KEY, 
				questionnaireSummary);
		map.addAttribute(QUESTIONNAIRE_SECTION_SUMMARIES_MODEL_KEY, 
				questionSectionSummaries);
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_MODEL_KEY, questionnaire);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(SECTION_LIST_VIEW_NAME, map);
	}
	
	/* Action Menu Model And Views */
	
	/**
	 * Displays the action menu for the questionnaire sections screen
	 * @param offender
	 * @param administeredQuestionnaire
	 * @return ModelAndView - view that displays the action menu for the 
	 * questionnaire sections screen
	 */
	@RequestMapping(value = "/administeredQuestionnaireSectionsActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayQuestionnaireSectionsActionMenu(
			@RequestParam(value="offender", 
			required=true) final Offender offender,
			@RequestParam(value="administeredQuestionnaire", required=true)
			final AdministeredQuestionnaire 
				administeredQuestionnaire){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY,
				administeredQuestionnaire.getQuestionnaireType());
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_MODEL_KEY,
				administeredQuestionnaire);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(
				QUESTIONNAIRE_SECTIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for the questionnaire section rows
	 * @param offender
	 * @param administeredQuestionnaireSectionStatus
	 * @return ModelAndView - view that displays the action menu for the 
	 * questionnaire section rows
	 */
	@RequestMapping(value = "/administeredQuestionnaireSectionsRowActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayQuestionnaireSectionsRowActionMenu(
			@RequestParam(value="offender", 
			required=true) final Offender offender,
			@RequestParam(value="administeredQuestionnaireSectionStatus", 
			required=true) final AdministeredQuestionnaireSectionStatus 
				administeredQuestionnaireSectionStatus){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_MODEL_KEY,
				administeredQuestionnaireSectionStatus);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(
				QUESTIONNAIRE_SECTIONS_ROW_ACTION_MENU_VIEW_NAME, map);
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
		binder.registerCustomEditor(AdministeredQuestionnaireSectionStatus.class, 
				this.administeredQuestionnaireSectionStatusPropertyEditorFactory
				.createPropertyEditor());
	}
	
	
	
}

