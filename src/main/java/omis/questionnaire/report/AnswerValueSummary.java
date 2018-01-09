package omis.questionnaire.report;

import java.io.Serializable;

/**
 * AnswerValueSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2016)
 *@since OMIS 3.0
 *
 */
public class AnswerValueSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final Long answerValueId;
	
	private final Long allowedAnswerId;
	
	private final String description;
	
	private final Short sortOrder;
	
	private final String value;

	/**
	 * @param answerValueId
	 * @param allowedAnswerId
	 * @param description
	 * @param sortOrder
	 * @param valid
	 */
	public AnswerValueSummary(final Long answerValueId, 
			final Long allowedAnswerId, final String description, 
			final Short sortOrder, final String value){
		this.answerValueId = answerValueId;
		this.allowedAnswerId = allowedAnswerId;
		this.description = description;
		this.sortOrder = sortOrder;
		this.value = value;
	}

	/**
	 * @return the answerValueId
	 */
	public Long getAnswerValueId() {
		return this.answerValueId;
	}

	/**
	 * @return the allowedAnswerId
	 */
	public Long getAllowedAnswerId() {
		return this.allowedAnswerId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return the sortOrder
	 */
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}
	
	

}
