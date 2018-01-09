package omis.supervisionfee.report.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.report.SupervisionFeeRequirementSummary;
import omis.supervisionfee.report.SupervisionFeeSummary;
import omis.supervisionfee.report.SupervisionFeeSummaryReportService;

/**
 * Hibernate implementation of the supervision fee report service.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 8, 2014)
 * @since OMIS 3.0
 */
public class SupervisionFeeSummaryReportServiceHibernateImpl 
				implements SupervisionFeeSummaryReportService {

	/* Queries. */
	
	private static final String 
					FIND_SUPERVISION_FEE_REQUIREMENTS_BY_SUPERVISION_FEE
					= "findSupervisionFeeRequirementsBySupervisionFee";

	private static final String FIND_SUPERVISION_FEES_BY
					= "findSupervisionFeesBy";
	
	/* Parameters. */

	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String MONTHLY_SUPERVISION_FEE 
					= "monthlySupervisionFee";
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	/* Memebers. */

	private final SessionFactory sessionFactory;

	/**
	 * Constructor.
	 * @param sessionFactory session factory
	 */
	public SupervisionFeeSummaryReportServiceHibernateImpl(
					final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeSummary> findByOffender(final Offender offender,
			final Date effectiveDate) {
		List<MonthlySupervisionFee> supervisionFees;		
		List<SupervisionFeeSummary> supervisionFeeSummaries 
						= new ArrayList<SupervisionFeeSummary>();		
		List<SupervisionFeeRequirementSummary> feeRequirements 
							= new ArrayList<SupervisionFeeRequirementSummary>();
		supervisionFees = this.findSupervisionFeesBy(offender);		
		for (MonthlySupervisionFee fee : supervisionFees) {	
			feeRequirements = this
							.findSupervisionFeeRequirementsBySupervisionFee(
							fee, effectiveDate);
			supervisionFeeSummaries.add(
							new SupervisionFeeSummary(fee, feeRequirements, 
									effectiveDate));
		}	
		return supervisionFeeSummaries;
	}	

	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeRequirementSummary> 
					findSupervisionFeeRequirementsBySupervisionFee(
					final MonthlySupervisionFee monthlySupervisionFee,
					final Date effectiveDate) {
		final Query query = this.sessionFactory.getCurrentSession()
						.getNamedQuery(
						FIND_SUPERVISION_FEE_REQUIREMENTS_BY_SUPERVISION_FEE);
		query.setParameter(MONTHLY_SUPERVISION_FEE, monthlySupervisionFee);
		query.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		@SuppressWarnings("unchecked")
		List<SupervisionFeeRequirementSummary> list = query.list();
		return list;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<MonthlySupervisionFee> findSupervisionFeesBy(
					final Offender offender) {
		final Query query = this.sessionFactory.getCurrentSession()
						.getNamedQuery(FIND_SUPERVISION_FEES_BY);
		query.setParameter(OFFENDER_PARAM_NAME, offender);
		@SuppressWarnings("unchecked")
		List<MonthlySupervisionFee> list = query.list();
		return list;
	}
}
