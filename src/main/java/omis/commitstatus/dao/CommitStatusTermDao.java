package omis.commitstatus.dao;

import java.util.Date;
import java.util.List;

import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Data access object for commit status term.
 * @author Yidong Li
 * @version 0.1.0 (June 1, 2017)
 * @since OMIS 3.0
 */
public interface CommitStatusTermDao
	extends GenericDao<CommitStatusTerm> {
	/**
	 * find existing commit status term.
	 * @param offender offender
	 * @param status commit status
	 * @param startDate start date
	 * @return commit status term 
	 */
	CommitStatusTerm findExists(Offender offender, CommitStatus status, 
		Date startDate);
	
	/**
	 * find commit status term.
	 * @param offender offender
	 * @param dateRange date range
	 * @param term commit status term
	 * @return list of commit status terms 
	 */
	List<CommitStatusTerm> findConflictExcluding(Offender offender, 
		DateRange dateRange, CommitStatusTerm term);
	
	/**
	 * find commit status term.
	 * @param offender offender
	 * @param dateRange date range
	 * @return list of commit status terms 
	 */
	List<CommitStatusTerm> findConflict(Offender offender, DateRange dateRange);
	
	/**
	 * find commit status term.
	 * @param offender offender
	 * @param dateRange date range
	 * @return list of commit status terms 
	 */
	List<CommitStatusTerm> findExistsAfter(Offender offender, DateRange dateRange);
	
	/**
	 * find commit status term.
	 * @param offender offender
	 * @param dateRange date range
	 * @param commitStatusTerm commitStatusTerm
	 * @return list of commit status terms 
	 */
	List<CommitStatusTerm> findExistsAfterExcluding(
		CommitStatusTerm commitStatusTerm, Offender offender, DateRange dateRange);
	
	/**
	 * Returns the commit status term, excluding
	 * the specified family association.
	 * 
	 * @param commitStatusTerm commit status term
	 * @param dateRange date range
	 * @param offender offender
	 * @param status commit status
	 * @return commit status term
	 */
	CommitStatusTerm findExcluding(CommitStatusTerm commitStatusTerm, 
		CommitStatus status, DateRange dateRange, Offender offender);
	
	/**
	 * Returns the existing term whose end date is null and the start date 
	 * of the new term is later than the that of the existing one 
	 * 
	 * 
	 * @param commitStatusTerm commit status term
	 * @param effectiveDate effective date
	 * @param offender offender
	 * @return commit status term
	 */
	CommitStatusTerm findForOffenderOnDate(Offender offender, Date effectiveDate);
}