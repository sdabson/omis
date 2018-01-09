package omis.presentenceinvestigation.domain.component;

import java.io.Serializable;

/**
 * DefendantsStatement.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class DefendantsStatement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chargeReason;
	
	private String involvementReason;
	
	private String courtRecommendation;
	
	/**
	 * Default Constructor for DefendantsStatement
	 */
	public DefendantsStatement() {
	}
	
	/**
	 * Constructor for DefendantsStatement
	 * @param chargeReason - String
	 * @param involvementReason - String
	 * @param courtRecommendation - String
	 */
	public DefendantsStatement(final String chargeReason,
			final String involvementReason,
			final String courtRecommendation) {
		this.chargeReason = chargeReason;
		this.involvementReason = involvementReason;
		this.courtRecommendation = courtRecommendation;
	}

	/**
	 * Returns the chargeReason
	 * @return chargeReason - String
	 */
	public String getChargeReason() {
		return chargeReason;
	}

	/**
	 * Sets the chargeReason
	 * @param chargeReason - String
	 */
	public void setChargeReason(final String chargeReason) {
		this.chargeReason = chargeReason;
	}

	/**
	 * Returns the involvementReason
	 * @return involvementReason - String
	 */
	public String getInvolvementReason() {
		return involvementReason;
	}

	/**
	 * Sets the involvementReason
	 * @param involvementReason - String
	 */
	public void setInvolvementReason(final String involvementReason) {
		this.involvementReason = involvementReason;
	}

	/**
	 * Returns the courtRecommendation
	 * @return courtRecommendation - String
	 */
	public String getCourtRecommendation() {
		return courtRecommendation;
	}

	/**
	 * Sets the courtRecommendation
	 * @param courtRecommendation - String
	 */
	public void setCourtRecommendation(final String courtRecommendation) {
		this.courtRecommendation = courtRecommendation;
	}
	
	
	

}
