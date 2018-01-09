package omis.treatment.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;
import omis.treatment.dao.TreatmentCenterDao;
import omis.treatment.domain.TreatmentCenter;

/**
 * Hibernate implementation of data access object for treatment center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public class TreatmentCenterDaoHibernateImpl 
		extends GenericHibernateDaoImpl<TreatmentCenter>
		implements TreatmentCenterDao {
	
	/* Queries. */
	private final static String FIND_TREATMENT_CENTER_QUERY_NAME 
		= "findTreatmentCenter";
	
	private final static String FIND_TREATMENT_CENTER_EXCLUDING_QUERY_NAME 
		= "findTreatmentCenterExcluding";
	
	/* Parameters. */
	private final static String LOCATION_PARAM_NAME = "location";
	
	private final static String NAME_PARAM_NAME = "name";
	
	private final static String TELEPHONE_NUMBER_PARAM_NAME = "telephoneNumber";
	
	private final static String EXCLUDED_TREATMENT_CENTER_PARAM_NAME 
		= "excludedTreatmentCenter";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * prerelease center.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TreatmentCenterDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public TreatmentCenter find(Location location, String name, 
			Long telephoneNumber) {
		TreatmentCenter treatmentCenter
			= (TreatmentCenter) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_TREATMENT_CENTER_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.uniqueResult();
		return treatmentCenter;
	}

	/** {@inheritDoc} */
	@Override
	public TreatmentCenter findExcluding(Location location, String name, 
			Long telephoneNumber, TreatmentCenter excludedTreatmentCenter) {
		TreatmentCenter treatmentCenter
			= (TreatmentCenter) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_TREATMENT_CENTER_EXCLUDING_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.setParameter(EXCLUDED_TREATMENT_CENTER_PARAM_NAME, 
					excludedTreatmentCenter)
			.uniqueResult();
		return treatmentCenter;
	}
}
