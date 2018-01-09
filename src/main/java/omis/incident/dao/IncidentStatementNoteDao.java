package omis.incident.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.incident.domain.IncidentStatement;
import omis.incident.domain.IncidentStatementNote;

/**
 * Data access object for incident report note.
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.0 (June 28, 2015)
 * @since OMIS 3.0
 */
public interface IncidentStatementNoteDao
	extends GenericDao<IncidentStatementNote> {
	/**
	 * Find corresponding incident statement note.
	 * @param statement incident statement
	 * @param note note
	 * @param date date
	 * @return incident statement note
	 */
	IncidentStatementNote findIncidentStatementNote(IncidentStatement statement, 
		String note, Date date);
	
	/**
	 * Find corresponding incident statement note.
	 * @param note note
	 * @param date date
	 * @param incidentStatementNote incident statement note
	 * @return same incident statement note
	 */
	IncidentStatementNote findIncidentStatementNoteExcluding(
		IncidentStatementNote incidentStatementNote, String note, Date date);
	
	/**
	 * Find corresponding incident statement note.
	 * @param statement incident statement
	 * @return list of incident statements
	 */
	List<IncidentStatementNote> findIncidentStatementNotes(
			IncidentStatement statement); 
}