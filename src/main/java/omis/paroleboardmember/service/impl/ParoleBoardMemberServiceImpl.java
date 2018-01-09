package omis.paroleboardmember.service.impl;

import java.util.Date;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.paroleboardmember.service.ParoleBoardMemberService;
import omis.paroleboardmember.service.delegate.ParoleBoardMemberDelegate;
import omis.staff.domain.StaffAssignment;

/**
 * Implementation of service for parole board member.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberServiceImpl implements ParoleBoardMemberService {

	/* Delegates. */
	
	private final ParoleBoardMemberDelegate paroleBoardMemberDelegate;
	
	/**
	 * Instantiates a parole board member service implementation with the 
	 * specified delegates.
	 * 
	 * @param paroleBoardMemberDelegate parole board member delegate	
	 */
	public ParoleBoardMemberServiceImpl(
			final ParoleBoardMemberDelegate paroleBoardMemberDelegate) {
		this.paroleBoardMemberDelegate = paroleBoardMemberDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public ParoleBoardMember create(final StaffAssignment staffAssignment, 
			final Date startDate, final Date endDate) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		DateRange dateRange = new DateRange(startDate, endDate);
		return this.paroleBoardMemberDelegate.create(staffAssignment, 
				dateRange);
	}

	/** {@inheritDoc} */
	@Override
	public ParoleBoardMember update(final ParoleBoardMember paroleBoardMember, 
			final Date startDate, final Date endDate) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		DateRange dateRange = new DateRange(startDate, endDate);
		return this.paroleBoardMemberDelegate.update(paroleBoardMember, 
				paroleBoardMember.getStaffAssignment(), dateRange);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(ParoleBoardMember paroleBoardMember) {
		this.paroleBoardMemberDelegate.remove(paroleBoardMember);
	}

}
