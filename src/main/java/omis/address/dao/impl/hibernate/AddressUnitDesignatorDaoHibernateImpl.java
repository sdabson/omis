package omis.address.dao.impl.hibernate;

import java.util.List;

import omis.address.dao.AddressUnitDesignatorDao;
import omis.address.domain.AddressUnitDesignator;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for address unit designators.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.1 Aug 4, 2015)
 * @since OMIS 3.0
 */
public class AddressUnitDesignatorDaoHibernateImpl extends
		GenericHibernateDaoImpl<AddressUnitDesignator> implements
		AddressUnitDesignatorDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_ADDRESS_UNIT_DESIGNATORS_QUERY_NAME 
		= "findAllAddressUnitDesignators";
	
	private static final String FIND_QUERY_NAME = "findAddressUnitDesignator";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * address unit designators.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AddressUnitDesignatorDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<AddressUnitDesignator> findAll() {
		@SuppressWarnings("unchecked")
		List<AddressUnitDesignator> designators = getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ALL_ADDRESS_UNIT_DESIGNATORS_QUERY_NAME)
			.list();
		return designators;
	}

	/** {@inheritDoc} */
	@Override
	public AddressUnitDesignator find(final String name) {
		AddressUnitDesignator unitDesignator = (AddressUnitDesignator)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return unitDesignator;
	}
}