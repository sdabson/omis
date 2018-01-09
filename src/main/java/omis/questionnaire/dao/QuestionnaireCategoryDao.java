package omis.questionnaire.dao;


import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.QuestionnaireCategory;

/**
 * QuestionnaireCategoryDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireCategoryDao 
	extends GenericDao<QuestionnaireCategory> {
	
	/**
	 * Returns a QuestionnaireCategory with specified description
	 * @param description - String
	 * @return QuestionnaireCategory
	 */
	QuestionnaireCategory find(String description);
	
	/**
	 * Returns a QuestionnaireCategory with specified description excluding
	 * specifed QuestionnaireCategory
	 * @param description - String
	 * @param excludedQuestionnaireCategory - QuestionnaireCategory to exclude
	 * @return QuestionnaireCategory
	 */
	QuestionnaireCategory findExcluding(String description, 
			QuestionnaireCategory excludedQuestionnaireCategory);
	
	/**
	 * Returns a list of all valid Questionnaire Categories
	 * @return List of valid QuestionnaireCategories */
	List<QuestionnaireCategory> findAll();
	
}
