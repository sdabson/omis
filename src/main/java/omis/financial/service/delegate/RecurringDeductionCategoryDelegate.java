package omis.financial.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.financial.dao.RecurringDeductionCategoryDao;
import omis.financial.domain.RecurringDeductionCategory;
import omis.instance.factory.InstanceFactory;

public class RecurringDeductionCategoryDelegate {
	private final RecurringDeductionCategoryDao recurringDeductionCategoryDao;
	private InstanceFactory<RecurringDeductionCategory> 
		recurringDeductionCategoryInstanceFactory;
	
	/**
	 * Constructor
	 * @param recurringDeductionCategoryDao recurringDeductionCategoryDao
	 */
	public RecurringDeductionCategoryDelegate(
		final RecurringDeductionCategoryDao recurringDeductionCategoryDao,
		final InstanceFactory<RecurringDeductionCategory> 
		recurringDeductionCategoryInstanceFactory) {
		this.recurringDeductionCategoryDao = recurringDeductionCategoryDao;
		this.recurringDeductionCategoryInstanceFactory 
			= recurringDeductionCategoryInstanceFactory;
	}
	
	/**
	 * Finds all recurring deduction categories
	 * @param offender offender
	 * @return List of all recurring deduction categories
	 */
	public List<RecurringDeductionCategory> findAll() {
		return this.recurringDeductionCategoryDao.findAll();
	}
	
	/**
	 * Creates a financial recurring deduction category
	 * @param name - name
	 * @param sortOrder - sort order
	 * @param valid - valid
	 * @return recurring deduction category
	 * @throws DuplicateEntityFoundException - when the category already exists
	 * 	with the given name
	 */
	public RecurringDeductionCategory create(final String name, 
			final Short sortOrder, final Boolean valid)
					throws DuplicateEntityFoundException {
		if (this.recurringDeductionCategoryDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
				"Duplicate recurring deduction category found.");
		}
		RecurringDeductionCategory category 
			= this.recurringDeductionCategoryInstanceFactory.createInstance();
		category.setName(name);
		category.setSortOrder(sortOrder);
		category.setValid(valid);
		return this.recurringDeductionCategoryDao.makePersistent(category);
		
	}
}