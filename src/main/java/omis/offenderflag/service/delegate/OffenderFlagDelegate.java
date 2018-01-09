package omis.offenderflag.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.offenderflag.dao.OffenderFlagDao;
import omis.offenderflag.domain.OffenderFlag;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * OffenderFlagDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class OffenderFlagDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Flag already exists for this offender and category.";
	
	private final OffenderFlagDao offenderFlagDao;
	
	private final InstanceFactory<OffenderFlag> 
		offenderFlagInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for OffenderFlagDelegate
	 * @param offenderFlagDao
	 * @param offenderFlagInstanceFactory
	 * @param auditComponentRetriever
	 */
	public OffenderFlagDelegate(
			final OffenderFlagDao offenderFlagDao,
			final InstanceFactory<OffenderFlag> 
				offenderFlagInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderFlagDao = offenderFlagDao;
		this.offenderFlagInstanceFactory = offenderFlagInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Created an OffenderFlag with specified properties
	 * @param offender - Offender
	 * @param category - Offender Flag Category
	 * @param value - Boolean
	 * @return Created OffenderFlag
	 * @throws DuplicateEntityFoundException
	 */
	public OffenderFlag create(final Offender offender,
			final OffenderFlagCategory category, final Boolean value)
			throws DuplicateEntityFoundException{
		if(this.offenderFlagDao.find(offender, category) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		OffenderFlag offenderFlag = 
				this.offenderFlagInstanceFactory.createInstance();
		
		offenderFlag.setOffender(offender);
		offenderFlag.setCategory(category);
		offenderFlag.setValue(value);
		offenderFlag.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		offenderFlag.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenderFlagDao.makePersistent(offenderFlag);
	}
	
	/**
	 * Updates an OffenderFlag with specified properties
	 * @param offenderFlag - Offender Flag
	 * @param category - Offender Flag Category
	 * @param value - Boolean
	 * @return Updated OffenderFlag
	 * @throws DuplicateEntityFoundException
	 */
	public OffenderFlag update(final OffenderFlag offenderFlag,
			final OffenderFlagCategory category, final Boolean value)
			throws DuplicateEntityFoundException{
		//if(this.offenderFlagDao.findExcluding(offender, category, offenderFlag) != null){
			//throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		//}
		
		offenderFlag.setCategory(category);
		offenderFlag.setValue(value);
		offenderFlag.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenderFlagDao.makePersistent(offenderFlag);
	}
	
	/**
	 * Removes an offenderFlag
	 * @param offenderFlag - offender flag to remove
	 */
	public void remove(final OffenderFlag offenderFlag){
		this.offenderFlagDao.makeTransient(offenderFlag);
	}
	
	/**
	 * Returns offender flags by offender.
	 * 
	 * @param offender offender
	 * @return offender flags be offender
	 */
	public List<OffenderFlag> findByOffender(
			final Offender offender) {
		return this.offenderFlagDao.findByOffender(offender);
	}
	
	/**
	 * Returns the number of missing flags per category for the offender.
	 * 
	 * @param offender offender
	 * @param categories categories
	 * @return number of missing flags per category
	 */
	public long countMissingFlags(final Offender offender,
			final OffenderFlagCategory... categories) {
		return this.offenderFlagDao.countMissingFlags(offender, categories);
	}
	
	
	
}
