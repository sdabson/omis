package omis.offenderrelationship.web.form;

import java.io.Serializable;

import omis.relationship.domain.RelationshipNote;

/**
 * Offender relationship note item.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public class OffenderRelationshipNoteItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RelationshipNote note;
	
	private OffenderRelationshipNoteItemOperation operation;
	
	private OffenderRelationshipNoteFields fields;
	
	/**
	 * Instantiates offender relationship note item.
	 */
	public OffenderRelationshipNoteItem() {
		// Default instantiation
	}
	
	/**
	 * Sets note.
	 * 
	 * @param note note
	 */
	public void setNote(final RelationshipNote note) {
		this.note = note;
	}
	
	/**
	 * Returns note.
	 * 
	 * @return note
	 */
	public RelationshipNote getNote() {
		return this.note;
	}
	
	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(
			final OffenderRelationshipNoteItemOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public OffenderRelationshipNoteItemOperation getOperation() {
		return this.operation;
	}
	
	/**
	 * Sets fields.
	 * 
	 * @param fields fields
	 */
	public void setFields(final OffenderRelationshipNoteFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns fields.
	 * 
	 * @return fields
	 */
	public OffenderRelationshipNoteFields getFields() {
		return this.fields;
	}
}