package omis.separationneed.service;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedNote;
import omis.separationneed.domain.SeparationNeedReason;
import omis.separationneed.domain.SeparationNeedReasonAssociation;
import omis.separationneed.domain.SeparationNeedRemovalReason;

/**
 * Services for separation need.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 21 2013)
 * @since OMIS 3.0
 */
public interface SeparationNeedService {
	
	/**
	 * Returns a list of all separation needs.
	 * 
	 * @return list of separation needs
	 */
	List<SeparationNeed> findAll();
	
	/**
	 * Returns a list of separation needs with the specified offender.
	 * 
	 * @param offender offender
	 * @return list of separation needs
	 */
	List<SeparationNeed> findByOffender(Offender offender);
	
	/**
	 * Returns a list of separation needs with the specified target offender.
	 * 
	 * @param offender offender
	 * @return list of separation needs
	 */
	List<SeparationNeed> findByTargetOffender(Offender offender);

	/**
	 * Removes the specified separation need.
	 * 
	 * @param separationNeed separation need
	 */
	void remove(SeparationNeed separationNeed);
	
	/**
	 * Checks for an existing relationship between two offenders
	 * if one does not exist, create new relationship 
	 * offender is always the "firstPerson" value of relationship. The
	 * relationship is then set to the specified separation need.
	 * 
	 * @param separationNeed separation need
	 * @param offender offender
	 * @param otherOffender other offender
	 * @return relationship
	 * @throws ReflexiveRelationshipException if both people are the same
	 */
	SeparationNeed associate(SeparationNeed separationNeed, Offender offender, 
			Offender otherOffender) throws ReflexiveRelationshipException;

	/**
	 * Returns a list of separation need reasons.
	 * 
	 * @return list of separation need reasons
	 */
	List<SeparationNeedReason> findReasons();

	/**
	 * Returns a list of separation need reason associations for the specified
	 * separation need.
	 * 
	 * @param separationNeed separation need
	 * @return list of separation need reason associations
	 */
	List<SeparationNeedReasonAssociation>
		findReasonAssociationsBySeparationNeed(SeparationNeed separationNeed);

	/**
	 * Returns a list of separation need notes for the specified separation
	 * need.
	 * 
	 * @param separationNeed separation need
	 * @return list of separation need notes
	 */
	List<SeparationNeedNote> findNotesBySeparationNeed(
			SeparationNeed separationNeed);
	
	/**
	 * Creates a new separation need.
	 * 
	 * @param relationship relationship
	 * @param date date
	 * @param creationComment creation comment
	 * @param reportingStaff reporting staff
	 * @param removalDate removal date
	 * @param removalReason separation need removal reason
	 * @param removal comment
	 * @return newly created separation need
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DateConflictException date conflict exception
	 */
	SeparationNeed create(Relationship relationship, Date date,
			String creationComment, Person reportingStaff,
			Date removalDate, SeparationNeedRemovalReason removalReason,
			String removalComment)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates the specified separation need.
	 * 
	 * @param separationNeed separation need
	 * @param relationship relationship
	 * @param date date
	 * @param creationComment creation comment
	 * @param reportingStaff reporting staff
	 * @param removalDate removal date
	 * @param removalReason removal reason
	 * @param removalComment removal comment
	 * @return updated separation need
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need is found
	 * @throws DateConflictException thrown when a separation need, with the
	 * same relationship, exists in a conflicting range of dates
	 */
	SeparationNeed update(SeparationNeed separationNeed,
			Relationship relationship, Date date, String creationComment, 
			Person reportingStaff, Date removalDate,
			SeparationNeedRemovalReason removalReason, String removalComment)
		throws DuplicateEntityFoundException, DateConflictException;

	/**
	 * Returns the relationship between the offender, and target offender.
	 * 
	 * @param offender offender
	 * @param targetOffender target offender
	 * @return relationship
	 */
	Relationship findRelationship(Offender offender, Offender targetOffender);

	/**
	 * Creates a relationship between the offender, and target offender
	 * 
	 * @param offender offender
	 * @param targetOffender target offender
	 * @return newly created relationship
	 */
	Relationship createRelationship(Offender offender, Offender targetOffender) 
			throws ReflexiveRelationshipException;
	
	/**
	 * Creates a separation need reason association for the specified reason and
	 * separation need.
	 * 
	 * @param separationNeed separation need
	 * @param reason separation need reason
	 * @return separation need reason association
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need reason association is found
	 */
	SeparationNeedReasonAssociation associateReason(
			SeparationNeed separationNeed, SeparationNeedReason reason)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified separation need reason association.
	 * 
	 * @param association separation need reason association
	 * @param reason separation need reason
	 * @return updated separation need reason association
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need reason association is found
	 */
	SeparationNeedReasonAssociation updateAssociatedReason(
			SeparationNeedReasonAssociation association,
			SeparationNeedReason reason)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified separation need reason association.
	 * 
	 * @param association separation need reason association
	 */
	void removeAssociatedReason(SeparationNeedReasonAssociation association);
	
	/**
	 * Creates a separation need note.
	 * 
	 * @param separationNeed separation need
	 * @param date date
	 * @param value note value
	 * @return created separation need note
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need note is found
	 */
	SeparationNeedNote addNote(SeparationNeed separationNeed, Date date,
			String value) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified separation need note.
	 * 
	 * @param note separation need note
	 * @param date date
	 * @param value note value
	 * @return updated separation need note
	 * @throws DuplicateEntityFoundException thrown when a duplicate separation
	 * need note is found
	 */
	SeparationNeedNote updateNote(SeparationNeedNote note, Date date,
			String value)throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified separation need note.
	 * 
	 * @param note separation need note
	 */
	void removeNote(SeparationNeedNote note);

	/**
	 * Returns removal reasons.
	 * 
	 * @return list of removal reasons
	 */
	List<SeparationNeedRemovalReason> findRemovalReasons();
}