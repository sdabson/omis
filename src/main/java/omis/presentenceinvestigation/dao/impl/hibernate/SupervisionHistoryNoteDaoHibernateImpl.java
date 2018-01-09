package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.SupervisionHistoryNoteDao;
import omis.presentenceinvestigation.domain.SupervisionHistoryNote;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Hibernate implementation of supervision history note data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 10, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistoryNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SupervisionHistoryNote>
	implements SupervisionHistoryNoteDao {
	
	private static final String 
	FIND_SUPERVISION_HISTORY_NOTE_QUERY_NAME = "findSupervisionHistoryNote";
	
	private static final String 
	FIND_EXCLUDING_SUPERVISION_HISTORY_NOTE_QUERY_NAME 
		= "findExcludingSupervisionHistoryNote";
	
	private static final String FIND_SUPERVISION_HISTORY_NOTES_QUERY_NAME 
		= "findSupervisionHistoryNotes";
	
	private static final String SUPERVISION_HISTORY_NOTE_PARAM_NAME 
		= "supervisionHistoryNote";
	
	private static final String SUPERVISION_HISTORY_SECTION_SUMMARY_PARAM_NAME 
		= "supervisionHistorySectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	

	/** Instantiates an implementation of supervision history
	 * note dao hibernate impl */
	public SupervisionHistoryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public SupervisionHistoryNote find(
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary,
			final String description, final Date date) {
		SupervisionHistoryNote note = (SupervisionHistoryNote) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SUPERVISION_HISTORY_NOTE_QUERY_NAME)
				.setParameter(SUPERVISION_HISTORY_SECTION_SUMMARY_PARAM_NAME, 
						supervisionHistorySectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)				
				.setParameter(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
			
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistoryNote findExcluding(
			final SupervisionHistoryNote supervisionHistoryNote,
			final SupervisionHistorySectionSummary 
				supervisionHistorySectionSummary, 
			final String description, final Date date) {
		SupervisionHistoryNote note = (SupervisionHistoryNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_EXCLUDING_SUPERVISION_HISTORY_NOTE_QUERY_NAME)				
				.setParameter(SUPERVISION_HISTORY_NOTE_PARAM_NAME, 
						supervisionHistoryNote)
				.setParameter(SUPERVISION_HISTORY_SECTION_SUMMARY_PARAM_NAME, 
						supervisionHistorySectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
				
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisionHistoryNote> 
	findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary) {
		@SuppressWarnings("unchecked")
		List<SupervisionHistoryNote> notes = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_SUPERVISION_HISTORY_NOTES_QUERY_NAME)
				.setParameter(SUPERVISION_HISTORY_SECTION_SUMMARY_PARAM_NAME, 
						supervisionHistorySectionSummary)
				.list();
		return notes;
	}
}