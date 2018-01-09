package omis.paroleboardmember.service;

import java.util.Date;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;

/**
 * Service for parole board member.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface ParoleBoardMemberService {

	/**
	 * Creates a new parole board member.
	 * 
	 * @param staffAssignment staff assignment
	 * @param startDate start date
	 * @param endDate end date
	 * @return parole board member
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date range not within staff assignment 
	 * date range
	 */
	ParoleBoardMember create(StaffAssignment staffAssignment, Date startDate, 
			Date endDate) throws DuplicateEntityFoundException, 
					DateConflictException;
	
	/**
	 * Updates an existing parole board member.
	 * 
	 * @param paroleBoardMember parole board member
	 * @param startDate start date
	 * @param endDate end date
	 * @return parole board member
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if date range not within staff assignment 
	 * date range
	 */
	ParoleBoardMember update(ParoleBoardMember paroleBoardMember,
			Date startDate, Date endDate) throws DuplicateEntityFoundException, 
					DateConflictException;
	
	/**
	 * Removes an existing parole board member.
	 * 
	 * @param paroleBoardMember parole board member
	 */
	void remove(ParoleBoardMember paroleBoardMember);
}
