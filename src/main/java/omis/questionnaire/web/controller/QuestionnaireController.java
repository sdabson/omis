package omis.questionnaire.web.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;
import omis.questionnaire.domain.QuestionConditionality;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.domain.SectionType;
import omis.questionnaire.report.QuestionnaireReportService;
import omis.questionnaire.report.QuestionnaireTypeSummary;
import omis.questionnaire.service.QuestionService;
import omis.questionnaire.service.QuestionnaireSectionService;
import omis.questionnaire.service.QuestionnaireTypeService;
import omis.questionnaire.web.form.AllowedAnswerItem;
import omis.questionnaire.web.form.ItemOperation;
import omis.questionnaire.web.form.QuestionAnswerEditForm;
import omis.questionnaire.web.form.QuestionAnswerEditItem;
import omis.questionnaire.web.form.QuestionnaireSectionForm;
import omis.questionnaire.web.form.QuestionnaireTypeForm;
import omis.questionnaire.web.validator.QuestionAnswerEditFormValidator;
import omis.questionnaire.web.validator.QuestionEditFormValidator;
import omis.questionnaire.web.validator.QuestionnaireSectionFormValidator;
import omis.questionnaire.web.validator.QuestionnaireTypeFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * QuestionnaireController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 5, 2016)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/questionnaire/")
@PreAuthorize("hasRole('USER')")
public class QuestionnaireController {
	
	/* View Names */
	
	private static final String QUESTIONNAIRE_TYPE_EDIT_VIEW_NAME = 
			"/questionnaire/questionnaireTypeEdit";
	
	private static final String QUESTIONNAIRE_SECTION_EDIT_VIEW_NAME = 
			"/questionnaire/questionnaireSectionEdit";
	
	private static final String QUESTION_ANSWER_EDIT_VIEW_NAME = 
			"/questionnaire/questionAnswerEdit";
	
	private static final String QUESTION_EDIT_VIEW_NAME = 
			"/questionnaire/questionEdit";
	
	private static final String QUESTION_ANSWER_EDIT_ITEM_CONTENT_VIEW_NAME =
			"questionnaire/includes/questionAnswerEditItemContent";
	
	private static final String ANSWER_EDIT_ITEM_CONTENT_VIEW_NAME =
			"questionnaire/includes/answerEditItemContent";
	
	private static final String SINGLE_ANSWER_EDIT_ITEM_CONTENT_VIEW_NAME =
			"questionnaire/includes/singleAnswerEditItemContent";
	
	private static final String QUESTIONNAIRE_TYPE_ACTION_MENU_VIEW_NAME = 
			"/questionnaire/includes/questionnaireTypeActionMenu";
	
	
	private static final String QUESTIONNAIRE_SECTION_ACTION_MENU_VIEW_NAME =
			"/questionnaire/includes/questionnaireSectionActionMenu";
	
	private static final String QUESTION_ANSWER_EDIT_ACTION_MENU_VIEW_NAME =
			"/questionnaire/includes/questionAnswerEditActionMenu";
	
	private static final String QUESTION_ACTION_MENU_VIEW_NAME =
			"/questionnaire/includes/questionActionMenu";
	
	private static final String QUESTIONNAIRE_TYPE_LIST_REDIRECT =
			"redirect:/questionnaire/questionnaireTypeList.html";
	
	private static final String QUESTIONNAIRE_SECTION_LIST_REDIRECT =
			"redirect:/questionnaire/questionnaireSectionList.html?"
			+ "questionnaireType=%d";
	
	private static final String QUESTION_LIST_REDIRECT =
			"redirect:/questionnaire/questionList.html?"
					+ "questionnaireSection=%d";
	
	private static final String QUESTIONS_VIEW_NAME = 
			"questionnaire/json/questions";
	
	private static final String ANSWER_VALUES_VIEW_NAME = 
			"questionnaire/json/answerValues";
	
	/* Model Keys */
	
	private static final String QUESTIONNAIRE_CATEGORIES_MODEL_KEY =
			"questionnaireCategories";
	
	private static final String QUESTIONNAIRE_TYPE_MODEL_KEY =
			"questionnaireType";
	
	private static final String QUESTIONNAIRE_SECTION_MODEL_KEY =
			"questionnaireSection";
	
	private static final String ALLOWED_QUESTION_MODEL_KEY = "allowedQuestion";
	
	private static final String QUESTIONNAIRE_TYPE_FORM_MODEL_KEY =
			"questionnaireTypeForm";
	
	private static final String QUESTIONNAIRE_SECTION_FORM_MODEL_KEY =
			"questionnaireSectionForm";
	
	private static final String QUESTION_ANSWER_EDIT_FORM_MODEL_KEY =
			"questionAnswerEditForm";
	
	private static final String QUESTION_ANSWER_EDIT_ITEM_MODEL_KEY =
			"questionAnswerEditItem";
	
	private static final String ALLOWED_ANSWER_ITEM_MODEL_KEY =
			"allowedAnswerItem";
	
	private static final String QUESTION_ANSWER_EDIT_ITEM_INDEX =
			"questionAnswerEditItemIndex";
	
	private static final String ALLOWED_ANSWER_ITEM_INDEXES_MODEL_KEY =
			"allowedAnswerItemIndexes";
	
	private static final String ALLOWED_ANSWER_ITEM_INDEX_MODEL_KEY =
			"allowedAnswerItemIndex";
	
	private static final String SECTION_TYPES_MODEL_KEY = "sectionTypes";
	
	private static final String QUESTION_TYPES_MODEL_KEY = "questionTypes";
	
	private static final String QUESTIONS_MODEL_KEY = "questions";
	
	private static final String ANSWER_VALUES_MODEL_KEY = "answerValues";
	
	private static final String QUESTIONNAIRE_TYPE_SUMMARY_MODEL_KEY =
			"questionnaireTypeSummary";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"entity.exists";
	
	/* Error Bundle */
	
	private static final String ERROR_BUNDLE_NAME = 
			"omis.questionnaire.msgs.form";
	
	/* Services */
	
	@Autowired
	@Qualifier("questionnaireTypeService")
	private QuestionnaireTypeService questionnaireTypeService;
	
	@Autowired
	@Qualifier("questionnaireSectionService")
	private QuestionnaireSectionService questionnaireSectionService;
	
	@Autowired
	@Qualifier("questionService")
	private QuestionService questionService;
	
	@Autowired
	@Qualifier("questionnaireReportService")
	private QuestionnaireReportService questionnaireReportService;
	
	/* Helpers/Validators */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("questionnaireTypeFormValidator")
	private QuestionnaireTypeFormValidator questionnaireTypeFormValidator;
	
	@Autowired
	@Qualifier("questionnaireSectionFormValidator")
	private QuestionnaireSectionFormValidator questionnaireSectionFormValidator;
	
	@Autowired
	@Qualifier("questionAnswerEditFormValidator")
	private QuestionAnswerEditFormValidator questionAnswerEditFormValidator;
	
	@Autowired
	@Qualifier("questionEditFormValidator")
	private QuestionEditFormValidator questionEditFormValidator;
	
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
	@Qualifier("sectionTypePropertyEditorFactory")
	private PropertyEditorFactory sectionTypePropertyEditorFactory;
	
	@Autowired
	@Qualifier("questionPropertyEditorFactory")
	private PropertyEditorFactory questionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("answerValuePropertyEditorFactory")
	private PropertyEditorFactory answerValuePropertyEditorFactory;
	
	@Autowired
	@Qualifier("allowedQuestionPropertyEditorFactory")
	private PropertyEditorFactory allowedQuestionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("allowedAnswerPropertyEditorFactory")
	private PropertyEditorFactory allowedAnswerPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/**
	 * Default constructor for QuestionnaireController
	 */
	public QuestionnaireController() {
	}
	
	/* Model And Views */
	
	/**
	 * Returns a model and view for creating a questionnaire type
	 * @return ModelAndView - model and view for creating a questionnaire type
	 */
	@RequestMapping(value = "questionnaireTypeCreate.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypeCreate(){
		return this.prepareTypeEditMav(null, new QuestionnaireTypeForm());
	}
	
	/**
	 * Attempts to save a questionnaire type and return to the questionnaireType
	 * list, or back to QuestionnaireType creation on form validation error
	 * @param form - QuestionnaireTypeFormValidator
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - QuestionnaireType List screen on successful save,
	 * or back to questionnaireType create form on validation error
	 * @throws DuplicateEntityFoundException - when questionnaireType already 
	 * exists 
	 */
	@RequestMapping(value = "questionnaireTypeCreate.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypeSave(
			final QuestionnaireTypeForm form, 
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		this.questionnaireTypeFormValidator.validate(form, bindingResult);
		if(bindingResult.hasErrors()){
			return this.prepareTypeEditMav(null, form);
		}
		else{
			this.questionnaireTypeService.create(form.getTitle(),
					form.getShortTitle(), form.getCycle(), form.getValid(),
					form.getStartDate(), form.getEndDate(),
					form.getQuestionnaireHelp(), form.getCategory());
			
			return new ModelAndView(
					String.format(QUESTIONNAIRE_TYPE_LIST_REDIRECT));
		}
	}
	
	/**
	 * Returns a model and view for editing a questionnaire type
	 * @param questionnaireType - QuestionnaireType to edit
	 * @return ModelAndView - model and view for editing a questionnaire type
	 */
	@RequestMapping(value = "questionnaireTypeEdit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypeEdit(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType){
		
		QuestionnaireTypeForm form = new QuestionnaireTypeForm();
		form.setCycle(questionnaireType.getCycle());
		form.setEndDate(questionnaireType.getDateRange().getEndDate());
		form.setQuestionnaireHelp(questionnaireType.getQuestionnaireHelp());
		form.setShortTitle(questionnaireType.getShortTitle());
		form.setStartDate(questionnaireType.getDateRange().getStartDate());
		form.setTitle(questionnaireType.getTitle());
		form.setValid(questionnaireType.getValid());
		form.setCategory(questionnaireType.getQuestionnaireCategory());
		return this.prepareTypeEditMav(questionnaireType, form);
	}
	
	/**
	 *  Attempts to update  a questionnaire type and return to the
	 * questionnaireType list, or back to QuestionnaireType editing on form 
	 * validation error
	 * @param questionnaireType
	 * @param form - QuestionnaireTypeFormValidator
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - QuestionnaireType List screen on successful update,
	 * or back to questionnaireType edit form on validation error
	 * @throws DuplicateEntityFoundException - when questionnaireType already 
	 * exists 
	 */
	@RequestMapping(value = "questionnaireTypeEdit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypeUpdate(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType,
			final QuestionnaireTypeForm form, 
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.questionnaireTypeFormValidator.validate(form, bindingResult);
		if(bindingResult.hasErrors()){
			return this.prepareTypeEditMav(questionnaireType, form);
		}
		else{
			this.questionnaireTypeService.update(questionnaireType,
					form.getTitle(), form.getShortTitle(), form.getCycle(),
					form.getValid(), form.getStartDate(), form.getEndDate(),
					form.getQuestionnaireHelp(),
					questionnaireType.getQuestionnaireCategory());
			
			return new ModelAndView(QUESTIONNAIRE_TYPE_LIST_REDIRECT);
		}
	}
	
	/**
	 * Returns a model and view for creating a questionnaire section
	 * @param questionnaireType - QuestionnaireType creating Section for
	 * @return ModelAndView - model and view for creating a questionnaire section
	 */
	@RequestMapping(value = "questionnaireSectionCreate.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionCreate(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType){
		return this.prepareSectionEditMav(null, questionnaireType, 
				new QuestionnaireSectionForm());
	}
	
	/**
	 * Attempts to save  a questionnaire section and return to the section
	 * list, or back to QuestionnaireSection creation on form validation error
	 * @param questionnaireType - QuestionnaireType creating section for
	 * @param form - QuestionnaireSectionForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - return to the section
	 * list on successful save, or back to QuestionnaireSection creation on
	 * form validation error
	 * @throws DuplicateEntityFoundException - when questionnaireSection already
	 * exists with given parameters
	 */
	@RequestMapping(value = "questionnaireSectionCreate.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionSave(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType,
			final QuestionnaireSectionForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.questionnaireSectionFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareSectionEditMav(null, questionnaireType, form);
		}
		else{
			//count current sections and add one for sort order
			Short sortOrder = (short) (this.questionnaireSectionService
					.countSectionsByQuestionnaireType(questionnaireType) + 1);
			
			this.questionnaireSectionService.create(form.getTitle(), sortOrder,
					form.getSectionNumber(), form.getSectionType(),
					form.getSectionHelp(), questionnaireType);
			
			return new ModelAndView(
					String.format(QUESTIONNAIRE_SECTION_LIST_REDIRECT,
							questionnaireType.getId()));
		}
	}
	
	/**
	 * Returns a model and view for editing a questionnaire section
	 * @param questionnaireSection - QuestionnaireSection to edit
	 * @return ModelAndView - model and view for editing a questionnaire section
	 */
	@RequestMapping(value = "questionnaireSectionEdit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionEdit(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection){
		
		QuestionnaireSectionForm form = new QuestionnaireSectionForm();
		form.setSectionHelp(questionnaireSection.getSectionHelp());
		form.setSectionNumber(questionnaireSection.getSectionNumber());
		form.setSectionType(questionnaireSection.getSectionType());
		form.setTitle(questionnaireSection.getTitle());
		
		return this.prepareSectionEditMav(questionnaireSection,
				questionnaireSection.getQuestionnaireType(), 
				form);
	}
	
	/**
	 * Attempts to update a questionnaire section and return to the section
	 * list, or back to QuestionnaireSection creation on form validation error
	 * @param questionnaireSection - QuestionnaireSection to update
	 * @param form - QuestionnaireSectionForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - return to the section
	 * list on successful update, or back to QuestionnaireSection creation on
	 * form validation error
	 * @throws DuplicateEntityFoundException - when questionnaireSection already
	 * exists with given parameters
	 */
	@RequestMapping(value = "questionnaireSectionEdit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionUpdate(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection,
			final QuestionnaireSectionForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.questionnaireSectionFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareSectionEditMav(questionnaireSection,
					questionnaireSection.getQuestionnaireType(), form);
		}
		else{
			this.questionnaireSectionService.update(questionnaireSection,
					form.getTitle(), questionnaireSection.getSortOrder(),
					form.getSectionNumber(), form.getSectionType(),
					form.getSectionHelp(),
					questionnaireSection.getQuestionnaireType());
			
			return new ModelAndView(
					String.format(QUESTIONNAIRE_SECTION_LIST_REDIRECT,
						questionnaireSection.getQuestionnaireType().getId()));
		}
	}
	
	/**
	 * Returns a model and view for editing (creating and updating) all of
	 * a QuestionnaireSections questions and their possible answers
	 * @param questionnaireSection - QuestionnaireSection to edit the questions
	 * of
	 * @return ModelAndView - model and view for editing (creating and updating)
	 * all of a QuestionnaireSections questions and their possible answers
	 */
	@RequestMapping(value = "questionAnswerEdit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionAnswerEdit(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection){
		return this.prepareQuestionAnswerEditMav(questionnaireSection,
				populateQuestionAnswerEditForm(questionnaireSection));
	}
	
	/**
	 * Attempts to create/update the list of questions/answers for the
	 * QuestionnaireSection and return to the section list,
	 * or back to the QuestionAnswerEdit form on form validation error
	 * @param questionnaireSection - QuestionnaireSection to update questions 
	 * for
	 * @param form - QuestionAnswerEditForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - return to the section list on successful update 
	 * of list, or back to the QuestionAnswerEdit form on form validation error
	 * @throws DuplicateEntityFoundException - when a question, allowedQuestion,
	 * answerValue, or allowedAnswer already exists with given parameters
	 */
	@RequestMapping(value = "questionAnswerEdit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionAnswerUpdate(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection,
			final QuestionAnswerEditForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		//The following code (until the form validation) is used to ensure
		//that the questionAnswerItems are returned to the screen in the proper
		//order (as sortOrder is changed dynamically during user interaction,
		//but the item indexes are not.)
		if(form.getQuestionAnswerEditItems() != null){
			List<QuestionAnswerEditItem> qaItems =
					new ArrayList<QuestionAnswerEditItem>();
			for(QuestionAnswerEditItem item : form.getQuestionAnswerEditItems()){
				if(item.getSortOrder() != null){
					qaItems.add(item);
				//ensures that items that were not created/removed are added, 
					//which would create a nullPointerException
				}
			}
			if(qaItems != null){
				Collections.sort(qaItems, 
						new Comparator<QuestionAnswerEditItem>() {
					@Override
					public int compare(QuestionAnswerEditItem o1,
							QuestionAnswerEditItem o2) {
						return o1.getSortOrder().compareTo(o2.getSortOrder());
					}
				});
			}int i =0;
			for(QuestionAnswerEditItem qaItem : qaItems){
				System.out.println(i + " " + qaItem.getQuestionNumber());
				i++;
			}
			form.setQuestionAnswerEditItems(qaItems);
		}
		
		this.questionAnswerEditFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareQuestionAnswerEditMav(questionnaireSection, 
					form);
		}
		else{
			
			processQuestionAnswerEditItems(form.getQuestionAnswerEditItems(),
					questionnaireSection);
			
			return new ModelAndView(
					String.format(QUESTIONNAIRE_SECTION_LIST_REDIRECT,
						questionnaireSection.getQuestionnaireType().getId()));
		}
	}
	
	/**
	 * Returns a model and view of a single question/answer edit item
	 * @param index - current index of the Question/Answer Edit Items
	 * @return ModelAndView - model and view of a single question/answer edit 
	 * item
	 */
	@RequestMapping(value = "addQuestionAnswerItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayQuestionAnswerEditItem(
			@RequestParam(value = "questionAnswerEditItemIndex",
			required = true) final Integer index){
		ModelMap map = new ModelMap();
		
		QuestionAnswerEditItem item = new QuestionAnswerEditItem();
		
		
		item.setOperation(ItemOperation.CREATE);
		item.setRequired(false);
		item.setValid(false);
		map.addAttribute(QUESTION_TYPES_MODEL_KEY, QuestionCategory.values());
		map.addAttribute(QUESTION_ANSWER_EDIT_ITEM_MODEL_KEY, item);
		map.addAttribute(QUESTION_ANSWER_EDIT_ITEM_INDEX, index);
		return new ModelAndView(QUESTION_ANSWER_EDIT_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	/**
	 * Returns a model and view of a single Allowed Answer Item 
	 * @param qaIndex - index of Question/Answer Edit item that the Allowed
	 * Answer Item is being added to
	 * @param aaIndex - index of current Allowed Answer Item for current 
	 * Question/Answer Item
	 * @return ModelAndView - model and view of a single Allowed Answer Item
	 */
	@RequestMapping(value = "addAllowedAnswerItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayAllowedAnswerItem(
			@RequestParam(value = "questionAnswerEditItemIndex",
			required = true) final Integer qaIndex,
			@RequestParam(value = "allowedAnswerItemIndex",
			required = true) final Integer aaIndex){
		ModelMap map = new ModelMap();
		
		AllowedAnswerItem item = new AllowedAnswerItem();
		
		item.setOperation(ItemOperation.CREATE);
		map.addAttribute(ALLOWED_ANSWER_ITEM_MODEL_KEY, item);
		map.addAttribute(QUESTION_ANSWER_EDIT_ITEM_INDEX, qaIndex);
		map.addAttribute(ALLOWED_ANSWER_ITEM_INDEX_MODEL_KEY, aaIndex);
		return new ModelAndView(ANSWER_EDIT_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	/**
	 * Returns a model and view for editing of a single question and its answers
	 * @param allowedQuestion - allowedQuestion to edit
	 * @return ModelAndView - model and view for editing of a single question 
	 * and its answers
	 */
	@RequestMapping(value = "questionEdit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionEdit(
			@RequestParam(value = "allowedQuestion",
			required = true) final AllowedQuestion allowedQuestion){
		
		List<AllowedAnswer> allowedAnswers = this.questionService
				.findAllowedAnswersByAllowedQuestion(allowedQuestion);
		List<AllowedAnswerItem> aaItems = new ArrayList<AllowedAnswerItem>();
		for(AllowedAnswer allowedAnswer : allowedAnswers){
			AllowedAnswerItem aaItem = new AllowedAnswerItem();
			aaItem.setAllowedAnswer(allowedAnswer);
			aaItem.setAnswerValue(allowedAnswer.getAnswerValue());
			aaItem.setExistingAnswer(true);
			aaItem.setOperation(ItemOperation.UPDATE);
			aaItem.setDescription(
					allowedAnswer.getAnswerValue().getDescription());
			aaItems.add(aaItem);
		}
		
		QuestionAnswerEditItem item = new QuestionAnswerEditItem();
		
		if(allowedAnswers.size() > 0){
			item.setAllowedAnswerItems(aaItems);
		}
		
		item.setAllowedQuestion(allowedQuestion);
		item.setAnswerCardinality(allowedQuestion.getAnswerCardinality());
		item.setExistingQuestion(true);
		item.setMultipleCardinality(
				(allowedQuestion.getAnswerCardinality()
						.equals(AnswerCardinality.MULTIPLE) ? true 
								: false));
		item.setQuestion(allowedQuestion.getQuestion());
		item.setQuestionCategory(allowedQuestion.getQuestion()
				.getQuestionCategory());
		item.setQuestionHelp(allowedQuestion.getQuestionHelp());
		item.setQuestionNumber(allowedQuestion.getQuestionNumber());
		item.setQuestionText(allowedQuestion.getQuestion().getText());
		item.setOperation(ItemOperation.UPDATE);
		item.setRequired((allowedQuestion.getQuestionConditionality()
				.equals(QuestionConditionality.REQUIRED) ? true : false));
		item.setValid(allowedQuestion.getValid());
		item.setSortOrder(allowedQuestion.getSortOrder());
		
		return prepareSingleQuestionEditMav(allowedQuestion, item);
	}
	
	/**
	 * Attempts to update an allowedQuestion and its allowedAnswers and return
	 * to the question list screen on successful update, or return to the 
	 * question edit view on form validation error
	 * @param allowedQuestion - allowedQuestion being updated
	 * @param form - QuestionAnswerEditItem
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to the question list screen on successful 
	 * update, or return to the question edit view on form validation error
	 * @throws DuplicateEntityFoundException - When allowedQuestion or its
	 * answers already exists
	 */
	@RequestMapping(value = "questionEdit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionUpdate(
			@RequestParam(value = "allowedQuestion",
			required = true) final AllowedQuestion allowedQuestion,
			final QuestionAnswerEditItem form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.questionEditFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return prepareSingleQuestionEditMav(allowedQuestion, form);
		}
		else{
			List<QuestionAnswerEditItem> itemList = 
					new ArrayList<QuestionAnswerEditItem>();
			itemList.add(form);
			this.processQuestionAnswerEditItems(itemList,
					allowedQuestion.getQuestionnaireSection());
			
			return new ModelAndView(
					String.format(QUESTION_LIST_REDIRECT,
						allowedQuestion.getQuestionnaireSection().getId()));
		}
	}
	
	/**
	 * Displays an allowed answer item meant to be displayed on the single
	 * question edit form
	 * @param aaIndex - current index of allowed answer items
	 * @return ModelAndView - Displays an allowed answer item meant to be
	 * displayed on the single question edit form
	 */
	@RequestMapping(value = "addSingleAllowedAnswerItem.html",
			method = RequestMethod.GET)
	public ModelAndView displaySingleAllowedAnswerItem(
			@RequestParam(value = "allowedAnswerItemIndex",
			required = true) final Integer aaIndex){
		ModelMap map = new ModelMap();
		
		AllowedAnswerItem item = new AllowedAnswerItem();
		
		item.setOperation(ItemOperation.CREATE);
		map.addAttribute(ALLOWED_ANSWER_ITEM_MODEL_KEY, item);
		map.addAttribute(ALLOWED_ANSWER_ITEM_INDEX_MODEL_KEY, aaIndex);
		return new ModelAndView(SINGLE_ANSWER_EDIT_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	/**
	 * Removes an allowedQuestion and returns to the question list screen
	 * @param allowedQuestion - AllowedQuestion to remove
	 * @return ModelAndView - redirects back to Question List Screen
	 */
	@RequestMapping(value="/questionRemove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_EDIT')")
	public ModelAndView removeQuestion(@RequestParam(value = "allowedQuestion",
			required = true) final AllowedQuestion allowedQuestion){
		
		List<AllowedAnswer> allowedAnswers =
				this.questionService
					.findAllowedAnswersByAllowedQuestion(allowedQuestion);
		
		if(!(allowedAnswers.isEmpty())){
			for(AllowedAnswer allowedAnswer : allowedAnswers){
				this.questionService.removeAllowedAnswer(allowedAnswer);
			}
		}
		
		this.questionService
			.removeAllowedQuestion(allowedQuestion);
		
		return new ModelAndView(
				String.format(QUESTION_LIST_REDIRECT,
					allowedQuestion.getQuestionnaireSection().getId()));
	}
	
	
	/**
	 * Removes a questionnaireSection and returns to the section list screen
	 * @param questionnaireSection - QuestionnaireSection to remove
	 * @return ModelAndView - redirects to the Questionnaire Section List screen
	 */
	@RequestMapping(value="/questionnaireSectionRemove.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_EDIT')")
	public ModelAndView removeQuestionnaireSection(
			@RequestParam(value = "questionnaireSection",
			required = true) final QuestionnaireSection questionnaireSection){
		
		List<AllowedQuestion> allowedQuestions = this.questionService
				.findAllAllowedQuestionsByQuestionnaireSection(
						questionnaireSection);
		for(AllowedQuestion allowedQuestion : allowedQuestions){
			List<AllowedAnswer> allowedAnswers =
					this.questionService
						.findAllowedAnswersByAllowedQuestion(allowedQuestion);
			
			if(!(allowedAnswers.isEmpty())){
				for(AllowedAnswer allowedAnswer : allowedAnswers){
					this.questionService.removeAllowedAnswer(allowedAnswer);
				}
			}
			
			this.questionService
				.removeAllowedQuestion(allowedQuestion);
		}
		this.questionnaireSectionService.remove(questionnaireSection);
		
		return new ModelAndView(
				String.format(QUESTIONNAIRE_SECTION_LIST_REDIRECT,
						questionnaireSection.getQuestionnaireType().getId()));
	}
	
	/**
	 * Removes a questionnaireType and returns to the questionnaire type list 
	 * screen
	 * @param questionnaireType - QuestionnaireType to remove
	 * @return ModelAndView - redirects to the questionnaire type list screen
	 */
	@RequestMapping(value="/questionnaireTypeRemove.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_EDIT')")
	public ModelAndView removeQuestionnaireType(
			@RequestParam(value = "questionnaireType",
			required = true) final QuestionnaireType questionnaireType){
		
		List<QuestionnaireSection> questionnaireSections =
				this.questionnaireSectionService
				.findAllByQuestionnaireType(questionnaireType);
		
		for(QuestionnaireSection questionnaireSection : questionnaireSections){
			List<AllowedQuestion> allowedQuestions = this.questionService
					.findAllAllowedQuestionsByQuestionnaireSection(
							questionnaireSection);
			for(AllowedQuestion allowedQuestion : allowedQuestions){
				List<AllowedAnswer> allowedAnswers =
						this.questionService
							.findAllowedAnswersByAllowedQuestion(
									allowedQuestion);
				
				if(!(allowedAnswers.isEmpty())){
					for(AllowedAnswer allowedAnswer : allowedAnswers){
						this.questionService.removeAllowedAnswer(allowedAnswer);
					}
				}
				
				this.questionService
					.removeAllowedQuestion(allowedQuestion);
			}
			this.questionnaireSectionService.remove(questionnaireSection);
		}
		this.questionnaireTypeService.remove(questionnaireType);
		
		return new ModelAndView(
				String.format(QUESTIONNAIRE_TYPE_LIST_REDIRECT));
	}
	
	@RequestMapping(value="/questionnaireTypeCopy.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_EDIT')")
	public ModelAndView copyQuestionnaireType(
			@RequestParam(value = "questionnaireType",
			required = true) final QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException{
		
		this.questionnaireTypeService.copy(questionnaireType);
		
		return new ModelAndView(
				String.format(QUESTIONNAIRE_TYPE_LIST_REDIRECT));
	}
	
	
	/* Action Menus Displays */
	
	/**
	 * Displays the questionnaireType action menu
	 * @return ModelAndView - questionnaireType action menu
	 */
	@RequestMapping(value = "questionnaireTypeActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireTypeActionMenuDisplay(){
		ModelMap map = new ModelMap();
		
		return new ModelAndView(QUESTIONNAIRE_TYPE_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	/**
	 * Displays the questionnaireSection action menu
	 * @param questionnaireType - QuestionnaireType of the questionnaireSection
	 * @return ModelAndView - questionnaireSection action menu
	 */
	@RequestMapping(value = "questionnaireSectionActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionnaireSectionActionMenuDisplay(
			@RequestParam(value="questionnaireType", required=true)
			final QuestionnaireType questionnaireType){
		ModelMap map = new ModelMap();
		
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY, questionnaireType);
		
		return new ModelAndView(
				QUESTIONNAIRE_SECTION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the question/Answer (all) edit action menu
	 * @param questionnaireSection - QuestionnaireSection of the allowedQuestions
	 * being edited
	 * @return ModelAndView - question/Answer edit action menu
	 */
	@RequestMapping(value = "questionAnswerEditActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionAnswerEditActionMenuDisplay(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection){
		ModelMap map = new ModelMap();
		
		map.addAttribute(QUESTIONNAIRE_SECTION_MODEL_KEY, questionnaireSection);
		
		return new ModelAndView(
				QUESTION_ANSWER_EDIT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the question/answer (single) edit action menu
	 * @param questionnaireSection - QuestionnaireSection of the allowedQuestion
	 * being edited
	 * @return ModelAndView - question/answer (single) edit action menu
	 */
	@RequestMapping(value = "questionActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('QUESTIONNAIRE_LIST')")
	public ModelAndView questionActionMenuDisplay(
			@RequestParam(value="questionnaireSection", required=true)
			final QuestionnaireSection questionnaireSection){
		ModelMap map = new ModelMap();
		
		map.addAttribute(QUESTIONNAIRE_SECTION_MODEL_KEY, questionnaireSection);
		
		return new ModelAndView(
				QUESTION_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	/* JSON Search Methods */
	
	/**
	 * Displays the JSON for a question search
	 * @param query - query being searched
	 * @return ModelAndView - JSON for the question search
	 */
	@RequestMapping(value = "/questionSearch.json",
			method = RequestMethod.GET)
	public ModelAndView questionSearch(
			@RequestParam(value = "term", required = true)
				final String query) {
		ModelAndView mav = new ModelAndView(QUESTIONS_VIEW_NAME);
		
		List<Question> questions = this.questionService
				.findQuestionsByText(query);
		mav.addObject(QUESTIONS_MODEL_KEY, questions);
		return mav;
	}
	
	/**
	 * Displays the JSON for an answerValue search
	 * @param query - query being searched
	 * @return ModelAndView - JSON for the question search
	 */
	@RequestMapping(value = "/answerValueSearch.json",
			method = RequestMethod.GET)
	public ModelAndView answerValueSearch(
			@RequestParam(value = "term", required = true)
				final String query) {
		ModelAndView mav = new ModelAndView(ANSWER_VALUES_VIEW_NAME);
		
		List<AnswerValue> answerValues = this.questionService
				.findAnswerValuesByDescription(query);
		mav.addObject(ANSWER_VALUES_MODEL_KEY, answerValues);
		return mav;
	}
	
	
	/* Helper Methods */
	
	/**
	 * Prepares the model and view for questionnaireType edit screens
	 * @param questionnaireType - questionnaireType to be edited
	 * @param form - QuestionnaireTypeForm
	 * @return ModelAndView - model and view for questionnaireType edit screens
	 */
	private ModelAndView prepareTypeEditMav(
			final QuestionnaireType questionnaireType,
			final QuestionnaireTypeForm form){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY, questionnaireType);
		map.addAttribute(QUESTIONNAIRE_CATEGORIES_MODEL_KEY,
				this.questionnaireTypeService.findAllQuestionnaireCategories());
		map.addAttribute(QUESTIONNAIRE_TYPE_FORM_MODEL_KEY, form);
		
		return new ModelAndView(QUESTIONNAIRE_TYPE_EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Prepares the model and view for questionnaireSection edit screens
	 * @param questionnaireSection - questionnaireSection to be edited
	 * @param questionnaireType - questionnaireType of the questionnaireSection
	 * to be created/edited
	 * @param form - QuestionnaireSectionForm
	 * @return ModelAndView - model and view for questionnaireSection edit 
	 * screens
	 */
	private ModelAndView prepareSectionEditMav(
			final QuestionnaireSection questionnaireSection,
			final QuestionnaireType questionnaireType,
			final QuestionnaireSectionForm form){
		
		ModelMap map = new ModelMap();
		QuestionnaireTypeSummary questionnaireTypeSummary = 
				this.questionnaireReportService.summarize(questionnaireType);
		
		map.addAttribute(QUESTIONNAIRE_TYPE_SUMMARY_MODEL_KEY,
				questionnaireTypeSummary);
		map.addAttribute(QUESTIONNAIRE_SECTION_MODEL_KEY, questionnaireSection);
		map.addAttribute(SECTION_TYPES_MODEL_KEY,
			this.questionnaireSectionService.findAllSectionTypes());
		map.addAttribute(QUESTIONNAIRE_TYPE_MODEL_KEY,
				questionnaireType);
		map.addAttribute(QUESTIONNAIRE_SECTION_FORM_MODEL_KEY, form);
		
		return new ModelAndView(QUESTIONNAIRE_SECTION_EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Prepares the model and view for the Question/Answer Edit Screen (multiple)
	 * @param questionnaireSection - QuestionnaireSection of question/answers
	 * being edited
	 * @param form - QuestionAnswerEditForm
	 * @return ModelAndView - model and view for the Question/Answer Edit 
	 * Screen (multiple)
	 */
	private ModelAndView prepareQuestionAnswerEditMav(
			final QuestionnaireSection questionnaireSection,
			final QuestionAnswerEditForm form){
		ModelMap map = new ModelMap();
		
		Integer qaIndex;
		List<Integer> aaIndexes = new ArrayList<Integer>();
		
		if(form.getQuestionAnswerEditItems() != null){
			qaIndex = form.getQuestionAnswerEditItems().size();
			
			int qaIndexCounter = 0;
			for(QuestionAnswerEditItem item :
					form.getQuestionAnswerEditItems()){
				if(item.getAllowedAnswerItems() != null){
					aaIndexes.add(qaIndexCounter, 
							item.getAllowedAnswerItems().size());
				}
				else{
					aaIndexes.add(qaIndexCounter, 0);
				}
				qaIndexCounter++;
			}
		}
		else{
			qaIndex = 0;
		}
		
		QuestionnaireTypeSummary questionnaireTypeSummary = 
				this.questionnaireReportService
				.summarize(questionnaireSection.getQuestionnaireType());
		
		map.addAttribute(QUESTIONNAIRE_TYPE_SUMMARY_MODEL_KEY,
				questionnaireTypeSummary);
		map.addAttribute(QUESTION_ANSWER_EDIT_ITEM_INDEX, qaIndex);
		map.addAttribute(ALLOWED_ANSWER_ITEM_INDEXES_MODEL_KEY, aaIndexes);
		map.addAttribute(QUESTION_TYPES_MODEL_KEY, QuestionCategory.values());
		map.addAttribute(QUESTIONNAIRE_SECTION_MODEL_KEY, questionnaireSection);
		map.addAttribute(QUESTION_ANSWER_EDIT_FORM_MODEL_KEY, form);
		
		return new ModelAndView(QUESTION_ANSWER_EDIT_VIEW_NAME, map);
	}
	
	/**
	 *  Prepares the model and view for the Question/Answer Edit Screen (single)
	 * @param allowedQuestion - allowedQuestion being edited
	 * @param questionnaireSection - QuestionnaireSection of allowedQuestion
	 * being edited
	 * @param form - QuestionAnswerEditForm
	 * @return ModelAndView - model and view for the Question/Answer Edit 
	 * Screen (single)
	 */
	private ModelAndView prepareSingleQuestionEditMav(
			final AllowedQuestion allowedQuestion,
			final QuestionAnswerEditItem questionAnswerEditItem){
		
		ModelMap map = new ModelMap();
		
		QuestionnaireTypeSummary questionnaireTypeSummary = 
				this.questionnaireReportService
				.summarize(allowedQuestion.getQuestionnaireSection()
						.getQuestionnaireType());
		int aaIndex = this.questionService.findAllowedAnswersByAllowedQuestion(
				allowedQuestion).size();
		
		map.addAttribute(ALLOWED_QUESTION_MODEL_KEY, allowedQuestion);
		map.addAttribute(QUESTION_ANSWER_EDIT_ITEM_MODEL_KEY,
				questionAnswerEditItem);
		map.addAttribute(QUESTIONNAIRE_TYPE_SUMMARY_MODEL_KEY,
				questionnaireTypeSummary);
		map.addAttribute(QUESTION_TYPES_MODEL_KEY, QuestionCategory.values());
		map.addAttribute(ALLOWED_ANSWER_ITEM_INDEX_MODEL_KEY, aaIndex);
		
		
		return new ModelAndView(QUESTION_EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates the QuestionAnswerEditForm with a list of 
	 * questionAnswerEditItems for allowedQuestions to be edited
	 * @param questionnaireSection - QuestionnaireSection of allowedQuestions 
	 * to be edited
	 * @return QuestionAnswerEditForm - Populated form with a list of 
	 * questionAnswerEditItems for allowedQuestions to be edited
	 */
	private QuestionAnswerEditForm populateQuestionAnswerEditForm(
			final QuestionnaireSection questionnaireSection){
		List<QuestionAnswerEditItem> qaItems =
				new ArrayList<QuestionAnswerEditItem>();
		List<AllowedQuestion> allowedQuestions = this.questionService
				.findAllAllowedQuestionsByQuestionnaireSection(
						questionnaireSection);
		
		for(AllowedQuestion allowedQuestion : allowedQuestions){
			List<AllowedAnswer> allowedAnswers = this.questionService
					.findAllowedAnswersByAllowedQuestion(allowedQuestion);
			List<AllowedAnswerItem> aaItems = new ArrayList<AllowedAnswerItem>();
			for(AllowedAnswer allowedAnswer : allowedAnswers){
				AllowedAnswerItem aaItem = new AllowedAnswerItem();
				aaItem.setAllowedAnswer(allowedAnswer);
				aaItem.setAnswerValue(allowedAnswer.getAnswerValue());
				aaItem.setExistingAnswer(true);
				aaItem.setOperation(ItemOperation.UPDATE);
				aaItem.setDescription(
						allowedAnswer.getAnswerValue().getDescription());
				aaItems.add(aaItem);
			}
			
			QuestionAnswerEditItem item = new QuestionAnswerEditItem();
			
			if(allowedAnswers.size() > 0){
				item.setAllowedAnswerItems(aaItems);
			}
			
			item.setAllowedQuestion(allowedQuestion);
			item.setAnswerCardinality(allowedQuestion.getAnswerCardinality());
			item.setExistingQuestion(true);
			item.setMultipleCardinality(
					(allowedQuestion.getAnswerCardinality()
							.equals(AnswerCardinality.MULTIPLE) ? true 
									: false));
			item.setQuestion(allowedQuestion.getQuestion());
			item.setQuestionCategory(allowedQuestion.getQuestion()
					.getQuestionCategory());
			item.setQuestionHelp(allowedQuestion.getQuestionHelp());
			item.setQuestionNumber(allowedQuestion.getQuestionNumber());
			item.setQuestionText(allowedQuestion.getQuestion().getText());
			item.setOperation(ItemOperation.UPDATE);
			item.setRequired((allowedQuestion.getQuestionConditionality()
					.equals(QuestionConditionality.REQUIRED) ? true : false));
			item.setValid(allowedQuestion.getValid());
			item.setSortOrder(allowedQuestion.getSortOrder());
			
			
			qaItems.add(item);
		}
		
		QuestionAnswerEditForm form = new QuestionAnswerEditForm();
		form.setQuestionAnswerEditItems(qaItems);
		
		return form;
	}
	
	/**
	 * Processes QuestionAnswerEditItems, creates/updates/removes AllowedQuestions
	 * and AllowedAnswers
	 * @param questionAnswerEditItems - List of QuestionAnswerEditItems to be
	 * processed
	 * @param questionnaireSection - QuestionnaireSection of the questions
	 * @throws DuplicateEntityFoundException - When allowedQuestion, question,
	 * allowedAnswer, or answerValue already exist with given paramets
	 */
	private void processQuestionAnswerEditItems(
			final List<QuestionAnswerEditItem> questionAnswerEditItems,
			final QuestionnaireSection questionnaireSection)
					throws DuplicateEntityFoundException{
		if(questionAnswerEditItems != null){
			
			for(QuestionAnswerEditItem qaItem : questionAnswerEditItems){
				AllowedQuestion allowedQuestion = null;
				
				QuestionConditionality required =
						QuestionConditionality.REQUIRED;
				AnswerCardinality cardinality = AnswerCardinality.MULTIPLE;
				Boolean isRequired = qaItem.getRequired();
				Boolean isMultiple = qaItem.getMultipleCardinality();
				Boolean valid = qaItem.getValid();
				
				if(isRequired == null){//if it is null, it is false
					required = QuestionConditionality.NOT_REQUIRED;
				}
				else{
					if(isRequired){
						required = QuestionConditionality.REQUIRED;
					}
					else{
						required = QuestionConditionality.NOT_REQUIRED;
					}
				}
				
				if(qaItem.getValid() == null){//if it is null, it is false
					valid = false;
				}
				if(isMultiple == null 
							//if it is null, it is false 
						|| !(qaItem.getQuestionCategory()
								.equals(QuestionCategory.MULTIPLE_CHOICE)
						|| qaItem.getQuestionCategory()
								.equals(
									QuestionCategory.MULTIPLE_CHOICE_ESSAY))){
					
					cardinality = AnswerCardinality.SINGLE;
				}
				else{
					if(qaItem.getMultipleCardinality()){
						cardinality = AnswerCardinality.MULTIPLE;
					}
					else{
						cardinality = AnswerCardinality.SINGLE;
					}
				}
				
				if(qaItem.getOperation().equals(ItemOperation.CREATE)){
					if(qaItem.getExistingQuestion()){
						allowedQuestion = this.questionService
								.createAllowedQuestion(
										questionnaireSection,
										qaItem.getQuestion(), 
										required, cardinality,
										qaItem.getQuestionNumber(),
										qaItem.getSortOrder(), valid,
										qaItem.getQuestionHelp());
					}
					else{
						Question question = this.questionService
								.createQuestion(qaItem.getQuestionCategory(),
										qaItem.getQuestionText(), true, true);
						allowedQuestion = this.questionService.
								createAllowedQuestion(
										questionnaireSection, question,
										required, cardinality,
										qaItem.getQuestionNumber(),
										qaItem.getSortOrder(), valid,
										qaItem.getQuestionHelp());
					}
				}
				else if(qaItem.getOperation().equals(ItemOperation.UPDATE)){
					if(qaItem.getExistingQuestion()){
						allowedQuestion = this.questionService
								.updateAllowedQuestion(
										qaItem.getAllowedQuestion(),
										questionnaireSection,
										qaItem.getQuestion(), required,
										cardinality, qaItem.getQuestionNumber(),
										qaItem.getSortOrder(), valid,
										qaItem.getQuestionHelp());
					}
					else{
						Question question = this.questionService
								.createQuestion(qaItem.getQuestionCategory(),
										qaItem.getQuestionText(), true, true);
						allowedQuestion = this.questionService
								.updateAllowedQuestion(
										qaItem.getAllowedQuestion(),
										questionnaireSection, question,
										required, cardinality,
										qaItem.getQuestionNumber(),
										qaItem.getSortOrder(), valid,
										qaItem.getQuestionHelp());
					}
				}
				else{//remove
					List<AllowedAnswer> allowedAnswers =
							this.questionService
								.findAllowedAnswersByAllowedQuestion(
									qaItem.getAllowedQuestion());
					
					if(!(allowedAnswers.isEmpty())){
						for(AllowedAnswer allowedAnswer : allowedAnswers){
							this.questionService
								.removeAllowedAnswer(allowedAnswer);
						}
					}
					
					this.questionService
						.removeAllowedQuestion(qaItem.getAllowedQuestion());
				}
				if(qaItem.getAllowedAnswerItems() != null &&
						(qaItem.getQuestionCategory()
								.equals(QuestionCategory.MULTIPLE_CHOICE)
						|| qaItem.getQuestionCategory()
								.equals(QuestionCategory.MULTIPLE_CHOICE_ESSAY)
						|| qaItem.getQuestionCategory()
								.equals(QuestionCategory.SELECT_FROM_LIST))
						&& !(qaItem.getOperation()
								.equals(ItemOperation.REMOVE))){
					Short sortOrder = 0;
					for(AllowedAnswerItem aaItem :
							qaItem.getAllowedAnswerItems()){
						if(aaItem.getOperation().equals(ItemOperation.CREATE)){
							if(aaItem.getExistingAnswer()){
								this.questionService
									.createAllowedAnswer(allowedQuestion,
											aaItem.getAnswerValue(), sortOrder);
							}
							else{
								AnswerValue answerValue = this.questionService
									.createAnswerValue(aaItem.getDescription(),
												aaItem.getDescription());
								this.questionService
									.createAllowedAnswer(allowedQuestion,
											answerValue, sortOrder);
							}
							sortOrder++;
						}
						else if(aaItem.getOperation()
								.equals(ItemOperation.UPDATE)){
							if(aaItem.getExistingAnswer()){
								this.questionService
									.updateAllowedAnswer(
											aaItem.getAllowedAnswer(),
											allowedQuestion,
											aaItem.getAnswerValue(), sortOrder);
							}
							else{
								AnswerValue answerValue = this.questionService
									.createAnswerValue(aaItem.getDescription(),
												aaItem.getDescription());
								this.questionService
									.updateAllowedAnswer(
											aaItem.getAllowedAnswer(),
											allowedQuestion,
											answerValue, sortOrder);
							}
							sortOrder++;
						}
						else{//remove
							this.questionService
								.removeAllowedAnswer(aaItem.getAllowedAnswer());
						}
					}
				}
				else if((qaItem.getQuestionCategory()
								.equals(QuestionCategory.TRUE_FALSE))
						&& !(qaItem.getOperation()
								.equals(ItemOperation.REMOVE))){
						this.questionService
						.setTrueFalseAllowedAnswersToAllowedQuestion(
								allowedQuestion);
						
				}
			}
		}
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
		(ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, exception);
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
		binder.registerCustomEditor(SectionType.class,
				this.sectionTypePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Question.class,
				this.questionPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(AnswerValue.class,
				this.answerValuePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(AllowedQuestion.class,
				this.allowedQuestionPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(AllowedAnswer.class,
				this.allowedAnswerPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		
	}
	
}
