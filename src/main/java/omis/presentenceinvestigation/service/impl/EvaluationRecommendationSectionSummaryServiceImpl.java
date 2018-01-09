package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.EvaluationRecommendationSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.EvaluationRecommendationSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.EvaluationRecommendationSectionSummaryNoteDelegate;

/**
 * Evaluation Recommendation Section Summary Service Impl
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryServiceImpl
		implements EvaluationRecommendationSectionSummaryService {
	
	private final EvaluationRecommendationSectionSummaryDelegate
		evaluationRecommendationSectionSummaryDelegate;
	
	private final EvaluationRecommendationSectionSummaryNoteDelegate
		evaluationRecommendationSectionSummaryNoteDelegate;
	
	/**
	 * @param evaluationRecommendationSectionSummaryDelegate
	 * @param evaluationRecommendationSectionSummaryNoteDelegate
	 */
	public EvaluationRecommendationSectionSummaryServiceImpl(
			final EvaluationRecommendationSectionSummaryDelegate
				evaluationRecommendationSectionSummaryDelegate,
			final EvaluationRecommendationSectionSummaryNoteDelegate
				evaluationRecommendationSectionSummaryNoteDelegate) {
		this.evaluationRecommendationSectionSummaryDelegate =
				evaluationRecommendationSectionSummaryDelegate;
		this.evaluationRecommendationSectionSummaryNoteDelegate =
				evaluationRecommendationSectionSummaryNoteDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummary
		createEvaluationRecommendationSectionSummary(final String text,
				final PresentenceInvestigationRequest
					presentenceInvestigationRequest)
						throws DuplicateEntityFoundException {
		return this.evaluationRecommendationSectionSummaryDelegate
				.create(text, presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummary
		updateEvaluationRecommendationSectionSummary(
				final EvaluationRecommendationSectionSummary
					evaluationRecommendationSectionSummary,
				final String text) throws DuplicateEntityFoundException {
		return this.evaluationRecommendationSectionSummaryDelegate
				.update(evaluationRecommendationSectionSummary, text);
	}

	/**{@inheritDoc} */
	@Override
	public void removeEvaluationRecommendationSectionSummary(
			final EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary) {
		this.evaluationRecommendationSectionSummaryDelegate
				.remove(evaluationRecommendationSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummaryNote
		createEvaluationRecommendationSectionSummaryNote(
				final EvaluationRecommendationSectionSummary
					evaluationRecommendationSectionSummary,
				final String description, final Date date)
						throws DuplicateEntityFoundException {
		return this.evaluationRecommendationSectionSummaryNoteDelegate
				.create(evaluationRecommendationSectionSummary, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummaryNote
		updateEvaluationRecommendationSectionSummaryNote(
				final EvaluationRecommendationSectionSummaryNote
					evaluationRecommendationSectionSummaryNote,
				final String description, final Date date)
						throws DuplicateEntityFoundException {
		return this.evaluationRecommendationSectionSummaryNoteDelegate
				.update(evaluationRecommendationSectionSummaryNote, description,
						date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeEvaluationRecommendationSectionSummaryNote(
			final EvaluationRecommendationSectionSummaryNote
				evaluationRecommendationSectionSummaryNote) {
		this.evaluationRecommendationSectionSummaryNoteDelegate
				.remove(evaluationRecommendationSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public EvaluationRecommendationSectionSummary
		findByPresentenceInvestigationRequest(
				final PresentenceInvestigationRequest
					presentenceInvestigationRequest) {
		return this.evaluationRecommendationSectionSummaryDelegate
				.find(presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<EvaluationRecommendationSectionSummaryNote>
		findSectionSummaryNotesByEvaluationRecommendationSectionSummary(
				final EvaluationRecommendationSectionSummary
					evaluationRecommendationSectionSummary) {
		return this.evaluationRecommendationSectionSummaryNoteDelegate
				.findByEvaluationRecommendationSectionSummary(
						evaluationRecommendationSectionSummary);
	}
}
