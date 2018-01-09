package omis.questionnaire.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionStatusDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireSectionStatusDao 
	extends GenericDao<AdministeredQuestionnaireSectionStatus> {
	
	/**
	 * Returns an AdministeredQuestionnaireSectionStatus with specified 
	 * properties
	 * @param questionnaireSection
	 * @param administeredQuestionnaire
	 * @param draft - Boolean
	 * @return AdministeredQuestionnaireSectionStatus with specified properties
	 */
	AdministeredQuestionnaireSectionStatus find(
			QuestionnaireSection questionnaireSection,
			AdministeredQuestionnaire administeredQuestionnaire, Boolean draft);
	
	/**
	 * Returns an AdministeredQuestionnaireSectionStatus with specified 
	 * properties excluding specified AdministeredQuestionnaireSectionStatus
	 * @param questionnaireSection
	 * @param administeredQuestionnaire
	 * @param draft - Boolean
	 * @param excludedStatus - AdministeredQuestionnaireSectionStatus to exclude
	 * @return AdministeredQuestionnaireSectionStatus with specified 
	 * properties excluding specified AdministeredQuestionnaireSectionStatus
	 */
	AdministeredQuestionnaireSectionStatus findExcluding(
			QuestionnaireSection questionnaireSection,
			AdministeredQuestionnaire administeredQuestionnaire, Boolean draft,
			AdministeredQuestionnaireSectionStatus excludedStatus);
	
	/**
	 * Returns a list of AdministeredQuestionnaireSectionStatuses found by
	 * specified AdministeredQuestionnaire
	 * @param administeredQuestionnaire
	 * @return list of AdministeredQuestionnaireSectionStatuses found by
	 * specified AdministeredQuestionnaire
	 */
	List<AdministeredQuestionnaireSectionStatus> findByAdministeredQuestionnaire
			(AdministeredQuestionnaire administeredQuestionnaire);
}
