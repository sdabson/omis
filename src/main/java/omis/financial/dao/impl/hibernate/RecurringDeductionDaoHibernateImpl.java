package omis.financial.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.financial.dao.RecurringDeductionDao;
import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;
import omis.offender.domain.Offender;

/**
 * Recurring deduction dao implementation
 * 
 *@author Yidong Li
 *@version 0.1.0 (May 10, 2017)
 *@since OMIS 3.0
 *
 */

public class RecurringDeductionDaoHibernateImpl 
	extends GenericHibernateDaoImpl<RecurringDeduction> 
	implements RecurringDeductionDao {
	/* Query Names */
	private static final String FIND_RECURRING_DEDUCTION_QUERY_NAME 
		= "findRecurringDeduction";
	private static final String FIND_RECURRING_DEDUCTION_EXCLUDING_QUERY_NAME 
		= "findRecurringDeductionExcluding";
	private static final String FIND_ALL_RECURRING_DEDUCTIONS_QUERY_NAME 
	= "findAllRecurringDeductions";

	/* Parameter names */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String CATEGORY_PARAM_NAME = "category";
	private static final String DESCRIPTION_PARAM_NAME = "description";
	private static final String REPORTED_DATE_PARAM_NAME = "reportedDate";
	private static final String AMOUNT_PARAM_NAME = "amount";
	private static final String FREQUENCY_PARAM_NAME = "frequency";
	private static final String EXCLUDED_RECURRING_DEDUCTION_PARAM_NAME 
		= "excludedRecurringDeduction";
	
	/* Constructors */
	
	public RecurringDeductionDaoHibernateImpl (
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public RecurringDeduction find(final Offender offender, 
			final RecurringDeductionCategory category, 
			final String description, final Date reportedDate, 
			final BigDecimal amount, 
			final RecurringDeductionFrequency frequency) {
		RecurringDeduction recurringDeduction = 
				(RecurringDeduction) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RECURRING_DEDUCTION_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(REPORTED_DATE_PARAM_NAME, reportedDate)
				.setParameter(AMOUNT_PARAM_NAME, amount)
				.setParameter(FREQUENCY_PARAM_NAME, frequency)
				.uniqueResult();
		return recurringDeduction;
	}

	/**{@inheritDoc} */
	@Override
	public RecurringDeduction findExcluding(
			final RecurringDeductionCategory category, 
			final String description, final Date reportedDate, 
			final BigDecimal amount, final RecurringDeductionFrequency frequency, 
			final RecurringDeduction excludedRecurringDeduction) {
		Offender offender=excludedRecurringDeduction.getOffender();
		RecurringDeduction recurringDeduction = 
				(RecurringDeduction) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_RECURRING_DEDUCTION_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(REPORTED_DATE_PARAM_NAME, reportedDate)
				.setParameter(AMOUNT_PARAM_NAME, amount)
				.setParameter(FREQUENCY_PARAM_NAME, frequency)
				.setParameter(EXCLUDED_RECURRING_DEDUCTION_PARAM_NAME, 
					excludedRecurringDeduction)
				.uniqueResult();
		return recurringDeduction;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<RecurringDeduction> findAll(final Offender offender){
		@SuppressWarnings("unchecked")
		List<RecurringDeduction> recurringDeductions = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ALL_RECURRING_DEDUCTIONS_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		return recurringDeductions;
			
	};
}