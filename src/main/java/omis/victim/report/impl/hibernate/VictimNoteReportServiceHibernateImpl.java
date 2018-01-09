package omis.victim.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.person.domain.Person;
import omis.victim.report.VictimNoteReportService;
import omis.victim.report.VictimNoteSummary;

/**
 * Hibernate implementation of report service for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 24, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteReportServiceHibernateImpl
		implements VictimNoteReportService {
	
	/* Query names. */
	
	private final String SUMMARIZE_BY_VICTIM_QUERY_NAME
		= "summarizeVictimNotesByVictim";
	
	/* Parameter names. */
	
	private final String VICTIM_PARAM_NAME = "victim";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for victim notes.
	 * 
	 * @param sessionFactory session factory
	 */
	public VictimNoteReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<VictimNoteSummary> summarizeByVictim(final Person victim) {
		@SuppressWarnings("unchecked")
		List<VictimNoteSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						SUMMARIZE_BY_VICTIM_QUERY_NAME)
				.setParameter(VICTIM_PARAM_NAME, victim).list();
		return summaries;
	}
}