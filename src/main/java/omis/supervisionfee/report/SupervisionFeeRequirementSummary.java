package omis.supervisionfee.report;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.1 (Jun 3, 2016)
 * @since OMIS 3.0
 */
public class SupervisionFeeRequirementSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date startDate;
	
	private Date endDate;
	
	private String authority;
	
	private String reason;
	
	private BigDecimal fee;
	
	private boolean active;
	
	/**
	 * Constructor.
	 * 
	 * @param active - active.
	 * @param startDate start date
	 * @param endDate end date
	 * @param authority authority
	 * @param reason reason
	 * @param fee fee
	 */
	public SupervisionFeeRequirementSummary(final boolean active,
			final Date startDate, final Date endDate, 
			final String authority, final String reason, 
			final BigDecimal fee) {
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		this.authority = authority;
		this.reason = reason;
		this.fee = fee;
	}
	
	/** Gets active.
	 * @return active. */
	public boolean getActive() {
		return this.active; 
	}
	
	/**
	 * Gets the supervision fee requirement start date.
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Gets the supervision fee requirements end date.
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Gets the supervision fee requirements authority.
	 * 
	 * @return the authority
	 */
	public String getAuthority() {
		return this.authority;
	}

	/**
	 * Gets the supervision fee requirements reason.
	 * 
	 * @return the reason
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * Gets the supervision fee requirements fee.
	 * 
	 * @return the fee
	 */
	public BigDecimal getFee() {
		return this.fee;
	}
}