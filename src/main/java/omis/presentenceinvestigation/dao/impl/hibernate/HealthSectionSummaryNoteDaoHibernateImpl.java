package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.HealthSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;

/**
 * Health section summary note data access object hibernate implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 10, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<HealthSectionSummaryNote>
	implements HealthSectionSummaryNoteDao {
	
	private static final String FIND_HEATLH_SECTION_SUMMARY_NOTE_QUERY_NAME
		= "findHealthSectionSummaryNote";

	private static final String 
	FIND_HEATLH_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME
		= "findHealthSectionSummaryNoteExcluding";
	
	private static final String FIND_NOTES_BY_HEALTH_SECTION_SUMMARY_QUERY_NAME
		= "findHealthSectionSummaryNotesByHealthSectionSummary";
	
	private static final String HEALTH_SECTION_SUMMARY_PARAM_NAME
		= "healthSectionSummary";
	
	private static final String HEALTH_SECTION_SUMMARY_NOTE_PARAM_NAME
		= "healthSectionSummaryNote";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/**
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HealthSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummaryNote find(
			final HealthSectionSummary healthSectionSummary, 
			final String description, final Date date) {
		HealthSectionSummaryNote summaryNote = (HealthSectionSummaryNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_HEATLH_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(HEALTH_SECTION_SUMMARY_PARAM_NAME, 
						healthSectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DATE_PARAM_NAME, date)
				.uniqueResult();
		return summaryNote;
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummaryNote findExcluding(
			final HealthSectionSummaryNote healthSectionSummaryNote,
			final HealthSectionSummary healthSectionSummary, 
			final String description, final Date date) {
		HealthSectionSummaryNote summaryNote = (HealthSectionSummaryNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_HEATLH_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(HEALTH_SECTION_SUMMARY_NOTE_PARAM_NAME, 
						healthSectionSummaryNote)
				.setParameter(HEALTH_SECTION_SUMMARY_PARAM_NAME, 
						healthSectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(DATE_PARAM_NAME, date)
				.uniqueResult();
		return summaryNote;
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthSectionSummaryNote> 
	findHealthSectionSummaryNotesByHealthSectionSummary(
			final HealthSectionSummary healthSectionSummary) {
		@SuppressWarnings("unchecked")
		List<HealthSectionSummaryNote> notes = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_NOTES_BY_HEALTH_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(HEALTH_SECTION_SUMMARY_PARAM_NAME, 
						healthSectionSummary)
				.list();
		
		return notes;
	}
}