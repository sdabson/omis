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

/**
 * Classification of how offense items are connected.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum OffenseItemConnectionClassification {

	/** Classifies initial offense item. */
	INITIAL,
	
	/**
	 * Classifies offense item that is concurrently connected to another
	 * offense item.
	 */
	CONCURRENT,
	
	/**
	 * Classifies offense item that is consecutively connected to another
	 * offense item on the same docket.
	 */
	CONSECUTIVE,
	
	/**
	 * Classifies offense item that is consecutively connected to another
	 * offense item on a different docket.
	 */
	CONSECUTIVE_OTHER_DOCKET;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}