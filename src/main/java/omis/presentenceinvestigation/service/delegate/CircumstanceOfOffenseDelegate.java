package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.CircumstanceOfOffenseDao;
import omis.presentenceinvestigation.domain.CircumstanceOfOffense;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.component.DefendantsStatement;

/**
 * CircumstanceOfOffenseDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class CircumstanceOfOffenseDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Circumstance Of Offense already exists for specified "
			+ "Offense Section Summary.";
	
	private final CircumstanceOfOffenseDao circumstanceOfOffenseDao;
	
	private final InstanceFactory<CircumstanceOfOffense> 
		circumstanceOfOffenseInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for CircumstanceOfOffenseDelegate
	 * @param circumstanceOfOffenseDao
	 * @param circumstanceOfOffenseInstanceFactory
	 * @param auditComponentRetriever
	 */
	public CircumstanceOfOffenseDelegate(
			final CircumstanceOfOffenseDao circumstanceOfOffenseDao,
			final InstanceFactory<CircumstanceOfOffense> 
				circumstanceOfOffenseInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.circumstanceOfOffenseDao = circumstanceOfOffenseDao;
		this.circumstanceOfOffenseInstanceFactory = circumstanceOfOffenseInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a CircumstanceOfOffense with specified properties
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @param document - Document
	 * @param defendantsStatementChargeReason - String
	 * @param defendantsStatementInvolvementReason - String
	 * @param defendantsStatementCourtRecommendation - String
	 * @return Newly created CircumstanceOfOffense
	 * @throws DuplicateEntityFoundException - when a CircumstanceOfOffense
	 * already exists with given OffenseSectionSummary
	 */
	public CircumstanceOfOffense create(
			final OffenseSectionSummary offenseSectionSummary,
			final Document document, final String defendantsStatementChargeReason,
			final String defendantsStatementInvolvementReason,
			final String defendantsStatementCourtRecommendation)
			throws DuplicateEntityFoundException{
		if(this.circumstanceOfOffenseDao.find(offenseSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		CircumstanceOfOffense circumstanceOfOffense = 
				this.circumstanceOfOffenseInstanceFactory.createInstance();
		
		DefendantsStatement statement = new DefendantsStatement(
				defendantsStatementChargeReason,
				defendantsStatementInvolvementReason,
				defendantsStatementCourtRecommendation);
		
		circumstanceOfOffense.setDocument(document);
		circumstanceOfOffense.setOffenseSectionSummary(offenseSectionSummary);
		circumstanceOfOffense.setStatement(statement);
		circumstanceOfOffense.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		circumstanceOfOffense.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.circumstanceOfOffenseDao.makePersistent(circumstanceOfOffense);
	}
	
	/**
	 * Updates specified CircumstanceOfOffense with given parameters
	 * @param circumstanceOfOffense - CircumstanceOfOffense to update
	 * @param document - Document
	 * @param defendantsStatementChargeReason - String
	 * @param defendantsStatementInvolvementReason - String
	 * @param defendantsStatementCourtRecommendation - String
	 * @return Updated CircumstanceOfOffense
	 * @throws DuplicateEntityFoundException - when a CircumstanceOfOffense
	 * already exists with given OffenseSectionSummary
	 */
	public CircumstanceOfOffense update(
			final CircumstanceOfOffense circumstanceOfOffense,
			final Document document, final String defendantsStatementChargeReason,
			final String defendantsStatementInvolvementReason,
			final String defendantsStatementCourtRecommendation)
					throws DuplicateEntityFoundException{
		if(this.circumstanceOfOffenseDao.findExcluding(
				circumstanceOfOffense.getOffenseSectionSummary(),
				circumstanceOfOffense) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		DefendantsStatement statement = new DefendantsStatement(
				defendantsStatementChargeReason,
				defendantsStatementInvolvementReason,
				defendantsStatementCourtRecommendation);
		
		circumstanceOfOffense.setDocument(document);
		circumstanceOfOffense.setStatement(statement);
		circumstanceOfOffense.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.circumstanceOfOffenseDao.makePersistent(circumstanceOfOffense);
	}
	
	/**
	 * Removes specified CircumstanceOfOffense
	 * @param circumstanceOfOffense - CircumstanceOfOffense to remove
	 */
	public void remove(final CircumstanceOfOffense circumstanceOfOffense){
		this.circumstanceOfOffenseDao.makeTransient(circumstanceOfOffense);
	}
	
	/**
	 * Returns the CircumstanceOfOffense for specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @return CircumstanceOfOffense for specified OffenseSectionSummary
	 */
	public CircumstanceOfOffense findByOffenseSectionSummary(
			final OffenseSectionSummary offenseSectionSummary){
		return this.circumstanceOfOffenseDao.find(offenseSectionSummary);
	}
	
}
