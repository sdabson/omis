package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.EducationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.EducationSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.EducationSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.EducationSectionSummaryNoteDelegate;

/**
 * EducationSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 16, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationSectionSummaryServiceImpl implements EducationSectionSummaryService {
	
	private final EducationSectionSummaryDelegate
			educationSectionSummaryDelegate;
	
	private final EducationSectionSummaryNoteDelegate
			educationSectionSummaryNoteDelegate;
	
	
	/**
	 * @param educationSectionSummaryDelegate
	 * @param educationSectionSummaryNoteDelegate
	 */
	public EducationSectionSummaryServiceImpl(
			final EducationSectionSummaryDelegate educationSectionSummaryDelegate,
			final EducationSectionSummaryNoteDelegate
				educationSectionSummaryNoteDelegate) {
		this.educationSectionSummaryDelegate = educationSectionSummaryDelegate;
		this.educationSectionSummaryNoteDelegate =
				educationSectionSummaryNoteDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummary createEducationSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)  throws DuplicateEntityFoundException{
		return this.educationSectionSummaryDelegate.create(
				presentenceInvestigationRequest, text);
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummary updateEducationSectionSummary(
			final EducationSectionSummary educationSectionSummary,
			final String text)  throws DuplicateEntityFoundException{
		return this.educationSectionSummaryDelegate.update(
				educationSectionSummary, text);
	}

	/**{@inheritDoc} */
	@Override
	public void removeEducationSectionSummary(
			final EducationSectionSummary educationSectionSummary) {
		this.educationSectionSummaryDelegate.remove(educationSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummaryNote createEducationSectionSummaryNote(
			final String description, final Date date,
			final EducationSectionSummary educationSectionSummary) 
					throws DuplicateEntityFoundException{
		return this.educationSectionSummaryNoteDelegate.create(description, date,
				educationSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummaryNote updateEducationSectionSummaryNote(
			final EducationSectionSummaryNote educationSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.educationSectionSummaryNoteDelegate.update(
				educationSectionSummaryNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeEducationSectionSummaryNote(
			final EducationSectionSummaryNote educationSectionSummaryNote) {
		this.educationSectionSummaryNoteDelegate.remove(
				educationSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public EducationSectionSummary
			findEducationSectionSummaryByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		return this.educationSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	/**{@inheritDoc} */
	@Override
	public List<EducationSectionSummaryNote> findNotesByEducationSectionSummary(
			final EducationSectionSummary educationSectionSummary) {
		return this.educationSectionSummaryNoteDelegate
				.findByEducationSectionSummary(educationSectionSummary);
	}

}
