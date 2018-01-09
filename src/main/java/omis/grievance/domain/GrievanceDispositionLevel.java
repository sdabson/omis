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
package omis.grievance.domain;

/**
 * Level of grievance disposition.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 7, 2015)
 * @since OMIS 3.0
 */
public enum GrievanceDispositionLevel {

	/** Coordinator. */
	COORDINATOR(1),
	
	/** Warden. */
	WARDEN(2),
	
	/** Facility Health Administrator. */
	FHA(2),
	
	/** Director. */
	DIRECTOR(3);
	
	// Used to determine order of levels
	private final int order;
	
	// Instantiates level with an order
	private GrievanceDispositionLevel(final int order) {
		this.order = order;
	}
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns whether {@code this} comes before {@code level}.
	 * 
	 * @param level level
	 * @return whether {@code this} comes before {@code level}
	 */
	public boolean isBefore(final GrievanceDispositionLevel level) {
		return this.order < level.order;
	}
	
	/**
	 * Returns whether {@code this} comes after {@code level}.
	 * 
	 * @param level level
	 * @return whether {@code this} comes after {@code level}
	 */
	public boolean isAfter(final GrievanceDispositionLevel level) {
		return this.order > level.order;
	}
}