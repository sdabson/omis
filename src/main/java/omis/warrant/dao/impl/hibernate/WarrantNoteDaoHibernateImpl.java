package omis.warrant.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.warrant.dao.WarrantNoteDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantNote;

/**
 * WarrantNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantNote> implements WarrantNoteDao {
	
	private static final String FIND_WARRANT_NOTE_QUERY_NAME =
			"findWarrantNote";
	
	private static final String FIND_WARRANT_NOTE_EXCLUDING_QUERY_NAME =
			"findWarrantNoteExcluding";
	
	private static final String FIND_WARRANT_NOTES_BY_WARRANT_QUERY_NAME =
			"findWarrantNotesByWarrant";
	
	private static final String NOTE_PARAM_NAME = "note";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String WARRANT_NOTE_PARAM_NAME = "warrantNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WarrantNoteDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantNote find(final String note, final Date date,
			final Warrant warrant) {
		WarrantNote warrantNote = (WarrantNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_NOTE_QUERY_NAME)
				.setParameter(NOTE_PARAM_NAME, note)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.uniqueResult();
		
		return warrantNote;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantNote findExcluding(final String note, final Date date,
			final Warrant warrant, final WarrantNote warrantNoteExcluding) {
		WarrantNote warrantNote = (WarrantNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(NOTE_PARAM_NAME, note)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(WARRANT_NOTE_PARAM_NAME, warrantNoteExcluding)
				.uniqueResult();
		
		return warrantNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<WarrantNote> findByWarrant(final Warrant warrant) {
		@SuppressWarnings("unchecked")
		List<WarrantNote> warrantNotes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_NOTES_BY_WARRANT_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.list();
		
		return warrantNotes;
	}

}
