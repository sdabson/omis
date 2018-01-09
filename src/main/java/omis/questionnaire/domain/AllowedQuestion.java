package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * AllowedQuestion.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface AllowedQuestion extends Creatable, Updatable {
	
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/**
	 * Returns the questionnaire section
	 * @return questionnaireSection
	 */
	public QuestionnaireSection getQuestionnaireSection();
	
	/**
	 * Sets the questionnaire section
	 * @param questionnaireSection
	 */
	public void setQuestionnaireSection(
			QuestionnaireSection questionnaireSection);
	
	/**
	 * Returns the question
	 * @return question
	 */
	public Question getQuestion();
	
	/**
	 * Sets the question
	 * @param question
	 */
	public void setQuestion(Question question);
	
	/**
	 * Returns valid
	 * @return valid- Boolean
	 */
	public Boolean getValid();
	
	/**
	 * Sets valid
	 * @param valid - Boolean
	 */
	public void setValid(Boolean valid);
	
	/**
	 * Returns the sort order
	 * @return sortOrder - Short
	 */
	public Short getSortOrder();
	
	/**
	 * Sets the sort order
	 * @param sortOrder - Short
	 */
	public void setSortOrder(Short sortOrder);
	
	/**
	 * Returns the question number
	 * @return questionNumber - String
	 */
	public String getQuestionNumber();
	
	/**
	 * Sets the question number
	 * @param questionNumber - String
	 */
	public void setQuestionNumber(String questionNumber);
	
	/**
	 * Returns the question conditionality
	 * @return questionConditionality
	 */
	public QuestionConditionality getQuestionConditionality();
	
	/**
	 * Sets the question conditionality
	 * @param questionConditionality
	 */
	public void setQuestionConditionality(
			QuestionConditionality questionConditionality);
	
	/**
	 * Returns the answer cardinality
	 * @return answerCardinality
	 */
	public AnswerCardinality getAnswerCardinality();
	
	/**
	 * Sets the answer cardinality
	 * @param answerCardinality
	 */
	public void setAnswerCardinality(AnswerCardinality answerCardinality);
	
	/**
	 * Returns the question help
	 * @return questionHelp - String
	 */
	public String getQuestionHelp();
	
	/**
	 * Sets the question help
	 * @param questionHelp - String
	 */
	public void setQuestionHelp(String questionHelp);
	
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
