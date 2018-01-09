package omis.presentenceinvestigation.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.HealthSectionSummaryDao;
import omis.presentenceinvestigation.domain.HealthRating;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Health section summary data access object hibernate implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 10, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<HealthSectionSummary>
	implements HealthSectionSummaryDao {

	private static final String FIND_HEATLH_SECTION_SUMMARY_QUERY_NAME
		= "findHealthSectionSummary";
	
	private static final String FIND_HEATLH_SECTION_SUMMARY_EXCLUDING_QUERY_NAME
		= "findHealthSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME 
		= "presentenceInvestigationRequest";
	
	private static final String HEALTH_SECTION_SUMMARY_PARAM_NAME
		= "healthSectionSummary";
	
	private static final String HEALTH_RATING_SUMMARY_PARAM_NAME = "rating";
	
	/**
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HealthSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummary find(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest, final HealthRating rating) {
		HealthSectionSummary summary 
			= (HealthSectionSummary) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_HEATLH_SECTION_SUMMARY_QUERY_NAME)
			.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
					presentenceInvestigationRequest)
			.setParameter(HEALTH_RATING_SUMMARY_PARAM_NAME, rating)
			.uniqueResult();
		
		return summary;
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummary findExcluding(
			final HealthSectionSummary healthSectionSummary,
			final HealthRating rating) {
		HealthSectionSummary summary 
			= (HealthSectionSummary) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_HEATLH_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
			.setParameter(HEALTH_SECTION_SUMMARY_PARAM_NAME, 
					healthSectionSummary)
			.setParameter(HEALTH_RATING_SUMMARY_PARAM_NAME, rating)
			.uniqueResult();
	
		return summary;
	}

	/** {@inheritDoc} */
	@Override
	public HealthSectionSummary 
	findHealthSectionSummaryByPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest) {
		HealthSectionSummary summary = (HealthSectionSummary) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
					"findHealthSectionsummaryByPresentenceInvestigationRequest")
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME, 
						presentenceInvestigationRequest)
				.uniqueResult();
		return summary;
	}
}