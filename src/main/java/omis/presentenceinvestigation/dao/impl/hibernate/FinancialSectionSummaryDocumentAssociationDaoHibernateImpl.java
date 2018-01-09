package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.presentenceinvestigation.dao.FinancialSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;

/**
 * FinancialSectionSummaryDocumentAssociationDaoHibernateImplementation
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (May 31, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryDocumentAssociationDaoHibernateImpl
	extends GenericHibernateDaoImpl<FinancialSectionSummaryDocumentAssociation> 
	implements FinancialSectionSummaryDocumentAssociationDao {
	
	private static final String
	FIND_FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME =
		"findFinancialSectionSummaryDocumentAssociation";

private static final String
	FIND_FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME =
		"findFinancialSectionSummaryDocumentAssociationExcluding";

private static final String 
	FIND_FINANCIAL_SECT_SUM_DCMT_ASTNS_BY_SECTION_SUMMARY_QUERY_NAME =
	"findFinancialSectionSummaryDocumentAssociationsByFinancialSectionSummary";

private static final String DOCUMENT_PARAM_NAME = "document";

private static final String FINANCIAL_SECTION_SUMMARY_PARAM_NAME =
		"financialSectionSummary";

private static final String
	FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME =
		"financialSectionSummaryDocumentAssociation";

	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected FinancialSectionSummaryDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryDocumentAssociation find(
			final Document document,
			final FinancialSectionSummary financialSectionSummary) {
		FinancialSectionSummaryDocumentAssociation association 
			= (FinancialSectionSummaryDocumentAssociation)
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
				FIND_FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME)
			.setParameter(DOCUMENT_PARAM_NAME, document)
			.setParameter(FINANCIAL_SECTION_SUMMARY_PARAM_NAME, 
					financialSectionSummary)
			.uniqueResult();
		
		return association;
	}

	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummaryDocumentAssociation findExcluding(
			final Document document,
			final FinancialSectionSummary financialSectionSummary,
			final FinancialSectionSummaryDocumentAssociation
				financialSectionSummaryDocumentAssociationExcluded) {
		FinancialSectionSummaryDocumentAssociation association =
				(FinancialSectionSummaryDocumentAssociation)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
	FIND_FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(FINANCIAL_SECTION_SUMMARY_PARAM_NAME, 
						financialSectionSummary)
				.setParameter(
					FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME, 
					financialSectionSummaryDocumentAssociationExcluded)
				.uniqueResult();
				
				return association;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<FinancialSectionSummaryDocumentAssociation>
		findByFinancialSectionSummary(
				final FinancialSectionSummary financialSectionSummary) {
		@SuppressWarnings("unchecked")
		List<FinancialSectionSummaryDocumentAssociation> associations
			= this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
				FIND_FINANCIAL_SECT_SUM_DCMT_ASTNS_BY_SECTION_SUMMARY_QUERY_NAME)
			.setParameter(FINANCIAL_SECTION_SUMMARY_PARAM_NAME, 
					financialSectionSummary)
			.list();
		
		return associations;
	}
	
}
