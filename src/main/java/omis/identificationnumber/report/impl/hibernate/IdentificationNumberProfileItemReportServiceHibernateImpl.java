package omis.identificationnumber.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.identificationnumber.report.IdentificationNumberProfileItemReportService;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of report service for profile item for
 * identification numbers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberProfileItemReportServiceHibernateImpl
		implements IdentificationNumberProfileItemReportService {
	
	/* Query names */
	
	private static final String COUNT_FOR_OFFENDER_ON_DATE_QUERY_NAME
		= "countIdentificationNumbersForOffenderOnDate";
	
	/* Parameter names */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";

	/* Resources */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors */
	
	/**
	 * Instantiates Hibernate implementation of report service for profile
	 * item for identification numbers.
	 * 
	 * @param sessionFactory session factory
	 */
	public IdentificationNumberProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override
	public Integer countForOffenderOnDate(
			final Offender offender, final Date date) {
		Long count = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(COUNT_FOR_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		return count.intValue();
	}
}