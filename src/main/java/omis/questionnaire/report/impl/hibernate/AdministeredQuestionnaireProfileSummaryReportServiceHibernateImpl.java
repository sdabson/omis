package omis.questionnaire.report.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.person.domain.Person;
import omis.questionnaire.report.AdministeredQuestionnaireProfileSummaryReportService;

/**
 * AdministeredQuestionnaireProfileSummaryReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 23, 2017)
 *@since OMIS 3.0
 *
 */
public class AdministeredQuestionnaireProfileSummaryReportServiceHibernateImpl
		implements AdministeredQuestionnaireProfileSummaryReportService {
	
	private static final String FIND_COUNT_BY_OFFENDER_QUERY_NAME =
			"findAdministeredQuestionnaireCountByAnswerer";
	
	private static final String ANSWERER_PARAM_NAME = "answerer";
	
	private final SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory
	 */
	public AdministeredQuestionnaireProfileSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**{@inheritDoc} */
	@Override
	public Integer findCountByAnswerer(final Person answerer) {
		Integer count = Integer.valueOf(((Long) (this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_COUNT_BY_OFFENDER_QUERY_NAME)
				.setEntity(ANSWERER_PARAM_NAME, answerer)
				.uniqueResult())).intValue());
		
		return count;
	}

}
