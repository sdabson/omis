package omis.incident.dao.impl.hibernate;

import java.util.Date;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.incident.dao.IncidentStatementDao;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.component.IncidentScene;
import omis.person.domain.Person;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Incident report data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.0 (June 28, 2015)
 * @since OMIS 3.0
 */
public class IncidentStatementDaoHibernateImpl 
	extends GenericHibernateDaoImpl<IncidentStatement>	
	implements IncidentStatementDao {

	/* Query names. */
	private static final String FIND_INCIDENT_STATEMENT_QUERY_NAME
		= "findIncidentStatement";
	private static final String FIND_INCIDENT_STATEMENT_EXCLUDING_QUERY_NAME
		= "findIncidentStatementExcluding";
	private static final String GET_NEXT_INCIDENT_STATEMENT_NUMBER_QUERY_NAME
		= "getNextIncidentStatementNumber";
	
	/* Parameter names. */
	private static final String REPORTER_PARAMETER_NAME = "reporter";
	private static final String STATEMENT_DATE_PARAMETER_NAME = "statementDate";
	private static final String INCIDENT_DATE_PARAMETER_NAME = "incidentDate";
	private static final String SUMMARY_PARAMETER_NAME = "summary";
	private static final String SCENE_PARAMETER_NAME = "scene";
	private static final String STATEMENT_PARAMETER_NAME = "statement";
	
	/**
	 * Instantiates an instance of incident report data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public IncidentStatementDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatement find(final Person reporter, 
		final Date statementDate, final Date incidentDate, final String summary, 
		final IncidentScene scene){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_INCIDENT_STATEMENT_QUERY_NAME)
			.setParameter(REPORTER_PARAMETER_NAME, reporter)
			.setParameter(STATEMENT_DATE_PARAMETER_NAME, statementDate)
			.setParameter(INCIDENT_DATE_PARAMETER_NAME, incidentDate)
			.setParameter(SUMMARY_PARAMETER_NAME, summary)
			.setParameter(SCENE_PARAMETER_NAME, scene);
		return (IncidentStatement) q.uniqueResult() ; 
	}
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatement findExcluding(final IncidentStatement statement,
		final Person reporter, final Date reportDate, final Date incidentDate, 
		final String summary, final IncidentScene scene){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_INCIDENT_STATEMENT_EXCLUDING_QUERY_NAME)
			.setParameter(STATEMENT_PARAMETER_NAME, statement)
			.setParameter(REPORTER_PARAMETER_NAME, reporter)
			.setParameter(STATEMENT_DATE_PARAMETER_NAME, reportDate)
			.setParameter(INCIDENT_DATE_PARAMETER_NAME, incidentDate)
			.setParameter(SUMMARY_PARAMETER_NAME, summary)
			.setParameter(SCENE_PARAMETER_NAME, scene);
		return (IncidentStatement) q.uniqueResult() ; 		
	}

	/** {@inheritDoc} */
	@Override
	public String generateReportNumber() {
		String sequenceNumber = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(GET_NEXT_INCIDENT_STATEMENT_NUMBER_QUERY_NAME)
				.uniqueResult().toString();
		return sequenceNumber;
	}
}

