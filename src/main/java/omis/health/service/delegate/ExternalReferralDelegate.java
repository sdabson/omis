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

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.dao.ExternalReferralDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.HealthRequest;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.exception.ExternalReferralException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for external referral.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov 7, 2018)
 * @since OMIS 3.0
 */
public class ExternalReferralDelegate {
	
	private final InstanceFactory<ExternalReferral>
	externalReferralInstanceFactory;
	
	private final ExternalReferralDao externalReferralDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a delegate for external referral.
	 * 
	 * @param externalReferralAssociationInstanceFactory
	 * instance factory for external referral association
	 * @param externalReferralAssociationDao data access object
	 * for external referral association
	 * @param auditComponentRetriever audit component retriever
	 */
	public ExternalReferralDelegate(
		final InstanceFactory<ExternalReferral>
		externalReferralInstanceFactory,
		final ExternalReferralDao externalReferralDao,
		final AuditComponentRetriever auditComponentRetriever) {
		this.externalReferralInstanceFactory
		= externalReferralInstanceFactory;
		this.externalReferralDao = externalReferralDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates and persists a external referral.
	 * 
	 * @param offenderAppointmentAssociation offender appointment
	 * association
	 * @param externalReferralStatusReason external referral status
	 * reason
	 * @param rescheduleRequired reschedule required
	 * @param schedulingNotes scheduling notes
	 * @param assessmentNotes assessment notes
	 * @param reportedDate reported date
	 * @param actionrequest action request
	 * @param authorization external referral authorization
	 * @return external referral
	 * @throws ExternalReferralException if already exists
	 */
	public ExternalReferral create(
		final OffenderAppointmentAssociation offenderAppointmentAssociation,
		final ExternalReferralStatusReason externalReferralStatusReason,
		final Boolean rescheduleRequired, final String schedulingNotes,
		final String assessmentNotes, final Date reportedDate,
		final HealthRequest actionrequest,
		final ExternalReferralAuthorization authorization)
			throws ExternalReferralException {
		if(this.externalReferralDao
			.findExisting(offenderAppointmentAssociation)!=null) {
			throw new ExternalReferralException("External referral"
					+ "already exists");
		}
		
		ExternalReferral er
		= this.externalReferralInstanceFactory.createInstance();
		er.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		er.setStatusReason(externalReferralStatusReason);
		er.setSchedulingNotes(schedulingNotes);
		er.setRescheduleRequired(rescheduleRequired);
		er.setReportedDate(reportedDate);
		er.setOffenderAppointmentAssociation(offenderAppointmentAssociation);
		er.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(), 
			this.auditComponentRetriever.retrieveDate()));
		er.setAuthorization(authorization);
		return this.externalReferralDao.makePersistent(er);
		
	}
}