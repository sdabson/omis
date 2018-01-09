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

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.OffenderAssociable;

/**
 * Placement term.
 * 
 * <p>A change in correctional status or supervisory organization is reflected
 * in a new placement term for an offender.
 * 
 * @author Stephen Abson
 * @author Jason Nelson
 * @version 0.1.0 (Jun 17, 2013)
 * @since OMIS 3.0
 */
public interface PlacementTerm
		extends Creatable, Updatable, OffenderAssociable {

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
	 * Sets status.
	 * 
	 * @param status status
	 */
	void setStatus(PlacementStatus status);
	
	/**
	 * Returns status.
	 * 
	 * @return status
	 */
	PlacementStatus getStatus();
	
	/**
	 * Sets status date range.
	 * 
	 * @param statusDateRange status date range
	 */
	void setStatusDateRange(DateRange statusDateRange);
	
	/**
	 * Returns status date range.
	 * 
	 * @return status date range
	 */
	DateRange getStatusDateRange();
	
	/**
	 * Sets the supervisory organization term designating the organization the
	 * offender receives supervision from during the term.
	 * 
	 * @param supervisoryOrganization organization from which offender
	 * receives supervision during term
	 */
	void setSupervisoryOrganizationTerm(
			SupervisoryOrganizationTerm setSupervisoryOrganizationTerm);
	
	/**
	 * Returns the supervisory organization term designating the organization
	 * the offender receives supervision from during the term.
	 * 
	 * @return organization from which offender receives supervision 
	 * during the placement term
	 */
	SupervisoryOrganizationTerm getSupervisoryOrganizationTerm();
	
	/**
	 * Sets the correctional status term during the placement term.
	 * 
	 * @param correctionalStatusTerm correctional status term 
	 * during the placement term
	 */
	void setCorrectionalStatusTerm(
			CorrectionalStatusTerm correctionalStatusTerm);
	
	/**
	 * Returns the correctional status term during the placement term.
	 * @return correctional status term during the placement term
	 */
	CorrectionalStatusTerm getCorrectionalStatusTerm();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the reason for the change to the term.
	 * 
	 * @param startChangeReason reason for change to term
	 */
	void setStartChangeReason(PlacementTermChangeReason startChangeReason);
	
	/**
	 * Returns the reason from the change to the term.
	 * 
	 * @return reason for change to term
	 */
	PlacementTermChangeReason getStartChangeReason();
	
	/**
	 * Sets the reason for the change from the term.
	 * 
	 * @param endChangeReason reason for change from term
	 */
	void setEndChangeReason(PlacementTermChangeReason endChangeReason);
	
	/**
	 * Returns the reason for the change from the term.
	 * 
	 * @return reason for change from term
	 */
	PlacementTermChangeReason getEndChangeReason();
	
	/**
	 * Sets whether or not the placement term is locked.
	 * 
	 * @param locked locked
	 */
	void setLocked(Boolean locked);
	
	/**
	 * Returns whether or not the placement term is locked.
	 * 
	 * @return whether or not the placement term is locked
	 */
	Boolean getLocked();
	
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