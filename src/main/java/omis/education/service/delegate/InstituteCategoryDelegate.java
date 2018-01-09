package omis.education.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.education.dao.InstituteCategoryDao;
import omis.education.domain.InstituteCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * InstituteCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 8, 2016)
 *@since OMIS 3.0
 *
 */
public class InstituteCategoryDelegate {
	
	private final InstanceFactory<InstituteCategory> 
		instituteCategoryInstanceFactory;

	private final InstituteCategoryDao instituteCategoryDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor
	 * @param instituteCategoryInstanceFactory
	 * @param instituteCategoryDao
	 */
	public InstituteCategoryDelegate(
			InstanceFactory<InstituteCategory> 
				instituteCategoryInstanceFactory,
			InstituteCategoryDao instituteCategoryDao,
			AuditComponentRetriever auditComponentRetriever) {
		this.instituteCategoryInstanceFactory = 
				instituteCategoryInstanceFactory;
		this.instituteCategoryDao = instituteCategoryDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates an institute category
	 * @param name - name
	 * @param sortOrder - sort order
	 * @param valid - valid
	 * @return InstituteCategory
	 * @throws DuplicateEntityFoundException - when the category already exists
	 * 	with the given name
	 */
	public InstituteCategory create(final String name, 
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
						if(this.instituteCategoryDao.find(name) != null){
							throw new DuplicateEntityFoundException(
									"Duplicate institute category found.");
						}
		InstituteCategory instituteCategory 
			= this.instituteCategoryInstanceFactory
				.createInstance(); 
		instituteCategory.setName(name);
		instituteCategory.setSortOrder(sortOrder);
		instituteCategory.setValid(valid);
		instituteCategory.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.instituteCategoryDao.makePersistent(instituteCategory);
		
	}
}
