package omis.paroleeligibility.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleeligibility.dao.AppearanceCategoryDao;
import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Delegate for the appearance category.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class AppearanceCategoryDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<AppearanceCategory> 
		appearanceCategoryInstanceFactory;
	
	/* DAOs. */
	
	private final AppearanceCategoryDao appearanceCategoryDao;
	
	/* Constructor. */
	
	public AppearanceCategoryDelegate(
			final InstanceFactory<AppearanceCategory> 
				appearanceCategoryInstanceFactory,
			final AppearanceCategoryDao appearanceCategoryDao) {
		this.appearanceCategoryInstanceFactory 
			= appearanceCategoryInstanceFactory;
		this.appearanceCategoryDao = appearanceCategoryDao;
	}
	
	/**
	 * Creates a new appearance category.
	 * 
	 * @param name name of the appearance category
	 * @param valid whether an appearance category is valid
	 * @return appearance category
	 * @throws DuplicateEntityFoundException
	 */
	public AppearanceCategory create(
			final String name, 
			final Boolean valid)
		throws DuplicateEntityFoundException {
		if (this.appearanceCategoryDao.find(
				name) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate appearance category found.");
		}
		
		AppearanceCategory category = 
				this.appearanceCategoryInstanceFactory.createInstance();
				this.populateAppearanceCategory(category, name, valid);
		return this.appearanceCategoryDao.makePersistent(category);
	}
	
	/**
	 * Updates an appearance category.
	 * 
	 * @param appearanceCategory
	 * @param name
	 * @param valid
	 * @return updated appearance category
	 */
	public AppearanceCategory update(
			final AppearanceCategory appearanceCategory,
			final String name,
			final Boolean valid)
		throws DuplicateEntityFoundException {
		if (this.appearanceCategoryDao.findExcluding(appearanceCategory, name) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate appearance category found.");
		}
		this.populateAppearanceCategory(appearanceCategory, name, valid);
		return this.appearanceCategoryDao.makePersistent(appearanceCategory);
	}
	
	/**
	 * Removes an appearance category.
	 * 
	 * @param appearanceCategory
	 */
	public void remove(final AppearanceCategory appearanceCategory) {
		this.appearanceCategoryDao.makeTransient(appearanceCategory);
	}
	
	public List<AppearanceCategory> findAppearanceCategories() {
		return this.appearanceCategoryDao.findAppearanceCategories();
	}
	
	/**
	 * Populates a specified appearance category.
	 * 
	 * @param appearanceCategory
	 * @param name
	 * @param valid
	 */
	private void populateAppearanceCategory(
			final AppearanceCategory appearanceCategory,
			final String name,
			final Boolean valid) {
		appearanceCategory.setName(name);
		appearanceCategory.setValid(valid);
	}

}
