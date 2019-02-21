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

import java.util.Date;
import java.util.List;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestInternalApprovalDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestInternalApproval;
import omis.earlyreleasetracking.domain.InternalApprovalDecisionCategory;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestInternalApprovalExists;
import omis.instance.factory.InstanceFactory;

/**
 * Early Release Request Internal Approval Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestInternalApprovalDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Request Internal Approval already exists with the"
			+ "given Name and Early Release Request.";
	
	private final EarlyReleaseRequestInternalApprovalDao
			earlyReleaseRequestInternalApprovalDao;
	
	private final InstanceFactory<EarlyReleaseRequestInternalApproval>
		earlyReleaseRequestInternalApprovalInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseRequestInternalApprovalDelegate.
	 * 
	 * @param earlyReleaseRequestInternalApprovalDao Early Release Request
	 * Internal Approval DAO
	 * @param earlyReleaseRequestInternalApprovalInstanceFactory Early Release
	 * Request Internal Approval Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseRequestInternalApprovalDelegate(
			final EarlyReleaseRequestInternalApprovalDao
				earlyReleaseRequestInternalApprovalDao,
			final InstanceFactory<EarlyReleaseRequestInternalApproval> 
				earlyReleaseRequestInternalApprovalInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseRequestInternalApprovalDao =
				earlyReleaseRequestInternalApprovalDao;
		this.earlyReleaseRequestInternalApprovalInstanceFactory =
				earlyReleaseRequestInternalApprovalInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates an Early Release Request Internal Approval with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param name Name
	 * @param date Date
	 * @param decision Decision
	 * @param narrative Narrative
	 * @return Newly created Early Release Request Internal Approval
	 * @throws EarlyReleaseRequestInternalApprovalExists When a Early Release
	 * Request Internal Approval already exists with the given Name and Early
	 * Release Request.
	 */
	public EarlyReleaseRequestInternalApproval create(
			final EarlyReleaseRequest earlyReleaseRequest,
			final String name, final Date date,
			final InternalApprovalDecisionCategory decision,
			final String narrative)
					throws EarlyReleaseRequestInternalApprovalExists {
		if (this.earlyReleaseRequestInternalApprovalDao.find(
				earlyReleaseRequest, name) != null) {
			throw new EarlyReleaseRequestInternalApprovalExists(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseRequestInternalApproval
			earlyReleaseRequestInternalApproval = 
				this.earlyReleaseRequestInternalApprovalInstanceFactory
				.createInstance();

		earlyReleaseRequestInternalApproval.setDate(date);
		earlyReleaseRequestInternalApproval.setDecision(decision);
		earlyReleaseRequestInternalApproval.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestInternalApproval.setName(name);
		earlyReleaseRequestInternalApproval.setNarrative(narrative);
		earlyReleaseRequestInternalApproval.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseRequestInternalApproval.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestInternalApprovalDao.makePersistent(
				earlyReleaseRequestInternalApproval);
	}
	
	/**
	 * Updates the specified Early Release Request Internal Approval with the
	 * given properties.
	 * 
	 * @param earlyReleaseRequestInternalApproval Early Release Request Internal
	 * Approval to update
	 * @param earlyReleaseRequest Early Release Request
	 * @param name Name
	 * @param date Date
	 * @param decision Decision
	 * @param narrative Narrative
	 * @return Updated Early Release Request Internal Approval
	 * @throws EarlyReleaseRequestInternalApprovalExists When a Early Release
	 * Request Internal Approval already exists with the given Name and Early
	 * Release Request.
	 */
	public EarlyReleaseRequestInternalApproval update(
			final EarlyReleaseRequestInternalApproval
				earlyReleaseRequestInternalApproval,
			final EarlyReleaseRequest earlyReleaseRequest,
			final String name, final Date date,
			final InternalApprovalDecisionCategory decision,
			final String narrative)
					throws EarlyReleaseRequestInternalApprovalExists {
		if (this.earlyReleaseRequestInternalApprovalDao.findExcluding(
				earlyReleaseRequest, name,
				earlyReleaseRequestInternalApproval) != null) {
			throw new EarlyReleaseRequestInternalApprovalExists(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		earlyReleaseRequestInternalApproval.setDate(date);
		earlyReleaseRequestInternalApproval.setDecision(decision);
		earlyReleaseRequestInternalApproval.setEarlyReleaseRequest(
				earlyReleaseRequest);
		earlyReleaseRequestInternalApproval.setName(name);
		earlyReleaseRequestInternalApproval.setNarrative(narrative);
		earlyReleaseRequestInternalApproval.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestInternalApprovalDao.makePersistent(
				earlyReleaseRequestInternalApproval);
	}
	
	/**
	 * Removes the specified Early Release Request Internal Approval.
	 * 
	 * @param earlyReleaseRequestInternalApproval Early Release Request Internal
	 * Approval to remove
	 */
	public void remove(final EarlyReleaseRequestInternalApproval
			earlyReleaseRequestInternalApproval) {
		this.earlyReleaseRequestInternalApprovalDao.makeTransient(
				earlyReleaseRequestInternalApproval);
	}
	
	/**
	 * Returns a list ofEarly Release Request Internal Approvals by the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Internal Approvals by the specified
	 * Early Release Request.
	 */
	public List<EarlyReleaseRequestInternalApproval> findByEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		return this.earlyReleaseRequestInternalApprovalDao
				.findByEarlyReleaseRequest(earlyReleaseRequest);
	}
	
}
