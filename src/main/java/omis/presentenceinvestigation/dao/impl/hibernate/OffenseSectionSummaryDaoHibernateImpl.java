package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.OffenseSectionSummaryDao;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * OffenseSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class OffenseSectionSummaryDaoHibernateImpl extends GenericHibernateDaoImpl<OffenseSectionSummary>
		implements OffenseSectionSummaryDao {
	
	private static final String FIND_OFFENSE_SECTION_SUMMARY_QUERY_NAME =
			"findOffenseSectionSummary";
	
	private static final String FIND_OFFENSE_SECTION_SUMMARY_EXCLUDING_QUERY_NAME =
			"findOffenseSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	private static final String OFFENSE_SECTION_SUMMARY_PARAM_NAME =
			"offenseSectionSummary";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected OffenseSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		OffenseSectionSummary offenseSectionSummary = (OffenseSectionSummary)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_OFFENSE_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return offenseSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public OffenseSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final OffenseSectionSummary offenseSectionSummaryExcluded) {
		OffenseSectionSummary offenseSectionSummary = (OffenseSectionSummary)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_OFFENSE_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(OFFENSE_SECTION_SUMMARY_PARAM_NAME,
						offenseSectionSummaryExcluded)
				.uniqueResult();
		
		return offenseSectionSummary;
	}

}
