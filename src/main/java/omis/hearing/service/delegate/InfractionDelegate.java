package omis.hearing.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.hearing.dao.InfractionDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.Infraction;
import omis.hearing.domain.component.Resolution;
import omis.instance.factory.InstanceFactory;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * InfractionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 17, 2017)
 *@since OMIS 3.0
 *
 */
public class InfractionDelegate {
	
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Infraction already exists with given Hearing, ConditionViolation, and DisciplinaryCodeViolation.";
	
	private final InfractionDao infractionDao;
	
	private final InstanceFactory<Infraction> 
		infractionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for InfractionDelegate
	 * @param infractionDao
	 * @param infractionInstanceFactory
	 * @param auditComponentRetriever
	 */
	public InfractionDelegate(
			final InfractionDao infractionDao,
			final InstanceFactory<Infraction> 
				infractionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.infractionDao = infractionDao;
		this.infractionInstanceFactory = infractionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new Infraction with the specified properties
	 * @param hearing - Hearing
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @param resolution - Resolution
	 * @return Newly created Infraction
	 * @throws DuplicateEntityFoundException - When an Infraction already exists
	 * with given Hearing, ConditionViolation, and DisciplinaryCodeViolation.
	 * @throws DuplicateEntityFoundException
	 */
	public Infraction create(final Hearing hearing,
			final ConditionViolation conditionViolation,
			final DisciplinaryCodeViolation disciplinaryCodeViolation,
			final Resolution resolution)
					throws DuplicateEntityFoundException{
		if(this.infractionDao.find(hearing, conditionViolation,
				disciplinaryCodeViolation) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		Infraction infraction = 
				this.infractionInstanceFactory.createInstance();
		
		
		infraction.setConditionViolation(conditionViolation);
		infraction.setDisciplinaryCodeViolation(disciplinaryCodeViolation);
		infraction.setResolution(resolution);
		infraction.setHearing(hearing);
		infraction.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		infraction.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.infractionDao.makePersistent(infraction);
	}
	
	/**
	 * Updates an Infraction with the specified properties
	 * @param infraction - Infraction to update
	 * @param conditionViolation - ConditionViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation
	 * @param resolution - Resolution
	 * @return Updated Infraction
	 * @throws DuplicateEntityFoundException - When an Infraction already exists
	 * with given Hearing, ConditionViolation, and DisciplinaryCodeViolation.
	 */
	public Infraction update(final Infraction infraction,
			final ConditionViolation conditionViolation,
			final DisciplinaryCodeViolation disciplinaryCodeViolation,
			final Resolution resolution)
					throws DuplicateEntityFoundException{
		if(this.infractionDao.findExcluding(infraction.getHearing(),
				conditionViolation, disciplinaryCodeViolation, infraction) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		infraction.setConditionViolation(conditionViolation);
		infraction.setDisciplinaryCodeViolation(disciplinaryCodeViolation);
		infraction.setResolution(resolution);
		infraction.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.infractionDao.makePersistent(infraction);
	}
	
	/**
	 * Removes an Infraction
	 * @param infraction - Infraction to remove
	 */
	public void remove(final Infraction infraction){
		this.infractionDao.makeTransient(infraction);
	}
	
	/**
	 * Returns a list of Infractions by specified Hearing
	 * @param hearing - Hearing
	 * @return List of Infractions by specified Hearing
	 */
	public List<Infraction> findByHearing(final Hearing hearing){
		return this.infractionDao.findByHearing(hearing);
	}
	
}
