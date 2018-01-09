package omis.commitstatus.service;

import java.util.List;

import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.commitstatus.exception.CommitStatusTermConflictException;
import omis.commitstatus.exception.CommitStatusTermExistsAfterException;
import omis.commitstatus.exception.CommitStatusTermExistsException;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Commit status term service.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 1, 2017)
 * @since OMIS 3.0
 */
public interface CommitStatusTermService {
	/**
	 * Create commit status term.
	 * 
	 * @param offender offender
	 * @param commitStatus commit status
	 * @param dateRange date range
	 * @return created commit status term
	 */
	CommitStatusTerm create(Offender offender, CommitStatus commitStatus,
		DateRange dateRange) throws CommitStatusTermExistsException, 
		CommitStatusTermConflictException, CommitStatusTermExistsAfterException;
	
	/**
	 * Update commit status term.
	 * 
	 * @param commitStatusTerm commit status term
	 * @param commitStatus commit status
	 * @param dateRange date range
	 * @return updated commit status term
	 */
	CommitStatusTerm update(CommitStatusTerm commitStatusTerm, 
		CommitStatus commitStatus, DateRange dateRange) 
		throws CommitStatusTermExistsException,	CommitStatusTermConflictException, 
		CommitStatusTermExistsAfterException;
	
	/**
	 * Remove commit status term.
	 * 
	 * @param commitStatusTerm commit status term
	 */
	void remove(CommitStatusTerm commitStatusTerm); 
	
	/**
	 * Find all commit statuses.
	 * 
	 */
	List<CommitStatus> findStatuses(); 
}