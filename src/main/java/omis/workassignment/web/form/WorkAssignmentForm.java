package omis.workassignment.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.workassignment.domain.FenceRestriction;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentChangeReason;

/** Form object for work assignment.
 * @author: Yidong Li
 * @version 0.1.1 (Aug 24, 2016)
 * @since OMIS 3.0 */
public class WorkAssignmentForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private WorkAssignmentCategory workAssignmentCategory;
	private Date assignmentDate;
	private WorkAssignmentChangeReason workAssignmentChangeReason;
	private Date terminationDate;
	private FenceRestriction fenceRestriction;
	private String comments;
	private List<WorkAssignmentNoteItem> workAssignmentNoteItems 
	= new ArrayList<WorkAssignmentNoteItem>();
	private Boolean endExistingWorkAssignment;
	
	/** Instantiates a work assignment form. */
	public WorkAssignmentForm() {
		// Default instantiation
	}
	
	public WorkAssignmentCategory getWorkAssignmentCategory() {
		return this.workAssignmentCategory;
	}
	public void setWorkAssignmentCategory(WorkAssignmentCategory workAssignmentCategory) {
		this.workAssignmentCategory = workAssignmentCategory;
	}
	 
	public Date getAssignmentDate() {
		return this.assignmentDate;
	}
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}
	public WorkAssignmentChangeReason getWorkAssignmentChangeReason() {
		return this.workAssignmentChangeReason;
	}
	public void setWorkAssignmentChangeReason(WorkAssignmentChangeReason workAssignmentChangeReason) {
		this.workAssignmentChangeReason = workAssignmentChangeReason;
	}
	public Date getTerminationDate() {
		return this.terminationDate;
	}
	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}
	public FenceRestriction getFenceRestriction() {
		return this.fenceRestriction;
	}
	public void setFenceRestriction(FenceRestriction fenceRestriction) {
		this.fenceRestriction = fenceRestriction;
	}
	public String getComments() {
		return this.comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<WorkAssignmentNoteItem> getWorkAssignmentNoteItems() {
		return workAssignmentNoteItems;
	}

	public void setWorkAssignmentNoteItems(
			List<WorkAssignmentNoteItem> workAssignmentNoteItems) {
		this.workAssignmentNoteItems = workAssignmentNoteItems;
	}
	public Boolean getEndExistingWorkAssignment() {
		return this.endExistingWorkAssignment;
	}
	public void setEndExistingWorkAssignment(Boolean endExistingWorkAssignment) {
		this.endExistingWorkAssignment = endExistingWorkAssignment;
	}
}