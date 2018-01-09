package omis.sentence.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.sentence.dao.SentenceCategoryDao;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.TermRequirement;

/**
 * Delegate to manage sentence categories.
 *
 * @author Josh Divine
 * @version 0.0.1 (Jan 27, 2017)
 * @since OMIS 3.0
 */
public class SentenceCategoryDelegate {

	private final SentenceCategoryDao sentenceCategoryDao;
	
	private final InstanceFactory<SentenceCategory> 
		sentenceCategoryInstanceFactory;

	/**
	 * Instantiates delegate to manage sentence categories.
	 * 
	 * @param sentenceCategoryDao data access object for sentence categories
	 * @param sentenceCategoryInstanceFactory instance factory for sentence 
	 * categories
	 */
	public SentenceCategoryDelegate(
			final SentenceCategoryDao sentenceCategoryDao,
			final InstanceFactory<SentenceCategory> 
				sentenceCategoryInstanceFactory) {
		this.sentenceCategoryDao = sentenceCategoryDao;
		this.sentenceCategoryInstanceFactory 
			= sentenceCategoryInstanceFactory;
	}
	
	/**
	 * Creates a new sentence category.
	 * 
	 * @param name name
	 * @param prisonRequirement prison term requirement
	 * @param probationRequirement probation term requirement
	 * @param deferredRequirement deferred term requirement
	 * @return sentence category
	 * @throws DuplicateEntityFoundException occurs when duplicate sentence 
	 * category is found
	 */
	public SentenceCategory create(final String name, 
			final TermRequirement prisonRequirement, 
			final TermRequirement probationRequirement, 
			final TermRequirement deferredRequirement) 
					throws DuplicateEntityFoundException {
		if (this.sentenceCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Duplicate sentence "
					+ "category found.");
		}
		SentenceCategory category = this.sentenceCategoryInstanceFactory
				.createInstance();
		category.setName(name);
		category.setPrisonRequirement(prisonRequirement);
		category.setProbationRequirement(probationRequirement);
		category.setDeferredRequirement(deferredRequirement);
		return this.sentenceCategoryDao.makePersistent(category);
	}
	
	/**
	 * Updates the specified sentence category.
	 * 
	 * @param category sentence category
	 * @param name name
	 * @param prisonRequirement prison term requirement
	 * @param probationRequirement probation term requirement
	 * @param deferredRequirement deferred term requirement
	 * @return sentence category
	 * @throws DuplicateEntityFoundException occurs when duplicate sentence 
	 * category is found
	 */
	public SentenceCategory update(SentenceCategory category, String name, 
			final TermRequirement prisonRequirement, 
			final TermRequirement probationRequirement, 
			final TermRequirement deferredRequirement) 
					throws DuplicateEntityFoundException {
		if (this.sentenceCategoryDao.findExcluding(name, category) != null) {
			throw new DuplicateEntityFoundException("Duplicate sentence "
					+ "category found.");
		}
		category.setName(name);
		category.setPrisonRequirement(prisonRequirement);
		category.setProbationRequirement(probationRequirement);
		category.setDeferredRequirement(deferredRequirement);
		return this.sentenceCategoryDao.makePersistent(category);	
	}
	
	/**
	 * Removes the specified sentence category.
	 * 
	 * @param category sentence category
	 */
	public void remove(SentenceCategory category) {
		this.sentenceCategoryDao.makeTransient(category);
	}
	
	/**
	 * Returns sentence categories.
	 * 
	 * @return sentence categories
	 */
	public List<SentenceCategory> findSentenceCategories() {
		return this.sentenceCategoryDao.findAll();
	}
}
