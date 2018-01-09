package omis.immigration.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.immigration.dao.AlienResidenceDao;
import omis.immigration.domain.AlienResidence;
import omis.immigration.domain.ImmigrationStatus;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * AlienResidenceDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 9, 2016)
 *@since OMIS 3.0
 *
 */
public class AlienResidenceDelegate {
	
	private final AlienResidenceDao alienResidenceDao;
	
	private final InstanceFactory<AlienResidence> 
		alienResidenceInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AlienResidenceDelegate
	 * @param alienResidenceDao
	 * @param alienResidenceInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AlienResidenceDelegate(
			final AlienResidenceDao alienResidenceDao,
			final InstanceFactory<AlienResidence> 
				alienResidenceInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.alienResidenceDao = alienResidenceDao;
		this.alienResidenceInstanceFactory = alienResidenceInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an AlienResidence
	 * @param offender
	 * @param dateRange
	 * @param immigrationStatus
	 * @param legal - Boolean
	 * @param insNumber - String
	 * @return Newly created AlienResidence
	 */
	public AlienResidence create(final Offender offender, 
			final DateRange dateRange, final ImmigrationStatus immigrationStatus,
			final Boolean legal, final String insNumber){
		
		AlienResidence alienResidence = 
				this.alienResidenceInstanceFactory.createInstance();
		
		alienResidence.setDateRange(dateRange);
		alienResidence.setImmigrationStatus(immigrationStatus);
		alienResidence.setInsNumber(insNumber);
		alienResidence.setLegal(legal);
		alienResidence.setOffender(offender);
		alienResidence.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		alienResidence.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.alienResidenceDao.makePersistent(alienResidence);
	}
	
	/**
	 * Updates an AlienResidence
	 * @param alienResidence - AlienResidence to update
	 * @param dateRange
	 * @param immigrationStatus
	 * @param legal - Boolean
	 * @param insNumber - String 
	 * @return Updated AlienResidence
	 */
	public AlienResidence update(final AlienResidence alienResidence,
			final DateRange dateRange, final ImmigrationStatus immigrationStatus,
			final Boolean legal, final String insNumber){
		alienResidence.setDateRange(dateRange);
		alienResidence.setImmigrationStatus(immigrationStatus);
		alienResidence.setInsNumber(insNumber);
		alienResidence.setLegal(legal);
		alienResidence.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.alienResidenceDao.makePersistent(alienResidence);
	}
	
	/**
	 * Removes an AlienResidence
	 * @param alienResidence - AlienResidence to remove
	 */
	public void remove(final AlienResidence alienResidence){
		this.alienResidenceDao.makeTransient(alienResidence);
	}
	
	/**
	 * Finds AlienResidence by Offender
	 * @param offender
	 * @return AlienResidence by specified Offender
	 */
	public AlienResidence findByOffender(final Offender offender){
		return this.alienResidenceDao.findByOffender(offender);
	}
	
	
	
}
