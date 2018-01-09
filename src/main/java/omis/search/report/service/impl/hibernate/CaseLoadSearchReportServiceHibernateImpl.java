package omis.search.report.service.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.search.report.CaseLoadSearchResult;
import omis.search.report.service.CaseLoadSearchReportService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Hibernate implementation of case load search report service.
 * @author Ryan Johns (Nov 19, 2013)
 * @since OMIS 3.0 */
public class CaseLoadSearchReportServiceHibernateImpl
		implements CaseLoadSearchReportService {

	private static final String FIND_CASELOAD_REPORT_BY_TITLE =
			"findCaseLoadReportByTitle";

	private static final String FIND_CASELOAD_REPORT_BY_DESCRIPTION =
			"findCaseLoadReportByDescription";

	private static final String FIND_CASELOAD_REPORT_BY_OFFENDER =
			"findCaseLoadReportByOffender";

	private static final String FIND_CASELOAD_REPORT_BY_CASEWORKER =
			"findCaseLoadReportByCaseWorker";

	private final SessionFactory sessionFactory;

	/** constructor.
	 * @param sessionFactory session factory. */
	public CaseLoadSearchReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseLoadSearchResult> findByTitle(final String title) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_CASELOAD_REPORT_BY_TITLE);

		q.setParameter("title", title);

		@SuppressWarnings("unchecked")
		final List<CaseLoadSearchResult> results =
				(List<CaseLoadSearchResult>) q.list();

		return results;
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseLoadSearchResult> findByDescription(
			final String description) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_CASELOAD_REPORT_BY_DESCRIPTION);

		q.setParameter("description", description);

		@SuppressWarnings("unchecked")
		final List<CaseLoadSearchResult> results =
				(List<CaseLoadSearchResult>) q.list();

		return results;
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseLoadSearchResult> findByOffender(final Offender offender) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_CASELOAD_REPORT_BY_OFFENDER);

		q.setParameter("offender", offender);
		q.setDate("date", new Date());

		@SuppressWarnings("unchecked")
		final List<CaseLoadSearchResult> results =
				(List<CaseLoadSearchResult>) q.list();

		return results;
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseLoadSearchResult> findByCaseWorker(final Person person) {
		final Query q = this.sessionFactory.getCurrentSession().getNamedQuery(
				FIND_CASELOAD_REPORT_BY_CASEWORKER);

		q.setParameter("person",  person);

		@SuppressWarnings("unchecked")
		final List<CaseLoadSearchResult> results =
			(List<CaseLoadSearchResult>) q.list();

		return results;
	}
}
