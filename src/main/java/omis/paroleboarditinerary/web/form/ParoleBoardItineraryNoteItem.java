package omis.paroleboarditinerary.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.paroleboarditinerary.domain.ParoleBoardItineraryNote;

/**
 * Parole board itinerary note item.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 29, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardItineraryNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private ParoleBoardItineraryNote paroleBoardItineraryNote;
	
	private Date date;
	
	private String value;
	
	private ParoleBoardItineraryNoteItemOperation operation;
	
	/** Instantiates a default note on a parole board itinerary form. */
	public ParoleBoardItineraryNoteItem() {
		// Default instantiation
	}

	/**
	 * Returns the parole board itinerary note.
	 * 
	 * @return parole board itinerary note
	 */
	public ParoleBoardItineraryNote getParoleBoardItineraryNote() {
		return paroleBoardItineraryNote;
	}

	/**
	 * Sets the parole board itinerary note.
	 * 
	 * @param paroleBoardItineraryNote parole board itinerary note
	 */
	public void setParoleBoardItineraryNote(
			final ParoleBoardItineraryNote paroleBoardItineraryNote) {
		this.paroleBoardItineraryNote = paroleBoardItineraryNote;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
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
	 * Returns the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Returns the parole board itinerary note item operation.
	 * 
	 * @return parole board itinerary note item operation
	 */
	public ParoleBoardItineraryNoteItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the parole board itinerary note item operation.
	 * 
	 * @param operation parole board itinerary note item operation
	 */
	public void setOperation(
			final ParoleBoardItineraryNoteItemOperation operation) {
		this.operation = operation;
	}
}
