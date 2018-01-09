package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * AllowedAnswer.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface AllowedAnswer extends Creatable, Updatable {

	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Returns the allowed question
	 * @return AllowedQuestion - allowed question
	 */
	public AllowedQuestion getAllowedQuestion();
	
	/**
	 * Sets the Allowed Question
	 * @param allowedQuestion - allowed question
	 */
	public void setAllowedQuestion(AllowedQuestion allowedQuestion);
	
	/**
	 * Returns the sort order
	 * @return sortOrder - short
	 */
	public Short getSortOrder();
	
	/**
	 * Sets the sort order
	 * @param sortOrder - short
	 */
	public void setSortOrder(Short sortOrder);
	
	/**
	 * Returns the Answer Value
	 * @return AnswerValue - answer value
	 */
	public AnswerValue getAnswerValue();
	
	/**
	 * Sets the Answer Value
	 * @param answerValue - answer value
	 */
	public void setAnswerValue(AnswerValue answerValue);
	
	
	
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
