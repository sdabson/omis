package omis.supervisionfee.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.supervisionfee.dao.SupervisionFeeRequirementDao;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.SupervisionFeeRequirement;

import org.hibernate.SessionFactory;



/**
 * Hibernate implentation of the supervision fee requirement data access object.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 15, 2014)
 * @since  OMIS 3.0
 */
public class SupervisionFeeRequirementDaoHibernateImpl 
				extends GenericHibernateDaoImpl<SupervisionFeeRequirement>
				implements SupervisionFeeRequirementDao {

	/* Query names. */
	private static final String 
					FIND_BY_MONTHLY_SUPERVISION_FEE_QUERY_NAME
							= "findSupervisionFeeRequirementsByMonthly"
									+ "SupervisionFee";
	private static final String FIND_BY_DATE_RANGE_EXCLUDING_QUERY_NAME
					= "findSupervisionFeeRequirementByDateRangeExcluding";
	private static final String FIND_BY_DATE_RANGE_QUERY_NAME = 
						"findSupervisionFeeRequirementByDateRange";	
	private static final String 
				FIND_WITH_IN_MONTHLY_SUPERVISION_FEE_DATE_RANGE_QUERY_NAME 
					= "findSupervisionFeeRequirementWithin"
							+ "MonthlySupervisionFeeDateRange";
	
	/* Parameter names. */
	
	private static final String MONTHLY_SUPERVISION_FEE_PARAM_NAME
					= "monthlySupervisionFee";
	private static final String START_DATE_PARAM_NAME = "startDate";
	private static final String END_DATE_PARAM_NAME = "endDate";
	private static final String SUPERVISION_FEE_REQUIREMENT_PARAM_NAME
					= "feeRequirement";

	/**
	 * Instantiates an hibernate implementation of the data access object for 
	 * supervision fee requirements with the specified resources.
	 * 
	 * @param sessionFactory session factory	
	 * @param entityName entity name
	 */
	public SupervisionFeeRequirementDaoHibernateImpl(
					final SessionFactory sessionFactory, 
					final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeRequirement> findByMonthlySupervisionFee(
					final MonthlySupervisionFee monthlySupervisionFee) {
		@SuppressWarnings("unchecked")
		List<SupervisionFeeRequirement> feeRequirements = 
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
						FIND_BY_MONTHLY_SUPERVISION_FEE_QUERY_NAME)
						.setParameter(MONTHLY_SUPERVISION_FEE_PARAM_NAME, 
						monthlySupervisionFee).list();
		return feeRequirements;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeRequirement> findByDateRangeExcluding(
					final Date startDate, final Date endDate,
					final MonthlySupervisionFee monthlySupervisionFee,
					final SupervisionFeeRequirement feeRequirement) {
		@SuppressWarnings("unchecked")
		final List <SupervisionFeeRequirement> feeRequirements =
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
						FIND_BY_DATE_RANGE_EXCLUDING_QUERY_NAME)
						.setParameter(MONTHLY_SUPERVISION_FEE_PARAM_NAME, 
								monthlySupervisionFee)
						.setParameter(SUPERVISION_FEE_REQUIREMENT_PARAM_NAME, 
								feeRequirement)
						.setDate(START_DATE_PARAM_NAME, startDate)
						.setDate(END_DATE_PARAM_NAME, endDate).list();
		return feeRequirements;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeRequirement> findByDateRange(
					final Date startDate, final Date endDate,
					final MonthlySupervisionFee monthlySupervisionFee) {
		@SuppressWarnings("unchecked")
		final List <SupervisionFeeRequirement> feeRequirements =
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
						FIND_BY_DATE_RANGE_QUERY_NAME)
						.setParameter(MONTHLY_SUPERVISION_FEE_PARAM_NAME, 
								monthlySupervisionFee)
						.setDate(START_DATE_PARAM_NAME, startDate)
						.setDate(END_DATE_PARAM_NAME, endDate).list();
		return feeRequirements;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeRequirement> 
					findWithinMonthlySupervisionFeeDateRange(
							final Date startDate, final Date endDate,
							final MonthlySupervisionFee monthlySupervisionFee) {
		@SuppressWarnings("unchecked")
		final List <SupervisionFeeRequirement> feeRequirements =
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
				FIND_WITH_IN_MONTHLY_SUPERVISION_FEE_DATE_RANGE_QUERY_NAME)
						.setParameter(MONTHLY_SUPERVISION_FEE_PARAM_NAME, 
								monthlySupervisionFee)
						.setDate(START_DATE_PARAM_NAME, startDate)
						.setDate(END_DATE_PARAM_NAME, endDate).list();
		return feeRequirements;
	}	
}