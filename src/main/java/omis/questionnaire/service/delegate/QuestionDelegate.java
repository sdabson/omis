package omis.questionnaire.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.QuestionDao;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;

/**
 * QuestionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 20, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG = 
			"Question Already Exists For Given Category and With Given Text";
	
	private final QuestionDao questionDao;
	
	private final InstanceFactory<Question> questionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * @param questionDao
	 * @param questionInstanceFactory
	 * @param auditComponentRetriever
	 */
	public QuestionDelegate(final QuestionDao questionDao, 
			final InstanceFactory<Question> questionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.questionDao = questionDao;
		this.questionInstanceFactory = questionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Question
	 * @param questionCategory
	 * @param text - String
	 * @param statik - Boolean
	 * @param valid - Boolean
	 * @return Question - Newly Created Question
	 * @throws DuplicateEntityFoundException - When Question already exists
	 * with given Text
	 */
	public Question create(final QuestionCategory questionCategory, 
			final String text, final Boolean statik, final Boolean valid) 
					throws DuplicateEntityFoundException{
		if(this.questionDao.find(questionCategory, text) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		Question question = this.questionInstanceFactory.createInstance();
		
		question.setQuestionCategory(questionCategory);
		question.setText(text);
		question.setStatic(statik);
		question.setValid(valid);
		question.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		question.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		
		return this.questionDao.makePersistent(question);
	}
	
	/**
	 * Updates a Question
	 * @param question - Question to remove
	 * @param questionCategory
	 * @param text - String
	 * @param statik - Boolean
	 * @param valid - Boolean
	 * @return Question - Updated Question
	 * @throws DuplicateEntityFoundException - When Question already exists
	 * with given Text
	 */
	public Question update(final Question question, 
			final QuestionCategory questionCategory, final String text, 
			final Boolean statik, final Boolean valid)
					throws DuplicateEntityFoundException{
		if(this.questionDao.findExcluding(questionCategory, text, question) 
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		question.setQuestionCategory(questionCategory);
		question.setText(text);
		question.setStatic(statik);
		question.setValid(valid);
		question.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		
		return this.questionDao.makePersistent(question);
	}
	
	/**
	 * Removes a Question
	 * @param question - Question to remove
	 */
	public void remove(final Question question){
		this.questionDao.makeTransient(question);
	}
	
	/**
	 * Returns a list of Questions by question text
	 * @param text - String
	 * @return List of questions by specified description
	 */
	public List<Question> findQuestionsByText(final String text){
		return this.questionDao.findByText(text);
	}
}
