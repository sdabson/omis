package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.HealthRequestDao;
import omis.health.dao.ProviderLevelDao;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.HealthRequestStatus;
import omis.health.domain.ProviderLevel;
import omis.health.exception.HealthRequestException;
import omis.health.service.HealthRequestService;
import omis.health.service.delegate.HealthRequestDelegate;
import omis.offender.domain.Offender;

/**
 * Implementation of service for health requests.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 15, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestServiceImpl
		implements HealthRequestService {

	private final HealthRequestDelegate healthRequestDelegate;

	private final HealthRequestDao healthRequestDao;

	private final ProviderLevelDao providerLevelDao;

	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Instantiates an implementation of service for health requests.
	 *
	 * @param healthRequestDelegate delegate for health requests
	 * @param healthRequestDao data access object for health requests
	 * @param providerLevelDao data access object for provider levels
	 * @param auditComponentRetriever retriever of audit components
	 */
	public HealthRequestServiceImpl(
			final HealthRequestDelegate healthRequestDelegate,
			final HealthRequestDao healthRequestDao,
			final ProviderLevelDao providerLevelDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.healthRequestDelegate = healthRequestDelegate;
		this.healthRequestDao = healthRequestDao;
		this.providerLevelDao = providerLevelDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest request(final Offender offender,
			final Facility facility, final Date date,
			final Boolean labsRequired, final HealthRequestCategory category,
			final boolean asap, final ProviderLevel providerLevel,
			final String notes)
					throws DuplicateEntityFoundException {
		return this.healthRequestDelegate
			.createOpen(offender, facility, labsRequired, date, category, asap,
					providerLevel, notes);
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest requestLabWork(final Offender offender,
			final Facility facility, final Date date,
			final boolean asap, final String notes)
					throws DuplicateEntityFoundException {
		return this.healthRequestDelegate.createOpenLabWorkRequest(offender,
				facility, date, asap, null, notes);
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest update(final HealthRequest healthRequest,
			final HealthRequestCategory category, final Boolean labsRequired,
			final boolean asap, final ProviderLevel providerLevel,
			final String notes)
				throws DuplicateEntityFoundException, HealthRequestException {
		if (!HealthRequestStatus.OPEN.equals(healthRequest.getStatus())) {
			throw new HealthRequestException("Request not open");
		}
		if (this.healthRequestDao.findExcluding(
				healthRequest.getOffender(),
				healthRequest.getFacility(),
				healthRequest.getDate(), category,
				healthRequest.getStatus(), healthRequest) != null) {
			throw new DuplicateEntityFoundException("Health request exists");
		}
		healthRequest.setAsap(asap);
		healthRequest.setCategory(category);
		healthRequest.setProviderLevel(providerLevel);
		healthRequest.setLabsRequired(labsRequired);
		healthRequest.setNotes(notes);
		healthRequest.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.healthRequestDao.makePersistent(healthRequest);
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest cancel(final HealthRequest healthRequest) {
		healthRequest.setStatus(HealthRequestStatus.CANCELLED);
		return healthRequest;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderLevel> findProviderLevels() {
		return this.providerLevelDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final HealthRequest healthRequest) {
		this.healthRequestDao.makeTransient(healthRequest);
	}
}