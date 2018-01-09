package omis.questionnaire.web.form;

import java.util.List;

import omis.questionnaire.domain.AllowedQuestion;
import omis.questionnaire.domain.AnswerCardinality;
import omis.questionnaire.domain.Question;
import omis.questionnaire.domain.QuestionCategory;

/**
 * QuestionAnswerEditItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 17, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionAnswerEditItem {
	
	private AllowedQuestion allowedQuestion;
	
	private Boolean existingQuestion;
	
	private Boolean multipleCardinality;
	
	private String questionNumber;
	
	private String questionHelp;
	
	private Question question;
	
	private String questionQuery;
	
	private String questionText;
	
	private Boolean valid;
	
	private Boolean required;
	
	private QuestionCategory questionCategory;
	
	private AnswerCardinality answerCardinality;
	
	private List<AllowedAnswerItem> allowedAnswerItems;
	
	private ItemOperation operation;
	
	public Short sortOrder;
	
	/**
	 * 
	 */
	public QuestionAnswerEditItem() {
	}

	/**
	 * @return the allowedQuestion
	 */
	public AllowedQuestion getAllowedQuestion() {
		return this.allowedQuestion;
	}



	/**
	 * @param allowedQuestion the allowedQuestion to set
	 */
	public void setAllowedQuestion(AllowedQuestion allowedQuestion) {
		this.allowedQuestion = allowedQuestion;
	}



	/**
	 * @return the existingQuestion
	 */
	public Boolean getExistingQuestion() {
		return this.existingQuestion;
	}

	/**
	 * @param existingQuestion the existingQuestion to set
	 */
	public void setExistingQuestion(Boolean existingQuestion) {
		this.existingQuestion = existingQuestion;
	}

	/**
	 * @return the multipleCardinality
	 */
	public Boolean getMultipleCardinality() {
		return this.multipleCardinality;
	}

	/**
	 * @param multipleCardinality the multipleCardinality to set
	 */
	public void setMultipleCardinality(Boolean multipleCardinality) {
		this.multipleCardinality = multipleCardinality;
	}

	/**
	 * @return the questionNumber
	 */
	public String getQuestionNumber() {
		return this.questionNumber;
	}
	
	/**
	 * @return the questionHelp
	 */
	public String getQuestionHelp() {
		return this.questionHelp;
	}

	/**
	 * @param questionHelp the questionHelp to set
	 */
	public void setQuestionHelp(String questionHelp) {
		this.questionHelp = questionHelp;
	}

	/**
	 * @param questionNumber the questionNumber to set
	 */
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
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
	 * @return the questionQuery
	 */
	public String getQuestionQuery() {
		return this.questionQuery;
	}

	/**
	 * @param questionQuery the questionQuery to set
	 */
	public void setQuestionQuery(String questionQuery) {
		this.questionQuery = questionQuery;
	}

	/**
	 * @return the questionText
	 */
	public String getQuestionText() {
		return this.questionText;
	}

	/**
	 * @param questionText the questionText to set
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * @return the valid
	 */
	public Boolean getValid() {
		return this.valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the required
	 */
	public Boolean getRequired() {
		return this.required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required) {
		this.required = required;
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
	 * @return the allowedAnswerItems
	 */
	public List<AllowedAnswerItem> getAllowedAnswerItems() {
		return this.allowedAnswerItems;
	}

	/**
	 * @param allowedAnswerItems the allowedAnswerItems to set
	 */
	public void setAllowedAnswerItems(
			List<AllowedAnswerItem> allowedAnswerItems) {
		this.allowedAnswerItems = allowedAnswerItems;
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
	 * @return the sortOrder
	 */
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	
	
	
}
