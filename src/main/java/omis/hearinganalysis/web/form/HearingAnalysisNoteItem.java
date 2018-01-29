/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.hearinganalysis.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.hearinganalysis.domain.HearingAnalysisNote;

/**
 * Hearing analysis note item.
 *
 * @author Josh Divine
 * @version 0.1.0 (Dec 19, 2017)
 * @since OMIS 3.0
 */
public class HearingAnalysisNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private HearingAnalysisNote hearingAnalysisNote;
	
	private Date date;
	
	private String value;
	
	private HearingAnalysisNoteItemOperation operation;
	
	/** Instantiates a default note on a parole board itinerary form. */
	public HearingAnalysisNoteItem() {
		// Default instantiation
	}

	/**
	 * Returns the hearing analysis note.
	 * 
	 * @return parole board itinerary note
	 */
	public HearingAnalysisNote getHearingAnalysisNote() {
		return hearingAnalysisNote;
	}

	/**
	 * Sets the hearing analysis note.
	 * 
	 * @param hearingAnalysisNote parole board itinerary note
	 */
	public void setHearingAnalysisNote(
			final HearingAnalysisNote hearingAnalysisNote) {
		this.hearingAnalysisNote = hearingAnalysisNote;
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
	public HearingAnalysisNoteItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the parole board itinerary note item operation.
	 * 
	 * @param operation parole board itinerary note item operation
	 */
	public void setOperation(
			final HearingAnalysisNoteItemOperation operation) {
		this.operation = operation;
	}
}
