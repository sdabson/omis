package omis.demographics.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.EyeColorDao;
import omis.demographics.domain.EyeColor;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for eye colors.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public class EyeColorDaoHibernateImpl
		extends GenericHibernateDaoImpl<EyeColor>
		implements EyeColorDao {
	
	/* Query names. */
	
	private static final String FIND_BY_NAME_QUERY_NAME = "findEyeColorByName";
	
	private static final String FIND_ALL_QUERY_NAME = "findEyeColors";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for eye
	 * colors.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EyeColorDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public EyeColor findByName(final String name) {
		EyeColor eyeColor = (EyeColor) getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_NAME_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return eyeColor;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EyeColor> findAll() {
		@SuppressWarnings("unchecked")
		List<EyeColor> eyeColors = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return eyeColors;
	}
}