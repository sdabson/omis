package omis.adaaccommodation.dao.impl.hibernate;

import java.util.Date;

import omis.adaaccommodation.dao.AuthorizationDao;
import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.Authorization;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.user.domain.UserAccount;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of the authorization data access object.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class AuthorizationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Authorization>
		implements AuthorizationDao {
	
	/* Queries. */
	private static final String FIND_AUTHORIZATION_QUERY_NAME
		= "findAuthorization";
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findAuthorizationExcluding";
	private static final String FIND_AUTHORIZATION_BY_ACCOMMODATION_QUERY_NAME
		= "findAuthorizationsByAccommodation";
		
	/* Parameters. */
	private static final String ACCOMMODATION_PARAM_NAME = "accommodation";
	private static final String AUTHORIZATION_DATE_PARAM_NAME 
		= "authorizationDate";
	private static final String AUTHORIZATION_USER_PARAM_NAME
		= "authorizationUser";
	private static final String AUTHORIZATION_SOURCE_CATEGORY_PARAM_NAME
		= "authorizationSourceCategory";
	private static final String START_DATE_PARAM_NAME = "startDate";
	private static final String END_DATE_PARAM_NAME = "endDate";
	private static final String AUTHORIZAIION_PARAM_NAME = "authorization";
	

	/**
	 * Instantiates a hibernate implementation of the data access object for
	 * authorization.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AuthorizationDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public Authorization find(final Accommodation accommodation,
			final Date authorizationDate, final UserAccount authorizationUser,
			final AuthorizationSourceCategory authorizationSourceCategory,
			final DateRange dateRange) {
		Authorization authorization = 
				(Authorization) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_AUTHORIZATION_QUERY_NAME)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.setDate(AUTHORIZATION_DATE_PARAM_NAME, authorizationDate)
				.setParameter(
						AUTHORIZATION_USER_PARAM_NAME, authorizationUser)
				.setParameter(
						AUTHORIZATION_SOURCE_CATEGORY_PARAM_NAME, 
						authorizationSourceCategory)
				.setTimestamp(START_DATE_PARAM_NAME, 
						DateRange.getStartDate(dateRange))
				.setTimestamp(END_DATE_PARAM_NAME, 
						DateRange.getEndDate(dateRange))
				.uniqueResult();
		return authorization;
	}

	/** {@inheritDoc} */
	@Override
	public Authorization findExcluding(final Authorization authorization, 
			final Accommodation accommodation,
			final Date authorizationDate, final UserAccount authorizationUser,
			final AuthorizationSourceCategory authorizationSourceCategory,
			final DateRange dateRange) {
		Authorization thisAuthorization = 
				(Authorization) this.getSessionFactory().getCurrentSession()
		.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
		.setParameter(AUTHORIZAIION_PARAM_NAME, authorization)
		.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
		.setDate(AUTHORIZATION_DATE_PARAM_NAME, authorizationDate)
		.setParameter(AUTHORIZATION_USER_PARAM_NAME, authorizationUser)
		.setParameter(AUTHORIZATION_SOURCE_CATEGORY_PARAM_NAME, 
				authorizationSourceCategory)
		.setTimestamp(START_DATE_PARAM_NAME, 
						DateRange.getStartDate(dateRange))
				.setTimestamp(END_DATE_PARAM_NAME, 
						DateRange.getEndDate(dateRange))
		.uniqueResult();
		return thisAuthorization;
	}

	/** {@inheritDoc} */
	@Override
	public Authorization findByAccommodation(
			final Accommodation accommodation) {
		Authorization authorization = (Authorization) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_AUTHORIZATION_BY_ACCOMMODATION_QUERY_NAME)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.uniqueResult();
		return authorization;
	}
}