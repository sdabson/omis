package omis.questionnaire.dao;

import omis.dao.GenericDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionNote;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionNoteDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireSectionNoteDao 
	extends GenericDao<AdministeredQuestionnaireSectionNote> {
	
	/**
	 * Returns an AdministeredQuestionnaireSectionNote with specified properties
	 * @param questionnaireSection
	 * @param comments - String
	 * @param administeredQuestionnaire
	 * @return AdministeredQuestionnaireSectionNote
	 */
	AdministeredQuestionnaireSectionNote find(
			QuestionnaireSection questionnaireSection, String comments, 
			AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Returns an AdministeredQuestionnaireSectionNote with specified properties
	 * excluding specified AdministeredQuestionnaireSectionNote
	 * @param questionnaireSection
	 * @param comments - String
	 * @param administeredQuestionnaire
	 * @param excludedAdministeredQuestionnaireSectionNote -
	 * AdministeredQuestionnaireSectionNote to exclude
	 * @return AdministeredQuestionnaireSectionNote
	 */
	AdministeredQuestionnaireSectionNote findExcluding(
			QuestionnaireSection questionnaireSection, String comments, 
			AdministeredQuestionnaire administeredQuestionnaire,
			AdministeredQuestionnaireSectionNote 
				excludedAdministeredQuestionnaireSectionNote);
	
	/**
	 * Returns AdministeredQuestionnaireSectionNote by specified 
	 * administeredQuestionnaire and questionnaireSection
	 * @param administeredQuestionnaire
	 * @param questionnaireSection
	 * @return AdministeredQuestionnaireSectionNote
	 */
	AdministeredQuestionnaireSectionNote findByQuestionnaireAndSection(
			AdministeredQuestionnaire administeredQuestionnaire,
			QuestionnaireSection questionnaireSection);
	
}
