package omis.questionnaire.web.form;

import java.util.List;

import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.AnswerValue;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;
import omis.questionnaire.domain.QuestionConditionality;

/**
 * QuestionAnswerItems.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 20, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionAnswerItem {
	
	private Question question;
	
	private AnswerValue answerValue;
	
	private List<AnswerValueItem> answerValueItems;
	
	private String answerValueText;
	
	private String comments;
	
	private QuestionCategory questionCategory;
	
	private AnswerCardinality answerCardinality;
	
	private QuestionConditionality questionConditionality;
	
	private Boolean addComments;
	
	/**
	 * Default constructor for QuestionAnswerItem
	 */
	public QuestionAnswerItem(){
		
	}
	
	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return this.question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
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
	 * @return the answerValueItems
	 */
	public List<AnswerValueItem> getAnswerValueItems() {
		return this.answerValueItems;
	}

	/**
	 * @param answerValueItens the answerValueItems to set
	 */
	public void setAnswerValueItems(List<AnswerValueItem> answerValueItems) {
		this.answerValueItems = answerValueItems;
	}

	/**
	 * @return the answerValueText
	 */
	public String getAnswerValueText() {
		return this.answerValueText;
	}

	/**
	 * @param answerValueText the answerValueText to set
	 */
	public void setAnswerValueText(String answerValueText) {
		this.answerValueText = answerValueText;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the questionCategory
	 */
	public QuestionCategory getQuestionCategory() {
		return this.questionCategory;
	}

	/**
	 * @param questionCategory the questionCategory to set
	 */
	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	/**
	 * @return the answerCardinality
	 */
	public AnswerCardinality getAnswerCardinality() {
		return this.answerCardinality;
	}

	/**
	 * @param answerCardinality the answerCardinality to set
	 */
	public void setAnswerCardinality(AnswerCardinality answerCardinality) {
		this.answerCardinality = answerCardinality;
	}

	/**
	 * @return the questionConditionality
	 */
	public QuestionConditionality getQuestionConditionality() {
		return this.questionConditionality;
	}

	/**
	 * @param questionConditionality the questionConditionality to set
	 */
	public void setQuestionConditionality(QuestionConditionality questionConditionality) {
		this.questionConditionality = questionConditionality;
	}

	/**
	 * @return the addComments
	 */
	public Boolean getAddComments() {
		return this.addComments;
	}

	/**
	 * @param addComments the addComments to set
	 */
	public void setAddComments(Boolean addComments) {
		this.addComments = addComments;
	}
	
	
}
