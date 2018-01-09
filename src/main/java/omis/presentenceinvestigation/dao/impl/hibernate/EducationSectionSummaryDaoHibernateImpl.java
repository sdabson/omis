package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.EducationSectionSummaryDao;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * EducationSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationSectionSummaryDaoHibernateImpl
		extends GenericHibernateDaoImpl<EducationSectionSummary>
		implements EducationSectionSummaryDao {
	
	private static final String FIND_EDUCATION_SECTION_SUMMARY_QUERY_NAME =
			"findEducationSectionSummary";
	
	private static final String FIND_EDUCATION_SECTION_SUMMARY_EXCLUDING_QUERY_NAME =
			"findEducationSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";
	
	private static final String EDUCATION_SECTION_SUMMARY_PARAM_NAME =
			"educationSectionSummary";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected EducationSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		EducationSectionSummary educationSectionSummary =
				(EducationSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		return educationSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final EducationSectionSummary educationSectionSummaryExcluding) {
		EducationSectionSummary educationSectionSummary =
				(EducationSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.setParameter(EDUCATION_SECTION_SUMMARY_PARAM_NAME,
						educationSectionSummaryExcluding)
				.uniqueResult();
		return educationSectionSummary;
	}

}
