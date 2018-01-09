package omis.immigration.service.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.immigration.dao.AlienResidenceDao;
import omis.immigration.domain.AlienResidence;
import omis.immigration.service.AlienResidenceService;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Implementation of service for alien residences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 3, 2014)
 * @since OMIS 3.0
 */
public class AlienResidenceServiceImpl
		implements AlienResidenceService {

	private final AlienResidenceDao alienResidenceDao;
	
	private final InstanceFactory<AlienResidence> alienResidenceInstanceFactory;
	
	/**
	 * Instantiates an implementation of service for alien residences.
	 * 
	 * @param alienResidenceDao data access object for alien residences
	 * @param alienResidenceInstanceFactory instance factory for alien
	 * residences
	 */
	public AlienResidenceServiceImpl(
			final AlienResidenceDao alienResidenceDao,
			final InstanceFactory<AlienResidence>
				alienResidenceInstanceFactory) {
		this.alienResidenceDao = alienResidenceDao;
		this.alienResidenceInstanceFactory = alienResidenceInstanceFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setLegalResidenceStatus(final Offender offender,
			final Boolean legal, final UserAccount userAccount,
			final Date date) {
		AlienResidence residence = this.alienResidenceDao
				.findByOffender(offender);
		if (residence == null) {
			residence = this.alienResidenceInstanceFactory
					.createInstance();
			residence.setCreationSignature(
					new CreationSignature(userAccount, date));
			residence.setOffender(offender);
		}
		residence.setUpdateSignature(new UpdateSignature(userAccount, date));
		residence.setLegal(legal);
		this.alienResidenceDao.makePersistent(residence);
	}

	/** {@inheritDoc} */
	@Override
	public AlienResidence getAlienResidence(final Offender offender) {
		AlienResidence residence = this.alienResidenceDao
				.findByOffender(offender);
		return residence;
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final AlienResidence alienResidence) {
		this.alienResidenceDao.makeTransient(alienResidence);
	}
}