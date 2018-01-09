package omis.demographics.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.HairColorDao;
import omis.demographics.domain.HairColor;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * hair colors.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public class HairColorDaoHibernateImpl
		extends GenericHibernateDaoImpl<HairColor>
		implements HairColorDao {
	
	/* Query names. */
	
	private static final String FIND_BY_NAME_QUERY_NAME = "findHairColorByName";
	
	private static final String FIND_ALL_QUERY_NAME = "findHairColors";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for hair
	 * colors.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HairColorDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public HairColor findByName(final String name) {
		HairColor hairColor =
				(HairColor) getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_NAME_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return hairColor;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<HairColor> findAll() {
		@SuppressWarnings("unchecked")
		List<HairColor> hairColors = getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return hairColors;
	}
}