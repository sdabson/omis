package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.VictimSectionSummaryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.VictimSectionSummary;

/**
 * VictimSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryDaoHibernateImpl
	extends GenericHibernateDaoImpl<VictimSectionSummary>
		implements VictimSectionSummaryDao {
	
	private static final String FIND_VICTIM_SECTION_SUMMARY_QUERY_NAME =
			"findVictimSectionSummary";
	
	private static final String FIND_VICTIM_SECTION_SUMMARY_EXCLUDING_QUERY_NAME =
			"findVictimSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	private static final String VICTIM_SECTION_SUMMARY_PARAM_NAME =
			"victimSectionSummary";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected VictimSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		VictimSectionSummary victimSectionSummary = (VictimSectionSummary)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_VICTIM_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return victimSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final VictimSectionSummary victimSectionSummaryExcluded) {
		VictimSectionSummary victimSectionSummary = (VictimSectionSummary)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_VICTIM_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(VICTIM_SECTION_SUMMARY_PARAM_NAME,
						victimSectionSummaryExcluded)
				.uniqueResult();
		
		return victimSectionSummary;
	}
}
