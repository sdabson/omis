package omis.questionnaire.web.form;


import omis.user.domain.UserAccount;

/**
 * QuestionnaireForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 5, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireForm {
	
	private UserAccount assessor;
	
	private String comments;
	
	/**
	 * Default Constructor for QuestionnaireForm
	 */
	public QuestionnaireForm() {
	}


	/**
	 * @return the assessor
	 */
	public UserAccount getAssessor() {
		return this.assessor;
	}

	/**
	 * @param assessor the assessor to set
	 */
	public void setAssessor(UserAccount assessor) {
		this.assessor = assessor;
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
	
	
	
}
