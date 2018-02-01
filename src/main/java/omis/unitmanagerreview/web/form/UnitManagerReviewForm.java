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
package omis.unitmanagerreview.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.staff.domain.StaffAssignment;

/**
 * Unit manager review form.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
public class UnitManagerReviewForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private StaffAssignment staffAssignment;
	
	private Date date;
	
	private String text;
	
	private List<UnitManagerReviewDocumentAssociationItem> 
			unitManagerReviewDocumentAssociationItems = new ArrayList<>();
	/**
	 * Instantiates a default unit manager review form. 
	 */
	public UnitManagerReviewForm() {
	}

	/**
	 * Returns the staff assignment.
	 *
	 * @return staff assignment
	 */
	public StaffAssignment getStaffAssignment() {
		return staffAssignment;
	}

	/**
	 * Sets the staff assignment.
	 *
	 * @param staffAssignment staff assignment
	 */
	public void setStaffAssignment(final StaffAssignment staffAssignment) {
		this.staffAssignment = staffAssignment;
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
	 * Returns the text.
	 *
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text text
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * Returns the list of unit manager review document association items.
	 *
	 * @return list of unit manager review document association items
	 */
	public List<UnitManagerReviewDocumentAssociationItem> 
			getUnitManagerReviewDocumentAssociationItems() {
		return unitManagerReviewDocumentAssociationItems;
	}

	/**
	 * Sets the list of unit manager review document association items.
	 *
	 * @param unitManagerReviewDocumentAssociationItems list of unit manager 
	 * review document association items
	 */
	public void setUnitManagerReviewDocumentAssociationItems(
			final List<UnitManagerReviewDocumentAssociationItem>
					unitManagerReviewDocumentAssociationItems) {
		this.unitManagerReviewDocumentAssociationItems = 
				unitManagerReviewDocumentAssociationItems;
	}
	
}
