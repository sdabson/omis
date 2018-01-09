package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireSectionDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireSectionDao 
	extends GenericDao<QuestionnaireSection> {
	
	/**
	 * Returns a QuestionnaireSection by specified title
	 * @param title - String
	 * @return QuestionnaireSection
	 */
	QuestionnaireSection find(String title, QuestionnaireType questionnaireType);
	
	/**
	 * Returns a QuestionnaireSection by specified title excluding specified
	 * QuestionnaireSection
	 * @param title - String
	 * @param excludedQuestionnaireSection - QuestionnaireSection to exclude
	 * @return QuestionnaireSection
	 */
	QuestionnaireSection findExcluding(String title,
			QuestionnaireType questionnaireType,
			QuestionnaireSection excludedQuestionnaireSection);
	
	/**
	 * Returns a list of QuestionnaireSections by specified QuestionnaireType
	 * @param questionnaireType
	 * @return List of QuestionnaireSections by specified QuestionnaireType
	 */
	List<QuestionnaireSection> findByQuestionnaireType(
			QuestionnaireType questionnaireType);
	
}
