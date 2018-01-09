package omis.financial.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.financial.dao.FinancialAssetDao;
import omis.financial.domain.FinancialAsset;
import omis.financial.domain.FinancialAssetCategory;
import omis.offender.domain.Offender;

/**
 * Financial Asset Dao Hibernate Impl.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialAssetDaoHibernateImpl 
	extends GenericHibernateDaoImpl<FinancialAsset>
	implements FinancialAssetDao {

	/* Query Names */
	private static final String FIND_FINANCIAL_ASSET_QUERY_NAME 
		= "findFinancialAsset";

	private static final String FIND_FINANCIAL_ASSET_EXCLUDING_QUERY_NAME 
		= "findFinancialAssetExcluding";

	private static final String FIND_BY_OFFENDER_QUERY_NAME 
		= "findAllFinancialAssetsByOffender";

	private static final String FIND_ASSET_TOTAL_BY_OFFENDER_QUERY_NAME 
		= "findAssetTotalByOffender";
	
	/* Parameter names */
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String CATEGORY_PARAM_NAME 
		= "category";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String REPORTED_DATE_PARAM_NAME = "reportedDate";
	
	private static final String AMOUNT_PARAM_NAME = "amount";
	
	private static final String FINANCIAL_ASSET_PARAM_NAME 
		= "excludedFinancialAsset";
	
	
	/* Constructors */
	
	public FinancialAssetDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public FinancialAsset find(final Offender offender, 
			final FinancialAssetCategory category, final String description,
			final Date reportedDate, final BigDecimal amount) {
		FinancialAsset financialAsset = 
				(FinancialAsset) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_ASSET_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(REPORTED_DATE_PARAM_NAME, reportedDate)
				.setParameter(AMOUNT_PARAM_NAME, amount)
				.uniqueResult();
		return financialAsset;
	}
	
	/** {@inheritDoc} */
	@Override
	public FinancialAsset findExcluding(final Offender offender, 
			final FinancialAssetCategory category, final String description,
			final Date reportedDate, final BigDecimal amount, 
			final FinancialAsset excludedFinancialAsset) {
		FinancialAsset financialAsset = 
				(FinancialAsset) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_ASSET_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(REPORTED_DATE_PARAM_NAME, reportedDate)
				.setParameter(AMOUNT_PARAM_NAME, amount)
				.setParameter(FINANCIAL_ASSET_PARAM_NAME, 
						excludedFinancialAsset)
				.uniqueResult();
		return financialAsset;
	}

	/** {@inheritDoc} */
	@Override
	public List<FinancialAsset> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<FinancialAsset> financialAssets = 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return financialAssets;
	}

	/** {@inheritDoc} */
	@Override
	public BigDecimal findAssetSumByOffender(final Offender offender) {
		BigDecimal assetTotal = (BigDecimal) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ASSET_TOTAL_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return assetTotal;
	}

}
