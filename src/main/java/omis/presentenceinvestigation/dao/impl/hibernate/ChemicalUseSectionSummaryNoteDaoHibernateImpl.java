package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.ChemicalUseSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryNote;

/**
 * ChemicalUseSectionSummaryNoteDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<ChemicalUseSectionSummaryNote>
	implements ChemicalUseSectionSummaryNoteDao {
	
	private static final String FIND_CHEMICAL_USE_SECTION_SUMMARY_NOTE_QUERY_NAME =
			"findChemicalUseSectionSummaryNote";
	
	private static final String
		FIND_CHEMICAL_USE_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME =
			"findChemicalUseSectionSummaryNoteExcluding";
	
	private static final String
		FIND_CHEMICAL_USE_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME =
			"findChemicalUseSectionSummaryNotesByChemicalUseSectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME =
			"chemicalUseSectionSummary";
	
	private static final String CHEMICAL_USE_SECTION_SUMMARY_NOTE_PARAM_NAME =
			"chemicalUseSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ChemicalUseSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryNote find(final String description,
			final Date date,
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote =
				(ChemicalUseSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CHEMICAL_USE_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME,
						chemicalUseSectionSummary)
				.uniqueResult();
		
		return chemicalUseSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryNote findExcluding(final String description,
			final Date date,
			final ChemicalUseSectionSummary chemicalUseSectionSummary,
			final ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNoteExcluded) {
		ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote =
				(ChemicalUseSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_CHEMICAL_USE_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME,
						chemicalUseSectionSummary)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_NOTE_PARAM_NAME,
						chemicalUseSectionSummaryNoteExcluded)
				.uniqueResult();
		
		return chemicalUseSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<ChemicalUseSectionSummaryNote> findByChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		@SuppressWarnings("unchecked")
		List<ChemicalUseSectionSummaryNote> chemicalUseSectionSummaryNotes =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_CHEMICAL_USE_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME,
						chemicalUseSectionSummary)
				.list();
		
		return chemicalUseSectionSummaryNotes;
	}

}
