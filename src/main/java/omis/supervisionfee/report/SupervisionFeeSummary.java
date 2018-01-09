package omis.supervisionfee.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.MonthlySupervisionFeeAuthorityCategory;

/**
 * Supervision fee summary.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 8, 2014)
 * @since OMIS 3.0
 */
public class SupervisionFeeSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long monthlySupervisionFeeId;

	private final BigDecimal fee;

	private final Date startDate;

	private final Date endDate;
	
	private MonthlySupervisionFeeAuthorityCategory authorityCategory;
	
	private final String comment;
	
	private List<SupervisionFeeRequirementSummary> supervisionFeeRequirements;
	
	private boolean active;
	/**
	 * Constructor.
	 * 
	 * @param monthlySupervisionFeeId monthly supervision fee id
	 * @param fee fee
	 * @param startDate start date
	 * @param endDate end date
	 * @param authorityCategory supervision fee authority
	 * @param comment comment
	 */
	public SupervisionFeeSummary(
					final Long monthlySupervisionFeeId, 
					final BigDecimal fee, final Date startDate, 
					final Date endDate, 
					final MonthlySupervisionFeeAuthorityCategory 
					authorityCategory, final String comment) {

		this.monthlySupervisionFeeId = monthlySupervisionFeeId;
		this.fee = fee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.authorityCategory = authorityCategory;
		this.comment = comment;
	}	
	
	/**
	 * Constructor.
	 * 
	 * @param monthlySupervisionFee monthly supervision fee	 
	 * @param supervisionFeeRequirements supervision fee requirements
	 * @param effectiveDate - effective date.
	 */
	public SupervisionFeeSummary(
					final MonthlySupervisionFee monthlySupervisionFee,
					final List<SupervisionFeeRequirementSummary> 
					supervisionFeeRequirements, final Date effectiveDate) {
		this.monthlySupervisionFeeId = monthlySupervisionFee.getId();
		this.fee = monthlySupervisionFee.getFee();
		this.startDate = monthlySupervisionFee.getDateRange().getStartDate();
		this.endDate = monthlySupervisionFee.getDateRange().getEndDate();
		this.authorityCategory = monthlySupervisionFee.getAuthorityCategory();
		this.comment = monthlySupervisionFee.getComment();
		this.supervisionFeeRequirements = supervisionFeeRequirements;
		long activeFeeRequirements = 
				supervisionFeeRequirements.stream().filter(
						supervisionFeeRequirement -> 
							supervisionFeeRequirement.getActive()).count();
		this.active = (activeFeeRequirements <= 0 && (new DateRange(
				this.startDate, this.endDate).isActive(effectiveDate)));
	}
	
	/**
	 * Gets the monthly supervision fee id.
	 * 
	 * @return the monthlySupervisionFeeId
	 */
	public Long getMonthlySupervisionFeeId() {
		return this.monthlySupervisionFeeId;
	}

	/**
	 * Gets the fee.
	 * 
	 * @return the fee
	 */
	public BigDecimal getFee() {
		return this.fee;
	}

	/**
	 * Gets the start date.
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Gets end date.
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 *Gets the monthly supervision fee category.
	 *
	 * @return the authorityCategory
	 */
	public MonthlySupervisionFeeAuthorityCategory getAuthorityCategory() {
		return this.authorityCategory;
	}

	/**
	 * Gets the supervision fee requirements comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}
	
	/** Gets active.
	 * @return active. */
	public boolean getActive() {
		return this.active;
	}
	
	/**
	 * Gets the supervision fee requirements.
	 * 
	 * @return the supervisionFeeRequirements
	 */
	public List<SupervisionFeeRequirementSummary> 
					getSupervisionFeeRequirements() {
		return this.supervisionFeeRequirements;
	}

	/**
	 * Sets the supervision fee requirements.
	 * 
	 * @param supervisionFeeRequirements supervision fee requirements
	 */
	public void setSupervisionFeeRequirements(
					final List<SupervisionFeeRequirementSummary> 
						supervisionFeeRequirements) {
		this.supervisionFeeRequirements = supervisionFeeRequirements;
	}		
}