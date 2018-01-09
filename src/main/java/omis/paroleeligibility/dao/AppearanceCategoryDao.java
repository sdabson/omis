package omis.paroleeligibility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Appearance category data access object.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface AppearanceCategoryDao extends GenericDao<AppearanceCategory> {

	/**
	 * Finds all appearance categories.
	 * 
	 * @return all appearance categories
	 */
	List<AppearanceCategory> findAppearanceCategories();

	/**
	 * Finds an appearance category
	 * 
	 * @param name name of the appearance category
	 * @param valid whether the appearance category is valid
	 * @return appearance category
	 */
	AppearanceCategory find(String name);
	
	/**
	 * Finds an appearance category, not including the specified appearance 
	 * category
	 * 
	 * @param appearanceCategory appearance category to exclude
	 * @param name name of the appearance category
	 * @param valid whether an appearance category is valid
	 * @return appearance category, not including the specified appearance
	 * category
	 */
	AppearanceCategory findExcluding(AppearanceCategory appearanceCategory,
			String name);
	
}
