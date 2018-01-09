package omis.questionnaire.web.form;

import java.util.Date;

import omis.questionnaire.domain.QuestionnaireCategory;

/**
 * QuestionnaireTypeForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 17, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireTypeForm {
	
	private String title;
	
	private QuestionnaireCategory category;
	
	private String shortTitle;
	
	private Integer cycle;
	
	private Date startDate;
	
	private Date endDate;
	
	private Boolean valid;
	
	private String questionnaireHelp;
	
	public QuestionnaireTypeForm(){
		
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the questionnaireCategory
	 */
	public QuestionnaireCategory getCategory() {
		return this.category;
	}

	/**
	 * @param questionnaireCategory the questionnaireCategory to set
	 */
	public void setCategory(QuestionnaireCategory category) {
		this.category = category;
	}

	/**
	 * @return the shortTitle
	 */
	public String getShortTitle() {
		return this.shortTitle;
	}

	/**
	 * @param shortTitle the shortTitle to set
	 */
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	/**
	 * @return the cycle
	 */
	public Integer getCycle() {
		return this.cycle;
	}

	/**
	 * @param cycle the cycle to set
	 */
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the questionnaireHelp
	 */
	public String getQuestionnaireHelp() {
		return this.questionnaireHelp;
	}

	/**
	 * @param questionnaireHelp the questionnaireHelp to set
	 */
	public void setQuestionnaireHelp(String questionnaireHelp) {
		this.questionnaireHelp = questionnaireHelp;
	}
	
	
	
	
}

