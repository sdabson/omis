package omis.questionnaire.web.form;

import java.util.List;

/**
 * QuestionAnswerForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 17, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionAnswerEditForm {
	
	private List<QuestionAnswerEditItem> questionAnswerEditItems;
	
	/**
	 * 
	 */
	public QuestionAnswerEditForm() {
	}

	/**
	 * @return the questionAnswerEditItems
	 */
	public List<QuestionAnswerEditItem> getQuestionAnswerEditItems() {
		return this.questionAnswerEditItems;
	}

	/**
	 * @param questionAnswerEditItems the questionAnswerEditItems to set
	 */
	public void setQuestionAnswerEditItems(
			List<QuestionAnswerEditItem> questionAnswerEditItems) {
		this.questionAnswerEditItems = questionAnswerEditItems;
	}
	
	
	
}
