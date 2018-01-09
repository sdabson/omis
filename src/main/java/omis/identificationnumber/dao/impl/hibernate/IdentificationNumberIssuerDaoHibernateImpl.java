package omis.identificationnumber.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.identificationnumber.dao.IdentificationNumberIssuerDao;
import omis.identificationnumber.domain.IdentificationNumberIssuer;

/**
 * Hibernate implementation of data access object for identification number
 * issuers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class IdentificationNumberIssuerDaoHibernateImpl
		extends GenericHibernateDaoImpl<IdentificationNumberIssuer>
		implements IdentificationNumberIssuerDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findIdentificationNumberIssuers";
	
	private static final String FIND_QUERY_NAME
		= "findIdentificationNumberIssuer";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for
	 * identification number issuers.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public IdentificationNumberIssuerDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<IdentificationNumberIssuer> findAll() {
		@SuppressWarnings("unchecked")
		List<IdentificationNumberIssuer> issuers
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return issuers;
	}

	/** {@inheritDoc} */
	@Override
	public IdentificationNumberIssuer find(final String name) {
		IdentificationNumberIssuer issuer = (IdentificationNumberIssuer)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return issuer;
	}
}