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
package omis.caution.domain;

import java.io.Serializable;

/**
 * Caution source.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 22, 2013)
 * @since OMIS 3.0
 */
public interface CautionSource extends Serializable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();

	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	String getName();

	/**
	 * Sets whether the use of the source indicates a medical caution.
	 * 
	 * @param medical whether use of source indicated medical caution
	 */
	void setMedical(Boolean medical);
	
	/**
	 * Returns whether the use of the source indicates a medical caution.
	 * 
	 * @return whether use of source indicates medical caution
	 */
	Boolean getMedical();
	
	/**
	 * Sets whether the caution source code is valid.
	 * 
	 * @param valid whether caution source code is valid
	 */
	void setValid(Boolean valid);

	/**
	 * Returns whether the caution source code is valid.
	 * 
	 * @return whether caution source code is valid
	 */
	Boolean getValid();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
	
	/**
	 * Returns a string representation of the caution source including the
	 * code, description and indications of both whether the source is for
	 * medical use and whether it is valid.
	 * 
	 * @return string containing code, description, medical use and validity
	 * indicators
	 */
	@Override
	String toString();
}