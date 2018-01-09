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

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.user.domain.UserAccount;

/**
 * Grievance disposition.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 7, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceDisposition
		extends Creatable, Updatable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets grievance. 
	 * 
	 * @param grievance grievance
	 */
	void setGrievance(Grievance grievance);
	
	/**
	 * Returns grievance.
	 * 
	 * @return grievance
	 */
	Grievance getGrievance();
	
	/**
	 * Sets level.
	 * 
	 * @param level level
	 */
	void setLevel(GrievanceDispositionLevel level);
	
	/**
	 * Returns level.
	 * 
	 * @return level
	 */
	GrievanceDispositionLevel getLevel();
	
	/**
	 * Sets status.
	 * 
	 * @param status status
	 */
	void setStatus(GrievanceDispositionStatus status);
	
	/**
	 * Returns status.
	 * 
	 * @return status
	 */
	GrievanceDispositionStatus getStatus();
	
	/**
	 * Sets reason.
	 * 
	 * @param reason reason
	 */
	void setReason(GrievanceDispositionReason reason);
	
	/**
	 * Returns reason.
	 * 
	 * @return reason
	 */
	GrievanceDispositionReason getReason();
	
	/**
	 * Sets received date.
	 * 
	 * @param receivedDate received date
	 */
	void setReceivedDate(Date receivedDate);
	
	/**
	 * Returns received date.
	 * 
	 * @return received date
	 */
	Date getReceivedDate();
	
	/**
	 * Sets response due date.
	 * 
	 * @param responseDueDate response due date
	 */
	void setResponseDueDate(Date responseDueDate);
	
	/**
	 * Returns response due date.
	 * 
	 * @return response due date
	 */
	Date getResponseDueDate();
	
	/**
	 * Sets response by user account.
	 * 
	 * @param responseBy response by user account
	 */
	void setResponseBy(UserAccount responseBy);
	
	/**
	 * Returns response by user account.
	 * 
	 * @return response by user account
	 */
	UserAccount getResponseBy();
	
	/**
	 * Sets response to offender date.
	 * 
	 * @param responseToOffenderDate response to offender date
	 */
	void setResponseToOffenderDate(Date responseToOffenderDate);
	
	/**
	 * Returns response to offender date.
	 * 
	 * @return response to offender date
	 */
	Date getResponseToOffenderDate();
	
	/**
	 * Sets appeal date.
	 * 
	 * @param appealDate appeal date
	 */
	void setAppealDate(Date appealDate);
	
	/**
	 * Returns appeal date.
	 * 
	 * @return appeal date
	 */
	Date getAppealDate();
	
	/**
	 * Sets notes.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns notes.
	 * 
	 * @return notes
	 */
	String getNotes();
	
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