package omis.offenderflag.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.offenderflag.report.OffenderFlagCategorySummary;
import omis.offenderflag.report.OffenderFlagCategorySummaryReportService;

/**
 * Offender flag category summary report service hibernate implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 31, 2017)
 * @since OMIS 3.0
 */
public class OffenderFlagCategorySummaryReportServiceHibernateImpl 
	implements OffenderFlagCategorySummaryReportService {
	
	private static final String FIND_OFFENDER_FLAG_CATEGORY_SUMMARIES 
		= "findOffenderFlagCategorySummaries";
	
	private final SessionFactory sessionFactory;	

	/** Instantiates an implementation of offender flag category summary report
	 * service hibernate impl */
	public OffenderFlagCategorySummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderFlagCategorySummary> 
		findAllOffenderFlagCategorySummaries() {
		@SuppressWarnings("unchecked")
		List<OffenderFlagCategorySummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_OFFENDER_FLAG_CATEGORY_SUMMARIES)
				.list();
		
		return summaries;
	}
}