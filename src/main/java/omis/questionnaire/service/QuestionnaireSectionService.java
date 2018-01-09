package omis.questionnaire.service;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.domain.SectionType;

/**
 * QuestionnaireSectionService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface QuestionnaireSectionService {
	
	/**
	 * Creates a QuestionnaireSection
	 * @param title - String
	 * @param sortOrder - Short
	 * @param sectionNumber - Integer
	 * @param sectionType 
	 * @param sectionHelp - String
	 * @param questionnaireType
	 * @return Newly Created QuestionnaireSection
	 * @throws DuplicateEntityFoundException - When QuestionnaireSection
	 * already exists with given title
	 */
	public QuestionnaireSection create(String title, Short sortOrder, 
			Integer sectionNumber, SectionType sectionType, String sectionHelp,
			QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a QuestionnaireSection
	 * @param questionnaireSection - QuestionnaireSection to update
	 * @param title - String
	 * @param sortOrder - Short
	 * @param sectionNumber - Integer
	 * @param sectionType 
	 * @param sectionHelp - String
	 * @param questionnaireType
	 * @return Updated QuestionnaireSection
	 * @throws DuplicateEntityFoundException - When QuestionnaireSection
	 * already exists with given title
	 */
	public QuestionnaireSection update(QuestionnaireSection questionnaireSection,
			String title, Short sortOrder, 
			Integer sectionNumber, SectionType sectionType, String sectionHelp,
			QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a questionnaireSection
	 * @param questionnaireSection - QuestionnaireSection to remove
	 */
	public void remove(QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of all valid SectionTypes
	 * @return List of all valid SectionTypes
	 */
	public List<SectionType> findAllSectionTypes();
	
	/**
	 * Returns a list of all questionnaireSections by specified 
	 * questionnaireType
	 * @param questionnaireType
	 * @return list of all questionnaireSections by specified 
	 */
	public List<QuestionnaireSection> findAllByQuestionnaireType(
			QuestionnaireType questionnaireType);
	
	/**
	 * Returns a count of all questionnaire sections existing in a 
	 * questionnaireType
	 * @param questionnaireType
	 * @return count of all questionnaire sections existing in a 
	 * questionnaireType
	 */
	public Integer countSectionsByQuestionnaireType(
			QuestionnaireType questionnaireType);
	
}
