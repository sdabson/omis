package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.OtherPertinentInformationSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.OtherPertinentInformationSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.OtherPertinentInformationSectionSummaryNoteDelegate;

/**
 * OtherPertinentInformationSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryServiceImpl
		implements OtherPertinentInformationSectionSummaryService {
	
	private final OtherPertinentInformationSectionSummaryDelegate
		otherPertinentInformationSectionSummaryDelegate;
	
	private final OtherPertinentInformationSectionSummaryNoteDelegate
		otherPertinentInformationSectionSummaryNoteDelegate;
	
	/**
	 * @param otherPertinentInformationSectionSummaryDelegate
	 * @param otherPertinentInformationSectionSummaryNoteDelegate
	 */
	public OtherPertinentInformationSectionSummaryServiceImpl(
			final OtherPertinentInformationSectionSummaryDelegate
				otherPertinentInformationSectionSummaryDelegate,
			final OtherPertinentInformationSectionSummaryNoteDelegate
				otherPertinentInformationSectionSummaryNoteDelegate) {
		this.otherPertinentInformationSectionSummaryDelegate =
				otherPertinentInformationSectionSummaryDelegate;
		this.otherPertinentInformationSectionSummaryNoteDelegate =
				otherPertinentInformationSectionSummaryNoteDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummary
		createOtherPertinentInformationSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String description)
					throws DuplicateEntityFoundException {
		return this.otherPertinentInformationSectionSummaryDelegate
				.create(presentenceInvestigationRequest, description);
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummary
		updateOtherPertinentInformationSectionSummary(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			final String description)
					throws DuplicateEntityFoundException {
		return this.otherPertinentInformationSectionSummaryDelegate
				.update(otherPertinentInformationSectionSummary, description);
	}

	/**{@inheritDoc} */
	@Override
	public void removeOtherPertinentInformationSectionSummary(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary) {
		this.otherPertinentInformationSectionSummaryDelegate
				.remove(otherPertinentInformationSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummaryNote
		createOtherPertinentInformationSectionSummaryNote(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.otherPertinentInformationSectionSummaryNoteDelegate
				.create(otherPertinentInformationSectionSummary,
						description, date);
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummaryNote
		updateOtherPertinentInformationSectionSummaryNote(
			final OtherPertinentInformationSectionSummaryNote
				otherPertinentInformationSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.otherPertinentInformationSectionSummaryNoteDelegate
				.update(otherPertinentInformationSectionSummaryNote,
						description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeOtherPertinentInformationSectionSummaryNote(
			final OtherPertinentInformationSectionSummaryNote
				otherPertinentInformationSectionSummaryNote) {
		this.otherPertinentInformationSectionSummaryNoteDelegate
				.remove(otherPertinentInformationSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public OtherPertinentInformationSectionSummary
		findByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.otherPertinentInformationSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<OtherPertinentInformationSectionSummaryNote>
		findSectionSummaryNotesByOtherPertinentInformationSectionSummary(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary) {
		return this.otherPertinentInformationSectionSummaryNoteDelegate
				.findByOtherPertinentInformationSectionSummary(
						otherPertinentInformationSectionSummary);
	}
}
