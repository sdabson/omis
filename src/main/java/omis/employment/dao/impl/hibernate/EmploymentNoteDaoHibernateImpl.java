package omis.employment.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.employment.dao.EmploymentNoteDao;
import omis.employment.domain.EmploymentNote;
import omis.employment.domain.EmploymentTerm;

import org.hibernate.SessionFactory;

/**
 * Employment note data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Dec 13, 2017)
 * @since OMIS 3.0
 */
public class EmploymentNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<EmploymentNote>	
	implements EmploymentNoteDao {

	/* Query names. */
	private static final String FIND_QUERY_NAME = "findEmploymentNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findEmploymentNoteExluding";
	
	private static final String FIND_EMPLOYMENT_NOTES_QUERY_NAME = 
			"findEmploymentNotes";

	/* Parameter names. */
	private static final String TERM_PARAMETER_NAME = "term";
	
	private static final String DATE_PARAMETER_NAME = "date";
	
	private static final String VALUE_PARAMETER_NAME = "value";
	
	private static final String EXCLUDED_NOTE_PARAM_NAME = "excludedNote";
		
	/**
	 * Instantiates an instance of employment note data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EmploymentNoteDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public EmploymentNote find(final EmploymentTerm term, 
		final Date date, final String value) {
		EmploymentNote employmentNote = (EmploymentNote) this
				.getSessionFactory().getCurrentSession() 
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(TERM_PARAMETER_NAME, term)
				.setParameter(DATE_PARAMETER_NAME, date)
				.setParameter(VALUE_PARAMETER_NAME, value)
				.uniqueResult();
		return employmentNote;
	}
	
	/** {@inheritDoc} */
	@Override
	public EmploymentNote findExcluding(final EmploymentTerm term, 
			final Date date, final String value, 
			final EmploymentNote excludedNote) {
			EmploymentNote employmentNote = (EmploymentNote) this
					.getSessionFactory().getCurrentSession() 
					.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
					.setParameter(TERM_PARAMETER_NAME, term)
					.setParameter(DATE_PARAMETER_NAME, date)
					.setParameter(VALUE_PARAMETER_NAME, value)
					.setParameter(EXCLUDED_NOTE_PARAM_NAME, excludedNote)
					.uniqueResult();
			return employmentNote;
		}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmploymentNote> findNotes(final EmploymentTerm term) {
		List<EmploymentNote> employmentNote;
		employmentNote = (List<EmploymentNote>) getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EMPLOYMENT_NOTES_QUERY_NAME)
			.setParameter(TERM_PARAMETER_NAME, term);
		return employmentNote;
	}
}
