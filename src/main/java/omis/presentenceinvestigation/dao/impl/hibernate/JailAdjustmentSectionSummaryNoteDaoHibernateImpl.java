package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.JailAdjustmentSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;

/**
 * JailAdjustmentSectionSummaryNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryNoteDaoHibernateImpl extends
		GenericHibernateDaoImpl<JailAdjustmentSectionSummaryNote>
		implements JailAdjustmentSectionSummaryNoteDao {
	
	private static final String FIND_JASS_NOTE_QUERY_NAME = 
			"findJailAdjustmentSectionSummaryNote";
	
	private static final String FIND_JASS_NOTE_EXCLUDING_QUERY_NAME = 
			"findJailAdjustmentSectionSummaryNoteExcluding";
	
	private static final String FIND_JASS_NOTES_BY_JASS_QUERY_NAME = 
			"findJailAdjustmentSectionSummaryNotesByJailAdjustmentSectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String JAIL_ADJUSTMENT_SECTION_SUMMARY_PARAM_NAME =
			"jailAdjustmentSectionSummary";
	
	private static final String JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_PARAM_NAME =
			"jailAdjustmentSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected JailAdjustmentSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}


	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummaryNote find(final String description,
			final Date date, final JailAdjustmentSectionSummary
					jailAdjustmentSectionSummary) {
		JailAdjustmentSectionSummaryNote note = 
				(JailAdjustmentSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_JASS_NOTE_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(JAIL_ADJUSTMENT_SECTION_SUMMARY_PARAM_NAME,
						jailAdjustmentSectionSummary)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummaryNote findExcluding(
			final String description, final Date date,
			final JailAdjustmentSectionSummary jailAdjustmentSectionSummary,
			final JailAdjustmentSectionSummaryNote
					jailAdjustmentSectionSummaryNoteExcluded) {
		JailAdjustmentSectionSummaryNote note = 
				(JailAdjustmentSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_JASS_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(JAIL_ADJUSTMENT_SECTION_SUMMARY_PARAM_NAME,
						jailAdjustmentSectionSummary)
				.setParameter(JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_PARAM_NAME,
						jailAdjustmentSectionSummaryNoteExcluded)
				.uniqueResult();
		
		return note;
	}


	/**{@inheritDoc} */
	@Override
	public List<JailAdjustmentSectionSummaryNote>
			findByJailAdjustmentSectionSummary(
					JailAdjustmentSectionSummary jailAdjustmentSectionSummary) {
		@SuppressWarnings("unchecked")
		List<JailAdjustmentSectionSummaryNote> notes =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_JASS_NOTES_BY_JASS_QUERY_NAME)
				.setParameter(JAIL_ADJUSTMENT_SECTION_SUMMARY_PARAM_NAME,
						jailAdjustmentSectionSummary)
				.list();
		
		return notes;
	}

}
