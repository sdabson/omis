package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.document.domain.Document;
import omis.presentenceinvestigation.dao.HealthSectionSummaryDocumentAssociationDao;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;

/**
 * Health section summary document association data access object hibernate 
 * implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 10, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryDocumentAssociationDaoHibernateImpl
	extends GenericHibernateDaoImpl<HealthSectionSummaryDocumentAssociation>
		implements HealthSectionSummaryDocumentAssociationDao {
	
	private static final String 
	FIND_HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME
		= "findHealthSectionSummaryDocumentAssociation";
	
	private static final String 
	FIND_HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME
		= "findHealthSectionSummaryDocumentAssociationExcluding";
	
	private static final String 
		FIND_DOCUMENT_ASSOCIATIONS_BY_HEALTH_SECTION_SUMMARY_QUERY_NAME
		= "findHealthSectionSummaryDocumentAssociationsByHealthSectionSummary";
	
	private static final String HEALTH_SECTION_SUMMARY_PARAM_NAME
		= "healthSectionSummary";
	
	private static final String DOCUMENT_PARAM_NAME
		="document";
	
	private static final String 
		HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME
		= "healthSectionSummaryDocumentAssociation";

	/**
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HealthSectionSummaryDocumentAssociationDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
			super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummaryDocumentAssociation find(
			final HealthSectionSummary healthSectionSummary,
			final Document document) {
		HealthSectionSummaryDocumentAssociation association = 
				(HealthSectionSummaryDocumentAssociation) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_QUERY_NAME)
				.setParameter(HEALTH_SECTION_SUMMARY_PARAM_NAME, 
						healthSectionSummary)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummaryDocumentAssociation findExcluding(
			final HealthSectionSummaryDocumentAssociation 
			healthSectionSummaryDocumentAssociation,
			final HealthSectionSummary healthSectionSummary, 
			final Document document) {
		HealthSectionSummaryDocumentAssociation association = 
				(HealthSectionSummaryDocumentAssociation) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_EXCLUDING_QUERY_NAME)
				.setParameter(
						HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_PARAM_NAME, 
						healthSectionSummaryDocumentAssociation)
				.setParameter(HEALTH_SECTION_SUMMARY_PARAM_NAME, 
						healthSectionSummary)
				.setParameter(DOCUMENT_PARAM_NAME, document)
				.uniqueResult();
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthSectionSummaryDocumentAssociation> 
	findHealthSectionSummaryDocumentAssociation(
			HealthSectionSummary healthSectionSummary) {
		@SuppressWarnings("unchecked")
		List<HealthSectionSummaryDocumentAssociation> associations
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_DOCUMENT_ASSOCIATIONS_BY_HEALTH_SECTION_SUMMARY_QUERY_NAME)
			.setParameter(HEALTH_SECTION_SUMMARY_PARAM_NAME, healthSectionSummary)
			.list();
		return associations;
	}
}