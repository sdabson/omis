package omis.offender.report.delegate.hibernate;

import java.util.Date;
import java.util.List;

import omis.offender.report.OffenderSummary;

import org.hibernate.SessionFactory;

/**
 * Delegate for reporting offenders.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 11, 2015)
 * @since OMIS 3.0
 */
public class OffenderReportDelegate {

	/* Query names. */

	private static final String SUMMARIZE_BY_NAME_QUERY_NAME
		= "summarizeOffendersByName";

	private static final String SUMMARIZE_BY_OFFENDER_NUMBER_QUERY_NAME
		= "summarizeOffendersByOffenderNumber";

	private static final String SUMMARIZE_BY_SOCIAL_SECURITY_NUMBER_QUERY_NAME
		= "summarizeOffendersBySocialSecurityNumber";
	
	private static final String SUMMARIZE_BY_BIRTH_DATE_QUERY_NAME
		= "summarizeOffendersByBirthDate";
	
	/* Parameter names. */

	private static final String LAST_NAME_PARAM_NAME = "lastName";

	private static final String FIRST_NAME_PARAM_NAME = "firstName";

	private static final String OFFENDER_NUMBER_PARAM_NAME = "offenderNumber";

	private static final String SOCIAL_SECURITY_NUMBER_PARAM_NAME
		= "socialSecurityNumber";

	private static final String BIRTH_DATE_PARAM_NAME = "birthDate";
	
	private static final String QUERY_PARAM_NAME = "query";

	private static final String SUMMARIZE_BY_NAME_QUERY_QUERY_NAME
		= "summarizeOffendersByNameQuery";

	/* Resources. */

	private final SessionFactory sessionFactory;

	/* Constructors. */

	/**
	 * Instantiates delegate for reporting offenders.
	 * 
	 * @param sessionFactory session factory
	 */
	public OffenderReportDelegate(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Returns offender summaries by name.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @return offender summaries by name
	 */
	public List<OffenderSummary> summarizeByName(final String lastName,
			final String firstName) {
		@SuppressWarnings("unchecked")
		List<OffenderSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_NAME_QUERY_NAME)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName).list();
		return summaries;
	}

	/**
	 * Returns offender summaries by offender number.
	 * 
	 * @param offenderNumber offender number
	 * @return offender summaries by offender number
	 */
	public List<OffenderSummary> summarizeByOffenderNumber(
			final Integer offenderNumber) {
		@SuppressWarnings("unchecked")
		List<OffenderSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_NUMBER_QUERY_NAME)
				.setParameter(OFFENDER_NUMBER_PARAM_NAME, offenderNumber)
				.list();
		return summaries;
	}

	/**
	 * Returns offender summaries by social security number. 
	 * 
	 * @param socialSecurityNumber social security number
	 * @return offender summaries by social security number
	 */
	public List<OffenderSummary> summarizeBySocialSecurityNumber(
			final Integer socialSecurityNumber) {
		@SuppressWarnings("unchecked")
		List<OffenderSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(SUMMARIZE_BY_SOCIAL_SECURITY_NUMBER_QUERY_NAME)
			.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME,
					socialSecurityNumber)
			.list();
		return summaries;
	}
	
	/**
	 * Returns offender summaries by birth date.
	 * 
	 * @param birthDate birth date
	 * @return offender summaries by birth date
	 */
	public List<OffenderSummary> summarizeByBirthDate(final Date birthDate) {
		@SuppressWarnings("unchecked")
		List<OffenderSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(SUMMARIZE_BY_BIRTH_DATE_QUERY_NAME)
			.setTimestamp(BIRTH_DATE_PARAM_NAME, birthDate)
			.list();
		return summaries;
	}
	
	/**
	 * Returns offender summaries by name query.
	 * 
	 * @param query query
	 * @return offender summaries by name query
	 */
	public List<OffenderSummary> summarizeByNameQuery(final String query) {
		@SuppressWarnings("unchecked")
		List<OffenderSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(SUMMARIZE_BY_NAME_QUERY_QUERY_NAME)
			.setParameter(QUERY_PARAM_NAME, query)
			.list();
		return summaries;
	}
}