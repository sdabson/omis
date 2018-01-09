/**
 * 
 */
package omis.specialneed.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.specialneed.dao.SpecialNeedDao;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedSource;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * special needs.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @version 0.1.1 (Sep 01, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SpecialNeed> 
	implements SpecialNeedDao {
	
	/* Query names. */
	
	private static final String FIND_SPECIAL_NEED_QUERY_NAME
		= "findSpecialNeed";
	
	private static final String FIND_SPECIAL_NEED_EXCLUDING_QUERY_NAME
		= "findSpecialNeedExcluding";
	
	private static final String FIND_SPECIAL_NEED_BY_CATEGORY_QUERY_NAME 
		= "findSpecialNeedByCategory";
	
	private static final String FIND_SPECIAL_NEED_BY_SOURCE_QUERY_NAME 
		= "findSpecialNeedBySource";
	
	/* Parameter names. */

	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String SOURCE_PARAM_NAME = "source";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String SPECIAL_NEED_PARAM_NAME = "specialNeed";
	
	private static final String SPECIAL_NEED_CLASSIFICATION_PARAM_NAME 
	= "classification";
	
	/* Constructor. */

	/**
	 * Instantiates a data access object for special need with the specified
	 * session factory and entity name.
	 *  
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SpecialNeedDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Service method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SpecialNeed> findBySpecialNeedCategory(
			final SpecialNeedCategory specialNeedCategory) {
		@SuppressWarnings("unchecked")
		List<SpecialNeed> specialNeeds = getSessionFactory().getCurrentSession()
		.getNamedQuery(FIND_SPECIAL_NEED_BY_CATEGORY_QUERY_NAME)
		.setParameter(CATEGORY_PARAM_NAME, specialNeedCategory)
		.list();
		return specialNeeds;
	}

	/** {@inheritDoc} */
	@Override
	public List<SpecialNeed> findBySource(
			final SpecialNeedSource specialNeedSource) {
		@SuppressWarnings("unchecked")
		List<SpecialNeed> specialNeeds = getSessionFactory().getCurrentSession()
		.getNamedQuery(FIND_SPECIAL_NEED_BY_SOURCE_QUERY_NAME)
		.setParameter(SOURCE_PARAM_NAME, specialNeedSource)
		.list();
		return specialNeeds;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeed find(Date startDate, 
			SpecialNeedClassification classification,
			SpecialNeedCategory category, SpecialNeedSource source,
			Offender offender) {
		SpecialNeed specialNeed = (SpecialNeed) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SPECIAL_NEED_QUERY_NAME)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setParameter(SPECIAL_NEED_CLASSIFICATION_PARAM_NAME, 
						classification)				
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(SOURCE_PARAM_NAME, source)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return specialNeed;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeed findExcluding(SpecialNeed specialNeed, Date startDate,
			SpecialNeedCategory category, SpecialNeedClassification classification,
			SpecialNeedSource source, Offender offender) {
		SpecialNeed matchingSpecialNeed = (SpecialNeed) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SPECIAL_NEED_EXCLUDING_QUERY_NAME)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(SOURCE_PARAM_NAME, source)
				.setParameter(SPECIAL_NEED_CLASSIFICATION_PARAM_NAME, 
						classification)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(SPECIAL_NEED_PARAM_NAME, specialNeed)
				.uniqueResult();
		return matchingSpecialNeed;
	}
}