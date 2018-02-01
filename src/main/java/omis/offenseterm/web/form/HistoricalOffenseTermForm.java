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
package omis.offenseterm.web.form;

import java.io.Serializable;

import omis.sentence.web.form.SentenceFields;

/**
 * Form for historical offense terms.
 *
 * <p>Historical offense terms are represented as inactive sentences.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HistoricalOffenseTermForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SentenceFields sentenceFields = new SentenceFields();
	
	/** Instantiates form for historical offense terms. */
	public HistoricalOffenseTermForm() {
		// Default instantiation
	}
	
	/**
	 * Sets sentence fields.
	 * 
	 * @param sentenceFields sentence fields
	 */
	public void setSentenceFields(final SentenceFields sentenceFields) {
		this.sentenceFields = sentenceFields;
	}
	
	/**
	 * Returns sentence fields.
	 * 
	 * @return sentence fields
	 */
	public SentenceFields getSentenceFields() {
		return this.sentenceFields;
	}
}