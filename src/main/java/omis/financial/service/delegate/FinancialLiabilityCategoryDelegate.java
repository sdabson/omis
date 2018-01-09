package omis.financial.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.financial.dao.FinancialLiabilityCategoryDao;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.instance.factory.InstanceFactory;

/**
 * Financial Liability Category Delegate.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialLiabilityCategoryDelegate {

	private FinancialLiabilityCategoryDao financialLiabilityCategoryDao;
	private InstanceFactory<FinancialLiabilityCategory> 
		financialLiabilityCategoryInstanceFactory;
	
	
	/**
	 * Constructor
	 * @param financialLiabilityCategoryInstanceFactory
	 * @param financialLiabilityCategoryDao
	 */
	public FinancialLiabilityCategoryDelegate(
			final InstanceFactory<FinancialLiabilityCategory> 
			financialLiabilityCategoryInstanceFactory,
			final FinancialLiabilityCategoryDao financialLiabilityCategoryDao) {
		this.financialLiabilityCategoryInstanceFactory 
			= financialLiabilityCategoryInstanceFactory;
		this.financialLiabilityCategoryDao = financialLiabilityCategoryDao;
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
	public FinancialLiabilityCategory create(final String name, 
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
		if(this.financialLiabilityCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate financial asset category found.");
						}
		FinancialLiabilityCategory liabilityCategory 
			= this.financialLiabilityCategoryInstanceFactory
				.createInstance(); 
		liabilityCategory.setName(name);
		liabilityCategory.setSortOrder(sortOrder);
		liabilityCategory.setValid(valid);
		
		return this.financialLiabilityCategoryDao
				.makePersistent(liabilityCategory);
		
	}
	
	/**
	 * Finds all financial liability categories
	 * @return list of financial liability categories
	 */
	public List<FinancialLiabilityCategory> findAll() {
		return this.financialLiabilityCategoryDao.findAll();
	}
}
