package omis.incident.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.incident.dao.InvolvedPartyDao;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.InvolvedParty;
import omis.person.domain.Person;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Incident report data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.1 (Oct 2, 2015)
 * @since OMIS 3.0
 */
public class InvolvedPartyDaoHibernateImpl 
	extends GenericHibernateDaoImpl<InvolvedParty>	
	implements InvolvedPartyDao {

	/* Query names. */
	private static final String FIND_INVOLVED_PARTY_QUERY_NAME
		= "findInvolvedParty";
	private static final String FIND_EXCLUDING_INVOLVED_PARTY_QUERY_NAME
		= "findInvolvedPartyExcluding";
	private static final String FIND_INVOLVED_PARTIES_QUERY_NAME 
		= "findInvolvedParties";
	
	/* Parameter names. */
	private static final String STATEMENT_PARAMETER_NAME = "statement";
	private static final String PERSON_PARAMETER_NAME = "person";
	private static final String INCIDENT_STATEMENT_PARAMETER_NAME 
		= "incidentStatement";
	private static final String INVOLVED_PARTY_PARAMETER_NAME = "involvedParty";
	
	/**
	 * Instantiates an instance of involved party data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InvolvedPartyDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public InvolvedParty find(IncidentStatement report, Person person) {
		InvolvedParty involvedParty = (InvolvedParty) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_INVOLVED_PARTY_QUERY_NAME)
				.setParameter(STATEMENT_PARAMETER_NAME, report)
				.setParameter(PERSON_PARAMETER_NAME, person)
				.uniqueResult();
		return involvedParty;
	}
	
	/** {@inheritDoc} */
	@Override
	public InvolvedParty findExcluding(final IncidentStatement statement, 
		final Person person, final InvolvedParty involvedParty){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EXCLUDING_INVOLVED_PARTY_QUERY_NAME)
			.setParameter(STATEMENT_PARAMETER_NAME, statement)
			.setParameter(PERSON_PARAMETER_NAME, person)
			.setParameter(INVOLVED_PARTY_PARAMETER_NAME, involvedParty);
		return (InvolvedParty) q.uniqueResult() ; 
	}
	
	/** {@inheritDoc} */
	@Override
	public List<InvolvedParty> findInvolvedParties(
			final IncidentStatement statement){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_INVOLVED_PARTIES_QUERY_NAME)
			.setParameter(INCIDENT_STATEMENT_PARAMETER_NAME, statement);
			@SuppressWarnings("unchecked")
		List<InvolvedParty> involvedParties = (List<InvolvedParty>)q.list();
		return involvedParties; 
	}
}