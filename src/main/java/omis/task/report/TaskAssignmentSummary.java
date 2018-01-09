package omis.task.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Summary of task assignment.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class TaskAssignmentSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String assigneeUserLastName;
	
	private final String assigneeUserFirstName;
	
	private final String assigneeUserMiddleName;
	
	private final String assigneeUserSuffix;
	
	private final String assigneeUsername;
	
	private final Date assignedDate;
	
	/**
	 * Instantiates summary of task assignment.
	 * 
	 * @param id ID
	 * @param assigneeUserLastName last name of assignee user
	 * @param assigneeUserFirstName first name of assignee user
	 * @param assigneeUserMiddleName middle name of assignee user
	 * @param assigneeUserSuffix suffix of assignee user
	 * @param assigneeUsername username of assignee user
	 * @param assignedDate assigned date
	 */
	public TaskAssignmentSummary(
			final Long id,
			final String assigneeUserLastName,
			final String assigneeUserFirstName,
			final String assigneeUserMiddleName,
			final String assigneeUserSuffix,
			final String assigneeUsername,
			final Date assignedDate) {
		this.id = id;
		this.assigneeUserLastName = assigneeUserLastName;
		this.assigneeUserFirstName = assigneeUserFirstName;
		this.assigneeUserMiddleName = assigneeUserMiddleName;
		this.assigneeUserSuffix = assigneeUserSuffix;
		this.assigneeUsername = assigneeUsername;
		this.assignedDate = assignedDate;
	}
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns last name of assignee.
	 * 
	 * @return last name of assignee
	 */
	public String getAssigneeUserLastName() {
		return this.assigneeUserLastName;
	}
	
	/**
	 * Returns first name of assignee.
	 * 
	 * @return first name of assignee
	 */
	public String getAssigneeUserFirstName() {
		return this.assigneeUserFirstName;
	}
	
	/**
	 * Returns middle name of assignee.
	 * 
	 * @return middle name of assignee
	 */
	public String getAssigneeUserMiddleName() {
		return this.assigneeUserMiddleName;
	}
	
	/**
	 * Returns suffix of assignee.
	 * 
	 * @return suffix of assignee
	 */
	public String getAssigneeUserSuffix() {
		return this.assigneeUserSuffix;
	}
	
	/**
	 * Returns username of assignee.
	 * 
	 * @return username of assignee
	 */
	public String getAssigneeUsername() {
		return this.assigneeUsername;
	}
	
	/**
	 * Returns assigned date.
	 * 
	 * @return assigned date
	 */
	public Date getAssignedDate() {
		return this.assignedDate;
	}
}