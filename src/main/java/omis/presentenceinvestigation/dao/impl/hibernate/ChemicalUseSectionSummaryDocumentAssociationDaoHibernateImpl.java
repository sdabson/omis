
package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.presentenceinvestigation.dao.ChemicalUseSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryDocumentAssociation;

/**
 * ChemicalUseSectionSummaryDocumentAssociationDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryDocumentAssociationDaoHibernateImpl
	extends GenericHibernateDaoImpl<ChemicalUseSectionSummaryDocumentAssociation>
	implements ChemicalUseSectionSummaryDocumentAssociationDao {
	
	private static final String
		FIND_CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME =
			"findChemicalUseSectionSummaryDocumentAssociation";
	
	private static final String
		FIND_CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME =
			"findChemicalUseSectionSummaryDocumentAssociationExcluding";
	
	private static final String 
		FIND_CHEM_USE_SECT_SUM_DCMT_ASTNS_BY_SECTION_SUMMARY_QUERY_NAME =
		"findChemicalUseSectionSummaryDocumentAssociationsByChemicalUseSectionSummary";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME =
			"chemicalUseSectionSummary";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME =
			"chemicalUseSectionSummaryDocumentAssociation";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ChemicalUseSectionSummaryDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryDocumentAssociation find(
			final Document document,
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		ChemicalUseSectionSummaryDocumentAssociation association =
				(ChemicalUseSectionSummaryDocumentAssociation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME,
						chemicalUseSectionSummary)
				.uniqueResult();
		
		return association;
	}

	/**{@inheritDoc} */
	@Override
	public ChemicalUseSectionSummaryDocumentAssociation findExcluding(
			final Document document,
			final ChemicalUseSectionSummary chemicalUseSectionSummary,
			final ChemicalUseSectionSummaryDocumentAssociation
				chemicalUseSectionSummaryDocumentAssociationExcluded) {
		ChemicalUseSectionSummaryDocumentAssociation association =
				(ChemicalUseSectionSummaryDocumentAssociation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
		FIND_CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME,
						chemicalUseSectionSummary)
				.setParameter(
					CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME,
					chemicalUseSectionSummaryDocumentAssociationExcluded)
				.uniqueResult();
		
		return association;
	}

	/**{@inheritDoc} */
	@Override
	public List<ChemicalUseSectionSummaryDocumentAssociation>
		findByChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		@SuppressWarnings("unchecked")
		List<ChemicalUseSectionSummaryDocumentAssociation> associations =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_CHEM_USE_SECT_SUM_DCMT_ASTNS_BY_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(CHEMICAL_USE_SECTION_SUMMARY_PARAM_NAME,
						chemicalUseSectionSummary)
				.list();
		
		return associations;
	}

}
