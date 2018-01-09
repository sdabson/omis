package omis.military.service;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryDischargeStatus;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.domain.MilitaryServiceTermNote;
import omis.offender.domain.Offender;

/**
 * Military service term service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public interface MilitaryServiceTermService {

	/**
	 * Creates a new military service term for the specified offender.
	 * 
	 * @param offender offender
	 * @param branch military branch
	 * @param dischargeStatus military discharge status
	 * @param startDate start date
	 * @param endDate end date
	 * @return newly created military service term
	 * @throws DuplicateEntityFoundException thrown when a duplicate military
	 * service term is found
	 * @throws DateConflictException thrown when a military service term's
	 * date range overlaps another service term's date range for the specified 
	 * offender
	 */
	MilitaryServiceTerm create(Offender offender, MilitaryBranch branch, 
			MilitaryDischargeStatus dischargeStatus, Date startDate,
			Date endDate) 
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates the specified military service term.
	 * 
	 * @param serviceTerm military service term
	 * @param branch military branch
	 * @param dischargeStatus military discharge status
	 * @param startDate start date
	 * @param endDate end date
	 * @return updated military service term
	 * @throws DuplicateEntityFoundException thrown when a duplicate military
	 * service term is found
	 * @throws DateConflictException thrown when a military service term's date
	 * range overlaps another service term's date range for the specified 
	 * offender
	 */
	MilitaryServiceTerm update(MilitaryServiceTerm serviceTerm, 
			MilitaryBranch branch, MilitaryDischargeStatus dischargeStatus, 
			Date startDate, Date endDate)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Removes the specified military service term.
	 * 
	 * @param serviceTerm service term
	 */
	void remove(MilitaryServiceTerm militaryServiceTerm);
	
	/**
	 * Returns all military branches.
	 * 
	 * @return list of military branches
	 */
	List<MilitaryBranch> findAllMilitaryBranches();
	
	/**
	 * Returns all discharge statuses.
	 * 
	 * @return list of military discharge statuses
	 */
	List<MilitaryDischargeStatus> findAllMilitaryDischargeStatus();
	
	/**
	 * Create a military service term note.
	 * 
	 * @param serviceTerm military service term
	 * @param date date
	 * @param note note
	 * @return newly created military service term note
	 * @throws DuplicateEntityFoundException thrown when a duplicate military
	 * service term note is found
	 */
	MilitaryServiceTermNote createNote(MilitaryServiceTerm serviceTerm, 
			Date date, String note) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified military service term note.
	 * 
	 * @param serviceTermNote military service term note
	 * @param date date
	 * @param note note
	 * @return updated military service term note
	 * @throws DuplicateEntityFoundException thrown when a duplicate military
	 * service term note is found
	 */
	MilitaryServiceTermNote updateNote(MilitaryServiceTermNote serviceTermNote,
			Date date, String note) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified military service term note.
	 * @param serviceTermNote
	 */
	void removeNote(MilitaryServiceTermNote serviceTermNote);

	/**
	 * Returns a list of military service terms for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of military service terms
	 */
	List<MilitaryServiceTerm> findByOffender(Offender offender);

	/**
	 * Returns a list of service term notes for the specified military service
	 * term.
	 * 
	 * @param serviceTerm military service term
	 * @return list of military service term notes
	 */
	List<MilitaryServiceTermNote> findServiceTermNotesByServiceTerm(
			MilitaryServiceTerm serviceTerm);
}