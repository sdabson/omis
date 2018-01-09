package omis.presentenceinvestigation.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Evaluation Recommendation Section Summary Service
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public interface EvaluationRecommendationSectionSummaryService {
	
	/**
	 * Creates an Evaluation Recommendation Section Summary with the specified
	 * properties
	 * @param text - String
	 * @param presentenceInvestigationRequest - Presentence Investigation
	 * Request
	 * @return Newly created Evaluation Recommendation Section Summary
	 * @throws DuplicateEntityFoundException - When an Evaluation Recommendation
	 * Section Summary already exists for the given Presentence Investigation
	 * Request
	 */
	public EvaluationRecommendationSectionSummary
		createEvaluationRecommendationSectionSummary(String text,
			PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the given Evaluation Recommendation Section Summary with the specified
	 * properties
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary to update
	 * @param text - String
	 * @return Updated Evaluation Recommendation Section Summary
	 * @throws DuplicateEntityFoundException - When an Evaluation Recommendation
	 * Section Summary already exists for the given Presentence Investigation
	 * Request
	 */
	public EvaluationRecommendationSectionSummary
		updateEvaluationRecommendationSectionSummary(
			EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary, String text)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Evaluation Recommendation Section Summary
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary to remove
	 */
	public void removeEvaluationRecommendationSectionSummary(
			EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary);
	
	/**
	 * Creates an Evaluation Recommendation Section Summary Note with the
	 * specified properties
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary
	 * @param description - String
	 * @param date - Date
	 * @return Newly created Evaluation Recommendation Section Summary Note
	 * @throws DuplicateEntityFoundException - When an Evaluation Recommendation
	 * Section Summary Note already exists for the Evaluation Recommendation
	 * Section Summary with the given date and description
	 */
	public EvaluationRecommendationSectionSummaryNote
		createEvaluationRecommendationSectionSummaryNote(
			EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates the given Evaluation Recommendation Section Summary Note with the
	 * specified properties
	 * @param evaluationRecommendationSectionSummaryNote - Evaluation Recommendation
	 * Section Summary Note to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated Evaluation Recommendation Section Summary Note
	 * @throws DuplicateEntityFoundException - When a Evaluation Recommendation
	 * Section Summary Note already exists for the Evaluation Recommendation
	 * Section Summary with the given date and description
	 */
	public EvaluationRecommendationSectionSummaryNote
		updateEvaluationRecommendationSectionSummaryNote(
			EvaluationRecommendationSectionSummaryNote
				evaluationRecommendationSectionSummaryNote,
			String description, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an Evaluation Recommendation Section Summary Note
	 * @param evaluationRecommendationSectionSummaryNote - Evaluation
	 * Recommendation Section Summary Note to remove
	 */
	public void removeEvaluationRecommendationSectionSummaryNote(
			EvaluationRecommendationSectionSummaryNote
				evaluationRecommendationSectionSummaryNote);
	
	/**
	 * Returns an Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request
	 * @param presentenceInvestigationRequest - Presentence Investigation Request
	 * @return Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request
	 */
	public EvaluationRecommendationSectionSummary
		findByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns a list of Evaluation Recommendation Section Summary Notes by the
	 * specified Evaluation Recommendation Section Summary
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary
	 * @return List of Evaluation Recommendation Section Summary Notes by the
	 * specified Evaluation Recommendation Section Summary
	 */
	public List<EvaluationRecommendationSectionSummaryNote>
		findSectionSummaryNotesByEvaluationRecommendationSectionSummary(
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary);
}
