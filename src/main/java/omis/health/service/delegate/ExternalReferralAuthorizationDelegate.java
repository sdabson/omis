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
package omis.health.service.delegate;

import java.util.Date;

import omis.health.dao.ExternalReferralAuthorizationDao;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralAuthorizationStatus;
import omis.health.domain.component.ExternalReferralMedicalPanelReviewDecision;
import omis.health.exception.ExternalReferralAuthorizationExistsException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/**
 * Delegate for external referral authorization.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 29, 2018)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationDelegate {
	private final InstanceFactory<ExternalReferralAuthorization>
		externalReferralAuthorizationInstanceFactory;
	private final ExternalReferralAuthorizationDao
		externalReferralAuthorizationDao;
	
	/**
	 * Instantiates a delegate for external referral authorization
	 * 
	 * @param externalReferralAuthorizationInstanceFactory
	 * externalReferralAuthorizationInstanceFactory
	 * @param externalReferralAuthorizationDao
	 * externalReferralAuthorizationDao
	 * 
	 */
	public ExternalReferralAuthorizationDelegate(
		final InstanceFactory<ExternalReferralAuthorization>
		externalReferralAuthorizationInstanceFactory,
		final ExternalReferralAuthorizationDao
		externalReferralAuthorizationDao) {
		this.externalReferralAuthorizationInstanceFactory
			= externalReferralAuthorizationInstanceFactory;
		this.externalReferralAuthorizationDao
			= externalReferralAuthorizationDao;
	}

	/**
	 * Creates and persists a external referral authorization.
	 * 
	 * @param request external referral authorization request
	 * @param decisionDate decision date
	 * @param authorizedBy authorized by
	 * @param notes notes
	 * @param authorizationStatus external referral authorization status
	 * @param medicalPanelReviewDecision medical panel review decision
	 * @throws ExternalReferralAuthorizationExistsException if external
	 * referral authorization exists
	 */
	public ExternalReferralAuthorization create(
		final ExternalReferralAuthorizationRequest request,
		final Date decisionDate, final Person authorizedBy,
		final String notes,
		final ExternalReferralAuthorizationStatus authorizationStatus,
		final ExternalReferralMedicalPanelReviewDecision
			medicalPanelReviewDecision)
			throws ExternalReferralAuthorizationExistsException {
		if(this.externalReferralAuthorizationDao
			.findByRequest(request)!=null) {
			throw new ExternalReferralAuthorizationExistsException(""
			+ "External referral authorization already exists");
		}
		
		ExternalReferralAuthorization externalReferralAuthorization
			= this.externalReferralAuthorizationInstanceFactory
			.createInstance();
		externalReferralAuthorization.setStatus(authorizationStatus);
		externalReferralAuthorization.setRequest(request);
		externalReferralAuthorization.setPanelReviewDecision(
			medicalPanelReviewDecision);
		externalReferralAuthorization.setNotes(notes);
		externalReferralAuthorization.setDecisionDate(decisionDate);
		externalReferralAuthorization.setAuthorizedBy(authorizedBy);
				
		return this.externalReferralAuthorizationDao.makePersistent(
			externalReferralAuthorization);
	}
}