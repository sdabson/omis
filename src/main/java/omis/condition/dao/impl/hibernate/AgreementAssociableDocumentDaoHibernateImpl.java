package omis.condition.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.condition.dao.AgreementAssociableDocumentDao;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;

/**
 * Agreement Associable Document DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 28, 2017)
 *@since OMIS 3.0
 *
 */
public class AgreementAssociableDocumentDaoHibernateImpl
		extends GenericHibernateDaoImpl<AgreementAssociableDocument>
		implements AgreementAssociableDocumentDao {
	
	private static final String FIND_AGRMNT_ASSC_DCMT_QUERY_NAME =
			"findAgreementAssociableDocument";
	
	private static final String FIND_AGRMNT_ASSC_DCMT_EXCLUDING_QUERY_NAME =
			"findAgreementAssociableDocumentExcluding";
	
	private static final String FIND_BY_AGREEMENT_QUERY_NAME =
			"findAgreementAssociableDocumentsByAgreement";
	
	private static final String AGREEMENT_PARAM_NAME = "agreement";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String AGREEMENT_ASSOCIABLE_DOCUMENT_PARAM_NAME =
			"agreementAssociableDocument";
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String Entity Name
	 */
	protected AgreementAssociableDocumentDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public AgreementAssociableDocument find(final Agreement agreement,
			final Document document) {
		AgreementAssociableDocument agreementAssociableDocument =
				(AgreementAssociableDocument) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_AGRMNT_ASSC_DCMT_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		
		return agreementAssociableDocument;
	}
	
	/**{@inheritDoc} */
	@Override
	public AgreementAssociableDocument findExcluding(final Agreement agreement,
			final Document document,
			final AgreementAssociableDocument
				excludedAgreementAssociableDocument) {
		AgreementAssociableDocument agreementAssociableDocument =
				(AgreementAssociableDocument) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_AGRMNT_ASSC_DCMT_EXCLUDING_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(AGREEMENT_ASSOCIABLE_DOCUMENT_PARAM_NAME,
						excludedAgreementAssociableDocument)
				.uniqueResult();
		
		return agreementAssociableDocument;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<AgreementAssociableDocument> findByAgreement(
			final Agreement agreement) {
		@SuppressWarnings("unchecked")
		List<AgreementAssociableDocument> agreementAssociableDocuments =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_AGREEMENT_QUERY_NAME)
				.setParameter(AGREEMENT_PARAM_NAME, agreement)
				.list();
		
		return agreementAssociableDocuments;
	}

}
