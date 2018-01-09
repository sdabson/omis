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
package omis.paroleboardcondition.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Parole Board Agreement Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 19, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementForm {
	
	private Date startDate;
	
	private Date endDate;
	
	private String description;
	
	private List<ParoleBoardAgreementAssociableDocumentItem>
		agreementAssociableDocumentItems =
		new ArrayList<ParoleBoardAgreementAssociableDocumentItem>();
	
	/**
	 * Default constructor for ParoleBoardAgreementForm.
	 */
	public ParoleBoardAgreementForm() {
	}

	/**
	 * Returns the start Date.
	 * @return startDate - Date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start Date.
	 * @param startDate - Date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end Date.
	 * @return endDate - Date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end Date.
	 * @param endDate - Date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the description.
	 * @return description - String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the agreementAssociableDocumentItems.
	 * @return agreementAssociableDocumentItems -
	 * List of ParoleBoardAgreementAssociableDocumentItems
	 */
	public List<ParoleBoardAgreementAssociableDocumentItem>
			getAgreementAssociableDocumentItems() {
		return this.agreementAssociableDocumentItems;
	}

	/**
	 * Sets the agreementAssociableDocumentItems.
	 * @param agreementAssociableDocumentItems -
	 * List of ParoleBoardAgreementAssociableDocumentItems
	 */
	public void setAgreementAssociableDocumentItems(
			final List<ParoleBoardAgreementAssociableDocumentItem>
				agreementAssociableDocumentItems) {
		this.agreementAssociableDocumentItems =
				agreementAssociableDocumentItems;
	}
	
	
}
