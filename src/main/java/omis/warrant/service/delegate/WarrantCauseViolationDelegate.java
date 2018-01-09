package omis.warrant.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.warrant.dao.WarrantCauseViolationDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCauseViolation;

/**
 * WarrantCauseDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCauseViolationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A WarrantCauseViolation already exists with given Cause and "
			+ "Condition for the specified Warrant.";
	
	private final WarrantCauseViolationDao warrantCauseViolationDao;
	
	private final InstanceFactory<WarrantCauseViolation> 
		warrantCauseViolationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for WarrantCauseDelegate
	 * @param warrantCauseViolationDao
	 * @param warrantCauseViolationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public WarrantCauseViolationDelegate(
			final WarrantCauseViolationDao warrantCauseViolationDao,
			final InstanceFactory<WarrantCauseViolation> 
				warrantCauseViolationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.warrantCauseViolationDao = warrantCauseViolationDao;
		this.warrantCauseViolationInstanceFactory =
				warrantCauseViolationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a WarrantCauseViolation with the specified properties
	 * @param warrant - Warrant
	 * @param cause - CourtCase
	 * @param condition - Condition
	 * @param description - String
	 * @return Newly created WarrantCauseViolation
	 * @throws DuplicateEntityFoundException - When a WarrantCauseViolation already
	 * exists with the given Cause and Condition for the specified Warrant
	 */
	public WarrantCauseViolation create(final Warrant warrant,
			final CourtCase cause, final Condition condition,
			final String description)
					throws DuplicateEntityFoundException{
		if(this.warrantCauseViolationDao.find(warrant, cause, condition) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		WarrantCauseViolation warrantCauseViolation = 
				this.warrantCauseViolationInstanceFactory.createInstance();
		
		warrantCauseViolation.setWarrant(warrant);
		warrantCauseViolation.setCause(cause);
		warrantCauseViolation.setCondition(condition);
		warrantCauseViolation.setDescription(description);
		warrantCauseViolation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		warrantCauseViolation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantCauseViolationDao.makePersistent(warrantCauseViolation);
	}
	
	/**
	 * Updates a WarrantCauseViolation with the specified properties
	 * @param warrantCauseViolation - WarrantCauseViolation to update
	 * @param cause - CourtCase
	 * @param condition - Condition
	 * @param description - String
	 * @return Updated WarrantCauseViolation
	 * @throws DuplicateEntityFoundException - When a WarrantCauseViolation already
	 * exists with the given Cause and Condition for the specified
	 * WarrantCauseViolation Warrant
	 */
	public WarrantCauseViolation update(
			final WarrantCauseViolation warrantCauseViolation,
			final CourtCase cause, final Condition condition,
			final String description)
					throws DuplicateEntityFoundException{
		if(this.warrantCauseViolationDao.findExcluding(
				warrantCauseViolation.getWarrant(),
				cause, condition, warrantCauseViolation) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		warrantCauseViolation.setCause(cause);
		warrantCauseViolation.setCondition(condition);
		warrantCauseViolation.setDescription(description);
		warrantCauseViolation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantCauseViolationDao.makePersistent(warrantCauseViolation);
	}
	
	/**
	 * Removes a WarrantCauseViolation
	 * @param warrantCauseViolation - WarrantCauseViolation to remove
	 */
	public void remove(final WarrantCauseViolation warrantCause){
		this.warrantCauseViolationDao.makeTransient(warrantCause);
	}
	
	/**
	 * Returns a list of all WarrantCauseViolations with specified Warrant
	 * @param warrant - Warrant
	 * @return List of all WarrantCauseViolations with specified Warrant
	 */
	public List<WarrantCauseViolation> findByWarrant(final Warrant warrant){
		return this.warrantCauseViolationDao.findByWarrant(warrant);
	}
	
}
