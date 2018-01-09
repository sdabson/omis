package omis.caseload.service;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseloadContact;
import omis.caseload.domain.CaseworkCategory;
import omis.caseload.domain.ContactCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;

/**
 * Caseload service.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.0 (May 17, 2017)
 * @since OMIS 3.0
 */
public interface CaseloadService {

	
	/** Create new caseload.
	 * @param name name.
	 * @param category - category.
	 * @return caseload.
	 * @throws DuplicateEntityFoundException - when duplicate caseload 
	 * found. */
	Caseload createCaseload(String name, CaseworkCategory category) 
				throws DuplicateEntityFoundException;
	
	/**
	 * Creates a new caseload.
	 *
	 * @param name name
	 * @param category category
	 * @param owner - caseload owner.
	 * @return caseload.
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	Caseload createCaseload(String name, CaseworkCategory category, 
					Caseload owner) throws DuplicateEntityFoundException;
	
	/** Updates an existing caseload.
	 * @param caseload - caseload to update.
	 * @param name - caseload name.
	 * @param category - caseload category.
	 * @param owner - caseload 
	 * @return updated caseload
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	Caseload updateCaseload(Caseload caseload, String name, 
					CaseworkCategory category, Caseload owner) 
							throws DuplicateEntityFoundException;
	
	/**
	 * Edits an existing caseload.
	 *
	 * @param name name
	 * @param category category
	 * @param caseload caseload
	 * @return updated caseload
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	/*Caseload editCaseload(String name, CaseWorkCategory category, 
			Caseload caseload) throws DuplicateEntityFoundException;*/
	
	/**
	 * Removes a caseload.
	 *
	 * @param caseload caseload
	 */
	void removeCaseload(Caseload caseload);
	
	/**
	 * Creates a new case worker assignment.
	 *
	 * @param caseload caseload
	 * @param staffMember staff member
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return case worker assignment
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DateConflictException date conflict exception
	 */
	CaseWorkerAssignment createCaseWorkerAssignment(Caseload caseload, 
					Person staffMember, Date startDate, Date endDate)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates an existing case worker assignment.
	 *
	 * @param caseWorkerAssignment case worker assignment
	 * @param caseload caseload
	 * @param staffMember staff member
	 * @param startDate - start date
	 * @param endDate - end date.
	 * @return new case worker assignment
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DateConflictException date conflict exception
	 */
	CaseWorkerAssignment updateCaseWorkerAssignment(
					CaseWorkerAssignment caseWorkerAssignment, 
					Caseload caseload, Person staffMember, 
					Date startDate, Date endDate)
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Removes a case worker assignment.
	 *
	 * @param caseWorkerAssignment case worker assignment
	 */
	void removeCaseWorkerAssignment(CaseWorkerAssignment caseWorkerAssignment);
	
	/**
	 * Creates a new offender case assignment.
	 *
	 * @param offender offender
	 * @param caseload caseload
	 * @param startDate - start date
	 * @param endDate - end date.
	 * @return offender case assignment
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DateConflictException date conflict exception
	 */
	OffenderCaseAssignment createOffenderCaseAssignment(Offender offender, 
					Caseload caseload, Date startDate, Date endDate) 
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates an existing offender case assignment.
	 *
	 * @param offenderCaseAssignment offender case assignment
	 * @param offender offender
	 * @param caseload - caseload.
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return updated offender case assignment
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws DateConflictException date conflict exception
	 */
	OffenderCaseAssignment updateOffenderCaseAssignment(
					OffenderCaseAssignment offenderCaseAssignment, 
					Offender offender, Caseload caseload, Date startDate, 
					Date endDate) 
		throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Removes an offender case assignment.
	 *
	 * @param offenderCaseAssignment offender case assignment
	 */
	void removeOffenderCaseAssignment(
					OffenderCaseAssignment offenderCaseAssignment);
	
	/**
	 * Creates a new contact.
	 *
	 * @param offenderCaseAssignment offender case assignment
	 * @param contactDate - contact date.
	 * @param contactBy contact by.
	 * @param category - contact category.
	 * @param caseNote - case note.
	 * @return new contact
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	CaseloadContact createContact(
					OffenderCaseAssignment offenderCaseAssignment, 
					Date contactDate, Person contactBy, 
					ContactCategory category, String caseNote)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing contact.
	 *
	 * @param contact contact
	 * @param offenderCaseAssignment - offender case assignment.
	 * @param date date
	 * @param contactBy contact by
	 * @param category category
	 * @return updated contact
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	CaseloadContact updateContact(CaseloadContact contact, 
					OffenderCaseAssignment offenderCaseAssignment,
					Date date, Person contactBy, ContactCategory category) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a contact.
	 *
	 * @param contact contact
	 */
	void removeContact(CaseloadContact contact);
	
	/**
	 * Finds a list of contact categories.
	 *
	 * @return list of contact categories
	 */
	List<ContactCategory> findAllContactCategories();
	
	/**
	 * Finds a list of offender case assignments by caseload.
	 *
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of offender case assignments
	 */
	List<OffenderCaseAssignment> 
					findOffenderCaseAssignmentsByCaseload(
							Caseload caseload, Date effectiveDate);
	
	/**
	 * Finds user account by user name.
	 * @param username - user name
	 * @return user account name
	 */
	UserAccount findUserAccountByUsername(String username);

	/**
	 * Finds supervisory case worker assignment by person and date.
	 *
	 * @param caseWorker case worker
	 * @param effectiveDate effective
	 * @return case worker assignment
	 */
	CaseWorkerAssignment findSupervisoryCaseWorkerAssignmentBy(
					Person caseWorker, Date effectiveDate);
	
	/**
	 * 
	 * Finds list of offender case assignments by caseload and date range.
	 *
	 *
	 * @param caseload caseload
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of offender case assignments
	 */
	List<OffenderCaseAssignment> 
					findOffenderCaseAssignmentsByCaseloadAndDateRange(
							Caseload caseload, Date startDate, Date endDate);
	
	/**
	 * Reassigns offender case assignment.
	 *
	 *
	 * @param caseload caseload
	 * @param reassignDate reassign date
	 * @param offender offender.
	 * @return offender case assignment
	 */
	OffenderCaseAssignment reassign(Caseload caseload, Date reassignDate, 
					Offender offender);
	
	/**
	 * Reassigns officer case worker assignment.
	 *
	 *
	 * @param caseWorkerAssignment case worker assignment
	 * @param reassignDate reassign date
	 * @param staffMember staff member
	 * @return case worker assignment
	 */
	CaseWorkerAssignment reassignOfficer(
					CaseWorkerAssignment caseWorkerAssignment, 
					Date reassignDate, 
					Person staffMember);
	
	/**
	 * Temporary transfer offender case assignment.
	 *
	 *
	 * @param from from
	 * @param to to
	 * @param startDate start date
	 * @param endDate end date
	 * @param offenderCaseAssignment offender case assignment
	 * @return offender case assignment
	 */
	OffenderCaseAssignment temporaryTransfer(Caseload from, Caseload to, 
					Date startDate, Date endDate, 
					OffenderCaseAssignment offenderCaseAssignment);
	
	/**
	 * Finds a list of case worker assignments by caseload.
	 *
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of case worker assignments
	 */
	List<CaseWorkerAssignment> findCaseWorkerAssignmentsByCaseload(
					Caseload caseload, Date effectiveDate);
}