package omis.grievance.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.grievance.dao.GrievanceDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.offender.domain.Offender;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Grievance data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.0.2 (Oct 2, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Grievance>	
	implements GrievanceDao {

	/* Query names. */
	private static final String FIND_BY_OFFENDER_QUERY_NAME
 		= "findGrievanceByOffender";
	private static final String FIND_GRIEVANCE_QUERY_NAME
		= "findGrievance";
	private static final String FIND_EXCLUDING_GRIEVANCE_QUERY_NAME
		= "findExcludingGrievance";
	private static final String FIND_MAX_GRIEVANCE_NUMBER_QUERY_NAME
		= "findMaxGrievanceNumber";

	/* Parameter names. */
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	private static final String LOCATION_PARAMETER_NAME = "location";
	private static final String UNIT_PARAMETER_NAME = "unit";
	private static final String OPENED_DATE_PARAMETER_NAME = "openedDate";
	private static final String SUBJECT_PARAMETER_NAME = "subject";
	private static final String EXCLUDED_GRIEVANCES_PARAM_NAME
		= "excludedGrievances";
	private static final String GRIEVANCE_NUMBER_PARAMETER_NAME
		= "grievanceNumber";

	/**
	 * Instantiates an instance of grievance data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public GrievanceDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<Grievance> findByOffender(final Offender offender){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAMETER_NAME, offender);
		@SuppressWarnings("unchecked")
		List<Grievance> result = (List<Grievance>)q.list();
		return result; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Grievance find(final Offender offender, GrievanceLocation location,
		GrievanceUnit unit, Date openedDate, GrievanceSubject subject,
		Integer grievanceNumber){
		Grievance result = (Grievance)this.getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_GRIEVANCE_QUERY_NAME)
			.setParameter(OFFENDER_PARAMETER_NAME, offender)
			.setParameter(LOCATION_PARAMETER_NAME, location)
			.setParameter(UNIT_PARAMETER_NAME, unit)
			.setParameter(OPENED_DATE_PARAMETER_NAME, openedDate)
			.setParameter(SUBJECT_PARAMETER_NAME, subject)
			.setParameter(GRIEVANCE_NUMBER_PARAMETER_NAME, grievanceNumber)
			.uniqueResult(); 
		return result; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Grievance findExcluding(Offender offender, GrievanceLocation location,
			GrievanceUnit unit, Date openedDate, GrievanceSubject subject,
			Integer grievanceNumber, Grievance... excludedGrievances){
		Grievance result = (Grievance)this.getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EXCLUDING_GRIEVANCE_QUERY_NAME)
			.setParameter(OFFENDER_PARAMETER_NAME, offender)
			.setParameter(LOCATION_PARAMETER_NAME, location)
			.setParameter(UNIT_PARAMETER_NAME, unit)
			.setParameter(OPENED_DATE_PARAMETER_NAME, openedDate)
			.setParameter(SUBJECT_PARAMETER_NAME, subject)
			.setParameter(GRIEVANCE_NUMBER_PARAMETER_NAME, grievanceNumber)
			.setParameterList(EXCLUDED_GRIEVANCES_PARAM_NAME,
					excludedGrievances)
			.uniqueResult(); 
			return result;
	}

	/** {@inheritDoc} */
	@Override
	public int findMaxGrievanceNumber() {
		Integer max = (Integer) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_MAX_GRIEVANCE_NUMBER_QUERY_NAME)
				.uniqueResult();
		return max;
	}
}

