package omis.education.report.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.education.report.EducationDocumentSummary;
import omis.education.report.EducationSummary;
import omis.education.report.EducationSummaryReportService;
import omis.offender.domain.Offender;

/**
 * EducationSummaryReportServiceHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationSummaryReportServiceHibernateImpl 
	implements EducationSummaryReportService {
	
	/* Queries. */
	private static final String FIND_EDUCATION_SUMMARIES_BY_OFFENDER
		= "findEducationSummariesByOffender";
	
	private static final String FIND_EDUCATION_DOCUMENT_SUMMARIES_BY_OFFENDER
		= "findEducationDocumentSummariesByOffender";
	
	/* Parameters. */
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	/* Members. */
	private final SessionFactory sessionFactory;
	
	
	
	/**
	 * Constructor
	 * @param sessionFactory - session factory
	 */
	public EducationSummaryReportServiceHibernateImpl(
			SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/**{@inheritDoc} */
	@Override
	public List<EducationSummary> findByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<EducationSummary> educationSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_SUMMARIES_BY_OFFENDER)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.list();
		return educationSummaries;
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationDocumentSummary>
			findEducationDocumentSummariesByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<EducationDocumentSummary> educationDocumentSummaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_EDUCATION_DOCUMENT_SUMMARIES_BY_OFFENDER)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.list();
		return educationDocumentSummaries;
	}
	
	
	

}
