package omis.hearing.web.form;

import omis.hearing.domain.StaffAttendance;
import omis.staff.domain.StaffAssignment;

/**
 * StaffAttendanceItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public class StaffAttendanceItem {
	
	private StaffAttendance staffAttended;
	
	private StaffAssignment staff;
	
	private ItemOperation itemOperation;
	
	/**
	 * 
	 */
	public StaffAttendanceItem() {
	}
	
	/**
	 * @return the staffAttended
	 */
	public StaffAttendance getStaffAttended() {
		return staffAttended;
	}

	/**
	 * @param staffAttended the staffAttended to set
	 */
	public void setStaffAttended(StaffAttendance staffAttended) {
		this.staffAttended = staffAttended;
	}

	/**
	 * @return the staff
	 */
	public StaffAssignment getStaff() {
		return staff;
	}

	/**
	 * @param staff the staff to set
	 */
	public void setStaff(StaffAssignment staff) {
		this.staff = staff;
	}

	/**
	 * @return the itemOperation
	 */
	public ItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * @param itemOperation the itemOperation to set
	 */
	public void setItemOperation(ItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
