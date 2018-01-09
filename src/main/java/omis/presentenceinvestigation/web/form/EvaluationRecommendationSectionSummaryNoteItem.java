package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;

/**
 * Evaluation Recommendation Section Summary Note Item
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryNoteItem {
	
	private EvaluationRecommendationSectionSummaryNote
		evaluationRecommendationSectionSummaryNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	/**
	 * 
	 */
	public EvaluationRecommendationSectionSummaryNoteItem() {
	}

	/**
	 * Returns the evaluationRecommendationSectionSummaryNote
	 * @return evaluationRecommendationSectionSummaryNote -
	 * EvaluationRecommendationSectionSummaryNote
	 */
	public EvaluationRecommendationSectionSummaryNote
			getEvaluationRecommendationSectionSummaryNote() {
		return evaluationRecommendationSectionSummaryNote;
	}

	/**
	 * Sets the evaluationRecommendationSectionSummaryNote
	 * @param evaluationRecommendationSectionSummaryNote -
	 * EvaluationRecommendationSectionSummaryNote
	 */
	public void setEvaluationRecommendationSectionSummaryNote(
			final EvaluationRecommendationSectionSummaryNote
				evaluationRecommendationSectionSummaryNote) {
		this.evaluationRecommendationSectionSummaryNote =
				evaluationRecommendationSectionSummaryNote;
	}

	/**
	 * Returns the description
	 * @return description - String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - PresentenceInvestigationItemOperation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - PresentenceInvestigationItemOperation
	 */
	public void setItemOperation(
			final PresentenceInvestigationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
