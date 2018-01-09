package omis.questionnaire.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.QuestionnaireSectionDao;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.domain.SectionType;

/**
 * QuestionnaireSectionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 17, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireSectionDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Questionnaire Section Already Exists With Given Title"
			+ " And Questionnaire Type";
	
	private final QuestionnaireSectionDao questionnaireSectionDao;
	
	private final InstanceFactory<QuestionnaireSection> 
		questionnaireSectionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for QuestionnaireSectionDelegate
	 * @param questionnaireSectionDao
	 * @param questionnaireSectionInstanceFactory
	 * @param auditComponentRetriever
	 */
	public QuestionnaireSectionDelegate(
			final QuestionnaireSectionDao questionnaireSectionDao,
			final InstanceFactory<QuestionnaireSection> 
				questionnaireSectionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.questionnaireSectionDao = questionnaireSectionDao;
		this.questionnaireSectionInstanceFactory = 
				questionnaireSectionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a QuestionnaireSection
	 * @param title - String
	 * @param sortOrder - Short
	 * @param questionnaireType
	 * @param sectionType
	 * @param sectionNumber - Integer
	 * @param sectionHelp - String
	 * @return QuestionnaireSection - Newly Created QuestionnaireSection
	 * @throws DuplicateEntityFoundException - When QuestionnaireSection
	 * already exists with given title
	 */
	public QuestionnaireSection create(final String title, final Short sortOrder, 
			final QuestionnaireType questionnaireType, 
			final SectionType sectionType, final Integer sectionNumber, 
			final String sectionHelp)
			throws DuplicateEntityFoundException{
		if(this.questionnaireSectionDao.find(title, questionnaireType) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		QuestionnaireSection questionnaireSection = 
				this.questionnaireSectionInstanceFactory.createInstance();
		
		questionnaireSection.setTitle(title);
		questionnaireSection.setSortOrder(sortOrder);
		questionnaireSection.setQuestionnaireType(questionnaireType);
		questionnaireSection.setSectionType(sectionType);
		questionnaireSection.setSectionNumber(sectionNumber);
		questionnaireSection.setSectionHelp(sectionHelp);
		questionnaireSection.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		questionnaireSection.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.questionnaireSectionDao.makePersistent(questionnaireSection);
	}
	
	/**
	 * Updates a QuestionnaireSection
	 * @param questionnaireSection - QuestionnaireSection to update
	 * @param title - String
	 * @param sortOrder - Short
	 * @param questionnaireType
	 * @param sectionType
	 * @param sectionNumber - Integer
	 * @param sectionHelp - String
	 * @return QuestionnaireSection - Updated QuestionnaireSection
	 * @throws DuplicateEntityFoundException - When QuestionnaireSection
	 * already exists with given title
	 */
	public QuestionnaireSection update(
			final QuestionnaireSection questionnaireSection, final String title, 
			final Short sortOrder, final QuestionnaireType questionnaireType, 
			final SectionType sectionType, final Integer sectionNumber, 
			final String sectionHelp)
			throws DuplicateEntityFoundException{
		if(this.questionnaireSectionDao.findExcluding(title, questionnaireType,
				questionnaireSection) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		questionnaireSection.setTitle(title);
		questionnaireSection.setSortOrder(sortOrder);
		questionnaireSection.setQuestionnaireType(questionnaireType);
		questionnaireSection.setSectionType(sectionType);
		questionnaireSection.setSectionNumber(sectionNumber);
		questionnaireSection.setSectionHelp(sectionHelp);
		questionnaireSection.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.questionnaireSectionDao.makePersistent(questionnaireSection);
	}
	
	/**
	 * Removes a QuestionnaireSection
	 * @param questionnaireSection - QuestionnaireSection to Remove
	 */
	public void remove(final QuestionnaireSection questionnaireSection){
		this.questionnaireSectionDao.makeTransient(questionnaireSection);
	}
	
	/**
	 * Returns a list of QuestionnaireSections by specified QuestionnaireType
	 * @param questionnaireType
	 * @return List of QuestionnaireSections by specified QuestionnaireType
	 */
	public List<QuestionnaireSection> findByQuestionnaireType(
			QuestionnaireType questionnaireType){
		return this.questionnaireSectionDao
				.findByQuestionnaireType(questionnaireType);
	}
	
}
