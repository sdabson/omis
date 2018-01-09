package omis.mail.dao.impl.hibernate;

import java.util.List;

import omis.address.domain.ZipCode;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.mail.dao.PoBoxDao;
import omis.mail.domain.PoBox;

import org.hibernate.SessionFactory;

/**
 * Post office box data access object.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @version 0.1.1 (June 2, 2015)
 * @since OMIS 3.0
 * @deprecated use {@code omis.contact.domain.component.PoBox} instead
 */
@Deprecated
public class PoBoxDaoHibernateImpl 
	extends GenericHibernateDaoImpl<PoBox> 
	implements PoBoxDao {
	
	/* Query names. */
	
	private static final String FIND_PO_BOX_QUERY_NAME = "findPoBox";
	
	private static final String FIND_PO_BOX_EXCLUDING_QUERY_NAME
		= "findPoBoxExcluding";
	
	private static final String FIND_PO_BOXES_BY_ZIP_CODE_QUERY_NAME
		= "findPoBoxesByZipCode";
	
	/* Parameter names. */
	
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String ZIP_CODE_PARAM_NAME = "zipCode";
	
	private static final String PO_BOX_PARAM_NAME = "poBox";

	/**
	 * Instantiates an instance of PO box data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PoBoxDaoHibernateImpl(final SessionFactory sessionFactory, 
		final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public PoBox find(String value, ZipCode zipCode) {
		PoBox poBox = (PoBox) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PO_BOX_QUERY_NAME)
			.setParameter(VALUE_PARAM_NAME, value)
			.setParameter(ZIP_CODE_PARAM_NAME, zipCode)
			.uniqueResult();
		return poBox;
	}

	/** {@inheritDoc} */
	@Override
	public PoBox findExcluding(PoBox poBox, String value, ZipCode zipCode) {
		PoBox matchingPoBox = (PoBox) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PO_BOX_EXCLUDING_QUERY_NAME)
			.setParameter(PO_BOX_PARAM_NAME, poBox)
			.setParameter(VALUE_PARAM_NAME, value)
			.setParameter(ZIP_CODE_PARAM_NAME, zipCode)
			.uniqueResult();
		return matchingPoBox;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PoBox> findPoBoxByZipCode(ZipCode zipCode) {
		@SuppressWarnings("unchecked")
		List<PoBox> poBoxes = (List<PoBox>) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_PO_BOXES_BY_ZIP_CODE_QUERY_NAME)
			.setParameter(ZIP_CODE_PARAM_NAME, zipCode)
			.list();
		return poBoxes;
	}
}