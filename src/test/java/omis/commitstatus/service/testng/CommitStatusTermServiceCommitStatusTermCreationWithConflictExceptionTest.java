package omis.commitstatus.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.exception.CommitStatusTermConflictException;
import omis.commitstatus.service.CommitStatusTermService;
import omis.commitstatus.service.delegate.CommitStatusDelegate;
import omis.commitstatus.service.delegate.CommitStatusTermDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests commit status term creation with conflict exception
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"commitstatus"})
public class CommitStatusTermServiceCommitStatusTermCreationWithConflictExceptionTest
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
	 * Tests the creation of commit status term with conflict exception.
	 * Start date of the new term is later than the start date of the existing 
	 * one but before the end date of the existing one 
	 * @throws CommitStatusTermConflictException 
	 */
	@Test(expectedExceptions = {CommitStatusTermConflictException.class})
	public void testCommitStatusTermCreationConflictExceptionTest1() 
		throws DuplicateEntityFoundException{
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		CommitStatus status1 = this.commitStatusDelegate.create("Status A", true);
		DateRange dateRange1 = new DateRange();
		Date startDate1= new Date(1205120000);
		Date endDate1= new Date(1805120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);;
		this.commitStatusTermService.create(offender, 
			status1, dateRange1);
		
		CommitStatus status2 = this.commitStatusDelegate.create("Status b", true);
		DateRange dateRange2 = new DateRange();
		Date startDate2= new Date(165120000);
		dateRange2.setStartDate(startDate2);
		
		// Action
		this.commitStatusTermService.create(offender, 
			status2, dateRange2);
	}
	
	/**
	 * Tests the creation of commit status term with conflict exception.
	 * Start date of the new term is before the start date of the existing 
	 * one
	 * @throws CommitStatusTermConflictException 
	 */
	@Test(expectedExceptions = {CommitStatusTermConflictException.class})
	public void testCommitStatusTermCreationConflictExceptionTest2() 
		throws DuplicateEntityFoundException{
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		CommitStatus status1 = this.commitStatusDelegate.create("Status A", true);
		DateRange dateRange1 = new DateRange();
		Date startDate1= new Date(1205120000);
		Date endDate1= new Date(1805120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);;
		this.commitStatusTermService.create(offender, 
			status1, dateRange1);
		
		CommitStatus status2 = this.commitStatusDelegate.create("Status b", true);
		DateRange dateRange2 = new DateRange();
		Date startDate2= new Date(65120000);
		dateRange2.setStartDate(startDate2);
		
		// Action
		this.commitStatusTermService.create(offender, 
			status2, dateRange2);
	}
	
	/**
	 * Tests the creation of commit status term with conflict exception.
	 * The range of the new one is with the range of  the existing one
	 * one
	 * @throws CommitStatusTermConflictException 
	 */
	@Test(expectedExceptions = {CommitStatusTermConflictException.class})
	public void testCommitStatusTermCreationConflictExceptionTest3() 
		throws DuplicateEntityFoundException{
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		CommitStatus status1 = this.commitStatusDelegate.create("Status A", true);
		DateRange dateRange1 = new DateRange();
		Date startDate1= new Date(1205120000);
		Date endDate1= new Date(1805120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);;
		this.commitStatusTermService.create(offender, 
			status1, dateRange1);
		
		CommitStatus status2 = this.commitStatusDelegate.create("Status b", true);
		DateRange dateRange2 = new DateRange();
		Date startDate2= new Date(1365120000);
		Date endDate2= new Date(1505120000);
		dateRange2.setStartDate(startDate2);
		dateRange2.setEndDate(endDate2);
		
		// Action
		this.commitStatusTermService.create(offender, 
			status2, dateRange2);
	}
	
	/**
	 * Tests the creation of commit status term with conflict exception.
	 * The start date of the new one is with the range of  the existing one
	 * and the end date is later than the start date of the existing and before 
	 * end date of the existing. The end date of the new is later than that of 
	 * the existing 
	 * @throws CommitStatusTermConflictException 
	 */
	@Test(expectedExceptions = {CommitStatusTermConflictException.class})
	public void testCommitStatusTermCreationConflictExceptionTest4() 
		throws DuplicateEntityFoundException{
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		CommitStatus status1 = this.commitStatusDelegate.create("Status A", true);
		DateRange dateRange1 = new DateRange();
		Date startDate1= new Date(1205120000);
		Date endDate1= new Date(1805120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);;
		this.commitStatusTermService.create(offender, 
			status1, dateRange1);
		
		CommitStatus status2 = this.commitStatusDelegate.create("Status b", true);
		DateRange dateRange2 = new DateRange();
		Date startDate2= new Date(1365120000);
		Date endDate2= new Date(1995120000);
		dateRange2.setStartDate(startDate2);
		dateRange2.setEndDate(endDate2);
		
		// Action
		this.commitStatusTermService.create(offender, 
			status2, dateRange2);
	}
	
	/**
	 * Tests the creation of commit status term with conflict exception.
	 * The start date of the new one is before that of  the existing one
	 * and the end date is later than the start date of the existing and before 
	 * end date of the existing.  
	 * @throws CommitStatusTermConflictException 
	 */
	@Test(expectedExceptions = {CommitStatusTermConflictException.class})
	public void testCommitStatusTermCreationConflictExceptionTest5() 
		throws DuplicateEntityFoundException{
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		CommitStatus status1 = this.commitStatusDelegate.create("Status A", true);
		DateRange dateRange1 = new DateRange();
		Date startDate1= new Date(1205120000);
		Date endDate1= new Date(1805120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);;
		this.commitStatusTermService.create(offender, 
			status1, dateRange1);
		
		CommitStatus status2 = this.commitStatusDelegate.create("Status b", true);
		DateRange dateRange2 = new DateRange();
		Date startDate2= new Date(365120000);
		Date endDate2= new Date(1595120000);
		dateRange2.setStartDate(startDate2);
		dateRange2.setEndDate(endDate2);
		
		// Action
		this.commitStatusTermService.create(offender, 
			status2, dateRange2);
	}
}