package omis.presentenceinvestigation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Evaluation Recommendation Section Summary Note
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 11, 2017)
 *@since OMIS 3.0
 *
 */
public interface EvaluationRecommendationSectionSummaryNote
		extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the Evaluation Recommendation Section Summary Note
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the Evaluation Recommendation Section Summary Note
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the description of the Evaluation Recommendation Section
	 * Summary Note
	 * @return description - string
	 */
	public String getDescription();
	
	/**
	 * Sets the description of the Evaluation Recommendation Section
	 * Summary Note
	 * @param description - String
	 */
	public void setDescription(String description);
	
	/**
	 * Returns the date of the Evaluation Recommendation Section Summary Note
	 * @return date - Date
	 */
	public Date getDate();
	
	/**
	 * Sets the date of the Evaluation Recommendation Section Summary Note
	 * @param date - Date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the Evaluation Recommendation Section Summary of the
	 * Evaluation Recommendation Section Summary Note
	 * @return evaluationRecommendationSectionSummary -
	 * Evaluation Recommendation Section Summary
	 */
	public EvaluationRecommendationSectionSummary
			getEvaluationRecommendationSectionSummary();
	
	/**
	 * Sets the Evaluation Recommendation Section Summary of the
	 * Evaluation Recommendation Section Summary Note
	 * @param evaluationRecommendationSectionSummary - Evaluation
	 * Recommendation Section Summary
	 */
	public void setEvaluationRecommendationSectionSummary(
			EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
	
}
