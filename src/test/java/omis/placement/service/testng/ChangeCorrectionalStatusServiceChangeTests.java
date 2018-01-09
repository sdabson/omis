package omis.placement.service.testng;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.placement.service.ChangeCorrectionalStatusService;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeAction;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.exception.EndedPlacementTermException;
import omis.supervision.exception.IllegalCorrectionalStatusChangeException;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.exception.PlacementTermChangeReasonNotAllowedException;
import omis.supervision.exception.PlacementTermConflictException;
import omis.supervision.service.delegate.AllowedCorrectionalStatusChangeDelegate;
import omis.supervision.service.delegate.AllowedCorrectionalStatusChangeReasonRuleDelegate;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.supervision.service.delegate.PlacementTermChangeActionDelegate;
import omis.supervision.service.delegate.PlacementTermChangeReasonDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests for change method of service to change correctional status.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 20, 2016)
 * @since OMIS 3.0
 */
@Test(groups = {"placement"})
public class ChangeCorrectionalStatusServiceChangeTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("allowedCorrectionalStatusChangeDelegate")
	private AllowedCorrectionalStatusChangeDelegate
	allowedCorrectionalStatusChangeDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeActionDelegate")
	private PlacementTermChangeActionDelegate placementTermChangeActionDelegate;
	
	@Autowired
	@Qualifier("allowedCorrectionalStatusChangeReasonRuleDelegate")
	private AllowedCorrectionalStatusChangeReasonRuleDelegate
	allowedCorrectionalStatusChangeReasonRuleDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate
	supervisoryOrganizationTermDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("changeCorrectionalStatusService")
	private ChangeCorrectionalStatusService changeCorrectionalStatusService;
	
	/**
	 * Tests changing placement.
	 * 
	 * Throws {@code OffenderNotUnderSupervisionException} as offender is not
	 * under supervision.
	 * 
	 * @throws IllegalCorrectionalStatusChangeException if correctional status
	 * is illegal
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws PlacementTermChangeReasonNotAllowedException if change reason
	 * is not allowed
	 * @throws EndedPlacementTermException if placement is ended
	 */
	@Test(expectedExceptions = {OffenderNotUnderSupervisionException.class})
	public void testChangeWhileNotUnderSupervision()
			throws IllegalCorrectionalStatusChangeException,
				OffenderNotUnderSupervisionException,
				PlacementTermConflictException,
				PlacementTermChangeReasonNotAllowedException,
				EndedPlacementTermException {
		CorrectionalStatus secureCorrectionalStatus;
		try {
			secureCorrectionalStatus = this.correctionalStatusDelegate.create(
					"Secure", "SEC", true, (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Secure status exists", e);
		}
		CorrectionalStatus altSecureStatus;
		try {
			altSecureStatus = this.correctionalStatusDelegate.create(
					"Alt-Secure", "ALT", true, (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Alt-Secure status exists", e);
		}
		PlacementTermChangeReason programPlacementChangeReason;
		try {
			programPlacementChangeReason
				= this.placementTermChangeReasonDelegate.create(
						"Program Placement", (short) 1, false, false);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Program placement reason exists", e);
		}
		SupervisoryOrganization prerelease;
		try {
			prerelease = this.supervisoryOrganizationDelegate.create(
					"Prerelease", "PR", null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Prerelease exists", e);
		}
		final Offender offender
			= this.offenderDelegate.createWithoutIdentity(
					"Blofeld", "Ernst", "Stavro", null);
		Date effectiveDate = this.parseDateText("09/23/2012");
		PlacementTermChangeAction changeAction;
		try {
			changeAction = this.placementTermChangeActionDelegate.create(
					"Alternative Placement");
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Alternative placement exists", e);
		}
		this.allowCorrectionalStatusChange(
				changeAction, secureCorrectionalStatus, altSecureStatus);
		this.allowCorrectionalStatusChangeReason(
				secureCorrectionalStatus, altSecureStatus,
				programPlacementChangeReason);
		PlacementTerm placementTerm = this.changeCorrectionalStatusService
				.change(offender, altSecureStatus, prerelease,
						programPlacementChangeReason, effectiveDate, null);
		this.assertValues(placementTerm, altSecureStatus, prerelease,
				programPlacementChangeReason, null,
				new DateRange(effectiveDate, null));
	}
	
	/**
	 * Tests changing of plcament term.
	 * 
	 * @throws IllegalCorrectionalStatusChangeException if correctional
	 * status change is not allowed
	 * @throws OffenderNotUnderSupervisionException if offender is not
	 * under supervision
	 * @throws PlacementTermConflictException if change cases a placement
	 * conflict
	 * @throws PlacementTermChangeReasonNotAllowedException if change reason
	 * is not allowed
	 * @throws EndedPlacementTermException if placement is ended
	 */
	@Test
	public void testChangeWhileUnderSupervision()
			throws IllegalCorrectionalStatusChangeException,
				OffenderNotUnderSupervisionException,
				PlacementTermConflictException,
				PlacementTermChangeReasonNotAllowedException,
				EndedPlacementTermException {
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", "Stavro", null);
		CorrectionalStatus altSecure;
		try {
			altSecure = this.correctionalStatusDelegate
					.create("Alt-Secure", "ALT", true, (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Correctional status exists", e);
		}
		SupervisoryOrganization prerelease;
		try {
			prerelease = this.supervisoryOrganizationDelegate
					.create("Prerelease", "PR", null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Supervisory organization exists", e);
		}
		PlacementTermChangeReason beginSupervisionChangeReason;
		try {
			beginSupervisionChangeReason
				= this.placementTermChangeReasonDelegate
					.create("Begin Supervision", (short) 1, true, false);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Change reason exists", e);
		}
		Date secureStartDate = this.parseDateText("08/30/2009");
		this.createPlacementTerm(offender, altSecure, prerelease,
					beginSupervisionChangeReason, null, new DateRange(
							secureStartDate, null));
		CorrectionalStatus probation;
		try {
			probation = this.correctionalStatusDelegate
					.create("Probation", "PRO", false, (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Correctional status exists", e);
		}
		
		PlacementTermChangeAction action;
		try {
			action = this.placementTermChangeActionDelegate.create("Probation");
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Change action exists", e);
		}
		try {
			this.allowedCorrectionalStatusChangeDelegate.create(
					action, altSecure, probation);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Correctional status change allowed", e);
		}
		PlacementTermChangeReason beginProbationChangeReason;
		try {
			beginProbationChangeReason = this.placementTermChangeReasonDelegate
					.create("Begin Probation", (short) 1, true, false);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Change reason exists");
		}
		try {
			this.allowedCorrectionalStatusChangeReasonRuleDelegate
				.create(altSecure, probation, beginProbationChangeReason);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Change reason exists", e);
		}
		Date probationStartDate = this.parseDateText("07/30/2014");
		this.changeCorrectionalStatusService
				.change(offender, probation, prerelease,
						beginProbationChangeReason, probationStartDate, null);
		PlacementTerm placementTerm = this.placementTermDelegate
				.findForOffenderOnDate(offender, probationStartDate);
		this.assertValues(placementTerm, probation, prerelease,
				beginProbationChangeReason, null,
				new DateRange(probationStartDate, null));
	}
	
	// Creates placement term - business violations are not checked for
	private PlacementTerm createPlacementTerm(final Offender offender,
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization,
			final PlacementTermChangeReason changeReason,
			final PlacementTermChangeReason placementTermChangeReason,
			final DateRange dateRange) {
		CorrectionalStatusTerm correctionalStatusTerm;
		try {
			correctionalStatusTerm = this.correctionalStatusTermDelegate
				.create(offender, dateRange, correctionalStatus);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Correctional status term exists", e);
		}
		SupervisoryOrganizationTerm supervisoryOrganizationTerm;
		try {
			supervisoryOrganizationTerm = this.supervisoryOrganizationTermDelegate
				.create(offender, dateRange, supervisoryOrganization);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Correctional status term exists", e);
		}
		try {
			return this.placementTermDelegate
					.create(offender, dateRange, supervisoryOrganizationTerm, 
							correctionalStatusTerm, changeReason, null, false);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Placement term exists", e);
		}
	}

	// Allows correctional status change, does nothing if already allowed
	private void allowCorrectionalStatusChange(
			final PlacementTermChangeAction action,
			final CorrectionalStatus toCorrectionalStatus,
			final CorrectionalStatus fromCorrectionalStatus) {
		if (this.allowedCorrectionalStatusChangeDelegate
				.find(toCorrectionalStatus, fromCorrectionalStatus) == null) {
			try {
				this.allowedCorrectionalStatusChangeDelegate.create(
						action, fromCorrectionalStatus, toCorrectionalStatus);
			} catch (DuplicateEntityFoundException e) {
				throw new RuntimeException(
						"Correctional status change unexpectedly allowed", e);
			}
		}
	}
	
	// Allows change reason for correctional status change, does nothing if
	// already allowed
	private void allowCorrectionalStatusChangeReason(
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus,
			final PlacementTermChangeReason changeReason) {
		if (this.allowedCorrectionalStatusChangeReasonRuleDelegate.find(
				fromCorrectionalStatus, toCorrectionalStatus, changeReason)
					== null) {
			try {
				this.allowedCorrectionalStatusChangeReasonRuleDelegate.create(
						fromCorrectionalStatus, toCorrectionalStatus,
						changeReason);
			} catch (DuplicateEntityFoundException e) {
				throw new RuntimeException(
						"Reason for correctional status change already allowed",
						e);
			}
		}
	}
	
	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
	
	// Asserts placement term values
	private void assertValues(
			final PlacementTerm placementTerm,
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization,
			final PlacementTermChangeReason startChangeReason,
			final PlacementTermChangeReason endChangeReason,
			final DateRange dateRange) {
		assert correctionalStatus.equals(
				placementTerm.getCorrectionalStatusTerm()
					.getCorrectionalStatus())
			: String.format("Wrong correctional status: %s expected; %s found",
					correctionalStatus.getName(),
					placementTerm.getCorrectionalStatusTerm()
						.getCorrectionalStatus().getName());
		assert supervisoryOrganization.equals(
				placementTerm.getSupervisoryOrganizationTerm()
					.getSupervisoryOrganization())
			: String.format(
					"Wrong supervisory organization: %s expected; %s found",
					supervisoryOrganization.getName(),
					placementTerm.getSupervisoryOrganizationTerm()
						.getSupervisoryOrganization().getName());
		assert this.nullSafeEquals(startChangeReason,
				placementTerm.getStartChangeReason())
			: String.format("Wrong start change reason: %s expected; %s found",
				startChangeReason, placementTerm.getStartChangeReason());
		assert this.nullSafeEquals(endChangeReason,
				placementTerm.getEndChangeReason())
			: String.format("Wrong end change reason: %s expected; %s found",
				endChangeReason, placementTerm.getEndChangeReason());
		assert this.nullSafeEquals(dateRange, placementTerm.getDateRange())
			: String.format("Wrong date range: %s expected; %s found",
				dateRange, placementTerm.getDateRange());
	}
	
	// Returns true if objects are equal, false otherwise - null safe
	private boolean nullSafeEquals(
			final Serializable obj1, final Serializable obj2) {
		return (obj1 == null && obj2 == null)
				|| (obj1 != null && obj1.equals(obj2));
	}
}