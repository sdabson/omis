package omis.victim.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;

/**
 * Victim note item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 17, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private VictimNoteItemOperation operation;
	
	private VictimNote note;
	
	private VictimNoteCategory category;
	
	private VictimAssociation association;
	
	private Date date;
	
	private String value;
	
	/** Instantiates victim note item. */
	public VictimNoteItem() {
		// Default instantiation
	}

	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public VictimNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final VictimNoteItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns note.
	 * 
	 * @return note
	 */
	public VictimNote getNote() {
		return this.note;
	}

	/**
	 * Sets note.
	 * 
	 * @param note note
	 */
	public void setNote(final VictimNote note) {
		this.note = note;
	}

	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	public VictimNoteCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets category.
	 * 
	 * @param category category
	 */
	public void setCategory(final VictimNoteCategory category) {
		this.category = category;
	}

	/**
	 * Returns association.
	 * 
	 * @return association
	 */
	public VictimAssociation getAssociation() {
		return this.association;
	}

	/**
	 * Sets association.
	 * 
	 * @param association association
	 */
	public void setAssociation(final VictimAssociation association) {
		this.association = association;
	}

	/**
	 * Returns date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
}