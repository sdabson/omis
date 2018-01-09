package omis.victim.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.person.domain.Person;
import omis.victim.report.VictimReportService;
import omis.victim.report.VictimSummary;

/**
 * Hibernate implementation of victim report service.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 30, 2015)
 * @since OMIS 3.0
 */
public class VictimReportServiceHibernateImpl
		implements VictimReportService {
	
	/* Queries. */
	
	private static final String SUMMARIZE_BY_NAME_QUERY_NAME
		= "summarizeVictimByName";
	
	private static final String SUMMARIZE_VICTIM_QUERY_NAME
		= "summarizeVictim";
	
	/* Parameter names. */
	
	private static final String LAST_NAME_PARAM_NAME = "lastName";
	
	private static final String FIRST_NAME_PARAM_NAME = "firstName";

	private static final String VICTIM_MODEL_KEY = "victim";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of victim report service.
	 * 
	 * @param sessionFactory session factory
	 */
	public VictimReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<VictimSummary> findByName(
			final String lastName, final String firstName) {
		@SuppressWarnings("unchecked")
		List<VictimSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(SUMMARIZE_BY_NAME_QUERY_NAME)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public VictimSummary summarizeVictim(final Person victim) {
		VictimSummary victimSummary = (VictimSummary) this.sessionFactory
				.getCurrentSession().getNamedQuery(SUMMARIZE_VICTIM_QUERY_NAME)
				.setParameter(VICTIM_MODEL_KEY, victim).uniqueResult();
		return victimSummary;
	}
}