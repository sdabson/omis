package omis.offenderflag.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offenderflag.dao.OffenderFlagCategoryDao;
import omis.offenderflag.domain.FlagUsage;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * OffenderFlagCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@author Sheronda Vaughn
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class OffenderFlagCategoryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
		= "Offfender flag catgegory already exists.";
	
	private final OffenderFlagCategoryDao offenderFlagCategoryDao;
	
	private final InstanceFactory<OffenderFlagCategory> 
		offenderFlagCategoryInstanceFactory;
	

	/**
	 * Instantiates an implementation of Offender flag category delegate.
	 */
	public OffenderFlagCategoryDelegate(
			OffenderFlagCategoryDao offenderFlagCategoryDao, 
			InstanceFactory<OffenderFlagCategory> 
			offenderFlagCategoryInstanceFactory) {
		this.offenderFlagCategoryDao = offenderFlagCategoryDao;
		this.offenderFlagCategoryInstanceFactory 
			= offenderFlagCategoryInstanceFactory;
	}
	
	/**
	 * Returns a list of all OffenderFlagCategorys
	 * @return List of all OffenderFlagCategorys
	 */
	public List<OffenderFlagCategory> findAll() {
		return this.offenderFlagCategoryDao.findAll();
	}
	
	/**
	 * Returns categories for which offender flags are required.
	 * 
	 * @return categories for which offender flags are required
	 */
	public List<OffenderFlagCategory> findRequired() {
		return this.offenderFlagCategoryDao.findRequired();
	}
	
	/**
	 * Creates an offender flag category.
	 *
	 * @param name name
	 * @param description description
	 * @param requried required
	 * @param sortOrder sort order
	 * @param usage usage
	 * @return offender flag category
	 * @throws DuplicateEntityFoundException
	 */
	public OffenderFlagCategory createOffenderFlagCategory(final String name, 
			final String description, final Boolean requried, 
			final Short sortOrder, final FlagUsage usage) 
					throws DuplicateEntityFoundException { 
		if (this.offenderFlagCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}		
		
		OffenderFlagCategory offenderFlagCategory 
			= this.offenderFlagCategoryInstanceFactory.createInstance();
		offenderFlagCategory.setName(name);
		offenderFlagCategory.setDescription(description);
		offenderFlagCategory.setRequired(requried);
		offenderFlagCategory.setSortOrder(sortOrder);
		offenderFlagCategory.setUsage(usage);
		offenderFlagCategory.setValid(true);
				
		return this.offenderFlagCategoryDao.makePersistent(
				offenderFlagCategory);
	}
	
	/**
	 * Updates an offender flag category.
	 *
	 * @param offenderFlagCategory offender flag category
	 * @param name name
	 * @param requried required
	 * @param sortOrder sort order
	 * @param usage usage
	 * @return updated offender flag category
	 * @throws DuplicateEntityFoundException
	 */
	public OffenderFlagCategory updateOffenderFlagCategory(
			OffenderFlagCategory offenderFlagCategory, String name, 
			Boolean requried, Short sortOrder, FlagUsage usage) 
					throws DuplicateEntityFoundException {
		if (this.offenderFlagCategoryDao.findExcluding(
				offenderFlagCategory, name) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}				
	
		offenderFlagCategory.setName(name);
		offenderFlagCategory.setRequired(requried);
		offenderFlagCategory.setSortOrder(sortOrder);
		offenderFlagCategory.setUsage(usage);
		offenderFlagCategory.setValid(true);
				
		return this.offenderFlagCategoryDao.makePersistent(
				offenderFlagCategory);
	}	
	
	/**
	 * Removes an offender flag category.
	 *
	 * @param offenderFlagCategory offender flag category
	 */
	public void removeOffenderFlagCategory(
			OffenderFlagCategory offenderFlagCategory) {
		this.offenderFlagCategoryDao.makeTransient(offenderFlagCategory);
	}	
}