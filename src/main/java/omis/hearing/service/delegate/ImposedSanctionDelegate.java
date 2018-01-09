
package omis.hearing.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.hearing.dao.ImposedSanctionDao;
import omis.hearing.domain.ImposedSanction;
import omis.hearing.domain.Infraction;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * ImposedSanctionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ImposedSanctionDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Imposed Sanction already exists with given Infraction and Description.";
	
	private final ImposedSanctionDao imposedSanctionDao;
	
	private final InstanceFactory<ImposedSanction> 
		imposedSanctionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ImposedSanctionDelegate
	 * @param imposedSanctionDao
	 * @param imposedSanctionInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ImposedSanctionDelegate(
			final ImposedSanctionDao imposedSanctionDao,
			final InstanceFactory<ImposedSanction> 
				imposedSanctionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.imposedSanctionDao = imposedSanctionDao;
		this.imposedSanctionInstanceFactory = imposedSanctionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an ImposedSanction with the specified properties
	 * @param infraction - Infraction
	 * @param offender - Offender
	 * @param description - String
	 * @return Newly created ImposedSanction
	 * @throws DuplicateEntityFoundException - When an ImposedSanction already
	 * exists with given Infraction and Description
	 */
	public ImposedSanction create(final Infraction infraction,
			final Offender offender, final String description)
					throws DuplicateEntityFoundException{
		if(this.imposedSanctionDao.find(infraction, description) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ImposedSanction imposedSanction = 
				this.imposedSanctionInstanceFactory.createInstance();
		
		imposedSanction.setDescription(description);
		imposedSanction.setInfraction(infraction);
		imposedSanction.setOffender(offender);
		imposedSanction.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		imposedSanction.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.imposedSanctionDao.makePersistent(imposedSanction);
	}
	
	/**
	 * Updates an ImposedSanction with the specified properties
	 * @param imposedSanction - ImposedSanction to update
	 * @param offender - Offender
	 * @param description - String
	 * @return Updated ImposedSanction
	 * @throws DuplicateEntityFoundException - When an ImposedSanction already
	 * exists with given Infraction and Description
	 */
	public ImposedSanction update(final ImposedSanction imposedSanction,
			final Offender offender, final String description)
					throws DuplicateEntityFoundException{
		if(this.imposedSanctionDao.findExcluding(imposedSanction.getInfraction(),
				description, imposedSanction) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		imposedSanction.setDescription(description);
		imposedSanction.setOffender(offender);
		imposedSanction.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.imposedSanctionDao.makePersistent(imposedSanction);
	}
	
	/**
	 * Removes an ImposedSanction
	 * @param imposedSanction - ImposedSanction to remove
	 */
	public void remove(final ImposedSanction imposedSanction){
		this.imposedSanctionDao.makeTransient(imposedSanction);
	}
	
	/**
	 * Returns an ImposedSanction found by specified Infraction
	 * @param infraction - Infraction
	 * @return ImposedSanction found by specified Infraction
	 */
	public ImposedSanction findByInfraction(final Infraction infraction){
		return this.imposedSanctionDao.findByInfraction(infraction);
	}
	
}
