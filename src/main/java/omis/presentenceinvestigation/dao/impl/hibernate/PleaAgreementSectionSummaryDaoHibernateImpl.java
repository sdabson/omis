package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PleaAgreementSectionSummaryDao;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * PleaAgreementSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public class PleaAgreementSectionSummaryDaoHibernateImpl
		extends GenericHibernateDaoImpl<PleaAgreementSectionSummary>
		implements PleaAgreementSectionSummaryDao {

	private static final String FIND_PLEA_AGREEMENT_SECTION_SUMMARY_QUERY_NAME =
			"findPleaAgreementSectionSummary";

	private static final String
			FIND_PLEA_AGREEMENT_SECTION_SUMMARY_EXCLUDING_QUERY_NAME =
				"findPleaAgreementSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY =
			"pleaAgreementSectionSummary";
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PleaAgreementSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		PleaAgreementSectionSummary pleaAgreementSectionSummary = 
				(PleaAgreementSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PLEA_AGREEMENT_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return pleaAgreementSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public PleaAgreementSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final PleaAgreementSectionSummary pleaAgreementSectionSummaryExcluded) {
		PleaAgreementSectionSummary pleaAgreementSectionSummary = 
				(PleaAgreementSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_PLEA_AGREEMENT_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.setParameter(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
						pleaAgreementSectionSummaryExcluded)
				.uniqueResult();
		
		return pleaAgreementSectionSummary;
	}

}
