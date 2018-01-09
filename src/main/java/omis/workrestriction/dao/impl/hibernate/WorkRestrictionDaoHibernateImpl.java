package omis.workrestriction.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.workrestriction.dao.WorkRestrictionDao;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionDaoHibernateImpl 
	extends GenericHibernateDaoImpl<WorkRestriction> 
		implements WorkRestrictionDao{
	
	/* Query names */
	
	private static final String FIND_WORK_RESTRICTION_QUERY_NAME 
		= "findWorkRestriction";
	
	private static final String FIND_WORK_RESTRICTION_EXCLUDING_QUERY_NAME 
		= "findWorkRestrictionExcluding";
	
	private static final String FIND_WORK_RESTRICTIONS_BY_OFFENDER_QUERY_NAME 
		= "findWorkRestrictionsByOffender";
	
	/* Parameter names */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String WORK_RESTRICTION_PARAM_NAME = "workRestriction";

	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WorkRestrictionDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestriction find(final Offender offender,
			final WorkRestrictionCategory category) {
		WorkRestriction workRestriction = (WorkRestriction)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_RESTRICTION_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		return workRestriction;
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestriction findExcluding(
			final WorkRestriction excludedWorkRestriction,
			final Offender offender,
			WorkRestrictionCategory category) {
		WorkRestriction workRestriction = (WorkRestriction)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_RESTRICTION_EXCLUDING_QUERY_NAME)
				.setParameter(WORK_RESTRICTION_PARAM_NAME,
						excludedWorkRestriction)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		return workRestriction;
	}

	/**{@inheritDoc} */
	@Override
	public List<WorkRestriction> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<WorkRestriction> workRestrictions = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_RESTRICTIONS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return workRestrictions;
	}

}
