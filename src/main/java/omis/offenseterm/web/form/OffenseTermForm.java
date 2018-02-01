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
import java.util.ArrayList;
import java.util.List;

import omis.courtcase.web.form.CourtCaseFields;
import omis.docket.domain.Docket;
import omis.docket.web.form.DocketFields;

/**
 * Form for offense term.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenseTermForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean allowExistingDocket;
	
	private Docket existingDocket;
	
	private Boolean allowDocketFields;
	
	private DocketFields docketFields = new DocketFields();
	
	private CourtCaseFields fields = new CourtCaseFields();
	
	private List<OffenseItem> offenseItems
		= new ArrayList<OffenseItem>();
	
	/** Instantiates form for offense term. */
	public OffenseTermForm() {
		// Default instantiation
	}

	/**
	 * Sets whether existing docket is allowed.
	 * 
	 * @param allowExistingDocket whether existing docket is allowed
	 */
	public void setAllowExistingDocket(final Boolean allowExistingDocket) {
		this.allowExistingDocket = allowExistingDocket;
	}
	
	/**
	 * Returns whether existing docket is allowed.
	 * 
	 * @return whether existing docket is allowed
	 */
	public Boolean getAllowExistingDocket() {
		return this.allowExistingDocket;
	}
	
	/**
	 * Sets existing docket.
	 * 
	 * @param existingDocket existing docket
	 */
	public void setExistingDocket(final Docket existingDocket) {
		this.existingDocket = existingDocket;
	}
	
	/**
	 * Returns existing docket.
	 * 
	 * @return existing docket
	 */
	public Docket getExistingDocket() {
		return this.existingDocket;
	}
	
	/**
	 * Sets whether to allow docket fields.
	 * 
	 * @param allowCourt whether to allow docket fields
	 */
	public void setAllowDocketFields(final Boolean allowDocketFields) {
		this.allowDocketFields = allowDocketFields;
	}
	
	/**
	 * Returns whether to allow docket fields.
	 * 
	 * @return whether to allow docket fields
	 */
	public Boolean getAllowDocketFields() {
		return this.allowDocketFields;
	}
	
	/**
	 * Sets docket fields.
	 * 
	 * @param docketFields docket fields
	 */
	public void setDocketFields(final DocketFields docketFields) {
		this.docketFields = docketFields;
	}
	
	/**
	 * Returns fields for docket.
	 * 
	 * @return fields for docket
	 */
	public DocketFields getDocketFields() {
		return this.docketFields;
	}
	
	/**
	 * Sets fields for court case.
	 * 
	 * @param fields fields for court case
	 */
	public void setFields(final CourtCaseFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns fields for court case.
	 * 
	 * @return fields for court case
	 */
	public CourtCaseFields getFields() {
		return this.fields;
	}
	
	/**
	 * Returns offense items.
	 * 
	 * @return offense items
	 */
	public List<OffenseItem> getOffenseItems() {
		return this.offenseItems;
	}

	/**
	 * Sets offense items.
	 * 
	 * @param offenseItems offense items
	 */
	public void setOffenseItems(
			final List<OffenseItem> offenseItems) {
		this.offenseItems = offenseItems;
	}
}