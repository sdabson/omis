package omis.questionnaire.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.questionnaire.dao.QuestionnaireTypeDao;
import omis.questionnaire.domain.QuestionnaireCategory;
import omis.questionnaire.domain.QuestionnaireType;

/**
 * QuestionnaireTypeDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 8, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireTypeDaoHibernateImpl 
	extends GenericHibernateDaoImpl<QuestionnaireType>
		implements QuestionnaireTypeDao {
	
	/* Query Names */
	
	private static final String FIND_QUESTIONNAIRE_TYPE_QUERY_NAME =
			"findQuestionnaireType";
	
	private static final String FIND_QUESTIONNAIRE_TYPE_EXCLUDING_QUERY_NAME =
			"findQuestionnaireTypeExcluding";
	
	private static final String 
		FIND_QUESTIONNAIRE_TYPE_BY_EFFECTIVE_DATE_QUERY_NAME = 
			"findQuestionnaireTypeByEffectiveDate";
	
	private static final String 
	FIND_QUESTIONNAIRE_TYPES_BY_QUESTIONNAIRE_CATEGORY_QUERY_NAME = 
			"findQuestionnaireTypesByQuestionnaireCategory";
	
	private static final String 
	FIND_QUESTIONNAIRE_TYPES_BY_QUESTIONNAIRE_CATEGORY_AND_DATE_QUERY_NAME = 
			"findQuestionnaireTypesByQuestionnaireCategoryAndEffectiveDate";
	
	private static final String FIND_CURRENT_CYCLE_BY_TITLE_QUERY_NAME =
			"findCurrentCycleByTitle";
		
	
	/* Parameter Names */
	
	private static final String TITLE_PARAM_NAME = "title";
	
	private static final String CYCLE_PARAM_NAME = "cycle";
	
	private static final String QUESTIONNAIRE_TYPE_PARAM_NAME = 
			"questionnaireType";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";

	private static final String QUESTIONNAIRE_CATEGORY_PARAM_NAME = 
			"questionnaireCategory";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected QuestionnaireTypeDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireType find(final String title, final Integer cycle) {
		QuestionnaireType questionnaireType = 
				(QuestionnaireType) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONNAIRE_TYPE_QUERY_NAME)
				.setParameter(TITLE_PARAM_NAME, title)
				.setParameter(CYCLE_PARAM_NAME, cycle)
				.uniqueResult();
		
		return questionnaireType;
	}

	/**{@inheritDoc} */
	@Override
	public QuestionnaireType findExcluding(final String title,
			final Integer cycle,
			final QuestionnaireType excludedQuestionnaireType) {
		QuestionnaireType questionnaireType = 
				(QuestionnaireType) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUESTIONNAIRE_TYPE_EXCLUDING_QUERY_NAME)
				.setParameter(TITLE_PARAM_NAME, title)
				.setParameter(CYCLE_PARAM_NAME, cycle)
				.setParameter(QUESTIONNAIRE_TYPE_PARAM_NAME, 
						excludedQuestionnaireType)
				.uniqueResult();
		
		return questionnaireType;
	}

	/**{@inheritDoc} */
	@Override
	public List<QuestionnaireType> findByDate(final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<QuestionnaireType> questionnaireTypes = 
				(List<QuestionnaireType>) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_QUESTIONNAIRE_TYPE_BY_EFFECTIVE_DATE_QUERY_NAME)
				.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		
		return questionnaireTypes;
	}

	/**{@inheritDoc} */
	@Override
	public List<QuestionnaireType> findByCategory(
			final QuestionnaireCategory category) {
		@SuppressWarnings("unchecked")
		List<QuestionnaireType> questionnaireTypes = 
				(List<QuestionnaireType>) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_QUESTIONNAIRE_TYPES_BY_QUESTIONNAIRE_CATEGORY_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_CATEGORY_PARAM_NAME, category)
				.list();
		
		return questionnaireTypes;
	}

	/**{@inheritDoc} */
	@Override
	public List<QuestionnaireType> findByCategoryAndDate(
			final QuestionnaireCategory category, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<QuestionnaireType> questionnaireTypes = 
				(List<QuestionnaireType>) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
		FIND_QUESTIONNAIRE_TYPES_BY_QUESTIONNAIRE_CATEGORY_AND_DATE_QUERY_NAME)
				.setParameter(QUESTIONNAIRE_CATEGORY_PARAM_NAME, category)
				.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		
		return questionnaireTypes;
	}

	/**{@inheritDoc} */
	@Override
	public Integer findNextCycleByQuestionnaireType(
			final QuestionnaireType questionnaireType) {
		Integer cycle = (Integer) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CURRENT_CYCLE_BY_TITLE_QUERY_NAME)
				.setMaxResults(1)
				.setParameter(TITLE_PARAM_NAME, questionnaireType.getTitle())
				.uniqueResult();
		cycle++;
		return cycle;
	}

}
