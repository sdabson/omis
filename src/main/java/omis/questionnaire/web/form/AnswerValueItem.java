package omis.questionnaire.web.form;

import omis.questionnaire.domain.AnswerValue;

/**
 * AnswerValueItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 26, 2016)
 *@since OMIS 3.0
 *
 */
public class AnswerValueItem {
	
	private AnswerValue answerValue;
	
	/**
	 * Default constructor for AnswerValueItem 
	 */
	public AnswerValueItem() {
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
	
	
	
}
