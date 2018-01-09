package omis.health.dao.impl.hibernate;

import java.util.Date;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.domain.Facility;
import omis.health.dao.HealthRequestDao;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.HealthRequestStatus;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for health requests.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 15, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestDaoHibernateImpl
		extends GenericHibernateDaoImpl<HealthRequest>
		implements HealthRequestDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findHealthRequest";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findHealthRequestExcluding";
	
	/* Parameters. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String STATUS_PARAM_NAME = "status";

	private static final String EXCLUDED_HEALTH_REQUEST_PARAM_NAME
		= "excludedHealthRequest";

	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of health requests.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HealthRequestDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public HealthRequest find(
			final Offender offender, final Facility facility, 
			final Date date, final HealthRequestCategory category,
			final HealthRequestStatus status) {
		HealthRequest request = (HealthRequest)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(STATUS_PARAM_NAME, status)
				.uniqueResult();
		return request;
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthRequest findExcluding(
			final Offender offender, final Facility facility, 
			final Date date, final HealthRequestCategory category,
			final HealthRequestStatus status,
			final HealthRequest excludedHealthRequest) {
		HealthRequest request = (HealthRequest)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(STATUS_PARAM_NAME, status)
				.setParameter(EXCLUDED_HEALTH_REQUEST_PARAM_NAME,
						excludedHealthRequest)
				.uniqueResult();
		return request;
	}
}