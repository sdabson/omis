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
package omis.assessment.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Rating Note Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 6, 2019)
 *@since OMIS 3.0
 *
 */
public class RatingNoteForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<RatingNoteItem> ratingNoteItems =
			new ArrayList<RatingNoteItem>();
	
	/**
	 * Default constructor for Rating Note Form. 
	 */
	public RatingNoteForm() {
	}

	/**
	 * Returns the ratingNoteItems.
	 * @return ratingNoteItems - List<RatingNoteItem>
	 */
	public List<RatingNoteItem> getRatingNoteItems() {
		return this.ratingNoteItems;
	}

	/**
	 * Sets the ratingNoteItems.
	 * @param ratingNoteItems - List<RatingNoteItem>
	 */
	public void setRatingNoteItems(
			final List<RatingNoteItem> ratingNoteItems) {
		this.ratingNoteItems = ratingNoteItems;
	}
	
}
