package omis.presentenceinvestigation.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;

/**
 * Evaluation Recommendation Section Summary Note Dao
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public interface EvaluationRecommendationSectionSummaryNoteDao
		extends GenericDao<EvaluationRecommendationSectionSummaryNote> {
	
	/**
	 * Returns a Evaluation Recommendation Section Summary Note with the specified
	 * properties
	 * @param description - String
	 * @param date - Date
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary
	 * @return Evaluation Recommendation Section Summary Note with the specified
	 * properties
	 */
	public EvaluationRecommendationSectionSummaryNote find(String description,
			Date date, EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary);
	
	/**
	 * Returns a Evaluation Recommendation Section Summary Note with the specified
	 * properties excluding specified Evaluation Recommendation Section Summary
	 * Note
	 * @param description - String
	 * @param date - Date
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary
	 * @param evaluationRecommendationSectionSummaryNoteExcluded - Evaluation
	 * Recommendation Section Summary Note to exclude
	 * @return Evaluation Recommendation Section Summary Note with the specified
	 * properties excluding specified Evaluation Recommendation Section Summary
	 * Note
	 */
	public EvaluationRecommendationSectionSummaryNote findExcluding(
			String description, Date date,
			EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary,
			EvaluationRecommendationSectionSummaryNote
				evaluationRecommendationSectionSummaryNoteExcluded);
	
	/**
	 * Returns a list of Evaluation Recommendation Section Summary Notes by the
	 * specified Evaluation Recommendation Section Summary
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary
	 * @return List of Evaluation Recommendation Section Summary Notes by the
	 * specified Evaluation Recommendation Section Summary
	 */
	public List<EvaluationRecommendationSectionSummaryNote>
		findBySectionSummary(EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary);
	
}
