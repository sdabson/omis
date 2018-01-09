package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.EducationSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.EducationSectionSummaryNote;

/**
 * EducationSectionSummaryNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationSectionSummaryNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<EducationSectionSummaryNote>
		implements EducationSectionSummaryNoteDao {
	
	private static final String FIND_EDUCATION_SECTION_SUMMARY_NOTE_QUERY_NAME =
			"findEducationSectionSummaryNote";
	
	private static final String FIND_EDUCATION_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME =
			"findEducationSectionSummaryNoteExcluding";
	
	private static final String FIND_BY_EDUCATION_SECTION_SUMMARY_QUERY_NAME =
			"findByEducationSectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String EDUCATION_SECTION_SUMMARY_PARAM_NAME =
			"educationSectionSummary";
	
	private static final String EDUCATION_SECTION_SUMMARY_NOTE_PARAM_NAME =
			"educationSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummaryNote find(final String description, 
			final Date date, final EducationSectionSummary educationSectionSummary) {
		EducationSectionSummaryNote educationSectionSummaryNote = 
				(EducationSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(EDUCATION_SECTION_SUMMARY_PARAM_NAME,
						educationSectionSummary)
				.uniqueResult();
		
		return educationSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummaryNote findExcluding(final String description,
			final Date date, final EducationSectionSummary educationSectionSummary,
			final EducationSectionSummaryNote educationSectionSummaryNoteExcluding) {
		EducationSectionSummaryNote educationSectionSummaryNote = 
				(EducationSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_EDUCATION_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(EDUCATION_SECTION_SUMMARY_PARAM_NAME,
						educationSectionSummary)
				.setParameter(EDUCATION_SECTION_SUMMARY_NOTE_PARAM_NAME,
						educationSectionSummaryNoteExcluding)
				.uniqueResult();
		
		return educationSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationSectionSummaryNote> findByEducationSectionSummary(
			final EducationSectionSummary educationSectionSummary) {
		
		@SuppressWarnings("unchecked")
		List<EducationSectionSummaryNote> educationSectionSummaryNotes =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_EDUCATION_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(EDUCATION_SECTION_SUMMARY_PARAM_NAME,
						educationSectionSummary)
				.list();
		
		return educationSectionSummaryNotes;
	}

}
