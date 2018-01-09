package omis.financial.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.financial.dao.RecurringDeductionCategoryDao;
import omis.financial.domain.RecurringDeductionCategory;

public class RecurringDeductionCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<RecurringDeductionCategory>
	implements RecurringDeductionCategoryDao {
	/* Query Names */
	private static final String FIND_ALL_RECURRING_DEDUCTION_CATEGORIES_QUERY_NAME 
		= "findAllRecurringDeductionCategories";
	private static final String FIND_RECURRING_DEDUCTION_CATEGORY_QUERY_NAME 
		= "findRecurringDeductionCategory";
	private static final String FIND_RECURRING_DEDUCTION_CATEGORY_EXCLUDING_QUERY_NAME 
		= "findRecurringDeductionCategoryExcluding";
	
	/* Parameter names */
	private static final String NAME_PARAM_NAME = "name";
	private static final String EXCLUDED_RECURRING_DEDUCTION_CATEGORY_PARAM_NAME 
		= "excludedRecurringdeDuctionCategory";
	
	public RecurringDeductionCategoryDaoHibernateImpl (
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<RecurringDeductionCategory> findAll(){
		List<RecurringDeductionCategory> recurringDeductionCategories =
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ALL_RECURRING_DEDUCTION_CATEGORIES_QUERY_NAME)
			.list();
		return recurringDeductionCategories;
	};
	
	/**{@inheritDoc} */
	@Override
	public RecurringDeductionCategory find(final String name) {
		RecurringDeductionCategory recurringDeductionCategory = 
				(RecurringDeductionCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RECURRING_DEDUCTION_CATEGORY_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return recurringDeductionCategory;
	}

	/**{@inheritDoc} */
	@Override
	public RecurringDeductionCategory findExcluding(final String name, 
			final RecurringDeductionCategory excludedRecurringDeductionCategory) {
		RecurringDeductionCategory recurringDeductionCategory = 
				(RecurringDeductionCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RECURRING_DEDUCTION_CATEGORY_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_RECURRING_DEDUCTION_CATEGORY_PARAM_NAME, 
					excludedRecurringDeductionCategory)
				.uniqueResult();
		return recurringDeductionCategory;
	}
}