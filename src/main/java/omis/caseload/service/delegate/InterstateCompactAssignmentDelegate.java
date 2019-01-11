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
package omis.caseload.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.dao.InterstateCompactAssignmentDao;
import omis.caseload.domain.InterstateCompactAssignment;
import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.region.domain.State;

/**
 * Interstate compact assignment delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactAssignmentDelegate {

	/* DAOs */
	private final InterstateCompactAssignmentDao interstateCompactAssignmentDao;
	
	/* Instance factory. */
	private final InstanceFactory<InterstateCompactAssignment> 
					interstateCompactAssignmentInstanceFactory;
	
	/* Helpers. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of interstate compact assignment delegate 
	 * with the specified data access object, instance factory and audit 
	 * component retriever.
	 * 
	 * @param interstateCompactAssignmentDao interstate compact assignment data 
	 * access object
	 * @param interstateCompactAssignmentInstanceFactory interstate compact 
	 * assignment instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public InterstateCompactAssignmentDelegate(
			final InterstateCompactAssignmentDao interstateCompactAssignmentDao,
			final InstanceFactory<InterstateCompactAssignment> 
					interstateCompactAssignmentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.interstateCompactAssignmentDao = interstateCompactAssignmentDao;
		this.interstateCompactAssignmentInstanceFactory = 
				interstateCompactAssignmentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new interstate compact assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @param jurisdiction jurisdiction
 	 * @param jurisdictionStateId jurisdiction state id
	 * @param projectedEndDate projected end date
	 * @param interstateCompactStatus interstate compact correctional status
	 * @param endReason interstate compact end reason category
	 * @param interstateCompactType interstate compact type category
	 * @return interstate compact assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactAssignment create(
			OfficerCaseAssignment officerCaseAssignment, State jurisdiction, 
			String jurisdictionStateId, Date projectedEndDate, 
			InterstateCompactCorrectionalStatus interstateCompactStatus, 
			InterstateCompactEndReasonCategory endReason, 
			InterstateCompactTypeCategory interstateCompactType) 
					throws DuplicateEntityFoundException {
		if (this.interstateCompactAssignmentDao.find(officerCaseAssignment) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact assignment already exists.");
		}
		InterstateCompactAssignment interstateCompactAssignment = this
				.interstateCompactAssignmentInstanceFactory.createInstance();
		populateInterstateCompactAssignment(interstateCompactAssignment,
				officerCaseAssignment, jurisdiction, jurisdictionStateId,
				projectedEndDate, interstateCompactStatus, endReason,
				interstateCompactType);
		interstateCompactAssignment.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		return this.interstateCompactAssignmentDao.makePersistent(
				interstateCompactAssignment);
	}
	
	/**
	 * Updates an existing interstate compact assignment.
	 * 
	 * @param interstateCompactAssignment interstate compact assignment
	 * @param officerCaseAssignment officer case assignment
	 * @param jurisdiction jurisdiction
 	 * @param jurisdictionStateId jurisdiction state id
	 * @param projectedEndDate projected end date
	 * @param interstateCompactStatus interstate compact correctional status
	 * @param endReason interstate compact end reason category
	 * @param interstateCompactType interstate compact type category
	 * @return interstate compact assignment
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactAssignment update(
			InterstateCompactAssignment interstateCompactAssignment, 
			OfficerCaseAssignment officerCaseAssignment, State jurisdiction, 
			String jurisdictionStateId, Date projectedEndDate, 
			InterstateCompactCorrectionalStatus interstateCompactStatus, 
			InterstateCompactEndReasonCategory endReason, 
			InterstateCompactTypeCategory interstateCompactType) 
					throws DuplicateEntityFoundException {
		if (this.interstateCompactAssignmentDao.findExcluding(
				officerCaseAssignment, interstateCompactAssignment) != null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact assignment already exists.");
		}
		populateInterstateCompactAssignment(interstateCompactAssignment,
				officerCaseAssignment, jurisdiction, jurisdictionStateId,
				projectedEndDate, interstateCompactStatus, endReason,
				interstateCompactType);
		return this.interstateCompactAssignmentDao.makePersistent(
				interstateCompactAssignment);
	}

	/**
	 * Removes the specified interstate compact assignment.
	 * 
	 * @param interstateCompactAssignment interstate compact assignment
	 */
	public void remove(InterstateCompactAssignment interstateCompactAssignment) {
		this.interstateCompactAssignmentDao.makeTransient(
				interstateCompactAssignment);
	}
	
	/**
	 * Returns the interstate compact assignment for the specified officer case 
	 * assignment.
	 * 
	 * @param officerCaseAssignment officer case assignment
	 * @return interstate compact assignment
	 */
	public InterstateCompactAssignment findByOfficerCaseAssignment(
			OfficerCaseAssignment officerCaseAssignment) {
		return this.interstateCompactAssignmentDao.findByOfficerCaseAssignment(
				officerCaseAssignment);
	}
	
	//Populates an interstate compact assignment
	private void populateInterstateCompactAssignment(
			InterstateCompactAssignment interstateCompactAssignment,
			OfficerCaseAssignment officerCaseAssignment, State jurisdiction,
			String jurisdictionStateId, Date projectedEndDate,
			InterstateCompactCorrectionalStatus interstateCompactStatus,
			InterstateCompactEndReasonCategory endReason,
			InterstateCompactTypeCategory interstateCompactType) {
		interstateCompactAssignment.setOfficerCaseAssignment(
				officerCaseAssignment);
		interstateCompactAssignment.setJurisdiction(jurisdiction);
		interstateCompactAssignment.setJurisdictionStateId(jurisdictionStateId);
		interstateCompactAssignment.setProjectedEndDate(projectedEndDate);
		interstateCompactAssignment.setInterstateCompactStatus(
				interstateCompactStatus);
		interstateCompactAssignment.setEndReason(endReason);
		interstateCompactAssignment.setInterstateCompactType(
				interstateCompactType);
		interstateCompactAssignment.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
	}
}