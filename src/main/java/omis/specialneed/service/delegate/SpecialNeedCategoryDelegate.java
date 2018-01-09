package omis.specialneed.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.specialneed.dao.SpecialNeedCategoryDao;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;

/**
 * Special need category delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedCategoryDelegate {

	/* Data access objects. */
	
	private final SpecialNeedCategoryDao specialNeedCategoryDao;
	

	/* Instance factories. */
	
	private final InstanceFactory<SpecialNeedCategory> 
			specialNeedCategoryInstanceFactory;
	
	/** Instantiates an implementation of special need category delegate with 
	 * the specified date access object and instance factory.
	 * 
	 * @param specialNeedCategoryDao special need category data access object
	 * @param specialNeedCategoryInstanceFactory special need category instance 
	 * factory
	 */
	public SpecialNeedCategoryDelegate(
			final SpecialNeedCategoryDao specialNeedCategoryDao,
			final InstanceFactory<SpecialNeedCategory> 
				specialNeedCategoryInstanceFactory) {
		this.specialNeedCategoryDao = specialNeedCategoryDao;
		this.specialNeedCategoryInstanceFactory = 
				specialNeedCategoryInstanceFactory; 
	}
	
	/**
	 * Searches for a special need category based on a specified 
	 * special need classification.
	 *
	 *
	 * @param classification classification
	 * @return list of special need categories
	 */
	public List<SpecialNeedCategory> findCategories(
			SpecialNeedClassification classification) {
		return this.specialNeedCategoryDao.findCategories(classification);
	}	
	
	/**
	 * Creates a special need category.
	 * 
	 * @param name name
	 * @param classification special need classification
	 * @param valid valid
	 * @return special need category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedCategory create(final String name, 
			final SpecialNeedClassification classification, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.specialNeedCategoryDao.find(name, classification) != null) {
			throw new DuplicateEntityFoundException("Special need category "
					+ "already exists");
		}
		SpecialNeedCategory specialNeedCategory = 
				this.specialNeedCategoryInstanceFactory.createInstance();
		populateSpecialNeedCategory(specialNeedCategory, name, classification, 
				valid);
		return this.specialNeedCategoryDao.makePersistent(specialNeedCategory);
	}
	
	/**
	 * Updates a special need category.
	 * 
	 * @param specialNeedCategory special need category
	 * @param name name
	 * @param classification special need classification
	 * @param valid valid
	 * @return special need category
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedCategory update(
			final SpecialNeedCategory specialNeedCategory, final String name, 
			final SpecialNeedClassification classification, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.specialNeedCategoryDao.findExcluding(name, classification, 
				specialNeedCategory) != null) {
			throw new DuplicateEntityFoundException("Special need category "
					+ "already exists");
		}
		populateSpecialNeedCategory(specialNeedCategory, name, classification, 
				valid);
		return this.specialNeedCategoryDao.makePersistent(specialNeedCategory);		
	}

	/**
	 * Removes a special need category.
	 * 
	 * @param specialNeedCategory special need category
	 */
	public void remove(final SpecialNeedCategory specialNeedCategory) {
		this.specialNeedCategoryDao.makeTransient(specialNeedCategory);
	}

	// Populates a special need category
	private void populateSpecialNeedCategory(
			final SpecialNeedCategory specialNeedCategory, final String name,
			final SpecialNeedClassification classification,
			final Boolean valid) {
		specialNeedCategory.setName(name);
		specialNeedCategory.setClassification(classification);
		specialNeedCategory.setValid(valid);
	}
}
