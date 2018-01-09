package omis.military.service.testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.military.domain.MilitaryBranch;
import omis.military.domain.MilitaryDischargeStatus;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.service.MilitaryServiceTermService;
import omis.military.service.delegate.MilitaryBranchDelegate;
import omis.military.service.delegate.MilitaryDischargeStatusDelegate;
import omis.military.service.delegate.MilitaryServiceTermDelegate;
import omis.military.service.delegate.MilitaryServiceTermNoteDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests for updating military service terms using military service term 
 * service.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Jul 15, 2016)
 * @since OMIS 3.0
 */
@Test(groups = {"military"})
public class MilitaryServiceTermUpdateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("militaryBranchDelegate")
	private MilitaryBranchDelegate militaryBranchDelegate;
	
	@Autowired
	@Qualifier("militaryDischargeStatusDelegate")
	private MilitaryDischargeStatusDelegate militaryDischargeStatusDelegate;
	
	@Autowired
	@Qualifier("militaryServiceTermDelegate")
	private MilitaryServiceTermDelegate militaryServiceTermDelegate;
	
	@Autowired
	@Qualifier("militaryServiceTermNoteDelegate")
	private MilitaryServiceTermNoteDelegate militaryServiceTermNoteDelegate;

	/* Service to test.*/
	
	@Autowired
	@Qualifier("militaryServiceTermService")
	private MilitaryServiceTermService militaryServiceTermService; 
	
	/* Tests. */

	/** Tests update of MilitaryServiceTerms. 
	 * @throws DuplicateEntityFoundException if caution exists.
	 * @throws DateConflictException */
	@Test
	public void testUpdate() throws DuplicateEntityFoundException, 
		DateConflictException {
		
		MilitaryServiceTerm militaryServiceTerm;
		
		final Offender offender = this.offenderDelegate.createWithoutIdentity
				("Blofeld", "Ernst", "Stavro", null);

		try {
			
		militaryServiceTerm = this.militaryServiceTermDelegate
			.create(offender, 
					this.militaryBranchDelegate.create("Marines", "USMC", true),
					this.militaryDischargeStatusDelegate.create("Dishonorable", 
							true), 
					this.parseDateText("07/15/2015"), 
					this.parseDateText("07/25/2016"));
							
		} catch (DuplicateEntityFoundException e) {
			throw new RuntimeException("Military term exists.", e);
		}
		final Date startDate = this.parseDateText("07/15/2015");
		final Date endDate = this.parseDateText("07/25/2016");
		final MilitaryBranch branch = this.militaryBranchDelegate
				.findAll().get(0);
		final MilitaryDischargeStatus dischargeStatus 
			= this.militaryDischargeStatusDelegate.create("Honorable", 
					true);
	
		militaryServiceTerm = this.militaryServiceTermDelegate
				.update(militaryServiceTerm, branch, dischargeStatus, startDate,
						endDate);
		
		assertValues(militaryServiceTerm, offender, branch, dischargeStatus, 
				startDate, endDate);
	}

	// Parses date text - returns result
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date.", e);
		}
	}
	
	// Asserts caution values
	private void assertValues(
			final MilitaryServiceTerm militaryServiceTerm,
			final Offender offender,
			final MilitaryBranch branch,
			final MilitaryDischargeStatus dischargeStatus,
			final Date startDate,
			final Date endDate) {
	
		assert offender.equals(militaryServiceTerm.getOffender())
		: String.format("Wrong offender: #%d found; #%d expected.",
				militaryServiceTerm.getOffender().getOffenderNumber(),
				offender.getOffenderNumber());
		assert branch.equals(militaryServiceTerm.getBranch())
			: String.format("Wrong military branch: %s found; %s expected.",
					militaryServiceTerm.getBranch().getName(), branch.getName());
		assert dischargeStatus.equals(militaryServiceTerm.getDischargeStatus())
			: String.format("Wrong discharge status: %s found; %s expected.",
					militaryServiceTerm.getDischargeStatus().getName(),
					dischargeStatus.getName());
		assert startDate.equals(militaryServiceTerm.getDateRange().getStartDate())
			: String.format("Wrong start date: %s found; %s expected.",
					militaryServiceTerm.getDateRange().getStartDate(), startDate);	
		assert endDate.equals(militaryServiceTerm.getDateRange().getEndDate())
		: String.format("Wrong end date: %s found; %s expected.",
				militaryServiceTerm.getDateRange().getEndDate(), endDate);
	}

}
