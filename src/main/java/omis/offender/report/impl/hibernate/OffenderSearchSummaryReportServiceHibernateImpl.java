package omis.offender.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.demographics.domain.Sex;
import omis.location.domain.Location;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderSearchSummary;
import omis.offender.report.OffenderSearchSummaryReportService;
import omis.person.report.AlternateNameSummary;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Implementation of offender search summary service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 7, 2016)
 * @since OMIS 3.0
 */
public class OffenderSearchSummaryReportServiceHibernateImpl 
	implements OffenderSearchSummaryReportService {
	
	private final SessionFactory sessionFactory;
	
	/* Query names. */
	
	private static final String FIND_ALTERNATE_NAME_SUMMARIES_QUERY_NAME = "findAlternateNameSummariesByOffender";
	private static final String SEARCHING_FOR_OFFENDER_QUERY_NAME = "searchingForOffender";
	private static final String SEARCH_FOR_OFFENDER_QUERY_NAME = "searchForOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String OFFENDER_NUMBER_PARAM_NAME = "offenderNumber";
	private static final String FIRST_NAME_PARAM_NAME = "firstName";
	private static final String MIDDLE_NAME_PARAM_NAME = "middleName";
	private static final String LAST_NAME_PARAM_NAME = "lastName";
	private static final String LOCATION_PARAM_NAME = "location";
	private static final String SEX_PARAM_NAME = "sex";
	private static final String DATE_OF_BIRTH_PARAM_NAME = "dateOfBirth";
	private static final String SOCIAL_SECURITY_NUMBER_PARAM_NAME = "socialSecurityNumber";
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	private static final String ACTIVE_PARAM_NAME = "active";
	
	/**
	 * Constructor.
	 */
	public OffenderSearchSummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSearchSummary> search(final Integer offenderNumber, 
			final String firstName, final String middleName, 
			final String lastName, final Sex sex, final Location location, 
			final Date dateOfBirth, final Integer socialSecurityNumber,
			final Date effectiveDate, final Boolean active) {
		@SuppressWarnings("unchecked")
		List<OffenderSearchSummary> offenderSearchSummaries 
				= this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SEARCHING_FOR_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_NUMBER_PARAM_NAME, offenderNumber)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName)
				.setParameter(MIDDLE_NAME_PARAM_NAME, middleName)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setParameter(SEX_PARAM_NAME, sex)
				.setDate(DATE_OF_BIRTH_PARAM_NAME, dateOfBirth)
				.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME, socialSecurityNumber)
				.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.setBoolean(ACTIVE_PARAM_NAME, active)
				.list();

		return offenderSearchSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSearchSummary> searchForOffender(
			final Integer offenderNumber, final String firstName, 
			final String middleName, final String lastName, final Sex sex, 
			final Date dateOfBirth, final Integer socialSecurityNumber) {
		@SuppressWarnings("unchecked")
		List<OffenderSearchSummary> offenderSearchSummaries 
				= this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(SEARCH_FOR_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_NUMBER_PARAM_NAME, offenderNumber)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName)
				.setParameter(MIDDLE_NAME_PARAM_NAME, middleName)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(SEX_PARAM_NAME, sex)
				.setDate(DATE_OF_BIRTH_PARAM_NAME, dateOfBirth)
				.setParameter(SOCIAL_SECURITY_NUMBER_PARAM_NAME, socialSecurityNumber)
				.list();

		return offenderSearchSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<AlternateNameSummary> findAlternateNameSummariesByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<AlternateNameSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_ALTERNATE_NAME_SUMMARIES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganization findSupervisoryOrganizationByOffenderOnDate(
			final Offender offender, final Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatus findCorrectionalStatusByOffenderOnDate(
			final Offender offender, final Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Location findLocationByOffenderOnDate(final Offender offender, final Date date) {
		// TODO Auto-generated method stub
		return null;
	}
}