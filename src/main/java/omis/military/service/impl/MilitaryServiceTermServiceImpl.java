package omis.military.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryDischargeStatus;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.domain.MilitaryServiceTermNote;
import omis.military.service.MilitaryServiceTermService;
import omis.military.service.delegate.MilitaryBranchDelegate;
import omis.military.service.delegate.MilitaryDischargeStatusDelegate;
import omis.military.service.delegate.MilitaryServiceTermDelegate;
import omis.military.service.delegate.MilitaryServiceTermNoteDelegate;
import omis.offender.domain.Offender;

/**
 * Military service term service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermServiceImpl 
implements MilitaryServiceTermService{

	/* Data access objects. */
	
	private MilitaryServiceTermDelegate militaryServiceTermDelegate;
	
	private MilitaryBranchDelegate militaryBranchDelegate;	
	
	private MilitaryDischargeStatusDelegate militaryDischargeStatusDelegate;
	
	private MilitaryServiceTermNoteDelegate militaryServiceTermNoteDelegate;

	
	/**
	 * Instantiates a military service term service with the specified data
	 * access objects, instance factories, and component retrievers.
	 * 
	 * @param militaryServiceTermDao military service term data access object
	 * @param militaryBranchDao military branch data access object
	 * @param militaryDischargeStatusDao military discharge status data access
	 * object
	 * @param militaryServiceTermNoteDao military service term note data
	 * access object
	 * @param militaryServiceTermInstanceFactory military service term instance
	 * factory
	 * @param militaryServiceTermNoteInstanceFactory military service term note
	 * instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public MilitaryServiceTermServiceImpl(
			final MilitaryServiceTermDelegate militaryServiceTermDelegate,
			final MilitaryBranchDelegate militaryBranchDelegate,
			final MilitaryDischargeStatusDelegate militaryDischargeStatusDelegate,
			final MilitaryServiceTermNoteDelegate militaryServiceTermNoteDelegate) 
	{
		this.militaryServiceTermDelegate = militaryServiceTermDelegate;
		this.militaryBranchDelegate = militaryBranchDelegate;
		this.militaryDischargeStatusDelegate = militaryDischargeStatusDelegate;
		this.militaryServiceTermNoteDelegate = militaryServiceTermNoteDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTerm create(final Offender offender, 
			final MilitaryBranch branch,
			final MilitaryDischargeStatus dischargeStatus, 
			final Date startDate, final Date endDate) 
		throws DuplicateEntityFoundException, DateConflictException {
	return this.militaryServiceTermDelegate.create(offender, branch, 
			dischargeStatus, startDate, endDate);
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTerm update(final MilitaryServiceTerm serviceTerm,
			final MilitaryBranch branch,
			final MilitaryDischargeStatus dischargeStatus,
			final Date startDate, final Date endDate) 
		throws DuplicateEntityFoundException, DateConflictException {
		return this.militaryServiceTermDelegate.update(serviceTerm, branch,
				dischargeStatus, startDate, endDate);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final MilitaryServiceTerm serviceTerm) {
		this.militaryServiceTermDelegate.remove(serviceTerm);
		
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryBranch> findAllMilitaryBranches() {
		return this.militaryBranchDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryDischargeStatus> findAllMilitaryDischargeStatus() {
		return this.militaryDischargeStatusDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTermNote createNote(
			final MilitaryServiceTerm serviceTerm, final Date date, 
			final String note)
			throws DuplicateEntityFoundException {
			return (MilitaryServiceTermNote) this
					.militaryServiceTermNoteDelegate.create(serviceTerm, date, 
							note);
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTermNote updateNote(
			MilitaryServiceTermNote serviceTermNote, Date date, String note)
		throws DuplicateEntityFoundException {
		return this.militaryServiceTermNoteDelegate.update(serviceTermNote, 
				date, note);
	}

	/** {@inheritDoc} */
	@Override
	public void removeNote(final MilitaryServiceTermNote serviceTermNote) {
		this.militaryServiceTermNoteDelegate.remove(serviceTermNote);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTerm> findByOffender(final Offender offender) {
		return this.militaryServiceTermDelegate.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTermNote> findServiceTermNotesByServiceTerm(
			final MilitaryServiceTerm serviceTerm) {
		return this.militaryServiceTermNoteDelegate.findByServiceTerm(serviceTerm);
	}
	
}