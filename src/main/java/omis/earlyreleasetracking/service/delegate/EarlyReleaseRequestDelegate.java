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
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.docket.domain.Docket;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestDao;
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.user.domain.UserAccount;

/**
 * Early Release Request Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Early Release Request already exists with the given Docket and "
			+ "Request Date.";
	
	private final EarlyReleaseRequestDao earlyReleaseRequestDao;
	
	private final InstanceFactory<EarlyReleaseRequest> 
		earlyReleaseRequestInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EarlyReleaseRequestDelegate.
	 * 
	 * @param earlyReleaseRequestDao Early Release Request DAO
	 * @param earlyReleaseRequestInstanceFactory Early Release Request
	 * Instance Factory
	 * @param auditComponentRetriever Audit Component Retriever
	 */
	public EarlyReleaseRequestDelegate(
			final EarlyReleaseRequestDao earlyReleaseRequestDao,
			final InstanceFactory<EarlyReleaseRequest> 
				earlyReleaseRequestInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.earlyReleaseRequestDao = earlyReleaseRequestDao;
		this.earlyReleaseRequestInstanceFactory =
				earlyReleaseRequestInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates an Early Release Request with the given properties.
	 * 
	 * @param docket Docket
	 * @param requestDate Request Date
	 * @param category Category
	 * @param requestStatus Request Status
	 * @param judgeResponse Judge Response
	 * @param requestBy Request By
	 * @param approvalDate Approval Date
	 * @param comments Comments
	 * @return Newly created Early Release Request
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * already exists with the given Docket and Request Date
	 */
	public EarlyReleaseRequest create(final Docket docket,
			final Date requestDate, final EarlyReleaseRequestCategory category,
			final EarlyReleaseStatusCategory requestStatus,
			final EarlyReleaseJudgeResponseCategory judgeResponse,
			final UserAccount requestBy, final Date approvalDate,
			final String comments)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseRequestDao.find(docket, requestDate) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EarlyReleaseRequest earlyReleaseRequest = 
				this.earlyReleaseRequestInstanceFactory.createInstance();
		
		earlyReleaseRequest.setApprovalDate(approvalDate);
		earlyReleaseRequest.setCategory(category);
		earlyReleaseRequest.setComments(comments);
		earlyReleaseRequest.setDocket(docket);
		earlyReleaseRequest.setJudgeResponse(judgeResponse);
		earlyReleaseRequest.setRequestBy(requestBy);
		earlyReleaseRequest.setRequestDate(requestDate);
		earlyReleaseRequest.setRequestStatus(requestStatus);
		earlyReleaseRequest.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		earlyReleaseRequest.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestDao.makePersistent(earlyReleaseRequest);
	}
	
	/**
	 * Update the specified Early Release Request with the given properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request to update
	 * @param docket Docket
	 * @param requestDate Request Date
	 * @param category Category
	 * @param requestStatus Request Status
	 * @param judgeResponse Judge Response
	 * @param requestBy Request By
	 * @param approvalDate Approval Date
	 * @param comments Comments
	 * @return Updated Early Release Request
	 * @throws DuplicateEntityFoundException When a Early Release Request
	 * already exists with the given Docket and Request Date
	 */
	public EarlyReleaseRequest update(
			final EarlyReleaseRequest earlyReleaseRequest, final Docket docket,
			final Date requestDate, final EarlyReleaseRequestCategory category,
			final EarlyReleaseStatusCategory requestStatus,
			final EarlyReleaseJudgeResponseCategory judgeResponse,
			final UserAccount requestBy, final Date approvalDate,
			final String comments)
					throws DuplicateEntityFoundException {
		if (this.earlyReleaseRequestDao.findExcluding(docket, requestDate,
				earlyReleaseRequest) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		earlyReleaseRequest.setApprovalDate(approvalDate);
		earlyReleaseRequest.setCategory(category);
		earlyReleaseRequest.setComments(comments);
		earlyReleaseRequest.setDocket(docket);
		earlyReleaseRequest.setJudgeResponse(judgeResponse);
		earlyReleaseRequest.setRequestBy(requestBy);
		earlyReleaseRequest.setRequestDate(requestDate);
		earlyReleaseRequest.setRequestStatus(requestStatus);
		earlyReleaseRequest.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.earlyReleaseRequestDao.makePersistent(earlyReleaseRequest);
	}
	
	/**
	 * Removes the specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request to remove
	 */
	public void remove(final EarlyReleaseRequest earlyReleaseRequest) {
		this.earlyReleaseRequestDao.makeTransient(earlyReleaseRequest);
	}
}
