package omis.paroleboardmember.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;

/**
 * Data access object for parole board member.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface ParoleBoardMemberDao extends GenericDao<ParoleBoardMember> {

	/**
	 * Returns the parole board member matching the specified staff assignment 
	 * and start date.
	 * 
	 * @param staffAssignment staff assignment
	 * @param startDate start date
	 * @return parole board member
	 */
	ParoleBoardMember find(StaffAssignment staffAssignment, Date startDate);
	
	/**
	 * Returns the parole board member matching the specified staff assignment 
	 * and start date, excluding the specified parole board member.
	 * 
	 * @param staffAssignment staff assignment
	 * @param startDate start date
	 * @param excludedParoleBoardMember parole board member
	 * @return parole board member
	 */
	ParoleBoardMember findExcluding(StaffAssignment staffAssignment, 
			Date startDate, ParoleBoardMember excludedParoleBoardMember);
	
	/**
	 * Returns a list of parole board members on the effective date.
	 * 
	 * @param effectiveDate effective date
	 * @return list of parole board members
	 */
	List<ParoleBoardMember> findBoardMembersByDate(Date effectiveDate);
}
