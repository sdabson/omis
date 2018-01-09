package omis.specialneed.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.specialneed.dao.SpecialNeedNoteDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedNote;

/**
 * Hibernate implementation of the special need note data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 31, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SpecialNeedNote> 
	implements SpecialNeedNoteDao {
	
	/* Queries. */
	private static final String FIND_SPECIAL_NEED_NOTES_QUERY_NAME 
		= "findSpecialNeedNotes";
	
	private static final String FIND_SPECIAL_NEED_NOTES_EXCLUDING_QUERY_NAME 
	= "findSpecialNeedNotesExcluding";
	
	private static final String 
		FIND_SPECIAL_NEED_NOTES_BY_SPECIAL_NEED_QUERY_NAME 
		= "findSpecialNeedNotesBySpecialNeed";
	
	/* Parameters. */
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String SPECIAL_NEED_PARAM_NAME = "specialNeed";
	
	private static final String SPECIAL_NEED_NOTE_PARAM_NAME 
		= "specialNeedNote";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  special need note.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SpecialNeedNoteDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName); 
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedNote find(final String value, final Date date, 
			final SpecialNeed specialNeed) {
		SpecialNeedNote note = (SpecialNeedNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SPECIAL_NEED_NOTES_QUERY_NAME)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(SPECIAL_NEED_PARAM_NAME, specialNeed)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedNote findExcluding(final String value, final Date date, 
			final SpecialNeed specialNeed, 
			final SpecialNeedNote specialNeedNote) {
		SpecialNeedNote note = (SpecialNeedNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SPECIAL_NEED_NOTES_EXCLUDING_QUERY_NAME)
				.setParameter(VALUE_PARAM_NAME, value)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(SPECIAL_NEED_PARAM_NAME, specialNeed)
				.setParameter(SPECIAL_NEED_NOTE_PARAM_NAME, specialNeedNote)
				.uniqueResult();
		
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedNote> findBySpecialNeed(SpecialNeed specialNeed) {
		@SuppressWarnings("unchecked")
		List<SpecialNeedNote> notes = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_SPECIAL_NEED_NOTES_BY_SPECIAL_NEED_QUERY_NAME)
				.setParameter(SPECIAL_NEED_PARAM_NAME, specialNeed)
				.list();
				
		return notes;
	}	
}