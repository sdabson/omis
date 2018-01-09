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
package omis.grievance.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.user.domain.UserAccount;

/**
 * Grievance disposition item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 29, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionItem
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private GrievanceDisposition disposition;
	
	private Boolean edit;
	
	private GrievanceDispositionStatus status;
	
	private Boolean currentLevel;
	
	private Boolean required;
	
	private GrievanceDispositionReason reason;
	
	private Boolean closedDateAllowed;
	
	private Date closedDate;
	
	private Date receivedDate;
	
	private Date responseDueDate;
	
	private String responseByQuery;
	
	private UserAccount responseBy;
	
	private Date responseToOffenderDate;
	
	private Boolean allowAppealDate;
	
	private Date appealDate;
	
	private String notes;
	
	/** Instantiates grievance disposition item. */
	public GrievanceDispositionItem() {
		// Default instantiation
	}

	/**
	 * Returns disposition.
	 * 
	 * @return disposition
	 */
	public GrievanceDisposition getDisposition() {
		return this.disposition;
	}

	/**
	 * Sets disposition.
	 * 
	 * @param disposition disposition
	 */
	public void setDisposition(final GrievanceDisposition disposition) {
		this.disposition = disposition;
	}

	/**
	 * Returns whether to edit disposition.
	 * 
	 * @return whether to edit disposition
	 */
	public Boolean getEdit() {
		return this.edit;
	}

	/**
	 * Sets whether to edit disposition.
	 * 
	 * @param edit whether to edit disposition
	 */
	public void setEdit(final Boolean edit) {
		this.edit = edit;
	}

	/**
	 * Returns status.
	 * 
	 * @return status
	 */
	public GrievanceDispositionStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets status.
	 * 
	 * @param status status
	 */
	public void setStatus(final GrievanceDispositionStatus status) {
		this.status = status;
	}

	/**
	 * Returns whether current disposition level for grievance.
	 * 
	 * @return whether current disposition level for grievance
	 */
	public Boolean getCurrentLevel() {
		return this.currentLevel;
	}

	/**
	 * Sets whether current disposition level for grievance.
	 * 
	 * @param currentLevel whether current disposition level for grievance
	 */
	public void setCurrentLevel(final Boolean currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	/**
	 * Returns whether level is required.
	 * 
	 * @return whether level is required
	 */
	public Boolean getRequired() {
		return this.required;
	}
	
	/**
	 * Sets whether level is required.
	 * 
	 * @param required whether level is required
	 */
	public void setRequired(final Boolean required) {
		this.required = required;
	}

	/**
	 * Returns reason.
	 * 
	 * @return reason
	 */
	public GrievanceDispositionReason getReason() {
		return this.reason;
	}
	
	/**
	 * Sets reason.
	 * 
	 * @param reason reason
	 */
	public void setReason(final GrievanceDispositionReason reason) {
		this.reason = reason;
	}
	
	/**
	 * Returns closed date.
	 * 
	 * @return closed date
	 */
	public Date getClosedDate() {
		return this.closedDate;
	}

	/**
	 * Sets closed date.
	 * 
	 * @param closedDate closed date
	 */
	public void setClosedDate(final Date closedDate) {
		this.closedDate = closedDate;
	}
	
	/**
	 * Returns whether closed date is allowed.
	 * 
	 * @return whether closed date is allowed
	 */
	public Boolean getClosedDateAllowed() {
		return this.closedDateAllowed;
	}
	
	/**
	 * Sets whether closed date is allowed.
	 * 
	 * @param closedDateAllowed whether closed date is allowed
	 */
	public void setClosedDateAllowed(final Boolean closedDateAllowed) {
		this.closedDateAllowed = closedDateAllowed;
	}
	
	/**
	 * Returns received date.
	 * 
	 * @return received date
	 */
	public Date getReceivedDate() {
		return this.receivedDate;
	}

	/**
	 * Sets received date.
	 * 
	 * @param receivedDate received date
	 */
	public void setReceivedDate(final Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * Returns response due date.
	 * 
	 * @return response due date
	 */
	public Date getResponseDueDate() {
		return this.responseDueDate;
	}

	/**
	 * Sets response due date.
	 * 
	 * @param responseDueDate response due date
	 */
	public void setResponseDueDate(final Date responseDueDate) {
		this.responseDueDate = responseDueDate;
	}

	/**
	 * Returns response by query.
	 * 
	 * @return response by query
	 */
	public String getResponseByQuery() {
		return this.responseByQuery;
	}

	/**
	 * Sets response by query.
	 * 
	 * @param responseByQuery response by query
	 */
	public void setResponseByQuery(final String responseByQuery) {
		this.responseByQuery = responseByQuery;
	}

	/**
	 * Returns account of responding user.
	 * 
	 * @return account of responding user
	 */
	public UserAccount getResponseBy() {
		return this.responseBy;
	}

	/**
	 * Sets account of responding user.
	 * 
	 * @param responseBy account of responding user
	 */
	public void setResponseBy(final UserAccount responseBy) {
		this.responseBy = responseBy;
	}

	/**
	 * Returns date of response to offender.
	 * 
	 * @return date of response to offender
	 */
	public Date getResponseToOffenderDate() {
		return this.responseToOffenderDate;
	}

	/**
	 * Sets date of response to offender.
	 * 
	 * @param responseToOffenderDate date of response to offender
	 */
	public void setResponseToOffenderDate(
			final Date responseToOffenderDate) {
		this.responseToOffenderDate = responseToOffenderDate;
	}

	/**
	 * Returns whether to allow appeal date to be entered.
	 * 
	 * @return whether to allow appeal date to be entered
	 */
	public Boolean getAllowAppealDate() {
		return this.allowAppealDate;
	}

	/**
	 * Sets whether to allow appeal date to be entered.
	 * 
	 * @param allowAppealDate whether to allow appeal date to be entered
	 */
	public void setAllowAppealDate(final Boolean allowAppealDate) {
		this.allowAppealDate = allowAppealDate;
	}

	/**
	 * Returns appeal date.
	 * 
	 * @return appeal date
	 */
	public Date getAppealDate() {
		return this.appealDate;
	}

	/**
	 * Sets appeal date.
	 * 
	 * @param appealDate appeal date
	 */
	public void setAppealDate(final Date appealDate) {
		this.appealDate = appealDate;
	}

	/**
	 * Returns notes.
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Returns notes.
	 * 
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}
}