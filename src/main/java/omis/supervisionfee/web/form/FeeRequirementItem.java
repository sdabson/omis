package omis.supervisionfee.web.form;

import java.math.BigDecimal;
import java.util.Date;

import omis.court.domain.Court;
import omis.person.domain.Person;
import omis.supervisionfee.domain.SupervisionFeeRequirement;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;

/**
 * Supervision fee requirement form.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 9, 2014)
 * @since OMIS 3.0
 */
public class FeeRequirementItem {

	private SupervisionFeeRequirementItemOperation operation;
	
	private BigDecimal fee;
	
	private Date startDate;
	
	private Date endDate;
	
	private Person officer;
	
	private String comments;
	
	private SupervisionFeeRequirementReason reason;
	
	private SupervisionFeeRequirement feeRequirement;
	
	private Long feeRequirementId;
	
	private SupervisionFeeRequirementItemAuthority itemAuthority;
	
	private Court court;
		
	/**
	 * Instantiates a default instance of the supervision fee requirement form.
	 */
	public FeeRequirementItem() {
		//Default constructor.
	}
	
	/**
	 * Return the operation of the supervision fee requirement item.
	 * 
	 * @return the operation
	 */
	public SupervisionFeeRequirementItemOperation getOperation() {
		return this.operation;
	}
	/**
	 * Set the operation of the supervision fee requirement item..
	 * 
	 * @param operation operation
	 */
	public void setOperation(final SupervisionFeeRequirementItemOperation 
					operation) {
		this.operation = operation;
	}
	
	/**
	 * Return the fee.
	 * 
	 * @return the fee
	 */
	public BigDecimal getFee() {
		return this.fee;
	}
	/**
	 * Set the fee.
	 * 
	 * @param fee fee
	 */
	public void setFee(final BigDecimal fee) {
		this.fee = fee;
	}
	
	/**
	 * Return the start date of supervision fee requirement form.
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	/**
	 * Set the start date of supervision fee requirement form.
	 * 
	 * @param startDate startDate
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Return the end date of supervision requirement form.
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	/**
	 * Set the end date of supervision requirement form.
	 * 
	 * @param endDate endDate
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Return the officer supervision fee authority of the supervision 
	 * fee requirement.
	 * 
	 * @return the officer
	 */
	public Person getOfficer() {
		return this.officer;
	}
	/**
	 * Sets the officer supervision fee authority of the supervision 
	 * fee requirement.
	 * @param officer officer
	 */
	public void setOfficer(final Person officer) {
		this.officer = officer;
	}
	
	/**
	 * Returns comments for the fee requirement.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return this.comments;
	}
	/**
	 * Sets the comments for the fee requirement.
	 * 
	 * @param comments comments
	 */
	public void setComment(final String comments) {
		this.comments = comments;
	}
	
	/**
	 * Returns the fee requirement reason.
	 * 
	 * @return the reason
	 */
	public SupervisionFeeRequirementReason getReason() {
		return this.reason;
	}
	/**
	 * Sets the fee requirement reason.
	 * 
	 * @param reason reason
	 */
	public void setReason(final SupervisionFeeRequirementReason reason) {
		this.reason = reason;
	}
	
	/**
	 * Returns the fee requirement of the supervision fee requirement.
	 * 
	 * @return the feeRequirement
	 */
	public SupervisionFeeRequirement getFeeRequirement() {
		return this.feeRequirement;
	}
	/**
	 * Sets the fee requirement of the supervision fee requirement.
	 * 
	 * @param feeRequirement fee requirement
	 */
	public void setFeeRequirement(final SupervisionFeeRequirement 
					feeRequirement) {
		this.feeRequirement = feeRequirement;
	}
	
	/**
	 * Returns the fee requirement id of the supervision fee requirement.
	 * 
	 * @return the feeRequirementId 
	 */
	public Long getFeeRequirementId() {
		return this.feeRequirementId;
	}
	/**
	 * Sets the fee requirement id of the supervision fee requirement.
	 * 
	 * @param feeRequirementId the fee requirement id to set
	 */
	public void setFeeRequirementId(final Long feeRequirementId) {
		this.feeRequirementId = feeRequirementId;
	}
	
	/**
	 * Returns the supervision fee requirement item authority.
	 * 
	 * @return the itemAuthority
	 */
	public SupervisionFeeRequirementItemAuthority getItemAuthority() {
		return this.itemAuthority;
	}	
	/**
	 * Sets the supervision fee requirement item authority.
	 *
	 * @param itemAuthority item authority
	 */
	public void setItemAuthority(final SupervisionFeeRequirementItemAuthority 
					itemAuthority) {
		this.itemAuthority = itemAuthority;
	}
	
	/**
	 * Returns the court supervision fee authority of the 
	 * supervision fee requirement.
	 * 
	 * @return the court
	 */
	public Court getCourt() {
		return this.court;
	}	
	/**
	 * Sets the court supervision fee authority of the 
	 * supervision fee authority.
	 * 
	 * @param court court
	 */
	public void setCourt(final Court court) {
		this.court = court;
	}
}
