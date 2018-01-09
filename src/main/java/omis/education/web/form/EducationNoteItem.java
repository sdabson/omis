package omis.education.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.education.domain.EducationNote;

/**
 * EducationNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 27, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationNoteItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EducationNote note;
	
	private Date date;
	
	private String description;
	
	private ItemOperation operation;
	
	/**
	 * Default constructor for EducationNoteItem
	 */
	public EducationNoteItem(){
		//Default
	}

	/**
	 * Returns the education note associated with this item
	 * @return the note
	 */
	public EducationNote getNote() {
		return note;
	}

	/**
	 * Sets the education note associated with this item
	 * @param note the education note to set
	 */
	public void setNote(EducationNote note) {
		this.note = note;
	}

	/**
	 * Returns the date
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the item's operation (enum)
	 * @return the operation
	 */
	public ItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the item's operation (enum)
	 * @param operation the operation to set
	 */
	public void setOperation(ItemOperation operation) {
		this.operation = operation;
	}

	
	
}
