package omis.paroleeligibility.dao.impl.hibernate;

import java.util.Date;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.paroleeligibility.dao.ParoleEligibilityDao;
import omis.paroleeligibility.domain.ParoleEligibility;

/**
 * Parole eligibility hibernate implementation.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ParoleEligibility> 
	implements ParoleEligibilityDao {

	/* Query names. */
	
	private static final String FIND_PAROLE_ELIGIBILITY_QUERY_NAME 
		= "findParoleEligibility";
	
	private static final String FIND_PAROLE_ELIGIBILITY_EXCLUDING_QUERY_NAME
		= "findParoleEligibilityExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String HEARING_ELIGIBILITY_DATE_PARAM_NAME
		= "hearingEligibilityDate";
	
	private static final String EXCLUDED_PAROLE_ELIGIBILITY_PARAM_NAME 
		= "excludedEligibility";
	
	/* Constructor */
	
	/**
	 * Instantiates a hibernate implementation of data access object for
	 * parole eligibility.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ParoleEligibilityDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ParoleEligibility find(final Offender offender, 
			final Date hearingEligibilityDate) {
		ParoleEligibility eligibility = (ParoleEligibility)
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PAROLE_ELIGIBILITY_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(HEARING_ELIGIBILITY_DATE_PARAM_NAME, 
					hearingEligibilityDate)
			.uniqueResult();
		return eligibility;
	}

	/** {@inheritDoc} */
	@Override
	public ParoleEligibility findExcluding(
			final ParoleEligibility excludedEligibility, 
			final Offender offender, 
			final Date hearingEligibilityDate) {
		ParoleEligibility eligibility = (ParoleEligibility)
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PAROLE_ELIGIBILITY_EXCLUDING_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(EXCLUDED_PAROLE_ELIGIBILITY_PARAM_NAME, 
					excludedEligibility)
			.setParameter(HEARING_ELIGIBILITY_DATE_PARAM_NAME, 
					hearingEligibilityDate)
			.uniqueResult();
		return eligibility;
	}

}
