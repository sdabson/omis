package omis.detainernotification.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.DetainerNoteDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerNote;

/**
 * Detainer note data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 16, 2017)
 * @since OMIS 3.0
 */
public class DetainerNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<DetainerNote>
	implements DetainerNoteDao {
	
	/* Query names */

	private static final String FIND_NOTES_BY_DETAINER_QUERY_NAME
		= "findDetainerNotesByDetainer";
	
	private static final String FIND_NOTE_QUERY_NAME
		= "findDetainerNote";
	
	private static final String FIND_NOTE_EXCLUDING_QUERY_NAME
		= "findDetainerNoteExcluding";
	
	/* Parameter names */
	
	private static final String DETAINER_PARAM_NAME = "detainer";
	private static final String VALUE_PARAM_NAME = "value";
	private static final String DATE_PARAM_NAME = "date";
	private static final String DETAINER_NOTE_PARAM_NAME = "detainerNote";

	/**
	 * Instantiates a detainer note data access object  with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public DetainerNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<DetainerNote> findNotesByDetainer(final Detainer detainer) {
		@SuppressWarnings("unchecked")
		List<DetainerNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_NOTES_BY_DETAINER_QUERY_NAME)
				.setParameter(DETAINER_PARAM_NAME, detainer)
				.list();
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public DetainerNote find(final Detainer detainer, final String value,
			final Date date) {
		DetainerNote note = (DetainerNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_NOTE_QUERY_NAME)
				.setParameter(DETAINER_PARAM_NAME, detainer)
				.setParameter(VALUE_PARAM_NAME, value)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return note;
	}

	/** {@inheritDoc} */
	@Override
	public DetainerNote findExcluding(final Detainer detainer,
			final String value, final Date date, final DetainerNote note) {
		DetainerNote detainerNote = (DetainerNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(DETAINER_PARAM_NAME, detainer)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(DETAINER_NOTE_PARAM_NAME, note)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return detainerNote;
	}
}