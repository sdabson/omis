package omis.grievance.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceUnit;

/**
 * Form for grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
public class GrievanceForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean edit;
	
	private Date openedDate;
	
	private Date informalFileDate;
	
	private GrievanceUnit unit;
	
	private GrievanceComplaintCategory complaintCategory;
	
	private GrievanceLocation location;
	
	private String description;
	
	private String initialComment;
	
	private Boolean closed;
	
	private GrievanceDispositionItem coordinatorLevelDispositionItem;
	
	private GrievanceDispositionItem wardenFhaLevelDispositionItem;
	
	private GrievanceDispositionItem directorLevelDispositionItem;
	
	/** Instantiates form for grievances. */
	public GrievanceForm() {
		// Default instantiation
	}

	/**
	 * Returns whether to edit grievance.
	 * 
	 * @return whether to edit grievance
	 */
	public Boolean getEdit() {
		return this.edit;
	}

	/**
	 * Sets whether to edit grievance.
	 * 
	 * @param edit whether to edit grievance
	 */
	public void setEdit(final Boolean edit) {
		this.edit = edit;
	}

	/**
	 * Returns date opened.
	 * 
	 * @return date opened
	 */
	public Date getOpenedDate() {
		return this.openedDate;
	}

	/**
	 * Sets date opened.
	 * 
	 * @param openedDate date opened
	 */
	public void setOpenedDate(final Date openedDate) {
		this.openedDate = openedDate;
	}

	/**
	 * Returns informal file date.
	 * 
	 * @return informal file date
	 */
	public Date getInformalFileDate() {
		return this.informalFileDate;
	}

	/**
	 * Sets informal file date.
	 * 
	 * @param informalFileDate informal file date
	 */
	public void setInformalFileDate(final Date informalFileDate) {
		this.informalFileDate = informalFileDate;
	}

	/**
	 * Returns unit.
	 * 
	 * @return unit
	 */
	public GrievanceUnit getUnit() {
		return this.unit;
	}

	/**
	 * Sets unit.
	 * 
	 * @param unit unit
	 */
	public void setUnit(final GrievanceUnit unit) {
		this.unit = unit;
	}

	/**
	 * Returns complaint category.
	 * 
	 * @return complaint category
	 */
	public GrievanceComplaintCategory getComplaintCategory() {
		return this.complaintCategory;
	}

	/**
	 * Sets complaint category.
	 * 
	 * @param complaintCategory complaint category
	 */
	public void setComplaintCategory(
			final GrievanceComplaintCategory complaintCategory) {
		this.complaintCategory = complaintCategory;
	}

	/**
	 * Returns location.
	 * 
	 * @return location
	 */
	public GrievanceLocation getLocation() {
		return this.location;
	}

	/**
	 * Sets location.
	 * 
	 * @param location location
	 */
	public void setLocation(final GrievanceLocation location) {
		this.location = location;
	}

	/**
	 * Returns description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets description.
	 * 
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns initial comment.
	 * 
	 * @return initial comment
	 */
	public String getInitialComment() {
		return this.initialComment;
	}

	/**
	 * Sets initial comment.
	 * 
	 * @param initialComment initial comment
	 */
	public void setInitialComment(final String initialComment) {
		this.initialComment = initialComment;
	}

	/**
	 * Returns whether closed.
	 * 
	 * @return whether closed
	 */
	public Boolean getClosed() {
		return this.closed;
	}

	/**
	 * Sets whether closed.
	 * 
	 * @param closed whether closed
	 */
	public void setClosed(final Boolean closed) {
		this.closed = closed;
	}

	/**
	 * Returns coordinator level disposition item.
	 * 
	 * @return coordinator level disposition item
	 */
	public GrievanceDispositionItem getCoordinatorLevelDispositionItem() {
		return this.coordinatorLevelDispositionItem;
	}

	/**
	 * Sets coordinator level disposition item.
	 * 
	 * @param coordinatorLevelDispositionItem coordinator level disposition item
	 */
	public void setCoordinatorLevelDispositionItem(
			final GrievanceDispositionItem coordinatorLevelDispositionItem) {
		this.coordinatorLevelDispositionItem = coordinatorLevelDispositionItem;
	}

	/**
	 * Returns warden/FHA level disposition item.
	 * 
	 * @return warden/FHA level disposition item
	 */
	public GrievanceDispositionItem getWardenFhaLevelDispositionItem() {
		return this.wardenFhaLevelDispositionItem;
	}

	/**
	 * Sets warden/FHA level disposition item.
	 * 
	 * @param wardenFhaLevelDispositionItem warden/FHA level disposition item
	 */
	public void setWardenFhaLevelDispositionItem(
			final GrievanceDispositionItem wardenFhaLevelDispositionItem) {
		this.wardenFhaLevelDispositionItem = wardenFhaLevelDispositionItem;
	}

	/**
	 * Returns director level disposition item.
	 * 
	 * @return director level disposition item
	 */
	public GrievanceDispositionItem getDirectorLevelDispositionItem() {
		return this.directorLevelDispositionItem;
	}

	/**
	 * Sets director level disposition item.
	 * 
	 * @param directorLevelDispositionItem director level disposition item
	 */
	public void setDirectorLevelDispositionItem(
			final GrievanceDispositionItem directorLevelDispositionItem) {
		this.directorLevelDispositionItem = directorLevelDispositionItem;
	}
}