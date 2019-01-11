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

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.dao.InternalReferralDao;
import omis.health.domain.HealthRequest;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;
import omis.health.exception.InternalReferralExistsException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for internal referral.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 31, 2018)
 * @since OMIS 3.0
 */
public class InternalReferralDelegate {
	private final InstanceFactory<InternalReferral>
		internalReferralInstanceFactory;
	private final InternalReferralDao internalReferralDao;
	
	/**
	 * Instantiates a delegate for internal referral.
	 * @param internalReferralInstanceFactory
	 * internal referral instance factory
	 * @param internalReferralDao internal referral dao
	 * 
	 */
	public InternalReferralDelegate(
		final InstanceFactory<InternalReferral>
		internalReferralInstanceFactory,
		final InternalReferralDao internalReferralDao) {
		this.internalReferralInstanceFactory
			= internalReferralInstanceFactory;
		this.internalReferralDao = internalReferralDao;
	}

	/**
	 * Creates and persists a internal referral.
	 * 
	 * @param offenderAppointmentAssociation offender appointment
	 * association
	 * @param providerLevel provider level
	 * @param reason internal referral reason
	 * @param statusReason internal referral status reason
	 * @param rescheduleRequired rescheduleRequired
	 * @param schedulingNotes schedulingNotes
	 * @param assessmentNotes assessmentNotes
	 * @param actionRequest health request
	 * @param creationSignature creation signature
	 * @param updateSignature update signature
	 * @param locationDesignator location designator
	 * @throws InternalReferralExistsException if assignment exists
	 * @return InternalReferral InternalReferral
	 */
	public InternalReferral create(
		final OffenderAppointmentAssociation offenderAppointmentAssociation,
		final ProviderLevel providerLevel,
		final InternalReferralReason reason,
		final InternalReferralStatusReason statusReason,
		final Boolean recheduleRequired,
		final String schedulingNotes, final String assessmentNotes,
		final HealthRequest actionRequest,
		final CreationSignature creationSignature,
		final UpdateSignature updateSignature,
		final ReferralLocationDesignator locationDesignator)
			throws InternalReferralExistsException {
		if(this.internalReferralDao.findExisting(
			offenderAppointmentAssociation)!=null) {
			throw new InternalReferralExistsException("Internal"
					+ "referral already exists"); 
		}
		InternalReferral referral
		= this.internalReferralInstanceFactory.createInstance();
		referral.setActionRequest(actionRequest);
		referral.setAssessmentNotes(assessmentNotes);
		referral.setCreationSignature(creationSignature);
		referral.setLocationDesignator(locationDesignator);
		referral.setOffenderAppointmentAssociation(offenderAppointmentAssociation);
		referral.setProviderLevel(providerLevel);
		referral.setReason(reason);
		referral.setRescheduleRequired(recheduleRequired);
		referral.setSchedulingNotes(schedulingNotes);
		referral.setStatusReason(statusReason);
		referral.setUpdateSignature(updateSignature);
		return this.internalReferralDao.makePersistent(referral);
	} 
}