package omis.caseload.service.impl;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseloadContact;
import omis.caseload.domain.CaseworkCategory;
import omis.caseload.domain.ContactCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.caseload.service.CaseloadService;
import omis.caseload.service.delegate.CaseWorkerAssignmentDelegate;
import omis.caseload.service.delegate.CaseloadContactDelegate;
import omis.caseload.service.delegate.CaseloadDelegate;
import omis.caseload.service.delegate.OffenderCaseAssignmentDelegate;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/** Implementation of service for caseloads.
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2017)
 * @since OMIS 3.0 */
public class CaseloadServiceImpl implements CaseloadService {
	
	/* Delegates. */
	private final CaseloadDelegate caseloadDelegate;
	private final CaseWorkerAssignmentDelegate caseWorkerAssignmentDelegate;
	private final OffenderCaseAssignmentDelegate offenderCaseAssignmentDelegate;
	private final CaseloadContactDelegate caseloadContactDelegate;
	private final UserAccountDelegate userAccountDelegate;
	
	/** Instantiates an implementation of CaseloadServiceImpl. 
	 * @param caseloadDelegate -caseload delegate. 
	 * @param caseWorkerAssignmentDelegate - caseworker assignment delegate. 
	 * @param offenderCaseAssignmentDelegate - offender case assignment 
	 * delegate.
	 * @param caseloadContactDelegate - caseload contact delegate. 
	 * @param userAccountDelegate - user account delegate. */
	public CaseloadServiceImpl(final CaseloadDelegate caseloadDelegate,
					final CaseWorkerAssignmentDelegate 
						caseWorkerAssignmentDelegate,
					final OffenderCaseAssignmentDelegate 
						offenderCaseAssignmentDelegate,
					final CaseloadContactDelegate caseloadContactDelegate,
					final UserAccountDelegate userAccountDelegate) {
		this.caseloadDelegate = caseloadDelegate;
		this.caseWorkerAssignmentDelegate = caseWorkerAssignmentDelegate;
		this.offenderCaseAssignmentDelegate = offenderCaseAssignmentDelegate;
		this.caseloadContactDelegate = caseloadContactDelegate;
		this.userAccountDelegate = userAccountDelegate;
		
	}

	/** {@inheritDoc} */
	@Override
	public Caseload createCaseload(final String name,
					final CaseworkCategory category) 
			throws DuplicateEntityFoundException {
		return this.caseloadDelegate.createCaseload(name, category, null);
	}
	
	/** {@inheritDoc} */
	@Override
	public Caseload createCaseload(final String name, 
					final CaseworkCategory category,
					final Caseload caseload)
			throws DuplicateEntityFoundException {
		return this.caseloadDelegate.createCaseload(name, category, caseload);
	}

	/** {@inheritDoc} */
	@Override
	public Caseload updateCaseload(final Caseload caseload, final String name, 
					final CaseworkCategory category, final Caseload owner)
			throws DuplicateEntityFoundException {
		return this.caseloadDelegate.updateCaseload(caseload, name, category, 
				owner);
	}

	/** {@inheritDoc} */
	@Override
	public void removeCaseload(final Caseload caseload) {
		this.caseloadDelegate.removeCaseload(caseload);
	}

	/** {@inheritDoc} */
	@Override
	public CaseWorkerAssignment createCaseWorkerAssignment(
					final Caseload caseload, final Person staffMember,
					final Date startDate, final Date endDate)
			throws DuplicateEntityFoundException, DateConflictException {
		return this.caseWorkerAssignmentDelegate.create(caseload, staffMember, 
				startDate, endDate);
	}
	
	/** {@inheritDoc} */
	@Override
	public CaseWorkerAssignment updateCaseWorkerAssignment(
					final CaseWorkerAssignment caseWorkerAssignment, 
					final Caseload caseload, final Person staffMember, 
					final Date startDate, final Date endDate)
			throws DuplicateEntityFoundException, DateConflictException {
		return this.caseWorkerAssignmentDelegate.update(caseWorkerAssignment, 
				caseload, staffMember, startDate, endDate);
	}

	/** {@inheritDoc} */
	@Override
	public void removeCaseWorkerAssignment(
					final CaseWorkerAssignment caseWorkerAssignment) {
		this.caseWorkerAssignmentDelegate.remove(caseWorkerAssignment);
		
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment createOffenderCaseAssignment(
					final Offender offender, final Caseload caseload,
					final Date startDate, final Date endDate) 
		throws DuplicateEntityFoundException, 
		DateConflictException {
		return this.offenderCaseAssignmentDelegate.create(offender, caseload, 
				startDate, endDate);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment updateOffenderCaseAssignment(
					final OffenderCaseAssignment offenderCaseAssignment,
					final Offender offender, final Caseload caseload, 
					final Date startDate, final Date endDate)
			throws DuplicateEntityFoundException, DateConflictException {
		return this.offenderCaseAssignmentDelegate.update(
				offenderCaseAssignment, offender, caseload, startDate, 
				endDate);
	}

	/** {@inheritDoc} */
	@Override
	public void removeOffenderCaseAssignment(
					final OffenderCaseAssignment offenderCaseAssignment) {
		this.offenderCaseAssignmentDelegate.remove(offenderCaseAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public CaseloadContact createContact(
					final OffenderCaseAssignment offenderCaseAssignment, 
					final Date contactDate, final Person contactBy, 
					final ContactCategory category, final String caseNote) 
					throws DuplicateEntityFoundException {
		return this.caseloadContactDelegate.create(offenderCaseAssignment, 
				contactDate, contactBy, category, caseNote);
	}

	/** {@inheritDoc} */
	@Override
	public CaseloadContact updateContact(final CaseloadContact contact, 
					final OffenderCaseAssignment offenderCaseAssignment, 
					final Date contactDate, final Person contactBy, 
					final ContactCategory category) 
							throws DuplicateEntityFoundException {
		return this.caseloadContactDelegate.update(contact, 
				offenderCaseAssignment, contactDate, 
				contactBy, category);
	}

	/** {@inheritDoc} */
	@Override
	public void removeContact(final CaseloadContact contact) {
		this.caseloadContactDelegate.remove(contact);
	}

	/** {@inheritDoc} */
	@Override
	public List<ContactCategory> findAllContactCategories() {
		return this.caseloadContactDelegate.findAllContactCategories();
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseWorkerAssignment> findCaseWorkerAssignmentsByCaseload(
					final Caseload caseload, final Date effectiveDate) {
		return this.caseWorkerAssignmentDelegate
					.findCaseWorkerAssignmentsByCaseload(
							caseload, effectiveDate);
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaseAssignment> findOffenderCaseAssignmentsByCaseload(
					final Caseload caseload, final Date effectiveDate) {
		return this.offenderCaseAssignmentDelegate
					.findOffenderCaseAssignmentsByCaseload(caseload, 
							effectiveDate);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccountByUsername(final String username) {
		return this.userAccountDelegate.findByUsername(username);
	}

	/** {@inheritDoc} */
	@Override
	public CaseWorkerAssignment findSupervisoryCaseWorkerAssignmentBy(
					final Person caseWorker, final Date effectiveDate) {
		return this.caseWorkerAssignmentDelegate
					.findSupervisoryCaseWorkerAssignment(caseWorker, 
							effectiveDate);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment reassign(final Caseload caseload, 
					final Date reassignDate,
					final Offender offender) {
		return this.offenderCaseAssignmentDelegate.reassign(caseload, 
							reassignDate, offender);
	}

	/** {@inheritDoc} */
	@Override
	public CaseWorkerAssignment reassignOfficer(
					final CaseWorkerAssignment caseWorkerAssignment, 
					final Date reassignDate, final Person staffMember) {
		return this.caseWorkerAssignmentDelegate.reassignOfficer(
							caseWorkerAssignment, reassignDate, staffMember);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment temporaryTransfer(final Caseload from, 
					final Caseload to, final Date startDate, final Date endDate,
					final OffenderCaseAssignment offenderCaseAssignment) {
		return this.offenderCaseAssignmentDelegate.temporaryTransfer(from, to, 
							startDate, endDate, offenderCaseAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaseAssignment> 
					findOffenderCaseAssignmentsByCaseloadAndDateRange(
					final Caseload caseload,
						final Date startDate, final Date endDate) {
		return this.offenderCaseAssignmentDelegate
					.findOffenderCaseAssignmentsByCaseloadAndDateRange(
							caseload, startDate, endDate);
	}	
}