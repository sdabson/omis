package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PsychologicalSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryNote;

/**
 * PsychologicalSectionSummaryNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<PsychologicalSectionSummaryNote> implements PsychologicalSectionSummaryNoteDao {

	private static final String
			FIND_PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_QUERY_NAME =
					"findPsychologicalSectionSummaryNote";
	
	private static final String
			FIND_PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME =
					"findPsychologicalSectionSummaryNoteExcluding";
	
	private static final String
			FIND_NOTES_BY_PSYCHOLOGICAL_SECTION_SUMMARY_QUERY_NAME =
					"findNotesByPsychologicalSectionSummary";
	
	private static final String DESCRIPTION_MODEL_KEY = "description";
	
	private static final String DATE_MODEL_KEY = "date";
	
	private static final String PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY =
			"psychologicalSectionSummary";
	
	private static final String PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_MODEL_KEY =
			"psychologicalSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PsychologicalSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryNote find(final String description,
			final Date date,
			final PsychologicalSectionSummary psychologicalSectionSummary) {
		PsychologicalSectionSummaryNote note = (PsychologicalSectionSummaryNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(DESCRIPTION_MODEL_KEY, description)
				.setTimestamp(DATE_MODEL_KEY, date)
				.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
						psychologicalSectionSummary)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryNote findExcluding(
			final String description, final Date date,
			final PsychologicalSectionSummary psychologicalSectionSummary,
			final PsychologicalSectionSummaryNote
					psychologicalSectionSummaryNoteExcluded) {
		PsychologicalSectionSummaryNote note = (PsychologicalSectionSummaryNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
					FIND_PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_MODEL_KEY, description)
				.setTimestamp(DATE_MODEL_KEY, date)
				.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
						psychologicalSectionSummary)
				.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_MODEL_KEY,
						psychologicalSectionSummaryNoteExcluded)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public List<PsychologicalSectionSummaryNote> findByPsychologicalSectionSummary(
			final PsychologicalSectionSummary psychologicalSectionSummary) {
		@SuppressWarnings("unchecked")
		List<PsychologicalSectionSummaryNote> notes = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_NOTES_BY_PSYCHOLOGICAL_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
						psychologicalSectionSummary)
				.list();
		
		return notes;
	}

}
