package omis.grievance.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceNote;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.offender.domain.Offender;
import omis.user.domain.UserAccount;

/**
 * Service for grievances.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.2 (Oct 3, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceService {

	/**
	 * Returns grievances for offender.
	 * 
	 * @param offender offender
	 * @return grievances for offender
	 */
	List<Grievance> findGrievancesForOffender(Offender offender);
	
	/**
	 * Creates grievance.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param unit unit
	 * @param openedDate opened date
	 * @param informalFileDate informal file date
	 * @param subject subject
	 * @param complaintCategory complaint category
	 * @param description description
	 * @param initialComment initial comment
	 * @return created grievance
	 * @throws DuplicateEntityFoundException if grievance exists
	 */
	Grievance create(Offender offender, GrievanceLocation location,
			GrievanceUnit unit, Date openedDate, Date informalFileDate,
			GrievanceSubject subject,
			GrievanceComplaintCategory complaintCategory,
			String description, String initialComment)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates grievance.
	 * 
	 * @param grievance grievance
	 * @param location location
	 * @param unit unit
	 * @param openedDate opened date
	 * @param informalFileDate informal file date
	 * @param complaintCategory complaint category
	 * @param description description
	 * @throws DuplicateEntityFoundException if grievance exists
	 */
	Grievance update(Grievance grievance, GrievanceLocation location,
			GrievanceUnit unit, Date openedDate, Date informalFileDate,
			GrievanceComplaintCategory complaintCategory,
			String description, String initialComment)
				throws DuplicateEntityFoundException;
	
	/**
	 * Returns grievance subjects.
	 * 
	 * @return grievance subjects
	 */
	List<GrievanceSubject> findSubjects();
	
	/**
	 * @param subject grievance subject
	 * Returns complaint categories.
	 * 
	 * @return complaint categories
	 */
	List<GrievanceComplaintCategory> findComplaintCategoriesBySubject(
		GrievanceSubject subject);
	
	/**
	 * Returns disposition reasons.
	 * 
	 * @return disposition reasons
	 * @deprecated find by status instead
	 */
	@Deprecated
	List<GrievanceDispositionReason> findDispositionReasons();
	
	/**
	 * Returns disposition reasons by status.
	 * 
	 * @param status status
	 * @return disposition reasons by status
	 */
	List<GrievanceDispositionReason> findDispositionReasonsByStatus(
			GrievanceDispositionStatus status);
	
	/**
	 * Returns disposition statuses by levels.
	 * 
	 * @param levels levels
	 * @return disposition statuses by levels
	 */
	List<GrievanceDispositionStatus> findDispositionStatusesByLevels(
			GrievanceDispositionLevel... levels);
	
	/**
	 * Returns opened disposition statuses by levels.
	 * 
	 * @param levels levels
	 * @return opened disposition statuses by levels
	 */
	List<GrievanceDispositionStatus> findOpenedDispositionStatusesByLevels(
			GrievanceDispositionLevel... levels);
	
	/**
	 * Returns coordinator level disposition for grievance.
	 * 
	 * <p>If no such disposition exists, returns {@code null}.
	 * 
	 * @param grievance grievance
	 * @return coordinator level disposition for grievance
	 */
	GrievanceDisposition findCoordinatorLevelDisposition(Grievance grievance);
	
	/**
	 * Returns warden level disposition for grievance.
	 * 
	 * <p>If no such disposition exists, returns {@code null}.
	 * 
	 * @param grievance grievance
	 * @return warden level disposition for grievance
	 */
	GrievanceDisposition findWardenLevelDisposition(Grievance grievance);
	
	/**
	 * Returns FHA level disposition for grievance.
	 * 
	 * <p>If no such disposition exists, returns {@code null}.
	 * 
	 * @param grievance grievance
	 * @return FHA level disposition for grievance
	 */
	GrievanceDisposition findFhaLevelDisposition(Grievance grievance);
	
	/**
	 * Returns director level disposition for grievance. 
	 * 
	 * <p>If no such disposition exists, returns {@code null}.
	 * 
	 * @param grievance grievance
	 * @return director level disposition for grievance
	 */
	GrievanceDisposition findDirectorLevelDisposition(Grievance grievance);
	
	/**
	 * Returns a list of location. 
	 * 
	 * @return A list of location
	 */
	 List<GrievanceLocation> findLocations();
	 
	/**
	 * Returns a list of units. 
	 * 
	 * @return A list of units
	 */
	 List<GrievanceUnit> findUnits();
	 
	 /**
	  * Returns pending grievance disposition status for level.
	  * 
	  * @param level level
	  * @return pending disposition status for level
	  */
	 GrievanceDispositionStatus findPendingDispositionStatusForLevel(
			 final GrievanceDispositionLevel level);
	
	/**
	 * Creates coordinator level disposition.
	 * 
	 * @param grievance grievance
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return new coordinator level disposition
	 * @throws DuplicateEntityFoundException if disposition exists
	 */
	GrievanceDisposition createCoordinatorLevelDisposition(Grievance grievance,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, Date appealDate,
			String notes) throws DuplicateEntityFoundException;
	
	/**
	 * Updates coordinator level disposition.
	 * 
	 * @param coordinatorDisposition coordinator disposition
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param responseToOffenderDate response to offender date
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return updated coordinator level disposition
	 */
	GrievanceDisposition updateCoordinatorLevelDisposition(
			GrievanceDisposition coordinatorDisposition,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, Date appealDate,
			String notes);
	
	/**
	 * Creates warden level disposition.
	 * 
	 * @param grievance grievance
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param responseToOffenderDate response to offender date
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return new warden level disposition
	 * @throws DuplicateEntityFoundException if disposition exists
	 */
	GrievanceDisposition createWardenLevelDisposition(Grievance grievance,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, Date appealDate,
			String notes) throws DuplicateEntityFoundException;
	
	/**
	 * Updates warden level disposition.
	 * 
	 * @param wardenDisposition warden disposition
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param responseToOffenderDate response to offender date
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return updated warden level disposition
	 */
	GrievanceDisposition updateWardenLevelDisposition(
			GrievanceDisposition wardenDisposition,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, Date appealDate,
			String notes);
	
	/**
	 * Creates Facility Health Administrator (FHA) level disposition.
	 * 
	 * @param grievance grievance
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param responseToOffenderDate response to offender date
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return new Facility Health Administrator (FHA) level disposition
	 * @throws DuplicateEntityFoundException if disposition exists
	 */
	GrievanceDisposition createFhaLevelDisposition(Grievance grievance,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, Date appealDate,
			String notes) throws DuplicateEntityFoundException;
	
	/**
	 * Updates Facility Health Administrator (FHA) level disposition.
	 * 
	 * @param fhaDisposition Facility Health Administrator (FHA) disposition
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param responseToOffenderDate response to offender date
	 * @param appealDate appeal date
	 * @param notes notes
	 * @return updated Facility Health Administrator (FHA) level disposition
	 */
	GrievanceDisposition updateFhaLevelDisposition(
			GrievanceDisposition fhaDisposition,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, Date appealDate,
			String notes);
	
	/**
	 * Creates director level disposition.
	 * 
	 * @param grievance grievance
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param responseToOffenderDate response to offender date
	 * @param notes notes
	 * @return new director level disposition
	 * @throws DuplicateEntityFoundException if disposition exists
	 */
	GrievanceDisposition createDirectorLevelDisposition(Grievance grievance,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, String notes)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates director level disposition.
	 * 
	 * @param directorDisposition director disposition
	 * @param status status
	 * @param closedDate closed date
	 * @param reason reason
	 * @param receivedDate received date
	 * @param responseDueDate response due date
	 * @param responseBy account of user responding
	 * @param responseToOffenderDate response to offender date
	 * @param notes notes
	 * @return updated director level disposition
	 */
	GrievanceDisposition updateDirectorLevelDisposition(
			GrievanceDisposition directorDisposition,
			GrievanceDispositionStatus status, Date closedDate,
			GrievanceDispositionReason reason, Date receivedDate,
			Date responseDueDate, UserAccount responseBy,
			Date responseToOffenderDate, String notes);
	
	/**
	 * Adds note to grievance.
	 * 
	 * @param grievance grievance to which to add note
	 * @param date date
	 * @param value value
	 * @return added note
	 * @throws DuplicateEntityFoundException if note exists
	 */
	GrievanceNote addNote(Grievance grievance, Date date, String value)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates note.
	 * 
	 * @param note note to update
	 * @param date date
	 * @param value value
	 * @return updated note
	 * @throws DuplicateEntityFoundException if note exists
	 */
	GrievanceNote updateNote(GrievanceNote note, Date date, String value)
			throws DuplicateEntityFoundException;
	
	/**
	 * Removes note.
	 * 
	 * @param note note
	 */
	void removeNote(GrievanceNote note);
	
	/**
	 * Returns notes for grievance.
	 * 
	 * @param grievance grievance for which to find notes
	 * @return grievance notes
	 */
	List<GrievanceNote> findNotes(Grievance grievance);
	
	/**
	 * Searches and returns user accounts.
	 * 
	 * @param query query for user accounts
	 * @return user accounts
	 */
	List<UserAccount> searchUserAccounts(String query);

	/**
	 * Returns user account with username.
	 * 
	 * @param username username
	 * @return user account with username
	 */
	UserAccount findUserAccount(String username);
	
	/**
	 * Removes grievance.
	 * 
	 * <p>If dispositions are associated with grievance, removes dispositions
	 * too.
	 * 
	 * @param grievance grievance
	 */
	void remove(Grievance grievance);
	
	/**
	 * Returns response due date.
	 * 
	 * @param openedDate opened date
	 * @return response due date
	 */
	Date calculateResponseDueDate(Date openedDate);
}