/**
 * InterstateAgreementDetainerDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 11, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.dao.InterstateAgreementDetainerDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

public class InterstateAgreementDetainerDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
	= "Interstate Agreement Detainer exists for this detainer.";
	
	private final InterstateAgreementDetainerDao interstateAgreementDetainerDao;
	
	private final InstanceFactory<InterstateAgreementDetainer> 
		interstateAgreementDetainerInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	
	/**
	 * Constructor
	 * @param interstateAgreementDetainerDao - interstate agreement detainer dao
	 * @param interstateAgreementDetainerInstanceFactory -  insterstate 
	 * agreement detainer instance factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public InterstateAgreementDetainerDelegate(
			final InterstateAgreementDetainerDao interstateAgreementDetainerDao, 
			final InstanceFactory<InterstateAgreementDetainer> 
				interstateAgreementDetainerInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever){
		this.interstateAgreementDetainerDao = interstateAgreementDetainerDao;
		this.interstateAgreementDetainerInstanceFactory = 
				interstateAgreementDetainerInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an interstate agreement detainer
	 * @param detainer - detainer
	 * @param prosecutorReceivedDate - prosecutor received date
	 * @param initiatedBy - initiated by
	 * @throws DuplicateEntityFoundException - when Interstate Agreement Detainer 
	 * exists for given detainer 
	 */
	public InterstateAgreementDetainer create(final Detainer detainer, 
			final Date prosecutorReceivedDate,
			final InterstateAgreementInitiatedByCategory initiatedBy)
					throws DuplicateEntityFoundException{
		if(this.interstateAgreementDetainerDao.find(detainer) != null){
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
			}
		
		InterstateAgreementDetainer interstateAgreementDetainer = 
			this.interstateAgreementDetainerInstanceFactory.createInstance();
		
		
		interstateAgreementDetainer.setDetainer(detainer);
		interstateAgreementDetainer.setInitiatedBy(initiatedBy);
		
		interstateAgreementDetainer.setProsecutorReceivedDate(
				prosecutorReceivedDate);
		interstateAgreementDetainer.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		interstateAgreementDetainer.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));

		
		return this.interstateAgreementDetainerDao.makePersistent(
				interstateAgreementDetainer);
	}
	
	/**
	 * Updates an existing interstate agreement detainer
	 * @param interstateAgreementDetainer - interstate agreement detainer
	 * @param prosecutorReceivedDate - prosecutor received date
	 * @param initiatedBy - initiated by
	 * @throws DuplicateEntityFoundException - when Interstate Agreement Detainer 
	 * exists for given detainer 
	 */
	public InterstateAgreementDetainer update(final InterstateAgreementDetainer 
				interstateAgreementDetainer,
			final Date prosecutorReceivedDate,
			final InterstateAgreementInitiatedByCategory initiatedBy)
					throws DuplicateEntityFoundException{
		if(this.interstateAgreementDetainerDao.findExcluding(
				interstateAgreementDetainer.getDetainer(), 
				interstateAgreementDetainer) != null){
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
			}
		
		
		interstateAgreementDetainer.setInitiatedBy(initiatedBy);
		interstateAgreementDetainer.setProsecutorReceivedDate(
				prosecutorReceivedDate);
		interstateAgreementDetainer.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));

		
		return this.interstateAgreementDetainerDao.makePersistent(
				interstateAgreementDetainer);
	}

	
	/**
	 * Removes specified interstate Agreement detainer
	 * @param interstateAgreementDetainer - interstate Agreement detainer
	 */
	public void remove(final InterstateAgreementDetainer 
			interstateAgreementDetainer){
		this.interstateAgreementDetainerDao.makeTransient(
				interstateAgreementDetainer);
	}
	
	/**
	 * Finds an interstate agreement detainer by specified detainer
	 * @param detainer - detainer
	 * @return interstate agreement detainer
	 */
	public InterstateAgreementDetainer find(final Detainer detainer){
		return this.interstateAgreementDetainerDao.find(detainer);
	}
	
	
	
}
