package omis.person.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.person.dao.AlternativeNameCategoryDao;
import omis.person.domain.AlternativeNameCategory;

/**
 * AlternativeNameCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 9, 2016)
 *@since OMIS 3.0
 *
 */
public class AlternativeNameCategoryDelegate {
	
	private final AlternativeNameCategoryDao alternativeNameCategoryDao;
	
	private final InstanceFactory<AlternativeNameCategory>
		alternativeNameCategoryInstanceFactory;
	
	/**
	 * @param alternativeNameCategoryDao
	 */
	public AlternativeNameCategoryDelegate(AlternativeNameCategoryDao 
			alternativeNameCategoryDao, InstanceFactory<AlternativeNameCategory>
			alternativeNameCategoryInstanceFactory) {
		this.alternativeNameCategoryDao = alternativeNameCategoryDao;
		this.alternativeNameCategoryInstanceFactory =
				alternativeNameCategoryInstanceFactory;
	}
	
	/**
	 * Returns a list of all AlternativeNameCategories
	 * @return list of all AlternativeNameCategories
	 */
	public List<AlternativeNameCategory> findAll() {
		return this.alternativeNameCategoryDao.findAll();
	}
	
	/**
	 * Creates an Alternative Name Category (used for testing purposes)
	 * @param description - String
	 * @param name - String
	 * @param sortOrder - Short
	 * @param valid - Boolean
	 * @return Created AlternativeNameCategory
	 */
	public AlternativeNameCategory create(final String description,
			final String name, final Short sortOrder, final Boolean valid){
		AlternativeNameCategory alternativeNameCategory =
				this.alternativeNameCategoryInstanceFactory.createInstance();
		
		alternativeNameCategory.setDescription(description);
		alternativeNameCategory.setName(name);
		alternativeNameCategory.setSortOrder(sortOrder);
		alternativeNameCategory.setValid(valid);
		
		return alternativeNameCategoryDao.makePersistent(
				alternativeNameCategory);
	}
	
	
}
