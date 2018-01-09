package omis.military.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Military service term summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long militaryServiceTermId;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final String branchName;
	
	private final String dischargeStatusName;
	
	/**
	 * Instantiates a military service term summary with the specified values.
	 * 
	 * @param militaryServiceTermId military service term ID
	 * @param startDate start date
	 * @param endDate end date
	 * @param branchName branch name
	 * @param dischargeStatusName discharge status name
	 */
	public MilitaryServiceTermSummary(final Long militaryServiceTermId,
			final Date startDate, final Date endDate, final String branchName,
			final String dischargeStatusName) {
		this.militaryServiceTermId = militaryServiceTermId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.branchName = branchName;
		this.dischargeStatusName = dischargeStatusName;
	}

	/**
	 * Returns the military service term ID.
	 * 
	 * @return military service term ID
	 */
	public Long getMilitaryServiceTermId() {
		return this.militaryServiceTermId;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Returns the branch name.
	 * 
	 * @return branch name
	 */
	public String getBranchName() {
		return this.branchName;
	}

	/**
	 * Returns the discharge status name.
	 * 
	 * @return discharge status name
	 */
	public String getDischargeStatusName() {
		return this.dischargeStatusName;
	}
}