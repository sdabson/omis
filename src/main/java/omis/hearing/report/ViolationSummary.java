
package omis.hearing.report;

import java.io.Serializable;
import java.util.Date;
import omis.hearing.domain.ResolutionClassificationCategory;
import omis.violationevent.domain.ViolationEventCategory;

/**
 * Violation Summary.
 * 
 *@author Annie Wahl
 *@version 0.1.3 (Jan 22, 2019)
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
	
	private final String disciplinaryCodeValue;
	
	private final String violationEventDetails;
	
	private final Date violationEventDate;
	
	private final String conditionClause;
	
	private final String conditionTitle;
	
	private final String modifiedDisciplinaryCodeValue;
	
	private final String modifiedDisciplinaryCodeDescription;

	private final String modifiedConditionTitle;
	
	private final String modifiedConditionClause;
	
	private final String decisionReason;
	
	private final String decision;
	
	private final String dispositionCategory;
	
	private final Date appealDate;
	
	private final String sanctionDescription;
	
	private final ResolutionClassificationCategory resolutionCategory;
	
	private final String violationDetails;
	
	/**
	 * Constructor to find resolved violations.
	 * @param conditionViolationId - Long
	 * @param disciplinaryCodeViolationId -Long
	 * @param violationEventCategory - Violation Event Category
	 * @param disciplinaryCodeDescription - String
	 * @param disciplinaryCodeValue - String
	 * @param conditionClause - String
	 * @param conditionTitle - String
	 * @param modifiedDisciplinaryCodeValue - String
	 * @param modifiedDisciplinaryCodeDescription - String
	 * @param modifiedConditionTitle - String
	 * @param modifiedConditionClause - String
	 * @param violationEventDetails - String
	 * @param violationEventDate - Date
	 * @param decisionReason - String
	 * @param decision - String
	 * @param dispositionCategory - String
	 * @param appealDate - appeal date
	 * @param sanctionDescription - String
	 * @param resolutionCategory - Resolution Classification Category
	 * @param violationDetails - violation details
	 */
	public ViolationSummary(final Long conditionViolationId,
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String disciplinaryCodeValue,
			final String conditionClause,
			final String conditionTitle,
			final String modifiedDisciplinaryCodeValue,
			final String modifiedDisciplinaryCodeDescription,
			final String modifiedConditionTitle,
			final String modifiedConditionClause,
			final String violationEventDetails,
			final Date violationEventDate,
			final String decisionReason,
			final String decision, final String dispositionCategory,
			final Date appealDate,
			final String sanctionDescription,
			final ResolutionClassificationCategory resolutionCategory,
			final String violationDetails) {
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.disciplinaryCodeValue = disciplinaryCodeValue;
		this.violationEventDate = violationEventDate;
		this.violationEventDetails = violationEventDetails;
		this.conditionClause = conditionClause;
		this.conditionTitle = conditionTitle;
		this.decisionReason = decisionReason;
		this.decision = decision;
		this.dispositionCategory = dispositionCategory;
		this.appealDate = appealDate;
		this.sanctionDescription = sanctionDescription;
		this.resolutionCategory = resolutionCategory;
		this.infractionId = null;
		this.violationDetails = violationDetails;
		this.modifiedConditionClause = modifiedConditionClause;
		this.modifiedConditionTitle = modifiedConditionTitle;
		this.modifiedDisciplinaryCodeValue = modifiedDisciplinaryCodeValue;
		this.modifiedDisciplinaryCodeDescription =
				modifiedDisciplinaryCodeDescription;
	}
	
	/**
	 * Constructor for Resolved disciplinary code violation summaries.
	 * 
	 * @param infractionId - Long
	 * @param disciplinaryCodeViolationId - Long
	 * @param violationEventCategory - Violation Event Category
	 * @param disciplinaryCodeDescription - String
	 * @param disciplinaryCodeValue - String
	 * @param modifiedDisciplinaryCodeValue - String
	 * @param modifiedDisciplinaryCodeDescription - String
	 * @param violationEventDetails - String
	 * @param violationEventDate - Date
	 * @param decisionReason - String
	 * @param decision - String
	 * @param dispositionCategory - String
	 * @param appealDate - appeal date
	 * @param sanctionDescription - String
	 * @param resolutionCategory - Resolution Classification Category
	 * @param violationDetails - violation details
	 */
	public ViolationSummary(
			final Long infractionId,
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String disciplinaryCodeValue,
			final String modifiedDisciplinaryCodeValue,
			final String modifiedDisciplinaryCodeDescription,
			final String violationEventDetails,
			final Date violationEventDate, final String decisionReason,
			final String decision, final String dispositionCategory,
			final Date appealDate,
			final String sanctionDescription,
			final ResolutionClassificationCategory resolutionCategory,
			final String violationDetails) {
		this.infractionId = infractionId;
		this.conditionViolationId = null;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.disciplinaryCodeValue = disciplinaryCodeValue;
		this.violationEventDate = violationEventDate;
		this.violationEventDetails = violationEventDetails;
		this.modifiedConditionClause = null;
		this.modifiedConditionTitle = null;
		this.modifiedDisciplinaryCodeValue = modifiedDisciplinaryCodeValue;
		this.modifiedDisciplinaryCodeDescription =
				modifiedDisciplinaryCodeDescription;
		this.conditionClause = null;
		this.conditionTitle = null;
		this.decisionReason = decisionReason;
		this.decision = decision;
		this.dispositionCategory = dispositionCategory;
		this.appealDate = appealDate;
		this.sanctionDescription = sanctionDescription;
		this.resolutionCategory = resolutionCategory;
		this.violationDetails = violationDetails;
	}
	
	/**
	 * Constructor for Resolved Condition Violation Summaries.
	 * 
	 * @param infractionId - Long
	 * @param violationEventCategory - Violation Event Category
	 * @param conditionViolationId - Long
	 * @param conditionClause - String
	 * @param conditionTitle - String
	 * @param modifiedConditionTitle - String
	 * @param modifiedConditionClause - String
	 * @param violationEventDetails - String
	 * @param violationEventDate - Date
	 * @param decisionReason - String
	 * @param decision - String
	 * @param dispositionCategory - String
	 * @param appealDate - appeal date
	 * @param sanctionDescription - String
	 * @param resolutionCategory - Resolution Classification Category
	 * @param violationDetails - violation details
	 */
	public ViolationSummary(
			final Long infractionId,
			final ViolationEventCategory violationEventCategory,
			final Long conditionViolationId,
			final String conditionClause,
			final String conditionTitle,
			final String modifiedConditionTitle,
			final String modifiedConditionClause,
			final String violationEventDetails, final Date violationEventDate,
			final String decisionReason,
			final String decision, final String dispositionCategory,
			final Date appealDate,
			final String sanctionDescription,
			final ResolutionClassificationCategory resolutionCategory,
			final String violationDetails) {
		this.infractionId = infractionId;
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = null;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = null;
		this.disciplinaryCodeValue = null;
		this.violationEventDate = violationEventDate;
		this.violationEventDetails = violationEventDetails;
		this.conditionClause = conditionClause;
		this.conditionTitle = conditionTitle;
		this.decisionReason = decisionReason;
		this.decision = decision;
		this.dispositionCategory = dispositionCategory;
		this.appealDate = appealDate;
		this.sanctionDescription = sanctionDescription;
		this.resolutionCategory = resolutionCategory;
		this.violationDetails = violationDetails;
		this.modifiedConditionClause = modifiedConditionClause;
		this.modifiedConditionTitle = modifiedConditionTitle;
		this.modifiedDisciplinaryCodeValue = null;
		this.modifiedDisciplinaryCodeDescription = null;
	}
	
	/**
	 * Constructor to find unresolved DisciplinaryCodeViolations.
	 * @param disciplinaryCodeViolationId - Long
	 * @param violationEventCategory - Violation Event Category
	 * @param disciplinaryCodeDescription - String
	 * @param disciplinaryCodeValue - String
	 * @param violationEventDetails - String
	 * @param violationEventDate - Date
	 * @param violationDetails - violation details
	 */
	public ViolationSummary(
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String disciplinaryCodeValue,
			final String violationEventDetails,
			final Date violationEventDate,
			final String violationDetails) {
		this.infractionId = null;
		this.conditionViolationId = null;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.disciplinaryCodeValue = disciplinaryCodeValue;
		this.modifiedDisciplinaryCodeDescription = null;
		this.modifiedDisciplinaryCodeValue = null;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.modifiedConditionClause = null;
		this.modifiedConditionTitle = null;
		this.conditionClause = null;
		this.conditionTitle = null;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.appealDate = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
		this.violationDetails = violationDetails;
	}
	
	/**
	 * Constructor to find unresolved ConditionViolations.
	 * @param violationEventCategory - Violation Event Category
	 * @param conditionViolationId - Long
	 * @param conditionClause - String
	 * @param conditionTitle - String
	 * @param violationEventDetails - String
	 * @param violationEventDate - Date
	 * @param violationDetails - violation details
	 */
	public ViolationSummary(
			final ViolationEventCategory violationEventCategory,
			final Long conditionViolationId,
			final String conditionClause,
			final String conditionTitle,
			final String violationEventDetails, final Date violationEventDate,
			final String violationDetails) {
		this.infractionId = null;
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = null;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = null;
		this.disciplinaryCodeValue = null;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.conditionClause = conditionClause;
		this.conditionTitle = conditionTitle;
		this.modifiedConditionClause = null;
		this.modifiedConditionTitle = null;
		this.modifiedDisciplinaryCodeValue = null;
		this.modifiedDisciplinaryCodeDescription = null;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.appealDate = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
		this.violationDetails = violationDetails;
	}
	
	/**
	 * Constructor to find all unresolved violations.
	 * @param conditionViolationId - Long
	 * @param disciplinaryCodeViolationId - Long
	 * @param violationEventCategory - Violation Event Category
	 * @param disciplinaryCodeDescription - String
	 * @param disciplinaryCodeValue - String
	 * @param conditionClause - String
	 * @param conditionTitle - String
	 * @param violationEventDetails - String
	 * @param violationEventDate - Date
	 * @param violationDetails - violation details
	 */
	public ViolationSummary(final Long conditionViolationId,
			final Long disciplinaryCodeViolationId,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String disciplinaryCodeValue,
			final String conditionClause,
			final String conditionTitle,
			final String violationEventDetails, final Date violationEventDate,
			final String violationDetails) {
		this.infractionId = null;
		this.conditionViolationId = conditionViolationId;
		this.disciplinaryCodeViolationId = disciplinaryCodeViolationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.disciplinaryCodeValue = disciplinaryCodeValue;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.conditionClause = conditionClause;
		this.conditionTitle = conditionTitle;
		this.modifiedConditionClause = null;
		this.modifiedConditionTitle = null;
		this.modifiedDisciplinaryCodeValue = null;
		this.modifiedDisciplinaryCodeDescription = null;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.appealDate = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
		this.violationDetails = violationDetails;
	}
	
	/**
	 * Constructor to summarize a single
	 *  DisciplinaryCodeViolation/ConditionViolation.
	 * @param violationId - violation id
	 * @param violationEventDetails - String
	 * @param violationEventCategory - Violation Event Category
	 * @param disciplinaryCodeDescription - String
	 * @param disciplinaryCodeValue - String
	 * @param conditionClause - String
	 * @param conditionTitle - String
	 * @param violationEventDate - Date
	 * @param violationDetails - violation details
	 */
	public ViolationSummary(
			final Long violationId,
			final String violationEventDetails,
			final ViolationEventCategory violationEventCategory,
			final String disciplinaryCodeDescription,
			final String disciplinaryCodeValue,
			final String conditionClause, 
			final String conditionTitle,
			final Date violationEventDate,
			final String violationDetails) {
		this.infractionId = null;
		this.conditionViolationId = violationId;
		this.disciplinaryCodeViolationId = violationId;
		this.violationEventCategory = violationEventCategory;
		this.disciplinaryCodeDescription = disciplinaryCodeDescription;
		this.disciplinaryCodeValue = disciplinaryCodeValue;
		this.violationEventDetails = violationEventDetails;
		this.violationEventDate = violationEventDate;
		this.conditionClause = conditionClause;
		this.conditionTitle = conditionTitle;
		this.modifiedConditionClause = null;
		this.modifiedConditionTitle = null;
		this.modifiedDisciplinaryCodeValue = null;
		this.modifiedDisciplinaryCodeDescription = null;
		this.decisionReason = null;
		this.decision = null;
		this.dispositionCategory = null;
		this.appealDate = null;
		this.sanctionDescription = null;
		this.resolutionCategory = null;
		this.violationDetails = violationDetails;
	}
	
	/**
	 * Returns the infractionId.
	 * @return infractionId - Long
	 */
	public Long getInfractionId() {
		return infractionId;
	}

	/**
	 * Returns the conditionViolationId.
	 * @return conditionViolationId - Long
	 */
	public Long getConditionViolationId() {
		return conditionViolationId;
	}

	/**
	 * Returns the disciplinaryCodeViolationId.
	 * @return disciplinaryCodeViolationId - Long
	 */
	public Long getDisciplinaryCodeViolationId() {
		return disciplinaryCodeViolationId;
	}

	/**
	 * Returns the violationEventCategory.
	 * @return violationEventCategory - ViolationEventCategory
	 */
	public ViolationEventCategory getViolationEventCategory() {
		return violationEventCategory;
	}

	/**
	 * Returns the disciplinaryCodeDescription.
	 * @return disciplinaryCodeDescription - String
	 */
	public String getDisciplinaryCodeDescription() {
		return disciplinaryCodeDescription;
	}

	/**
	 * Returns the conditionClause.
	 * @return conditionClause - String
	 */
	public String getConditionClause() {
		return conditionClause;
	}

	/**
	 * Returns the decisionReason.
	 * @return decisionReason - String
	 */
	public String getDecisionReason() {
		return decisionReason;
	}

	/**
	 * Returns the decision.
	 * @return decision - String
	 */
	public String getDecision() {
		return decision;
	}

	/**
	 * Returns the dispositionCategory.
	 * @return dispositionCategory - DispositionCategory
	 */
	public String getDispositionCategory() {
		return dispositionCategory;
	}

	/**
	 * Returns the sanctionDescription.
	 * @return sanctionDescription - String
	 */
	public String getSanctionDescription() {
		return sanctionDescription;
	}

	/**
	 * Returns the violationEventDetails.
	 * @return violationEventDetails - String
	 */
	public String getViolationEventDetails() {
		return violationEventDetails;
	}

	/**
	 * Returns the resolutionCategory.
	 * @return resolutionCategory - ResolutionClassificationCategory
	 */
	public ResolutionClassificationCategory getResolutionCategory() {
		return resolutionCategory;
	}

	/**
	 * Returns the violationEventDate.
	 * @return violationEventDate - Date
	 */
	public Date getViolationEventDate() {
		return violationEventDate;
	}

	/**
	 * Returns the disciplinaryCodeValue.
	 * @return disciplinaryCodeValue - String
	 */
	public String getDisciplinaryCodeValue() {
		return this.disciplinaryCodeValue;
	}

	/**
	 * Returns the conditionTitle.
	 * @return conditionTitle - String
	 */
	public String getConditionTitle() {
		return this.conditionTitle;
	}

	/**
	 * Returns the appealDate.
	 * @return appealDate - Date
	 */
	public Date getAppealDate() {
		return this.appealDate;
	}

	/**
	 * Returns the violation details.
	 * @return the violationDetails
	 */
	public String getViolationDetails() {
		return this.violationDetails;
	}

	/**
	 * Returns this modified disciplinary code.
	 * @return modifiedDisciplinaryCode - modified disciplinary code
	 */
	public String getModifiedDisciplinaryCodeDescription() {
		return this.modifiedDisciplinaryCodeDescription;
	}

	/**
	 * Returns the modified condition clause.
	 * @return modifiedConditionClause - modified condition clause
	 */
	public String getModifiedConditionClause() {
		return this.modifiedConditionClause;
	}

	/**
	 * Returns the Modified Disciplinary Code Value.
	 *
	 * @return modifiedDisciplinaryCodeValue
	 */
	public String getModifiedDisciplinaryCodeValue() {
		return this.modifiedDisciplinaryCodeValue;
	}

	/**
	 * Returns the Modified Condition Title.
	 *
	 * @return modifiedConditionTitle
	 */
	public String getModifiedConditionTitle() {
		return this.modifiedConditionTitle;
	}

}
