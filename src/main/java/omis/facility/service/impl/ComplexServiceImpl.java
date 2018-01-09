/**
 * 
 */
package omis.facility.service.impl;

import java.util.List;

import omis.facility.dao.ComplexDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.service.ComplexService;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public class ComplexServiceImpl implements ComplexService {

	private ComplexDao complexDao;
	
	/**
	 * Instantiates a complex service implementation with the specified data
	 * access object.
	 * 
	 * @param complexDao complex DAO
	 */
	public ComplexServiceImpl(final ComplexDao complexDao) {
		this.complexDao = complexDao;
	}

	/** {@inheritDoc} */
	@Override
	public Complex findById(final Long id) {
		return this.complexDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public Complex save(final Complex complex) {
		return this.complexDao.makePersistent(complex);
	}

	/** {@inheritDoc} */
	@Override
	public List<Complex> findByFacility(final Facility facility) {
		return this.complexDao.findByFacility(facility);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Complex complex) {
		this.complexDao.makeTransient(complex);
	}
}
