package omis.military.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.military.dao.MilitaryServiceTermNoteDao;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.domain.MilitaryServiceTermNote;

import org.hibernate.SessionFactory;

public class MilitaryServiceTermNoteDaoHibernateImpl
extends GenericHibernateDaoImpl<MilitaryServiceTermNote> 
implements MilitaryServiceTermNoteDao{

	/* Query names. */
	
	private static final String FIND_SERVICE_TERM_NOTE_QUERY_NAME
		= "findMilitaryServiceTermNote";
	
	private static final String FIND_SERVICE_TERM_NOTE_EXCLUDING_QUERY_NAME
		= "findMilitaryServiceTermNoteExcluding";
	
	private static final String 
	FIND_SERVICE_TERM_NOTES_BY_SERVICE_TERM_QUERY_NAME
		= "findServiceTermNotesByServiceTerm";
	
	/* Parameter names. */
	
	private static final String NOTE_PARAM_NAME = "note";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String SERVICE_TERM_NOTE_PARAM_NAME 
		= "serviceTermNote";
	
	private static final String SERVICER_TERM_PARAM_NAME = "serviceTerm";
	
	/**
	 * Instantiates a military service term note data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MilitaryServiceTermNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTermNote find(final MilitaryServiceTerm serviceTerm,
			final String note, final Date date) {
		MilitaryServiceTermNote serviceTermNote = (MilitaryServiceTermNote) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_SERVICE_TERM_NOTE_QUERY_NAME)
				.setParameter(SERVICER_TERM_PARAM_NAME, serviceTerm)
				.setParameter(NOTE_PARAM_NAME, note)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return serviceTermNote;
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryServiceTermNote findExcluding(
			final MilitaryServiceTermNote serviceTermNote,
			final MilitaryServiceTerm serviceTerm, final String note, 
			final Date date) {
		MilitaryServiceTermNote militaryServiceTermNote = 
				(MilitaryServiceTermNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SERVICE_TERM_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(NOTE_PARAM_NAME, note)
				.setParameter(SERVICER_TERM_PARAM_NAME, serviceTerm)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(SERVICE_TERM_NOTE_PARAM_NAME, serviceTermNote)
				.uniqueResult();
		return militaryServiceTermNote;
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTermNote> findByServiceTerm(
			MilitaryServiceTerm serviceTerm) {
		@SuppressWarnings("unchecked")
		List<MilitaryServiceTermNote> serviceTermNotes = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_SERVICE_TERM_NOTES_BY_SERVICE_TERM_QUERY_NAME)
				.setParameter(SERVICER_TERM_PARAM_NAME, serviceTerm)
				.list();
		return serviceTermNotes;
	}
}