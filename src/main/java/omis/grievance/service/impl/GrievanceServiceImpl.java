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
package omis.grievance.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceNote;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.grievance.service.GrievanceService;
import omis.grievance.service.delegate.GrievanceComplaintCategoryDelegate;
import omis.grievance.service.delegate.GrievanceDelegate;
import omis.grievance.service.delegate.GrievanceDispositionDelegate;
import omis.grievance.service.delegate.GrievanceDispositionReasonDelegate;
import omis.grievance.service.delegate.GrievanceDispositionStatusDelegate;
import omis.grievance.service.delegate.GrievanceLocationDelegate;
import omis.grievance.service.delegate.GrievanceNoteDelegate;
import omis.grievance.service.delegate.GrievanceResponseDueDateCalculatorDelegate;
import omis.grievance.service.delegate.GrievanceSubjectDelegate;
import omis.grievance.service.delegate.GrievanceUnitDelegate;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of grievance service.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.2 (Oct 3, 2015)
 * @since OMIS 3.0
 */
public class GrievanceServiceImpl
		implements GrievanceService {
	
	/* Resources. */
	
	private final GrievanceDelegate grievanceDelegate;
	
	private final GrievanceDispositionDelegate grievanceDispositionDelegate;
	
	private final GrievanceNoteDelegate grievanceNoteDelegate;
	
	private final GrievanceSubjectDelegate grievanceSubjectDelegate;
	
	private final GrievanceComplaintCategoryDelegate
	grievanceComplaintCategoryDelegate;
	
	private final GrievanceDispositionReasonDelegate
	grievanceDispositionReasonDelegate;
	
	private final GrievanceDispositionStatusDelegate
	grievanceDispositionStatusDelegate;
	
	private final GrievanceLocationDelegate grievanceLocationDelegate;
	
	private final GrievanceUnitDelegate grievanceUnitDelegate;
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final GrievanceResponseDueDateCalculatorDelegate
	grievanceResponseDueDateCalculatorDelegate;

	/* Constructors. */
	
	/**
	 * Instantiates implementation of grievance service.
	 * 
	 * @param grievanceDelegate delegate for grievances
	 * @param grievanceDispositionDelegate delegate for grievance dispositions
	 * @param grievanceNoteDelegate delegate for grievance notes
	 * @param grievanceSubjectDelegate delegate for grievance subjects
	 * @param grievanceComplaintCategoryDelegate delegate for grievance
	 * complaint categories
	 * @param grievanceDispositionReasonDelegate delegate for grievance
	 * disposition reasons 
	 * @param grievanceDispositionStatusDelegate delegate for grievance
	 * disposition statuses
	 * @param grievanceLocationDelegate delegate for grievance location
	 * @param grievanceUnitDelegate delegate for grievance unit
	 * @param userAccountDelegate delegate for user accounts
	 * @param grievanceResponseDueDateCalculatorDelegate delegate to calculate
	 * grievance response due date 
	 */
	public GrievanceServiceImpl(
			final GrievanceDelegate grievanceDelegate,
			final GrievanceDispositionDelegate grievanceDispositionDelegate,
			final GrievanceNoteDelegate grievanceNoteDelegate,
			final GrievanceSubjectDelegate grievanceSubjectDelegate,
			final GrievanceComplaintCategoryDelegate
			grievanceComplaintCategoryDelegate,
			final GrievanceDispositionReasonDelegate
			grievanceDispositionReasonDelegate,
			final GrievanceDispositionStatusDelegate
			grievanceDispositionStatusDelegate,
			final GrievanceLocationDelegate grievanceLocationDelegate,
			final GrievanceUnitDelegate grievanceUnitDelegate,
			final UserAccountDelegate userAccountDelegate,
			final GrievanceResponseDueDateCalculatorDelegate
				grievanceResponseDueDateCalculatorDelegate) {
		this.grievanceDelegate = grievanceDelegate;
		this.grievanceDispositionDelegate = grievanceDispositionDelegate;
		this.grievanceNoteDelegate = grievanceNoteDelegate;
		this.grievanceSubjectDelegate = grievanceSubjectDelegate;
		this.grievanceComplaintCategoryDelegate
			= grievanceComplaintCategoryDelegate;
		this.grievanceDispositionReasonDelegate
			= grievanceDispositionReasonDelegate;
		this.grievanceDispositionStatusDelegate
			= grievanceDispositionStatusDelegate;
		this.grievanceLocationDelegate = grievanceLocationDelegate;
		this.grievanceUnitDelegate = grievanceUnitDelegate;
		this.userAccountDelegate = userAccountDelegate;
		this.grievanceResponseDueDateCalculatorDelegate
			= grievanceResponseDueDateCalculatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Grievance> findGrievancesForOffender(
			final Offender offender) {
		return this.grievanceDelegate.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public Grievance create(final Offender offender,
			final GrievanceLocation location, final GrievanceUnit unit,
			final Date openedDate, final Date informalFileDate,
			final GrievanceSubject subject,
			final GrievanceComplaintCategory complaintCategory,
			final String description, final String initialComment)
					throws DuplicateEntityFoundException {
		Integer grievanceNumber
			= this.grievanceDelegate.findMaxGrievanceNumber() + 1;
		return this.grievanceDelegate.create(offender, location, unit, subject,
				complaintCategory, grievanceNumber, openedDate,
				informalFileDate, description, initialComment,
				null, null);
	}

	/** {@inheritDoc} */
	@Override
	public Grievance update(final Grievance grievance,
			final GrievanceLocation location, final GrievanceUnit unit,
			final Date openedDate, final Date informalFileDate,
			final GrievanceComplaintCategory complaintCategory,
			final String description, final String initialComment)
					throws DuplicateEntityFoundException {
		Integer grievanceNumber = grievance.getGrievanceNumber();
		Date closedDate = grievance.getClosedDate();
		GrievanceSubject subject = grievance.getSubject();
		return this.grievanceDelegate.update(grievance, location, unit,
				subject, complaintCategory, grievanceNumber, openedDate,
				informalFileDate, description, initialComment,closedDate);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceSubject> findSubjects() {
		return this.grievanceSubjectDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceComplaintCategory> findComplaintCategoriesBySubject(
		GrievanceSubject subject) {
		return this.grievanceComplaintCategoryDelegate.findBySubject(subject);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionReason> findDispositionReasons() {
		return this.grievanceDispositionReasonDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionReason> findDispositionReasonsByStatus(
			final GrievanceDispositionStatus status) {
		return this.grievanceDispositionReasonDelegate.findByStatus(status);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionStatus> findDispositionStatusesByLevels(
			final GrievanceDispositionLevel... levels) {
		return this.grievanceDispositionStatusDelegate.findByLevels(levels);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceDispositionStatus>
	findOpenedDispositionStatusesByLevels(
			final GrievanceDispositionLevel... levels) {
		return this.grievanceDispositionStatusDelegate
				.findByLevelsAndWhetherClosed(false, levels);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition findCoordinatorLevelDisposition(
			final Grievance grievance) {
		return this.grievanceDispositionDelegate.find(
				grievance, GrievanceDispositionLevel.COORDINATOR);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition findWardenLevelDisposition(
			final Grievance grievance) {
		return this.grievanceDispositionDelegate.find(
				grievance, GrievanceDispositionLevel.WARDEN);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition findFhaLevelDisposition(
			final Grievance grievance) {
		return this.grievanceDispositionDelegate.find(
				grievance, GrievanceDispositionLevel.FHA);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition findDirectorLevelDisposition(
			final Grievance grievance) {
		return this.grievanceDispositionDelegate.find(
				grievance, GrievanceDispositionLevel.DIRECTOR);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<GrievanceLocation> findLocations() {
		return this.grievanceLocationDelegate.findAll();
	}
	 
	/** {@inheritDoc} */
	@Override
	public List<GrievanceUnit> findUnits() {
		return this.grievanceUnitDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionStatus findPendingDispositionStatusForLevel(
			final GrievanceDispositionLevel level) {
		return this.grievanceDispositionStatusDelegate
				.findPendingForLevel(level);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition createCoordinatorLevelDisposition(
			final Grievance grievance, final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes)
					throws DuplicateEntityFoundException {
		if (status.getClosed()) {
			this.grievanceDelegate.close(grievance, closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
		}
		return this.grievanceDispositionDelegate.create(grievance,
				GrievanceDispositionLevel.COORDINATOR, status,
				reason, receivedDate, responseDueDate, responseBy,
				responseToOffenderDate, appealDate, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition updateCoordinatorLevelDisposition(
			final GrievanceDisposition coordinatorDisposition,
			final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes) {
		if (!GrievanceDispositionLevel.COORDINATOR.equals(
				coordinatorDisposition.getLevel())) {
			throw new IllegalArgumentException(
					"Coordinator level disposition required");
		}
		if (status.getClosed()) {
			this.grievanceDelegate.close(coordinatorDisposition.getGrievance(),
					closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
			this.grievanceDelegate.reopen(
					coordinatorDisposition.getGrievance());
		}
		return this.grievanceDispositionDelegate.update(coordinatorDisposition,
					status, reason, receivedDate, responseDueDate, responseBy,
					responseToOffenderDate, appealDate, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition createWardenLevelDisposition(
			final Grievance grievance, final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes)
						throws DuplicateEntityFoundException {
		if (status.getClosed()) {
			this.grievanceDelegate.close(grievance, closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
		}
		return this.grievanceDispositionDelegate.create(grievance,
				GrievanceDispositionLevel.WARDEN, status, reason, receivedDate,
				responseDueDate, responseBy, responseToOffenderDate,
				appealDate, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition updateWardenLevelDisposition(
			final GrievanceDisposition wardenDisposition,
			final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes) {
		if (!GrievanceDispositionLevel.WARDEN.equals(
				wardenDisposition.getLevel())) {
			throw new IllegalArgumentException(
					"Warden level disposition required");
		}
		if (status.getClosed()) {
			this.grievanceDelegate.close(wardenDisposition.getGrievance(),
					closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
			this.grievanceDelegate.reopen(wardenDisposition.getGrievance());
		}
		return this.grievanceDispositionDelegate.update(wardenDisposition,
					status, reason, receivedDate, responseDueDate, responseBy,
					responseToOffenderDate, appealDate, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition createFhaLevelDisposition(
			final Grievance grievance, final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes)
					throws DuplicateEntityFoundException {
		if (status.getClosed()) {
			this.grievanceDelegate.close(grievance, closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
		}
		return this.grievanceDispositionDelegate.create(grievance,
				GrievanceDispositionLevel.FHA, status, reason, receivedDate,
				responseDueDate, responseBy, responseToOffenderDate,
				appealDate, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition updateFhaLevelDisposition(
			final GrievanceDisposition fhaDisposition,
			final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final Date appealDate, final String notes) {
		if (!GrievanceDispositionLevel.FHA.equals(
				fhaDisposition.getLevel())) {
			throw new IllegalArgumentException(
					"FHA level disposition required");
		}
		if (status.getClosed()) {
			this.grievanceDelegate.close(fhaDisposition.getGrievance(),
					closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
			this.grievanceDelegate.reopen(fhaDisposition.getGrievance());
		}
		return this.grievanceDispositionDelegate.update(fhaDisposition,
					status, reason, receivedDate, responseDueDate, responseBy,
					responseToOffenderDate, appealDate, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition createDirectorLevelDisposition(
			final Grievance grievance, final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final String notes)
				throws DuplicateEntityFoundException {
		if (status.getClosed()) {
			this.grievanceDelegate.close(grievance, closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
		}
		return this.grievanceDispositionDelegate.create(grievance,
				GrievanceDispositionLevel.DIRECTOR, status, reason,
				receivedDate, responseDueDate, responseBy,
				responseToOffenderDate, null, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDisposition updateDirectorLevelDisposition(
			final GrievanceDisposition directorDisposition,
			final GrievanceDispositionStatus status,
			final Date closedDate, final GrievanceDispositionReason reason,
			final Date receivedDate, final Date responseDueDate,
			final UserAccount responseBy, final Date responseToOffenderDate,
			final String notes) {
		if (!GrievanceDispositionLevel.DIRECTOR.equals(
				directorDisposition.getLevel())) {
			throw new IllegalArgumentException(
					"Director level disposition required");
		}
		if (status.getClosed()) {
			this.grievanceDelegate.close(directorDisposition.getGrievance(),
					closedDate);
		} else {
			if (closedDate != null) {
				throw new IllegalArgumentException(
						"Closed date not allowed for open grievance");
			}
			this.grievanceDelegate.reopen(directorDisposition.getGrievance());
		}
		return this.grievanceDispositionDelegate.update(directorDisposition,
					status, reason, receivedDate, responseDueDate, responseBy,
					responseToOffenderDate, null, notes);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceNote addNote(final Grievance grievance, final Date date,
			final String value) throws DuplicateEntityFoundException {
		return this.grievanceNoteDelegate.create(grievance, date, value);
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceNote updateNote(final GrievanceNote note, final Date date,
			final String value) throws DuplicateEntityFoundException {
		return this.grievanceNoteDelegate.update(note, date, value);
	}

	/** {@inheritDoc} */
	@Override
	public void removeNote(final GrievanceNote note) {
		this.grievanceNoteDelegate.remove(note);
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceNote> findNotes(final Grievance grievance) {
		return this.grievanceNoteDelegate.findByGrievance(grievance);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> searchUserAccounts(final String query) {
		return this.userAccountDelegate.search(query);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccount(final String username) {
		return this.userAccountDelegate.findByUsername(username);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final Grievance grievance) {
		List<GrievanceDisposition> dispositions =
			this.grievanceDispositionDelegate.findByGrievance(grievance);
		for (int dispositionIndex = 0; dispositionIndex < dispositions.size();
				dispositionIndex++) {
			GrievanceDisposition disposition
				= dispositions.get(dispositionIndex);
			this.grievanceDispositionDelegate.remove(disposition);
		}
		this.grievanceDelegate.remove(grievance);
	}

	/** {@inheritDoc} */
	@Override
	public Date calculateResponseDueDate(final Date openedDate) {
		return this.grievanceResponseDueDateCalculatorDelegate
				.calculate(openedDate);
	}
}