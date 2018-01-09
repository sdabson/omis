package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.presentenceinvestigation.dao.OffenseSectionSummaryAssociableDocumentDao;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.OffenseSectionSummaryAssociableDocument;

/**
 * OffenseSectionSummaryAssociableDocumentDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class OffenseSectionSummaryAssociableDocumentDaoHibernateImpl
		extends GenericHibernateDaoImpl<OffenseSectionSummaryAssociableDocument>
		implements OffenseSectionSummaryAssociableDocumentDao {
	
	private static final String
		FIND_OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_QUERY_NAME =
			"findOffenseSectionSummaryAssociableDocument";
	
	private static final String
		FIND_OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME =
			"findOffenseSectionSummaryAssociableDocumentExcluding";
	
	private static final String
		FIND_OFFENSE_SECT_SUMM_ASSOC_DOCS_BY_OFFENSE_SECTION_SUMMARY_QUERY_NAME =
			"findOffenseSectionSummaryAssociableDocumentByOffenseSectionSummary";
	
	private static final String OFFENSE_SECTION_SUMMARY_PARAM_NAME =
			"offenseSectionSummary";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String
		OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_PARAM_NAME =
			"offenseSectionSummaryAssociableDocument";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected OffenseSectionSummaryAssociableDocumentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummaryAssociableDocument find(
			final OffenseSectionSummary offenseSectionSummary,
			final Document document) {
		OffenseSectionSummaryAssociableDocument associableDocument =
				(OffenseSectionSummaryAssociableDocument) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_QUERY_NAME)
				.setParameter(OFFENSE_SECTION_SUMMARY_PARAM_NAME,
						offenseSectionSummary)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		
		return associableDocument;
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummaryAssociableDocument findExcluding(
			final OffenseSectionSummary offenseSectionSummary,
			final Document document,
			final OffenseSectionSummaryAssociableDocument
				offenseSectionSummaryAssociableDocumentExcluded) {
		OffenseSectionSummaryAssociableDocument associableDocument =
				(OffenseSectionSummaryAssociableDocument) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENSE_SECTION_SUMMARY_PARAM_NAME,
						offenseSectionSummary)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(
						OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_PARAM_NAME,
						offenseSectionSummaryAssociableDocumentExcluded)
				.uniqueResult();
		
		return associableDocument;
	}

	/**{@inheritDoc} */
	@Override
	public List<OffenseSectionSummaryAssociableDocument>
				findByOffenseSectionSummary(
						final OffenseSectionSummary offenseSectionSummary) {
		@SuppressWarnings("unchecked")
		List<OffenseSectionSummaryAssociableDocument> associableDocuments =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
			FIND_OFFENSE_SECT_SUMM_ASSOC_DOCS_BY_OFFENSE_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(OFFENSE_SECTION_SUMMARY_PARAM_NAME,
						offenseSectionSummary)
				.list();
		
		return associableDocuments;
	}

}
