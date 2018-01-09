package omis.hearing.report;

import java.io.Serializable;
import java.util.Date;

import omis.hearing.domain.ResolutionClassificationCategory;
import omis.violationevent.domain.ViolationEventCategory;

/**
 * ViolationSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long infractionId;
	
	private final Long conditionViolationId;
	
	private final Long disciplinaryCodeViolationId;
	
	private final ViolationEventCategory violationEventCategory;
	
	private final String disciplinaryCodeDescription;
	
	private final String violationEventDetails;
	
	private final Date violationEventDate;
	
	private final String conditionClause;
	
	private final String decisionReason;
	
	private final String decision;
	
	private final String dispositionCategory;
	
	private final String sanctionDescription;
	
	private final ResolutionClassificationCategory resolutionCategory;

	/**
	 * Constructor to find resolved violations
	 * @param conditionViolationId
	 * @param disciplinaryCodeViolationId
	 * @param violationEventCategory
	 * @param disciplinaryCodeDescription
	 * @param conditionClause
	 * @param decisionReason
	 * @param decision
	 * @param dispositionCategory
	 * @param sanctionDescription
	 */
	public ViolationSummary(final Long conditionViolationId,
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String conditionClause,
			final String violationEventDetails,
			final Date violationEventDate,
			final String decisionReason,
			final String decision, final String dispositionCategory,
			final String sanctionDescription,
			final ResolutionClassificationCategory resolutionCategory) {
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.violationEventDate = violationEventDate;
		this.violationEventDetails = violationEventDetails;
		this.conditionClause = conditionClause;
		this.decisionReason = decisionReason;
		this.decision = decision;
		this.dispositionCategory = dispositionCategory;
		this.sanctionDescription = sanctionDescription;
		this.resolutionCategory = resolutionCategory;
		this.infractionId = null;
	}
	
	public ViolationSummary(
			final Long infractionId,
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String violationEventDetails,
			final Date violationEventDate, final String decisionReason,
			final String decision, final String dispositionCategory,
			final String sanctionDescription,
			final ResolutionClassificationCategory resolutionCategory) {
		this.infractionId = infractionId;
		this.conditionViolationId = null;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.violationEventDate = violationEventDate;
		this.violationEventDetails = violationEventDetails;
		this.conditionClause = null;
		this.decisionReason = decisionReason;
		this.decision = decision;
		this.dispositionCategory = dispositionCategory;
		this.sanctionDescription = sanctionDescription;
		this.resolutionCategory = resolutionCategory;
	}
	
	public ViolationSummary(
			final Long infractionId,
			final ViolationEventCategory violationEventCategory,
			final Long conditionViolationId,
			final String conditionClause,
			final String violationEventDetails, final Date violationEventDate,
			final String decisionReason,
			final String decision, final String dispositionCategory,
			final String sanctionDescription,
			final ResolutionClassificationCategory resolutionCategory) {
		this.infractionId = infractionId;
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = null;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = null;
		this.violationEventDate = violationEventDate;
		this.violationEventDetails = violationEventDetails;
		this.conditionClause = conditionClause;
		this.decisionReason = decisionReason;
		this.decision = decision;
		this.dispositionCategory = dispositionCategory;
		this.sanctionDescription = sanctionDescription;
		this.resolutionCategory = resolutionCategory;
	}
	
	/**
	 * Constructor to find unresolved DisciplinaryCodeViolations
	 * @param disciplinaryCodeViolationId
	 * @param violationEventCategory
	 * @param disciplinaryCodeDescription
	 * @param violationEventDetails
	 */
	public ViolationSummary(
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String violationEventDetails,
			final Date violationEventDate) {
		this.infractionId = null;
		this.conditionViolationId = null;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.conditionClause = null;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
	}
	
	/**
	 * Constructor to find unresolved ConditionViolations
	 * @param violationEventCategory
	 * @param conditionViolationId
	 * @param disciplinaryCodeDescription
	 * @param violationEventDetails
	 */
	public ViolationSummary(
			final ViolationEventCategory violationEventCategory,
			final Long conditionViolationId,
			final String conditionClause,
			final String violationEventDetails, final Date violationEventDate) {
		this.infractionId = null;
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = null;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = null;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.conditionClause = conditionClause;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
	}
	
	/**
	 * Constructor to find all unresolved violations
	 * @param conditionViolationId
	 * @param disciplinaryCodeViolationId
	 * @param violationEventCategory
	 * @param disciplinaryCodeDescription
	 * @param conditionClause
	 * @param violationEventDetails
	 */
	public ViolationSummary(final Long conditionViolationId,
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String conditionClause,
			final String violationEventDetails, final Date violationEventDate) {
		this.infractionId = null;
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.conditionClause = conditionClause;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
	}
	
	/**
	 * Constructor to summarize a single
	 *  DisciplinaryCodeViolation/ConditionViolation
	 * @param violationEventDetails
	 * @param disciplinaryCodeDescription
	 * @param conditionClause
	 */
	public ViolationSummary(final String violationEventDetails,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String conditionClause, final Date violationEventDate){
		this.infractionId = null;
		this.conditionViolationId = null;
		this.disciplinaryCodeViolationId = null;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.conditionClause = conditionClause;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
	}
	
	/**
	 * Returns the infractionId
	 * @return infractionId - Long
	 */
	public Long getInfractionId() {
		return infractionId;
	}

	/**
	 * Returns the conditionViolationId
	 * @return conditionViolationId - Long
	 */
	public Long getConditionViolationId() {
		return conditionViolationId;
	}

	/**
	 * Returns the disciplinaryCodeViolationId
	 * @return disciplinaryCodeViolationId - Long
	 */
	public Long getDisciplinaryCodeViolationId() {
		return disciplinaryCodeViolationId;
	}

	/**
	 * Returns the violationEventCategory
	 * @return violationEventCategory - ViolationEventCategory
	 */
	public ViolationEventCategory getViolationEventCategory() {
		return violationEventCategory;
	}

	/**
	 * Returns the disciplinaryCodeDescription
	 * @return disciplinaryCodeDescription - String
	 */
	public String getDisciplinaryCodeDescription() {
		return disciplinaryCodeDescription;
	}

	/**
	 * Returns the conditionClause
	 * @return conditionClause - String
	 */
	public String getConditionClause() {
		return conditionClause;
	}

	/**
	 * Returns the decisionReason
	 * @return decisionReason - String
	 */
	public String getDecisionReason() {
		return decisionReason;
	}

	/**
	 * Returns the decision
	 * @return decision - String
	 */
	public String getDecision() {
		return decision;
	}

	/**
	 * Returns the dispositionCategory
	 * @return dispositionCategory - DispositionCategory
	 */
	public String getDispositionCategory() {
		return dispositionCategory;
	}

	/**
	 * Returns the sanctionDescription
	 * @return sanctionDescription - String
	 */
	public String getSanctionDescription() {
		return sanctionDescription;
	}

	/**
	 * Returns the violationEventDetails
	 * @return violationEventDetails - String
	 */
	public String getViolationEventDetails() {
		return violationEventDetails;
	}

	/**
	 * Returns the resolutionCategory
	 * @return resolutionCategory - ResolutionClassificationCategory
	 */
	public ResolutionClassificationCategory getResolutionCategory() {
		return resolutionCategory;
	}

	/**
	 * Returns the violationEventDate
	 * @return violationEventDate - Date
	 */
	public Date getViolationEventDate() {
		return violationEventDate;
	}
	
	
	
}
