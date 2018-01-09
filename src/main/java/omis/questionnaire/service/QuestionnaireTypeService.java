package omis.questionnaire.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireTypeService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireTypeService {
	
	/**
	 * Creates a QuestionnaireType
	 * @param title - String
	 * @param shortTitle - String
	 * @param cycle - Integer
	 * @param valid - Boolean
	 * @param startDate - Date
	 * @param endDate - Date
	 * @param questionnaireHelp - String
	 * @param questionnaireCategory
	 * @return Newly Created QuestionnaireType
	 * @throws DuplicateEntityFoundException - When QuestionnaireType already
	 * exists with given Title 
	 */
	public QuestionnaireType create(String title, String shortTitle,
			Integer cycle, Boolean valid, Date startDate, Date endDate,
			String questionnaireHelp,
			QuestionnaireCategory questionnaireCategory)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a QuestionnaireType
	 * @param questionnaireType - QuestionnaireType to update
	 * @param title - String
	 * @param shortTitle - String
	 * @param cycle - Integer
	 * @param valid - Boolean
	 * @param startDate - Date
	 * @param endDate - Date
	 * @param questionnaireHelp - String
	 * @param questionnaireCategory
	 * @return Updated QuestionnaireType
	 * @throws DuplicateEntityFoundException - When QuestionnaireType already
	 * exists with given Title 
	 */
	public QuestionnaireType update(QuestionnaireType questionnaireType,
			String title, String shortTitle, Integer cycle, Boolean valid,
			Date startDate, Date endDate, String questionnaireHelp,
			QuestionnaireCategory questionnaireCategory)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a questionnaireType
	 * @param questionnaireType - QuestionnaireType to remove
	 */
	public void remove(QuestionnaireType questionnaireType);
	
	/**
	 * Creates a copy of a questionnaireType with a cycle + 1
	 * @param questionnaireType - QuestionnaireType to copy
	 * @return QuestionnaireType - copy of a questionnaireType with a cycle + 1
	 * @throws DuplicateEntityFoundException- When Questionnaire Type Already
	 * Exists With Given Title and Cycle
	 */
	public QuestionnaireType copy(QuestionnaireType questionnaireType)
			throws DuplicateEntityFoundException;
	
	/**
	 * Returns a list of all QuestionnaireCategories
	 * @return List of all QuestionnaireCategories
	 */
	public List<QuestionnaireCategory> findAllQuestionnaireCategories(); 
	
}
