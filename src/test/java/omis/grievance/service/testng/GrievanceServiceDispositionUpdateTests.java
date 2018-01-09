package omis.grievance.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionReasonCategory;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.grievance.service.GrievanceService;
import omis.grievance.service.delegate.GrievanceComplaintCategoryDelegate;
import omis.grievance.service.delegate.GrievanceDelegate;
import omis.grievance.service.delegate.GrievanceDispositionDelegate;
import omis.grievance.service.delegate.GrievanceDispositionReasonDelegate;
import omis.grievance.service.delegate.GrievanceDispositionStatusDelegate;
import omis.grievance.service.delegate.GrievanceLocationDelegate;
import omis.grievance.service.delegate.GrievanceSubjectDelegate;
import omis.grievance.service.delegate.GrievanceUnitDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests update of grievance service 
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"grievance"})
public class GrievanceServiceDispositionUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */

	@Autowired
	@Qualifier("grievanceSubjectDelegate")
	private GrievanceSubjectDelegate grievanceSubjectDelegate;
	
	@Autowired
	@Qualifier("grievanceComplaintCategoryDelegate")
	private GrievanceComplaintCategoryDelegate
	grievanceComplaintCategoryDelegate;
	
	@Autowired
	@Qualifier("grievanceUnitDelegate")
	private GrievanceUnitDelegate grievanceUnitDelegate;
	
	@Autowired
	@Qualifier("grievanceLocationDelegate")
	private GrievanceLocationDelegate grievanceLocationDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("grievanceDelegate")
	private GrievanceDelegate grievanceDelegate;
	
	@Autowired
	@Qualifier("grievanceDispositionStatusDelegate")
	private GrievanceDispositionStatusDelegate
	grievanceDispositionStatusDelegate;
	
	@Autowired
	@Qualifier("grievanceDispositionReasonDelegate")
	private GrievanceDispositionReasonDelegate
	grievanceDispositionReasonDelegate;
	
	@Autowired
	@Qualifier("grievanceDispositionDelegate")
	private GrievanceDispositionDelegate grievanceDispositionDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("grievanceService")
	private GrievanceService grievanceService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/**
	 * Tests closing grievance by status on disposition.
	 * 
	 * <p>Disposition statuses close grievances automatically if closed property
	 * of status is true. Disposition is updated with status that closes.
	 */
	public void testCloseGrievanceByDispositionStatusClosedDate() {
		
		// Arrangements
		GrievanceDisposition disposition = this.arrange();
		Date closedDate = this.convertDate("08/01/2003");
		Date responseToOffenderDate = this.convertDate("08/01/2003");
		GrievanceDispositionReason reason;
		try {
			reason = this.grievanceDispositionReasonDelegate
				.create(GrievanceDispositionReasonCategory.GRANTED,
						"Complies with policy", true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Reason exists", e);
		}
		GrievanceDispositionStatus status;
		try {
			status = this.grievanceDispositionStatusDelegate
				.create("Warden granted", (short) 1, true, true, true,
						GrievanceDispositionLevel.WARDEN);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Status exists", e);
		}
		
		// Action
		disposition = this.grievanceService
				.updateWardenLevelDisposition(disposition, status,
						closedDate, reason, disposition.getReceivedDate(),
						disposition.getResponseDueDate(),
						disposition.getResponseBy(),
						responseToOffenderDate, null, null);
		
		// Asserts that grievance is closed with closing date specified
		assert disposition.getGrievance().getClosedDate().equals(closedDate)
			: String.format("Wrong closed date: %s expected, %s found",
					closedDate,
					disposition.getGrievance().getClosedDate());
	}
	
	// Makes arrangements resulting in a warden level grievance disposition
	// existing - returns this disposition
	private GrievanceDisposition arrange() {
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"No", "Julius", null, null);
		Date openedDate = this.convertDate("09/12/2001");
		Date informalFileDate = this.convertDate("08/21/2002");
		GrievanceSubject subject;
		try {
			subject = this.grievanceSubjectDelegate.create(
						"Emergency", true, true,
						GrievanceDispositionLevel.WARDEN);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Subject exists", e);
		}
		GrievanceComplaintCategory complaintCategory;
		try {
			complaintCategory = this.grievanceComplaintCategoryDelegate
				.create(subject, "PREA Violation", true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Complaint category exists", e);
		}
		GrievanceLocation location;
		try {
			location = this.grievanceLocationDelegate
				.create("State Prison", (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Location exists", e);
		}
		GrievanceUnit unit;
		try {
			unit = this.grievanceUnitDelegate
					.create("Infirmary", true, (short) 1);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Unit exists", e);
		}
		Integer grievanceNumber = this.grievanceDelegate
				.findMaxGrievanceNumber() + 1;
		Grievance grievance;
		try {
			grievance = this.grievanceDelegate
					.create(offender, location, unit, subject,
							complaintCategory, grievanceNumber, openedDate,
							informalFileDate,
							"Inmate claims PREA violation occurred",
							null, null, null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Grievance exists", e);
		}
		GrievanceDispositionStatus status;
		try {
			status = this.grievanceDispositionStatusDelegate
				.create("Awaiting Warden Response", (short) 1, true, false,
						true, GrievanceDispositionLevel.WARDEN);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Status exists", e);
		}
		Date receivedDate = this.convertDate("06/12/2003");
		Date responseDueDate = this.convertDate("06/12/2003");
		try {
			return this.grievanceDispositionDelegate
				.create(grievance, GrievanceDispositionLevel.WARDEN, status,
						null, receivedDate, responseDueDate, null,
						null, null, null);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Disposition exists", e);
		}
	}
	
	// Converts date - null safe
	private Date convertDate(final String value) {
		if (value != null) {
			CustomDateEditor dateEditor = this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(false);
			dateEditor.setAsText(value);
			return (Date) dateEditor.getValue();
		} else {
			return null;
		}
	}
}