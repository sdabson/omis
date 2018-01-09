package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.EvaluationRecommendationSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;

/**
 * Evaluation Recommendation Section Summary Note Delegate
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Evaluation Recommendation Section Summary Note already exists with " +
			"given Date and Description for the specified Evaluation " +
			"Recommendation Section Summary.";
	
	private final EvaluationRecommendationSectionSummaryNoteDao 
			evaluationRecommendationSectionSummaryNoteDao;
	
	private final InstanceFactory<EvaluationRecommendationSectionSummaryNote> 
		evaluationRecommendationSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for EvaluationRecommendationSectionSummaryNoteDelegate
	 * @param evaluationRecommendationSectionSummaryNoteDao
	 * @param evaluationRecommendationSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public EvaluationRecommendationSectionSummaryNoteDelegate(
			final EvaluationRecommendationSectionSummaryNoteDao 
				evaluationRecommendationSectionSummaryNoteDao,
			final InstanceFactory<EvaluationRecommendationSectionSummaryNote> 
				evaluationRecommendationSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.evaluationRecommendationSectionSummaryNoteDao =
				evaluationRecommendationSectionSummaryNoteDao;
		this.evaluationRecommendationSectionSummaryNoteInstanceFactory =
				evaluationRecommendationSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an Evaluation Recommendation Section Summary Note with the
	 * specified properties
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary
	 * @param description - String
	 * @param date - Date
	 * @return Newly created Evaluation Recommendation Section Summary Note
	 * @throws DuplicateEntityFoundException - When an Evaluation Recommendation
	 * Section Summary Note already exists for the Evaluation Recommendation
	 * Section Summary with the given date and description
	 */
	public EvaluationRecommendationSectionSummaryNote create(
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.evaluationRecommendationSectionSummaryNoteDao.find(description,
				date, evaluationRecommendationSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EvaluationRecommendationSectionSummaryNote
			evaluationRecommendationSectionSummaryNote = 
				this.evaluationRecommendationSectionSummaryNoteInstanceFactory
				.createInstance();
		
		evaluationRecommendationSectionSummaryNote.setDate(date);
		evaluationRecommendationSectionSummaryNote.setDescription(description);
		evaluationRecommendationSectionSummaryNote
			.setEvaluationRecommendationSectionSummary(
					evaluationRecommendationSectionSummary);
		evaluationRecommendationSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		evaluationRecommendationSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.evaluationRecommendationSectionSummaryNoteDao
				.makePersistent(evaluationRecommendationSectionSummaryNote);
	}
	
	/**
	 * Updates the given Evaluation Recommendation Section Summary Note with the
	 * specified properties
	 * @param evaluationRecommendationSectionSummaryNote - Evaluation Recommendation
	 * Section Summary Note to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated Evaluation Recommendation Section Summary Note
	 * @throws DuplicateEntityFoundException - When a Evaluation Recommendation
	 * Section Summary Note already exists for the Evaluation Recommendation
	 * Section Summary with the given date and description
	 */
	public EvaluationRecommendationSectionSummaryNote update(
			final EvaluationRecommendationSectionSummaryNote
				evaluationRecommendationSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.evaluationRecommendationSectionSummaryNoteDao.findExcluding(
				description, date, evaluationRecommendationSectionSummaryNote
				.getEvaluationRecommendationSectionSummary(),
				evaluationRecommendationSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		evaluationRecommendationSectionSummaryNote.setDate(date);
		evaluationRecommendationSectionSummaryNote.setDescription(description);
		evaluationRecommendationSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.evaluationRecommendationSectionSummaryNoteDao
				.makePersistent(evaluationRecommendationSectionSummaryNote);
	}
	
	/**
	 * Removes an Evaluation Recommendation Section Summary Note
	 * @param evaluationRecommendationSectionSummaryNote - Evaluation
	 * Recommendation Section Summary Note to remove
	 */
	public void remove(final EvaluationRecommendationSectionSummaryNote
			evaluationRecommendationSectionSummaryNote){
		this.evaluationRecommendationSectionSummaryNoteDao.makeTransient(
				evaluationRecommendationSectionSummaryNote);
	}
	
	/**
	 * Returns a list of Evaluation Recommendation Section Summary Notes by the
	 * specified Evaluation Recommendation Section Summary
	 * @param evaluationRecommendationSectionSummary - Evaluation Recommendation
	 * Section Summary
	 * @return List of Evaluation Recommendation Section Summary Notes by the
	 * specified Evaluation Recommendation Section Summary
	 */
	public List<EvaluationRecommendationSectionSummaryNote>
		findByEvaluationRecommendationSectionSummary(
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary){
		return this.evaluationRecommendationSectionSummaryNoteDao
				.findBySectionSummary(evaluationRecommendationSectionSummary);
	}
	
}
