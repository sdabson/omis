package omis.health.service.impl;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.UpdateSignature;
import omis.health.dao.LabWorkDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.LabWork;
import omis.health.service.LabWorkAssessmentService;


/**
 * Lab Work Assessment Service Implementation.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (June 2, 2014)
 * @since OMIS 3.0
 */
public class LabWorkAssessmentServiceImpl  implements LabWorkAssessmentService {
	
	private final AuditComponentRetriever auditComponentRetriever;
	private final LabWorkDao labWorkDao;
	
	/**
	 * Instantiates an implementation of service to assess lab work.
	 * 
	 * @param labWorkDao data access object for lab work
	 * @param auditComponentRetriever retriever for audit components
	 */
	public LabWorkAssessmentServiceImpl (
			final AuditComponentRetriever auditComponentRetriever,
			final LabWorkDao labWorkDao){
		this.auditComponentRetriever = auditComponentRetriever;
		this.labWorkDao = labWorkDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public void assess(final LabWork labWork, final Date timeKept, 
			final String notes) {
		labWork.getOffenderAppointmentAssociation().getAppointment()
			.setTimeKept(timeKept);
		labWork.getOffenderAppointmentAssociation().getAppointment()
			.setStatus(HealthAppointmentStatus.KEPT);
		labWork.setSampleNotes(notes);
		labWork.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.labWorkDao.makePersistent(labWork);
	}	
}
