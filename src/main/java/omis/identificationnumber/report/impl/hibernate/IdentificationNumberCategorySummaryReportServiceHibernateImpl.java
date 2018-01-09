package omis.identificationnumber.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.identificationnumber.report.IdentificationNumberCategorySummary;
import omis.identificationnumber.report.IdentificationNumberCategorySummaryReportService;

/**
 * IdentificationNumberCategorySummaryReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 27, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberCategorySummaryReportServiceHibernateImpl
		implements IdentificationNumberCategorySummaryReportService {
	
	private static final String FIND_CATEGORY_SUMMARIES_QUERY_NAME =
			"summarizeIdentificationNumberCategories";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public IdentificationNumberCategorySummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<IdentificationNumberCategorySummary>
				findAllIdentificationNumberCategorySummaries() {
		@SuppressWarnings("unchecked")
		List<IdentificationNumberCategorySummary> summaries =
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_CATEGORY_SUMMARIES_QUERY_NAME)
				.list();
		
		return summaries;
	}

}
