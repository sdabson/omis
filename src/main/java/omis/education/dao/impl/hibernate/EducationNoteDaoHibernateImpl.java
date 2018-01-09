package omis.education.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.education.dao.EducationNoteDao;
import omis.education.domain.EducationNote;
import omis.education.domain.EducationTerm;

/**
 * EducationNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<EducationNote> implements EducationNoteDao {
	
	/* Query Names */
	
	private static final String FIND_EDUCATION_NOTE_QUERY_NAME 
		= "findEducationNote";
	
	private static final String FIND_EDUCATION_NOTES_BY_EDUCATION_TERM_QUERY_NAME 
			= "findEducationNotesByEducationTerm";
	
	private static final String FIND_EDUCATION_NOTE_EXCLUDING_QUERY_NAME 
		= "findEducationNoteExcluding";
	
	/* Parameter names */
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String EDUCATION_TERM_PARAM_NAME = "educationTerm";
	
	private static final String EDUCATION_NOTE_PARAM_NAME = "educationNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationNoteDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public EducationNote find(final String description, 
			final EducationTerm educationTerm, final Date date) {
		EducationNote educationNote
		= (EducationNote) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EDUCATION_NOTE_QUERY_NAME)
			.setParameter(DESCRIPTION_PARAM_NAME, description)
			.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm)
			.setParameter(DATE_PARAM_NAME, date)
			.uniqueResult();
		return educationNote;
	}

	/**{@inheritDoc} */
	@Override
	public EducationNote findExcluding(final String description, 
			final EducationTerm educationTerm, final Date date,
			EducationNote educationNote) {
		EducationNote foundEducationNote 
		= (EducationNote) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EDUCATION_NOTE_EXCLUDING_QUERY_NAME)
			.setParameter(DESCRIPTION_PARAM_NAME, description)
			.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm)
			.setParameter(DATE_PARAM_NAME, date)
			.setParameter(EDUCATION_NOTE_PARAM_NAME, educationNote)
			.uniqueResult();
		return foundEducationNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationNote> findByEducationTerm(
			final EducationTerm educationTerm) {
		@SuppressWarnings("unchecked")
		List<EducationNote> educationNotes 
			= this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_NOTES_BY_EDUCATION_TERM_QUERY_NAME)
				.setParameter(EDUCATION_TERM_PARAM_NAME, educationTerm).list();
		return educationNotes;
	}

}
