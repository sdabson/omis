package omis.identificationnumber.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.identificationnumber.report.IdentificationNumberReportService;
import omis.identificationnumber.report.IdentificationNumberSummary;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of report service for identification numbers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberReportServiceHibernateImpl
		implements IdentificationNumberReportService {

	/* Query names. */
	
	private static final String SUMMARIZE_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "summarizeIdentificationNumbersByOffenderOnDate";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";

	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for
	 * identification numbers.
	 * 
	 * @param sessionFactory session factory
	 */
	public IdentificationNumberReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<IdentificationNumberSummary> summarizeByOffenderOnDate(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<IdentificationNumberSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						SUMMARIZE_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return summaries;
	}
}