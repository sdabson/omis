package omis.presentenceinvestigation.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PsychologicalSectionSummaryDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;

/**
 * PsychologicalSectionSummaryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryDaoHibernateImpl
	extends GenericHibernateDaoImpl<PsychologicalSectionSummary>
		implements PsychologicalSectionSummaryDao {
	
	private static final String FIND_PSYCHOLOGICAL_SECTION_SUMMARY_QUERY_NAME =
			"findPsychologicalSectionSummary";
	
	private static final String
			FIND_PSYCHOLOGICAL_SECTION_SUMMARY_EXCLUDING_QUERY_NAME =
					"findPsychologicalSectionSummaryExcluding";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY =
			"psychologicalSectionSummary";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PsychologicalSectionSummaryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		PsychologicalSectionSummary psychologicalSectionSummary =
				(PsychologicalSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PSYCHOLOGICAL_SECTION_SUMMARY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return psychologicalSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummary findExcluding(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final PsychologicalSectionSummary psychologicalSectionSummaryExcluded) {
		PsychologicalSectionSummary psychologicalSectionSummary =
				(PsychologicalSectionSummary) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_PSYCHOLOGICAL_SECTION_SUMMARY_EXCLUDING_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.setParameter(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
						psychologicalSectionSummaryExcluded)
				.uniqueResult();
		
		return psychologicalSectionSummary;
	}

}
