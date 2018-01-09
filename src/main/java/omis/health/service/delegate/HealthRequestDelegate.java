package omis.health.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.HealthRequestDao;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.HealthRequestStatus;
import omis.health.domain.ProviderLevel;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate for health requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 13, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestDelegate {
	
	private final InstanceFactory<HealthRequest> healthRequestInstanceFactory;
	
	private final HealthRequestDao healthRequestDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a delegate for health requests.
	 * 
	 * @param healthRequestInstanceFactory instance factory for health requests
	 * @param healthRequestDao data access object for health requests
	 * @param auditComponentRetriever retriever of audit components
	 */
	public HealthRequestDelegate(
			final InstanceFactory<HealthRequest> healthRequestInstanceFactory,
			final HealthRequestDao healthRequestDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.healthRequestInstanceFactory = healthRequestInstanceFactory;
		this.healthRequestDao = healthRequestDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/**
	 * Creates and persists a health request.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param labsRequired whether labs are required
	 * @param date date
	 * @param category category
	 * @param asap whether request is to be scheduled ASAP
	 * @param providerLevel provider level
	 * @param notes notes
	 * @return health request
	 * @throws DuplicateEntityFoundException if request exists
	 */
	public HealthRequest createOpen(final Offender offender,
			final Facility facility, final Boolean labsRequired,
			final Date date, final HealthRequestCategory category,
			final boolean asap, final ProviderLevel providerLevel,
			final String notes)
					throws DuplicateEntityFoundException {
		return this.createImpl(
				offender, facility, labsRequired, date, category,
				HealthRequestStatus.OPEN, asap, providerLevel, notes);
	}

	/**
	 * Creates and persists a request for lab work.
	 * 
	 * @param offender offender
	 * @param facility facility
	 * @param date date
	 * @param asap whether request is to be scheduled ASAP
	 * @param providerLevel provider level
	 * @param notes notes
	 * @return request for lab work
	 * @throws DuplicateEntityFoundException if request exists
	 */
	public HealthRequest createOpenLabWorkRequest(final Offender offender,
			final Facility facility, final Date date,
			final boolean asap, final ProviderLevel providerLevel,
			final String notes)
					throws DuplicateEntityFoundException {
		return this.createImpl(offender, facility, null, date,
				HealthRequestCategory.LAB, HealthRequestStatus.OPEN, asap,
				providerLevel, notes);
	}
	
	// Implementation
	private HealthRequest createImpl(final Offender offender,
			final Facility facility, final Boolean labsRequired,
			final Date date, final HealthRequestCategory category,
			final HealthRequestStatus status, final boolean asap,
			final ProviderLevel providerLevel, final String notes)
						throws DuplicateEntityFoundException {
		if (this.healthRequestDao.find(offender, facility, date, category,
				status) != null) {
			throw new DuplicateEntityFoundException("Health request exists");
		}
		HealthRequest request
			= this.healthRequestInstanceFactory.createInstance();
		request.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		request.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		request.setCategory(category);
		request.setDate(date);
		request.setOffender(offender);
		request.setLabsRequired(labsRequired);
		request.setFacility(facility);
		request.setAsap(asap);
		request.setProviderLevel(providerLevel);
		request.setNotes(notes);
		request.setStatus(status);
		return this.healthRequestDao.makePersistent(request);
	}
}