package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.presentenceinvestigation.dao.PsychologicalSectionSummaryDocumentDao;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryDocument;

/**
 * PsychologicalSectionSummaryDocumentDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryDocumentDaoHibernateImpl extends
		GenericHibernateDaoImpl<PsychologicalSectionSummaryDocument> implements PsychologicalSectionSummaryDocumentDao {

	private static final String
			FIND_PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_QUERY_NAME =
					"findPsychologicalSectionSummaryDocument";

	private static final String
			FIND_PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_EXCLUDING_QUERY_NAME =
					"findPsychologicalSectionSummaryDocumentExcluding";
	
	private static final String
			FIND_PSS_DOCUMENTS_BY_PSYCHOLOGICAL_SECTION_SUMMARY_QUERY_NAME =
				"findPsychologicalSectionDocumentsByPsychologicalSectionSummary";
	
	private static final String DOCUMENT_MODEL_KEY = "document";
	
	private static final String PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY =
			"psychologicalSectionSummary";
	
	private static final String PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_MODEL_KEY
			= "psychologicalSectionSummaryDocument";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PsychologicalSectionSummaryDocumentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryDocument find(final Document document,
			final PsychologicalSectionSummary psychologicalSectionSummary) {
		PsychologicalSectionSummaryDocument psychologicalSectionSummaryDocument
			= (PsychologicalSectionSummaryDocument) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_QUERY_NAME)
			.setParameter(DOCUMENT_MODEL_KEY, document)
			.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
					psychologicalSectionSummary)
			.uniqueResult();
		
		return psychologicalSectionSummaryDocument;
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummaryDocument findExcluding(
			final Document document,
			final PsychologicalSectionSummary psychologicalSectionSummary,
			final PsychologicalSectionSummaryDocument
					psychologicalSectionSummaryDocumentExcluding) {
		PsychologicalSectionSummaryDocument psychologicalSectionSummaryDocument
			= (PsychologicalSectionSummaryDocument) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
				FIND_PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_EXCLUDING_QUERY_NAME)
			.setParameter(DOCUMENT_MODEL_KEY, document)
			.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
					psychologicalSectionSummary)
			.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_MODEL_KEY,
					psychologicalSectionSummaryDocumentExcluding)
			.uniqueResult();

return psychologicalSectionSummaryDocument;
	}

	/**{@inheritDoc} */
	@Override
	public List<PsychologicalSectionSummaryDocument> findByPsychologicalSectionSummary(
			final PsychologicalSectionSummary psychologicalSectionSummary) {
		@SuppressWarnings("unchecked")
		List<PsychologicalSectionSummaryDocument>
			psychologicalSectionSummaryDocuments = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
				FIND_PSS_DOCUMENTS_BY_PSYCHOLOGICAL_SECTION_SUMMARY_QUERY_NAME)
			.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
					psychologicalSectionSummary)
			.list();

			return psychologicalSectionSummaryDocuments;
	}

}
