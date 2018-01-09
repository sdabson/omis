package omis.physicalfeature.service.impl;

import java.util.List;

import omis.physicalfeature.dao.PhysicalFeatureDao;
import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;
import omis.physicalfeature.service.PhysicalFeatureService;

/**
 * Implementation of physical feature service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeatureServiceImpl implements PhysicalFeatureService {

	private PhysicalFeatureDao physicalFeatureDao;
	
	/**
	 * Instantiates a physical feature service implementation with the 
	 * specified data access object.
	 * @param physicalFeatureDao physical feature DAO
	 */
	public PhysicalFeatureServiceImpl(
			final PhysicalFeatureDao physicalFeatureDao) {
		this.physicalFeatureDao = physicalFeatureDao;
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeature findById(final Long id) {
		return this.physicalFeatureDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeature save(final PhysicalFeature physicalFeature) {
		return this.physicalFeatureDao.makePersistent(physicalFeature);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final PhysicalFeature physicalFeature) {
		this.physicalFeatureDao.makeTransient(physicalFeature);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeature> findAll() {
		return this.physicalFeatureDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeature> findPhysicalFeatureByClassification(
			final FeatureClassification featureClassification) {
		return this.physicalFeatureDao
				.findPhysicalFeatureByClassification(featureClassification);
	}	
}