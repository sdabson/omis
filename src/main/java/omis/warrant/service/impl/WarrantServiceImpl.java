package omis.warrant.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.domain.Condition;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.ConditionDelegate;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.jail.domain.Jail;
import omis.jail.service.delegate.JailDelegate;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;
import omis.warrant.domain.WarrantCauseViolation;
import omis.warrant.domain.WarrantNote;
import omis.warrant.domain.WarrantReasonCategory;
import omis.warrant.service.WarrantService;
import omis.warrant.service.delegate.WarrantArrestDelegate;
import omis.warrant.service.delegate.WarrantCauseViolationDelegate;
import omis.warrant.service.delegate.WarrantDelegate;
import omis.warrant.service.delegate.WarrantNoteDelegate;

/**
 * WarrantServiceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantServiceImpl implements WarrantService {
	
	private final WarrantDelegate warrantDelegate;
	
	private final WarrantCauseViolationDelegate warrantCauseViolationDelegate;
	
	private final WarrantNoteDelegate warrantNoteDelegate;
	
	private final WarrantArrestDelegate warrantArrestDelegate;
	
	private final JailDelegate jailDelegate;
	
	private final CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	private final CourtCaseDelegate courtCaseDelegate;
	
	private final ConditionDelegate conditionDelegate;
	
	private final AgreementDelegate agreementDelegate;
	
	
	/**
	 * @param warrantDelegate
	 * @param warrantCauseViolationDelegate
	 * @param warrantViolationDelegate
	 * @param warrantNoteDelegate
	 * @param warrantArrestDelegate
	 * @param warrantReasonCategoryDelegate
	 * @param jailDelegate
	 * @param correctionalStatusTermDelegate
	 * @param courtCaseDelegate
	 * @param conditionDelegate
	 */
	public WarrantServiceImpl(final WarrantDelegate warrantDelegate,
			final WarrantCauseViolationDelegate warrantCauseViolationDelegate,
			final WarrantNoteDelegate warrantNoteDelegate,
			final WarrantArrestDelegate warrantArrestDelegate,
			final JailDelegate jailDelegate,
			final CorrectionalStatusTermDelegate correctionalStatusTermDelegate,
			final CourtCaseDelegate courtCaseDelegate,
			final ConditionDelegate conditionDelegate,
			final AgreementDelegate agreementDelegate) {
		this.warrantDelegate = warrantDelegate;
		this.warrantCauseViolationDelegate = warrantCauseViolationDelegate;
		this.warrantNoteDelegate = warrantNoteDelegate;
		this.warrantArrestDelegate = warrantArrestDelegate;
		this.jailDelegate = jailDelegate;
		this.correctionalStatusTermDelegate = correctionalStatusTermDelegate;
		this.courtCaseDelegate = courtCaseDelegate;
		this.conditionDelegate = conditionDelegate;
		this.agreementDelegate = agreementDelegate;
	}

	/**{@inheritDoc} */
	@Override
	public Warrant create(final Offender offender, final Date date,
			final String addressee, final Person issuedBy,
			final Boolean bondable, final BigDecimal bondRecommendation,
			final WarrantReasonCategory warrantReason)
					throws DuplicateEntityFoundException {
		return this.warrantDelegate.create(offender, date, addressee, issuedBy,
				bondable, bondRecommendation, warrantReason);
	}

	/**{@inheritDoc} */
	@Override
	public Warrant update(final Warrant warrant, final Date date,
			final String addressee, final Person issuedBy,
			final Boolean bondable, final BigDecimal bondRecommendation,
			final WarrantReasonCategory warrantReason)
					throws DuplicateEntityFoundException {
		return this.warrantDelegate.update(warrant, date, addressee, issuedBy,
				bondable, bondRecommendation, warrantReason);
	}

	/**{@inheritDoc} */
	@Override
	public void remove(final Warrant warrant) {
		this.warrantDelegate.remove(warrant);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCauseViolation createWarrantCauseViolation(
			final Warrant warrant, final CourtCase cause,
			final Condition condition, final String description)
					throws DuplicateEntityFoundException {
		return this.warrantCauseViolationDelegate.create(
				warrant, cause, condition, description);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCauseViolation updateWarrantCauseViolation(
			final WarrantCauseViolation warrantCauseViolation,
			final CourtCase cause, final Condition condition,
			final String description)
					throws DuplicateEntityFoundException {
		return this.warrantCauseViolationDelegate.update(
				warrantCauseViolation, cause, condition, description);
	}

	/**{@inheritDoc} */
	@Override
	public void removeWarrantCauseViolation(
			final WarrantCauseViolation warrantCauseViolation) {
		this.warrantCauseViolationDelegate.remove(warrantCauseViolation);
	}

	

	/**{@inheritDoc} */
	@Override
	public WarrantNote createWarrantNote(
			final Warrant warrant, final String note, final Date date)
					throws DuplicateEntityFoundException {
		return this.warrantNoteDelegate.create(warrant, note, date);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantNote updateWarrantNote(
			final WarrantNote warrantNote, final String note, final Date date)
					throws DuplicateEntityFoundException {
		return this.warrantNoteDelegate.update(warrantNote, note, date);
	}

	/**{@inheritDoc} */
	@Override
	public void removeWarrantNote(final WarrantNote warrantNote) {
		this.warrantNoteDelegate.remove(warrantNote);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantArrest createArrest(final Warrant warrant, final Date date,
			final Jail jail, final Date contactByDate)
					throws DuplicateEntityFoundException {
		return this.warrantArrestDelegate.create(
				warrant, date, jail, contactByDate);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantArrest updateArrest(final WarrantArrest warrantArrest,
			final Date date, final Jail jail, final Date contactByDate)
					throws DuplicateEntityFoundException {
		return this.warrantArrestDelegate.update(
				warrantArrest, date, jail, contactByDate);
	}

	/**{@inheritDoc} */
	@Override
	public void removeArrest(final WarrantArrest warrantArrest) {
		this.warrantArrestDelegate.remove(warrantArrest);
	}
	/**{@inheritDoc} */
	@Override
	public List<WarrantCauseViolation> findWarrantCauseViolationsByWarrant(
			final Warrant warrant) {
		return this.warrantCauseViolationDelegate.findByWarrant(warrant);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantArrest findWarrantArrestByWarrant(final Warrant warrant) {
		return this.warrantArrestDelegate.findByWarrant(warrant);
	}

	/**{@inheritDoc} */
	@Override
	public List<WarrantNote> findWarrantNotesByWarrant(final Warrant warrant) {
		return this.warrantNoteDelegate.findByWarrant(warrant);
	}

	/**{@inheritDoc} */
	@Override
	public List<Jail> findAllJails() {
		return this.jailDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public CorrectionalStatusTerm findCorrectionalStatusTermForOffenderOnDate(
			final Offender offender, final Date date) {
		return this.correctionalStatusTermDelegate.findForOffenderOnDate(
				offender, date);
	}

	/**{@inheritDoc} */
	@Override
	public List<CourtCase> findCourtCasesByDefendant(final Offender offender) {
		return this.courtCaseDelegate.findByDefendant(offender);
	}

	/**{@inheritDoc} */
	@Override
	public List<Condition> findConditionsByCourtCase(final CourtCase courtCase) {
		List<Condition> conditions = new ArrayList<Condition>();
		
		List<Agreement> agreements = this.agreementDelegate
				.findByDocket(courtCase.getDocket());
		
		for(Agreement agreement : agreements){
			conditions.addAll(this.conditionDelegate
					.findByAgreement(agreement));
		}
		
		return conditions;
	}
}
