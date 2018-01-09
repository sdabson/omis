package omis.residence.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.region.domain.City;
import omis.region.domain.State;
import omis.residence.report.ResidenceSearchSummary;
import omis.residence.report.ResidenceSearchSummaryReportService;

/**
 * Implementation of residence search summary service.
 *
 * @author Sheronda Vaughn
 * @author Joel Norris
 * @version 0.1.1 (July 11, 2017)
 * @since OMIS 3.0
 */

public class ResidenceSearchSummaryReportServiceHibernateImpl 
	implements ResidenceSearchSummaryReportService {
	
	private final SessionFactory sessionFactory;
	
	/* Parameter names. */
	
	private final static String VALUE_PARAM_NAME = "value";
	private final static String CITY_PARAM_NAME = "city";
	private final static String STATE_PARAM_NAME = "state";
	private final static String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	/* Query names. */
	
	private final static String RESIDENCE_SEARCH_QUERY_NAME = "searchingForResidence";
	private final static String FIND_RESIDENCE_CITIES_BY_STATE_QUERY_NAME = "findResidenceCitiesByState";
	private final static String FIND_RESIDENCE_STATES_QUERY_NAME = "findAllResidenceStates";

	/**
	 * Constructor.
	 */
	public ResidenceSearchSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<ResidenceSearchSummary> findResidenceSearchSummary(
			final String value, final State state,
			final City city, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ResidenceSearchSummary> residenceSearchSummaries 
			= this.sessionFactory
		.getCurrentSession()
		.getNamedQuery(RESIDENCE_SEARCH_QUERY_NAME)
		.setParameter(VALUE_PARAM_NAME, value)
		.setParameter(STATE_PARAM_NAME, state)
		.setParameter(CITY_PARAM_NAME, city)
		.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
		.list();
		return residenceSearchSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		@SuppressWarnings("unchecked")
		List<City> cities = this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_RESIDENCE_CITIES_BY_STATE_QUERY_NAME)
				.setParameter(STATE_PARAM_NAME, state)
				.list();
		return cities;
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findAllStates() {
		@SuppressWarnings("unchecked")
		List<State> states = this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_RESIDENCE_STATES_QUERY_NAME)
				.list();
		return states;
	}
}