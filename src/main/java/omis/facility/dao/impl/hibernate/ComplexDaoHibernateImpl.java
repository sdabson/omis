/**
 * 
 */
package omis.facility.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.facility.dao.ComplexDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;

import org.hibernate.SessionFactory;

/**
 * Complex data access object hibernate implementation.
 * 
 * @author Joel Norris 
 * @version 0.1.1 (June 06, 2017)
 * @since OMIS 3.0
 */
public class ComplexDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Complex> 
	implements ComplexDao {
	
	/* Query names.  */
	
	private static final String FIND_QUERY_NAME = "findComplex";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findComplexExcluding";

	/* Parameter names. */
	
	private static final String COMPLEX_PARAM_NAME = "complex";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String FACILITY_PARAM_NAME = "facility";
	
	/**
	 * Instantiates a data access object for complex with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ComplexDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Complex> findByFacility(final Facility facility) {
		@SuppressWarnings("unchecked")
		List<Complex> complexes = getSessionFactory().getCurrentSession()
			.getNamedQuery("findComplexesByFacility")
			.setParameter("facility", facility)
			.list();
		return complexes;
	}
	
	/** {@inheritDoc} */
	@Override
	public Complex find(final String name, final Facility facility) {
		Complex complex = (Complex) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.uniqueResult();
		return complex;
	}
	
	/** {@inheritDoc} */
	@Override
	public Complex findExcluding(final String name, final Facility facility,
			final Complex complex) {
		Complex matchingComplex = (Complex) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(COMPLEX_PARAM_NAME, complex)
				.uniqueResult();
		return matchingComplex;
	}
}