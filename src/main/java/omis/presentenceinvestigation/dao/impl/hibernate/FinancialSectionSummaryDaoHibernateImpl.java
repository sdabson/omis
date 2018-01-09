package omis.presentenceinvestigation.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.FinancialSectionSummaryDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * FinancialSectionSummaryDaoHibernateImplementation
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (May 31, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryDaoHibernateImpl
	extends GenericHibernateDaoImpl<FinancialSectionSummary>
	implements FinancialSectionSummaryDao {
	
	private static final String FIND_FINANCIAL_SECTION_SUMMARY_QUERY_NAME =
			"findFinancialSectionSummary";
	
	private static final String 
		FIND_FINANCIAL_SECTION_SUMMARY_EXCLUDING_QUERY_NAME = 
		"findFinancialSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	private static final String FINANCIAL_SECTION_SUMMARY_PARAM_NAME =
			"financialSectionSummary";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected FinancialSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummary find(final PresentenceInvestigationRequest
			presentenceInvestigationRequest) {
		FinancialSectionSummary sectionSummary = (FinancialSectionSummary)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
						presentenceInvestigationRequest)
				.uniqueResult();
		return sectionSummary;
	}
	
	/**{@inheritDoc} */
	@Override
	public FinancialSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final FinancialSectionSummary financialSectionSummaryExcluded) {
		FinancialSectionSummary sectionSummary = 
				(FinancialSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FINANCIAL_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
						presentenceInvestigationRequest)
				.setParameter(FINANCIAL_SECTION_SUMMARY_PARAM_NAME, 
						financialSectionSummaryExcluded)
				.uniqueResult();
		return sectionSummary;
	}

}
