package omis.physicalfeature.web.form;

import java.util.Date;

import omis.physicalfeature.domain.PhysicalFeatureAssociationNote;

/**
 * Physical feature association note item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 10, 2015)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationNoteItem {

	private PhysicalFeatureAssociationNote physicalFeatureAssociationNote;
	
	private String note;
	
	private Date date;
	
	private PhysicalFeatureAssociationNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of physical feature association 
	 * note item.
	 */
	public PhysicalFeatureAssociationNoteItem() {
		//Default constructor.
	}

	/**
	 * Returns the physical feature association note.
	 * 
	 * @return physical feature association note
	 */
	public PhysicalFeatureAssociationNote getPhysicalFeatureAssociationNote() {
		return this.physicalFeatureAssociationNote;
	}

	/**
	 * Sets the physical feature association note.
	 * 
	 * @param physicalFeatureAssociationNote physical feature association note
	 */
	public void setPhysicalFeatureAssociationNote(
			final PhysicalFeatureAssociationNote 
			physicalFeatureAssociationNote) {
		this.physicalFeatureAssociationNote = physicalFeatureAssociationNote;
	}

	/**
	 * Returns the note.
	 * 
	 * @return note
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * Sets the note.
	 * 
	 * @param note note
	 */
	public void setNote(final String note) {
		this.note = note;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the operation.
	 * 
	 * @return physical feature association note item operation
	 */
	public PhysicalFeatureAssociationNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation physical feature association note item operation
	 */
	public void setOperation(
			final PhysicalFeatureAssociationNoteItemOperation operation) {
		this.operation = operation;
	}
}