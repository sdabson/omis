package omis.condition.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import omis.condition.dao.ConditionClauseDao;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionGroup;
import omis.condition.domain.ConditionTitle;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;
/**
 * Condition Clause Dao Hibernate Implementation.
 * 
 *@author unsigned
 *@author Annie Wahl 
 *@version 0.1.2 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionClauseDaoHibernateImpl
	extends GenericHibernateDaoImpl <ConditionClause>
	implements ConditionClauseDao {

	private static final String FIND_BY_CONDITION_GROUP_QUERY_NAME =
			"findConditionClausesByConditionGroup";
	
	private static final String
		FIND_BY_COURT_CASE_AGREEMENT_CATEGORY_QUERY_NAME =
			"findConditionClausesByCourtCaseAgreementCategory";
	
	private static final String
		FIND_BY_PAROLE_BOARD_AGREEMENTCATEGORY_QUERY_NAME =
			"findConditionClausesByParoleBoardAgreementCategory";
	
	private static final String
		FIND_BY_CATEGORY_EXCLUDING_AGREEMENT_QUERY_NAME =
			"findConditionClausesByCategoryExcludingCourtCaseAgreement";

	private static final String FIND_BY_CONDITION_TITLE_QUERY_NAME =
			"findConditionClausesByConditionTitle";
	
	private static final String CONDITION_GROUP_PARAM_NAME = "conditionGroup";
	
	private static final String COURT_CASE_AGREEMENT_CAT_PARAM_NAME =
			"courtCaseAgreementCategory";
	
	private static final String PAROLE_BOARD_AGREEMENT_CATEGORY_PARAM_NAME =
			"paroleBoardAgreementCategory";
	
	private static final String COURT_CASE_AGREEMENT_PARAM_NAME =
			"agreement";
	
	private static final String CONDITION_TITLE_PARAM_NAME = "conditionTitle";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String entity name
	 */
	protected ConditionClauseDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ConditionClause> findByConditionGroup(
			final ConditionGroup conditionGroup) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_CONDITION_GROUP_QUERY_NAME);
		q.setEntity(CONDITION_GROUP_PARAM_NAME, conditionGroup);
		@SuppressWarnings("unchecked")
		List<ConditionClause> retVal = (List<ConditionClause>) q.list();
		return retVal;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<ConditionClause> findByCourtCaseAgreementCategory(
			final CourtCaseAgreementCategory courtCaseAgreementCategory) {
		Query q = this.getSessionFactory().getCurrentSession().getNamedQuery(
				FIND_BY_COURT_CASE_AGREEMENT_CATEGORY_QUERY_NAME);
		q.setEntity(COURT_CASE_AGREEMENT_CAT_PARAM_NAME,
				courtCaseAgreementCategory);
		@SuppressWarnings("unchecked")
		List<ConditionClause> retVal = (List<ConditionClause>) q.list();
		return retVal;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<ConditionClause> findByParoleBoardAgreementCategory(
			final ParoleBoardAgreementCategory category) {
		@SuppressWarnings("unchecked")
		List<ConditionClause> conditionClauses = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_PAROLE_BOARD_AGREEMENTCATEGORY_QUERY_NAME)
				.setParameter(PAROLE_BOARD_AGREEMENT_CATEGORY_PARAM_NAME,
						category)
				.list();
		
		return conditionClauses;
	}

	/**{@inheritDoc} */
	@Override
	public List<ConditionClause>
		findByCourtCaseAgreementCategoryExcludingAgreement(
				final CourtCaseAgreementCategory courtCaseAgreementCategory,
				final CourtCaseAgreement courtCaseAgreement) {
		@SuppressWarnings("unchecked")
		List<ConditionClause> conditionClauses = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_CATEGORY_EXCLUDING_AGREEMENT_QUERY_NAME)
				.setParameter(COURT_CASE_AGREEMENT_CAT_PARAM_NAME,
						courtCaseAgreementCategory)
				.setParameter(COURT_CASE_AGREEMENT_PARAM_NAME,
						courtCaseAgreement.getAgreement())
				.list();
		
		return conditionClauses;
	}

	/**{@inheritDoc} */
	@Override
	public ConditionClause findByConditionTitle(
			final ConditionTitle conditionTitle) {
		ConditionClause conditionClause = (ConditionClause)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_CONDITION_TITLE_QUERY_NAME)
				.setParameter(CONDITION_TITLE_PARAM_NAME, conditionTitle)
				.uniqueResult();
		return conditionClause;
	}
}
