package omis.staff.report.impl.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.organization.domain.OrganizationDivision;
import omis.search.util.PersonRegexUtility;
import omis.staff.report.StaffSearchResult;
import omis.staff.report.StaffSearchSummary;
import omis.staff.report.StaffSearchSummaryReportService;
import omis.supervision.domain.SupervisoryOrganization;

/** Implementation of staff person summary report service.
 * @author Ryan Johns
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 16, 2014)
 * @since OMIS 3.0 */
public class StaffSearchSummaryReportServiceHibernateImpl
		implements StaffSearchSummaryReportService {
	private final SessionFactory sessionFactory;


	/* Names queries */
	private static String FIND_STAFF_SUMMARY_BY_ID_QUERY_NAME =
			"findStaffSummaryById";
	private static String FIND_STAFF_SUMMARY_BY_FIRST_LAST_QUERY_NAME =
			"findStaffSummaryByFirstLast";
	private static String FIND_STAFF_SUMMARY_BY_FULL_NAME_QUERY_NAME =
			"findStaffSummaryByFirstMiddleLast";
	private static String FIND_STAFF_SUMMARY_BY_LAST_NAME_QUERY_NAME =
			"findStaffSummaryByLast";
	private static String FIND_STAFF_SUMMARY_BY_USERNAME_QUERY_NAME =
			"findStaffSummaryByUsername";

	/* Parameters */
	private static String DATE_PARAM_NAME = "date";
	private static String FIRST_NAME_PARAM_NAME = "first";
	private static String ID_PARAM_NAME = "id";
	private static String LAST_NAME_PARAM_NAME = "last";
	private static String MIDDLE_NAME_PARAM_NAME = "middle";
	private static String NAME1_PARAM_NAME = "name1";
	private static String NAME2_PARAM_NAME = "name2";
	private static String USERNAME_PARAM_NAME = "username";

	/** Constructor.
	 * @param sessionFactory session factory. */
	public StaffSearchSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public StaffSearchResult findById(final Long id) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_STAFF_SUMMARY_BY_ID_QUERY_NAME);

		return (StaffSearchResult) q.setLong(ID_PARAM_NAME, id).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public List<StaffSearchResult> findPersonNamesByUnspecified(
			final String searchCriteria, final Date date) {
		final List<StaffSearchResult> result;

		if (PersonRegexUtility.isFirstLast(searchCriteria)) {
				final String[] firstLast = searchCriteria.split("[\\s,]+");

				result = this.findPersonNamesByNameSearch(firstLast[0],
							firstLast[1], date);
				} else if (PersonRegexUtility.isFirstMiddleLast(
						searchCriteria)) {
					final String[] firstMiddleLast =
							searchCriteria.split("[\\s,]+");

					result = this.findPersonNamesByNameSearch(
							firstMiddleLast[0], firstMiddleLast[1],
							firstMiddleLast[2], date);
				} else if (PersonRegexUtility.isName(searchCriteria)) {
					result = this.findPersonNamesByLastName(searchCriteria, 
							date);
				} else if (PersonRegexUtility.isUserName(searchCriteria)) {
					result = new ArrayList<StaffSearchResult>();
					result.add(this.findPersonNameByUsername(searchCriteria, 
							date));
				}else {
					result = null;
				}

		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<StaffSearchResult> findPersonNamesByNameSearch(
			final String name1, final String name2, final Date date) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_STAFF_SUMMARY_BY_FIRST_LAST_QUERY_NAME);

		@SuppressWarnings("unchecked")
		final List<StaffSearchResult> results = (List<StaffSearchResult>)
			q.setParameter(NAME1_PARAM_NAME, name1).setParameter(
					NAME2_PARAM_NAME, name2)
			.setTimestamp(DATE_PARAM_NAME, date).list();

		return results;
	}

	/** {@inheritDoc} */
	@Override
	public List<StaffSearchResult> findPersonNamesByNameSearch(
			final String first, final String middle, final String last,
			final Date date) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_STAFF_SUMMARY_BY_FULL_NAME_QUERY_NAME);

		@SuppressWarnings("unchecked")
		final List<StaffSearchResult> results = (List<StaffSearchResult>)
			q.setParameter(FIRST_NAME_PARAM_NAME, first).setParameter(
					MIDDLE_NAME_PARAM_NAME, middle).setParameter(
							LAST_NAME_PARAM_NAME, last).setTimestamp(
									DATE_PARAM_NAME, date).list();
		return results;
	}

	/** {@inheritDoc} */
	@Override
	public List<StaffSearchResult> findPersonNamesByLastName(final String name,
			final Date date) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_STAFF_SUMMARY_BY_LAST_NAME_QUERY_NAME);

		@SuppressWarnings("unchecked")
		final List<StaffSearchResult> results = (List<StaffSearchResult>)
			q.setParameter(LAST_NAME_PARAM_NAME, name).setTimestamp(
					DATE_PARAM_NAME, date).list();

		return results;
	}
	
	/** {@inheritDoc} ) */
	@Override
	public StaffSearchResult findPersonNameByUsername(final String username, 
			final Date date) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_STAFF_SUMMARY_BY_USERNAME_QUERY_NAME);
		
		q.setString(USERNAME_PARAM_NAME, username)
		.setTimestamp(DATE_PARAM_NAME, date);
		
		return (StaffSearchResult) q.uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public List<StaffSearchSummary> search(
			final String firstName, 
			final String lastName, final SupervisoryOrganization organization,
			final OrganizationDivision division) {
		@SuppressWarnings("unchecked")
		List<StaffSearchSummary> staffSearchSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery("searchingForStaff")
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.setParameter("organization", organization)
				.setParameter("division", division)
				.list();
		
		return staffSearchSummaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findAllSupervisoryOrganizations() {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> organizations = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery("findAllSupervisoryOrganizations")
				.list();
		return organizations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OrganizationDivision> findAllOrganizationDivisions() {
		@SuppressWarnings("unchecked")
		List<OrganizationDivision> divisions = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery("findAllOrganizationDivisions")
				.list();
		return divisions;
	}
}