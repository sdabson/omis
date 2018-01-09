package omis.identificationnumber.service.delegate;

import java.util.List;
import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.dao.IdentificationNumberCategoryDao;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.Multitude;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for identification number categories.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class IdentificationNumberCategoryDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<IdentificationNumberCategory>
	identificationNumberCategoryInstanceFactory;

	/* Data access objects. */
	
	private final IdentificationNumberCategoryDao
	identificationNumberCategoryDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for identification number categories.
	 * 
	 * @param identificationNumberCategoryInstanceFactory instance factory
	 * for identification number categories
	 * @param identificationNumberCategoryDao data access object for
	 * identification number categories
	 */
	public IdentificationNumberCategoryDelegate(
			final InstanceFactory<IdentificationNumberCategory>
				identificationNumberCategoryInstanceFactory,
			final IdentificationNumberCategoryDao
				identificationNumberCategoryDao) {
		this.identificationNumberCategoryInstanceFactory
			= identificationNumberCategoryInstanceFactory;
		this.identificationNumberCategoryDao = identificationNumberCategoryDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns identification number categories.
	 * 
	 * @return identification number categories
	 */
	public List<IdentificationNumberCategory> findAll() {
		return this.identificationNumberCategoryDao.findAll();
	}

	/**
	 * Creates identification number category.
	 * 
	 * @param name name
	 * @param multitude multitude
	 * @param valid whether valid
	 * @return created identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 */
	public IdentificationNumberCategory create(
			final String name, final Multitude multitude, final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.identificationNumberCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Category exists");
		}
		IdentificationNumberCategory category
			= this.identificationNumberCategoryInstanceFactory.createInstance();
		category.setName(name);
		category.setMultitude(multitude);
		category.setValid(valid);
		return this.identificationNumberCategoryDao.makePersistent(category);
	}
	
	/**
	 * Updates identification number category.
	 * 
	 * @param category identification number category being updated
	 * @param name name
	 * @param multitude multitude
	 * @param valid whether valid
	 * @return updated identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 */
	public IdentificationNumberCategory update(
			final IdentificationNumberCategory category,
			final String name, final Multitude multitude, final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.identificationNumberCategoryDao.findExcluding(name, category) != null) {
			throw new DuplicateEntityFoundException("Category exists");
		}
		category.setName(name);
		category.setMultitude(multitude);
		category.setValid(valid);
		return this.identificationNumberCategoryDao.makePersistent(category);
	}
	
	/**
	 * Removes identification number category
	 * 
	 * @param category identification number category to remove
	 */
	public void remove(final IdentificationNumberCategory category) {
		this.identificationNumberCategoryDao.makeTransient(category);
	}
}