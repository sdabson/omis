package omis.presentenceinvestigation.service.impl;

import java.util.Date;
import java.util.List;

import omis.docket.domain.Docket;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryNote;
import omis.presentenceinvestigation.service.VictimSectionSummaryService;
import omis.presentenceinvestigation.service.delegate.VictimSectionSummaryDelegate;
import omis.presentenceinvestigation.service.delegate.VictimSectionSummaryDocumentAssociationDelegate;
import omis.presentenceinvestigation.service.delegate.VictimSectionSummaryNoteDelegate;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.service.delegate.VictimAssociationDelegate;
import omis.victim.service.delegate.VictimDocketAssociationDelegate;

/**
 * VictimSectionSummaryServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 20, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryServiceImpl
	implements VictimSectionSummaryService {
	
	private final VictimSectionSummaryDelegate victimSectionSummaryDelegate;
	
	private final VictimSectionSummaryNoteDelegate
		victimSectionSummaryNoteDelegate;
	
	private final VictimDocketAssociationDelegate 
		victimDocketAssociationDelegate;
	
	private final VictimAssociationDelegate victimAssociationDelegate;
	
	/**
	 * @param victimSectionSummaryDelegate
	 * @param victimSectionSummaryNoteDelegate
	 * @param victimSectionSummaryDocumentAssociationDelegate
	 * @param documentDelegate
	 * @param documentTagDelegate
	 */
	public VictimSectionSummaryServiceImpl(
			final VictimSectionSummaryDelegate victimSectionSummaryDelegate,
			final VictimSectionSummaryNoteDelegate
				victimSectionSummaryNoteDelegate,
			final VictimSectionSummaryDocumentAssociationDelegate
				victimSectionSummaryDocumentAssociationDelegate,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate,
			final VictimDocketAssociationDelegate 
				victimDocketAssociationDelegate,
			final VictimAssociationDelegate victimAssociationDelegate) {
		this.victimSectionSummaryDelegate = victimSectionSummaryDelegate;
		this.victimSectionSummaryNoteDelegate = victimSectionSummaryNoteDelegate;
		this.victimDocketAssociationDelegate = victimDocketAssociationDelegate;
		this.victimAssociationDelegate = victimAssociationDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummary createVictimSectionSummary(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final String text)
					throws DuplicateEntityFoundException {
		return this.victimSectionSummaryDelegate.create(
				presentenceInvestigationRequest, text);
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummary updateVictimSectionSummary(
			final VictimSectionSummary victimSectionSummary, final String text)
					throws DuplicateEntityFoundException {
		return this.victimSectionSummaryDelegate.update(
				victimSectionSummary, text);
	}

	/**{@inheritDoc} */
	@Override
	public void removeVictimSectionSummary(
			final VictimSectionSummary victimSectionSummary) {
		this.victimSectionSummaryDelegate.remove(victimSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummaryNote createVictimSectionSummaryNote(
			final VictimSectionSummary victimSectionSummary,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.victimSectionSummaryNoteDelegate.create(
				victimSectionSummary, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummaryNote updateVictimSectionSummaryNote(
			final VictimSectionSummaryNote victimSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException {
		return this.victimSectionSummaryNoteDelegate.update(
				victimSectionSummaryNote, description, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeVictimSectionSummaryNote(
			final VictimSectionSummaryNote victimSectionSummaryNote) {
		this.victimSectionSummaryNoteDelegate.remove(victimSectionSummaryNote);
	}

	/**{@inheritDoc} */
	@Override
	public List<VictimSectionSummaryNote>
		findVictimSectionSummaryNotesByVictimSectionSummary(
			final VictimSectionSummary victimSectionSummary) {
		return this.victimSectionSummaryNoteDelegate
				.findByVictimSectionSummary(victimSectionSummary);
	}

	/**{@inheritDoc} */
	@Override
	public VictimSectionSummary
		findVictimSectionSummaryByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest) {
		return this.victimSectionSummaryDelegate
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
	}

	@Override
	public VictimDocketAssociation createVictimDocketAssociation(
			final VictimAssociation victimAssociation, final Docket docket) {
		throw new UnsupportedOperationException(
				"This method has not yet been implemented"); 
	}

	@Override
	public void removeVictimDocketAssociation(
			final VictimDocketAssociation victimDocketAssociation) {
		this.victimDocketAssociationDelegate.remove(victimDocketAssociation);
	}

	@Override
	public List<VictimAssociation> findVictimsByOffender(
			final Offender offender) {
		return this.victimAssociationDelegate.findByOffender(offender);
	}

	@Override
	public List<VictimDocketAssociation> findVictimAssociationByDocket(
			final Docket docket) {
		return this.victimDocketAssociationDelegate.findByDocket(docket);
	}
}
