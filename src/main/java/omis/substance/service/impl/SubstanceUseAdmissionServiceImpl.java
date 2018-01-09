package omis.substance.service.impl;

import java.util.List;

import omis.substance.dao.SubstanceUseAdmissionDao;
import omis.substance.domain.SubstanceUseAdmission;
import omis.substance.service.SubstanceUseAdmissionService;

/**
 * Substance use admission service impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 25, 2013)
 * @since OMIS 3.0
 */
public class SubstanceUseAdmissionServiceImpl 
	implements SubstanceUseAdmissionService {

	private SubstanceUseAdmissionDao substanceUseAdmissionDao;
	
	/**
	 * Instantiates a substance use admission service implementation with the
	 * specified data access object.
	 * 
	 * @param substanceUseAdmissionDao substance use admission DAO
	 */
	public SubstanceUseAdmissionServiceImpl(
			final SubstanceUseAdmissionDao substanceUseAdmissionDao) {
		this.substanceUseAdmissionDao = substanceUseAdmissionDao;
	}

	/** {@inheritDoc} */
	@Override
	public SubstanceUseAdmission save(
			final SubstanceUseAdmission substanceUseAdmission) {
		return this.substanceUseAdmissionDao.makePersistent(
				substanceUseAdmission);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final SubstanceUseAdmission substanceUseAdmission) {
		this.substanceUseAdmissionDao.makeTransient(substanceUseAdmission);
	}

	/** {@inheritDoc} */
	@Override
	public List<SubstanceUseAdmission> findAll() {
		return this.substanceUseAdmissionDao.findAll();
	}
}