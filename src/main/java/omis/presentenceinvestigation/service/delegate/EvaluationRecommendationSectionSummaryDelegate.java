package omis.presentenceinvestigation.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.EvaluationRecommendationSectionSummaryDao;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Evaluation Recommendation Section Summary Delegate
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"An Evaluation Recommendation Section Summary already exists for the" +
			" given Presentence Investigation Request.";
	
	private final EvaluationRecommendationSectionSummaryDao
		evaluationRecommendationSectionSummaryDao;
	
	private final InstanceFactory<EvaluationRecommendationSectionSummary> 
		evaluationRecommendationSectionSummaryInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for Evaluation Recommendation Section Summary Delegate
	 * @param evaluationRecommendationSectionSummaryDao
	 * @param evaluationRecommendationSectionSummaryInstanceFactory
	 * @param auditComponentRetriever
	 */
	public EvaluationRecommendationSectionSummaryDelegate(
			final EvaluationRecommendationSectionSummaryDao
				evaluationRecommendationSectionSummaryDao,
			final InstanceFactory<EvaluationRecommendationSectionSummary> 
				evaluationRecommendationSectionSummaryInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.evaluationRecommendationSectionSummaryDao =
				evaluationRecommendationSectionSummaryDao;
		this.evaluationRecommendationSectionSummaryInstanceFactory =
				evaluationRecommendationSectionSummaryInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an Evaluation Recommendation Section Summary with the specified
	 * properties
	 * @param text - String
	 * @param presentenceInvestigationRequest - Presentence Investigation
	 * Request
	 * @return Newly created Evaluation Recommendation Section Summary
	 * @throws DuplicateEntityFoundException - When an Evaluation Recommendation
	 * Section Summary already exists for the given Presentence Investigation
	 * Request
	 */
	public EvaluationRecommendationSectionSummary create(final String text,
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException{
		if(this.evaluationRecommendationSectionSummaryDao
				.find(presentenceInvestigationRequest) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EvaluationRecommendationSectionSummary evaluationRecommendationSectionSummary = 
				this.evaluationRecommendationSectionSummaryInstanceFactory.createInstance();
		
		evaluationRecommendationSectionSummary.setText(text);
		evaluationRecommendationSectionSummary
			.setPresentenceInvestigationRequest(presentenceInvestigationRequest);
		evaluationRecommendationSectionSummary.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		evaluationRecommendationSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.evaluationRecommendationSectionSummaryDao.makePersistent(
				evaluationRecommendationSectionSummary);
	}
	
	/**
	 * Updates the given Evaluation Recommendation Section Summary with the specified
	 * properties
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary to update
	 * @param text - String
	 * @return Updated Evaluation Recommendation Section Summary
	 * @throws DuplicateEntityFoundException - When an Evaluation Recommendation
	 * Section Summary already exists for the given Presentence Investigation
	 * Request
	 */
	public EvaluationRecommendationSectionSummary update(
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary, final String text)
					throws DuplicateEntityFoundException{
		if(this.evaluationRecommendationSectionSummaryDao.findExcluding(
				evaluationRecommendationSectionSummary
					.getPresentenceInvestigationRequest(),
				evaluationRecommendationSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		evaluationRecommendationSectionSummary.setText(text);
		evaluationRecommendationSectionSummary.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.evaluationRecommendationSectionSummaryDao.makePersistent(
				evaluationRecommendationSectionSummary);
	}
	
	/**
	 * Removes the specified Evaluation Recommendation Section Summary
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary to remove
	 */
	public void remove(final EvaluationRecommendationSectionSummary
			evaluationRecommendationSectionSummary){
		this.evaluationRecommendationSectionSummaryDao.makeTransient(
				evaluationRecommendationSectionSummary);
	}
	
	/**
	 * Returns an Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request
	 * @param presentenceInvestigationRequest - Presentence Investigation Request
	 * @return Evaluation Recommendation Section Summary for the specified
	 * Presentence Investigation Request
	 */
	public EvaluationRecommendationSectionSummary find(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.evaluationRecommendationSectionSummaryDao.find(
				presentenceInvestigationRequest);
	}
	
}
