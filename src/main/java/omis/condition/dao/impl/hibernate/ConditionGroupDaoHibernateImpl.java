package omis.condition.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.condition.dao.ConditionGroupDao;
import omis.condition.domain.Agreement;
import omis.condition.domain.ConditionGroup;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Condition Group Dao Hibernate Implementation.
 * 
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Oct 5, 2017)
 * @since OMIS 3.0
 */
public class ConditionGroupDaoHibernateImpl extends GenericHibernateDaoImpl
<ConditionGroup> implements ConditionGroupDao{
	
	private static final String FIND_UNUSED_BY_AGREEMENT_QUERY_NAME =
			"findUnusedConditionGroupsByAgreement";

	private static final String FIND_USED_BY_AGREEMENT_QUERY_NAME =
			"findUsedConditionGroupsByAgreement";

	private static final String AGREEMENT_PARAM_NAME = "agreement";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	public ConditionGroupDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<ConditionGroup> findAll() {
		return super.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public List<ConditionGroup> findUnusedByAgreement(final Agreement agreement) {
		@SuppressWarnings("unchecked")
		List<ConditionGroup> conditionGroups = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_UNUSED_BY_AGREEMENT_QUERY_NAME)
			.setParameter(AGREEMENT_PARAM_NAME, agreement)
			.list();
		
		return conditionGroups;
	}

	/**{@inheritDoc} */
	@Override
	public List<ConditionGroup> findUsedByAgreement(final Agreement agreement) {
		@SuppressWarnings("unchecked")
		List<ConditionGroup> conditionGroups = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_USED_BY_AGREEMENT_QUERY_NAME)
			.setParameter(AGREEMENT_PARAM_NAME, agreement)
			.list();
		
		return conditionGroups;
	}
	
}
