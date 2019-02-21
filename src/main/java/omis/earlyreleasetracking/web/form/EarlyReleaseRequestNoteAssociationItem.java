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
package omis.earlyreleasetracking.web.form;

import java.io.Serializable;
import java.util.Date;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestNoteAssociation;

/**
 * Early Release Request Note Association Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 13, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestNoteAssociationItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EarlyReleaseRequestNoteAssociation
				earlyReleaseRequestNoteAssociation;
	
	private String description;
	
	private Date date;
	
	private EarlyReleaseRequestItemOperation itemOperation;
	
	/**
	 * Default constructor for EarlyReleaseRequestNoteAssociationItem.
	 */
	public EarlyReleaseRequestNoteAssociationItem() {
	}

	/**
	 * Returns the earlyReleaseRequestNoteAssociation.
	 *
	 * @return earlyReleaseRequestNoteAssociation
	 */
	public EarlyReleaseRequestNoteAssociation
				getEarlyReleaseRequestNoteAssociation() {
		return this.earlyReleaseRequestNoteAssociation;
	}

	/**
	 * Sets the earlyReleaseRequestNoteAssociation.
	 *
	 * @param earlyReleaseRequestNoteAssociation Early Release Request Note
	 * Association
	 */
	public void setEarlyReleaseRequestNoteAssociation(
			final EarlyReleaseRequestNoteAssociation
					earlyReleaseRequestNoteAssociation) {
		this.earlyReleaseRequestNoteAssociation =
				earlyReleaseRequestNoteAssociation;
	}

	/**
	 * Returns the description.
	 *
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description - description
	 */
	public void setDescription(final String description) {
		this.description = description;
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
	 * @param date - date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the itemOperation.
	 *
	 * @return itemOperation
	 */
	public EarlyReleaseRequestItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 *
	 * @param itemOperation - itemOperation
	 */
	public void setItemOperation(
			final EarlyReleaseRequestItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}
