package omis.specialneed.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.specialneed.dao.SpecialNeedClassificationDao;
import omis.specialneed.domain.SpecialNeedClassification;

/**
 * Hibernate entity configurable implementation of data access object for
 * special need classification.
 *
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.1 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedClassificationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SpecialNeedClassification> 
	implements SpecialNeedClassificationDao {
		
	/* Query names. */
	
	private static final String FIND_ALL_VALID_QUERY_NAME 
		= "findAllValidSpecialNeedClassifications";

	private static final String FIND_QUERY_NAME 
		= "findSpecialNeedClassification";
	
	private static final String FIND_EXCLDING_QUERY_NAME 
		= "findSpecialNeedClassificationExcluding";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CLASSIFICATION_PARAM_NAME 
		= "excludedClassification";
	
	/* Constructor. */
	
	/**
	 * Instantiates an implementation of SpecialNeedClassificationDaoHibernateImpl
	 */
	public SpecialNeedClassificationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedClassification> findAll() {
		@SuppressWarnings("unchecked")
		List<SpecialNeedClassification> classifications = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_VALID_QUERY_NAME)
				.list();
		return classifications;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedClassification find(final String name) {
		SpecialNeedClassification specialNeedClassification = 
				(SpecialNeedClassification) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return specialNeedClassification;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedClassification findExcluding(final String name, 
			final SpecialNeedClassification excludedClassification) {
		SpecialNeedClassification specialNeedClassification = 
				(SpecialNeedClassification) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CLASSIFICATION_PARAM_NAME, 
						excludedClassification)
				.uniqueResult();
		return specialNeedClassification;
	}
}