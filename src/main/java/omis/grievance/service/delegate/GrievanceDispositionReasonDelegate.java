package omis.grievance.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceDispositionReasonDao;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionReasonCategory;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance disposition reasons.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 25, 2016)
 * @since OMIS 3.0
 */
public class GrievanceDispositionReasonDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<GrievanceDispositionReason>
	grievanceDispositionReasonInstanceFactory;

	/* Data access objects. */
	
	private final GrievanceDispositionReasonDao grievanceDispositionReasonDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for grievance disposition reasons.
	 * 
	 * @param grievanceDispositionReasonInstanceFactory instance factory for
	 * grievance disposition reasons
	 * @param grievanceDispositionReasonDao data access object for grievance
	 * disposition reasons
	 */
	public GrievanceDispositionReasonDelegate(
			final InstanceFactory<GrievanceDispositionReason>
				grievanceDispositionReasonInstanceFactory,
			final GrievanceDispositionReasonDao grievanceDispositionReasonDao) {
		this.grievanceDispositionReasonInstanceFactory
			= grievanceDispositionReasonInstanceFactory;
		this.grievanceDispositionReasonDao = grievanceDispositionReasonDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns grievance disposition reasons.
	 * 
	 * @return grievance disposition reasons
	 */
	public List<GrievanceDispositionReason> findAll() {
		return this.grievanceDispositionReasonDao.findAll();
	}
	
	/**
	 * Returns grievance disposition reasons by status.
	 * 
	 * @param status status
	 * @return grievance disposition reasons by status
	 */
	public List<GrievanceDispositionReason> findByStatus(
			final GrievanceDispositionStatus status) {
		return this.grievanceDispositionReasonDao.findByStatus(status);
	}
	
	/**
	 * Creates grievance disposition reason.
	 * 
	 * @param category category
	 * @param name name
	 * @param valid whether valid
	 * @return created grievance disposition reason
	 * @throws DuplicateEntityFoundException if grievance disposition reason
	 * exists
	 */
	public GrievanceDispositionReason create(
			final GrievanceDispositionReasonCategory category,
			final String name, final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.grievanceDispositionReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Grievance disposition reason exists");
		}
		GrievanceDispositionReason reason
			= this.grievanceDispositionReasonInstanceFactory.createInstance();
		reason.setName(name);
		reason.setCategory(category);
		reason.setValid(valid);
		return this.grievanceDispositionReasonDao.makePersistent(reason);
	}
}