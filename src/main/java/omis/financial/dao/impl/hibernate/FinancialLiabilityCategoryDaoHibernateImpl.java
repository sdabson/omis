package omis.financial.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.financial.dao.FinancialLiabilityCategoryDao;
import omis.financial.domain.FinancialLiabilityCategory;

/**
 * Financial Liability Category Dao Hibernate Impl.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialLiabilityCategoryDaoHibernateImpl
	extends GenericHibernateDaoImpl<FinancialLiabilityCategory>
	implements FinancialLiabilityCategoryDao {

	/* Query Names */
	private static final String FIND_FINANCIAL_LIABILITY_CATEGORY_QUERY_NAME 
		= "findFinancialLiabilityCategory";

	private static final String FIND_FINANCIAL_LIABILITY_CATEGORY_EXCLUDING_QUERY_NAME 
		= "findFinancialLiabilityCategoryExcluding";

	private static final String FIND_ALL_FINANCIAL_LIABILITY_CATEGORIES_QUERY_NAME 
		= "findAllFinancialLiabilityCategories";

	/* Parameter names */
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_FINANCIAL_LIABILITY_CATEGORY_PARAM_NAME 
		= "excludedFinancialLiabilityCategory";
	
	/* Constructors */
	
	public FinancialLiabilityCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public FinancialLiabilityCategory find(final String name) {
		FinancialLiabilityCategory financialLiabilityCategory = 
				(FinancialLiabilityCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_LIABILITY_CATEGORY_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return financialLiabilityCategory;
	}

	/**{@inheritDoc} */
	@Override
	public FinancialLiabilityCategory findExcluding(final String name, 
			final FinancialLiabilityCategory financialLiabilityCategory) {
		FinancialLiabilityCategory foundFinancialLiabilityCategory = 
				(FinancialLiabilityCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_FINANCIAL_LIABILITY_CATEGORY_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_FINANCIAL_LIABILITY_CATEGORY_PARAM_NAME, 
						financialLiabilityCategory)
				.uniqueResult();
		return foundFinancialLiabilityCategory;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<FinancialLiabilityCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<FinancialLiabilityCategory> financialLiabilityCategories =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_ALL_FINANCIAL_LIABILITY_CATEGORIES_QUERY_NAME)
				.list();
		return financialLiabilityCategories;
	}

}
