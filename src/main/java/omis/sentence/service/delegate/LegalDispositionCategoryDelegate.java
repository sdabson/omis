package omis.sentence.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.sentence.dao.LegalDispositionCategoryDao;
import omis.sentence.domain.LegalDispositionCategory;

/**
 * Delegate to manage legal disposition categories.
 *
 * @author Josh Divine
 * @version 0.0.1 (Apr 3, 2017)
 * @since OMIS 3.0
 */
public class LegalDispositionCategoryDelegate {

	private final LegalDispositionCategoryDao legalDispositionCategoryDao;
	
	private final InstanceFactory<LegalDispositionCategory> 
		sentenceCategoryInstanceFactory;

	/**
	 * Instantiates delegate to manage legal disposition categories.
	 * 
	 * @param legalDispositionCategoryDao data access object for legal 
	 * disposition categories
	 * @param legalDispositionCategoryInstanceFactory instance factory for legal 
	 * disposition categories
	 */
	public LegalDispositionCategoryDelegate(
			final LegalDispositionCategoryDao legalDispositionCategoryDao,
			final InstanceFactory<LegalDispositionCategory> 
				legalDispositionCategoryInstanceFactory) {
		this.legalDispositionCategoryDao = legalDispositionCategoryDao;
		this.sentenceCategoryInstanceFactory 
			= legalDispositionCategoryInstanceFactory;
	}
	
	/**
	 * Creates a new legal disposition category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return legal disposition category
	 * @throws DuplicateEntityFoundException occurs when duplicate legal 
	 * disposition category is found
	 */
	public LegalDispositionCategory create(String name, Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.legalDispositionCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate legal disposition category found.");
		}
		LegalDispositionCategory category = this.sentenceCategoryInstanceFactory
				.createInstance();
		category.setName(name);
		category.setValid(valid);
		return this.legalDispositionCategoryDao.makePersistent(category);
	}
	
	/**
	 * Updates the specified legal disposition category.
	 * 
	 * @param category conviction category
	 * @param name name
	 * @param valid valid
	 * @return legal disposition category
	 * @throws DuplicateEntityFoundException occurs when duplicate legal 
	 * disposition category is found
	 */
	public LegalDispositionCategory update(LegalDispositionCategory category, 
			String name, Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.legalDispositionCategoryDao.findExcluding(name, category) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate legal disposition category found.");
		}
		category.setName(name);
		category.setValid(valid);
		return this.legalDispositionCategoryDao.makePersistent(category);	
	}
	
	/**
	 * Removes the specified legal disposition category.
	 * 
	 * @param category legal disposition category
	 */
	public void remove(LegalDispositionCategory category) {
		this.legalDispositionCategoryDao.makeTransient(category);
	}
	
	/**
	 * Returns legal disposition categories.
	 * 
	 * @return legal disposition categories
	 */
	public List<LegalDispositionCategory> findLegalDispositionCategories() {
		return this.legalDispositionCategoryDao.findAll();
	}
}
