package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.JailAdjustmentSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.JailAdjustmentSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.JailAdjustmentSectionSummaryNoteDelegate;

/**
 * JailAdjustmentSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 24, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryServiceImpl
		implements JailAdjustmentSectionSummaryService {
	
	private final JailAdjustmentSectionSummaryDelegate 
			jailAdjustmentSectionSummaryDelegate;
	
	private final JailAdjustmentSectionSummaryNoteDelegate
			jailAdjustmentSectionSummaryNoteDelegate;
	
	
	
	/**
	 * @param jailAdjustmentSectionSummaryDelegate
	 * @param jailAdjustmentSectionSummaryNoteDelegate
	 */
	public JailAdjustmentSectionSummaryServiceImpl(
			final JailAdjustmentSectionSummaryDelegate
					jailAdjustmentSectionSummaryDelegate,
			final JailAdjustmentSectionSummaryNoteDelegate 
					jailAdjustmentSectionSummaryNoteDelegate) {
		this.jailAdjustmentSectionSummaryDelegate =
				jailAdjustmentSectionSummaryDelegate;
		this.jailAdjustmentSectionSummaryNoteDelegate =
				jailAdjustmentSectionSummaryNoteDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummary createJailAdjustmentSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException {
		return this.jailAdjustmentSectionSummaryDelegate
				.create(presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummaryNote
		createJailAdjustmentSectionSummaryNote(final String description,
				final Date date, final JailAdjustmentSectionSummary
					jailAdjustmentSectionSummary)
							throws DuplicateEntityFoundException {
		return this.jailAdjustmentSectionSummaryNoteDelegate
				.create(description, date, jailAdjustmentSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummaryNote
		updateJailAdjustmentSectionSummaryNote(
				final JailAdjustmentSectionSummaryNote
						jailAdjustmentSectionSummaryNote,
				final String description, final Date date)
						throws DuplicateEntityFoundException {
		return this.jailAdjustmentSectionSummaryNoteDelegate.update(
				jailAdjustmentSectionSummaryNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeJailAdjustmentSectionSummaryNote(
				final JailAdjustmentSectionSummaryNote
						jailAdjustmentSectionSummaryNote) {
		this.jailAdjustmentSectionSummaryNoteDelegate
				.remove(jailAdjustmentSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public JailAdjustmentSectionSummary
		findJailAdjustmentSectionSummaryByPresentenceInvestigationRequest(
				final PresentenceInvestigationRequest
						presentenceInvestigationRequest) {
		return this.jailAdjustmentSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<JailAdjustmentSectionSummaryNote>
		findJailAdjustmentSectionSummaryNotesByJailAdjustmentSectionSummary(
				final JailAdjustmentSectionSummary jailAdjustmentSectionSummary) {
		return this.jailAdjustmentSectionSummaryNoteDelegate
				.findByJailAdjustmentSectionSummary(jailAdjustmentSectionSummary);
	}

}
