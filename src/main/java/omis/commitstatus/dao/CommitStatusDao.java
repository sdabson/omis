package omis.commitstatus.dao;

import java.util.List;

import omis.commitstatus.domain.CommitStatus;
import omis.dao.GenericDao;

/**
 * Data access object for commit status.
 * @author Yidong Li
 * @version 0.1.0 (May 31, 2017)
 * @since OMIS 3.0
 */
public interface CommitStatusDao
	extends GenericDao<CommitStatus> {
	/**
	 * Find all commit statuses.
	 * @return list of commit statuses
	 */
	List<CommitStatus> findStatuses();
	
	/**
	 * Find existing commit status.
	 * @param String name
	 * @return commit status
	 */
	CommitStatus findStatus(String name);
}