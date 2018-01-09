package omis.courtcase.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtcase.report.ChargeSummary;
import omis.courtcase.report.ChargeSummaryReportService;
import omis.person.domain.Person;

/**
 * Implementation of report service for charge summary.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 11, 2017)
 * @since OMIS 3.0
 */
public class ChargeSummaryReportServiceImpl 
	implements ChargeSummaryReportService {

	/* Query names. */
	
	private static final String SUMMARIZE_PENDING_CHARGES_BY_PERSON_QUERY_NAME
		= "summarizePendingChargesByPerson";
	
	/* Parameters. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Instantiates an instance of court case summary report service with the
	 * specified session factory.
	 * @param sessionFactory - session factory.
	 */
	ChargeSummaryReportServiceImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<ChargeSummary> summarizePendingChargesByOffender(
			final Person person) {
		@SuppressWarnings("unchecked")
		List<ChargeSummary> charges = (List<ChargeSummary>) this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_PENDING_CHARGES_BY_PERSON_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.list();
		return charges;
	}
}