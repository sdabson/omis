package omis.incident.report.impl.hibernate;

import java.util.List;

import omis.incident.report.JurisdictionSummary;
import omis.incident.report.JurisdictionSummaryService;

import org.hibernate.SessionFactory;

/**
 * Jurisdiction summary service hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 3, 2015)
 * @since OMIS 3.0
 */
public class JurisdictionSummaryServiceHibernateImpl
implements JurisdictionSummaryService {

	/* Queries */
	
	private final String SUMMARIZE_JURISDICTIONS_QUERY_NAME
		= "summarizeJurisdictions";
	
	/* Session factory. */
	
	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates a default instance of jurisdiction summary service.
	 */
	public JurisdictionSummaryServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<JurisdictionSummary> summarizeJurisdictions() {
		@SuppressWarnings("unchecked")
		List<JurisdictionSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_JURISDICTIONS_QUERY_NAME)
				.list();
		return summaries;
	}
}