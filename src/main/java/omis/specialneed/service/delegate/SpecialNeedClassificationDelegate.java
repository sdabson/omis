package omis.specialneed.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.specialneed.dao.SpecialNeedClassificationDao;
import omis.specialneed.domain.SpecialNeedClassification;

/**
 * Special need classification delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedClassificationDelegate {

	/* Data access objects. */
	
	private final SpecialNeedClassificationDao specialNeedClassificationDao;
	

	/* Instance factories. */
	
	private final InstanceFactory<SpecialNeedClassification> 
			specialNeedClassificationInstanceFactory;
	
	/** Instantiates an implementation of special need delegate with 
	 * the specified date access object and instance factory.
	 * 
	 * @param specialNeedClassificationDao special need classification 
	 * data access object
	 * @param specialNeedClassificationInstanceFactory special need 
	 * classification instance factory
	 */
	public SpecialNeedClassificationDelegate(
			final SpecialNeedClassificationDao specialNeedClassificationDao,
			final InstanceFactory<SpecialNeedClassification> 
				specialNeedClassificationInstanceFactory) {
		this.specialNeedClassificationDao = specialNeedClassificationDao;
		this.specialNeedClassificationInstanceFactory = 
				specialNeedClassificationInstanceFactory; 
	}
	
	/**
	 * Searches for all special need classifications.
	 *
	 *
	 * @return list of special need classifications
	 */
	public List<SpecialNeedClassification> findClassifications() {
		return this.specialNeedClassificationDao.findAll();
	}
	
	/**
	 * Creates a special need classification.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return special need classification
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedClassification create(final String name, 
			final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.specialNeedClassificationDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Special need "
					+ "classification already exists");
		}
		SpecialNeedClassification specialNeedClassification = 
				this.specialNeedClassificationInstanceFactory.createInstance();
		populateSpecialNeedClassification(specialNeedClassification, name, 
				valid);
		return this.specialNeedClassificationDao.makePersistent(
				specialNeedClassification);
	}
	
	/**
	 * Updates a special need classification.
	 * 
	 * @param specialNeedClassification special need classification
	 * @param name name
	 * @param valid valid
	 * @return special need classification
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedClassification update(
			final SpecialNeedClassification specialNeedClassification, 
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.specialNeedClassificationDao.findExcluding(name, 
				specialNeedClassification) != null) {
			throw new DuplicateEntityFoundException("Special need "
					+ "classification already exists");
		}
		populateSpecialNeedClassification(specialNeedClassification, name, 
				valid);
		return this.specialNeedClassificationDao.makePersistent(
				specialNeedClassification);		
	}

	/**
	 * Removes a special need classification.
	 * 
	 * @param specialNeedClassification special need classification
	 */
	public void remove(
			final SpecialNeedClassification specialNeedClassification) {
		this.specialNeedClassificationDao.makeTransient(
				specialNeedClassification);
	}

	// Populates a special need classification
	private void populateSpecialNeedClassification(
			final SpecialNeedClassification specialNeedClassification, 
			final String name, final Boolean valid) {
		specialNeedClassification.setName(name);
		specialNeedClassification.setValid(valid);
	}
}
