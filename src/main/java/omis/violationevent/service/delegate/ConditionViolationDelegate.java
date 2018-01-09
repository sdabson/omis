package omis.violationevent.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Condition;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.violationevent.dao.ConditionViolationDao;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * ConditionViolationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 15, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionViolationDelegate {
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Condition Violation already exists with given Condition and Violation Event";
	
	private final ConditionViolationDao conditionViolationDao;
	
	private final InstanceFactory<ConditionViolation> 
		conditionViolationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ConditionViolationDelegate
	 * @param conditionViolationDao
	 * @param conditionViolationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ConditionViolationDelegate(
			final ConditionViolationDao conditionViolationDao,
			final InstanceFactory<ConditionViolation> 
				conditionViolationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.conditionViolationDao = conditionViolationDao;
		this.conditionViolationInstanceFactory = conditionViolationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Create a ConditionViolation with specified properties
	 * @param condition - Condition
	 * @param violationEvent - ViolationEvent
	 * @return Newly created ConditionViolation
	 * @throws DuplicateEntityFoundExcepton - when ConditionViolation already
	 * exists with given Condition and ViolationEvent
	 */
	public ConditionViolation create(final Condition condition,
			final ViolationEvent violationEvent)
			throws DuplicateEntityFoundException{
		if(this.conditionViolationDao.find(condition, violationEvent) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ConditionViolation conditionViolation = 
				this.conditionViolationInstanceFactory.createInstance();
		
		conditionViolation.setCondition(condition);
		conditionViolation.setViolationEvent(violationEvent);
		conditionViolation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		conditionViolation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.conditionViolationDao.makePersistent(conditionViolation);
	}
	
	/**
	 * Updates a ConditionViolation with specified properties
	 * @param conditionViolation - ConditionViolation to update
	 * @param condition - Condition
	 * @return Updated ConditionViolation
	 * @throws DuplicateEntityFoundExcepton - when ConditionViolation already
	 * exists with given Condition and ViolationEvent
	 */
	public ConditionViolation update(final ConditionViolation conditionViolation,
			final Condition condition)
			throws DuplicateEntityFoundException{
		if(this.conditionViolationDao.findExcluding(condition, 
				conditionViolation.getViolationEvent(), conditionViolation)
					!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		conditionViolation.setCondition(condition);
		conditionViolation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.conditionViolationDao.makePersistent(conditionViolation);
	}
	
	/**
	 * Removes a ConditionViolation
	 * @param conditionViolation - ConditionViolation to remove
	 */
	public void remove(final ConditionViolation conditionViolation){
		this.conditionViolationDao.makeTransient(conditionViolation);
	}
	
	/**
	 * Finds and returns a list of ConditionViolations by specified 
	 * ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of ConditionViolations by specified ViolationEvent
	 */
	public List<ConditionViolation> findByViolationEvent(
			final ViolationEvent violationEvent){
		return this.conditionViolationDao.findByViolationEvent(violationEvent);
	}
	
	/**
	 * Finds and returns a list of ConditionViolations with no 
	 * Infraction/resolution association by specified ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of ConditionViolations  with no 
	 * Infraction/resolution association by specified ViolationEvent
	 */
	public List<ConditionViolation> findUnresolvedByViolationEvent(
			final ViolationEvent violationEvent){
		return this.conditionViolationDao.findUnresolvedByViolationEvent(
				violationEvent);
	}
	
	/**
	 * Returns a ConditionViolation with the specified ID
	 * @param id - Long
	 * @return ConditionViolation with specified ID
	 */
	public ConditionViolation findById(final Long id){
		return this.conditionViolationDao.findById(id, false);
	}
}
