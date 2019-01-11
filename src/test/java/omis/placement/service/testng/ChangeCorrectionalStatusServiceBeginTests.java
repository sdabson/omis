/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.placement.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.placement.service.ChangeCorrectionalStatusService;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeAction;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.AllowedCorrectionalStatusChangeExistsException;
import omis.supervision.exception.AllowedCorrectionalStatusChangeReasonRuleExistsException;
import omis.supervision.exception.CorrectionalStatusExistsException;
import omis.supervision.exception.CorrectionalStatusTermExistsException;
import omis.supervision.exception.IllegalCorrectionalStatusChangeException;
import omis.supervision.exception.OffenderUnderSupervisionException;
import omis.supervision.exception.PlacementTermChangeActionExistsException;
import omis.supervision.exception.PlacementTermChangeReasonExistsException;
import omis.supervision.exception.PlacementTermChangeReasonNotAllowedException;
import omis.supervision.exception.PlacementTermConflictException;
import omis.supervision.exception.PlacementTermExistsException;
import omis.supervision.exception.SupervisoryOrganizationExistsException;
import omis.supervision.exception.SupervisoryOrganizationTermExistsException;
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
import omis.util.DateRangeUtility;
import omis.util.DateUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests for begin supevision method of service to change correctional status.
 *
 * @author Stephen Abson
 * @version 0.0.2 (Dec 20, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"placement", "service"})
public class ChangeCorrectionalStatusServiceBeginTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate
	supervisoryOrganizationTermDelegate;
	
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
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	@Autowired
	@Qualifier("dateRangeUtility")
	private DateRangeUtility dateRangeUtility;
	
	/* Tests. */
	
	/**
	 * Tests successful beginning of supervision.
	 * 
	 * @throws PlacementTermConflictException if conflict exists 
	 * @throws PlacementTermChangeReasonNotAllowedException if reason is not
	 * allowed
	 * @throws OffenderUnderSupervisionException if offender is already under
	 * supervision 
	 * @throws IllegalCorrectionalStatusChangeException if status cannot
	 * be changed 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermChangeReasonExistsException if placement term exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws PlacementTermChangeActionExistsException if placement term
	 * change action exists 
	 * @throws AllowedCorrectionalStatusChangeExistsException if correctional
	 * status change is allowed
	 * @throws AllowedCorrectionalStatusChangeReasonRuleExistsException if
	 * reason is allowed for correction status change
	 */
	@Test(description = "Succeeds in beginning supervision")
	public void testSuccess()
			throws IllegalCorrectionalStatusChangeException,
				OffenderUnderSupervisionException,
				PlacementTermChangeReasonNotAllowedException,
				PlacementTermConflictException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				SupervisoryOrganizationExistsException,
				PlacementTermChangeActionExistsException,
				AllowedCorrectionalStatusChangeExistsException,
				AllowedCorrectionalStatusChangeReasonRuleExistsException {
		
		// Arranges related entities
		CorrectionalStatus secureCorrectionalStatus
				= this.createSecureStatus();
		PlacementTermChangeReason initialCommitChangeReason
				= this.createInitialCommitReason();
		SupervisoryOrganization statePrison
				= this.createStatePrisonOrganization();
		final Offender offender = this.createOffender();
		PlacementTermChangeAction changeAction
				= this.createBeginSupervisionChangeAction();
		Date startDate = this.dateUtility.parseDateText("12/30/2009");
		this.allowCorrectionalStatusChange(
				changeAction, null, secureCorrectionalStatus);
		this.allowCorrectionalStatusChangeReason(
				null, secureCorrectionalStatus, initialCommitChangeReason);
		
		// Action - begins supervision
		this.changeCorrectionalStatusService
				.beginSupervision(offender, secureCorrectionalStatus,
						statePrison, initialCommitChangeReason,
						startDate, null);
		
		// Asserts values of created placement term
		PropertyValueAsserter
			.create()
			.addExpectedValue("correctionalStatusTerm.correctionalStatus",
					secureCorrectionalStatus)
			.addExpectedValue(
					"supervisoryOrganizationTerm.supervisoryOrganization",
					statePrison)
			.addExpectedValue("startChangeReason", initialCommitChangeReason)
			.addExpectedValue("endChangeReason", null)
			.addExpectedValue("dateRange.startDate", startDate)
			.addExpectedValue("dateRange.endDate", null)
			.performAssertions(
				this.placementTermDelegate.findForOffenderOnDate(
						offender, startDate));
	}

	/**
	 * Tests that beginning supervision is prevented when offender is already
	 * under supervision by {@code OffenderUnderSupervisionException}.
	 * 
	 * @throws OffenderUnderSupervisionException if offender is under
	 * supervision - asserted
	 * @throws CorrectionalStatusExistsException if correctional status term
	 * exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization term exists 
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists  
	 * @throws CorrectionalStatusTermExistsException if correctional status term
	 * exists 
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 * @throws PlacementTermChangeActionExistsException if placement change
	 * action exists
	 * @throws AllowedCorrectionalStatusChangeExistsException if correctional
	 * status change is allowed 
	 * @throws AllowedCorrectionalStatusChangeReasonRuleExistsException if
	 * reason for change is allowed for correctional status 
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists
	 * @throws PlacementTermChangeReasonNotAllowedException if change reason
	 * is not allowed 
	 * @throws IllegalCorrectionalStatusChangeException if correctional status
	 * change is not allowed 
	 */
	@Test(
		description = "Fails when offender is under supervision",
		expectedExceptions = {OffenderUnderSupervisionException.class})
	public void testOffenderUnderSupervisionException()
			throws OffenderUnderSupervisionException,
				CorrectionalStatusExistsException,
				SupervisoryOrganizationExistsException,
				PlacementTermChangeReasonExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermChangeActionExistsException,
				AllowedCorrectionalStatusChangeExistsException,
				AllowedCorrectionalStatusChangeReasonRuleExistsException,
				IllegalCorrectionalStatusChangeException,
				PlacementTermChangeReasonNotAllowedException,
				PlacementTermConflictException {
		
		// Places offender securely
		PlacementTermChangeReason initialCommitReason
			= this.createInitialCommitReason();
		Offender offender = this.createOffender();
		this.createPlacementTerm(offender,
				this.dateRangeUtility.parseDateTexts(
						"12/12/2012", "12/12/2018"),
				this.createSecureStatus(),
				this.createStatePrisonOrganization(),
				initialCommitReason,
				null, false);
		
		// Allows change and reason for action - this is required so as to
		// not create a dependency on the service method's order of operations
		// for the test to correctly pass
		CorrectionalStatus probationStatus = this.createProbationStatus();
		PlacementTermChangeAction beginChangeAction
			= this.createBeginSupervisionChangeAction();
		this.allowCorrectionalStatusChange(
				beginChangeAction, null, probationStatus);
		this.allowedCorrectionalStatusChangeReasonRuleDelegate.create(
				null, probationStatus, initialCommitReason);
		
		// Action - begins supervision
		SupervisoryOrganization pnpOfficeOrganization
			= this.createPnpOrganization();
		this.changeCorrectionalStatusService.beginSupervision(
				offender, probationStatus, pnpOfficeOrganization,
				initialCommitReason,
				this.dateUtility.parseDateText("12/12/2015"), null);
	}
	
	/**
	 * Tests that beginning supervision is prevented when conflicting placement
	 * terms exist by {@code PlacementTermConflictException} being thrown.
	 * 
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist - asserted
	 * @throws OffenderUnderSupervisionException if offender is under
	 * supervision
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermChangeActionExistsException if placement term
	 * change action exists
	 * @throws AllowedCorrectionalStatusChangeExistsException if correctional
	 * status change is already allowed
	 * @throws AllowedCorrectionalStatusChangeReasonRuleExistsException if
	 * reason is allowed for correctional status change
	 * @throws IllegalCorrectionalStatusChangeException if correctional status
	 * change is not allowed
	 * @throws PlacementTermChangeReasonNotAllowedException if change reason
	 * is not allowed
	 */
	@Test(
		description = "Fails when conflicts exist",
		expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflictException()
			throws PlacementTermConflictException,
				OffenderUnderSupervisionException,
				CorrectionalStatusExistsException,
				SupervisoryOrganizationExistsException,
				PlacementTermChangeReasonExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermChangeActionExistsException,
				AllowedCorrectionalStatusChangeExistsException,
				AllowedCorrectionalStatusChangeReasonRuleExistsException,
				IllegalCorrectionalStatusChangeException,
				PlacementTermChangeReasonNotAllowedException,
				PlacementTermConflictException {
		
		// Places offender securely
		PlacementTermChangeReason initialCommitReason
			= this.createInitialCommitReason();
		Offender offender = this.createOffender();
		this.createPlacementTerm(offender,
				this.dateRangeUtility.parseDateTexts(
						"12/12/2015", "12/12/2016"),
				this.createSecureStatus(),
				this.createStatePrisonOrganization(),
				initialCommitReason,
				null, false);
		
		// Allows change and reason for action - this is required so as to
		// not create a dependency on the service method's order of operations
		// for test to correctly pass
		CorrectionalStatus probationStatus = this.createProbationStatus();
		PlacementTermChangeAction beginChangeAction
			= this.createBeginSupervisionChangeAction();
		this.allowCorrectionalStatusChange(
				beginChangeAction, null, probationStatus);
		this.allowedCorrectionalStatusChangeReasonRuleDelegate.create(
				null, probationStatus, initialCommitReason);
		
		// Action - begins supervision starting before and ending after
		// existing secure placement - this should cause a conflict
		SupervisoryOrganization pnpOfficeOrganization
			= this.createPnpOrganization();
		this.changeCorrectionalStatusService.beginSupervision(
				offender, probationStatus, pnpOfficeOrganization,
				initialCommitReason,
				this.dateUtility.parseDateText("12/12/2012"),
				this.dateUtility.parseDateText("12/12/2018"));
	}
	
	/**
	 * Tests that beginning of supervision is prevented if correctional status
	 * term is not allowed with
	 * {@code IllegalCorrectionalStatusChangeException}.
	 * 
	 * @throws IllegalCorrectionalStatusChangeException if correctional status
	 * change is not allowed - asserted
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws AllowedCorrectionalStatusChangeReasonRuleExistsException if
	 * reason for change is already allowed
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws OffenderUnderSupervisionException if offender is already under
	 * supervision
	 * @throws PlacementTermChangeReasonNotAllowedException if change reason
	 * is not allowed
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 */
	@Test(
		description = "Fails when status change is not allowed",
		expectedExceptions = {IllegalCorrectionalStatusChangeException.class})
	public void testIllegalCorrectionalStatusChangeException()
			throws IllegalCorrectionalStatusChangeException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				AllowedCorrectionalStatusChangeReasonRuleExistsException,
				SupervisoryOrganizationExistsException,
				OffenderUnderSupervisionException,
				PlacementTermChangeReasonNotAllowedException,
				PlacementTermConflictException {
		
		// Allows change rule so as to not create a dependency on service
		// method's ordering of operations for test to correctly pass
		CorrectionalStatus secureStatus = this.createSecureStatus();
		PlacementTermChangeReason initialCommitReason
			= this.createInitialCommitReason();
		this.allowedCorrectionalStatusChangeReasonRuleDelegate.create(
				null, secureStatus, initialCommitReason);
		
		// Attempts to place offender
		Offender offender = this.createOffender();
		SupervisoryOrganization statePrisonOrganization
			= this.createStatePrisonOrganization();
		this.changeCorrectionalStatusService.beginSupervision(
				offender, secureStatus, statePrisonOrganization,
				initialCommitReason,
				this.dateUtility.parseDateText("12/12/2014"), null);
	}
	
	/**
	 * Tests that attempts to begin supervision with change reason not allowed
	 * for correctional status are prevented with
	 * {@code PlacementTermChangeReasonNotAllowedException}.
	 * 
	 * @throws PlacementTermChangeReasonNotAllowedException if placement change
	 * reason is not allowed - asserted
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermChangeActionExistsException if placement term
	 * change action exists
	 * @throws AllowedCorrectionalStatusChangeExistsException if correctional
	 * status change is already allowed
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists
	 * @throws IllegalCorrectionalStatusChangeException if change reason is not
	 * allowed for correctional status
	 * @throws OffenderUnderSupervisionException if offender is under
	 * supervision
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 */
	@Test(
		description = "Fails when change reason is now allowed",
		expectedExceptions = {PlacementTermChangeReasonNotAllowedException.class})
	public void testPlacementTermChangeReasonNotAllowedException()
			throws PlacementTermChangeReasonNotAllowedException,
				CorrectionalStatusExistsException,
				PlacementTermChangeActionExistsException,
				AllowedCorrectionalStatusChangeExistsException,
				SupervisoryOrganizationExistsException,
				PlacementTermChangeReasonExistsException,
				IllegalCorrectionalStatusChangeException,
				OffenderUnderSupervisionException,
				PlacementTermConflictException {
		
		// Allows change so as to not create a dependency on service method's
		// ordering of operations for test to correctly pass
		CorrectionalStatus secureStatus = this.createSecureStatus();
		PlacementTermChangeAction beginSupervisionChangeAction
			= this.createBeginSupervisionChangeAction();
		this.allowedCorrectionalStatusChangeDelegate.create(
				beginSupervisionChangeAction, null, secureStatus);
		
		// Attempts to place offender
		Offender offender = this.createOffender();
		SupervisoryOrganization statePrisonOrganization
			= this.createStatePrisonOrganization();
		this.changeCorrectionalStatusService
			.beginSupervision(offender, secureStatus, statePrisonOrganization,
					this.createInitialCommitReason(),
					this.dateUtility.parseDateText("12/12/2012"), null);
	}

	/* Helper methods. */
	
	// Returns probation status
	private CorrectionalStatus createProbationStatus()
			throws CorrectionalStatusExistsException {
		return this.correctionalStatusDelegate.create(
				"Probation", "PRO", true, (short) 1, true);
	}

	// Returns P&P office 
	private SupervisoryOrganization createPnpOrganization()
			throws SupervisoryOrganizationExistsException {
		return this.supervisoryOrganizationDelegate.create("P&P", "PNP", null);
	}
	
	// Returns begin supervision change action
	private PlacementTermChangeAction createBeginSupervisionChangeAction()
			throws PlacementTermChangeActionExistsException {
		return this.placementTermChangeActionDelegate
				.create("Begin Supervision");
	}

	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity(
				"Grant", "Ernst", "Julius", null);
	}

	// Returns State prison organization
	private SupervisoryOrganization createStatePrisonOrganization()
			throws SupervisoryOrganizationExistsException {
		return this.supervisoryOrganizationDelegate
				.create("State Prison", "SP", null);
	}

	// Returns initial commit reason
	private PlacementTermChangeReason createInitialCommitReason()
			throws PlacementTermChangeReasonExistsException {
		return this.placementTermChangeReasonDelegate
				.create("Initial Commit", (short) 1, true, false);
	}
	
	// Returns secure status
	private CorrectionalStatus createSecureStatus()
			throws CorrectionalStatusExistsException {
		return this.correctionalStatusDelegate.create(
				"Secure", "SEC", true, (short) 1, true);
	}
	
	// Creates placement term
	private PlacementTerm createPlacementTerm(
			final Offender offender, final DateRange dateRange,
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization,
			final PlacementTermChangeReason startChangeReason,
			final PlacementTermChangeReason endChangeReason,
			final Boolean locked)
					throws PlacementTermExistsException,
						SupervisoryOrganizationTermExistsException,
						CorrectionalStatusTermExistsException {
		return this.placementTermDelegate.create(offender, dateRange,
				this.supervisoryOrganizationTermDelegate.create(
						offender, dateRange, supervisoryOrganization),
				this.correctionalStatusTermDelegate.create(
						offender, dateRange, correctionalStatus),
				startChangeReason, endChangeReason, locked);
	}
	
	// Allows correctional status change, does nothing if already allowed
	private void allowCorrectionalStatusChange(
			final PlacementTermChangeAction action,
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus)
					throws AllowedCorrectionalStatusChangeExistsException {
		this.allowedCorrectionalStatusChangeDelegate.create(
			action, fromCorrectionalStatus, toCorrectionalStatus);
	}
	
	// Allows change reason for correctional status change, does nothing if
	// already allowed
	private void allowCorrectionalStatusChangeReason(
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus,
			final PlacementTermChangeReason changeReason)
				throws
					AllowedCorrectionalStatusChangeReasonRuleExistsException {
		this.allowedCorrectionalStatusChangeReasonRuleDelegate.create(
			fromCorrectionalStatus, toCorrectionalStatus,
			changeReason);
	}
}