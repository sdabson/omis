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
package omis.paroleboardmember.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleboardmember.dao.ParoleBoardMemberDao;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;

/**
 * Parole board member delegate.
 *
 * @author Josh Divine
 * @version 0.1.2 (Nov 28, 2018)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberDelegate {

	/* Data access objects. */
	
	private final ParoleBoardMemberDao paroleBoardMemberDao;

	/* Instance factories. */
	
	private final InstanceFactory<ParoleBoardMember> 
			paroleBoardMemberInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of parole board member delegate with the 
	 * specified date access object and instance factory.
	 * 
	 * @param paroleBoardMemberDao parole board member data access object
	 * @param paroleBoardMemberInstanceFactory parole board member instance 
	 * factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public ParoleBoardMemberDelegate(
			final ParoleBoardMemberDao paroleBoardMemberDao,
			final InstanceFactory<ParoleBoardMember> 
				paroleBoardMemberInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.paroleBoardMemberDao = paroleBoardMemberDao;
		this.paroleBoardMemberInstanceFactory = 
				paroleBoardMemberInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a new parole board member.
	 * 
	 * @param staffAssignment staff assignment
	 * @param dateRange date range
	 * @return parole board member
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date range not within staff assignment 
	 * date range or if an existing parole board member conflicts with the 
	 * specified date range
	 */
	public ParoleBoardMember create(final StaffAssignment staffAssignment,
			final DateRange dateRange) throws DuplicateEntityFoundException, 
					DateConflictException {
		if (this.paroleBoardMemberDao.find(staffAssignment, 
				DateRange.getStartDate(dateRange)) != null) {
			throw new DuplicateEntityFoundException(
					"Parole board member already exists.");
		}
		if (!DateRange.occursWithin(dateRange, 
				staffAssignment.getDateRange())) {
			throw new DateConflictException("Parole board member date range "
					+ "must be within the staff assignment date range.");
		}
		if (!this.paroleBoardMemberDao.findExistingWithinDateRange(
				staffAssignment, dateRange.getStartDate(), 
				dateRange.getEndDate()).isEmpty()) {
			throw new DateConflictException("Parole board member already exists"
					+ " for this staff assignment on the specified date range.");
		}
		ParoleBoardMember paroleBoardMember = this
				.paroleBoardMemberInstanceFactory.createInstance();
		paroleBoardMember.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		populateParoleBoardMember(paroleBoardMember, staffAssignment, 
				dateRange);
		return this.paroleBoardMemberDao.makePersistent(paroleBoardMember);
	}

	/**
	 * Updates an existing parole board member.
	 * 
	 * @param paroleBoardMember parole board member
	 * @param staffAssignment staff assignment
	 * @param dateRange date range
	 * @return parole board member
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date range not within staff assignment 
	 * date range or if an existing parole board member conflicts with the 
	 * specified date range
	 */
	public ParoleBoardMember update(final ParoleBoardMember paroleBoardMember,
			final StaffAssignment staffAssignment, final DateRange dateRange) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		if (this.paroleBoardMemberDao.findExcluding(staffAssignment, 
				DateRange.getStartDate(dateRange), paroleBoardMember) != null) {
			throw new DuplicateEntityFoundException(
					"Parole board member already exists.");
		}
		if (!DateRange.occursWithin(dateRange, 
				staffAssignment.getDateRange())) {
			throw new DateConflictException("Parole board member date range "
					+ "must be within the staff assignment date range.");
		}
		if (!this.paroleBoardMemberDao.findExistingWithinDateRangeExcluding(
				staffAssignment, dateRange.getStartDate(), 
				dateRange.getEndDate(), paroleBoardMember).isEmpty()) {
			throw new DateConflictException("Parole board member already exists"
					+ " for this staff assignment on the specified date range.");
		}
		populateParoleBoardMember(paroleBoardMember, staffAssignment, 
				dateRange);
		return this.paroleBoardMemberDao.makePersistent(paroleBoardMember);
	}
	
	/**
	 * Removes the specified parole board member.
	 * 
	 * @param paroleBoardMember parole board member
	 */
	public void remove(final ParoleBoardMember paroleBoardMember) {
		this.paroleBoardMemberDao.makeTransient(paroleBoardMember);
	}
	

	/**
	 * Returns a list of parole board members on the effective date.
	 * 
	 * @param effectiveDate effective date
	 * @return list of all parole board members
	 */
	public List<ParoleBoardMember> findBoardMembersByDate(
			final Date effectiveDate) {
		return this.paroleBoardMemberDao.findBoardMembersByDate(effectiveDate);
	}
	
	// Populates a parole board member.
	private void populateParoleBoardMember(
			final ParoleBoardMember paroleBoardMember, 
			final StaffAssignment staffAssignment,
			final DateRange dateRange) {
		paroleBoardMember.setStaffAssignment(staffAssignment);
		paroleBoardMember.setDateRange(dateRange);
		paroleBoardMember.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
	}
}