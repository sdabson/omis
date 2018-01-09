package omis.warrant.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;
import omis.exception.DuplicateEntityFoundException;
import omis.jail.domain.Jail;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;
import omis.warrant.domain.WarrantCauseViolation;
import omis.warrant.domain.WarrantNote;
import omis.warrant.domain.WarrantReasonCategory;

/**
 * WarrantService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantService {
	
	/**
	 * Creates a Warrant with the specified properties
	 * @param offender - Offender
	 * @param date - Date
	 * @param addressee - String
	 * @param issuedBy - Person
	 * @param bondable - Boolean
	 * @param bondRecommendation - BigDecimal
	 * @param warrantReason - WarrantReasonCategory
	 * @return Newly created Warrant
	 * @throws DuplicateEntityFoundException - When a Warrant already exists
	 * on given date for the specified offender
	 */
	public Warrant create(Offender offender, Date date,
			String addressee, Person issuedBy,
			Boolean bondable, BigDecimal bondRecommendation,
			WarrantReasonCategory warrantReason)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a Warrant with the specified properties
	 * @param warrant - Warrant to update
	 * @param date - Date
	 * @param addressee - String
	 * @param issuedBy - Person
	 * @param bondable - Boolean
	 * @param bondRecommendation - BigDecimal
	 * @param warrantReason - WarrantReasonCategory
	 * @return Updated Warrant
	 * @throws DuplicateEntityFoundException - When a Warrant already exists
	 * on given date for the specified offender
	 */
	public Warrant update(Warrant warrant, Date date,
			String addressee, Person issuedBy,
			Boolean bondable, BigDecimal bondRecommendation,
			WarrantReasonCategory warrantReason)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a Warrant
	 * @param warrant - Warrant to remove
	 */
	public void remove(Warrant warrant);
	
	/**
	 * Creates a WarrantCauseViolation with the specified properties
	 * @param warrant - Warrant
	 * @param cause - CourtCase
	 * @param condition - Condition
	 * @param description - String
	 * @return Newly created WarrantCauseViolation
	 * @throws DuplicateEntityFoundException - When a WarrantCauseViolation already
	 * exists with the given Cause for the specified Warrant
	 */
	public WarrantCauseViolation createWarrantCauseViolation(Warrant warrant,
			CourtCase cause, Condition condition, String description)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates a WarrantCauseViolation with the specified properties
	 * @param warrantCauseViolation - WarrantCauseViolation to update
	 * @param cause - CourtCase
	 * @param condition - Condition
	 * @param description - String
	 * @return Updated WarrantCauseViolation
	 * @throws DuplicateEntityFoundException - When a WarrantCauseViolation already
	 * exists with the given Cause for the specified WarrantCauseViolation's Warrant
	 */
	public WarrantCauseViolation updateWarrantCauseViolation(
			WarrantCauseViolation warrantCauseViolation, CourtCase cause,
			Condition condition, String description)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a WarrantCauseViolation
	 * @param warrantCause - WarrantCauseViolation to remove
	 */
	public void removeWarrantCauseViolation(
			WarrantCauseViolation warrantCauseViolation);
	
	/**
	 * Creates a WarrantNote with the specified properties
	 * @param warrant - Warrant
	 * @param note - String
	 * @param date - Date
	 * @return Newly created WarrantNote
	 * @throws DuplicateEntityFoundException - When a WarrantNote already exists
	 * with the given Note and Date for the specified Warrant
	 */
	public WarrantNote createWarrantNote(
			Warrant warrant, String note, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates a WarrantNote with the specified properties
	 * @param warrantNote - WarrantNote to update
	 * @param note - String
	 * @param date - Date
	 * @return Updated WarrantNote
	 * @throws DuplicateEntityFoundException - When a WarrantNote already exists
	 * with the given Note and Date for the specified Warrant
	 */
	public WarrantNote updateWarrantNote(
			WarrantNote warrantNote, String note, Date date)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a WarrantNote
	 * @param warrantNote - WarrantNote to remove
	 */
	public void removeWarrantNote(WarrantNote warrantNote);
	
	/**
	 * Creates a WarrantArrest with the specified properties
	 * @param warrant - Warrant
	 * @param date - Date
	 * @param jail - Jail
	 * @param contactByDate - Date
	 * @return Newly created WarrantArrest
	 * @throws DuplicateEntityFoundException - When a WarrantArrest already
	 * exists for the specified Warrant
	 */
	public WarrantArrest createArrest(Warrant warrant, Date date,
			Jail jail, Date contactByDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates specified WarrantArrest with the given properties
	 * @param warrantArrest - WarrantArrest to update
	 * @param date - Date
	 * @param jail - Jail
	 * @param contactByDate - Date
	 * @return Updated WarrantArrest
	 * @throws DuplicateEntityFoundException - When another WarrantArrest
	 * already exists for this WarrantArrest's Warrant
	 */
	public WarrantArrest updateArrest(WarrantArrest warrantArrest,
			Date date, Jail jail, Date contactByDate)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a WarrantArrest
	 * @param warrantArrest - WarrentArrest to remove
	 */
	public void removeArrest(WarrantArrest warrantArrest);
	
	/**
	 * Returns a list of all WarrantCauseViolations with specified Warrant
	 * @param warrant - Warrant
	 * @return List of all WarrantCauseViolations with specified Warrant
	 */
	public List<WarrantCauseViolation> findWarrantCauseViolationsByWarrant(
			Warrant warrant);
	
	/**
	 * Returns a WarrantArrest with specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantArrest with specified Warrant
	 */
	public WarrantArrest findWarrantArrestByWarrant(Warrant warrant);
	
	/**
	 * Returns a list of all WarrantNotes with specified Warrant
	 * @param warrant - Warrant
	 * @return List of all WarrantNotes with specified Warrant
	 */
	public List<WarrantNote> findWarrantNotesByWarrant(Warrant warrant);
	
	/**
	 * Returns a list of all Jails
	 * @return List of all Jails
	 */
	public List<Jail> findAllJails();
	
	/**
	 * Returns a CorrectionalStatusTerm for specified Offender on given Date
	 * @param offender - Offender
	 * @param date - Date
	 * @return CorrectionalStatusTerm for specified Offender on given Date
	 */
	public CorrectionalStatusTerm findCorrectionalStatusTermForOffenderOnDate(
			Offender offender, Date date);
	
	/**
	 * Returns a list of CourtCases for specified Offender
	 * @param offender - Offender
	 * @return List of CourtCases for specified Offender
	 */
	public List<CourtCase> findCourtCasesByDefendant(Offender offender);
	
	/**
	 * Returns a list of Conditions for specified CourtCase
	 * @param courtCase - CourtCase
	 * @return List of Conditions for specified CourtCase
	 */
	public List<Condition> findConditionsByCourtCase(CourtCase courtCase);
	
	
}
