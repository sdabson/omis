package omis.grievance.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceSubjectDao;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceSubject;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance subjects.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 25, 2016)
 * @since OMIS 3.0
 */
public class GrievanceSubjectDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<GrievanceSubject>
	grievanceSubjectInstanceFactory;

	/* Data access objects. */
	
	private final GrievanceSubjectDao grievanceSubjectDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for grievance subjects.
	 * 
	 * @param grievanceSubjectInstanceFactory instance factory for grievance
	 * subjects
	 * @param grievanceSubjectDao data access objects for grievance subjects
	 */
	public GrievanceSubjectDelegate(
			final InstanceFactory<GrievanceSubject>
				grievanceSubjectInstanceFactory,
			final GrievanceSubjectDao grievanceSubjectDao) {
		this.grievanceSubjectInstanceFactory = grievanceSubjectInstanceFactory;
		this.grievanceSubjectDao = grievanceSubjectDao;
	}
	
	/* Methods. */
	
	/**
	 * Creates grievance subject.
	 * 
	 * @param name name
	 * @param medical whether medical
	 * @param valid whether valid
	 * @param startLevel level at which to start
	 * @return new grievance subject
	 * @throws DuplicateEntityFoundException if grievance subject exists
	 */
	public GrievanceSubject create(
			final String name, final Boolean medical, final Boolean valid,
			final GrievanceDispositionLevel startLevel)
					throws DuplicateEntityFoundException {
		if (this.grievanceSubjectDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Grievance subject exists");
		}
		GrievanceSubject subject = this.grievanceSubjectInstanceFactory
				.createInstance();
		subject.setName(name);
		subject.setMedical(medical);
		subject.setValid(valid);
		subject.setStartLevel(startLevel);
		return this.grievanceSubjectDao.makePersistent(subject);
	}
	
	/**
	 * Returns grievance subjects.
	 * 
	 * @return grievance subjects
	 */
	public List<GrievanceSubject> findAll() {
		return this.grievanceSubjectDao.findAll();
	}
}