package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PleaAgreementSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryNote;

/**
 * PleaAgreementSectionSummaryNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<PleaAgreementSectionSummaryNote>
		implements PleaAgreementSectionSummaryNoteDao {
	
	private static final String FIND_PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_QUERY_NAME =
			"findPleaAgreementSectionSummaryNote";

	private static final String
			FIND_PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME =
				"findPleaAgreementSectionSummaryNoteExcluding";
	
	private static final String
			FIND_NOTES_BY_PLEA_AGREEMENT_SECTION_SUMMARY_QUERY_NAME =
				"findPleaAgreementSectionSummaryNotesByPleaAgreementSectionSummary";
	
	private static final String DESCRIPTION_MODEL_KEY =
			"description";
	
	private static final String PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY =
			"pleaAgreementSectionSummary";
	
	private static final String PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_MODEL_KEY =
			"pleaAgreementSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PleaAgreementSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryNote find(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final String description) {
		PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote =
				(PleaAgreementSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
						pleaAgreementSectionSummary)
				.setParameter(DESCRIPTION_MODEL_KEY, description)
				.uniqueResult();
		
		return pleaAgreementSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryNote findExcluding(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final String description,
			final PleaAgreementSectionSummaryNote
				pleaAgreementSectionSummaryNoteExcluded) {
		PleaAgreementSectionSummaryNote pleaAgreementSectionSummaryNote =
				(PleaAgreementSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
						pleaAgreementSectionSummary)
				.setParameter(DESCRIPTION_MODEL_KEY, description)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_MODEL_KEY,
						pleaAgreementSectionSummaryNoteExcluded)
				.uniqueResult();
		
		return pleaAgreementSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<PleaAgreementSectionSummaryNote> findByPleaAgreementSectionSummary(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary) {
		@SuppressWarnings("unchecked")
		List<PleaAgreementSectionSummaryNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_NOTES_BY_PLEA_AGREEMENT_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
						pleaAgreementSectionSummary)
				.list();
		
		return notes;
	}

}
