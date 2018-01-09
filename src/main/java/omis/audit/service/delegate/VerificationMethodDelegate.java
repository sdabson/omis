package omis.audit.service.delegate;

import java.util.List;

import omis.audit.dao.VerificationMethodDao;
import omis.audit.domain.VerificationMethod;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * VerificationMethodDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 8, 2016)
 *@since OMIS 3.0
 *
 */
public class VerificationMethodDelegate {
	private final InstanceFactory<VerificationMethod> 
	verificationMethodInstanceFactory;

	private final VerificationMethodDao verificationMethodDao;
	
	/**
	 * Constructor
	 * @param verificationMethodInstanceFactory
	 * @param verificationMethodDao
	 */
	public VerificationMethodDelegate(
			InstanceFactory<VerificationMethod> 
				verificationMethodInstanceFactory,
			VerificationMethodDao verificationMethodDao) {
		this.verificationMethodInstanceFactory = 
				verificationMethodInstanceFactory;
		this.verificationMethodDao = verificationMethodDao;
	}
	
	
	/**
	 * Creates a verification method
	 * @param name - name
	 * @param sortOrder - sort order
	 * @param valid - valid
	 * @return VerificationMethod
	 * @throws DuplicateEntityFoundException - when the verification method
	 *  already exists
	 */
	public VerificationMethod create(final String name, 
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
						
		VerificationMethod verificationMethod 
			= this.verificationMethodInstanceFactory
				.createInstance(); 
		verificationMethod.setName(name);
		verificationMethod.setSortOrder(sortOrder);
		verificationMethod.setValid(valid);
		
		if(this.verificationMethodDao.findAll().contains(verificationMethod)){
			throw new DuplicateEntityFoundException(
					"Duplicate verification method found.");
		}
		
		return this.verificationMethodDao.makePersistent(verificationMethod);
		
	}
	
	/**
	 * Returns all verification methods
	 * @return list of verification methods
	 */
	public List<VerificationMethod> findAll() {
		return this.verificationMethodDao.findAll();
	}
}
