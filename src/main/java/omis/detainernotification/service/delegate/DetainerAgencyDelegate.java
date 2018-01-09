/**
 * DetainerAgencyDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Mar 22, 2017)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.service.delegate;

import java.util.List;

import omis.address.domain.Address;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.detainernotification.dao.DetainerAgencyDao;
import omis.detainernotification.domain.DetainerAgency;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;



public class DetainerAgencyDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
	= "Detainer agency exists with given name.";
	
	private final DetainerAgencyDao detainerAgencyDao;
	
	private final InstanceFactory<DetainerAgency> detainerAgencyInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param detainerAgencyDao - detainer agency dao
	 * @param detainerAgencyInstanceFactory - detainer agency instance factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public DetainerAgencyDelegate(final DetainerAgencyDao detainerAgencyDao,
			final InstanceFactory<DetainerAgency> detainerAgencyInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever){
		this.detainerAgencyDao = detainerAgencyDao;
		this.detainerAgencyInstanceFactory = detainerAgencyInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates detainer agency
	 * @param agencyName - agency name
	 * @param valid - valid
	 * @param telephoneNumber - telephone number
	 * @param address - address
	 * @return detainerAgency - detainer agency
	 * @throws DuplicateEntityFoundException - when detainer agency exists
	 * with given name
	 */
	public DetainerAgency create(final String agencyName, final Boolean valid,
			final String telephoneNumber, final Address address)
					throws DuplicateEntityFoundException{
		if(this.detainerAgencyDao.find(agencyName) != null){
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		DetainerAgency detainerAgency = 
				this.detainerAgencyInstanceFactory.createInstance();
		
		detainerAgency.setAddress(address);
		detainerAgency.setAgencyName(agencyName);
		detainerAgency.setTelephoneNumber(telephoneNumber);
		detainerAgency.setValid(valid);
		detainerAgency.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		detainerAgency.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.detainerAgencyDao.makePersistent(detainerAgency);
	}
	
	/**
	 * Updates an existing detainer agency
	 * @param detainerAgency - detainer agency
	 * @param agencyName - agency name
	 * @param valid - valid
	 * @param telephoneNumber - telephone number
	 * @param address - address
	 * @return detainerAgency - detainer agency
	 * @throws DuplicateEntityFoundException - when detainer agency exists
	 * with given name
	 */
	public DetainerAgency update(DetainerAgency detainerAgency, 
			final String agencyName, final Boolean valid,
			final String telephoneNumber, final Address address)
					throws DuplicateEntityFoundException{
		if(this.detainerAgencyDao.findExcluding(
				agencyName, detainerAgency) != null){
			throw new DuplicateEntityFoundException(
					DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		detainerAgency.setAddress(address);
		detainerAgency.setAgencyName(agencyName);
		detainerAgency.setTelephoneNumber(telephoneNumber);
		detainerAgency.setValid(valid);
		detainerAgency.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.detainerAgencyDao.makePersistent(detainerAgency);
	}
	
	/**
	 * Removes specified detainer agency
	 * @param detainerAgency - detainer agency
	 */
	public void remove(final DetainerAgency detainerAgency){
		this.detainerAgencyDao.makeTransient(detainerAgency);
	}
	
	/**
	 * Returns a list of all valid DetainerAgencies
	 * @return List of all DetainerAgencies
	 */
	public List<DetainerAgency> findAll(){
		return this.detainerAgencyDao.findAll();
	}

}
