package omis.paroleboarditinerary.report;

import java.io.Serializable;

/**
 * Board attendee summary.
 *
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Feb 5, 2019)
 * @since OMIS 3.0
 */
public class BoardAttendeeSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String memberLastName;
	
	private final String memberFirstName;
	
	private final String memberMiddleName;
	
	private final String staffTitle;
	
	public BoardAttendeeSummary(final Long id, final String memberLastName,
			final String memberFirstName, final String memberMiddleName,
			final String staffTitle) {
		this.id = id;
		this.memberLastName = memberLastName;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.staffTitle = staffTitle;
	}

	/**
	 * Returns the id of the board attendee.
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the member last name.
	 * 
	 * @return member last name
	 */
	public String getMemberLastName() {
		return memberLastName;
	}

	/**
	 * Returns the member first name.
	 * 
	 * @return member first name
	 */
	public String getMemberFirstName() {
		return memberFirstName;
	}

	/**
	 * Returns the member middle name.
	 * @return member middle name
	 */
	public String getMemberMiddleName() {
		return memberMiddleName;
	}
	
	/**
	 * Returns the board member staff title
	 * @return staff title
	 */
	public String getStaffTitle() {
		return staffTitle;
	}
}
