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
package omis.docket.web.form;

import java.io.Serializable;

import omis.court.domain.Court;

/**
 * Fields for dockets.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 30, 2018)
 * @since OMIS 3.0
 */
public class DocketFields
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Court court;
	
	private String value;
	
	/**
	 * Instantiates fields for dockets.
	 */
	public DocketFields() {
		// Default instantiation
	}
	
	/**
	 * Sets court.
	 * 
	 * @param court court
	 */
	public void setCourt(final Court court) {
		this.court = court;
	}
	
	/**
	 * Returns court.
	 * 
	 * @return court
	 */
	public Court getCourt() {
		return this.court;
	}
	
	/**
	 * Sets value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
}