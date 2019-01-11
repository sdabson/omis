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
package omis.program.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.program.domain.Program;

/**
 * Form for program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class ProgramPlacementForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Program program;
	
	private Date startDate;
	
	private Date endDate;
	
	/** Instantiates form for program placements. */
	public ProgramPlacementForm() {
		// Default instantiation
	}

	/**
	 * Returns program.
	 * 
	 * @return program
	 */
	public Program getProgram() {
		return this.program;
	}

	/**
	 * Sets program.
	 * 
	 * @param program program
	 */
	public void setProgram(final Program program) {
		this.program = program;
	}

	/**
	 * Returns start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}