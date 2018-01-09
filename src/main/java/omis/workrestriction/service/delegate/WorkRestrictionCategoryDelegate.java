package omis.workrestriction.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.workrestriction.dao.WorkRestrictionCategoryDao;
import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionCategoryDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionCategoryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG = "Category Already "
			+ "Exists With Given Name";

	private final WorkRestrictionCategoryDao categoryDao;
	
	private final InstanceFactory<WorkRestrictionCategory> 
		workRestrictionCategoryInstanceFactory;
	
	/**
	 * Constructor for Work Restriction Category Delegate
	 * @param categoryDao - Work Restriction Category Dao
	 * @param workRestrictionCategoryInstanceFactory - instance factory
	 * for work restriction category
	 */
	public WorkRestrictionCategoryDelegate(WorkRestrictionCategoryDao categoryDao, 
			InstanceFactory<WorkRestrictionCategory> 
				workRestrictionCategoryInstanceFactory) {
		this.categoryDao = categoryDao;
		this.workRestrictionCategoryInstanceFactory 
			= workRestrictionCategoryInstanceFactory;
	}
	
	
	/**
	 * Finds and returns a list of all work restriction categories
	 * @return list of all work restriction categories
	 */
	public List<WorkRestrictionCategory> findAll(){
		return this.categoryDao.findAll();
	}
	
	/**
	 * Creates a work restriction category
	 * @param name - name of category
	 * @param valid - validity of category
	 * @return newly created work restriction category
	 */
	public WorkRestrictionCategory create(String name, Boolean valid)
			throws DuplicateEntityFoundException{
		
		WorkRestrictionCategory category 
			= this.workRestrictionCategoryInstanceFactory.createInstance();
		
		category.setName(name);
		category.setValid(valid);
		
		if(this.categoryDao.findAll().contains(category)){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		return this.categoryDao.makePersistent(category);
	}

}
