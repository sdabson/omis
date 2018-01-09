package omis.offender.report.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.offender.domain.Offender;
import omis.offender.report.AlternativeOffenderNameProfileItemReportService;

/**
 * Hibernate implementation of report service for alternative offender name
 * profile items.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class AlternativeOffenderNameProfileItemReportServiceHibernateImpl
		implements AlternativeOffenderNameProfileItemReportService {
	
	/* Query names. */
	
	private static final String
	COUNT_BY_PERSON_AND_EFFECTIVE_DATE_QUERY_NAME
		= "countAlternativeNameAssociationForPersonOnDate";
	
	/* Parameter names. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String DATE_PARAM_NAME = "date";
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for
	 * alternative offender name profile items.
	 * 
	 * @param sessionFactory session factory
	 */
	public AlternativeOffenderNameProfileItemReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/* Method implementation. */
	
	/** {@inheritDoc} */
	@Override
	public Integer findAlternativeOffenderNameCountByOffenderAndDate(
			final Offender offender, final Date effectiveDate) {
		Long result = (Long) this.sessionFactory.getCurrentSession()
				.getNamedQuery(COUNT_BY_PERSON_AND_EFFECTIVE_DATE_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, effectiveDate)
				.uniqueResult();
		return result.intValue();
	}
}