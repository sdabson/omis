package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.FinancialSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryNote;

/**
 * FinancialSectionSummaryNoteDaoHibernateImpl
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 20, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryNoteDaoHibernateImpl
	extends GenericHibernateDaoImpl<FinancialSectionSummaryNote>
	implements FinancialSectionSummaryNoteDao {

	private static final String FIND_FINANCIAL_SECTION_SUMMARY_NOTE_QUERY_NAME =
			"findFinancialSectionSummaryNote";
	
	private static final String
		FIND_FINANCIAL_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME =
			"findFinancialSectionSummaryNoteExcluding";
	
	private static final String
		FIND_FINANCIAL_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME =
			"findFinancialSectionSummaryNotesByFinancialSectionSummary";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String FINANCIAL_SECTION_SUMMARY_PARAM_NAME =
			"financialSectionSummary";
	
	private static final String FINANCIAL_SECTION_SUMMARY_NOTE_PARAM_NAME =
			"financialSectionSummaryNote";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected FinancialSectionSummaryNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryNote find(final String description,
			final Date date,
			final FinancialSectionSummary financialSectionSummary) {
		FinancialSectionSummaryNote financialSectionSummaryNote =
				(FinancialSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_SECTION_SUMMARY_NOTE_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(FINANCIAL_SECTION_SUMMARY_PARAM_NAME,
						financialSectionSummary)
				.uniqueResult();
		
		return financialSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryNote findExcluding(final String description,
			final Date date,
			final FinancialSectionSummary financialSectionSummary,
			final FinancialSectionSummaryNote financialSectionSummaryNoteExcluded) {
		FinancialSectionSummaryNote financialSectionSummaryNote =
				(FinancialSectionSummaryNote) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_FINANCIAL_SECTION_SUMMARY_NOTE_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(FINANCIAL_SECTION_SUMMARY_PARAM_NAME,
						financialSectionSummary)
				.setParameter(FINANCIAL_SECTION_SUMMARY_NOTE_PARAM_NAME,
						financialSectionSummaryNoteExcluded)
				.uniqueResult();
		
		return financialSectionSummaryNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<FinancialSectionSummaryNote> findByFinancialSectionSummary(
			final FinancialSectionSummary financialSectionSummary) {
		@SuppressWarnings("unchecked")
		List<FinancialSectionSummaryNote> financialSectionSummaryNotes =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_FINANCIAL_SECTION_SUMMARY_NOTES_BY_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(FINANCIAL_SECTION_SUMMARY_PARAM_NAME,
						financialSectionSummary)
				.list();
		
		return financialSectionSummaryNotes;
	}
	
}
