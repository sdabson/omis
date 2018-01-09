package omis.grievance.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.grievance.service.GrievanceService;
import omis.grievance.service.delegate.GrievanceComplaintCategoryDelegate;
import omis.grievance.service.delegate.GrievanceLocationDelegate;
import omis.grievance.service.delegate.GrievanceSubjectDelegate;
import omis.grievance.service.delegate.GrievanceUnitDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests for creating grievances. 
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"grievance"})
public class GrievanceServiceCreateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("grievanceLocationDelegate")
	private GrievanceLocationDelegate grievanceLocationDelegate;
	
	@Autowired
	@Qualifier("grievanceUnitDelegate")
	private GrievanceUnitDelegate grievanceUnitDelegate;
	
	@Autowired
	@Qualifier("grievanceSubjectDelegate")
	private GrievanceSubjectDelegate grievanceSubjectDelegate;
	
	@Autowired
	@Qualifier("grievanceComplaintCategoryDelegate")
	private GrievanceComplaintCategoryDelegate
	grievanceComplaintCategoryDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier(value = "grievanceService")
	private GrievanceService grievanceService;

	/** Tests creation of grievance. */
	@Test(groups = {"grievance"})
	public void testCreateGrievance() {
		
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Ernst", "Stavro", null);
		GrievanceLocation location;
		try {
			location = this.grievanceLocationDelegate.create(
					"State Prison", (short) 1, true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Grievance location exists", e);
		}
		GrievanceUnit unit;
		try {
			unit = this.grievanceUnitDelegate.create(
					"Infirmary", true, (short) 1);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Grievance unit exists", e);
		}
		Date openedDate = this.parseDateValue("03/18/2011");
		Date informalFileDate = this.parseDateValue("04/01/2011");
		GrievanceSubject subject;
		try {
			subject = this.grievanceSubjectDelegate
					.create("Policy", false, true,
							GrievanceDispositionLevel.WARDEN);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Grievance subject exists", e);
		}
		GrievanceComplaintCategory complaintCategory;
		try {
			complaintCategory = this.grievanceComplaintCategoryDelegate
					.create(subject, "Policy - see description", true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Grievance complaint category", e);
		}
		String description = "Inmate Blofeld says he cannot begin to describe"
				+ " how grieved he is by policy";
		String initialComment = "Wants policy changed";
		
		// Action
		Grievance grievance;
		try {
			grievance = this.grievanceService
					.create(offender, location, unit, openedDate,
							informalFileDate, subject, complaintCategory,
							description, initialComment);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Grievance unexpectedly exists", e);
		}
		
		// Assertions
		assert offender.equals(grievance.getOffender())
			: String.format("Wrong offender: #%d expected; #%d found",
					offender.getOffenderNumber(),
					grievance.getOffender().getOffenderNumber());
		assert location.equals(grievance.getLocation())
			: String.format("Wrong location: %s expected; %s found",
					location.getName(), grievance.getLocation().getName());
		assert unit.equals(grievance.getUnit())
			: String.format("Wrong unit: %s expected; %s found",
					unit.getName(), grievance.getUnit().getName());
		assert openedDate.equals(grievance.getOpenedDate())
			: String.format("Wrong opened date: %s expected; %s found",
					openedDate, grievance.getOpenedDate());
		assert informalFileDate.equals(grievance.getInformalFileDate())
			: String.format("Wrong informal file date: %s expected; %s found",
					informalFileDate, grievance.getInformalFileDate());
		assert subject.equals(grievance.getSubject())
			: String.format("Wrong subject: %s expected; %s found",
					subject.getName(), grievance.getSubject().getName());
		assert complaintCategory.equals(grievance.getComplaintCategory())
			: String.format("Wrong complaint category: %s expected; %s found",
					complaintCategory.getName(),
					grievance.getComplaintCategory().getName());
		assert description.equals(grievance.getDescription())
			: String.format("Wrong description: %s expected; %s found",
					description, grievance.getDescription());
		assert initialComment.equals(grievance.getInitialComment())
			: String.format("Wrong initial comment: %s expected; %s found",
					initialComment, grievance.getInitialComment());
	}
	
	/**
	 * Tests creation of a unique grievance.
	 * 
	 * <p>Only the business key of the grievance is different.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate grievance exists
	 * (should not happen as business key is different)
	 */
	@Test(groups = {"grievance"})
	public void testCreateUniqueGrievance()
			throws DuplicateEntityFoundException {
		
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Volkoff", "Nikolai", null, null);
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
					.create("Canteen", true, (short) 1);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Unit exists", e);
		}
		Date openedDate = this.parseDateValue("08/11/2001");
		Date informalFileDate = null;
		GrievanceSubject subject;
		try {
			subject = this.grievanceSubjectDelegate
					.create("Health Services", true, true,
							GrievanceDispositionLevel.COORDINATOR);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Subject exists", e);
		}
		GrievanceComplaintCategory complaintCategory;
		try {
			complaintCategory = this.grievanceComplaintCategoryDelegate.create(
					subject, "Dental", true);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Complaint category exists", e);
		}
		String description = "Appointment required";
		String initialComment = null;
		try {
			this.grievanceService.create(offender, location, unit,
					openedDate, informalFileDate, subject, complaintCategory,
					description, initialComment);
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Grievance exists", e);
		}
		
		// Action
		this.grievanceService.create(offender, location, unit, openedDate,
				informalFileDate, subject, complaintCategory, description,
				initialComment);
	}
	
	// Parses and returns date
	private Date parseDateValue(final String value) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(value);
		} catch (ParseException e) {
			throw new RuntimeException(
					String.format("Cannot parse date value: %s", value), e);
		}
	}
}