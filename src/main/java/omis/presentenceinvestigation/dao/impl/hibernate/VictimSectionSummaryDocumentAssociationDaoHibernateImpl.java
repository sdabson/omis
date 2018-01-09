package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.presentenceinvestigation.dao.VictimSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryDocumentAssociation;

/**
 * Victim Section Summary Document Association Dao Hibernate Implementation
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (August 29, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryDocumentAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<VictimSectionSummaryDocumentAssociation>
	implements VictimSectionSummaryDocumentAssociationDao {
	
	private static final String 
		FIND_VICTIM_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME 
			= "findVictimSectionSummaryDocumentAssociation";
	
	private static final String
		FIND_VICTIM_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME
			= "findVictimSectionSummaryDocumentAssociationExcluding";
	
	private static final String
		FIND_VICTIM_SCTN_SUMMRY_DCMNT_ASTNS_BY_SECTION_SUMMARY_QUERY_NAME
		= "findVictimSectionSummaryDocumentAssociationsByVictimSectionSummary";
	
	private static final String DOCUMENT_PARAM_NAME = "document";
	
	private static final String VICTIM_SECTION_SUMMARY_PARAM_NAME 
			= "victimSectionSummary";
	
	private static final String 
		VICTIM_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME 
			= "victimSectionSummaryDocumentAssociation";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected VictimSectionSummaryDocumentAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public VictimSectionSummaryDocumentAssociation find(
			final Document document, 
			final VictimSectionSummary victimSectionSummary) {
		VictimSectionSummaryDocumentAssociation association
			= (VictimSectionSummaryDocumentAssociation) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
					FIND_VICTIM_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME)
			.setParameter(DOCUMENT_PARAM_NAME, document)
			.setParameter(VICTIM_SECTION_SUMMARY_PARAM_NAME, 
					victimSectionSummary)
			.uniqueResult();
		return association;
	}
	
	/**{@inheritDoc} */
	@Override
	public VictimSectionSummaryDocumentAssociation findExcluding(
			final Document document, 
			final VictimSectionSummary victimSectionSummary, 
			final VictimSectionSummaryDocumentAssociation
				victimSectionSummaryDocumentAssociationExcluded) {
		VictimSectionSummaryDocumentAssociation association 
			= (VictimSectionSummaryDocumentAssociation) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
		FIND_VICTIM_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME)
			.setParameter(DOCUMENT_PARAM_NAME, document)
			.setParameter(VICTIM_SECTION_SUMMARY_PARAM_NAME, 
					victimSectionSummary)
			.setParameter(
					VICTIM_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME, 
						victimSectionSummaryDocumentAssociationExcluded)
			.uniqueResult();
		
		return association;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<VictimSectionSummaryDocumentAssociation> 
		findByVictimSectionSummary(
				final VictimSectionSummary victimSectionSummary) {
		@SuppressWarnings("unchecked")
		List<VictimSectionSummaryDocumentAssociation> associations 
			= this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
			FIND_VICTIM_SCTN_SUMMRY_DCMNT_ASTNS_BY_SECTION_SUMMARY_QUERY_NAME)
			.setParameter(VICTIM_SECTION_SUMMARY_PARAM_NAME, 
					victimSectionSummary)
			.list();
		return associations;
	}

}
