package omis.demographics.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.ComplexionDao;
import omis.demographics.domain.Complexion;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for complexions.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public class ComplexionDaoHibernateImpl
		extends GenericHibernateDaoImpl<Complexion>
		implements ComplexionDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findComplexions";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * complexes.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ComplexionDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Complexion> findAll() {
		@SuppressWarnings("unchecked")
		List<Complexion> complexions = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return complexions;
	}
}