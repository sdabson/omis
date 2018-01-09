package omis.questionnaire.report;

import java.io.Serializable;

import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.QuestionCategory;
import omis.questionnaire.domain.QuestionConditionality;

/**
 * QuestionSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long questionId;
	
	private final Long allowedQuestionId;
	
	private final String questionNumber;
	
	private final String questionText;
	
	private final String questionHelpText;
	
	private final QuestionCategory questionCategory;
	
	private final QuestionConditionality questionConditionality;
	
	private final AnswerCardinality answerCardinality;
	
	private final Boolean valid;
	
	/**
	 * @param questionId
	 * @param allowedQuestionId
	 * @param questionNumber
	 * @param questionText
	 * @param questionCategory
	 * @param questionConditionality
	 * @param answerCardinality
	 */
	public QuestionSummary(final Long questionId, final Long allowedQuestionId, 
			final String questionNumber, final String questionText,
			final String questionHelpText, 
			final QuestionCategory questionCategory, 
			final QuestionConditionality questionConditionality,
			final AnswerCardinality answerCardinality, final Boolean valid) {
		this.questionId = questionId;
		this.allowedQuestionId = allowedQuestionId;
		this.questionNumber = questionNumber;
		this.questionText = questionText;
		this.questionHelpText = questionHelpText;
		this.questionCategory = questionCategory;
		this.questionConditionality = questionConditionality;
		this.answerCardinality = answerCardinality;
		this.valid = valid;
	}

	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return this.questionId;
	}

	/**
	 * @return the allowedQuestionId
	 */
	public Long getAllowedQuestionId() {
		return this.allowedQuestionId;
	}

	/**
	 * @return the questionNumber
	 */
	public String getQuestionNumber() {
		return this.questionNumber;
	}

	/**
	 * @return the questionText
	 */
	public String getQuestionText() {
		return this.questionText;
	}
	
	
	/**
	 * @return the questionHelpText
	 */
	public String getQuestionHelpText() {
		return this.questionHelpText;
	}

	/**
	 * @return the questionCategory
	 */
	public QuestionCategory getQuestionCategory() {
		return this.questionCategory;
	}

	/**
	 * @return the questionConditionality
	 */
	public QuestionConditionality getQuestionConditionality() {
		return this.questionConditionality;
	}

	/**
	 * @return the answerCardinality
	 */
	public AnswerCardinality getAnswerCardinality() {
		return this.answerCardinality;
	}

	/**
	 * @return the valid
	 */
	public Boolean getValid() {
		return this.valid;
	}
	
	
	
}
