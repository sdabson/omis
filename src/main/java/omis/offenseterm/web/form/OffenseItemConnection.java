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

/**
 * Connection of offense item to other offense items.
 * 
 * <p>Connection can be concurrent, consecutive or consecutive to an offense
 * on another docket.
 * 
 * <p>Format of value is: {@code CLASSIFICATION_NAME[INDEX]} for
 * consecutive connections; {@code CLASSIFICATION_NAME} for initial
 * or concurrent connections.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class OffenseItemConnection
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String value;
	
	private final Long index;
	
	private final OffenseItemConnectionClassification classification;
	
	/**
	 * Instantiates connection of offense item.
	 * 
	 * <p>Value is parsed; other properties are set.
	 * 
	 * @param value value
	 * @throws IllegalArgumentException if classification supplied in
	 * {@code value} does not exist or is empty
	 */
	public OffenseItemConnection(final String value) {
		if (OffenseItemConnectionClassification.INITIAL.getName()
				.equals(value)
				|| OffenseItemConnectionClassification.CONCURRENT.getName()
					.equals(value)) {
			this.index = null;
			this.classification = OffenseItemConnectionClassification
					.valueOf(value);
		} else {
			this.index = Long.valueOf(value.substring(
					value.indexOf('[') + 1, value.indexOf(']')));
			
			// Enum#valueOf throws IllegalArgumentException if no instance with
			// name exists thus satisfying the contact of this method
			// String.substring(int, int) will not return null so Enum#valueOf
			// will not throw NullPointerException
			this.classification = OffenseItemConnectionClassification.valueOf(
					value.substring(0, value.indexOf('[')));
		}
		this.value = value;
	}
	
	/*
	 * Instantiates connection of offense item.
	 * 
	 * @param value value
	 * @param index index
	 * @param classification classification
	 */
	private OffenseItemConnection(
			final String value,
			final Long index,
			final OffenseItemConnectionClassification classification) {
		this.value = value;
		this.index = index;
		this.classification = classification;
	}
	
	/**
	 * Returns initial connection.
	 * 
	 * @return initial connection
	 */
	public static OffenseItemConnection createInitial() {
		return new OffenseItemConnection(
				OffenseItemConnectionClassification.INITIAL.getName(), null,
				OffenseItemConnectionClassification.INITIAL);
	}
	
	/**
	 * Returns concurrent connection.
	 * 
	 * @return concurrent connection
	 */
	public static OffenseItemConnection createConcurrent() {
		return new OffenseItemConnection(
				OffenseItemConnectionClassification.CONCURRENT.getName(), null,
				OffenseItemConnectionClassification.CONCURRENT);
	}
	
	/**
	 * Returns consecutive connection.
	 * 
	 * @return consecutive connection
	 */
	public static OffenseItemConnection createConsecutive(final Long index) {
		return new OffenseItemConnection(
				OffenseItemConnectionClassification.CONSECUTIVE.getName()
				 	+ "[" + index + "]", index,
				OffenseItemConnectionClassification.CONSECUTIVE);
	}
	
	/**
	 * Returns consecutive to other docket connection.
	 * 
	 * @return consecutive to other docket connection
	 */
	public static OffenseItemConnection createConsecutiveOtherDocket(
			final Long index) {
		return new OffenseItemConnection(
				OffenseItemConnectionClassification.CONSECUTIVE_OTHER_DOCKET
					.getName() + "[" + index + "]", index,
				OffenseItemConnectionClassification.CONSECUTIVE_OTHER_DOCKET);
	}
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Returns index.
	 * 
	 * @return index
	 */
	public Long getIndex() {
		return this.index;
	}
	
	/**
	 * Returns classification.
	 * 
	 * @return classification
	 */
	public OffenseItemConnectionClassification getClassification() {
		return this.classification;
	}
	
	/**
	 * Returns {@code this.getValue()}.
	 * 
	 * <p>See {@link this#getValue()}.
	 * 
	 * @return {@code this.value}
	 */
	public String toString() {
		return this.value;
	}
}