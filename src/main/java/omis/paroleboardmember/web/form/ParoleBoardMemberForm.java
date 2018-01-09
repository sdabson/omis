package omis.paroleboardmember.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.staff.domain.StaffAssignment;

/**
 * Parole board member form.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 14, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	
	private Date endDate;
	
	private StaffAssignment staffAssignment;
	
	/**
	 * Instantiates a default parole board member form. 
	 */
	public ParoleBoardMemberForm() {
		// Default instantiation
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the staff assignment.
	 * 
	 * @return staff assignment
	 */
	public StaffAssignment getStaffAssignment() {
		return staffAssignment;
	}

	/**
	 * Sets the staff assignment.
	 * 
	 * @param staffAssignment staff assignment
	 */
	public void setStaffAssignment(StaffAssignment staffAssignment) {
		this.staffAssignment = staffAssignment;
	}
}
