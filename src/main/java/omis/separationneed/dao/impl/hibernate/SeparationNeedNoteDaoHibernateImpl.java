package omis.separationneed.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.separationneed.dao.SeparationNeedNoteDao;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedNote;

import org.hibernate.SessionFactory;

/**
 * Separation need note data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 18, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<SeparationNeedNote>
	implements SeparationNeedNoteDao {

	/* Query names. */
	
	private static final String FIND_BY_SEPARATION_NEED_QUERY_NAME
		= "findBySeparationNeedNotesBySeparationNeed";
	
	private static final String FIND_SEPARATION_NEED_NOTE_QUERY_NAME
		= "findSeparationNeedNote";
	
	private static final String FIND_SEPARATION_NEED_NOTE_EXCLUDING_QUERY_NAME
	= "findSeparationNeedNoteExcluding";
	
	/* Parameter names. */
	
	private static final String SEPARATION_NEED_PARAM_NAME = "separationNeed";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String SEPARATION_NEED_NOTE_PARAM_NAME
		= "separationNeedNote";
	
	private static final String NOTE_PARAM_NAME = "note";
	
	/**
	 * Instantiates a separation need note note data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SeparationNeedNoteDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedNote> findBySeparationNeed(
			SeparationNeed separationNeed) {
		@SuppressWarnings("unchecked")
		List<SeparationNeedNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_SEPARATION_NEED_QUERY_NAME)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedNote find(String note, Date date,
			SeparationNeed separationNeed) {
		SeparationNeedNote sepNeedNote = 
				(SeparationNeedNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SEPARATION_NEED_NOTE_QUERY_NAME)
				.setParameter(NOTE_PARAM_NAME, note)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.uniqueResult();
		return sepNeedNote;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedNote findExcluding(String note, Date date,
			SeparationNeed separationNeed, SeparationNeedNote separationNeedNote) {
		SeparationNeedNote sepNeedNote = 
				(SeparationNeedNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SEPARATION_NEED_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(NOTE_PARAM_NAME, note)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.setParameter(SEPARATION_NEED_NOTE_PARAM_NAME,
						separationNeedNote)
				.uniqueResult();
		return sepNeedNote;
	}
}