package omis.courtcase.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtcase.dao.CourtCaseNoteDao;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.CourtCaseNote;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of data access object for court case notes.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 24, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CourtCaseNote> 
	implements CourtCaseNoteDao {

	/* Query names. */
	
	private static final String FIND_BY_COURT_CASE_QUERY_NAME
		= "findCourtCaseNotesByCourtCase";
	
	private static final String FIND_COURT_CASE_NOTE_QUERY_NAME
		= "findCourtCaseNote";
	
	private static final String FIND_COURT_CASE_NOTE_EXCLUDING_QUERY_NAME
		= "findCourtCaseNoteExcluding";

	/* Parameter names. */
	
	private static final String COURT_CASE_PARAM_NAME = "courtCase";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String EXCLUDED_NOTE_PARAM_NAME = "excludedNote";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for court
	 * case notes with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	protected CourtCaseNoteDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<CourtCaseNote> findByCourtCase(CourtCase courtCase) {
		@SuppressWarnings("unchecked")
		List<CourtCaseNote> courtCaseNotes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_COURT_CASE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase).list();
		return courtCaseNotes;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCaseNote findCourtCaseNote(CourtCase courtCase, Date date, 
			String value) {
		CourtCaseNote courtCaseNote = (CourtCaseNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_COURT_CASE_NOTE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(VALUE_PARAM_NAME, value).uniqueResult();
		return courtCaseNote;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCaseNote findCourtCaseNoteExcluding(CourtCase courtCase, 
			Date date, String value, CourtCaseNote excludedNote) {
		CourtCaseNote courtCaseNote = (CourtCaseNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_COURT_CASE_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(EXCLUDED_NOTE_PARAM_NAME, excludedNote)
				.uniqueResult();
		return courtCaseNote;
	}

}
