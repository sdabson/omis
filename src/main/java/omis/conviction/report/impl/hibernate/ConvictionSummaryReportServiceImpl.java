package omis.conviction.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.conviction.report.ConvictionSummary;
import omis.conviction.report.ConvictionSummaryReportService;
import omis.person.domain.Person;

/**
 * Conviction summary report service implementation.
 * @author Josh Divine
 * @version 0.1.0 (May 1, 2017)
 * @since OMIS 3.0
 */
public class ConvictionSummaryReportServiceImpl 
	implements ConvictionSummaryReportService {

	/* Query names. */
	
	private static final String SUMMARIZE_CONVICTIONS_BY_PERSON_QUERY_NAME
		= "summarizeConvictionsByPerson";

	/* Parameters. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Instantiates an instance of conviction summary report service with the
	 * specified session factory.
	 * @param sessionFactory session factory.
	 */
	public ConvictionSummaryReportServiceImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ConvictionSummary> summarizeByPerson(Person person) {
		@SuppressWarnings("unchecked")
		List<ConvictionSummary> convictions = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(SUMMARIZE_CONVICTIONS_BY_PERSON_QUERY_NAME)
			.setParameter(PERSON_PARAM_NAME, person)
			.list();
		return convictions;
	}

}
