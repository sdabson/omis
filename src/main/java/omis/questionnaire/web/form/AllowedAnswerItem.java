package omis.questionnaire.web.form;

import omis.questionnaire.domain.AllowedAnswer;
import omis.questionnaire.domain.AnswerValue;

/**
 * AllowedAnswerItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 22, 2016)
 *@since OMIS 3.0
 *
 */
public class AllowedAnswerItem {
	
	private AllowedAnswer allowedAnswer;
	
	private AnswerValue answerValue;
	
	private String description;
	
	private ItemOperation operation;
	
	private Boolean existingAnswer;
	
	/**
	 * 
	 */
	public AllowedAnswerItem() {
	}

	/**
	 * @return the allowedAnswer
	 */
	public AllowedAnswer getAllowedAnswer() {
		return this.allowedAnswer;
	}

	/**
	 * @param allowedAnswer the allowedAnswer to set
	 */
	public void setAllowedAnswer(AllowedAnswer allowedAnswer) {
		this.allowedAnswer = allowedAnswer;
	}
	/**
	 * @return the answerValue
	 */
	public AnswerValue getAnswerValue() {
		return this.answerValue;
	}

	/**
	 * @param answerValue the answerValue to set
	 */
	public void setAnswerValue(AnswerValue answerValue) {
		this.answerValue = answerValue;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the operation
	 */
	public ItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(ItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * @return the existingAnswer
	 */
	public Boolean getExistingAnswer() {
		return this.existingAnswer;
	}

	/**
	 * @param existingAnswer the existingAnswer to set
	 */
	public void setExistingAnswer(Boolean existingAnswer) {
		this.existingAnswer = existingAnswer;
	}
	
	
	
	
}
