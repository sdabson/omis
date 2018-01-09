package omis.courtcasecondition.report;

import java.util.Date;
import java.util.List;

import omis.condition.report.AgreementSummary;
import omis.condition.report.ConditionSummary;

/** Agreement Summary.
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Aug 30, 2017)
 * @since OMIS 3.0 */
public class CourtCaseAgreementSummary extends AgreementSummary{
	
	private final Long courtCaseAgreementId;
	private final Long docketId;
	private final String agreementCategoryName;
	private final String docketValue;

	
	/**
	 * Instantiates summary of the Court Case Agreement
	 * @param agreementId  the agreement Id which is relevant to the summary.
	 * @param docketId  the docket Id which is relevant to the summary.
	 * @param agreementCategoryName  the name of the agreementCategory obj.
	 * @param agreementStartDate the start Date of the Agreement 
	 * @param docketValue  the value of the docket.
	 * @param agreementEndDate the end Date of the Agreement 
	 * @param conditionSummaries the conditionSummaries relevant to the 
	 * courtcaseagreement
	 */
	public CourtCaseAgreementSummary(final Long agreementId,
			final Long courtCaseAgreementId,
			final Long docketId, 
			final String agreementCategoryName, final Date agreementStartDate,
			final String docketValue, final Date agreementEndDate, 
			final List<ConditionSummary> conditionSummaries) {
		super(agreementId, agreementStartDate, 
				agreementEndDate);
		this.courtCaseAgreementId = courtCaseAgreementId;
		this.docketId = docketId;
		this.agreementCategoryName = agreementCategoryName;
		this.docketValue = docketValue;
	}
	
	/**
	 * Instantiates summary of the Court Case Agreement
	 * @param agreementId  the agreement Id which is relevant to the summary.
	 * @param docketId  the docket Id which is relevant to the summary.
	 * @param agreementCategoryName  the name of the agreementCategory obj.
	 * @param agreementStartDate the start Date of the Agreement 
	 * @param docketValue  the value of the docket.
	 * @param agreementEndDate the end Date of the Agreement 
	 */
	public CourtCaseAgreementSummary(final Long agreementId, 
			final Long courtCaseAgreementId, final Long docketId, 
			final String agreementCategoryName, final Date agreementStartDate,
			final String docketValue, final Date agreementEndDate) {
		super(agreementId, agreementStartDate, 
				agreementEndDate);
		this.courtCaseAgreementId = courtCaseAgreementId;
		this.docketId = docketId;
		this.agreementCategoryName = agreementCategoryName;
		this.docketValue = docketValue;
	}


	/**
	 * @return the docket value
	 */
	public String getDocketValue() {
		return this.docketValue;
	}

	/**
	 * @return the docket Id
	 */
	public Long getDocketId() {
		return this.docketId;
	}

	/**
	 * @return the agreement Category Name
	 */
	public String getAgreementCategoryName() {
		return this.agreementCategoryName;
	}
	
	/**
	 * Returns the courtCaseAgreementId
	 * @return courtCaseAgreementId - Long
	 */
	public Long getCourtCaseAgreementId() {
		return this.courtCaseAgreementId;
	}
	
}
