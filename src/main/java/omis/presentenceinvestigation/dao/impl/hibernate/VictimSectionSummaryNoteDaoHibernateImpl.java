package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.VictimSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryNote;

/**
 * VictimSectionSummaryNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<VictimSectionSummaryNote>
		implements VictimSectionSummaryNoteDao {
	
	private static final String FIND_VICTIM_SECTION_SUMMARY_NOTE_QUERY_NAME =
			"findVictimSectionSummaryNote";
	
	private static final String
			FIND_VICTIM_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME =
				"findVictimSectionSummaryNoteExcluding";
	
	private static final String
		FIND_VICTIM_SECTION_SUMMARY_NOTES_BY_VICTIM_SECTION_SUMMARY_QUERY_NAME =
			"findVictimSectionSummaryNotesByVictimSectionSummary";
	
	private static final String VICTIM_SECTION_SUMMARY_PARAM_NAME =
			"victimSectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String VICTIM_SECTION_SUMMARY_NOTE_PARAM_NAME =
			"victimSectionSummaryNote";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected VictimSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummaryNote find(
			final VictimSectionSummary victimSectionSummary,
			final String description, final Date date) {
		VictimSectionSummaryNote victimSectionSummaryNote =
				(VictimSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VICTIM_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(VICTIM_SECTION_SUMMARY_PARAM_NAME,
						victimSectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		
		return victimSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummaryNote findExcluding(
			final VictimSectionSummary victimSectionSummary,
			final String description, final Date date,
			final VictimSectionSummaryNote victimSectionSummaryNoteExcluded) {
		VictimSectionSummaryNote victimSectionSummaryNote =
				(VictimSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_VICTIM_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(VICTIM_SECTION_SUMMARY_PARAM_NAME,
						victimSectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(VICTIM_SECTION_SUMMARY_NOTE_PARAM_NAME,
						victimSectionSummaryNoteExcluded)
				.uniqueResult();
		
		return victimSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<VictimSectionSummaryNote> findByVictimSectionSummary(
			final VictimSectionSummary victimSectionSummary) {
		@SuppressWarnings("unchecked")
		List<VictimSectionSummaryNote> victimSectionSummaryNotes =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
		FIND_VICTIM_SECTION_SUMMARY_NOTES_BY_VICTIM_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(VICTIM_SECTION_SUMMARY_PARAM_NAME,
						victimSectionSummary)
				.list();
		
		return victimSectionSummaryNotes;
	}

}
