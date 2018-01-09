package omis.military.service.testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
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
 * Tests for creating military service using military service service.
 *
 * @author Trevor Isles
 * @version 0.0.1 (Jul 12, 2016)
 * @since OMIS 3.0
 */
@Test(groups = {"military"})
public class MilitaryServiceTermCreateTests 
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
			
	/* Service to test. */
	
	@Autowired
	@Qualifier("militaryServiceTermService")
	private MilitaryServiceTermService militaryService;
	
	/* Tests. */
	
	/**
	 * Tests creation of military services.
	 *  
	 * @throws DuplicateEntityFoundException if military term exists
	 */
	
	@Test
	public void testCreate()
			throws DuplicateEntityFoundException, DateConflictException {
		final Offender offender
			= this.offenderDelegate.createWithoutIdentity(
					"Blofeld", "Ernst", "Stavro", null);
		
		//Create closed text entities
		final MilitaryBranch branch = this.militaryBranchDelegate.create(
				"U.S. Marines", "USMC", true);
		final MilitaryDischargeStatus dischargeStatus 
		= this.militaryDischargeStatusDelegate.create("Dishonorable", true);
		
		final Date startDate = this.parseDateText("07/07/2016");
		final Date endDate = this.parseDateText("07/11/2016");
		
		//Create service term
		final MilitaryServiceTerm serviceTerm = this.
				militaryServiceTermDelegate.create(offender, branch, 
						dischargeStatus, startDate, endDate);

		
	// NB - methods should be renamed to "create" - SA
		
		DateRange dateRange = new DateRange(startDate, endDate);
		
		assertValues(offender, serviceTerm, branch, dischargeStatus, dateRange);
	}
		
	// Parses date text - returns result

	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
		
	// Asserts military service term values

	private void assertValues(
			final Offender offender,
			final MilitaryServiceTerm serviceTerm,
			final MilitaryBranch branch,
			final MilitaryDischargeStatus dischargeStatus,
			final DateRange dateRange) {
		assert offender.equals(serviceTerm.getOffender())
		: String.format("Wrong offender: #%d found; #%d expected",
				serviceTerm.getOffender().getOffenderNumber(),
				offender.getOffenderNumber());
		assert branch.equals(serviceTerm.getBranch())
			: String.format("Wrong branch: %s found; %s expected",
					serviceTerm.getBranch().getName(), branch.getName());
		assert dischargeStatus.equals(serviceTerm.getDischargeStatus())
			: String.format("Wrong discharge status: %s found, %s expected.", 
					serviceTerm.getDischargeStatus().getName(), 
					dischargeStatus.getName());
		assert DateRange.areEqual(serviceTerm.getDateRange(), dateRange) 
			: String.format("Wrong date range: %s found; %s expected",
					serviceTerm.getDateRange(), dateRange);
	}

}
