package omis.offender.report.impl.hibernate;

import java.util.Date;

import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.report.OffenderSummary;
import omis.person.domain.Person;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of report service for offenders.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 19, 2013)
 * @since OMIS 3.0
 */
public class OffenderReportServiceHibernateImpl
		implements OffenderReportService {

	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates an implementation of report service for offenders with the
	 * specified resources.
	 * 
	 * @param sessionFactory session factory
	 */
	public OffenderReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Returns the session factory.
	 * 
	 * @return session factory
	 */
	protected final SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public final OffenderSummary summarizeOffender(
			final Offender offender) {
		OffenderSummary report =
				(OffenderSummary) this.getSessionFactory()
					.getCurrentSession().getNamedQuery("summarizeOffender")
					.setParameter("offender", offender).uniqueResult();
		return report;
	}
	
	/** {@inheritDoc} */
	@Override
	public final OffenderSummary summarizeIfOffender(
			final Person person) {
		OffenderSummary report =
				(OffenderSummary) this.getSessionFactory()
					.getCurrentSession().getNamedQuery("summarizeOffender")
					.setParameter("offender", person).uniqueResult();
		return report;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderSummary summarizeOffender(final Offender offender,
			final Date date) {
		
		// TODO Implement or remove - SA
		throw new UnsupportedOperationException("Not yet implemented");
	}
}