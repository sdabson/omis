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
 * Tests commit status term creation 
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"commitstatus"})
public class CommitStatusTermServiceCommitStatusTermCreationTest
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
	 * Tests the creation of commit status term.
	 */
	@Test
	public void testCommitStatusTermCreation() throws DuplicateEntityFoundException {
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		CommitStatus status = this.commitStatusDelegate.create("Status A", true);
		DateRange dateRange = new DateRange();
		Date startDate = new Date(105120000);
		Date endDate = new Date(205120000);
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);
		
		// Action
		CommitStatusTerm term = this.commitStatusTermService.create(offender, 
			status, dateRange);
		
		// Assertions
		assert offender.equals(term.getOffender())
		: String.format("Wrong offender: #%d expected; #%d found",
			offender.getOffenderNumber(), 
			term.getOffender().getOffenderNumber());
		assert status.equals(term.getStatus())
		: String.format("Wrong commit status: #%d expected; #%d found",
			status.getName(),	term.getStatus().getName());
		assert startDate.equals(term.getDateRange().getStartDate())
		: String.format("Wrong start date: #%s expected; #%s found",
			startDate, term.getDateRange().getStartDate());
		assert endDate.equals(term.getDateRange().getEndDate())
		: String.format("Wrong end date: #%s expected; #%s found",
			endDate, term.getDateRange().getEndDate());
	}
	
	/**
	 * Tests duplicate commit status term on creation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate term exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateCommitStatusCreate() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("asfd",
			"tjuty", "abedb", "Mr.");
		CommitStatus status;
		status = this.commitStatusDelegate.create("ahrt", true);
		DateRange dateRange = new DateRange();
		Date startDate = new Date(10000000);
		Date endDate = new Date(20000000);
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);
		this.commitStatusTermService.create(offender, status, dateRange);
		this.commitStatusTermService.create(offender, status, dateRange);
	}
}