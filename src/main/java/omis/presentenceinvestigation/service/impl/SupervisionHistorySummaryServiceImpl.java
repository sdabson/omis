package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.SupervisionHistoryNote;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;
import omis.presentenceinvestigation.service.SupervisionHistorySummaryService;
import omis.presentenceinvestigation.service.delegate.SupervisionHistoryNoteDelegate;
import omis.presentenceinvestigation.service.delegate.SupervisionHistorySummaryDelegate;

/**
 * Supervision history summary service implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistorySummaryServiceImpl 
	implements SupervisionHistorySummaryService {
	
	private final SupervisionHistorySummaryDelegate 
		supervisionHistorySummaryDelegate;
	
	private final SupervisionHistoryNoteDelegate supervisionHistoryNoteDelegate;

	/** Instantiates an implementation of 
	 * supervision history summary service impl */
	public SupervisionHistorySummaryServiceImpl(
			final SupervisionHistorySummaryDelegate 
				supervisionHistorySummaryDelegate,
			final SupervisionHistoryNoteDelegate 
				supervisionHistoryNoteDelegate) {
		this.supervisionHistorySummaryDelegate 
			= supervisionHistorySummaryDelegate;
		this.supervisionHistoryNoteDelegate = supervisionHistoryNoteDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistorySectionSummary 
	createSupervisionHistorySectionSummary(
			final String text,
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) 
					throws DuplicateEntityFoundException {
		return this.supervisionHistorySummaryDelegate
				.createSupervisionHistorySectionSummary(
						text, presentenceInvestigationRequest);
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistorySectionSummary updateSupervisionHistorySection(
			final SupervisionHistorySectionSummary 
				priorSupervisionSectionSummary,
			final String text, final PresentenceInvestigationRequest 
			presentenceInvestigationRequest) 
					throws DuplicateEntityFoundException {
		return this.supervisionHistorySummaryDelegate
				.updateSupervisionHistorySection(priorSupervisionSectionSummary, 
						text, presentenceInvestigationRequest);
	}

	/** {@inheritDoc} */
	@Override
	public void removeSupervisionHistorySectionSummary(
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary) {
		this.supervisionHistorySummaryDelegate
		.removeSupervisionHistorySectionSummary(
				supervisionHistorySectionSummary);
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistoryNote createSupervisionHistoryNote(
			final String description, final Date date,
			final SupervisionHistorySectionSummary 
				supervisionHistorySectionSummary) 
					throws DuplicateEntityFoundException {
		return this.supervisionHistoryNoteDelegate.createSupervisionHistoryNote(
				description, date, supervisionHistorySectionSummary);
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistoryNote updateSupervisionHistoryNote(
			final SupervisionHistoryNote supervisionHistoryNote, 
			final SupervisionHistorySectionSummary 
				supervisionHistorySectionSummary,
			String description, Date date) 
					throws DuplicateEntityFoundException {
		return this.supervisionHistoryNoteDelegate.updateSupervisionHistoryNote(
				supervisionHistoryNote, supervisionHistorySectionSummary, 
				description, date);
	}

	/** {@inheritDoc} */
	@Override
	public void removeSupervisionHistoryNote(
			final SupervisionHistoryNote supervisionHistoryNote) {
		this.supervisionHistoryNoteDelegate.removeSupervisionHistoryNote(
				supervisionHistoryNote);
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionHistorySectionSummary 
	findSupervisionHistorySectionSummaryByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) {
		return this.supervisionHistorySummaryDelegate
				.findSupervisionHistorySectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisionHistoryNote> 
	findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
			final SupervisionHistorySectionSummary 
			supervisionHistorySectionSummary) {
		return this.supervisionHistoryNoteDelegate
				.findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
						supervisionHistorySectionSummary);
	}
}