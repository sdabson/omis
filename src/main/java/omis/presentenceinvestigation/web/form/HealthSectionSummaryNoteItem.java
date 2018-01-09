package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;

/**
 * Health section summary note item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 10, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryNoteItem {

	private HealthSectionSummaryNote healthSectionSummaryNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	
	public HealthSectionSummaryNoteItem() {
		// Default constructor.
	}


	/**
	 * Return the health section summary note.
	 *
	 * @return the healthSectionSummaryNote
	 */
	public HealthSectionSummaryNote getHealthSectionSummaryNote() {
		return this.healthSectionSummaryNote;
	}


	/**
	 * Sets the health section summary note.
	 *
	 * @param healthSectionSummaryNote health section summary note
	 */
	public void setHealthSectionSummaryNote(final HealthSectionSummaryNote 
			healthSectionSummaryNote) {
		this.healthSectionSummaryNote = healthSectionSummaryNote;
	}


	/**
	 * Return the health section summary note item description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}


	/**
	 * Sets the health section summary note item description.
	 *
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}


	/**
	 * Returns the health section summary note item date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}


	/**
	 * Sets the health section summary note item date.
	 *
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}


	/**
	 * Returns the presentence investigation item operation.
	 *
	 * @return the itemOperation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return this.itemOperation;
	}


	/**
	 * Sets the presentence investigation item operation.
	 *
	 * @param itemOperation item operation
	 */
	public void setItemOperation(final PresentenceInvestigationItemOperation 
			itemOperation) {
		this.itemOperation = itemOperation;
	}		
}