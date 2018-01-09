package omis.questionnaire.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireTypeDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireTypeDao extends GenericDao<QuestionnaireType> {
	
	/**
	 * Returns a QuestionnaireType with specified title
	 * @param title - String
	 * @param cycle - Integer
	 * @return QuestionnaireType
	 */
	QuestionnaireType find(String title, Integer cycle);
	
	
	/**
	 * Returns a QuestionnaireType with specified title, excluding specified
	 * QuestionnaireType
	 * @param title - String
	 * @param cycle - Integer
	 * @param excludedQuestionnaireType - QuestionnaireType to exclude
	 * @return QuestionnaireType
	 */
	QuestionnaireType findExcluding(String title, Integer cycle,
			QuestionnaireType excludedQuestionnaireType);
	
	/**
	 * Returns a list of QuestionnaireTypes by specified effective date
	 * @param effectiveDate - Date
	 * @return List of QuestionnaireTypes
	 */
	List<QuestionnaireType> findByDate(Date effectiveDate);
	
	/**
	 * Returns a list of QuestionnaireTypes by specified QuestionnaireCategory
	 * @param category - QuestionnaireCategory
	 * @return List of QuestionnaireTypes by specified QuestionnaireCategory
	 */
	List<QuestionnaireType> findByCategory(QuestionnaireCategory category); 
	
	/**
	 * Returns a list of QuestionnaireTypes by specified QuestionnaireCategory
	 * and effective date
	 * @param category - QuestionnaireCategory
	 * @param effectiveDate - Date
	 * @return List of QuestionnaireTypes by specified QuestionnaireCategory
	 * and effective date
	 */
	List<QuestionnaireType> findByCategoryAndDate(
			QuestionnaireCategory category, Date effectiveDate); 
	
	/**
	 * Returns the next cycle number of questionnaire types that share the
	 * same title
	 * @param questionnaireType - QuestionnaireType with title to search by
	 * @return Integer - next cycle number of questionnaire types that share the
	 * same title
	 */
	Integer findNextCycleByQuestionnaireType(QuestionnaireType
			questionnaireType);
}
