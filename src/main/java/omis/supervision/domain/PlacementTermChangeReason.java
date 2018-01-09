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
package omis.supervision.domain;

import java.io.Serializable;

/**
 * Reason for a placement term change.
 * 
 * <p>Change reasons can be used at the start and/or end of a placement term.
 * Whether a reason instance is valid at the start and/or end of a supervision
 * term is indicated respectively by the {@code validStartReason} and
 * {@code validEndReason} properties.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 17, 2013)
 * @since OMIS 3.0
 */
public interface PlacementTermChangeReason
		extends Serializable {

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
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);
	
	/**
	 * Returns the sort order.
	 * 
	 * @return sort order
	 */
	Short getSortOrder();
	
	/**
	 * Sets whether {@code this} is a valid start reason.
	 * 
	 * @param validStartReason whether {@code this} is a valid start reason
	 */
	void setValidStartReason(Boolean validStartReason);
	
	/**
	 * Returns whether {@code this} is a valid start reason.
	 * 
	 * @return whether {@code this} is a valid start reason
	 */
	Boolean getValidStartReason();
	
	/**
	 * Sets whether {@code this} is a valid end reason.
	 * 
	 * @param validEndReason whether {@code this} is a valid end reason
	 */
	void setValidEndReason(Boolean validEndReason);
	
	/**
	 * Returns whether {@code this} is a valid end reason.
	 * 
	 * @return whether {@code this} is a valid end reason
	 */
	Boolean getValidEndReason();
	
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
}