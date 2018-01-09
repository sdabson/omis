package omis.presentenceinvestigation.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.JailAdjustmentSectionSummaryDao;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * JailAdjustmentSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryDaoHibernateImpl 
		extends GenericHibernateDaoImpl<JailAdjustmentSectionSummary>
		implements JailAdjustmentSectionSummaryDao {

	private static final String FIND_JAIL_ADJUSTMENT_SECTION_SUMMARY_QUERY_NAME =
			"findJailAdjustmentSectionSummary";
	
	private static final String 
			FIND_JAIL_ADJUSTMENT_SECTION_SUMMARY_EXCLUDING_QUERY_NAME =
				"findJailAdjustmentSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	private static final String JAIL_ADJUSTMENT_SECTION_SUMMARY_PARAM_NAME =
			"jailAdjustmentSectionSummary";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected JailAdjustmentSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummary find(
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest) {
		JailAdjustmentSectionSummary sectionSummary =
				(JailAdjustmentSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_JAIL_ADJUSTMENT_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return sectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final JailAdjustmentSectionSummary
					jailAdjustmentSectionSummaryExcluded) {
		JailAdjustmentSectionSummary sectionSummary =
				(JailAdjustmentSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_JAIL_ADJUSTMENT_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(JAIL_ADJUSTMENT_SECTION_SUMMARY_PARAM_NAME,
						jailAdjustmentSectionSummaryExcluded)
				.uniqueResult();
		
		return sectionSummary;
	}

}
