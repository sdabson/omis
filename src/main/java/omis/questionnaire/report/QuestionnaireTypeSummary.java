package omis.questionnaire.report;

import java.io.Serializable;
import java.util.Date;

/**
 * QuestionnaireTypeSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 17, 2016)
 *@since OMIS 3.0
 *
 */
public class QuestionnaireTypeSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long questionnaireTypeId;
	
	private final String categoryDescription;
	
	private final String questionnaireTypeTitle;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final Boolean valid;
	
	private final Integer cycle;

	/**
	 * @param questionnaireTypeId
	 * @param categoryDescription
	 * @param questionnaireTypeTitle
	 * @param startDate
	 * @param endDate
	 */
	public QuestionnaireTypeSummary(final Long questionnaireTypeId,
			final String categoryDescription,
			final String questionnaireTypeTitle,
			final Date startDate, final Date endDate, final Boolean valid,
			final Integer cycle) {
		this.questionnaireTypeId = questionnaireTypeId;
		this.categoryDescription = categoryDescription;
		this.questionnaireTypeTitle = questionnaireTypeTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.valid = valid;
		this.cycle = cycle;
	}

	/**
	 * @return the questionnaireTypeId
	 */
	public Long getQuestionnaireTypeId() {
		return this.questionnaireTypeId;
	}

	/**
	 * @return the categoryDescription
	 */
	public String getCategoryDescription() {
		return this.categoryDescription;
	}

	/**
	 * @return the questionnaireTypeTitle
	 */
	public String getQuestionnaireTypeTitle() {
		return this.questionnaireTypeTitle;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @return the valid
	 */
	public Boolean getValid() {
		return this.valid;
	}

	/**
	 * @return the cycle
	 */
	public Integer getCycle() {
		return cycle;
	}
	
	
	
}
