package omis.substance.service.impl;

import java.util.List;

import omis.substance.dao.SubstanceDao;
import omis.substance.domain.Substance;
import omis.substance.service.SubstanceService;

/**
 * Substance Service Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 19, 2013)
 * @since OMIS 3.0
 */
public class SubstanceServiceImpl implements SubstanceService {

	private SubstanceDao substanceDao;
	
	/**
	 * Instantiates a substance service implementation with the specified
	 * data access object.
	 * 
	 * @param substanceDao substance DAO
	 */
	public SubstanceServiceImpl(final SubstanceDao substanceDao) {
		this.substanceDao = substanceDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public Substance findById(final Long id) {
		return this.substanceDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public Substance save(final Substance substance) {
		return this.substanceDao.makePersistent(substance);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Substance substance) {
		this.substanceDao.makeTransient(substance);
	}

	/** {@inheritDoc} */
	@Override
	public List<Substance> findAll() {
		return this.substanceDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Substance> findValid() {
		return this.substanceDao.findValid();
	}
}