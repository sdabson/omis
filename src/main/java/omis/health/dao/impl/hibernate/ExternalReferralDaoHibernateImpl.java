package omis.health.dao.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ExternalReferralDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ProviderAssignment;
import omis.offender.domain.Offender;

/**
 * Hibernate implementation of data access object for external referrals.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralDaoHibernateImpl
		extends GenericHibernateDaoImpl<ExternalReferral>
		implements ExternalReferralDao {

	/* Queries. */
	
	private static final String FIND_BY_AUTHORIZATION_QUERY_NAME
		= "findExternalReferralByAuthorization";
	
	// TODO: Rename query - should simply be findExternalReferral - SA
	// This query finds by business key - there is no need to include
	// properties in query name when finding by business key
	private static final String
	FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_QUERY_NAME 
		= "findExternalReferralByOffenderDateTimeProvider";

	// TODO: Rename query - should simply be findExternalReferralExcluding - SA
	// This query finds by business key - there is no need to include
	// properties in query name when finding by business key
	private static final String
	FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_EXCLUDING_QUERY_NAME 
		= "findExternalReferralByOffenderDateTimeProviderExcluding";	
	
	/* Parameters. */
	
	private static final String AUTHORIZATION_PARAM_NAME = "authorization";

	private static final String OFFENDER_PARAM_NAME = 	"offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String START_TIME_PARAM_NAME = "startTime";
	
	private static final String PROVIDER_ASSIGNMENT_PARAM_NAME
		= "providerAssignment";

	private static final String EXCLUDED_REFERRAL_MODEL_KEY
		= "excludedExternalReferral";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * external referrals.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ExternalReferralDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferral findByAuthorization(
			final ExternalReferralAuthorization authorization) {
		ExternalReferral referral = (ExternalReferral)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_AUTHORIZATION_QUERY_NAME)
				.setParameter(AUTHORIZATION_PARAM_NAME, authorization)
				.uniqueResult();
		return referral;
	}
	/** {@inheritDoc} */
	@Override
	public ExternalReferral find(final Offender offender, final Date date,
			final Date startTime, final ProviderAssignment providerAssignment) {
		return (ExternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(START_TIME_PARAM_NAME, startTime)
				.setParameter(PROVIDER_ASSIGNMENT_PARAM_NAME, 
						providerAssignment).uniqueResult();
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferral findExcluding(
			final ExternalReferral excludedReferral,
			final Offender offender, final Date date, final Date startTime,
			final ProviderAssignment providerAssignment) {
		return (ExternalReferral) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
				FIND_BY_OFFENDER_DATE_TIME_AND_PROVIDER_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(START_TIME_PARAM_NAME, startTime)
				.setParameter(PROVIDER_ASSIGNMENT_PARAM_NAME, 
						providerAssignment)
				.setParameter(EXCLUDED_REFERRAL_MODEL_KEY, excludedReferral)
					.uniqueResult();
	}
}