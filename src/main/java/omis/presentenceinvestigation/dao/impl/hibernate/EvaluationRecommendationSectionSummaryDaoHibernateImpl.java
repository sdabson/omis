package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.EvaluationRecommendationSectionSummaryDao;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Evaluation Recommendation Section Summary Dao Hibernate Impl 
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryDaoHibernateImpl extends
		GenericHibernateDaoImpl<EvaluationRecommendationSectionSummary>
		implements EvaluationRecommendationSectionSummaryDao {
	
	private static final String FIND_SECTION_SUMMARY_QUERY_NAME = 
			"findEvaluationRecommendationSectionSummary";
	
	private static final String FIND_SECTION_SUMMARY_EXCLUDING_QUERY_NAME = 
			"findEvaluationRecommendationSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	private static final String
			EVALUATION_RECOMMENDATION_SECTION_SUMMARY_PARAM_NAME =
				"evaluationRecommendationSectionSummary";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EvaluationRecommendationSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummary
		find(final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		EvaluationRecommendationSectionSummary sectionSummary =
				(EvaluationRecommendationSectionSummary) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return sectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummaryExcluded) {
		EvaluationRecommendationSectionSummary sectionSummary =
				(EvaluationRecommendationSectionSummary) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(EVALUATION_RECOMMENDATION_SECTION_SUMMARY_PARAM_NAME,
						evaluationRecommendationSectionSummaryExcluded)
				.uniqueResult();
		
		return sectionSummary;
	}

}
