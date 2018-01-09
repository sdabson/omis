package omis.identificationnumber.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.identificationnumber.report.IdentificationNumberSummary;
import omis.identificationnumber.report.IdentificationNumberSummaryItemReportService;
import omis.offender.domain.Offender;

/**
 * Identification Number Summary Item Report Service Hibernate Implementation.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 2, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberSummaryItemReportServiceHibernateImpl
		implements IdentificationNumberSummaryItemReportService {
	
	private static final String FIND_SUMMARIES_BY_OFFENDER_QUERY_NAME =
			"findIdentificationNumberSummariesWithSingleMultitudeByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory - Session Factory
	 */
	public IdentificationNumberSummaryItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public List<IdentificationNumberSummary>
		findSummariesByOffender(final Offender offender) {
		
		@SuppressWarnings("unchecked")
		List<IdentificationNumberSummary> summaries = this.sessionFactory
			.getCurrentSession().getNamedQuery(
					FIND_SUMMARIES_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		
		return summaries;
	}

}
