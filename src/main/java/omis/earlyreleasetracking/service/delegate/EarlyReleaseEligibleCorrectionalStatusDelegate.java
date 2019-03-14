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
package omis.earlyreleasetracking.service.delegate;

import java.util.List;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.earlyreleasetracking.dao.EarlyReleaseEligibleCorrectionalStatusDao;
import omis.earlyreleasetracking.domain.EarlyReleaseEligibleCorrectionalStatus;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Early Release Eligible Correctional Status Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Mar 11, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseEligibleCorrectionalStatusDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Eligible Correctional Status already exists with "
			+ "the given Correctional Status.";
	
	private final EarlyReleaseEligibleCorrectionalStatusDao
		earlyReleaseEligibleCorrectionalStatusDao;
	
	private final InstanceFactory<EarlyReleaseEligibleCorrectionalStatus>
		earlyReleaseEligibleCorrectionalStatusInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseEligibleCorrectionalStatusDelegate.
	 * 
	 * @param earlyReleaseEligibleCorrectionalStatusDao Early Release Eligible
	 * Correctional Status DAO
	 * @param earlyReleaseEligibleCorrectionalStatusInstanceFactory Early
	 * Release Eligible Correctional Status Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseEligibleCorrectionalStatusDelegate(
			final EarlyReleaseEligibleCorrectionalStatusDao
				earlyReleaseEligibleCorrectionalStatusDao,
			final InstanceFactory<EarlyReleaseEligibleCorrectionalStatus> 
				earlyReleaseEligibleCorrectionalStatusInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseEligibleCorrectionalStatusDao =
				earlyReleaseEligibleCorrectionalStatusDao;
		this.earlyReleaseEligibleCorrectionalStatusInstanceFactory =
				earlyReleaseEligibleCorrectionalStatusInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Early Release Eligible Correctional Status with
	 * the given properties.
	 * 
	 * @param correctionalStatus Correctional Status
	 * @param valid Valid
	 * @return Newly created Early Release Eligible Correctional Status
	 * @throws DuplicateEntityFoundException When a Early Release Eligible
	 * Correctional Status already exists with the given Correctional Status.
	 */
	public EarlyReleaseEligibleCorrectionalStatus create(
			final CorrectionalStatus correctionalStatus, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseEligibleCorrectionalStatusDao.find(
				correctionalStatus) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseEligibleCorrectionalStatus
			earlyReleaseEligibleCorrectionalStatus = 
				this.earlyReleaseEligibleCorrectionalStatusInstanceFactory
				.createInstance();
		
		earlyReleaseEligibleCorrectionalStatus.setCorrectionalStatus(
				correctionalStatus);
		earlyReleaseEligibleCorrectionalStatus.setValid(valid);
		earlyReleaseEligibleCorrectionalStatus.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseEligibleCorrectionalStatus.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseEligibleCorrectionalStatusDao.makePersistent(
				earlyReleaseEligibleCorrectionalStatus);
	}
	
	/**
	 * Updates the specified Early Release Eligible Correctional Status with
	 * the given properties.
	 * 
	 * @param earlyReleaseEligibleCorrectionalStatus Early Release Eligible
	 * Correctional Status to update
	 * @param correctionalStatus Correctional Status
	 * @param valid Valid
	 * @return Updated Early Release Eligible Correctional Status
	 * @throws DuplicateEntityFoundException When a Early Release Eligible
	 * Correctional Status already exists with the given Correctional Status.
	 */
	public EarlyReleaseEligibleCorrectionalStatus update(
			final EarlyReleaseEligibleCorrectionalStatus
				earlyReleaseEligibleCorrectionalStatus,
			final CorrectionalStatus correctionalStatus, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseEligibleCorrectionalStatusDao.findExcluding(
				correctionalStatus, earlyReleaseEligibleCorrectionalStatus)
				!= null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		earlyReleaseEligibleCorrectionalStatus.setCorrectionalStatus(
				correctionalStatus);
		earlyReleaseEligibleCorrectionalStatus.setValid(valid);
		earlyReleaseEligibleCorrectionalStatus.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseEligibleCorrectionalStatusDao.makePersistent(
				earlyReleaseEligibleCorrectionalStatus);
	}
	
	/**
	 * Removes the specified Early Release Eligible Correctional Status.
	 * 
	 * @param earlyReleaseEligibleCorrectionalStatus Early Release Eligible
	 * Correctional Status
	 */
	public void remove(final EarlyReleaseEligibleCorrectionalStatus
			earlyReleaseEligibleCorrectionalStatus) {
		this.earlyReleaseEligibleCorrectionalStatusDao.makeTransient(
				earlyReleaseEligibleCorrectionalStatus);
	}
	
	/**
	 * Returns a list of all valid Early Release Eligible Correctional
	 * Statuses.
	 * 
	 * @return List of all valid Early Release Eligible Correctional
	 * Statuses.
	 */
	public List<EarlyReleaseEligibleCorrectionalStatus> findAllStatuses() {
		return this.earlyReleaseEligibleCorrectionalStatusDao.findAllStatuses();
	}
}
