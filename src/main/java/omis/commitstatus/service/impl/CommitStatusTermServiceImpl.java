package omis.commitstatus.service.impl;

import java.util.List;

import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.commitstatus.exception.CommitStatusTermConflictException;
import omis.commitstatus.exception.CommitStatusTermExistsAfterException;
import omis.commitstatus.exception.CommitStatusTermExistsException;
import omis.commitstatus.service.CommitStatusTermService;
import omis.commitstatus.service.delegate.CommitStatusDelegate;
import omis.commitstatus.service.delegate.CommitStatusTermDelegate;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Implementation of services for commit status term.
 * @author Yidong Li
 * @version 0.1.1 (June 2, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusTermServiceImpl implements CommitStatusTermService {
	private final CommitStatusTermDelegate commitStatusTermDelegate;
	private final CommitStatusDelegate commitStatusDelegate;
		
	/**
	 * Instantiates an instance of family association service with the
	 * specified data access objects and component retrievers.
	 * 
	 * @param commitStatusTermDelegate commit status term delegate
	 * @param commitStatusDelegate commit status delegate
	 * 
	 */
	public CommitStatusTermServiceImpl(
		final CommitStatusTermDelegate commitStatusTermDelegate,
		final CommitStatusDelegate commitStatusDelegate) {
		this.commitStatusTermDelegate = commitStatusTermDelegate;
		this.commitStatusDelegate = commitStatusDelegate;
	}
	
	/**{@inheritDoc} */
	@Override
	public CommitStatusTerm create(final Offender offender, 
		final CommitStatus status, final DateRange dateRange)
		throws CommitStatusTermExistsException, CommitStatusTermConflictException, 
		CommitStatusTermExistsAfterException {
		return this.commitStatusTermDelegate.create(offender, status, dateRange);
	}
	
	/**{@inheritDoc} */
	@Override
	public CommitStatusTerm update(final CommitStatusTerm term, 
		final CommitStatus status, final DateRange dateRange)
		throws CommitStatusTermExistsException, CommitStatusTermConflictException, 
		CommitStatusTermExistsAfterException {
		return this.commitStatusTermDelegate.update(term, status, dateRange);
	}
	
	/**{@inheritDoc} */
	@Override
	public void remove(final CommitStatusTerm term) {
		this.commitStatusTermDelegate.remove(term);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<CommitStatus> findStatuses(){
		return this.commitStatusDelegate.findAll();
	}
}