package omis.workassignment.report;

import java.util.Date;

import omis.util.TimeComparison;
import omis.workassignment.domain.WorkAssignment;

/**
 * Summary of work assignment.
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.2 (Dec 14, 2017)
 * @since OMIS 3.0
 */
public class WorkAssignmentSummary {
	private final Long id;
	private final String offenderLastName;
	private final String offenderFirstName;
	private final String offenderMiddleName;
	private final String offenderSuffix;
	private final Integer offenderNumber;
	private final String fenceRestrictionName;
	private final String categoryName;
	private final String categoryGroupName;
	private final String changeReasonName;
	private final Date assignedDate;
	private final Date terminationDate;
	private final Integer days;
	private final String comments;
	
	/**
	 * Instantiates summary of work assignment.
	 * 
	 * @param workAssignment work assignment
	 */
	public WorkAssignmentSummary(
			final WorkAssignment workAssignment) {
		if (workAssignment != null) {
			this.id = workAssignment.getId();
			this.offenderFirstName = workAssignment.getOffender().getName()
					.getFirstName();
			this.offenderLastName = workAssignment.getOffender().getName()
					.getLastName();
			this.offenderMiddleName = workAssignment.getOffender().getName()
					.getMiddleName();
			this.offenderSuffix = workAssignment.getOffender().getName()
					.getSuffix();
			this.offenderNumber = workAssignment.getOffender()
					.getOffenderNumber();
			this.fenceRestrictionName = workAssignment.getFenceRestriction()
					.getName();
			this.categoryName = workAssignment.getCategory().getName();
			this.changeReasonName = workAssignment.getChangeReason().getName();
			this.assignedDate = workAssignment.getAssignedDate();
			this.terminationDate = workAssignment.getTerminationDate();
			if(workAssignment.getTerminationDate()!=null){
				this.days = (int) TimeComparison.elapsedDays(
						workAssignment.getAssignedDate(), 
						workAssignment.getTerminationDate());
			}
			else {
				this.days = null;
			}
			this.comments = workAssignment.getComments();
			if (workAssignment.getCategory().getGroup() != null) {
				this.categoryGroupName = workAssignment.getCategory().getGroup()
						.getName();
			} else {
				this.categoryGroupName = null;
			}
		} else {
			this.id = null;
			this.offenderFirstName = null;
			this.offenderLastName = null;
			this.offenderMiddleName = null;
			this.offenderSuffix = null;
			this.offenderNumber = null;
			this.fenceRestrictionName = null;
			this.categoryName = null;
			this.changeReasonName = null;
			this.assignedDate = null;
			this.terminationDate = null;
			this.days = null;
			this.comments = null;
			this.categoryGroupName = null;
		}
	}

	/**
	 * Returns ID of contact.
	 * 
	 * @return ID of contact
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns offender last name.
	 * 
	 * @return offenderLastName
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns offender first name.
	 * 
	 * @return offenderFirstName
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns offender middle name.
	 * 
	 * @return offenderMiddleName
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns offender suffix.
	 * 
	 * @return offenderSuffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}
	
	/**
	 * Returns offender last name.
	 * 
	 * @return offenderLastName
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns fence restriction name.
	 * 
	 * @return fenceRestrictionName
	 */
	public String getFenceRestrictionName() {
		return this.fenceRestrictionName;
	}
	
	/**
	 * Returns category name.
	 * 
	 * @return categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * Returns category group name.
	 * 
	 * @return categoryGroupName
	 */
	public String getCategoryGroupName() {
		return this.categoryGroupName;
	}
	
	/**
	 * Returns change reason name.
	 * 
	 * @return changeReasonName
	 */
	public String getChangeReasonName() {
		return this.changeReasonName;
	}
	
	/**
	 * Returns assigned date.
	 * 
	 * @return assignedDate
	 */
	public Date getAssignedDate() {
		return this.assignedDate;
	}
	
	/**
	 * Returns termination date.
	 * 
	 * @return terminationDate
	 */
	public Date getTerminationDate() {
		return this.terminationDate;
	}
	
	/**
	 * Returns the number of days between assigedDate and teminationDate.
	 * 
	 * @return days
	 */
	public Integer getDays() {
		return this.days;
	}
	
	/**
	 * Returns notes.
	 * 
	 * @return notes
	 */
	public String getComments() {
		return this.comments;
	}
}