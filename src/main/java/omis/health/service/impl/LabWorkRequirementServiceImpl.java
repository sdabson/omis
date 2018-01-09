package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.LabDao;
import omis.health.dao.LabWorkCategoryDao;
import omis.health.dao.LabWorkRequirementRequestDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.domain.HealthRequest;
import omis.health.domain.Lab;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.service.LabWorkRequirementService;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;

/**
 * Implementation of service for lab work requirements.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 4, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementServiceImpl
		implements LabWorkRequirementService {

	private final LabWorkRequirementRequestDao labWorkRequirementRequestDao;
	
	private final InstanceFactory<LabWorkRequirementRequest>
	labWorkRequirementRequestInstanceFactory;
	
	private final LabWorkCategoryDao labWorkCategoryDao;
	
	private final LabDao labDao;
	
	private final ProviderAssignmentDao providerAssignmentDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Implementation of service for lab work requirements.
	 * 
	 * @param labWorkRequirementRequestDao data access object for lab work
	 * requirement requests
	 * @param labWorkRequirementRequestInstanceFactory instance factory for lab
	 * work requirement requests
	 * @param labWorkCategoryDao data access object for lab work categories
	 * @param labDao data access object for labs
	 * @param auditComponentRetriever retriever for audit components
	 */
	public LabWorkRequirementServiceImpl(
			final LabWorkRequirementRequestDao labWorkRequirementRequestDao,
			final InstanceFactory<LabWorkRequirementRequest>
				labWorkRequirementRequestInstanceFactory,
			final LabWorkCategoryDao labWorkCategoryDao,
			final LabDao labDao,
			final ProviderAssignmentDao providerAssignmentDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.labWorkRequirementRequestDao = labWorkRequirementRequestDao;
		this.labWorkRequirementRequestInstanceFactory
			= labWorkRequirementRequestInstanceFactory;
		this.labWorkCategoryDao = labWorkCategoryDao;
		this.labDao = labDao;
		this.providerAssignmentDao = providerAssignmentDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWorkRequirementRequest request(
			final HealthRequest healthRequest,
			final LabWorkOrder order, final LabWorkCategory category,
			final Date sampleDate, final Lab sampleLab, final Lab resultsLab,
			final LabWorkSampleRestrictions sampleRestrictions,
			final String notes) throws DuplicateEntityFoundException {
		LabWorkRequirementRequest request
			= this.labWorkRequirementRequestInstanceFactory.createInstance();
		request.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		request.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		request.setHealthRequest(healthRequest);
		request.setOrder(order);
		request.setCategory(category);
		request.setSampleDate(sampleDate);
		request.setSampleLab(sampleLab);
		request.setResultsLab(resultsLab);
		request.setSampleRestrictions(sampleRestrictions);
		request.setSchedulingNotes(notes);
		return this.labWorkRequirementRequestDao.makePersistent(request);
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkRequirementRequest update(
			final LabWorkRequirementRequest request, final LabWorkOrder order,
			final LabWorkCategory category, final Date sampleDate,
			final Lab sampleLab, final Lab resultsLab,
			final LabWorkSampleRestrictions sampleRestrictions,
			final String notes)
					throws DuplicateEntityFoundException {
		request.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		request.setOrder(order);
		request.setCategory(category);
		request.setSampleDate(sampleDate);
		request.setSampleLab(sampleLab);
		request.setResultsLab(resultsLab);
		request.setSampleRestrictions(sampleRestrictions);
		request.setSchedulingNotes(notes);
		return this.labWorkRequirementRequestDao.makePersistent(request);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final LabWorkRequirementRequest request) {
		this.labWorkRequirementRequestDao.makeTransient(request);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkRequirementRequest> findRequestsByHealthRequest(
			final HealthRequest healthRequest) {
		return this.labWorkRequirementRequestDao
				.findByHealthRequest(healthRequest);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkCategory> findLabWorkCategories() {
		return this.labWorkCategoryDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabsForLocation(final Location location) {
		return this.labDao.findLabsAtLocation(location);
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findProviders(
			final Facility facility, final Date date) {
		return this.providerAssignmentDao.findByFacility(facility, date);
	}
}