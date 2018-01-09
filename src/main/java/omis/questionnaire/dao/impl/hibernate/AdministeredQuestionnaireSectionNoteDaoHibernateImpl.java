package omis.questionnaire.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.AdministeredQuestionnaireSectionNoteDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionNote;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<AdministeredQuestionnaireSectionNote>
		implements AdministeredQuestionnaireSectionNoteDao {
	
	/* Query Names */
	
	private static final String 
		FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_QUERY_NAME = 
			"findAdministeredQuestionnaireSectionNote";
	
	private static final String 
		FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_EXCLUDING_QUERY_NAME = 
			"findAdministeredQuestionnaireSectionNoteExcluding";
	
	private static final String 
FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_BY_QUESTIONNAIRE_AND_SECTION_QUERY_NAME = 
		"findAdministeredQuestionnaireSectionNoteByQuestionnaireAndSection";
	
	/* Parameter Names */
	
	private static final String QUESTIONNAIRE_SECTION_PARAM_NAME =
			"questionnaireSection";
	
	private static final String COMMENTS_PARAM_NAME = "comments";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_PARAM_NAME =
			"administeredQuestionnaire";
	
	private static final String 
		ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_PARAM_NAME 
			= "administeredQuestionnaireSectionNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected AdministeredQuestionnaireSectionNoteDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaireSectionNote find(
			final QuestionnaireSection questionnaireSection, 
			final String comments,
			final AdministeredQuestionnaire administeredQuestionnaire) {
		AdministeredQuestionnaireSectionNote administeredQuestionnaireSectionNote
			= (AdministeredQuestionnaireSectionNote) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_QUERY_NAME)
			.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
					questionnaireSection)
			.setParameter(COMMENTS_PARAM_NAME, comments)
			.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
					administeredQuestionnaire)
			.uniqueResult();
		
		return administeredQuestionnaireSectionNote;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaireSectionNote findExcluding(
			final QuestionnaireSection questionnaireSection,
			final String comments, 
			final AdministeredQuestionnaire administeredQuestionnaire,
			final AdministeredQuestionnaireSectionNote 
				excludedAdministeredQuestionnaireSectionNote) {
		AdministeredQuestionnaireSectionNote administeredQuestionnaireSectionNote
		= (AdministeredQuestionnaireSectionNote) this.getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(
				FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_EXCLUDING_QUERY_NAME)
		.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
				questionnaireSection)
		.setParameter(COMMENTS_PARAM_NAME, comments)
		.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
				administeredQuestionnaire)
		.setParameter(ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_PARAM_NAME, 
				excludedAdministeredQuestionnaireSectionNote)
		.uniqueResult();
	
		return administeredQuestionnaireSectionNote;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaireSectionNote findByQuestionnaireAndSection(
			final AdministeredQuestionnaire administeredQuestionnaire, 
			final QuestionnaireSection questionnaireSection) {
		AdministeredQuestionnaireSectionNote administeredQuestionnaireSectionNote
		= (AdministeredQuestionnaireSectionNote) this.getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(
FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_NOTE_BY_QUESTIONNAIRE_AND_SECTION_QUERY_NAME)
		.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
				questionnaireSection)
		.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
				administeredQuestionnaire)
		.uniqueResult();
	
		return administeredQuestionnaireSectionNote;
	}
	
	
	
	
}
