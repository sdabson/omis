package omis.presentenceinvestigation.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.SupervisionHistorySectionSummaryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Supervision history section summary data access object hibernate 
 * implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 10, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistorySectionSummaryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SupervisionHistorySectionSummary>
	implements SupervisionHistorySectionSummaryDao {
	
	private static final String 
	FIND_SUPERVISION_HISTORY_SECTION_SUMMARY_QUERY_NAME
		= "findSupervisionHistorySectionSummary";
	
	private static final String 
	FIND_EXCLUDING_SUPERVISION_HISTORY_SECTION_SUMMARY_QUERY_NAME
		= "findExcludingSupervisionHistorySectionSummary";
	
	private static final String SUPERVISION_HISTORY_SECTION_SUMMARY_PARAM_NAME 
		= "supervisionHistorySectionSummary";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME 
		= "presentenceInvestigationRequest";
	
	/** Instantiates an implementation of supervision
	 * history section summary dao hibernate impl */
	public SupervisionHistorySectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistorySectionSummary findExcluding(
			SupervisionHistorySectionSummary supervisionHistorySectionSummary,
			PresentenceInvestigationRequest presentenceInvestigationRequest) {
		SupervisionHistorySectionSummary summary 
			= (SupervisionHistorySectionSummary) this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
				FIND_EXCLUDING_SUPERVISION_HISTORY_SECTION_SUMMARY_QUERY_NAME)
			.setParameter(SUPERVISION_HISTORY_SECTION_SUMMARY_PARAM_NAME, 
					supervisionHistorySectionSummary)
			.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
					presentenceInvestigationRequest)
			.uniqueResult();
		return summary;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistorySectionSummary 
	findSupervisionHistorySectionSummaryByPSI(
			final PresentenceInvestigationRequest 
		presentenceInvestigationRequest) {
	SupervisionHistorySectionSummary summary 
		= (SupervisionHistorySectionSummary) this.getSessionFactory()
		.getCurrentSession().getNamedQuery(
				FIND_SUPERVISION_HISTORY_SECTION_SUMMARY_QUERY_NAME)
		.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
				presentenceInvestigationRequest)
		.uniqueResult();
	return summary;
	}
}