package omis.financial.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.financial.dao.FinancialAssetCategoryDao;
import omis.financial.domain.FinancialAssetCategory;

/**
 * Financial Asset Category Dao Hibernate Impl.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialAssetCategoryDaoHibernateImpl
	extends GenericHibernateDaoImpl<FinancialAssetCategory>
	implements FinancialAssetCategoryDao {

	/* Query Names */
	private static final String FIND_FINANCIAL_ASSET_CATEGORY_QUERY_NAME 
		= "findFinancialAssetCategory";

	private static final String FIND_FINANCIAL_ASSET_CATEGORY_EXCLUDING_QUERY_NAME 
		= "findFinancialAssetCategoryExcluding";

	private static final String FIND_ALL_FINANCIAL_ASSET_CATEGORIES_QUERY_NAME 
		= "findAllFinancialAssetCategories";

	/* Parameter names */
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_FINANCIAL_ASSET_CATEGORY_PARAM_NAME 
		= "excludedFinancialAssetCategory";
	
	/* Constructors */
	
	public FinancialAssetCategoryDaoHibernateImpl(final SessionFactory 
			sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public FinancialAssetCategory find(final String name) {
		FinancialAssetCategory financialAssetCategory = 
				(FinancialAssetCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_ASSET_CATEGORY_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return financialAssetCategory;
	}

	/**{@inheritDoc} */
	@Override
	public FinancialAssetCategory findExcluding(final String name, 
			final FinancialAssetCategory financialAssetCategory) {
		FinancialAssetCategory foundFinancialAssetCategory = 
				(FinancialAssetCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_ASSET_CATEGORY_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_FINANCIAL_ASSET_CATEGORY_PARAM_NAME, 
						financialAssetCategory)
				.uniqueResult();
		return foundFinancialAssetCategory;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<FinancialAssetCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<FinancialAssetCategory> financialAssetCategories =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_FINANCIAL_ASSET_CATEGORIES_QUERY_NAME)
				.list();
		return financialAssetCategories;
	}

}
