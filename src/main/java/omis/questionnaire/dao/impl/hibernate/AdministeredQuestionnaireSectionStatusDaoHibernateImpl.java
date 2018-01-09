package omis.questionnaire.dao.impl.hibernate;


import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.AdministeredQuestionnaireSectionStatusDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.AdministeredQuestionnaireSectionStatus;
import omis.questionnaire.domain.QuestionnaireSection;

/**
 * AdministeredQuestionnaireSectionStatusDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 16, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSectionStatusDaoHibernateImpl
		extends GenericHibernateDaoImpl<AdministeredQuestionnaireSectionStatus>
		implements AdministeredQuestionnaireSectionStatusDao {
	
	/* Query Names */
	
	private static final String 
		FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_QUERY_NAME =
			"findAdministeredQuestionnaireSectionStatus";
	
	private static final String 
	FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_EXCLUDING_QUERY_NAME =
		"findAdministeredQuestionnaireSectionStatusExcluding";
	
	private static final String 
	FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_BY_QUESTIONNAIRE_QUERY_NAME =
		"findAdministeredQuestionnaireSectionStatusByAdministeredQuestionnaire";
	
	/* Parameter Names */
	
	private static final String QUESTIONNAIRE_SECTION_PARAM_NAME = 
			"questionnaireSection";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_PARAM_NAME =
			"administeredQuestionnaire";
	
	private static final String DRAFT_PARAM_NAME = "draft";
	
	private static final String 
		ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_PARAM_NAME =
			"administeredQuestionnaireSectionStatus";
	
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected AdministeredQuestionnaireSectionStatusDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaireSectionStatus find(
			final QuestionnaireSection questionnaireSection,
			final AdministeredQuestionnaire administeredQuestionnaire, 
			final Boolean draft) {
		AdministeredQuestionnaireSectionStatus 
			administeredQuestionnaireSectionStatus =
				(AdministeredQuestionnaireSectionStatus) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
						questionnaireSection)
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						administeredQuestionnaire)
				.setParameter(DRAFT_PARAM_NAME, draft)
				.uniqueResult();
		
		
		return administeredQuestionnaireSectionStatus;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaireSectionStatus findExcluding(
			final QuestionnaireSection questionnaireSection,
			final AdministeredQuestionnaire administeredQuestionnaire, 
			final Boolean draft,
			final AdministeredQuestionnaireSectionStatus excludedStatus) {
		AdministeredQuestionnaireSectionStatus 
		administeredQuestionnaireSectionStatus =
			(AdministeredQuestionnaireSectionStatus) 
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
			FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_EXCLUDING_QUERY_NAME)
			.setParameter(QUESTIONNAIRE_SECTION_PARAM_NAME, 
					questionnaireSection)
			.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
					administeredQuestionnaire)
			.setParameter(DRAFT_PARAM_NAME, draft)
			.setParameter(ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_PARAM_NAME, 
					excludedStatus)
			.uniqueResult();
	
	
	return administeredQuestionnaireSectionStatus;
	}

	/**{@inheritDoc} */
	@Override
	public List<AdministeredQuestionnaireSectionStatus>
		findByAdministeredQuestionnaire(
			final AdministeredQuestionnaire administeredQuestionnaire) {
		@SuppressWarnings("unchecked")
		List<AdministeredQuestionnaireSectionStatus> 
		administeredQuestionnaireSectionStatuses =
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
	FIND_ADMINISTERED_QUESTIONNAIRE_SECTION_STATUS_BY_QUESTIONNAIRE_QUERY_NAME)
			.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
					administeredQuestionnaire)
			.list();
	
		return administeredQuestionnaireSectionStatuses;
	}

	

}
