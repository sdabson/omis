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
package omis.grievance.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceDispositionDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.instance.factory.InstanceFactory;
import omis.user.domain.UserAccount;

/**
 * Delegate for grievance disposition.
 *
 * @author Stephen Abson
 * @version 0.0.2 (Oct 3, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionDelegate {

	/* Resources. */
	
	private final GrievanceDispositionDao grievanceDispositionDao;
	
	private final InstanceFactory<GrievanceDisposition>
	grievanceDispositionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Delegate for grievance dispositions.
	 * 
	 * @param grievanceDispositionDao data access object for grievance
	 * dispositions
	 * @param grievanceDispositionInstanceFactory instance factory for
	 * grievance dispositions
	 * @param auditComponentRetriever audit component retriever
	 */
	public GrievanceDispositionDelegate(
			final GrievanceDispositionDao grievanceDispositionDao,
			final InstanceFactory<GrievanceDisposition>
			grievanceDispositionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.grievanceDispositionDao = grievanceDispositionDao;
		this.grievanceDispositionInstanceFactory
			= grievanceDispositionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Grievance disposition management methods. */
	
	/**
	 * Creates grievance disposition.
	 * 
	 * @param grievance grievance for which to create disposition
	 * @param level level
	 * @param status status
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of responding user
	 * @param responseToOffenderDate response to offender date
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return new grievance disposition
	 * @throws DuplicateEntityFoundException if grievance disposition exists
	 */
	public GrievanceDisposition create(final Grievance grievance,
			final GrievanceDispositionLevel level,
			final GrievanceDispositionStatus status,
			final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes)
					throws DuplicateEntityFoundException {
		this.checkGrievanceDates(grievance, receivedDate, responseDueDate,
				responseToOffenderDate, appealDate);
		this.checkPreviousLevelDates(grievance, level, receivedDate,
				responseDueDate, responseToOffenderDate, appealDate);
		if (status.getLevel() != null && !level.equals(status.getLevel())) {
			throw new IllegalArgumentException(
					"Status level must be null or " + level);
		}
		if (this.grievanceDispositionDao.find(grievance, level) != null) {
			throw new DuplicateEntityFoundException(
					"Grievance disposition exists with level " + level);
		}
		GrievanceDisposition disposition
			= this.grievanceDispositionInstanceFactory.createInstance();
		disposition.setGrievance(grievance);
		disposition.setLevel(level);
		disposition.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateGrievanceDisposition(disposition, status, reason,
				receivedDate, responseDueDate, responseBy,
				responseToOffenderDate, appealDate, notes);
		return this.grievanceDispositionDao.makePersistent(disposition);
	}

	/**
	 * Updates grievance disposition.
	 * 
	 * @param disposition disposition
	 * @param status status
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of responding user
	 * @param responseToOffenderDate response to offender date
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return updated grievance disposition
	 * @throws DuplicateEntityFoundException if grievance disposition exists
	 */
	public GrievanceDisposition update(final GrievanceDisposition disposition,
			final GrievanceDispositionStatus status,
			final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes) {
		this.checkGrievanceDates(disposition.getGrievance(), receivedDate,
				responseDueDate, responseToOffenderDate, appealDate);
		this.checkPreviousLevelDates(disposition.getGrievance(),
				disposition.getLevel(), receivedDate, responseDueDate,
				responseToOffenderDate, appealDate);
		if (status.getLevel() != null && !disposition.getLevel()
				.equals(status.getLevel())) {
			throw new IllegalArgumentException(
					"Status level must be null or " + disposition.getLevel());
		}
		this.populateGrievanceDisposition(disposition, status, reason,
				receivedDate, responseDueDate, responseBy,
				responseToOffenderDate, appealDate, notes);
		return this.grievanceDispositionDao.makePersistent(disposition);
	}
	
	/**
	 * Removes grievance disposition.
	 * 
	 * @param disposition grievance disposition to remove
	 */
	public void remove(final GrievanceDisposition disposition) {
		this.grievanceDispositionDao.makeTransient(disposition);
	}
	
	/**
	 * Finds grievance disposition.
	 * 
	 * @param grievance grievance
	 * @param level level
	 * @return grievance disposition
	 */
	public GrievanceDisposition find(final Grievance grievance,
			final GrievanceDispositionLevel level) {
		return this.grievanceDispositionDao.find(grievance, level);
	}
	
	/**
	 * Finds dispositions for grievance.
	 * 
	 * @param grievance grievance for which to find grievances
	 * @return dispositions for grievance
	 */
	public List<GrievanceDisposition> findByGrievance(
			final Grievance grievance) {
		return this.grievanceDispositionDao.findByGrievance(grievance);
	}
	
	/* Helpers. */
	
	// Populates grievance disposition
	private void populateGrievanceDisposition(
			final GrievanceDisposition disposition,
			final GrievanceDispositionStatus status,
			final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes) {
		disposition.setStatus(status);
		disposition.setReason(reason);
		disposition.setReceivedDate(receivedDate);
		disposition.setResponseDueDate(responseDueDate);
		disposition.setResponseBy(responseBy);
		disposition.setResponseToOffenderDate(responseToOffenderDate);
		disposition.setAppealDate(appealDate);
		disposition.setNotes(notes);
		disposition.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
	
	// Checks previous level dates are before dates specified
	private void checkPreviousLevelDates(final Grievance grievance,
			final GrievanceDispositionLevel level, final Date receivedDate,
			final Date responseDueDate, final Date responseToOffenderDate,
			final Date appealDate) {
		for (GrievanceDispositionLevel previousLevel
				: GrievanceDispositionLevel.values()) {
			if (previousLevel.isBefore(level)) {
				GrievanceDisposition previousDisposition
					= this.grievanceDispositionDao.find(
							grievance, previousLevel);
				if (previousDisposition != null) {
					if (previousDisposition.getReceivedDate() != null
							&& receivedDate != null && receivedDate.before(
								previousDisposition.getReceivedDate())) {
						throw new IllegalArgumentException(
								"Received date must be after or on received"
								+ " date of previous level");
					}
					if (previousDisposition.getResponseDueDate() != null
							&& responseDueDate != null
								&& responseDueDate.before(previousDisposition
										.getResponseDueDate())) {
						throw new IllegalArgumentException(
								"Response due date must be after or on response"
								+ " due date of previous level");
					}
					if (previousDisposition.getResponseToOffenderDate() != null
							&& responseToOffenderDate != null
							&& responseToOffenderDate.before(
									previousDisposition
										.getResponseToOffenderDate())) {
						throw new IllegalArgumentException(
								"Response to offender date must be after or on"
								+ " response to offender date of previous level");
					}
					if (previousDisposition.getAppealDate() != null
							&& appealDate != null && appealDate.before(
								previousDisposition.getAppealDate())) {
						throw new IllegalArgumentException(
								"Appeal date must be after or on appeal"
								+ " date of previous level");
					}
				}
			}
		}
	}
	
	// Checks dates of disposition are on or after grievance open date
	private void checkGrievanceDates(final Grievance grievance,
			final Date receivedDate, final Date responseDueDate,
			final Date responseToOffenderDate, final Date appealDate) {
		if (grievance.getOpenedDate() != null) {
			if (receivedDate != null
					&& grievance.getOpenedDate().after(receivedDate)) {
				throw new IllegalArgumentException(
						"Received date must be on or after opened date");
			}
			if (responseDueDate != null
					&& grievance.getOpenedDate().after(responseDueDate)) {
				throw new IllegalArgumentException(
						"Response due date must be on or after opened date");
			}
			if (responseToOffenderDate != null && grievance.getOpenedDate()
					.after(responseToOffenderDate)) {
				throw new IllegalArgumentException(
						"Response to offender date must be on or after"
						+ " opened date");
			}
			if (appealDate != null && grievance.getOpenedDate()
					.after(appealDate)) {
				throw new IllegalArgumentException(
						"Appeal date must be on or after opened date");
			}
		}
	}
}