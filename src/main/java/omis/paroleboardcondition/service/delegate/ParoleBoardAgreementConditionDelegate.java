package omis.paroleboardcondition.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.condition.domain.ConditionClause;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.paroleboardcondition.dao.ParoleBoardAgreementConditionDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCondition;

/**
 * Parole Board Agreement Condition Delegate.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementConditionDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Parole Board Agreement Condition already exists with given "
			+ "Condition Clause and Category.";
	
	private final ParoleBoardAgreementConditionDao
		paroleBoardAgreementConditionDao;
	
	private final InstanceFactory<ParoleBoardAgreementCondition> 
		paroleBoardAgreementConditionInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ParoleBoardAgreementConditionDelegate.
	 * @param paroleBoardAgreementConditionDao - Parole Board Agreement
	 * Condition DAO
	 * @param paroleBoardAgreementConditionInstanceFactory - Parole Board
	 * Agreement Condition Instance Factory
	 * @param auditComponentRetriever - Audit Component Retriever
	 */
	public ParoleBoardAgreementConditionDelegate(
			final ParoleBoardAgreementConditionDao
				paroleBoardAgreementConditionDao,
			final InstanceFactory<ParoleBoardAgreementCondition> 
				paroleBoardAgreementConditionInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.paroleBoardAgreementConditionDao =
				paroleBoardAgreementConditionDao;
		this.paroleBoardAgreementConditionInstanceFactory =
				paroleBoardAgreementConditionInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a Parole Board Agreement Condition with the specified properties.
	 * @param conditionClause - Condition Clause
	 * @param category - Parole Board Agreement Category
	 * @return Newly created Parole Board Agreement Condition.
	 * @throws DuplicateEntityFoundException - When a Parole Board Agreement
	 * Condition already exists with the given Condition Clause and Category.
	 */
	public ParoleBoardAgreementCondition create(
			final ConditionClause conditionClause,
			final ParoleBoardAgreementCategory category)
				throws DuplicateEntityFoundException {
		if (this.paroleBoardAgreementConditionDao.find(
				conditionClause, category) != null) {
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ParoleBoardAgreementCondition paroleBoardAgreementCondition = 
				this.paroleBoardAgreementConditionInstanceFactory
					.createInstance();
		
		paroleBoardAgreementCondition.setConditionClause(conditionClause);
		paroleBoardAgreementCondition.setCategory(category);
		paroleBoardAgreementCondition.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		
		return this.paroleBoardAgreementConditionDao
				.makePersistent(paroleBoardAgreementCondition);
	}
	
	/**
	 * Removes the specified Parole Board Agreement Condition.
	 * @param paroleBoardAgreementCondition - Parole Board Agreement Condition
	 * to remove
	 */
	public void remove(
			final ParoleBoardAgreementCondition paroleBoardAgreementCondition) {
		this.paroleBoardAgreementConditionDao
			.makeTransient(paroleBoardAgreementCondition);
	}
}
