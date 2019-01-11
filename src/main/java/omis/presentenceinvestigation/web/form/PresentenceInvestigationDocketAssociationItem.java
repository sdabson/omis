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
package omis.presentenceinvestigation.web.form;

import java.io.Serializable;

import omis.court.domain.Court;
import omis.docket.domain.Docket;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;

/**
 * Presentence investigation docket association item.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Oct 25, 2018)
 * @since OMIS 3.0
 */
public class PresentenceInvestigationDocketAssociationItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PresentenceInvestigationDocketAssociation 
			presentenceInvestigationDocketAssociation;
	
	private Boolean useExisting;
	
	private Docket existingDocket;
	
	private String docketValue;
	
	private Court court;
	
	private PresentenceInvestigationItemOperation itemOperation;

	/**
	 * Instantiates a default presentence investigation docket association item.
	 */
	public PresentenceInvestigationDocketAssociationItem() {
		// Default constructor
	}
	
	/**
	 * Returns the presentence investigation docket association.
	 *
	 * @return presentence investigation docket association
	 */
	public PresentenceInvestigationDocketAssociation 
			getPresentenceInvestigationDocketAssociation() {
		return presentenceInvestigationDocketAssociation;
	}

	/**
	 * Sets the presentence investigation docket association.
	 *
	 * @param presentenceInvestigationDocketAssociation presentence 
	 * investigation docket association
	 */
	public void setPresentenceInvestigationDocketAssociation(
			final PresentenceInvestigationDocketAssociation 
				presentenceInvestigationDocketAssociation) {
		this.presentenceInvestigationDocketAssociation = 
				presentenceInvestigationDocketAssociation;
	}

	/**
	 * Returns whether or not to use an existing docket.
	 *
	 * @return whether or not to use an existing docket
	 */
	public Boolean getUseExisting() {
		return useExisting;
	}

	/**
	 * Sets whether or not to use an existing docket.
	 *
	 * @param useExisting use an existing docket
	 */
	public void setUseExisting(final Boolean useExisting) {
		this.useExisting = useExisting;
	}

	/**
	 * Returns the existing docket.
	 *
	 * @return existing docket
	 */
	public Docket getExistingDocket() {
		return existingDocket;
	}

	/**
	 * Sets the existing docket.
	 *
	 * @param existingDocket existing docket
	 */
	public void setExistingDocket(final Docket existingDocket) {
		this.existingDocket = existingDocket;
	}

	/**
	 * Returns the docket value.
	 * 
	 * @return docket value
	 */
	public String getDocketValue() {
		return docketValue;
	}

	/**
	 * Sets the docket value.
	 * 
	 * @param docketValue docket value
	 */
	public void setDocketValue(final String docketValue) {
		this.docketValue = docketValue;
	}

	/**
	 * Returns the court.
	 * 
	 * @return court
	 */
	public Court getCourt() {
		return court;
	}

	/**
	 * Sets the court.
	 * 
	 * @param court court
	 */
	public void setCourt(final Court court) {
		this.court = court;
	}
	
	/**
	 * Returns the item operation.
	 * 
	 * @return presentence investigation item operation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the item operation.
	 * 
	 * @param itemOperation presentence investigation item operation
	 */
	public void setItemOperation(
			final PresentenceInvestigationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
}