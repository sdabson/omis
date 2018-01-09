package omis.alert.service.impl;

import java.util.Date;
import java.util.List;

import omis.alert.dao.OffenderAlertDao;
import omis.alert.domain.OffenderAlert;
import omis.alert.domain.component.AlertResolution;
import omis.alert.service.OffenderAlertService;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Implementation of service for alerts.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.1 (September 13, 2012)
 * @since OMIS 3.0
 */

public class OffenderAlertServiceImpl
		implements OffenderAlertService {

	private final OffenderAlertDao offenderAlertDao;
	
	private final InstanceFactory<OffenderAlert> offenderAlertInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of service for offender alerts with
	 * the specified data access object.
	 * 
	 * @param offenderAlertDao data access object for offender alerts
	 * @param offenderAlertInstanceFactory instance factory for offender alerts
	 * @param auditComponentRetriever retriever for audit components
	 */
	public OffenderAlertServiceImpl(
			final OffenderAlertDao offenderAlertDao,
			final InstanceFactory<OffenderAlert> offenderAlertInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderAlertDao = offenderAlertDao;
		this.offenderAlertInstanceFactory = offenderAlertInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderAlert> findByOffender(final Offender offender) {
		return this.offenderAlertDao.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final OffenderAlert alert) {
		this.offenderAlertDao.makeTransient(alert);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderAlert save(
			final Offender offender, final Date expireDate,
			final String description, final AlertResolution resolution)
				throws DuplicateEntityFoundException {
		if (this.offenderAlertDao.find(offender, expireDate, description)
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		OffenderAlert offenderAlert
			= this.offenderAlertInstanceFactory.createInstance();
		offenderAlert.setOffender(offender);
		offenderAlert.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateAlert(offenderAlert, expireDate, description, resolution);
		return this.offenderAlertDao.makePersistent(offenderAlert);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderAlert update(final OffenderAlert offenderAlert,
			final Date expireDate, final String description,
			final AlertResolution resolution)
				throws DuplicateEntityFoundException {
		if (this.offenderAlertDao.findExcluding(offenderAlert.getOffender(),
					expireDate, description, offenderAlert)
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		this.populateAlert(offenderAlert, expireDate, description, resolution);
		return this.offenderAlertDao.makePersistent(offenderAlert);
	}
	 
	// Populate alert
	private void populateAlert(final OffenderAlert offenderAlert,
			final Date expireDate, final String description,
			final AlertResolution resolution){
		offenderAlert.setExpireDate(expireDate);
		offenderAlert.setDescription(description);
		offenderAlert.setResolution(new AlertResolution(
				resolution.getDescription(), resolution.getDate(),
				resolution.getResolvedBy()));
		offenderAlert.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}