package omis.paroleboardcondition.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleboardcondition.dao.ParoleBoardAgreementDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Parole Board Agreement already exists with the given Agreement "
			+ "and Category.";
	
	private final ParoleBoardAgreementDao paroleBoardAgreementDao;
	
	private final InstanceFactory<ParoleBoardAgreement> 
		paroleBoardAgreementInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ParoleBoardAgreementDelegate.
	 * @param paroleBoardAgreementDao - Parole Board Agreement DAO
	 * @param paroleBoardAgreementInstanceFactory - Parole Board Agreement
	 * Instance Factory
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public ParoleBoardAgreementDelegate(
			final ParoleBoardAgreementDao paroleBoardAgreementDao,
			final InstanceFactory<ParoleBoardAgreement> 
				paroleBoardAgreementInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.paroleBoardAgreementDao = paroleBoardAgreementDao;
		this.paroleBoardAgreementInstanceFactory =
				paroleBoardAgreementInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Parole Board Agreement with the specified properties.
	 * @param agreement - Agreement
	 * @param category - Parole Board Agreement Category
	 * @return Newly created Parole Board Agreement
	 * @throws DuplicateEntityFoundException - When a Parole Board Agreement
	 * already exists with the given Agreement and Category.
	 */
	public ParoleBoardAgreement create(final Agreement agreement,
			final ParoleBoardAgreementCategory category)
					throws DuplicateEntityFoundException {
		if (this.paroleBoardAgreementDao.find(agreement, category) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ParoleBoardAgreement paroleBoardAgreement = 
				this.paroleBoardAgreementInstanceFactory.createInstance();
		
		paroleBoardAgreement.setAgreement(agreement);
		paroleBoardAgreement.setCategory(category);
		paroleBoardAgreement.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		paroleBoardAgreement.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.paroleBoardAgreementDao
				.makePersistent(paroleBoardAgreement);
	}
	
	/**
	 * Updates a Parole Board Agreement with the specified properties.
	 * @param paroleBoardAgreement - Parole Board Agreement to update
	 * @param agreement - Agreement
	 * @param category - Parole Board Agreement Category
	 * @return Updated Parole Board Agreement
	 * @throws DuplicateEntityFoundException - When a Parole Board Agreement
	 * already exists with the given Agreement and Category.
	 */
	public ParoleBoardAgreement update(
			final ParoleBoardAgreement paroleBoardAgreement,
			final Agreement agreement,
			final ParoleBoardAgreementCategory category)
					throws DuplicateEntityFoundException {
		if (this.paroleBoardAgreementDao.findExcluding(agreement, category,
				paroleBoardAgreement) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}

		paroleBoardAgreement.setAgreement(agreement);
		paroleBoardAgreement.setCategory(category);
		paroleBoardAgreement.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.paroleBoardAgreementDao
				.makePersistent(paroleBoardAgreement);
	}
	
	/**
	 * Removes the specified Parole Board Agreement.
	 * @param paroleBoardAgreement - Parole Board Agreement to remove;
	 */
	public void remove(final ParoleBoardAgreement paroleBoardAgreement) {
		this.paroleBoardAgreementDao.makeTransient(paroleBoardAgreement);
	}
	
	/**
	 * Returns a list of all Parole Board Agreements. For use in Unit Testing.
	 * @return List of all Parole Board Agreements
	 */
	public List<ParoleBoardAgreement> findAll() {
		return this.paroleBoardAgreementDao.findAll();
	}
	
	
}
