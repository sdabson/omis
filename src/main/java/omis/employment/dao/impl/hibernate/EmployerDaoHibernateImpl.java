package omis.employment.dao.impl.hibernate;

import omis.address.domain.Address;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.employment.dao.EmployerDao;
import omis.employment.domain.Employer;

import org.hibernate.Query;
import org.hibernate.SessionFactory;


/**
 * Employer data access object hibernate implementation..
 * 
 * @author Yidong Li
 * @version 0.1.0 (Feb 20, 2014)
 * @since OMIS 3.0
 */

public class EmployerDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Employer> 
	implements EmployerDao  {
	/* Query names. */
	private static final String FIND_EMPLOYER_QUERY_NAME
 		= "findEmployer";
	
	/* Parameter names. */
	private static final String EMPLOYER_NAME_PARAMETER_NAME = "employerName";
	private static final String EMPLOYER_ADDRESS_PARAMETER_NAME = "employerAddress";
	
	/* Constructor. */
	
	/**
	 * Instantiates a Hibernate implementation of data access object for 
	 * employer with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EmployerDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public Employer findEmployer(final String employerName, 
		final Address employerAddress){
		Query q = getSessionFactory()
			.getCurrentSession() 
			.getNamedQuery(FIND_EMPLOYER_QUERY_NAME)
			.setParameter(EMPLOYER_NAME_PARAMETER_NAME, employerName)
			.setParameter(EMPLOYER_ADDRESS_PARAMETER_NAME, employerAddress);
		return (Employer) q.uniqueResult() ; 
	}
}
