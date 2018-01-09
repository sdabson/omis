package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.OtherPertinentInformationSectionSummaryDao;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OtherPertinentInformationSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryDaoHibernateImpl
		extends GenericHibernateDaoImpl<OtherPertinentInformationSectionSummary>
		implements OtherPertinentInformationSectionSummaryDao {

	private static final String
		FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_QUERY_NAME = 
			"findOtherPertinentInformationSectionSummary";
	
	private static final String
	FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_EXCLUDING_QUERY_NAME = 
		"findOtherPertinentInformationSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME = 
			"presentenceInvestigationRequest";
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_PARAM_NAME =
			"otherPertinentInformationSectionSummary";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected OtherPertinentInformationSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		OtherPertinentInformationSectionSummary sectionSummary =
				(OtherPertinentInformationSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return sectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummaryExcluding) {
		OtherPertinentInformationSectionSummary sectionSummary =
				(OtherPertinentInformationSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
			FIND_OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(
						OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_PARAM_NAME,
						otherPertinentInformationSectionSummaryExcluding)
				.uniqueResult();
		
		return sectionSummary;
	}

}
