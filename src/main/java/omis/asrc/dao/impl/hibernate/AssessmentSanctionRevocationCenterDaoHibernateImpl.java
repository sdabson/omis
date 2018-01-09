package omis.asrc.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.asrc.dao.AssessmentSanctionRevocationCenterDao;
import omis.asrc.domain.AssessmentSanctionRevocationCenter;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.location.domain.Location;

/**
 * Hibernate implementation of data access object for assessment sanction 
 * revocation center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public class AssessmentSanctionRevocationCenterDaoHibernateImpl extends
		GenericHibernateDaoImpl<AssessmentSanctionRevocationCenter> 
		implements AssessmentSanctionRevocationCenterDao {

	/* Queries. */
	private final static String 
		FIND_ASSESSMENT_SANCTION_REVOCATION_CENTER_QUERY_NAME 
			= "findAssessmentSanctionRevocationCenter";
	
	private final static String 
		FIND_ASSESSMENT_SANCTION_REVOCATION_CENTER_EXCLUDING_QUERY_NAME 
			= "findAssessmentSanctionRevocationCenterExcluding";
	
	/* Parameters. */
	private final static String LOCATION_PARAM_NAME = "location";
	
	private final static String NAME_PARAM_NAME = "name";
	
	private final static String TELEPHONE_NUMBER_PARAM_NAME = "telephoneNumber";
	
	private final static String 
		EXCLUDED_ASSESSMENT_SANCTION_REVOCATION_CENTER_PARAM_NAME 
				= "excludedAssessmentSanctionRevocationCenter";
	
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * assessment sanction revocation center.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AssessmentSanctionRevocationCenterDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */ 
	@Override
	public AssessmentSanctionRevocationCenter find(Location location, 
			String name, Long telephoneNumber) {
		AssessmentSanctionRevocationCenter assessmentSanctionRevocationCenter
			= (AssessmentSanctionRevocationCenter) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_ASSESSMENT_SANCTION_REVOCATION_CENTER_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.uniqueResult();
		return assessmentSanctionRevocationCenter;
	}

	/** {@inheritDoc} */
	@Override
	public AssessmentSanctionRevocationCenter findExcluding(Location location, 
			String name, Long telephoneNumber,
			AssessmentSanctionRevocationCenter 
				excludedAssessmentSanctionRevocationCenter) {
		AssessmentSanctionRevocationCenter assessmentSanctionRevocationCenter
			= (AssessmentSanctionRevocationCenter) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(
					FIND_ASSESSMENT_SANCTION_REVOCATION_CENTER_EXCLUDING_QUERY_NAME)
			.setParameter(LOCATION_PARAM_NAME, location)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(TELEPHONE_NUMBER_PARAM_NAME, telephoneNumber)
			.setParameter(EXCLUDED_ASSESSMENT_SANCTION_REVOCATION_CENTER_PARAM_NAME, 
					excludedAssessmentSanctionRevocationCenter)
			.uniqueResult();
		return assessmentSanctionRevocationCenter;
	}
}
