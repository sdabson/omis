package omis.workrestriction.service;

import java.util.Date;
import java.util.List;

import omis.audit.domain.AuthorizationSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;
import omis.workrestriction.domain.WorkRestrictionNote;

/**
 * WorkRestrictionService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestrictionService {
	
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
	public WorkRestriction create(Offender offender, 
			WorkRestrictionCategory category, 
			AuthorizationSignature authorizationSignature, String notes) 
					throws DuplicateEntityFoundException;
	
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
	public WorkRestriction update(WorkRestriction workRestriction, 
			WorkRestrictionCategory category, 
			AuthorizationSignature authorizationSignature, String notes) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes specified work restriction
	 * @param workRestriction - work restriction to remove
	 */
	public void remove(WorkRestriction workRestriction);
	
	/**
	 * Finds and returns a list of all work restrictions by specified offender
	 * @param offender - offender
	 * @return list of all work restrictions by specified offender
	 */
	public List<WorkRestriction> findByOffender(Offender offender);
	
	/**
	 * Returns a list of user accounts by given query
	 * 
	 * @param query - query
	 * @return list of user accounts by given query
	 */
	public List<UserAccount> findUserAccounts(String query);
	
	/**
	 * Finds and returns a list of all work restriction categories
	 * @return list of all work restriction categories
	 */
	public List<WorkRestrictionCategory> findAllCategories();
	
	/**
	 * Creates and returns a workRestrictionNote with given parameters
	 * @param workRestriction
	 * @param value - String
	 * @param date
	 * @return workRestrictionNote with given parameters
	 * @throws DuplicateEntityFoundException - when a workRestrictionNote 
	 * already exists with given parameters
	 */
	public WorkRestrictionNote createNote(WorkRestriction workRestriction,
			String value, Date date) throws DuplicateEntityFoundException;
	
	/**
	 * Updates and returns a workRestrictionNote with given parameters
	 * @param workRestrictionNote
	 * @param value - String
	 * @param date
	 * @return updated workRestrictionNote with given parameters
	 * @throws DuplicateEntityFoundException - when a workRestrictionNote 
	 * already exists with given parameters
	 */
	public WorkRestrictionNote updateNote(
			WorkRestrictionNote workRestrictionNote, String value, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified workRestrictionNote
	 * @param workRestrictionNote - work restriction note to remove
	 */
	public void removeNote(WorkRestrictionNote workRestrictionNote);
	
	/**
	 * Finds and returns a list of all workRestrictionNotes by specified 
	 * workRestriction
	 * @param workRestriction
	 * @return list of all workRestrictionNotes by specified workRestriction
	 */
	public List<WorkRestrictionNote> findNotes(WorkRestriction workRestriction);
}
