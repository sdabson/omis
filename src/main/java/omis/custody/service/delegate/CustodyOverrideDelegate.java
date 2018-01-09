package omis.custody.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.CreationSignature;
import omis.custody.dao.CustodyOverrideDao;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Custody Override Delegate
 * 
 * @author Josh Divine 
 * @version 0.1.0 (December 2, 2016)
 * @since OMIS 3.0
 */
public class CustodyOverrideDelegate {

	private final InstanceFactory<CustodyOverride> 
	custodyOverrideInstanceFactory;

	private final CustodyOverrideDao custodyOverrideDao;

	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param financialAssetCategoryInstanceFactory
	 * @param financialAssetCategoryDao
	 */
	public CustodyOverrideDelegate(
			final InstanceFactory<CustodyOverride> 
			custodyOverrideInstanceFactory,
			final CustodyOverrideDao custodyOverrideDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.custodyOverrideInstanceFactory 
			= custodyOverrideInstanceFactory;
		this.custodyOverrideDao = custodyOverrideDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new custody override
	 * @param custodyReview custody review
	 * @param custodyLevel custody level
	 * @return custody override
	 */
	public CustodyOverride create(final CustodyReview custodyReview, 
			final CustodyLevel custodyLevel) 
					throws DuplicateEntityFoundException {
		if (this.custodyOverrideDao.find(custodyReview, custodyLevel) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		CustodyOverride override = custodyOverrideInstanceFactory
				.createInstance();
		override.setCustodyLevel(custodyLevel);
		override.setCustodyReview(custodyReview);
		override.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));

		return this.custodyOverrideDao.makePersistent(override);
	}
	
	/**
	 * Authorizes the specified custody override
	 * @param custodyOverride custody override
	 * @param authorizationSignature authorization signature
	 * @return custody override
	 */
	public CustodyOverride authorize(final CustodyOverride custodyOverride) {
		custodyOverride.setAuthorizationSignature(
				new AuthorizationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.custodyOverrideDao.makePersistent(custodyOverride);
	}
	
	/**
	 * Deletes an existing custody override
	 * @param custodyOverride custody override
	 */
	public void remove(final CustodyOverride custodyOverride) {
		this.custodyOverrideDao.makeTransient(custodyOverride);
	}
	
	/**
	 * Finds all custody overrides
	 * @return list of custody overrides
	 */
	public List<CustodyOverride> findAll() {
		return this.custodyOverrideDao.findAll();
	}
	
	/**
	 * Find custody override by specified custody review
	 * @param custodyReview custody review
	 * @return custody override
	 */
	public CustodyOverride findByReview(final CustodyReview custodyReview) {
		return this.custodyOverrideDao.findByReview(custodyReview);
	}
}
