package omis.questionnaire.report.impl.hibernate;


import java.util.List;

import org.hibernate.SessionFactory;

import omis.person.domain.Person;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.QuestionnaireSection;
import omis.questionnaire.domain.QuestionnaireType;
import omis.questionnaire.report.AdministeredQuestionnaireSummary;
import omis.questionnaire.report.AnswerValueSummary;
import omis.questionnaire.report.QuestionSummary;
import omis.questionnaire.report.AdministeredQuestionnaireReportService;
import omis.questionnaire.report.AdministeredQuestionnaireSectionSummary;

/**
 * QuestionnaireReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireReportServiceHibernateImpl
	implements AdministeredQuestionnaireReportService {
	
	/* Query Names */
	
	private static final String 
		FIND_QUESTIONNAIRE_SUMMARIES_BY_ANSWERER_QUERY_NAME =
			"findAdministeredQuestionnaireSummariesByAnswerer";
	
	private static final String 
	FIND_QUESTIONNAIRE_SUMMARIES_BY_ANSWERER_AND_QUESTIONNAIRE_TYPE_QUERY_NAME =
		"findAdministeredQuestionnaireSummariesByAnswererAndQuestionnaireType";
	
	private static final String QUESTIONNAIRE_SUMMARIZE_QUERY_NAME =
			"administeredQuestionnaireSummarize";
	
	private static final String QUESTION_SUMMARIZE_QUERY_NAME =
			"questionSummarize";
	
	private static final String 
		FIND_QUESTIONNAIRE_SECTION_SUMMARIES_BY_QUESTIONNAIRE_QUERY_NAME =
			"findAdministeredQuestionnaireSectionSummariesByQuestionnaire";
	
	private static final String 
		FIND_QUESTION_SUMMARIES_BY_QUESTIONNAIRE_SECTION_QUERY_NAME =
			"findQuestionSummariesByQuestionnaireSection";
	
	private static final String 
		FIND_ANSWER_VALUE_SUMMARIES_BY_ALLOWED_QUESTION_QUERY_NAME =
			"findAnswerValueSummariesByAllowedQuestion";
	
	/* Parameter Names */
	
	private static final String ANSWERER_PARAM_NAME = "answerer";
	
	private static final String QUESTIONNAIRE_TYPE_PARAM_NAME =
			"questionnaireType";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_PARAM_NAME =
			"administeredQuestionnaire";
	
	private static final String QUESTIONNAIRE_SECTION_PARAM_NAME =
			"questionnaireSection";
	
	private static final String ALLOWED_QUESTION_PARAM_NAME = "allowedQuestion";
	
	/* Members */
	
	private final SessionFactory sessionFactory;
	
	/* Constructor */
	
	/**
	 * Constructor for QuestionnaireReportServiceHibernateImpl
	 * @param sessionFactory
	 */
	public AdministeredQuestionnaireReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Report Service Methods */
	
	/**{@inheritDoc} */
	@Override
	public List<AdministeredQuestionnaireSummary> 
		findQuestionnairesByAnswerer(final Person answerer) {
		@SuppressWarnings("unchecked")
		List<AdministeredQuestionnaireSummary> 
			administeredQuestionnaireSummaries
			= this.sessionFactory.getCurrentSession()
			.getNamedQuery(FIND_QUESTIONNAIRE_SUMMARIES_BY_ANSWERER_QUERY_NAME)
			.setParameter(ANSWERER_PARAM_NAME, answerer)
			.list();
		
		
		return administeredQuestionnaireSummaries;
	}
	
	/**{@inheritDoc} */
	public List<AdministeredQuestionnaireSummary> 
	findQuestionnairesByAnswererAndQuestionnaireType(
		final Person answerer, final QuestionnaireType questionnaireType){
		@SuppressWarnings("unchecked")
		List<AdministeredQuestionnaireSummary> 
			administeredQuestionnaireSummaries
			= this.sessionFactory.getCurrentSession()
			.getNamedQuery(
	FIND_QUESTIONNAIRE_SUMMARIES_BY_ANSWERER_AND_QUESTIONNAIRE_TYPE_QUERY_NAME)
			.setParameter(ANSWERER_PARAM_NAME, answerer)
			.setParameter(QUESTIONNAIRE_TYPE_PARAM_NAME, questionnaireType)
			.list();
		
		
		return administeredQuestionnaireSummaries;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaireSummary summarize(
			final AdministeredQuestionnaire administeredQuestionnaire) {
		AdministeredQuestionnaireSummary administeredQuestionnaireSummary = 
			(AdministeredQuestionnaireSummary)this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(QUESTIONNAIRE_SUMMARIZE_QUERY_NAME)
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						administeredQuestionnaire)
				.uniqueResult();
			
		return administeredQuestionnaireSummary;
	}
	
	/**{@inheritDoc}*/
	@Override
	public QuestionSummary summarize(final AllowedQuestion allowedQuestion){
		QuestionSummary questionSummary = (QuestionSummary) this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(QUESTION_SUMMARIZE_QUERY_NAME)
				.setParameter(ALLOWED_QUESTION_PARAM_NAME, allowedQuestion)
				.uniqueResult();
		
		return questionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public List<AdministeredQuestionnaireSectionSummary>
		findAdministeredQuestionnaireSectionSummariesByAdministeredQuestionnaire(
			final AdministeredQuestionnaire administeredQuestionnaire) {
		@SuppressWarnings("unchecked")
		List<AdministeredQuestionnaireSectionSummary> questionnaireSectionSummaries = 
			this.sessionFactory.getCurrentSession()
			.getNamedQuery(
			FIND_QUESTIONNAIRE_SECTION_SUMMARIES_BY_QUESTIONNAIRE_QUERY_NAME)
			.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
					administeredQuestionnaire)
			.list();
		
		return questionnaireSectionSummaries;
	}

	/**{@inheritDoc} */
	@Override
	public List<QuestionSummary> findQuestionSummariesByQuestionnaireSection(
			final QuestionnaireSection questionnaireSection) {
		@SuppressWarnings("unchecked")
		List<QuestionSummary> questionSummaries =
			this.sessionFactory.getCurrentSession()
			.getNamedQuery(
					FIND_QUESTION_SUMMARIES_BY_QUESTIONNAIRE_SECTION_QUERY_NAME)
			.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, questionnaireSection)
			.list();
		
		return questionSummaries;
	}

	/**{@inheritDoc} */
	@Override
	public List<AnswerValueSummary> findAnswerValueSummariesByAllowedQuestion(
			final AllowedQuestion allowedQuestion) {
		@SuppressWarnings("unchecked")
		List<AnswerValueSummary> answerValueSummaries =
			this.sessionFactory.getCurrentSession()
			.getNamedQuery(
					FIND_ANSWER_VALUE_SUMMARIES_BY_ALLOWED_QUESTION_QUERY_NAME)
			.setParameter(ALLOWED_QUESTION_PARAM_NAME, allowedQuestion)
			.list();
		
		return answerValueSummaries;
	}

}
