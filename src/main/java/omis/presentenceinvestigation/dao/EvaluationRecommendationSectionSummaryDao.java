package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Evaluation Recommendation Section Summary Dao
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public interface EvaluationRecommendationSectionSummaryDao
		extends GenericDao<EvaluationRecommendationSectionSummary> {
	
	/**
	 * Returns an Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request
	 * @param presentenceInvestigationRequest - Presentence Investigation Request
	 * @return Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request
	 */
	public EvaluationRecommendationSectionSummary find(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns an Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request excluding specified
	 * Evaluation Recommendation Section Summary
	 * @param presentenceInvestigationRequest - Presentence Investigation Request
	 * @param evaluationRecommendationSectionSummaryExcluded - Evaluation
	 * Recommendation Section Summary to exclude
	 * @return Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request excluding specified
	 */
	public EvaluationRecommendationSectionSummary findExcluding(
			PresentenceInvestigationRequest presentenceInvestigationRequest,
			EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummaryExcluded);
	
}
