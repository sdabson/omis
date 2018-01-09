package omis.workrestriction.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.workrestriction.web.form.ItemOperation;
import omis.workrestriction.domain.WorkRestrictionNote;

/**
 * NoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 25, 2016)
 *@since OMIS 3.0
 *
 */
public class NoteItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private WorkRestrictionNote note;
	
	private Date date;
	
	private String value;
	
	private ItemOperation operation;
	
	/**
	 * Default Constructor for NoteItem
	 */
	public NoteItem(){
		
	}

	/**
	 * @return the note
	 */
	public WorkRestrictionNote getNote() {
		return this.note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(WorkRestrictionNote note) {
		this.note = note;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the operation
	 */
	public ItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(ItemOperation operation) {
		this.operation = operation;
	}
	
	
}
