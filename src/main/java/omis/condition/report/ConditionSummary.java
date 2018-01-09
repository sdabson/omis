package omis.condition.report;


/**
 * Condition Summary Implementation.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Jacques
 * @version 0.1.1 (May 21, 2017)
 * @since OMIS 3.0
 */
public class ConditionSummary {
	
	private final Long conditionId;
	private final String conditionTitle;
	private final String conditionClause;
	private final Boolean active;
	private final String category;
	private final Boolean waived;
	
	/**
	 * Instantiates summary of Conditions.
	 * @param conditionId The condition ID.
	 * @param conditionTitle the Title of the Condition Summary.
	 * @param conditionClause the clause of the Condition Summary.
	 * @param active whether this condition summary is within the Imposed
	 * Condition's date range.
	 */
	public ConditionSummary(final Long conditionId, final String conditionTitle,
			final String conditionClause,final Boolean active,
			final String category, final Boolean waived) {
		this.conditionId = conditionId;
		this.conditionTitle = conditionTitle;
		this.conditionClause = conditionClause;
		this.active = active;
		this.category = category;
		this.waived = waived;
	}
	
	/**
	 * Returns the conditionId
	 * @return conditionId - Long
	 */
	public Long getConditionId() {
		return conditionId;
	}


	/**
	 * Returns the conditionTitle
	 * @return conditionTitle - String
	 */
	public String getConditionTitle() {
		return conditionTitle;
	}


	/**
	 * Returns the conditionClause
	 * @return conditionClause - String
	 */
	public String getConditionClause() {
		return conditionClause;
	}


	/**
	 * Returns the active
	 * @return active - Boolean
	 */
	public Boolean getActive() {
		return active;
	}


	/**
	 * Returns the category
	 * @return category - String
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * Returns the waived
	 * @return waived - Boolean
	 */
	public Boolean getWaived() {
		return waived;
	}
	
}
