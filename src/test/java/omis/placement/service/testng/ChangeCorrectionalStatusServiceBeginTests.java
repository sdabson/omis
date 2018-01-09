package omis.placement.service.testng;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.placement.service.ChangeCorrectionalStatusService;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeAction;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.IllegalCorrectionalStatusChangeException;
import omis.supervision.exception.OffenderUnderSupervisionException;
import omis.supervision.exception.PlacementTermChangeReasonNotAllowedException;
import omis.supervision.exception.PlacementTermConflictException;
import omis.supervision.service.delegate.AllowedCorrectionalStatusChangeDelegate;
import omis.supervision.service.delegate.AllowedCorrectionalStatusChangeReasonRuleDelegate;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.PlacementTermChangeActionDelegate;
import omis.supervision.service.delegate.PlacementTermChangeReasonDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * Tests for begin method of service to change correctional status.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 20, 2016)
 * @since OMIS 3.0
 */
@Test(groups = {"placement"})
public class ChangeCorrectionalStatusServiceBeginTests
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
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("allowedCorrectionalStatusChangeReasonRuleDelegate")
	private AllowedCorrectionalStatusChangeReasonRuleDelegate
	allowedCorrectionalStatusChangeReasonRuleDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("changeCorrectionalStatusService")
	private ChangeCorrectionalStatusService changeCorrectionalStatusService;
	
	/**
	 * Tests beginning placement.
	 * 
	 * @throws PlacementTermConflictException if conflict exists 
	 * @throws PlacementTermChangeReasonNotAllowedException if reason is not
	 * allowed
	 * @throws OffenderUnderSupervisionException if offender is already under
	 * supervision 
	 * @throws IllegalCorrectionalStatusChangeException if status cannot
	 * be changed 
	 */
	@Test
	public void testBeginPlacement()
			throws IllegalCorrectionalStatusChangeException,
				OffenderUnderSupervisionException,
				PlacementTermChangeReasonNotAllowedException,
				PlacementTermConflictException {
		CorrectionalStatus secureCorrectionalStatus;
		try {
			secureCorrectionalStatus = this.correctionalStatusDelegate.create(
					"Secure", "SEC", true, (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Secure status exists", e);
		}
		PlacementTermChangeReason initialCommitChangeReason;
		try {
			initialCommitChangeReason = this.placementTermChangeReasonDelegate
				.create("Initial Commit", (short) 1, true, false);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Initial commit reason exists", e);
		}
		SupervisoryOrganization statePrison;
		try {
			statePrison = this.supervisoryOrganizationDelegate
				.create("State Prison", "SP", null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("State prison exists", e);
		}
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Ernst", "Stavro", null);
		PlacementTermChangeAction changeAction;
		try {
			changeAction = this.placementTermChangeActionDelegate
						.create("Begin Supervision");
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Begin supervision action exists", e);
		}
		Date startDate = this.parseDateText("12/30/2009");
		this.allowCorrectionalStatusChange(
				changeAction, null, secureCorrectionalStatus);
		this.allowCorrectionalStatusChangeReason(
				null, secureCorrectionalStatus, initialCommitChangeReason);
		this.changeCorrectionalStatusService
				.beginSupervision(offender, secureCorrectionalStatus,
						statePrison, initialCommitChangeReason,
						startDate, null);
		PlacementTerm placementTerm =
				this.placementTermDelegate.findForOffenderOnDate(
						offender, startDate);
		this.assertValues(placementTerm, secureCorrectionalStatus, statePrison,
				initialCommitChangeReason, null,
				new DateRange(startDate, null));
	}

	// Allows correctional status change, does nothing if already allowed
	private void allowCorrectionalStatusChange(
			final PlacementTermChangeAction action,
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus) {
		if (this.allowedCorrectionalStatusChangeDelegate
				.find(fromCorrectionalStatus, toCorrectionalStatus) == null) {
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