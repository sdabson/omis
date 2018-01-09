package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.presentenceinvestigation.dao.PleaAgreementSectionSummaryAssociableDocumentDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryAssociableDocument;

/**
 * PleaAgreementSectionSummaryAssociableDocumentDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryAssociableDocumentDaoHibernateImpl
		extends GenericHibernateDaoImpl<PleaAgreementSectionSummaryAssociableDocument>
		implements PleaAgreementSectionSummaryAssociableDocumentDao {

	private static final String
		FIND_PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_QUERY_NAME =
			"findPleaAgreementSectionSummaryAssociableDocument";

	private static final String
		FIND_PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME =
			"findPleaAgreementSectionSummaryAssociableDocumentExcluding";
	
	private static final String
		FIND_ASSOCIABLE_DOCUMENTS_BY_PLEA_AGREEMENT_SECTION_SUMMARY_QUERY_NAME =
		"findPleaAgreementSectionSummaryAssociableDocumentsByPleaAgreementSectionSummary";
	
	private static final String DOCUMENT_MODEL_KEY =
			"document";
	
	private static final String PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY =
			"pleaAgreementSectionSummary";
	
	private static final String
			PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_MODEL_KEY =
				"pleaAgreementSectionSummaryAssociableDocument";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PleaAgreementSectionSummaryAssociableDocumentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryAssociableDocument find(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final Document document) {
		PleaAgreementSectionSummaryAssociableDocument
			pleaAgreementSectionSummaryAssociableDocument =
				(PleaAgreementSectionSummaryAssociableDocument)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_QUERY_NAME)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
						pleaAgreementSectionSummary)
				.setParameter(DOCUMENT_MODEL_KEY, document)
				.uniqueResult();
		
		return pleaAgreementSectionSummaryAssociableDocument;
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummaryAssociableDocument findExcluding(
			final PleaAgreementSectionSummary pleaAgreementSectionSummary,
			final Document document,
			final PleaAgreementSectionSummaryAssociableDocument
				pleaAgreementSectionSummaryAssociableDocumentExcluded) {
		PleaAgreementSectionSummaryAssociableDocument
			pleaAgreementSectionSummaryAssociableDocument =
				(PleaAgreementSectionSummaryAssociableDocument)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
						pleaAgreementSectionSummary)
				.setParameter(DOCUMENT_MODEL_KEY, document)
				.setParameter(
					PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_MODEL_KEY,
						pleaAgreementSectionSummaryAssociableDocumentExcluded)
				.uniqueResult();
		
		return pleaAgreementSectionSummaryAssociableDocument;
	}

	/**{@inheritDoc} */
	@Override
	public List<PleaAgreementSectionSummaryAssociableDocument>
			findByPleaAgreementSectionSummary(
				final PleaAgreementSectionSummary pleaAgreementSectionSummary) {
		@SuppressWarnings("unchecked")
		List<PleaAgreementSectionSummaryAssociableDocument> associableDocuments =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_ASSOCIABLE_DOCUMENTS_BY_PLEA_AGREEMENT_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
						pleaAgreementSectionSummary)
				.list();
		
		return associableDocuments;
	}

}
