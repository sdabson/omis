package omis.questionnaire.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.questionnaire.domain.AdministeredQuestionValue;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionNote;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.user.domain.UserAccount;

/**
 * QuestionnaireService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 9, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionnaireService {
	
	/**
	 * Finds and returns a list of all questionnaireTypes by specified
	 * effective date
	 * @param effectiveDate - Date
	 * @return List of QuestionnaireTypes
	 */
	public List<QuestionnaireType> getAllQuestionnaireTypes(Date effectiveDate);
	
	/**
	 * Creates a new AdministeredQuestionnaire
	 * @param answerer - Person
	 * @param draft - Boolean
	 * @param comments - String
	 * @param assessor - Person
	 * @param date - Date
	 * @param questionnaireType - QuestionnaireType
	 * @return newly created AdministeredQuestionnaire
	 * @throws DuplicateEntityFoundException - when an administered
	 * questionnaire already exists for given answerer
	 */
	public AdministeredQuestionnaire createAdministeredQuestionnaire(
			Person answerer, Boolean draft, String comments, Person assessor,
			Date date, QuestionnaireType questionnaireType) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an administered questionnaire
	 * @param administeredQuestionnaire - administered questionnaire to update
	 * @param draft - Boolean
	 * @param comments - String
	 * @param assessor - Person
	 * @param date - Date
	 * @param questionnaireType - QuestionnaireType
	 * @return updated AdministeredQuestionnaire
	 * @throws DuplicateEntityFoundException - when a separate administered 
	 * questionnaire already exists for that answerer
	 */
	public AdministeredQuestionnaire editAdministeredQuestionnaire(
			AdministeredQuestionnaire administeredQuestionnaire,
			Person answerer, Boolean draft, String comments, Person assessor,
			Date date, QuestionnaireType questionnaireType)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an administered questionnaire
	 * @param administeredQuestionnaire - administered questionnaire to remove
	 */
	public void removeAdministeredQuestionnaire(
			AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Creates a new AdministeredQuestionValue
	 * @param administeredQuestionnaire
	 * @param question
	 * @param questionnaireSection
	 * @param answerValue
	 * @param answerValueText - String
	 * @param comments - String
	 * @return Newly created Administered
	 * @throws DuplicateEntityFoundException - when administeredQuestionValue
	 * already exists with all given properties
	 */
	public AdministeredQuestionValue createAdministeredQuestionValue(
			AdministeredQuestionnaire administeredQuestionnaire,
			Question question, QuestionnaireSection questionnaireSection,
			AnswerValue answerValue, String answerValueText, String comments)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing administeredQuestionValue
	 * @param administeredQuestionValue - administeredQuestionValue to update
	 * @param administeredQuestionnaire
	 * @param question
	 * @param questionnaireSection
	 * @param answerValue
	 * @param answerValueText - String
	 * @param comments - String
	 * @return Updated AdministeredQuestionValue
	 * @throws DuplicateEntityFoundException - when administeredQuestionValue
	 * already exists with all given properties 
	 */
	public AdministeredQuestionValue editAdministeredQuestionValue(
			AdministeredQuestionValue administeredQuestionValue,
			AdministeredQuestionnaire administeredQuestionnaire,
			Question question, QuestionnaireSection questionnaireSection,
			AnswerValue answerValue, String answerValueText, String comments)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an AdministeredQuestionValue
	 * @param administeredQuestionValue - AdministeredQuestionValue to remove
	 */
	public void removeAdministeredQuestionValue(
			AdministeredQuestionValue administeredQuestionValue);
	
	/**
	 * Creates a new AdministeredQuestionnaireSectionNote
	 * @param administeredQuestionnaire
	 * @param questionnaireSection
	 * @param comments - String
	 * @return AdministeredQuestionnaireSectionNote
	 * @throws DuplicateEntityFoundException - when 
	 * AdministeredQuestionnaireSectionNote already exists with given properties
	 */
	public AdministeredQuestionnaireSectionNote 
		createAdministeredQuestionnaireSectionNote(
				AdministeredQuestionnaire administeredQuestionnaire,
				QuestionnaireSection questionnaireSection,
				String comments)
						throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing AdministeredQuestionnaireSectionNote
	 * @param administeredQuestionnaireSectionNote -
	 * AdministeredQuestionnaireSectionNote to update
	 * @param questionnaireSection
	 * @param comments - String
	 * @return AdministeredQuestionnaireSectionNote
	 * @throws DuplicateEntityFoundException - when
	 * AdministeredQuestionnaireSectionNote already exists with given properties
	 * for its administeredQuestionnaire
	 * 
	 */
	public AdministeredQuestionnaireSectionNote 
		updateAdministeredQuestionnaireSectionNote(
			AdministeredQuestionnaireSectionNote 
				administeredQuestionnaireSectionNote,
			QuestionnaireSection questionnaireSection, String comments)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an AdministeredQuestionnaireSectionNote
	 * @param administeredQuestionnaireSectionNote
	 */
	public void removeAdministeredQuestionnaireSectionNote(
			AdministeredQuestionnaireSectionNote 
				administeredQuestionnaireSectionNote);
	
	/**
	 * Returns an AdministeredQuestionValue by specified question
	 * @param question
	 * @param administeredQuestionnaire
	 * @return AdministeredQuestionValue
	 */
	public AdministeredQuestionValue 
		findAdministeredQuestionValueByQuestionAndQuestionnaire(
			Question question, 
			AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Returns a list of AdministeredQuestionValues by specified question
	 * @param question
	 * @param administeredQuestionnaire
	 * @return List of AdministeredQuestionValue
	 */
	public List<AdministeredQuestionValue> 
		findAdministeredQuestionValuesByQuestionAndQuestionnaire(
				Question question, AdministeredQuestionnaire
					administeredQuestionnaire);
	
	/**
	 * Returns an AdministeredQuestionnaireSectionNote found by 
	 * AdministeredQuestionnaire and QuestionnaireSection
	 * @param administeredQuestionnaire
	 * @param questionnaireSection
	 * @return AdministeredQuestionnaireSectionNote
	 */
	public AdministeredQuestionnaireSectionNote 
		findAdministeredQuestionnaireSectionNoteByQuestionnaireAndSection(
				AdministeredQuestionnaire administeredQuestionnaire,
				QuestionnaireSection questionnaireSection);
	
	/**
	 * Creates an AdministeredQuestionnaireSectionStatus
	 * @param questionnaireSection
	 * @param administeredQuestionnaire
	 * @param draft - Boolean
	 * @param date
	 * @return created AdministeredQuestionnaireSectionStatus
	 */
	public AdministeredQuestionnaireSectionStatus 
		createAdministeredQuestionnaireSectionStatus(
				QuestionnaireSection questionnaireSection, 
				AdministeredQuestionnaire administeredQuestionnaire, 
				Boolean draft, Date date)throws DuplicateEntityFoundException;
	
	/**
	 * Updates an AdministeredQuestionnaireSectionStatus
	 * @param administeredQuestionnaireSectionStatus -
	 * 	AdministeredQuestionnaireSectionStatus to update
	 * @param draft
	 * @param date
	 * @return updated AdministeredQuestionnaireSectionStatus
	 */
	public AdministeredQuestionnaireSectionStatus 
		updateAdministeredQuestionnaireSectionStatus(
			AdministeredQuestionnaireSectionStatus 
				administeredQuestionnaireSectionStatus, 
			Boolean draft, Date date)throws DuplicateEntityFoundException;
	
	/**
	 * Removes a AdministeredQuestionnaireSectionStatus
	 * @param administeredQuestionnaireSectionStatus - 
	 * 	AdministeredQuestionnaireSectionStatus to remove
	 */
	public void removeAdministeredQuestionnaireSectionStatus(
			AdministeredQuestionnaireSectionStatus 
			administeredQuestionnaireSectionStatus);
	
	/**
	 * Returns a list of all allowed questions by specified question section
	 * @param questionSection
	 * @return List<AllowedQuestion> by QuestionSection
	 */
	public List<AllowedQuestion> findAllowedQuestionsByQuestionSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns a list of AdministeredQuestionnaireSectionStatuses found by
	 * specified AdministeredQuestionnaire
	 * @param administeredQuestionnaire
	 * @return list of AdministeredQuestionnaireSectionStatuses found by
	 * specified AdministeredQuestionnaire
	 */
	public List<AdministeredQuestionnaireSectionStatus> 
		findAdministeredQuestionnaireSectionStatusByAdministeredQuestionnaire(
				AdministeredQuestionnaire administeredQuestionnaire);
	
	/**
	 * Returns a list of user accounts by given query
	 * 
	 * @param query - query
	 * @return list of user accounts by given query
	 */
	public List<UserAccount> findUserAccounts(String query);
	
	
	/**
	 * Returns a list of QuestionnaireSections by specified QuestionnaireType
	 * @param questionnaireType
	 * @return List of QuestionnaireSections by specified QuestionnaireType
	 */
	public List<QuestionnaireSection> 
		findQuestionnaireSectionsByQuestionnaireType(
				QuestionnaireType questionnaireType);
	
}
