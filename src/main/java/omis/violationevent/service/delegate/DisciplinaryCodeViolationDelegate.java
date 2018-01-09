package omis.violationevent.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.violationevent.dao.DisciplinaryCodeViolationDao;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * DisciplinaryCodeViolationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 18, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeViolationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Disciplinary Code Violation already exists with given Disciplinary"
			+ " Code for specified Violation Event";
	
	private final DisciplinaryCodeViolationDao disciplinaryCodeViolationDao;
	
	private final InstanceFactory<DisciplinaryCodeViolation> 
		disciplinaryCodeViolationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for DisciplinaryCodeViolationDelegate
	 * @param disciplinaryCodeViolationDao
	 * @param disciplinaryCodeViolationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public DisciplinaryCodeViolationDelegate(
			final DisciplinaryCodeViolationDao disciplinaryCodeViolationDao,
			final InstanceFactory<DisciplinaryCodeViolation> 
				disciplinaryCodeViolationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.disciplinaryCodeViolationDao = disciplinaryCodeViolationDao;
		this.disciplinaryCodeViolationInstanceFactory =
				disciplinaryCodeViolationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a DisciplinaryCodeViolation with specified properties
	 * @param disciplinaryCode - DisciplinaryCode
	 * @param violationEvent - ViolationEvent
	 * @return Newly Created DisciplinaryCodeViolation
	 * @throws DuplicateEntityFoundException - When a DisciplinaryCodeViolation
	 * already exists with specified DisciplinaryCode for given ViolationEvent
	 */
	public DisciplinaryCodeViolation create(
			final DisciplinaryCode disciplinaryCode,
			final ViolationEvent violationEvent)
					throws DuplicateEntityFoundException{
		if(this.disciplinaryCodeViolationDao.find(disciplinaryCode,
				violationEvent) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		DisciplinaryCodeViolation disciplinaryCodeViolation = 
				this.disciplinaryCodeViolationInstanceFactory.createInstance();
		
		disciplinaryCodeViolation.setDisciplinaryCode(disciplinaryCode);
		disciplinaryCodeViolation.setViolationEvent(violationEvent);
		disciplinaryCodeViolation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		disciplinaryCodeViolation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.disciplinaryCodeViolationDao
				.makePersistent(disciplinaryCodeViolation);
	}
	
	/**
	 * Updates a DisciplinaryCodeViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation to update
	 * @param disciplinaryCode - DisciplinaryCode
	 * @return Updated DisciplinaryCode
	 * @throws DuplicateEntityFoundException - when DisciplinaryCodeViolation
	 * already exists with given DisciplinaryCode for its ViolationEvent
	 */
	public DisciplinaryCodeViolation update(
			final DisciplinaryCodeViolation disciplinaryCodeViolation,
			final DisciplinaryCode disciplinaryCode)
					throws DuplicateEntityFoundException{
		if(this.disciplinaryCodeViolationDao
				.findExcluding(disciplinaryCodeViolation, disciplinaryCode,
						disciplinaryCodeViolation.getViolationEvent()) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		disciplinaryCodeViolation.setDisciplinaryCode(disciplinaryCode);
		disciplinaryCodeViolation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.disciplinaryCodeViolationDao
				.makePersistent(disciplinaryCodeViolation);
	}
	
	/**
	 * Removes the specified DisciplinaryCodeViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation to remove
	 */
	public void remove(
			final DisciplinaryCodeViolation disciplinaryCodeViolation){
		this.disciplinaryCodeViolationDao
			.makeTransient(disciplinaryCodeViolation);
	}
	
	
	/**
	 * Finds and returns a list of DisciplinaryCodeViolations found by 
	 * specified violationEvent
	 * @param violationEvent - ViolationEvent
	 * @return list of DisciplinaryCodeViolations found by 
	 * specified violationEvent
	 */
	public List<DisciplinaryCodeViolation> findByViolationEvent(
			final ViolationEvent violationEvent){
		return this.disciplinaryCodeViolationDao
				.findByViolationEvent(violationEvent);
	}
	
	/**
	 * Finds and returns a list of DisciplinaryCodeViolation with no 
	 * Infraction/resolution association by specified ViolationEvent
	 * @param violationEvent - ViolationEvent
	 * @return List of DisciplinaryCodeViolation  with no 
	 * Infraction/resolution association by specified ViolationEvent
	 */
	public List<DisciplinaryCodeViolation> findUnresolvedByViolationEvent(
			final ViolationEvent violationEvent){
		return this.disciplinaryCodeViolationDao.findUnresolvedByViolationEvent(
				violationEvent);
	}
	
	/**
	 * Returns a DisciplinaryCodeViolation with the specified ID
	 * @param id - Long
	 * @return DisciplinaryCodeViolation with specified ID
	 */
	public DisciplinaryCodeViolation findById(final Long id){
		return this.disciplinaryCodeViolationDao.findById(id, false);
	}
}
