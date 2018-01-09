package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.OtherPertinentInformationSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummaryNote;

/**
 * OtherPertinentInformationSectionSummaryNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<OtherPertinentInformationSectionSummaryNote>
		implements OtherPertinentInformationSectionSummaryNoteDao {
	
	private static final String
		FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_QUERY_NAME = 
			"findOtherPertinentInformationSectionSummaryNote";
	
	private static final String
	FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME = 
		"findOtherPertinentInformationSectionSummaryNoteExcluding";
	
	private static final String
		FIND_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME =
			"findOtherPertinentInformationSectionSummaryNotesBy"
			+ "OtherPertinentInformationSectionSummary";
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_PARAM_NAME =
			"otherPertinentInformationSectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_PARAM_NAME =
			"otherPertinentInformationSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected OtherPertinentInformationSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummaryNote find(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			final String description, final Date date) {
		OtherPertinentInformationSectionSummaryNote sectionSummaryNote =
				(OtherPertinentInformationSectionSummaryNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
				FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(
						OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_PARAM_NAME,
						otherPertinentInformationSectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		
		return sectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummaryNote findExcluding(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			final String description, final Date date,
			final OtherPertinentInformationSectionSummaryNote
				otherPertinentInformationSectionSummaryNoteExcluding) {
		OtherPertinentInformationSectionSummaryNote sectionSummaryNote =
				(OtherPertinentInformationSectionSummaryNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
		FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(
						OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_PARAM_NAME,
						otherPertinentInformationSectionSummary)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(
					OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_PARAM_NAME,
					otherPertinentInformationSectionSummaryNoteExcluding)
				.uniqueResult();
		
		return sectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<OtherPertinentInformationSectionSummaryNote>
		findByOtherPertinentInformationSectionSummary(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary) {
		@SuppressWarnings("unchecked")
		List<OtherPertinentInformationSectionSummaryNote> sectionSummaryNotes =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(
						OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_PARAM_NAME,
						otherPertinentInformationSectionSummary)
				.list();
		
		return sectionSummaryNotes;
	}

}
