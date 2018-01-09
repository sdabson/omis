package omis.questionnaire.report;

import java.io.Serializable;
import java.util.Date;

/**
 * AdministeredQuestionnaireSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2016)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long questionnaireId;
	
	private final String assessorFirstName;
	
	private final String assessorLastName;
	
	private final String helpDescription;
	
	private final String questionnaireTitle;
	
	private final String comments;
	
	private final Boolean draft;
	
	private final Date date;

	/**
	 * @param questionnaireId
	 * @param assessorFirstName
	 * @param assessorLastName
	 * @param helpDescription
	 * @param questionnaireTitle
	 * @param comments
	 * @param draft
	 * @param date
	 */
	public AdministeredQuestionnaireSummary(final Long questionnaireId, 
			final String assessorFirstName, final String assessorLastName,
			final String helpDescription, final String questionnaireTitle, 
			final String comments, final Boolean draft, final Date date) {
		this.questionnaireId = questionnaireId;
		this.assessorFirstName = assessorFirstName;
		this.assessorLastName = assessorLastName;
		this.helpDescription = helpDescription;
		this.questionnaireTitle = questionnaireTitle;
		this.comments = comments;
		this.draft = draft;
		this.date = date;
	}

	/**
	 * @return the questionnaireId
	 */
	public Long getQuestionnaireId() {
		return this.questionnaireId;
	}

	/**
	 * @return the assessorFirstName
	 */
	public String getAssessorFirstName() {
		return this.assessorFirstName;
	}

	/**
	 * @return the assessorLastName
	 */
	public String getAssessorLastName() {
		return this.assessorLastName;
	}

	/**
	 * @return the helpDescription
	 */
	public String getHelpDescription() {
		return this.helpDescription;
	}

	/**
	 * @return the questionnaireTitle
	 */
	public String getQuestionnaireTitle() {
		return this.questionnaireTitle;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @return the draft
	 */
	public Boolean getDraft() {
		return this.draft;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}
	
	
	
}
