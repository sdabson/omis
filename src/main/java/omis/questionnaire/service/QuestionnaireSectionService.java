package omis.questionnaire.service;

import java.util.List;
import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.domain.SectionType;

/**
 * Questionnaire Section Service.
 * 
 *@author Annie Wahl
 *@version 0.1.1 (Oct 4, 2018)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireSectionService {
	
	/**
	 * Creates a Questionnaire Section.
	 * @param title - String
	 * @param sortOrder - Short
	 * @param sectionNumber - String
	 * @param sectionType 
	 * @param sectionHelp - String
	 * @param questionnaireType - questionnaire type
	 * @return Newly Created QuestionnaireSection
	 * @throws DuplicateEntityFoundException - When QuestionnaireSection
	 * already exists with given title
	 */
	QuestionnaireSection create(String title, Short sortOrder, 
			String sectionNumber, SectionType sectionType, String sectionHelp,
			QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a Questionnaire Section.
	 * @param questionnaireSection - QuestionnaireSection to update
	 * @param title - String
	 * @param sortOrder - Short
	 * @param sectionNumber - String
	 * @param sectionType 
	 * @param sectionHelp - String
	 * @param questionnaireType - Questionnaire Type
	 * @return Updated QuestionnaireSection
	 * @throws DuplicateEntityFoundException - When QuestionnaireSection
	 * already exists with given title
	 */
	QuestionnaireSection update(QuestionnaireSection questionnaireSection,
			String title, Short sortOrder, 
			String sectionNumber, SectionType sectionType, String sectionHelp,
			QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a questionnaire section.
	 * @param questionnaireSection - QuestionnaireSection to remove
	 */
	void remove(QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of all valid Section Types.
	 * @return List of all valid SectionTypes
	 */
	List<SectionType> findAllSectionTypes();
	
	/**
	 * Returns a list of all questionnaire sections by specified 
	 * questionnaire type.
	 * @param questionnaireType - questionnaire type
	 * @return list of all questionnaireSections by specified 
	 */
	List<QuestionnaireSection> findAllByQuestionnaireType(
			QuestionnaireType questionnaireType);
	
	/**
	 * Returns a count of all questionnaire sections existing in a 
	 * questionnaire type.
	 * @param questionnaireType - questionnaire type
	 * @return count of all questionnaire sections existing in a 
	 * questionnaireType
	 */
	Integer countSectionsByQuestionnaireType(
			QuestionnaireType questionnaireType);
	
}
