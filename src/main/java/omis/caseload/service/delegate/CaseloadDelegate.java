package omis.caseload.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.dao.CaseloadDao;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Caseload service implementation delegate.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.1 (May 17, 2017)
 * @since OMIS 3.0
 */
public class CaseloadDelegate {

	/* Data access objects. */
	private final CaseloadDao caseloadDao;
	
	/* Instance factory. */
	private final InstanceFactory<Caseload> caseloadInstanceFactory;
	
	/* Component retrievers. */
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/** Instantiates a caseload service implementation delegate with the 
	 * specified data access objects and instance factories.
	 * @param caseloadDao -caseload dao.
	 * @param caseloadInstanceFactory - caseload instance factory.
	 * @param auditComponentRetriever - audit component retriever.
	 */
	public CaseloadDelegate(final CaseloadDao caseloadDao, 
					final InstanceFactory<Caseload> caseloadInstanceFactory,
					final AuditComponentRetriever auditComponentRetriever) {
		this.caseloadDao = caseloadDao;
		this.caseloadInstanceFactory = caseloadInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Management methods. */
	
	/**
	 * Creates a new caseload.
	 * @param name - name
	 * @param category -  casework category
	 * @param caseload caseload
	 * @return new caseload
	 * @throws DuplicateEntityFoundException when caseload with name exists.
	 */
	public Caseload createCaseload(final String name, 
					final CaseworkCategory category, final Caseload caseload) 
					throws DuplicateEntityFoundException {
		Caseload newCaseload = this.caseloadInstanceFactory.createInstance();
		if (this.caseloadDao.find(name, category) != null) {
			throw new DuplicateEntityFoundException("Duplicate caseload found");
		}
		newCaseload.setCaseload(caseload);
		newCaseload.setCategory(category);
		newCaseload.setName(name);
		newCaseload.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		newCaseload.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.caseloadDao.makePersistent(newCaseload);
	}
	
	/**
	 * Returns an updated caseload.
	 *
	 * @param caseload - caseload.
	 * @param name name
	 * @param category category
	 * @param owner caseload to update.
	 * @return edited caseload
	 * @throws DuplicateEntityFoundException - when caseload with given name 
	 * exists.
	 */
	public Caseload updateCaseload(final Caseload caseload, final String name, 
					final CaseworkCategory category, final Caseload owner) 
					throws DuplicateEntityFoundException {
		if (this.caseloadDao.findExcluding(name, category, caseload) != null) {
			throw new DuplicateEntityFoundException("Duplicate caseload found");
		}
		caseload.setCategory(category);
		caseload.setName(name);
		caseload.setCaseload(caseload);
		caseload.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));

		return this.caseloadDao.makePersistent(caseload);
	}

	/**
	 * Removes a caseload.
	 *
	 * @param caseload caseload
	 */
	public void removeCaseload(final Caseload caseload) {
		this.caseloadDao.makeTransient(caseload);
	}
}