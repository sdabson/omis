package omis.adaaccommodation.report.impl.hibernate;

import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.report.AccommodationReportService;
import omis.adaaccommodation.report.AccommodationSummary;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of the accommodation report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 24, 2015)
 * @since OMIS 3.0
 */
public class AccommodationReportServiceHibernateImpl 
	implements AccommodationReportService {

	/* Queries. */
		
	private static final String SUMMARIZE_ACCOMMODATION_QUERY_NAME
		= "summarizeAccommodations";
	
	private static final String FIND_BY_OFFENDER_ACCOMMODATION 
		= "findByOffenderAccommodation";
	
	private static final String COUNT_ISSUANCES_BY_ACCOMMODATION
		= "countIssuancesByAccommodation";
	
	/* Parameters. */
	
	private static final String ACCOMMODATION_PARAMETER_NAME = "accommodation";
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	/* Memebers. */
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public AccommodationReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationSummary summarize(Accommodation accommodation) {		
		AccommodationSummary accommodationSummary = (AccommodationSummary) 
				this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_ACCOMMODATION_QUERY_NAME)
				.setParameter(ACCOMMODATION_PARAMETER_NAME, accommodation)
				.uniqueResult();
		return accommodationSummary;
	}

	/** {@inheritDoc} */
	@Override
	public List<AccommodationSummary> findByOffenderAccommodation(Offender offender) {
		@SuppressWarnings("unchecked")
		List<AccommodationSummary> accommodationSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_ACCOMMODATION)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.list();
		return accommodationSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean hasIssuances(Accommodation accommodation) {
		final Boolean query = (Boolean) this.sessionFactory.getCurrentSession()
		.getNamedQuery(COUNT_ISSUANCES_BY_ACCOMMODATION)
		.setParameter(ACCOMMODATION_PARAMETER_NAME, accommodation)
		.uniqueResult();
		return query;
	}
}