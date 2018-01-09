package omis.questionnaire.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.QuestionnaireTypeDao;
import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireTypeDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 9, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireTypeDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Questionnaire Type Already Exists With Given Title and Cycle";
	
	private final QuestionnaireTypeDao questionnaireTypeDao;
	
	private final QuestionnaireSectionDelegate questionnaireSectionDelegate;
	
	private final AllowedQuestionDelegate allowedQuestionDelegate;
	
	private final AllowedAnswerDelegate allowedAnswerDelegate;
	
	private final InstanceFactory<QuestionnaireType> 
		questionnaireTypeInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * @param questionnaireTypeDao
	 * @param questionnaireSectionDelegate
	 * @param allowedQuestionDelegate
	 * @param allowedAnswerDelegate
	 * @param questionnaireTypeInstanceFactory
	 * @param auditComponentRetriever
	 */
	public QuestionnaireTypeDelegate(
			final QuestionnaireTypeDao questionnaireTypeDao,
			final QuestionnaireSectionDelegate questionnaireSectionDelegate,
			final AllowedQuestionDelegate allowedQuestionDelegate,
			final AllowedAnswerDelegate allowedAnswerDelegate,
			final InstanceFactory<QuestionnaireType> questionnaireTypeInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.questionnaireTypeDao = questionnaireTypeDao;
		this.questionnaireSectionDelegate = questionnaireSectionDelegate;
		this.allowedQuestionDelegate = allowedQuestionDelegate;
		this.allowedAnswerDelegate = allowedAnswerDelegate;
		this.questionnaireTypeInstanceFactory = questionnaireTypeInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates a QuestionnaireType
	 * @param shortTitle - String
	 * @param title - String
	 * @param cycle - Integer
	 * @param questionnaireCategory
	 * @param valid - Boolean
	 * @param dateRange
	 * @param questionnaireHelp - String
	 * @return QuestionnaireType - newly created Questionnaire Type
	 * @throws DuplicateEntityFoundException - When Questionnaire Type Already
	 * Exists With Given Title and Cycle
	 */
	public QuestionnaireType create(final String shortTitle, final String title, 
			final Integer cycle, 
			final QuestionnaireCategory questionnaireCategory,
			final Boolean valid, final Date startDate, final Date endDate, 
			final String questionnaireHelp)
			throws DuplicateEntityFoundException{
		if(this.questionnaireTypeDao.find(title, cycle) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		QuestionnaireType questionnaireType = 
				this.questionnaireTypeInstanceFactory.createInstance();
		
		questionnaireType.setShortTitle(shortTitle);
		questionnaireType.setTitle(title);
		questionnaireType.setCycle(cycle);
		questionnaireType.setQuestionnaireCategory(questionnaireCategory);
		questionnaireType.setValid(valid);
		questionnaireType.setDateRange(new DateRange(startDate, endDate));
		questionnaireType.setQuestionnaireHelp(questionnaireHelp);
		questionnaireType.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		questionnaireType.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.questionnaireTypeDao.makePersistent(questionnaireType);
	}
	
	/**
	 * Updates a QuestionnaireType
	 * @param questionnaireType - QuestionnaireType to remove
	 * @param shortTitle - String
	 * @param title - String
	 * @param cycle - Integer
	 * @param questionnaireCategory
	 * @param valid - Boolean
	 * @param dateRange
	 * @param questionnaireHelp - String
	 * @return QuestionnaireType - updated Questionnaire Type
	 * @throws DuplicateEntityFoundException - When Questionnaire Type Already
	 * Exists With Given Title and Cycle
	 */
	public QuestionnaireType update(final QuestionnaireType questionnaireType, 
			final String shortTitle, final String title, final Integer cycle, 
			final QuestionnaireCategory questionnaireCategory, 
			final Boolean valid, final Date startDate, final Date endDate, 
			final String questionnaireHelp)
			throws DuplicateEntityFoundException{
		if(this.questionnaireTypeDao.findExcluding(title, cycle,
				questionnaireType) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		questionnaireType.setShortTitle(shortTitle);
		questionnaireType.setTitle(title);
		questionnaireType.setCycle(cycle);
		questionnaireType.setQuestionnaireCategory(questionnaireCategory);
		questionnaireType.setValid(valid);
		questionnaireType.setDateRange(new DateRange(startDate, endDate));
		questionnaireType.setQuestionnaireHelp(questionnaireHelp);
		questionnaireType.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.questionnaireTypeDao.makePersistent(questionnaireType);
	}
	
	/**
	 * Removes a QuestionnaireType
	 * @param questionnaireType - QuestionnaireType to remove
	 */
	public void remove(final QuestionnaireType questionnaireType){
		this.questionnaireTypeDao.makeTransient(questionnaireType);
	}
	
	/**
	 * Finds and returns a list of all questionnaireTypes by specified
	 * effective date
	 * @param effectiveDate - Date
	 * @return List of QuestionnaireTypes
	 */
	public List<QuestionnaireType> findAllByDate(final Date effectiveDate){
		return this.questionnaireTypeDao.findByDate(effectiveDate);
	}
	
	/**
	 * Returns a list of QuestionnaireTypes by specified QuestionnaireCategory
	 * @param category - QuestionnaireCategory
	 * @return List of QuestionnaireTypes by specified QuestionnaireCategory
	 */
	public List<QuestionnaireType> findAllByQuestionnaireCategory(
			final QuestionnaireCategory category){
		return this.questionnaireTypeDao.findByCategory(category);
	}
	
	/**
	 * Returns a list of QuestionnaireTypes by specified QuestionnaireCategory
	 * and effective date
	 * @param category - QuestionnaireCategory
	 * @param effectiveDate - Date
	 * @return List of QuestionnaireTypes by specified QuestionnaireCategory
	 * and effective date
	 */
	public List<QuestionnaireType> findAllByCategoryAndDate(
			final QuestionnaireCategory category, final Date effectiveDate){
		return this.questionnaireTypeDao.findByCategoryAndDate(
				category, effectiveDate);
	}
	
	/**
	 * Creates a copy of a questionnaireType with a cycle + 1
	 * @param questionnaireType - QuestionnaireType to copy
	 * @return QuestionnaireType - copy of a questionnaireType with a cycle + 1
	 * @throws DuplicateEntityFoundException- When Questionnaire Type Already
	 * Exists With Given Title and Cycle
	 */
	public QuestionnaireType copy(final QuestionnaireType questionnaireType)
			throws DuplicateEntityFoundException{
		
		List<QuestionnaireSection> sections =
				this.questionnaireSectionDelegate
				.findByQuestionnaireType(questionnaireType);
		
		QuestionnaireType questionnaireTypeCopy = this.create(
				questionnaireType.getShortTitle(), questionnaireType.getTitle(),
				this.questionnaireTypeDao
					.findNextCycleByQuestionnaireType(questionnaireType),
				questionnaireType.getQuestionnaireCategory(),
				questionnaireType.getValid(),
				questionnaireType.getDateRange().getStartDate(),
				questionnaireType.getDateRange().getEndDate(),
				questionnaireType.getQuestionnaireHelp());
		
		if(sections != null){
			for(QuestionnaireSection section : sections){
				QuestionnaireSection sectionCopy = 
						this.questionnaireSectionDelegate.create(
								section.getTitle(), section.getSortOrder(),
								questionnaireTypeCopy, section.getSectionType(),
								section.getSectionNumber(),
								section.getSectionHelp());
				
				List<AllowedQuestion> allowedQuestions =
						this.allowedQuestionDelegate
						.findAllByQuestionnaireSectionIncludingInvalid(section);
				
				if(allowedQuestions != null){
					for(AllowedQuestion allowedQuestion : allowedQuestions){
						AllowedQuestion allowedQuestionCopy =
								this.allowedQuestionDelegate.create(sectionCopy,
										allowedQuestion.getQuestion(),
										allowedQuestion.getValid(),
										allowedQuestion.getSortOrder(),
										allowedQuestion.getQuestionNumber(),
										allowedQuestion
											.getQuestionConditionality(),
										allowedQuestion.getAnswerCardinality(),
										allowedQuestion.getQuestionHelp());
						
						List<AllowedAnswer> allowedAnswers =
								this.allowedAnswerDelegate
								.findAllByAllowedQuestion(allowedQuestion);
						
						if(allowedAnswers != null){
							for(AllowedAnswer allowedAnswer : allowedAnswers){
								this.allowedAnswerDelegate.create(
										allowedQuestionCopy,
										allowedAnswer.getSortOrder(),
										allowedAnswer.getAnswerValue());
							}
						}
					}
				}
			}
		}
		
		return questionnaireTypeCopy;
	}
	
	
	
}
