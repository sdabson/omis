package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.MedicalFacilityDao;
import omis.health.domain.MedicalFacility;
import omis.organization.domain.Organization;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for medical facilities.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 23, 2014)
 * @since OMIS 3.0
 */
public class MedicalFacilityDaoHibernateImpl
		extends GenericHibernateDaoImpl<MedicalFacility>
		implements MedicalFacilityDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findMedicalFacilities";
	
	private static final String FIND_HOSPITALS_QUERY_NAME
		= "findMedicalFacilitiesWhereHospitals";

	private static final String FIND_HOSPITALS_BY_ORGANIZATION_QUERY_NAME
		= "findMedicalFacilitiesByOrganizationWhereHospitals";
	
	/* Parameter names. */
	
	private static final String ORGANIZATION_PARAM_NAME = "organization";
	
	/* Constructors. */

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * medical facilities.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MedicalFacilityDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findAll() {
		@SuppressWarnings("unchecked")
		List<MedicalFacility> facilities = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findHospitals() {
		@SuppressWarnings("unchecked")
		List<MedicalFacility> facilities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_HOSPITALS_QUERY_NAME)
			.list();
		return facilities;
	}

	/** {@inheritDoc} */
	@Override
	public List<MedicalFacility> findHospitalsByOrganization(
			final Organization organization) {
		@SuppressWarnings("unchecked")
		List<MedicalFacility> facilities = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_HOSPITALS_BY_ORGANIZATION_QUERY_NAME)
			.setParameter(ORGANIZATION_PARAM_NAME, organization)
			.list();
		return facilities;
	}
}