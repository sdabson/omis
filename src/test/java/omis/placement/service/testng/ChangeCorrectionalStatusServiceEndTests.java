package omis.placement.service.testng;

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
 * Tests for end method of service to change correctional status.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"placement"})
public class ChangeCorrectionalStatusServiceEndTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate
	supervisoryOrganizationTermDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeActionDelegate")
	private PlacementTermChangeActionDelegate placementTermChangeActionDelegate;
	
	@Autowired
	@Qualifier("allowedCorrectionalStatusChangeDelegate")
	private AllowedCorrectionalStatusChangeDelegate
	allowedCorrectionalStatusChangeDelegate;
	
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
	 * Tests ending placement.
	 * 
	 * @throws IllegalCorrectionalStatusChangeException if correctional status 
	 * change is illegal
	 * @throws OffenderNotUnderSupervisionException if offender is not
	 * under supervision
	 * @throws PlacementTermChangeReasonNotAllowedException if change reason
	 * is not allowed for status change
	 * @throws PlacementTermConflictException if existing placement term
	 * conflicts
	 * @throws EndedPlacementTermException if placement term on end date is
	 * ended
	 */
	public void testEnd()
			throws IllegalCorrectionalStatusChangeException,
				OffenderNotUnderSupervisionException,
				PlacementTermChangeReasonNotAllowedException,
				PlacementTermConflictException,
				EndedPlacementTermException {
		
		// Arrangements
		CorrectionalStatus probation;
		try {
			probation = this.correctionalStatusDelegate
					.create("Probation", "PRO", false, (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Correctional status exists", e);
		}
		SupervisoryOrganization probationOffice;
		try {
			probationOffice = this.supervisoryOrganizationDelegate
				.create("Probation Office", "PRO", null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Supervisory organization exists", e);
		}
		PlacementTermChangeReason beginSentence;
		try {
			beginSentence = this.placementTermChangeReasonDelegate
				.create("Begin Sentence", (short) 1, true, false);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Change reason exists", e);
		}
		Date startDate = this.parseDateText("06/15/2011");
		Offender goldfinger = this.offenderDelegate
				.createWithoutIdentity("Goldfinger", "Auric", null, null);
		this.createPlacementTerm(goldfinger, probation,
				probationOffice, new DateRange(startDate, null),
				beginSentence, null);
		PlacementTermChangeAction endSupervision;
		try {
			endSupervision = this.placementTermChangeActionDelegate.create(
					"End Supervision");
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Change action exists", e);
		}
		PlacementTermChangeReason sentenceExpired;
		try {
			sentenceExpired = this.placementTermChangeReasonDelegate.create(
					"Sentence Expired", (short) 2, false, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Change reason exists", e);
		}
		try {
			this.allowedCorrectionalStatusChangeDelegate
				.create(endSupervision, probation, null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Status change allowed", e);
		}
		try {
			this.allowedCorrectionalStatusChangeReasonRuleDelegate
				.create(probation, null, sentenceExpired);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Status change reason rule exists", e);
		}
		Date endDate = this.parseDateText("06/15/2016");
		
		// Action
		this.changeCorrectionalStatusService
			.endSupervision(goldfinger, sentenceExpired, endDate);
		
		// Assertions
		PlacementTerm placementTerm = this.placementTermDelegate
				.findForOffenderOnDate(goldfinger, startDate);
		assert startDate.equals(placementTerm.getDateRange().getStartDate())
			: String.format("Wrong start date: %s expected; %s found",
					startDate, placementTerm.getDateRange().getStartDate());
		assert endDate.equals(placementTerm.getDateRange().getEndDate())
			: String.format("Wrong end date: %s expected; %s found", 
					endDate, placementTerm.getDateRange().getEndDate());
		assert probationOffice.equals(
				placementTerm.getSupervisoryOrganizationTerm()
					.getSupervisoryOrganization())
			: String.format(
					"Wrong supervisory organization: %s expected; %s found",
				probationOffice.getName(),
				placementTerm.getSupervisoryOrganizationTerm()
					.getSupervisoryOrganization().getName());
		assert probation.equals(placementTerm.getCorrectionalStatusTerm()
				.getCorrectionalStatus())
			: String.format("Wrong correctional status: %s expected; %s found",
					probation.getName(), placementTerm
						.getSupervisoryOrganizationTerm()
							.getSupervisoryOrganization().getName());
		assert beginSentence.equals(placementTerm.getStartChangeReason())
			: String.format("Wrong start reason: %s expected; %s found",
					beginSentence.getName(),
					placementTerm.getStartChangeReason().getName());
		assert sentenceExpired.equals(placementTerm.getEndChangeReason())
			: String.format("Wrong end reason: %s expected; %s found",
					sentenceExpired.getName(),
					placementTerm.getEndChangeReason().getName());
	}
	
	// Creates placement term
	private PlacementTerm createPlacementTerm(
			final Offender offender,
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization,
			final DateRange dateRange,
			final PlacementTermChangeReason startChangeReason,
			final PlacementTermChangeReason endChangeReason) {
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
			throw new RuntimeException(
					"Supervisory organization term exists", e);
		}
		try {
			return this.placementTermDelegate
					.create(offender, dateRange, supervisoryOrganizationTerm, 
							correctionalStatusTerm, startChangeReason, 
							endChangeReason, false);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Placement term exists", e);
		}
	}
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}
