package omis.hearing.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearing.dao.HearingNoteDao;
import omis.hearing.domain.Hearing;
import omis.hearing.domain.HearingNote;

/**
 * HearingNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 27, 2016)
 *@since OMIS 3.0
 *
 */
public class HearingNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<HearingNote> implements HearingNoteDao {
	
	/* Query Names */
	
	private static final String FIND_HEARING_NOTE_QUERY_NAME = "findHearingNote";
	
	private static final String FIND_HEARING_NOTE_EXCLUDING_QUERY_NAME =
			"findHearingNoteExcluding";
	
	private static final String FIND_HEARING_NOTES_BY_HEARING_QUERY_NAME =
			"findHearingNotesByHearing";
	
	/* Param Names */
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String HEARING_PARAM_NAME = "hearing";
	
	private static final String HEARING_NOTE_PARAM_NAME = "hearingNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected HearingNoteDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	
	/**{@inheritDoc} */
	@Override
	public HearingNote find(final Hearing hearing, final String description,
			final Date date) {
		HearingNote hearingNote = (HearingNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARING_NOTE_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return hearingNote;
	}

	/**{@inheritDoc} */
	@Override
	public HearingNote findExcluding(final Hearing hearing,
			final String description, final Date date,
			final HearingNote hearingNote) {
		HearingNote hearingNoteExcluding =
				(HearingNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_HEARING_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(HEARING_NOTE_PARAM_NAME, hearingNote)
				.uniqueResult();
		return hearingNoteExcluding;
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingNote> findByHearing(final Hearing hearing) {
		@SuppressWarnings("unchecked")
		List<HearingNote> hearingNotes = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_HEARING_NOTES_BY_HEARING_QUERY_NAME)
			.setParameter(HEARING_PARAM_NAME, hearing)
			.list();
		return hearingNotes;
	}

}
