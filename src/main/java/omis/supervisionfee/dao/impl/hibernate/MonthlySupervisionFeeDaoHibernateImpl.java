package omis.supervisionfee.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.supervisionfee.dao.MonthlySupervisionFeeDao;
import omis.supervisionfee.domain.MonthlySupervisionFee;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of the monthly supervision fee data access object.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 15, 2014)
 * @since  OMIS 3.0
 */
public class MonthlySupervisionFeeDaoHibernateImpl 
				extends GenericHibernateDaoImpl<MonthlySupervisionFee>
				implements MonthlySupervisionFeeDao {

	/* Query names. */
	private static final String FIND_BY_DATE_RANGE_QUERY_NAME
					= "findMonthlySupervisionFeeByDateRange";
	private static final String FIND_BY_DATE_RANGE_EXCLUDING_QUERY_NAME
					= "findMonthlySupervisionFeeByDateRangeExcluding";
	
	/* Parameter names. */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String START_DATE_PARAM_NAME = "startDate";
	private static final String END_DATE_PARAM_NAME = "endDate";
	private static final String MONTHLY_SUPERVISION_FEE_PARAM_NAME
					= "monthlySupervisionFee";

	/**
	 * Instantiates an hibernate implementation of the data access object for 
	 * monthly supervision fees with the specified resources.
	 * 
	 * @param sessionFactory session factory	
	 * @param entityName entity name
	 */
	public MonthlySupervisionFeeDaoHibernateImpl(
					final SessionFactory sessionFactory, 
					final String entityName) {
	super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public List <MonthlySupervisionFee> findByDateRange(final Offender offender,
					final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		final List <MonthlySupervisionFee> monthlySupervisionFees = 
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(FIND_BY_DATE_RANGE_QUERY_NAME)
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.setDate(START_DATE_PARAM_NAME, startDate)
						.setDate(END_DATE_PARAM_NAME, endDate).list();
		return monthlySupervisionFees;
	}	
	
	/**{@inheritDoc} */
	@Override
	public List <MonthlySupervisionFee> findByDateRangeExcluding(
					final Offender offender, final Date startDate, 
					final Date endDate, 
					final MonthlySupervisionFee monthlySupervisionFee) {
		@SuppressWarnings("unchecked")
		final List <MonthlySupervisionFee> supervisionFees =
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(FIND_BY_DATE_RANGE_EXCLUDING_QUERY_NAME)
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.setParameter(MONTHLY_SUPERVISION_FEE_PARAM_NAME, 
								monthlySupervisionFee)
						.setDate(START_DATE_PARAM_NAME, startDate)
						.setDate(END_DATE_PARAM_NAME, endDate).list();
		return supervisionFees;
	}
}