package omis.financial.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.financial.dao.FinancialDocumentAssociationDao;
import omis.financial.domain.FinancialDocumentAssociation;
import omis.offender.domain.Offender;

/**
 * FinancialAssociableDocumentDaoHibernateImpl
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (June 2, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialDocumentAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<FinancialDocumentAssociation>
		implements FinancialDocumentAssociationDao {
	
	private static final String FIND_FINANCIAL_ASSOCIABLE_DOCUMENT_QUERY_NAME 
			= "findFinancialDocument";
	
	private static final String
		FIND_FINANCIAL_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME 
		= "findFinancialDocumentAssociationExcluding";
	
	private static final String 
		FIND_FINANCIAL_DOCUMENTS_BY_OFFENDER_QUERY_NAME
		= "findFinancialDocumentAssociationsByOffender";

	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String DOCUMENT_PARAM_NAME = "document";

	private static final String FINANCIAL_ASSOCIABLE_DOCUMENT_PARAM_NAME =
		"financialDocumentAssociation";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected FinancialDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialDocumentAssociation findFinancialDocument(
			final Offender offender, final Document document) {
		FinancialDocumentAssociation financialAssociableDocument = 
				(FinancialDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_ASSOCIABLE_DOCUMENT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		return financialAssociableDocument;
	}

	/**{@inheritDoc} */
	@Override
	public FinancialDocumentAssociation findFinancialDocumentExcluding(
			final Offender offender, final Document document,
			FinancialDocumentAssociation financialAssociableDocumentExcluded) {
		FinancialDocumentAssociation financialAssociableDocument 
				= (FinancialDocumentAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_FINANCIAL_ASSOCIABLE_DOCUMENT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(FINANCIAL_ASSOCIABLE_DOCUMENT_PARAM_NAME,
						financialAssociableDocumentExcluded)
				.uniqueResult();
		return financialAssociableDocument;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<FinancialDocumentAssociation> 
		findFinancialDocumentAssociationsByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<FinancialDocumentAssociation> financialDocuments = this.getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_FINANCIAL_DOCUMENTS_BY_OFFENDER_QUERY_NAME)
		.setParameter(OFFENDER_PARAM_NAME, offender)
		.list();
		return financialDocuments;
	}

}
