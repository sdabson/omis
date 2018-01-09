package omis.questionnaire.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.person.domain.Person;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * AdministeredQuestionnaireDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireDao 
	extends GenericDao<AdministeredQuestionnaire> {
	
	/**
	 * Returns an AdministeredQuestionnaire by specified answerer
	 * @param answerer - Person
	 * @param date - Date
	 * @param questionnaireType - QuestionnaireType
	 * @return AdministeredQuestionnaire
	 */
	AdministeredQuestionnaire find(Person answerer, Date date, 
			QuestionnaireType questionnaireType);
	
	/**
	 * Returns an AdministeredQuestionnaire by specified answerer, excluding
	 * specified AdministeredQuestionnaire
	 * @param answerer - Person
	 * @param date - Date
	 * @param questionnaireType - QuestionnaireType
	 * @param excludedAdministeredQuestionnaire - AdministeredQuestionnaire to
	 * exclude
	 * @return AdministeredQuestionnaire
	 */
	AdministeredQuestionnaire findExcluding(Person answerer, Date date, 
			QuestionnaireType questionnaireType,
			AdministeredQuestionnaire excludedAdministeredQuestionnaire);
	
	
}
