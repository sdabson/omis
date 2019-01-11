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

import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.ExternalReferralAuthorizationRequestDao;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.exception.ExternalReferralAuthorizationRequestExistsException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate for external referral authorization requests.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public class ExternalReferralAuthorizationRequestDelegate {
	private final InstanceFactory<ExternalReferralAuthorizationRequest>
		externalReferralAuthorizationRequestInstanceFactory;
	private final ExternalReferralAuthorizationRequestDao
		externalReferralAuthorizationRequestDao;
	
	/**
	 * Instantiates a delegate for external referral authorization
	 * requests.
	 * @param externalReferralAuthorizationRequestInstanceFactory
	 * externalReferralAuthorizationRequestInstanceFactory
	 * @param externalReferralAuthorizationRequestDao
	 * externalReferralAuthorizationRequestDao
	 * 
	 */
	public ExternalReferralAuthorizationRequestDelegate(
		final InstanceFactory<ExternalReferralAuthorizationRequest>
		externalReferralAuthorizationRequestInstanceFactory,
		final ExternalReferralAuthorizationRequestDao
		externalReferralAuthorizationRequestDao) {
		this.externalReferralAuthorizationRequestInstanceFactory
			= externalReferralAuthorizationRequestInstanceFactory;
		this.externalReferralAuthorizationRequestDao
		= externalReferralAuthorizationRequestDao;
	}

	/**
	 * Creates and persists a external referral authorization request.
	 * 
	 * @param offender offender
	 * @param providerAssignment provider assignment
	 * @param medicalFacility medical facility
	 * @param facility facility
	 * @param reason reason
	 * @param reasonNotes reason notes
	 * @param referredByProviderAssignment referred provider assignment
	 * @param referredDate referred date
	 * @throws DuplicateEntityFoundException if request exists
	 */
	public ExternalReferralAuthorizationRequest create(
		final Offender offender, final Date date,
		final ProviderAssignment providerAssignment,
		final MedicalFacility medicalFacility,
		final Facility facility,
		final ExternalReferralReason reason,
		final String reasonNotes,
		final ProviderAssignment referredByProviderAssignment,
		final Date referredDate)
			throws ExternalReferralAuthorizationRequestExistsException {
		if(this.externalReferralAuthorizationRequestDao.find(offender,
			facility, referredDate, medicalFacility)!=null) {
			throw new ExternalReferralAuthorizationRequestExistsException(""
			+ "External referral authorization request already exists");
		}
		ExternalReferralAuthorizationRequest request
		= this.externalReferralAuthorizationRequestInstanceFactory
			.createInstance();
		request.setOffender(offender);
		request.setDate(date);
		request.setProviderAssignment(providerAssignment);
		request.setMedicalFacility(medicalFacility);
		request.setFacility(facility);
		request.setReason(reason);
		request.setReasonNotes(reasonNotes);
		request.setReferredByProviderAssignment(
			referredByProviderAssignment);
		request.setReferredDate(referredDate);
		return this.externalReferralAuthorizationRequestDao
				.makePersistent(request);
	}
}