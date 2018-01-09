package omis.offenderrelationship.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offenderrelationship.report.OffenderRelationshipSummary;
import omis.offenderrelationship.report.OffenderRelationshipSummaryReportService;

/**
 * Hibernate implementation of the summary report service for 
 * offender relationships.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 27, 2016)
 * @since OMIS 3.0
 */
public class OffenderRelationshipSummaryReportServiceHibernateImpl 
	implements OffenderRelationshipSummaryReportService {

	
	/* Parameters */
	
	private static final String OFFENDER_PARAM = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM = "effectiveDate";
	
	private static final String LAST_NAME_PARAM	= "lastName";
	
	private static final String FIRST_NAME_PARAM = "firstName";
	
	/* Memebers. */
	
	private final SessionFactory sessionFactory;	
	
	/**
	 * Constructor.
	 * @param sessionFactory session factory
	 */
	public OffenderRelationshipSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarize(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries 
			= (List<OffenderRelationshipSummary>)
					this.sessionFactory.getCurrentSession()
					.getNamedQuery("summarizeOffenderRelationsListByOffender")
					.setParameter(OFFENDER_PARAM, offender)
					.setParameter(EFFECTIVE_DATE_PARAM, effectiveDate)
					.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderRelationshipSummary> summarizeByName(
			final String lastName, final String firstName, 
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<OffenderRelationshipSummary> summaries 
			= (List<OffenderRelationshipSummary>)
				this.sessionFactory.getCurrentSession()
				.getNamedQuery("summarizeOffenderRelationsListByName")
				.setParameter(LAST_NAME_PARAM, lastName)
				.setParameter(FIRST_NAME_PARAM, firstName)
				.setParameter(EFFECTIVE_DATE_PARAM, effectiveDate)
				.list();
		return summaries;
	}
}