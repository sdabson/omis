package omis.paroleboardcondition.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleboardcondition.dao.ParoleBoardAgreementCategoryDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement Category Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementCategoryDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Parole Board Agreement Category already exists with the "
			+ "given name.";
	
	private final ParoleBoardAgreementCategoryDao
		paroleBoardAgreementCategoryDao;
	
	private final InstanceFactory<ParoleBoardAgreementCategory> 
		paroleBoardAgreementCategoryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ParoleBoardAgreementCategoryDelegate.
	 * @param paroleBoardAgreementCategoryDao - Parole Board Agreement Category
	 * DAO
	 * @param paroleBoardAgreementCategoryInstanceFactory - Parole Board
	 * Agreement Category Instance Factory
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public ParoleBoardAgreementCategoryDelegate(
			final ParoleBoardAgreementCategoryDao
				paroleBoardAgreementCategoryDao,
			final InstanceFactory<ParoleBoardAgreementCategory> 
				paroleBoardAgreementCategoryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.paroleBoardAgreementCategoryDao = paroleBoardAgreementCategoryDao;
		this.paroleBoardAgreementCategoryInstanceFactory =
				paroleBoardAgreementCategoryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Parole Board Agreement Category with the specified name.
	 * @param name - String
	 * @return Newly created Parole Board Agreement Category
	 * @throws DuplicateEntityFoundException - When a Parole Board Agreement
	 * Category already exits with the specified name
	 */
	public ParoleBoardAgreementCategory create(final String name)
				throws DuplicateEntityFoundException {
		if (this.paroleBoardAgreementCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ParoleBoardAgreementCategory paroleBoardAgreementCategory = 
				this.paroleBoardAgreementCategoryInstanceFactory
				.createInstance();
		
		paroleBoardAgreementCategory.setName(name);
		paroleBoardAgreementCategory.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		paroleBoardAgreementCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.paroleBoardAgreementCategoryDao
				.makePersistent(paroleBoardAgreementCategory);
	}
	
	/**
	 * Updates specified Parole Board Agreement Category with the
	 * specified name.
	 * @param paroleBoardAgreementCategory - Parole Board Agreement Category
	 * to update
	 * @param name - String
	 * @return Updated Parole Board Agreement Category
	 * @throws DuplicateEntityFoundException - When a Parole Board Agreement
	 * Category already exits with the specified name
	 */
	public ParoleBoardAgreementCategory update(
			final ParoleBoardAgreementCategory paroleBoardAgreementCategory,
			final String name)
				throws DuplicateEntityFoundException {
		if (this.paroleBoardAgreementCategoryDao.findExcluding(
				name, paroleBoardAgreementCategory) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		paroleBoardAgreementCategory.setName(name);
		paroleBoardAgreementCategory.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		
		return this.paroleBoardAgreementCategoryDao
				.makePersistent(paroleBoardAgreementCategory);
	}
	
	/**
	 * Removes the specified Parole Board Agreement Category.
	 * @param paroleBoardAgreementCategory - Parole Board Agreement Category
	 * to remove
	 */
	public void remove(
			final ParoleBoardAgreementCategory paroleBoardAgreementCategory) {
		this.paroleBoardAgreementCategoryDao
				.makeTransient(paroleBoardAgreementCategory);
	}
	
	/**
	 * Returns a list of all Parole Board Agreement Categories.
	 * @return List of all Parole Board Agreement Categories.
	 */
	public List<ParoleBoardAgreementCategory> findAll() {
		return this.paroleBoardAgreementCategoryDao.findAll();
	}
	
}
