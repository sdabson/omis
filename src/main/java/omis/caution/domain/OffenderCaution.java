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

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;
import omis.offender.domain.OffenderAssociable;

/**
 * Caution.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (Jan 22, 2013)
 * @since OMIS 3.0
 */
public interface OffenderCaution
		extends OffenderAssociable, Updatable, Creatable {
	
	/**
	 * Sets the ID of the offender caution.
	 * 
	 * @param id offender caution ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the offender caution.
	 * 
	 * @return offender caution ID
	 */
	Long getId();
	
	/**
	 * Sets the description of the caution.
	 * 
	 * @param description caution description
	 */
	void setDescription(CautionDescription description);
	
	/**
	 * Returns the description of the caution.
	 * 
	 * @return caution description
	 */
	CautionDescription getDescription();
	
	/**
	 * Sets the source of the caution.
	 * 
	 * @param source source of caution
	 */
	void setSource(CautionSource source);
	
	/**
	 * Returns the source of the caution.
	 * 
	 * @return source of caution
	 */
	CautionSource getSource();

	/**
	 * Sets the date range during which the caution is active.
	 * 
	 * @param dateRange date range during which caution is active
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range during which the caution is active.
	 * 
	 * @return date range during which caution is active
	 */
	DateRange getDateRange();

	/**
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	void setComment(String comment);
	
	/**
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	String getComment();

	/**
	 * Sets the caution source comment.
	 * 
	 * @param sourceComment caution source comment
	 */
	void setSourceComment(String sourceComment);
	
	/**
	 * Returns the caution source comment.
	 * 
	 * @return caution source comment
	 */
	String getSourceComment();
	
	/**
	 * Compares {@code this} and {@code o} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param o reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code o} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object o);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}