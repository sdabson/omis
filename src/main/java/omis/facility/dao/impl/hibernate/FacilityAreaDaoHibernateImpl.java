package omis.facility.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.FacilityAreaDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;

/**
 * Facility area data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 14, 2016)
 * @since OMIS 3.0
 */
public class FacilityAreaDaoHibernateImpl
	extends GenericHibernateDaoImpl<FacilityArea>
	implements FacilityAreaDao {

	/* Query names. */
	
	private static final String FIND_AREAS_BY_FACILITY_QUERY_NAME
	= "findFacilityAreasByFacility";
	
	private static final String FIND_AREAS_BY_COMPLEX_QUERY_NAME
	= "findFacilityAreasByComplex";
	
	/* Parameter names. */
	
	private static final String COMPLEX_PARAM_NAME = "complex";
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	/* Constructors. */
	
	/**
	 * Instantiates an instance of facility area data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FacilityAreaDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<FacilityArea> findFacilityAreasByFacility(
			final Facility facility) {
		@SuppressWarnings("unchecked")
		List<FacilityArea> areas = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_AREAS_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.list();
		return areas;
	}

	/** {@inheritDoc} */
	@Override	
	public List<FacilityArea> findFacilityAreasByComplex(
			final Complex complex) {
		@SuppressWarnings("unchecked")
		List<FacilityArea> areas = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_AREAS_BY_COMPLEX_QUERY_NAME)
				.setParameter(COMPLEX_PARAM_NAME, complex)
				.list();
		return areas;
	}
}