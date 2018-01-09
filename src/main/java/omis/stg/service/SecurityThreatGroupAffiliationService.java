package omis.stg.service;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;

/**
 * Service for security threat group affiliations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupAffiliationService {

	/**
	 * Returns security threat group affiliations for offender.
	 * 
	 * @param offender offender
	 * @return security threat group affiliations for offender
	 */
	List<SecurityThreatGroupAffiliation> findByOffender(Offender offender);
	
	/**
	 * Creates a security threat group affiliation.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param group group
	 * @param activityLevel activity level
	 * @param chapter chapter
	 * @param rank rank
	 * @param state State
	 * @param city city
	 * @param moniker moniker
	 * @param comment comment
	 * @param verificationSignature verification signature
	 * @return security threat group affiliation
	 * @throws DuplicateEntityFoundException if the affiliation exists
	 */
	SecurityThreatGroupAffiliation create(Offender offender,
			DateRange dateRange, SecurityThreatGroup group,
			SecurityThreatGroupActivityLevel activityLevel,
			SecurityThreatGroupChapter chapter,
			SecurityThreatGroupRank rank, State state, City city,
			String moniker, String comment,
			VerificationSignature verificationSignature)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates a security threat group affiliation.
	 * 
	 * @param affiliation affiliation
	 * @param dateRange date range
	 * @param group group
	 * @param activityLevel activity lever
	 * @param chapter chapter
	 * @param rank rank
	 * @param state State
	 * @param city city
	 * @param moniker moniker
	 * @param comment comment
	 * @param verificationSignature verification signature
	 * @return security threat group affiliation
	 * @throws DuplicateEntityFoundException if the affiliation existst
	 */
	SecurityThreatGroupAffiliation update(
			SecurityThreatGroupAffiliation affiliation,
			DateRange dateRange, SecurityThreatGroup group,
			SecurityThreatGroupActivityLevel activityLevel,
			SecurityThreatGroupChapter chapter,
			SecurityThreatGroupRank rank, State state, City city,
			String moniker, String comment,
			VerificationSignature verificationSignature)
				throws DuplicateEntityFoundException;
	
	/**
	 * Removes a security threat group affiliation.
	 * 
	 * @param affiliation security threat group affiliation to remove
	 */
	void remove(SecurityThreatGroupAffiliation affiliation);
	
	
	/**
	 * Create a security threat group affiliation note.
	 * 
	 * @param affiliation security threat group affiliation
	 * @param date date
	 * @param note note
	 * @return newly created security threat group affiliation note
	 * @throws DuplicateEntityFoundException thrown when a duplicate
	 * security threat group affiliation note is found
	 */
	SecurityThreatGroupAffiliationNote createNote(SecurityThreatGroupAffiliation affiliation, 
			Date date, String note) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified security threat group affiliation note.
	 * 
	 * @param affiliationNote security threat group affiliation note
	 * @param date date
	 * @param note note
	 * @return updated security threat group affiliation note
	 * @throws DuplicateEntityFoundException thrown when a duplicate
	 * security threat group affiliation note is found
	 */
	SecurityThreatGroupAffiliationNote updateNote(
			SecurityThreatGroupAffiliationNote affiliationNote,
			Date date, String note) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified security threat group affiliation note.
	 * @param affiliationNote
	 */
	void removeNote(SecurityThreatGroupAffiliationNote affiliationNote);
	
	/**
	 * Creates a new security threat group chapter with the specified name and 
	 * group
	 * @param name security threat group chapter name
	 * @param securityThreatGroup security threat group
	 * @return security threat group chapter
	 */
	SecurityThreatGroupChapter createChapter(String name, 
			SecurityThreatGroup securityThreatGroup) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates a new security threat group rank with the specified name and 
	 * group
	 * @param name security threat group rank name
	 * @param securityThreatGroup security threat group
	 * @return security threat group rank
	 * @throws DuplicateEntityFoundException
	 */
	SecurityThreatGroupRank createRank(String name, 
			SecurityThreatGroup securityThreatGroup) 
					throws DuplicateEntityFoundException;

	/**
	 * Returns security threat groups.
	 * 
	 * @return security threat groups
	 */
	List<SecurityThreatGroup> findGroups();
	
	/**
	 * Returns security threat group activity levels.
	 * 
	 * @return security threat group activity levels
	 */
	List<SecurityThreatGroupActivityLevel> findActivityLevels();
	
	/**
	 * Returns security threat group chapters.
	 * 
	 * @return security threat group chapters
	 */
	List<SecurityThreatGroupChapter> findChapters();
	
	/**
	 * Returns security threat group chapters from the specified group
	 * @param group security threat group
	 * @return list of security threat group chapters
	 */
	List<SecurityThreatGroupChapter> findChaptersByGroup(
			SecurityThreatGroup group);
	
	/**
	 * Returns security threat group ranks.
	 * 
	 * @return security threat group ranks
	 */
	List<SecurityThreatGroupRank> findRanks();
	
	/**
	 * Returns security threat group ranks from the specified group
	 * @param group security threat group
	 * @return security threat group rank
	 */
	List<SecurityThreatGroupRank> findRanksByGroup(SecurityThreatGroup group);
	
	/**
	 * Returns States.
	 * 
	 * @return States
	 */
	List<State> findStates();
	
	/**
	 * Returns cities by State.
	 * 
	 * @param state State
	 * @return cities by State
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Returns verification methods.
	 * 
	 * @return verification methods
	 */
	List<VerificationMethod> findVerificationMethods();
	
	/**
	 * Returns a list of security threat group affiliation notes for the 
	 * specified security threat group affiliation.
	 * 
	 * @param affiliation security threat group
	 * @return list of security threat group notes
	 */
	List<SecurityThreatGroupAffiliationNote> findAffiliationNotesByAffiliation(
			SecurityThreatGroupAffiliation affiliation);
}