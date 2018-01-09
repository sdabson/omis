package omis.adaaccommodation.report.impl.hibernate;

import java.util.List;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.report.AccommodationIssuanceReportService;
import omis.adaaccommodation.report.AccommodationIssuanceSummary;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of the accommodation issuance report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 17, 2015)
 * @since OMIS 3.0
 */
public class AccommodationIssuanceReportServiceHibernateImpl 
	implements AccommodationIssuanceReportService {

	/* Queries. */
	private static final String 
		FIND_ACCOMMODATION_ISSUANCES_BY_ACCOMMODATION_QUERY_NAME
		= "findIssuancesByAccommodation";
	
	/* Parameters. */
	private static final String ACCOMMODATION_PARAMETER_NAME = "accommodation";
	
	/* Memebers. */
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 * 
	 * @param sessionFactory session factory
	 */
	public AccommodationIssuanceReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public  List<AccommodationIssuanceSummary> findByAccommodation(
			final Accommodation accommodation) {
		@SuppressWarnings("unchecked")
		List<AccommodationIssuanceSummary> issuanceSummaries
			= this.sessionFactory.getCurrentSession()
			.getNamedQuery(
					FIND_ACCOMMODATION_ISSUANCES_BY_ACCOMMODATION_QUERY_NAME)
			.setParameter(ACCOMMODATION_PARAMETER_NAME, accommodation)
			.list();
		return issuanceSummaries;
	}
}