package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Evaluation Recommendation Section Summary Form
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryForm {
	
	private String description;
	
	private List<EvaluationRecommendationSectionSummaryNoteItem>
		evaluationRecommendationSectionSummaryNoteItems =
		new ArrayList<EvaluationRecommendationSectionSummaryNoteItem>();
	
	/**
	 * 
	 */
	public EvaluationRecommendationSectionSummaryForm() {
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
	 * Returns the evaluationRecommendationSectionSummaryNoteItems
	 * @return evaluationRecommendationSectionSummaryNoteItems -
	 * List<EvaluationRecommendationSectionSummaryNoteItem>
	 */
	public List<EvaluationRecommendationSectionSummaryNoteItem>
		getEvaluationRecommendationSectionSummaryNoteItems() {
		return evaluationRecommendationSectionSummaryNoteItems;
	}

	/**
	 * Sets the evaluationRecommendationSectionSummaryNoteItems
	 * @param evaluationRecommendationSectionSummaryNoteItems -
	 * List<EvaluationRecommendationSectionSummaryNoteItem>
	 */
	public void setEvaluationRecommendationSectionSummaryNoteItems(
			final List<EvaluationRecommendationSectionSummaryNoteItem>
				evaluationRecommendationSectionSummaryNoteItems) {
		this.evaluationRecommendationSectionSummaryNoteItems =
				evaluationRecommendationSectionSummaryNoteItems;
	}
}
