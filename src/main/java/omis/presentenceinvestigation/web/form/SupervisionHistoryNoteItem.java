package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.SupervisionHistoryNote;

/**
 * Supervision history note item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 10, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistoryNoteItem {
	
	private SupervisionHistoryNote supervisionHistoryNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation; 

	/** Instantiates an implementation of SupervisionHistoryNoteItem */
	public SupervisionHistoryNoteItem() {
		// Default constructor.
	}

	/**
	 * Returns a supervision history note.
	 *
	 * @return the supervisionHistoryNote
	 */
	public SupervisionHistoryNote getSupervisionHistoryNote() {
		return this.supervisionHistoryNote;
	}

	/**
	 * Sets the supervision history note.
	 *
	 * @param supervisionHistoryNote supervision history note
	 */
	public void setSupervisionHistoryNote(
			SupervisionHistoryNote supervisionHistoryNote) {
		this.supervisionHistoryNote = supervisionHistoryNote;
	}

	/**
	 * Returns the supervision history note description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the supervision history note description.
	 *
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the supervision history note date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the supervision history note date.
	 *
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the supervision history note presentence investigation 
	 * item operation.
	 *
	 * @return the itemOperation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Sets the supervision history note presentence investigation 
	 * item operation.
	 *
	 * @param itemOperation item operation
	 */
	public void setItemOperation(
			PresentenceInvestigationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}	
}