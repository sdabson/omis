package omis.specialneed.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.specialneed.dao.SpecialNeedAssociableDocumentCategoryDao;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;

/**
 * Special need associable document category delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedAssociableDocumentCategoryDelegate {

	/* Data access objects. */
	
	private final SpecialNeedAssociableDocumentCategoryDao 
			specialNeedAssociableDocumentCategoryDao;
	

	/* Instance factories. */
	
	private final InstanceFactory<SpecialNeedAssociableDocumentCategory> 
			specialNeedAssociableDocumentCategoryInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Instantiates an implementation of special need category delegate with 
	 * the specified date access object and instance factory.
	 * 
	 * @param specialNeedAssociableDocumentCategoryDao special need associable 
	 * document category data access object
	 * @param specialNeedAssociableDocumentCategoryInstanceFactory special need 
	 * associable document category instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public SpecialNeedAssociableDocumentCategoryDelegate(
			final SpecialNeedAssociableDocumentCategoryDao 
				specialNeedAssociableDocumentCategoryDao,
			final InstanceFactory<SpecialNeedAssociableDocumentCategory> 
				specialNeedAssociableDocumentCategoryInstanceFactory,
				final AuditComponentRetriever auditComponentRetriever) {
		this.specialNeedAssociableDocumentCategoryDao = 
				specialNeedAssociableDocumentCategoryDao;
		this.specialNeedAssociableDocumentCategoryInstanceFactory = 
				specialNeedAssociableDocumentCategoryInstanceFactory; 
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Searches for a special need associable document category.
	 *
	 * @return list of special need associable document categories
	 */
	public List<SpecialNeedAssociableDocumentCategory> 
			findSpecialNeedAssociableDocumentCategories() {
		return this.specialNeedAssociableDocumentCategoryDao.findAll();
	}	
	
	/**
	 * Creates a special need associable document category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return special need associable document category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedAssociableDocumentCategory create(final String name, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.specialNeedAssociableDocumentCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Special need associable "
					+ "document category already exists");
		}
		SpecialNeedAssociableDocumentCategory category = this
				.specialNeedAssociableDocumentCategoryInstanceFactory
				.createInstance();
		category.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		populateSpecialNeedAssociableDocumentCategory(category, name, valid);
		return this.specialNeedAssociableDocumentCategoryDao
				.makePersistent(category);
	}
	
	/**
	 * Updates a special need associable document category.
	 * 
	 * @param category special need associable document category
	 * @param name name
	 * @param valid valid
	 * @return special need associable document category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedAssociableDocumentCategory update(
			final SpecialNeedAssociableDocumentCategory category, 
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.specialNeedAssociableDocumentCategoryDao.findExcluding(name,  
				category) != null) {
			throw new DuplicateEntityFoundException("Special need category "
					+ "already exists");
		}
		populateSpecialNeedAssociableDocumentCategory(category, name, valid);
		return this.specialNeedAssociableDocumentCategoryDao.makePersistent(
				category);		
	}

	/**
	 * Removes a special need associable document category.
	 * 
	 * @param category special need associable document category
	 */
	public void remove(final SpecialNeedAssociableDocumentCategory category) {
		this.specialNeedAssociableDocumentCategoryDao.makeTransient(category);
	}

	// Populates a special need associable document category
	private void populateSpecialNeedAssociableDocumentCategory(
			final SpecialNeedAssociableDocumentCategory category, 
			final String name, final Boolean valid) {
		category.setName(name);
		category.setValid(valid);
		category.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}
