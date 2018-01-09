package omis.commitstatus.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.commitstatus.dao.CommitStatusTermDao;
import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.commitstatus.exception.CommitStatusTermConflictException;
import omis.commitstatus.exception.CommitStatusTermExistsAfterException;
import omis.commitstatus.exception.CommitStatusTermExistsException;
import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Commit status term delegate.
 * 
 * @author Yidong Li
 * @version 0.1.1 (June 1, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusTermDelegate {

	/* Data access objects. */
	private final CommitStatusTermDao commitStatusTermDao;
	
	/* Instance factories. */
	private final InstanceFactory<CommitStatusTerm> commitStatusTermInstanceFactory;
	
	/* Component retrievers. */
	private final AuditComponentRetriever auditComponentRetriever;

	/* Constructor. */

	/**
	 * Instantiates a commit status term delegate with
	 * the specified data access object.
	 * 
	 * @param commitStatusTermDao commit status term data access object
	 * @param commitStatusTermInstanceFactory commitStatusTermInstanceFactory
	 * @param auditComponentRetriever audit component retriever
	 */
	public CommitStatusTermDelegate(
		final CommitStatusTermDao commitStatusTermDao,
		final InstanceFactory<CommitStatusTerm> commitStatusTermInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever) 
	{
		this.commitStatusTermDao = commitStatusTermDao;
		this.commitStatusTermInstanceFactory = commitStatusTermInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Management methods. */
	
	/**
	 * Create commit status term.
	 * 
	 * @param offender offender
	 * @param status commit status
	 * @param dateRange date range
	 * @return created commit status term
	 */
	public CommitStatusTerm create(final Offender offender, 
		final CommitStatus status, final DateRange dateRange) 
		throws CommitStatusTermExistsException,CommitStatusTermConflictException, 
			CommitStatusTermExistsAfterException{
			if(this.commitStatusTermDao.findExists(offender, status, 
				dateRange.getStartDate())!=null){
				throw new CommitStatusTermExistsException("Duplicate commit "
					+ "status term found");
			}
			if(this.commitStatusTermDao.findExistsAfter(offender,  
					dateRange).size()!=0){
					throw new CommitStatusTermExistsAfterException("Commit "
							+ "status term existing after the start date of new "
							+ "term found");
				}
			if(this.commitStatusTermDao.findConflict(offender, dateRange)
				.size()!=0){
				throw new CommitStatusTermConflictException("Conflict commit "
						+ "status term found");
			}
			
			CommitStatusTerm term = this.commitStatusTermInstanceFactory
				.createInstance();
			term.setCreationSignature(new CreationSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			term.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(), 
					this.auditComponentRetriever.retrieveDate()));
			term.setDateRange(dateRange);
			term.setStatus(status); 
			term.setOffender(offender);
			
			CommitStatusTerm autoEndingTerm = this.commitStatusTermDao
				.findForOffenderOnDate(offender, dateRange.getStartDate());
			
			if(autoEndingTerm!=null){
				if(autoEndingTerm.getDateRange() != null 
					&& autoEndingTerm.getDateRange().getEndDate() == null){
					DateRange updatedDateRange = new DateRange(
						autoEndingTerm.getDateRange().getStartDate(),
						dateRange.getStartDate());
					autoEndingTerm.setDateRange(updatedDateRange);
					this.commitStatusTermDao.makePersistent(autoEndingTerm);
				}
				else{
					throw new CommitStatusTermConflictException("Conflict commit "
					+ "status term found");
				}
			}
			return this.commitStatusTermDao.makePersistent(term);
	}
	
	/**
	 * Update commit status term.
	 * 
	 * @param commitStatusTerm commit status term
	 * @param status commit status
	 * @param dateRange date range
	 * @return updated commit status term
	 */
	public CommitStatusTerm update(final CommitStatusTerm commitStatusTerm, 
		final CommitStatus status, final DateRange dateRange) 
		throws CommitStatusTermExistsException,CommitStatusTermConflictException, 
			CommitStatusTermExistsAfterException{
			if(this.commitStatusTermDao.findExcluding(commitStatusTerm,  
				status, dateRange, commitStatusTerm.getOffender())!=null){
				throw new CommitStatusTermExistsException("Commit status term "
						+ "already exists");
			}
			if(this.commitStatusTermDao.findExistsAfterExcluding(commitStatusTerm,
				commitStatusTerm.getOffender(), dateRange).size()!=0){
				throw new CommitStatusTermExistsAfterException("Overlapped "
						+ "commit status term found");
				}
			if(this.commitStatusTermDao.findConflictExcluding(commitStatusTerm
				.getOffender(),	dateRange, commitStatusTerm).size()!=0){
				throw new CommitStatusTermConflictException("Conflict commit "
					+ "status term found");
			}
			commitStatusTerm.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
			commitStatusTerm.setDateRange(dateRange);
			commitStatusTerm.setStatus(status);
			return this.commitStatusTermDao.makePersistent(commitStatusTerm);
	}
	
	/**
	 * Removes the specified commit status term.
	 * 
	 * @param commitStatusTerm commit status term
	 */
	public void remove(final CommitStatusTerm commitStatusTerm) {
		this.commitStatusTermDao.makeTransient(commitStatusTerm);
	}	
	
	/**
	 * Find existing commit status term .
	 * 
	 * @param offender offender
	 * @param status commit status
	 * @param startDate start date
	 * @return commit status
	 */
	public CommitStatusTerm findExists(final Offender offender, final CommitStatus status,
		final Date startDate) {
		return this.commitStatusTermDao.findExists(offender, status, startDate);
	}	
}