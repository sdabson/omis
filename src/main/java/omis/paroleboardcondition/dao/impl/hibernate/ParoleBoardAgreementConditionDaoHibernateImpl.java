package omis.paroleboardcondition.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.condition.domain.ConditionClause;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboardcondition.dao.ParoleBoardAgreementConditionDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCondition;

/**
 * Parole Board Agreement Condition DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementConditionDaoHibernateImpl
		extends GenericHibernateDaoImpl<ParoleBoardAgreementCondition>
		implements ParoleBoardAgreementConditionDao {
	
	private static final String FIND_QUERY_NAME =
			"findParoleBoardAgreementCondition";
	
	private static final String CONDITION_CLAUSE_PARAM_NAME = "conditionClause";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - Entity Name
	 */
	protected ParoleBoardAgreementConditionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public ParoleBoardAgreementCondition find(
			final ConditionClause conditionClause,
			final ParoleBoardAgreementCategory category) {
		ParoleBoardAgreementCondition paroleBoardAgreementCondition =
				(ParoleBoardAgreementCondition) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(CONDITION_CLAUSE_PARAM_NAME, conditionClause)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.uniqueResult();
		
		return paroleBoardAgreementCondition;
	}
}
