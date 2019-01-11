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
package omis.organization.domain;

import java.io.Serializable;

/**
 * Organization division.
 *
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @version 0.1.0 (Apr 13, 2016)
 * @since OMIS 3.0
 */

public interface OrganizationDivision extends Serializable {
	
	/**
	 * Sets the organization division ID.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	/**
	 * Returns the organization division ID.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the organization for division.
	 * 
	 * @param organization organization
	 */
	void setOrganization(Organization organization);
	/**
	 * Returns the organization for the division.
	 * 
	 * @return organization
	 */
	Organization getOrganization();
	
	/**
	 * Sets the name of the organization division.
	 * 
	 * @param name name
	 */
	void setName(String name);
	/**
	 * Returns the name of the organization division.
	 * 
	 * @return name 
	 */
	String getName();
	
	/**
	 * Sets whether the organization division is active.
	 * 
	 * @param active active
	 */
	void setActive(Boolean active);
	/**
	 * Returns whether the organization division is active.
	 * @return
	 */
	Boolean getActive();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
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
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
	
	/**
	 * Returns string representation of {@code this} including organization name
	 * and division name.
	 * 
	 * @return string representation of {@code this} including organization name
	 * and division name
	 */
	@Override
	String toString();
}