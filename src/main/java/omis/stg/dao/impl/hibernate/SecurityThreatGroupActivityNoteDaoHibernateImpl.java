package omis.stg.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.stg.dao.SecurityThreatGroupActivityNoteDao;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityNote;

/**
 * Hibernate implementation of data access object for security threat group
 * activity note.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityNoteDaoHibernateImpl 
		extends GenericHibernateDaoImpl<SecurityThreatGroupActivityNote> 
		implements SecurityThreatGroupActivityNoteDao {

	/* Query names. */
	
	private static final String FIND_NOTES_QUERY_NAME = "findActivityNotes";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findExcludingActivityNote";
	
	private static final String FIND_QUERY_NAME = "findActivityNote";
	
	/* Parameter names. */
	
	private static final String EXCLUDED_NOTE_PARAM_NAME = "excludedNote";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String ACTIVITY_PARAM_NAME = "activity";
	
	/* Constructor. */
	
	/**
	 * Instantiates a hibernate implementation of data access object for
	 * security threat group activity note.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupActivityNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivityNote> findNotes(
			final SecurityThreatGroupActivity activity) {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupActivityNote> notes 
		= this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_NOTES_QUERY_NAME)
			.setParameter(ACTIVITY_PARAM_NAME, activity).list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityNote findExcluding(
			final SecurityThreatGroupActivityNote excludedNote,
			final Date date,
			final String value) {
		SecurityThreatGroupActivityNote note 
		= (SecurityThreatGroupActivityNote) 
			getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
			.setParameter(EXCLUDED_NOTE_PARAM_NAME, excludedNote)
			.setParameter(DATE_PARAM_NAME, date)
			.setParameter(VALUE_PARAM_NAME, value)
			.uniqueResult();
		return note;
	}
	
	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivityNote findNote(
			final SecurityThreatGroupActivity activity,
			final Date date,
			final String value) {
		SecurityThreatGroupActivityNote note 
		= (SecurityThreatGroupActivityNote) 
			getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_QUERY_NAME)
			.setParameter(ACTIVITY_PARAM_NAME, activity)
			.setParameter(DATE_PARAM_NAME, date)
			.setParameter(VALUE_PARAM_NAME, value)
			.uniqueResult();
		return note;
	}
}
