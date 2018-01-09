package omis.incident.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.incident.dao.IncidentStatementNoteDao;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.IncidentStatementNote;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Incident report note data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.0 (June 28, 2015)
 * @since OMIS 3.0
 */
public class IncidentStatementNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<IncidentStatementNote>	
	implements IncidentStatementNoteDao {

	/* Query names. */
	private static final String
	FIND_INCIDENT_STATEMENT_NOTE_EXCLUDING_QUERY_NAME
		= "findIncidentStatementNoteExcluding";
	private static final String FIND_INCIDENT_STATEMENT_NOTE_QUERY_NAME
		= "findIncidentStatementNote";
	private static final String FIND_INCIDENT_STATEMENT_NOTES_NAME 
		= "findIncidentStatementNotes";
	
	/* Parameter names. */
	private static final String STATEMENT_PARAMETER_NAME = "statement";
	private static final String NOTE_PARAMETER_NAME = "note";
	private static final String DATE_PARAMETER_NAME = "date";
	private static final String INCIDENT_STATEMENT_PARAMETER_NAME 
		= "statement";
	private static final String INCIDENT_STATEMENT_NOTE_PARAM_NAME
		= "statementNote";
		
	/**
	 * Instantiates an instance of incident report note data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public IncidentStatementNoteDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatementNote findIncidentStatementNote(
		final IncidentStatement statement, final String note, final Date date){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_INCIDENT_STATEMENT_NOTE_QUERY_NAME)
			.setParameter(STATEMENT_PARAMETER_NAME, statement)
			.setParameter(NOTE_PARAMETER_NAME, note)
			.setParameter(DATE_PARAMETER_NAME, date);
		return (IncidentStatementNote) q.uniqueResult() ; 
	}
	
	/** {@inheritDoc} */
	@Override
	public IncidentStatementNote findIncidentStatementNoteExcluding(
		final IncidentStatementNote incidentReportNote, 
		final String note, final Date date){
		IncidentStatement report= incidentReportNote.getStatement();
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_INCIDENT_STATEMENT_NOTE_EXCLUDING_QUERY_NAME)
			.setParameter(INCIDENT_STATEMENT_PARAMETER_NAME, report)
			.setParameter(NOTE_PARAMETER_NAME, note)
			.setParameter(DATE_PARAMETER_NAME, date)
			.setParameter(INCIDENT_STATEMENT_NOTE_PARAM_NAME, incidentReportNote);
		return (IncidentStatementNote) q.uniqueResult() ; 
	}
	
	/** {@inheritDoc} */
	@Override
	public List<IncidentStatementNote> findIncidentStatementNotes(
		final IncidentStatement statement){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_INCIDENT_STATEMENT_NOTES_NAME)
			.setParameter(STATEMENT_PARAMETER_NAME, statement);
			@SuppressWarnings("unchecked")
			List<IncidentStatementNote> incidentStatementNotes 
				= (List<IncidentStatementNote>)q.list();
			return incidentStatementNotes; 
	}
}