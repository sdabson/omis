package omis.supervisionfee.web.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.supervisionfee.domain.MonthlySupervisionFeeAuthorityCategory;

/**
 * Monthly supervision fee form.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 9, 2014)
 * @since OMIS 3.0
 */
public class MonthlySupervisionFeeForm {

	private MonthlySupervisionFeeAuthorityCategory authorityCategory;
	
	private Date startDate;
	
	private Date endDate;
	
	private BigDecimal fee;
	
	private String comment;
	
	private List<FeeRequirementItem> feeRequirements = 
					new ArrayList<FeeRequirementItem>();
	
	
	/**
	 * Instantiates a default instance of supervision fee form.
	 */
	public MonthlySupervisionFeeForm() {
		// Default constructor.
	}

	/**
	 * Returns the authority category.
	 * 
	 * @return the authorityCategory
	 */
	public MonthlySupervisionFeeAuthorityCategory getAuthorityCategory() {
		return this.authorityCategory;
	}

	/**
	 * Sets the authority category.
	 * 
	 * @param authorityCategory authorityCategory
	 */
	public void setAuthorityCategory(
					final MonthlySupervisionFeeAuthorityCategory 
					authorityCategory) {
		this.authorityCategory = authorityCategory;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate startDate
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate endDate
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the fee.
	 * 
	 * @return the fee
	 */
	public BigDecimal getFee() {
		return this.fee;
	}

	/**
	 * Sets the fee.
	 * 
	 * @param fee fee
	 */
	public void setFee(final BigDecimal fee) {		
		this.fee = fee;
	}
	
	/**
	 * Returns the comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}
	
	/**
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * Returns the fee requirements.
	 * 
	 * @return the feeRequirements
	 */
	public List<FeeRequirementItem> getFeeRequirements() {
		return this.feeRequirements;
	}

	/**
	 * Sets the fee requirements.
	 * 
	 * @param feeRequirements feeRequirements
	 */
	public void setFeeRequirements(final List<FeeRequirementItem> 
					feeRequirements) {
		this.feeRequirements = feeRequirements;
	}
}
