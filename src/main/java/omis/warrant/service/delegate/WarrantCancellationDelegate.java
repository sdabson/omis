
package omis.warrant.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.warrant.dao.WarrantCancellationDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;
import omis.warrant.domain.component.ClearSignature;

/**
 * WarrantCancellationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCancellationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A WarrantCancellation already exists for the specified Warrant.";
	
	private final WarrantCancellationDao warrantCancellationDao;
	
	private final InstanceFactory<WarrantCancellation> 
		warrantCancellationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for WarrantCancellationDelegate
	 * @param warrantCancellationDao
	 * @param warrantCancellationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public WarrantCancellationDelegate(
			final WarrantCancellationDao warrantCancellationDao,
			final InstanceFactory<WarrantCancellation> 
				warrantCancellationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.warrantCancellationDao = warrantCancellationDao;
		this.warrantCancellationInstanceFactory = warrantCancellationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a WarrantCancellation with the specified properties
	 * @param warrant - Warrant
	 * @param date - Date
	 * @param comment - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Newly created WarrantCancellation
	 * @throws DuplicateEntityFoundException - When a WarrantCancellation already
	 * exists for the specified Warrant
	 */
	public WarrantCancellation create(final Warrant warrant, final Date date,
			final String comment, final Person clearedBy, final Date clearedByDate)
			throws DuplicateEntityFoundException{
		if(this.warrantCancellationDao.find(warrant) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		WarrantCancellation warrantCancellation = 
				this.warrantCancellationInstanceFactory.createInstance();
		
		warrantCancellation.setWarrant(warrant);
		warrantCancellation.setDate(date);
		warrantCancellation.setComment(comment);
		warrantCancellation.setClearSignature(new ClearSignature(
				clearedBy, clearedByDate));
		warrantCancellation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		warrantCancellation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantCancellationDao.makePersistent(warrantCancellation);
	}
	
	/**
	 * Updates a WarrantCancellation with the specified properties
	 * @param warrantCancellation - WarrantCancellation to update
	 * @param date - Date
	 * @param comment - String
	 * @param clearedBy - Person
	 * @param clearedByDate - Date
	 * @return Updated WarrantCancellation
	 * @throws DuplicateEntityFoundException - When another WarrantCancellation
	 * exists with specified WarrantCancellation's warrant
	 */
	public WarrantCancellation update(
			final WarrantCancellation warrantCancellation,
			final Date date, final String comment, final Person clearedBy,
			final Date clearedByDate)
			throws DuplicateEntityFoundException{
		if(this.warrantCancellationDao.findExcluding(
				warrantCancellation.getWarrant(), warrantCancellation) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		warrantCancellation.setDate(date);
		warrantCancellation.setComment(comment);
		warrantCancellation.setClearSignature(new ClearSignature(
				clearedBy, clearedByDate));
		warrantCancellation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantCancellationDao.makePersistent(warrantCancellation);
	}
	
	/**
	 * Removes a WarrantCancellation
	 * @param warrantCancellation - WarrantCancellation to remove
	 */
	public void remove(final WarrantCancellation warrantCancellation){
		this.warrantCancellationDao.makeTransient(warrantCancellation);
	}
	
	/**
	 * Returns a WarrantCancellation by specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantCancellation by specified Warrant
	 */
	public WarrantCancellation findByWarrant(final Warrant warrant){
		return this.warrantCancellationDao.find(warrant);
	}
	
}
