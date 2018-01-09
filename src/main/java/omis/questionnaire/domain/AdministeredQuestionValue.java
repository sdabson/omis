package omis.questionnaire.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * AdministeredQuestionValue.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public interface AdministeredQuestionValue extends Creatable, Updatable {
	
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
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
	 * Returns the comments
	 * @return comments - String
	 */
	public String getComments();
	
	/**
	 * Sets the comments 
	 * @param comments - String
	 */
	public void setComments(String comments);
	
	/**
	 * Returns the answer value
	 * @return answerValue
	 */
	public AnswerValue getAnswerValue();
	
	/**
	 * Sets the answer value
	 * @param answerValue
	 */
	public void setAnswerValue(AnswerValue answerValue);
	
	/**
	 * Returns the answer value text
	 * @return answerValueText - String
	 */
	public String getAnswerValueText();
	
	/**
	 * Sets the answer value Text
	 * @param answerValueText - String
	 */
	public void setAnswerValueText(String answerValueText);
	
	/**
	 * Returns the administered questionnaire
	 * @return administeredQuestionnaire
	 */
	public AdministeredQuestionnaire getAdministeredQuestionnaire();
	
	/**
	 * Sets the administered questionnaire
	 * @param administeredQuestionnaire
	 */
	public void setAdministeredQuestionnaire(
			AdministeredQuestionnaire administeredQuestionnaire);
	
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
