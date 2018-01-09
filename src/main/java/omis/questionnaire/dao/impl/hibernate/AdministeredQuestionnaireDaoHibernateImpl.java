package omis.questionnaire.dao.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;
import omis.questionnaire.dao.AdministeredQuestionnaireDao;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * AdministeredQuestionnaireDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AdministeredQuestionnaire>
		implements AdministeredQuestionnaireDao {
	
	/* Query Names */
	
	private static final String FIND_ADMINISTERED_QUESTIONNAIRE_QUERY_NAME =
			"findAdministeredQuestionnaire";
	
	private static final String 
		FIND_ADMINISTERED_QUESTIONNAIRE_EXCLUDING_QUERY_NAME =
			"findAdministeredQuestionnaireExcluding";
	
	/* Parameter names */
	
	private static final String ANSWERER_PARAM_NAME = "answerer";
	
	private static final String ADMINISTERED_QUESTIONNAIRE_PARAM_NAME = 
			"administeredQuestionnaire";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String QUESTIONNAIRE_TYPE_PARAM_NAME = 
			"questionnaireType";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected AdministeredQuestionnaireDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaire find(final Person answerer, 
			final Date date, final QuestionnaireType questionnaireType) {
		AdministeredQuestionnaire administeredQuestionnaire = 
				(AdministeredQuestionnaire) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ADMINISTERED_QUESTIONNAIRE_QUERY_NAME)
				.setParameter(ANSWERER_PARAM_NAME, answerer)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(QUESTIONNAIRE_TYPE_PARAM_NAME, questionnaireType)
				.uniqueResult();
				
		
		return administeredQuestionnaire;
	}

	/**{@inheritDoc} */
	@Override
	public AdministeredQuestionnaire findExcluding(final Person answerer,
			final Date date, final QuestionnaireType questionnaireType,
			final AdministeredQuestionnaire excludedAdministeredQuestionnaire) {
		AdministeredQuestionnaire administeredQuestionnaire = 
				(AdministeredQuestionnaire) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_ADMINISTERED_QUESTIONNAIRE_EXCLUDING_QUERY_NAME)
				.setParameter(ANSWERER_PARAM_NAME, answerer)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(QUESTIONNAIRE_TYPE_PARAM_NAME, questionnaireType)
				.setParameter(ADMINISTERED_QUESTIONNAIRE_PARAM_NAME, 
						excludedAdministeredQuestionnaire)
				.uniqueResult();
				
		
		return administeredQuestionnaire;
	}

	

}
