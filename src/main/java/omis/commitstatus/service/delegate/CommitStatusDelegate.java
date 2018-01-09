package omis.commitstatus.service.delegate;

import java.util.List;

import omis.commitstatus.dao.CommitStatusDao;
import omis.commitstatus.domain.CommitStatus;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Commit status delegate.
 * 
 * @author Yidong Li
 * @version 0.1.1 (June 1, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusDelegate {

	/* Data access objects. */
	private final CommitStatusDao commitStatusDao;
	
	/* Instance factories. */
	private final InstanceFactory<CommitStatus> commitStatusInstanceFactory;

	/* Constructor. */

	/**
	 * Instantiates a commit status delegate with
	 * the specified data access object.
	 * 
	 * @param commitStatusDao commit status data access object
	 * @param commitStatusInstanceFactory commit status instance factory
	 */
	public CommitStatusDelegate(
			final CommitStatusDao commitStatusDao,
			final InstanceFactory<CommitStatus> commitStatusInstanceFactory) {
		this.commitStatusDao = commitStatusDao;
		this.commitStatusInstanceFactory = commitStatusInstanceFactory;
	}
	
	/* Management methods. */
	
	/**
	 * Find all commit statuses.
	 * 
	 * @return created family association
	 */
	public List<CommitStatus> findAll() {
		return this.commitStatusDao.findAll();
	}
	
	/**
	 * Create commit status.
	 * 
	 * @param name status name
	 * @param valid valid
	 * @return created commit status
	 */
	public CommitStatus create(final String name, 
		final Boolean valid) throws DuplicateEntityFoundException{
		if(this.commitStatusDao.findStatus(name)!=null){
			throw new DuplicateEntityFoundException("Commit status already exist");
		}
		
		CommitStatus status = this.commitStatusInstanceFactory.createInstance();
		status.setName(name);
		status.setValid(valid);
		return this.commitStatusDao.makePersistent(status);
	}
}