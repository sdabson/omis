package omis.financial.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.financial.dao.FinancialAssetCategoryDao;
import omis.financial.domain.FinancialAssetCategory;
import omis.instance.factory.InstanceFactory;

/**
 * Financial Asset Category Delegate.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialAssetCategoryDelegate {

	private final InstanceFactory<FinancialAssetCategory> 
		financialAssetCategoryInstanceFactory;
	
	private final FinancialAssetCategoryDao financialAssetCategoryDao;
	
	/**
	 * Constructor
	 * @param financialAssetCategoryInstanceFactory
	 * @param financialAssetCategoryDao
	 */
	public FinancialAssetCategoryDelegate(
			final InstanceFactory<FinancialAssetCategory> 
			financialAssetCategoryInstanceFactory,
			final FinancialAssetCategoryDao financialAssetCategoryDao) {
		this.financialAssetCategoryInstanceFactory 
			= financialAssetCategoryInstanceFactory;
		this.financialAssetCategoryDao = financialAssetCategoryDao;
	}
	

	/**
	 * Creates an educational achievement category
	 * @param name - name
	 * @param sortOrder - sort order
	 * @param valid - valid
	 * @return EducationalAchievementCategory
	 * @throws DuplicateEntityFoundException - when the category already exists
	 * 	with the given name
	 */
	public FinancialAssetCategory create(final String name, 
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.financialAssetCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate financial asset category found.");
		}
		FinancialAssetCategory assetCategory 
			= this.financialAssetCategoryInstanceFactory
				.createInstance(); 
		assetCategory.setName(name);
		assetCategory.setSortOrder(sortOrder);
		assetCategory.setValid(valid);
		
		return this.financialAssetCategoryDao
				.makePersistent(assetCategory);
		
	}
	
	/**
	 * Finds all financial asset categories
	 * @return list of financial asset categories
	 */
	public List<FinancialAssetCategory> findAll() {
		return this.financialAssetCategoryDao.findAll();
	}
	
}
	

