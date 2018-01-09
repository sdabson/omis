package omis.financial.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.financial.dao.FinancialLiabilityDao;
import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.offender.domain.Offender;

/**
 * Financial Liability Dao Hibernate Impl.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialLiabilityDaoHibernateImpl 
	extends GenericHibernateDaoImpl<FinancialLiability>
	implements FinancialLiabilityDao {

	/* Query Names */
	private static final String FIND_FINANCIAL_LIABILITY_QUERY_NAME 
		= "findFinancialLiability";

	private static final String FIND_FINANCIAL_LIABILITY_EXCLUDING_QUERY_NAME 
		= "findFinancialLiabilityExcluding";

	private static final String FIND_BY_OFFENDER_QUERY_NAME 
		= "findAllFinancialLiabilitiesByOffender";
	
	private static final String FIND_LIABILITY_TOTAL_BY_OFFENDER_QUERY_NAME 
		= "findLiabilityTotalByOffender";

	/* Parameter names */
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String CATEGORY_PARAM_NAME 
		= "category";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String REPORTED_DATE_PARAM_NAME = "reportedDate";
	
	private static final String AMOUNT_PARAM_NAME = "amount";
	
	private static final String FINANCIAL_LIABILITY_PARAM_NAME 
		= "excludedFinancialLiability";
	
	
	/* Constructors */
	
	public FinancialLiabilityDaoHibernateImpl(final SessionFactory 
			sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public FinancialLiability find(final Offender offender, 
			final FinancialLiabilityCategory category, final String description,
			final Date reportedDate, final BigDecimal amount) {
		FinancialLiability financialLiability = 
				(FinancialLiability) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_LIABILITY_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(REPORTED_DATE_PARAM_NAME, reportedDate)
				.setParameter(AMOUNT_PARAM_NAME, amount)
				.uniqueResult();
		return financialLiability;
	}
	
	/** {@inheritDoc} */
	@Override
	public FinancialLiability findExcluding(final Offender offender, 
			final FinancialLiabilityCategory category, final String description,
			final Date reportedDate, final BigDecimal amount, 
			final FinancialLiability excludedFinancialLiability) {
		FinancialLiability financialLiability = 
				(FinancialLiability) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_LIABILITY_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(REPORTED_DATE_PARAM_NAME, reportedDate)
				.setParameter(AMOUNT_PARAM_NAME, amount)
				.setParameter(FINANCIAL_LIABILITY_PARAM_NAME, 
						excludedFinancialLiability)
				.uniqueResult();
		return financialLiability;
	}

	/** {@inheritDoc} */
	@Override
	public List<FinancialLiability> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<FinancialLiability> financialLiabilities = 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return financialLiabilities;
	}

	/** {@inheritDoc} */
	@Override
	public BigDecimal findLiabilitySumByOffender(final Offender offender) {
		BigDecimal liabilityTotal = (BigDecimal) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LIABILITY_TOTAL_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return liabilityTotal;
	}

}
