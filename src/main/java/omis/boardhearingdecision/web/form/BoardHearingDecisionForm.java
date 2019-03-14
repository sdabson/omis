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
package omis.boardhearingdecision.web.form;

import java.io.Serializable;
import java.util.List;

import omis.boardhearingdecision.domain.BoardHearingDecisionCategory;

/**
 * Board hearing decision form.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Mar 13, 2019)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private BoardHearingDecisionCategory category;
	
	private List<HearingDecisionNoteItem> hearingDecisionNoteItems;
	
	private List<BoardMemberDecisionItem> boardMemberDecisionItems;
	
	private String rulingDetails;
	
	/**
	 * Instantiates a default board hearing decision form. 
	 */
	public BoardHearingDecisionForm() {
		// Default instantiation
	}

	/**
	 * Returns the board hearing decision category.
	 * 
	 * @return board hearing decision category
	 */
	public BoardHearingDecisionCategory getCategory() {
		return category;
	}

	/**
	 * Sets the board hearing decision category.
	 * 
	 * @param category board hearing decision category
	 */
	public void setCategory(final BoardHearingDecisionCategory category) {
		this.category = category;
	}

	/**
	 * Returns the hearing decision note items.
	 * 
	 * @return hearing decision note items
	 */
	public List<HearingDecisionNoteItem> getHearingDecisionNoteItems() {
		return hearingDecisionNoteItems;
	}

	/**
	 * Sets the hearing decision note items.
	 * 
	 * @param hearingDecisionNoteItems hearing decision note items
	 */
	public void setHearingDecisionNoteItems(
			final List<HearingDecisionNoteItem> hearingDecisionNoteItems) {
		this.hearingDecisionNoteItems = hearingDecisionNoteItems;
	}

	/**
	 * Returns the board member decision items.
	 * 
	 * @return board member decision items
	 */
	public List<BoardMemberDecisionItem> getBoardMemberDecisionItems() {
		return boardMemberDecisionItems;
	}

	/**
	 * Sets the board member decision items.
	 * 
	 * @param boardMemberDecisionItems board member decision items
	 */
	public void setBoardMemberDecisionItems(
			final List<BoardMemberDecisionItem> boardMemberDecisionItems) {
		this.boardMemberDecisionItems = boardMemberDecisionItems;
	}

	/**
	 * Returns the ruling details.
	 *
	 * @return rulingDetails
	 */
	public String getRulingDetails() {
		return this.rulingDetails;
	}

	/**
	 * Sets the ruling details.
	 *
	 * @param rulingDetails - ruling details
	 */
	public void setRulingDetails(final String rulingDetails) {
		this.rulingDetails = rulingDetails;
	}
}
