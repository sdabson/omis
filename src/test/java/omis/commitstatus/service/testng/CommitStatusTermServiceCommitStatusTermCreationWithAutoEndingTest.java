package omis.commitstatus.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.commitstatus.service.CommitStatusTermService;
import omis.commitstatus.service.delegate.CommitStatusDelegate;
import omis.commitstatus.service.delegate.CommitStatusTermDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests commit status term creation with auto ending 
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"commitstatus"})
public class CommitStatusTermServiceCommitStatusTermCreationWithAutoEndingTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("commitStatusTermDelegate")
	private CommitStatusTermDelegate commitStatusTermDelegate;
	
	@Autowired
	@Qualifier("commitStatusDelegate")
	private CommitStatusDelegate commitStatusDelegate;
	
	/* Service to test. */
	@Autowired
	@Qualifier("commitStatusTermService")
	private CommitStatusTermService commitStatusTermService;
	
	/**
	 * Tests the creation of commit status term with auto ending.
	 * @throws DuplicateEntityFoundException 
	 */
	@Test
	public void testCommitStatusTermCreationWithAutoEnding() 
		throws DuplicateEntityFoundException{
		// Create the first term with end date is null
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		CommitStatus status1 = this.commitStatusDelegate.create("Status A", true);
		DateRange dateRange1 = new DateRange();
		Date startDate1= new Date(105120000);
		dateRange1.setStartDate(startDate1);
		CommitStatusTerm term1 = this.commitStatusTermService.create(offender, 
			status1, dateRange1);
		// Create the properties to prepare the creation of the second term whose 
		// start date is behind the start date of the first created term and its 
		// end date is null  
		CommitStatus status2 = this.commitStatusDelegate.create("Status b", true);
		DateRange dateRange2 = new DateRange();
		Date startDate2= new Date(1205120000);
		dateRange2.setStartDate(startDate2);
		
		// Create the second term
		CommitStatusTerm term2 = this.commitStatusTermService.create(offender, 
			status2, dateRange2);
		
		// Assertions
		// Check the 2nd term. Based on the successful test conducted in 
		// CommitStatusTermServiceCommitStatusTermCreationTest the creation of 
		// 1st term (lines 55 - 62) is assumed to be successful
		assert offender.equals(term2.getOffender())
		: String.format("Wrong offender: #%d expected; #%d found",
			offender.getOffenderNumber(), 
			term2.getOffender().getOffenderNumber());
		assert status2.equals(term2.getStatus())
		: String.format("Wrong 2nd term commit status: #%d expected; #%d found",
			status2.getName(),	term2.getStatus().getName());
		assert startDate2.equals(term2.getDateRange().getStartDate())
		: String.format("Wrong 2nd term start date: #%s expected; #%s found",
			startDate2, term2.getDateRange().getStartDate());
		assert term2.getDateRange().getEndDate()==null
		: String.format("Wrong end date: #%s expected; #%s found",
			"null", term2.getDateRange().getEndDate());
	
		// Check the 1st term
		assert offender.equals(term1.getOffender())
		: String.format("Wrong offender: #%d expected; #%d found",
			offender.getOffenderNumber(), 
			term2.getOffender().getOffenderNumber());
		assert status1.equals(term1.getStatus())
		: String.format("Wrong 1st term commit status: #%d expected; #%d found",
			status1.getName(),	term1.getStatus().getName());
		assert startDate2.equals(term1.getDateRange().getEndDate())
		: String.format("Wrong 1st term start date: #%s expected; #%s found",
			startDate2, term1.getDateRange().getStartDate());
		assert term1.getDateRange().getEndDate().equals(startDate2)
		: String.format("Wrong 1st end date: #%s expected; #%s found",
			startDate2, term1.getDateRange().getEndDate());
	}
}