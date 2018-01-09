package omis.hearing.web.form;

import java.util.Date;

import omis.hearing.domain.HearingNote;

/**
 * HearingNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 29, 2016)
 *@since OMIS 3.0
 *
 */
public class HearingNoteItem {
	
	private HearingNote hearingNote;
	
	private Date date;
	
	private String description;
	
	private ItemOperation itemOperation;
	
	/**
	 * 
	 */
	public HearingNoteItem() {
	}
	
	/**
	 * @return the hearingNote
	 */
	public HearingNote getHearingNote() {
		return hearingNote;
	}



	/**
	 * @param hearingNote the hearingNote to set
	 */
	public void setHearingNote(HearingNote hearingNote) {
		this.hearingNote = hearingNote;
	}



	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
