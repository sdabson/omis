package omis.offenderflag.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.offenderflag.dao.OffenderFlagDao;
import omis.offenderflag.domain.OffenderFlag;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Hibernate implementation of data access object for offender flags.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public class OffenderFlagDaoHibernateImpl
		extends GenericHibernateDaoImpl<OffenderFlag>
		implements OffenderFlagDao {

	/* Queries. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findOffenderFlagsByOffender";
	
	private static final String FIND_QUERY_NAME
		= "findOffenderFlag";
	
	private static final String COUNT_MISSING_BY_OFFENDER
		= "countMissingOffenderFlags";
	
	/* Parameters. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String CATEGORIES_PARAM_NAME = "categories";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * offender flags.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderFlagDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderFlag> findByOffender(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<OffenderFlag> flags = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return flags;
	}
	
	/** {@inheritDoc} */
	@Override
	public long countMissingFlags(final Offender offender,
			final OffenderFlagCategory... categories) {
		long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_MISSING_BY_OFFENDER)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameterList(CATEGORIES_PARAM_NAME, categories)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderFlag find(
			final Offender offender, final OffenderFlagCategory category) {
		OffenderFlag flag = (OffenderFlag) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		return flag;
	}
}