package omis.violationevent.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.violationevent.dao.ViolationEventNoteDao;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventNote;

/**
 * ViolationEventNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventNoteDaoHibernateImpl extends GenericHibernateDaoImpl<ViolationEventNote>
		implements ViolationEventNoteDao {
	
	
	private static final String FIND_VIOLATION_EVENT_NOTE_QUERY_NAME =
			"findViolationEventNote";
	
	private static final String FIND_VIOLATION_EVENT_NOTE_EXCLUDING_QUERY_NAME =
			"findViolationEventNoteExcluding";
	
	private static final String
		FIND_VIOLATION_EVENT_NOTES_BY_VIOLATION_EVENT_QUERY_NAME =
			"findViolationEventNotesByViolationEvent";
	
	
	
	
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String VIOLATION_EVENT_PARAM_NAME = "violationEvent";
	
	private static final String VIOLATION_EVENT_NOTE_PARAM_NAME =
			"violationEventNote";
	
	
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ViolationEventNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventNote find(final Date date, final String description,
			final ViolationEvent violationEvent) {
		ViolationEventNote violationEventNote = (ViolationEventNote)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VIOLATION_EVENT_NOTE_QUERY_NAME)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
				.uniqueResult();
		
		return violationEventNote;
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEventNote findExcluding(
			final ViolationEventNote excludedViolationEventNote,
			final Date date, final String description,
			final ViolationEvent violationEvent) {
		ViolationEventNote violationEventNote = (ViolationEventNote)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VIOLATION_EVENT_NOTE_EXCLUDING_QUERY_NAME)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
				.setParameter(VIOLATION_EVENT_NOTE_PARAM_NAME,
						excludedViolationEventNote)
				.uniqueResult();
		
		return violationEventNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<ViolationEventNote> findByViolationEvent(
			final ViolationEvent violationEvent) {
		@SuppressWarnings("unchecked")
		List<ViolationEventNote> violationEventNotes = 
			this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_VIOLATION_EVENT_NOTES_BY_VIOLATION_EVENT_QUERY_NAME)
			.setParameter(VIOLATION_EVENT_PARAM_NAME, violationEvent)
			.list();
		
		return violationEventNotes;
	}

}
