package omis.questionnaire.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.QuestionnaireCategoryDao;
import omis.questionnaire.domain.QuestionnaireCategory;

/**
 * QuestionnaireCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 15, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireCategoryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Questionnaire Category Already Exists With Given Description";
	
	private final QuestionnaireCategoryDao questionnaireCategoryDao;
	
	private final InstanceFactory<QuestionnaireCategory> 
		questionnaireCategoryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for QuestionnaireCategoryDelegate
	 * @param questionnaireCategoryDao
	 * @param questionnaireCategoryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public QuestionnaireCategoryDelegate(
			final QuestionnaireCategoryDao questionnaireCategoryDao,
			final InstanceFactory<QuestionnaireCategory> 
				questionnaireCategoryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.questionnaireCategoryDao = questionnaireCategoryDao;
		this.questionnaireCategoryInstanceFactory
			= questionnaireCategoryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates QuestionnaireCategory
	 * @param description - String
	 * @param valid - Boolean
	 * @param source - String
	 * @return QuestionnaireCategory - Newly Created QuestionnaireCategory
	 * @throws DuplicateEntityFoundException - When QuestionnaireCategory
	 * already exists with given Description
	 */
	public QuestionnaireCategory create(final String description,
			final Boolean valid, final String source)
			throws DuplicateEntityFoundException{
		if(this.questionnaireCategoryDao.find(description) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		QuestionnaireCategory questionnaireCategory = 
				this.questionnaireCategoryInstanceFactory.createInstance();
		
		questionnaireCategory.setDescription(description);
		questionnaireCategory.setValid(valid);
		questionnaireCategory.setSource(source);
		questionnaireCategory.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		questionnaireCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.questionnaireCategoryDao
				.makePersistent(questionnaireCategory);
	}
	
	/**
	 * Updates a QuestionnaireCategory
	 * @param questionnaireCategory - QuestionnaireCategory to update
	 * @param description - String
	 * @param valid - Boolean
	 * @param source - String
	 * @return QuestionnaireCategory - Updated QuestionnaireCategory
	 * @throws DuplicateEntityFoundException - When QuestionnaireCategory
	 * already exists with given Description
	 */
	public QuestionnaireCategory update(
			final QuestionnaireCategory questionnaireCategory,
			final String description, final Boolean valid, final String source)
			throws DuplicateEntityFoundException{
		if(this.questionnaireCategoryDao
				.findExcluding(description, questionnaireCategory) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		questionnaireCategory.setDescription(description);
		questionnaireCategory.setValid(valid);
		questionnaireCategory.setSource(source);
		questionnaireCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.questionnaireCategoryDao
				.makePersistent(questionnaireCategory);
	}
	
	/**
	 * Removes a QuestionnaireCategory
	 * @param questionnaireCategory - QuestionnaireCategory to remove
	 */
	public void remove(final QuestionnaireCategory questionnaireCategory){
		this.questionnaireCategoryDao.makeTransient(questionnaireCategory);
	}
	
	/**
	 * Returns a list of all valid QuestionnaireCategories
	 * @return List of all valid QuestionnaireCategories
	 */
	public List<QuestionnaireCategory> findAll(){
		return this.questionnaireCategoryDao.findAll();
	}
	
	
}
