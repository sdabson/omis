package omis.grievance.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceDispositionStatusDao;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance disposition statuses.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 25, 2016)
 * @since OMIS 3.0
 */
public class GrievanceDispositionStatusDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<GrievanceDispositionStatus>
	grievanceDispositionStatusInstanceFactory;
	
	/* Data access objects. */
	
	private final GrievanceDispositionStatusDao grievanceDispositionStatusDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for grievance disposition statuses.
	 * 
	 * @param grievanceDispositionStatusDao data access object for grievance
	 * disposition statuses
	 */
	public GrievanceDispositionStatusDelegate(
			final InstanceFactory<GrievanceDispositionStatus>
				grievanceDispositionStatusInstanceFactory,
			final GrievanceDispositionStatusDao grievanceDispositionStatusDao) {
		this.grievanceDispositionStatusInstanceFactory
			= grievanceDispositionStatusInstanceFactory;
		this.grievanceDispositionStatusDao = grievanceDispositionStatusDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns grievance disposition statuses by levels.
	 * 
	 * @param levels levels
	 * @return grievance disposition statuses by levels
	 */
	public List<GrievanceDispositionStatus> findByLevels(
			final GrievanceDispositionLevel... levels) {
		return this.grievanceDispositionStatusDao.findByLevels(levels);
	}
	
	/**
	 * Returns grievance disposition statuses by levels and whether closed.
	 * 
	 * @param closed whether closed
	 * @param levels levels
	 * @return grievance disposition statuses by levels and whether closed
	 */
	public List<GrievanceDispositionStatus> findByLevelsAndWhetherClosed(
			final boolean closed, 
			final GrievanceDispositionLevel... levels) {
		return this.grievanceDispositionStatusDao
				.findByLevelsAndWhetherClosed(closed, levels);
	}
	
	/**
	 * Creates grievance disposition status.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid whether valid
	 * @param closed whether disposition with status is closed
	 * @param pending whether disposition with status is pending
	 * @param level level
	 * @return new grievance disposition status
	 * @throws DuplicateEntityFoundException if grievance disposition status
	 * exists
	 */
	public GrievanceDispositionStatus create(
			final String name, final Short sortOrder, final Boolean valid,
			final Boolean closed, final Boolean pending,
			final GrievanceDispositionLevel level)
				throws DuplicateEntityFoundException {
		if (this.grievanceDispositionStatusDao.find(name, level) != null) {
			throw new DuplicateEntityFoundException(
					"Grievance disposition status exists");
		}
		GrievanceDispositionStatus status
			= this.grievanceDispositionStatusInstanceFactory.createInstance();
		status.setName(name);
		status.setSortOrder(sortOrder);
		status.setValid(valid);
		status.setClosed(closed);
		status.setLevel(level);
		status.setPending(pending);
		return this.grievanceDispositionStatusDao.makePersistent(status);
	}
	
	/**
	 * Returns pending grievance disposition status by level.
	 * 
	 * @param level level
	 * @return pending grievance disposition status by level
	 */
	public GrievanceDispositionStatus findPendingForLevel(
			final GrievanceDispositionLevel level) {
		return this.grievanceDispositionStatusDao.findPendingForLevel(level);
	}
}