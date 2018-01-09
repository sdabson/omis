package omis.physicalfeature.service.impl;

import java.util.List;

import omis.physicalfeature.dao.FeatureClassificationDao;
import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.service.FeatureClassificationService;

/**
 * Implementation of feature classification service.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class FeatureClassificationServiceImpl 
	implements FeatureClassificationService {

	private FeatureClassificationDao featureClassificationDao;
	
	/**
	 * Instantiates a feature classification service implementation with 
	 * the specified data access object.
	 * @param featureClassificationDao feature classification DAO
	 */
	public FeatureClassificationServiceImpl(
			final FeatureClassificationDao featureClassificationDao) {
		this.featureClassificationDao = featureClassificationDao;
	}

	/** {@inheritDoc} */
	@Override
	public FeatureClassification findById(final Long id) {
		return this.featureClassificationDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public FeatureClassification save(
			final FeatureClassification featureClassification) {
		return this.featureClassificationDao.makePersistent(
				featureClassification);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final FeatureClassification featureClassification) {
		this.featureClassificationDao.makeTransient(featureClassification);
	}

	/** {@inheritDoc} */
	@Override
	public List<FeatureClassification> findAll() {
		return this.featureClassificationDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<FeatureClassification> findValid() {
		return this.featureClassificationDao.findValid();
	}	
}