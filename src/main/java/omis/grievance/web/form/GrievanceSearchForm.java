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
package omis.grievance.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.grievance.domain.GrievanceLocation;
import omis.offender.domain.Offender;

/**
 * Form to search grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 6, 2016)
 * @since OMIS 3.0
 */
public class GrievanceSearchForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private GrievanceSearchType type;
	
	private Offender offender;
	
	private GrievanceLocation location;
	
	private String query;
	
	private Date effectiveDate;
	
	/** Instantiates form to search grievances. */
	public GrievanceSearchForm() {
		// Default instantiation
	}

	/**
	 * Returns type.
	 * 
	 * @return type
	 */
	public GrievanceSearchType getType() {
		return this.type;
	}

	/**
	 * Sets type.
	 * 
	 * @param type type
	 */
	public void setType(final GrievanceSearchType type) {
		this.type = type;
	}

	/**
	 * Returns offender.
	 * 
	 * @return offender
	 */
	public Offender getOffender() {
		return this.offender;
	}

	/**
	 * Sets offender.
	 * 
	 * @param offender offender
	 */
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/**
	 * Returns location of grievance.
	 * 
	 * @return location of grievance
	 */
	public GrievanceLocation getLocation() {
		return this.location;
	}

	/**
	 * Sets location of grievance.
	 * 
	 * @param location location of grievance
	 */
	public void setLocation(final GrievanceLocation location) {
		this.location = location;
	}

	/**
	 * Returns query.
	 * 
	 * @return query
	 */
	public String getQuery() {
		return this.query;
	}

	/**
	 * Sets query.
	 * 
	 * @param query query
	 */
	public void setQuery(final String query) {
		this.query = query;
	}

	/**
	 * Returns effective date.
	 * 
	 * @return effective date
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Sets effective date.
	 * 
	 * @param effectiveDate effective date
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}