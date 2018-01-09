package omis.residence.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.residence.report.ResidenceSummary;
import omis.residence.report.ResidenceSummaryItemReportService;

/**
 * Residence Summary Item Report Service Hibernate Implementation.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 26, 2017)
 *@since OMIS 3.0
 *
 */
public class ResidenceSummaryItemReportServiceHibernateImpl
		implements ResidenceSummaryItemReportService {
	
	private static final String FIND_RESIDENCE_SUMMARY_QUERY_NAME =
			"findCurrentPrimaryResidentTermByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory - Session Factory
	 */
	public ResidenceSummaryItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public ResidenceSummary findResidenceSummaryByOffender(
			final Offender offender) {
		ResidenceSummary residenceSummary = (ResidenceSummary)
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_RESIDENCE_SUMMARY_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(EFFECTIVE_DATE_PARAM_NAME, new Date())
				.uniqueResult();
		
		return residenceSummary;
	}

}
