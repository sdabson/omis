package omis.workrestriction.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.workrestriction.dao.WorkRestrictionDao;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
		= "Work Restriction Already Exists for Given Offender and Category.";
	
	private final WorkRestrictionDao workRestrictionDao;
	
	private final InstanceFactory<WorkRestriction> workRestrictionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for Work Restriction Delegate
	 * @param workRestrictionDao - work restriction dao
	 * @param workRestrictionInstanceFactory - work restriction instance factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public WorkRestrictionDelegate(WorkRestrictionDao workRestrictionDao,
			InstanceFactory<WorkRestriction> workRestrictionInstanceFactory,
			AuditComponentRetriever auditComponentRetriever) {
		this.workRestrictionDao = workRestrictionDao;
		this.workRestrictionInstanceFactory = workRestrictionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates and returns a work restriction with given parameters
	 * @param offender - offender
	 * @param category - work restriction category
	 * @param authorizationSignature - authorization signature
	 * @param notes - notes
	 * @return Newly Created Work Restriction with Given Parameters
	 * @throws DuplicateEntityFoundException - when a work restriction already
	 * exists with given offender and category
	 */
	public WorkRestriction create(final Offender offender, 
			final WorkRestrictionCategory category, 
			final AuthorizationSignature authorizationSignature, 
			final String notes)throws DuplicateEntityFoundException{
		if(this.workRestrictionDao.find(offender, category) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		WorkRestriction workRestriction = this.workRestrictionInstanceFactory
				.createInstance();
		
		workRestriction.setOffender(offender);
		workRestriction.setAuthorizationSignature(authorizationSignature);
		workRestriction.setCategory(category);
		workRestriction.setNotes(notes);
		workRestriction.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		workRestriction.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.workRestrictionDao.makePersistent(workRestriction);
	}
	
	/**
	 * Update and returns a work restriction with given parameters
	 * @param workRestriction - work restriction to update
	 * @param category - work restriction category
	 * @param authorizationSignature - authorization signature
	 * @param notes - notes
	 * @return Updated Work Restriction with Given Parameters
	 * @throws DuplicateEntityFoundException - when a work restriction already
	 * exists with given offender and category
	 */
	public WorkRestriction update(final WorkRestriction workRestriction, 
			final WorkRestrictionCategory category, 
			final AuthorizationSignature authorizationSignature,
			final String notes)throws DuplicateEntityFoundException{
		//TODO: Was this on purpose that there is no duplicate entity check? I'm adding one.
		if(this.workRestrictionDao.findExcluding(workRestriction,
				workRestriction.getOffender(), category) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		workRestriction.setAuthorizationSignature(authorizationSignature);
		workRestriction.setCategory(category);
		workRestriction.setNotes(notes);
		workRestriction.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		
		return this.workRestrictionDao.makePersistent(workRestriction);
	}
	
	/**
	 * Removes specified work restriction
	 * @param workRestriction - work restriction to remove
	 */
	public void remove(final WorkRestriction workRestriction){
		this.workRestrictionDao.makeTransient(workRestriction);
	}
	
	/**
	 * Finds and returns a list of all work restrictions by specified offender
	 * @param offender - offender
	 * @return list of all work restrictions by specified offender
	 */
	public List<WorkRestriction> findByOffender(final Offender offender){
		return this.workRestrictionDao.findByOffender(offender);
	}
	
}
