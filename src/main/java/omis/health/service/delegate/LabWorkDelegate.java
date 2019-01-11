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

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.health.dao.LabWorkDao;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.LabWorkException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for lab work.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov. 7, 2018)
 * @since OMIS 3.0
 */
public class LabWorkDelegate {
	private final InstanceFactory<LabWork>
		labWorkInstanceFactory;
	private final LabWorkDao labWorkDao;
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a delegate for lab work
	 * 
	 * @param labWorkInstanceFactory lab work instance factory
	 * @param labWorkDao data access object for lab work 
	 */
	public LabWorkDelegate(
		final InstanceFactory<LabWork> labWorkInstanceFactory,
		final LabWorkDao labWorkDao,
		final AuditComponentRetriever auditComponentRetriever) {
		this.labWorkInstanceFactory = labWorkInstanceFactory;
		this.labWorkDao = labWorkDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates and persists a lab work.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 * @param rescheduleRequired reschedule required
	 * @param schedulingNotes scheduling notes
	 * @param sampleNotes sample notes
	 * @param order order
	 * @param sampleLab sample lab
	 * @param labWorkCategory lab work category
	 * @param results results
	 * @param sampleRestrictions sample restrictions
	 * @param sampleTaken sample taken
	 * @throws LabWorkException if already exists
	 */
	public LabWork create(
		final OffenderAppointmentAssociation offenderAppointmentAssociation,
		final Boolean rescheduleRequired, final String schedulingNotes,
		final String sampleNotes, final LabWorkOrder order,
		final Lab sampleLab, final LabWorkCategory labWorkCategory,
		final LabWorkResults results,
		final LabWorkSampleRestrictions sampleRestrictions,
		final Boolean sampleTaken) throws LabWorkException {
		if(this.labWorkDao.findExisting(offenderAppointmentAssociation)!=null) {
			throw new LabWorkException("Lab work already exists");
		}
		
		LabWork labWork = this.labWorkInstanceFactory.createInstance();
		labWork.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		labWork.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		labWork.setSchedulingNotes(schedulingNotes);
		labWork.setSampleTaken(sampleTaken);
		labWork.setSampleRestrictions(sampleRestrictions);
		labWork.setSampleNotes(sampleNotes);
		labWork.setSampleLab(sampleLab);
		labWork.setResults(results);
		labWork.setRescheduleRequired(rescheduleRequired);
		labWork.setOrder(order);
		labWork.setOffenderAppointmentAssociation(
			offenderAppointmentAssociation);
		labWork.setLabWorkCategory(labWorkCategory);
		return this.labWorkDao.makePersistent(labWork);
	}
}