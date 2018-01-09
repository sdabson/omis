package omis.paroleboardmember.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Parole board member summary.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String staffMemberLastName;
	
	private final String staffMemberFirstName;

	private final String staffMemberMiddleName;
	
	private final String staffTitleName;
	
	private final Date boardStartDate;
	
	private final Date boardEndDate;
	
	/**
	 * Instantiates an implementation of parole board member summary.
	 *
	 * @param id id 
	 * @param staffMemberLastName staff member last name
	 * @param staffMemberFirstName staff member first name
	 * @param staffMemberMiddleName staff member middle name
	 * @param staffTitleName staff title name
	 * @param boardStartDate board start date
	 * @param boardEndDate board end date
	 */
	public ParoleBoardMemberSummary(final Long id,
			final String staffMemberLastName,
			final String staffMemberFirstName,
			final String staffMemberMiddleName,
			final String staffTitleName, final Date boardStartDate,
			final Date boardEndDate) {
		this.id = id;
		this.staffMemberFirstName = staffMemberFirstName;
		this.staffMemberLastName = staffMemberLastName;
		this.staffMemberMiddleName = staffMemberMiddleName;
		this.staffTitleName = staffTitleName;
		this.boardEndDate = boardEndDate;
		this.boardStartDate = boardStartDate;
	}
 
	/**
	 * Returns the id of the parole board member.
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Returns the staff member last name.
	 * 
	 * @return staff member last name
	 */
	public String getStaffMemberLastName() {
		return staffMemberLastName;
	}

	/**
	 * Returns the staff member first name.
	 * 
	 * @return staff member first name
	 */
	public String getStaffMemberFirstName() {
		return staffMemberFirstName;
	}

	/**
	 * Returns the staff member middle name.
	 * 
	 * @return staff member middle name
	 */
	public String getStaffMemberMiddleName() {
		return staffMemberMiddleName;
	}

	/**
	 * Returns the staff title name.
	 * 
	 * @return staff title name
	 */
	public String getStaffTitleName() {
		return staffTitleName;
	}

	/**
	 * Returns the board start date.
	 * 
	 * @return board start date
	 */
	public Date getBoardStartDate() {
		return boardStartDate;
	}

	/**
	 * Returns the board end date.
	 * 
	 * @return board end date
	 */
	public Date getBoardEndDate() {
		return boardEndDate;
	}
	
}
