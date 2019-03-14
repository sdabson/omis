package omis.questionnaire.dao.impl.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.AdministeredQuestionValueDao;
import omis.questionnaire.domain.AdministeredQuestionValue;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * Administered Question Value Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.2 (Mar 12, 2019)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionValueDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AdministeredQuestionValue>
		implements AdministeredQuestionValueDao {
	
	/* Query Names */
	
	private static final String FIND_ADMINISTERED_QUESTION_VALUE_QUERY_NAME = 
			"findAdministeredQuestionValue";
	
	private static final String 
		FIND_ADMINISTERED_QUESTION_VALUE_EXCLUDING_QUERY_NAME = 
			"findAdministeredQuestionValueExcluding"; 
	
	private static final String 
		FIND_BY_QUESTION_AND_QUESTIONNAIRE_QUERY_NAME =
		"findAdministeredQuestionValueByQuestionAndQuestionnaireSectionStatus";
	
	private static final String FIND_WITH_NO_ANSWER_VALUE_QUERY_NAME = 
			"findAdministeredQuestionValueWithNoAnswerValue";
	
	/* Parameter Names */
	
	private static final String QUESTION_PARAM_NAME = "question";
	
	private static final String ANSWER_VALUE_PARAM_NAME = "answerValue";
	
	private static final String ANSWER_VALUE_TEXT_PARAM_NAME =
			"answerValueText";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_PARAM_NAME =
			"administeredQuestionnaire";
	
	private static final String QUESTIONNAIRE_SECTION_PARAM_NAME = 
			"questionnaireSection";
	
	private static final String ADMINISTERED_QUESTION_VALUE_PARAM_NAME = 
			"administeredQuestionValue";
	
	private static final String ANSWER_VALUE_PROPERTY_NAME = "answerValue";
	
	private static final String ANSWER_VALUE_TEXT_PROPERTY_NAME =
			"answerValueText";
	
	private static final String
				ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_PARAM_NAME =
					"administeredQuestionnaireSectionStatus";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String entity name
	 */
	public AdministeredQuestionValueDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionValue find(final Question question, 
			final AnswerValue answerValue, final String answerValueText,
			final AdministeredQuestionnaire administeredQuestionnaire, 
			final QuestionnaireSection questionnaireSection) {
		AdministeredQuestionValue administeredQuestionValue = 
				(AdministeredQuestionValue) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ADMINISTERED_QUESTION_VALUE_QUERY_NAME)
				.setParameter(QUESTION_PARAM_NAME, question)
				.setParameter(ANSWER_VALUE_PARAM_NAME, answerValue,
					this.getEntityPropertyType(ANSWER_VALUE_PROPERTY_NAME))
				.setParameter(ANSWER_VALUE_TEXT_PARAM_NAME, answerValueText,
					this.getEntityPropertyType(ANSWER_VALUE_TEXT_PROPERTY_NAME))
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						administeredQuestionnaire)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						questionnaireSection)
				.uniqueResult();
		
		return administeredQuestionValue;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionValue findExcluding(final Question question, 
			final AnswerValue answerValue, final String answerValueText,
			final AdministeredQuestionnaire administeredQuestionnaire, 
			final QuestionnaireSection questionnaireSection,
			final AdministeredQuestionValue excludedAdministeredQuestionValue) {
		AdministeredQuestionValue administeredQuestionValue = 
				(AdministeredQuestionValue) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_ADMINISTERED_QUESTION_VALUE_EXCLUDING_QUERY_NAME)
				.setParameter(QUESTION_PARAM_NAME, question)
				.setParameter(ANSWER_VALUE_PARAM_NAME, answerValue,
					this.getEntityPropertyType(ANSWER_VALUE_PROPERTY_NAME))
				.setParameter(ANSWER_VALUE_TEXT_PARAM_NAME, answerValueText,
					this.getEntityPropertyType(ANSWER_VALUE_TEXT_PROPERTY_NAME))
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						administeredQuestionnaire)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						questionnaireSection)
				.setParameter(ADMINISTERED_QUESTION_VALUE_PARAM_NAME, 
						excludedAdministeredQuestionValue)
				.uniqueResult();
		
		return administeredQuestionValue;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionValue
		findByQuestionAndAdministeredQuestionnaireSectionStatus(
			final Question question, 
			final AdministeredQuestionnaireSectionStatus
					administeredQuestionnaireSectionStatus) {
		AdministeredQuestionValue administeredQuestionValue = 
				(AdministeredQuestionValue) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_BY_QUESTION_AND_QUESTIONNAIRE_QUERY_NAME)
				.setParameter(QUESTION_PARAM_NAME, question)
				.setParameter(
						ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_PARAM_NAME, 
						administeredQuestionnaireSectionStatus)
				.uniqueResult();
		
		return administeredQuestionValue;
	}

	/**{@inheritDoc} */
	@Override
	public List<AdministeredQuestionValue> 
		findAllByQuestionAndAdministeredQuestionnaireSectionStatus(
				final Question question, 
				final AdministeredQuestionnaireSectionStatus
					administeredQuestionnaireSectionStatus) {
		@SuppressWarnings("unchecked")
		List<AdministeredQuestionValue> administeredQuestionValues = 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_BY_QUESTION_AND_QUESTIONNAIRE_QUERY_NAME)
				.setParameter(QUESTION_PARAM_NAME, question)
				.setParameter(
						ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_PARAM_NAME,
						administeredQuestionnaireSectionStatus)
				.list();
		
		return administeredQuestionValues;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionValue findByNoAnswerValue(
			final Question question,
			final AdministeredQuestionnaire administeredQuestionnaire,
			final QuestionnaireSection questionnaireSection) {
		AdministeredQuestionValue administeredQuestionValue = 
				(AdministeredQuestionValue) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WITH_NO_ANSWER_VALUE_QUERY_NAME)
				.setParameter(QUESTION_PARAM_NAME, question)
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME,
						administeredQuestionnaire)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME,
						questionnaireSection)
				.uniqueResult();
		
		return administeredQuestionValue;
	}

}
