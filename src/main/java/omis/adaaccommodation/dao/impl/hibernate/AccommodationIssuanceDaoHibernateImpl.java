package omis.adaaccommodation.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.dao.AccommodationIssuanceDao;
import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationIssuance;
import omis.adaaccommodation.domain.Authorization;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibenate implementation of the accommodation issuance data access object.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class AccommodationIssuanceDaoHibernateImpl 
	extends GenericHibernateDaoImpl<AccommodationIssuance> 
	implements AccommodationIssuanceDao {

	/* Queries. */
	private static final String FIND_ACCOMMODATION_ISSUANCE_QUERY_NAME
		= "findAccommodationIssuance";
	private static final String FIND_ACCOMMODATION_ISSUANCE_EXCLUDING_QUERY_NAME
		= "findAccommodationIssuanceExcluding";
	private static final String 
		FIND_ACCOMMODATION_ISSUANCES_BY_ACCOMMODATION_QUERY_NAME
		= "findAccommodationIssuancesByAccommodation";
	private static final String
		FIND_ASSOCIATED_AUTHORIZATION_QUERY_NAME
		= "findAssociatedAuthorization";
	
	/* Parameters. */
	private static final String ACCOMMODAITON_ISSUANCE_PARAM_NAME 
		= "accommodationIssuance";
	private static final String TIME_STAMP_PARAM_NAME = "timestamp";
	private static final String ACCOMMODATION_PARAM_NAME = "accommodation";
	
	/**
	 * Instantiates a hibernate implementation of the data access object for
	 * accommodation issuance.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AccommodationIssuanceDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public AccommodationIssuance find(Accommodation accommodation,
			Date timestamp) {
		AccommodationIssuance issuance = (AccommodationIssuance) 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACCOMMODATION_ISSUANCE_QUERY_NAME)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.setTimestamp(TIME_STAMP_PARAM_NAME, timestamp)
				.uniqueResult();
		return issuance;
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationIssuance findExcluding(
			AccommodationIssuance accommodationIssuance, 
			Accommodation accommodation, Date timeStamp) {
		AccommodationIssuance issuance = (AccommodationIssuance)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACCOMMODATION_ISSUANCE_EXCLUDING_QUERY_NAME)
				.setParameter(ACCOMMODAITON_ISSUANCE_PARAM_NAME, 
						accommodationIssuance)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.setParameter(TIME_STAMP_PARAM_NAME, timeStamp)
				.uniqueResult();
		return issuance;
	}

	/** {@inheritDoc} */
	@Override
	public List<AccommodationIssuance> findByAccommodation(
			Accommodation accommodation) {
		@SuppressWarnings("unchecked")
		List<AccommodationIssuance> issuances = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
					FIND_ACCOMMODATION_ISSUANCES_BY_ACCOMMODATION_QUERY_NAME)
				.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
				.list();
		return issuances;
	}
	
	/** {@inheritDoc} */
	@Override
	public Authorization findAssociated(final Accommodation accommodation) {
		Authorization authorization = 
				(Authorization) this.getSessionFactory().getCurrentSession()
		.getNamedQuery(FIND_ASSOCIATED_AUTHORIZATION_QUERY_NAME)
		.setParameter(ACCOMMODATION_PARAM_NAME, accommodation)
		.uniqueResult();
		return authorization;
	}
}