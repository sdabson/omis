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
package omis.boardhearingdecision.domain;

import java.io.Serializable;

/**
 * Board hearing decision category.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public interface BoardHearingDecisionCategory extends Serializable {

	/**
	 * Sets the ID of the board hearing decision category.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the board hearing decision category.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Returns the name of the board hearing decision category.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the name of the board hearing decision category.
	 * 
	 * @param name name
	 */
	void setName(String name);

	/**
	 * Returns the decision category of the board hearing decision category.
	 * 
	 * @return decision category
	 */
	DecisionCategory getDecision();
	
	/**
	 * Sets the decision category of the board hearing decision category.
	 * 
	 * @param decision decision category
	 */
	void setDecision(DecisionCategory decision);
	
	/**
	 * Returns whether the board hearing decision category is valid or not.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets whether the board hearing decision category is valid or not.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
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
}
