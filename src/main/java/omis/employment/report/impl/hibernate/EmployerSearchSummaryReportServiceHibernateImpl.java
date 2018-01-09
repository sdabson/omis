package omis.employment.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.employment.report.EmployerSearchSummary;
import omis.employment.report.EmployerSearchSummaryReportService;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Implementation of employer summary report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 4, 2016)
 * @since OMIS 3.0
 */
public class EmployerSearchSummaryReportServiceHibernateImpl 
	implements EmployerSearchSummaryReportService {

	private final SessionFactory sessionFactory;

	/** Constructor. */
	public EmployerSearchSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		@SuppressWarnings("unchecked")
		List<City> cities = this.sessionFactory.getCurrentSession()
				.getNamedQuery("findEmployerCitiesByState")
				.setParameter("state", state)
				.list();
		return cities;
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findAllStates() {
		@SuppressWarnings("unchecked")
		List<State> states = this.sessionFactory.getCurrentSession()
				.getNamedQuery("findAllStates")
				.list();
		return states;
	}

	/** {@inheritDoc} */
	@Override
	public List<EmployerSearchSummary> search(
			final String employerLocationOrganizationName, final City city, 
			final State state, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<EmployerSearchSummary> employerSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery("searchForEmployer")
				.setParameter("employerLocationOrganizationName", 
						employerLocationOrganizationName)
				.setParameter("city", city)
				.setParameter("state", state)
				.setDate("effectiveDate", effectiveDate)
				.list();
		
		return employerSummaries;
	}
}