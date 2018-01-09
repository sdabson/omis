package omis.commitstatus.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.commitstatus.exception.CommitStatusTermExistsAfterException;
import omis.commitstatus.service.CommitStatusTermService;
import omis.commitstatus.service.delegate.CommitStatusDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests "update" of commit status term
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"commitstatus"})
public class CommitStatusTermServiceCommitStatusTermUpdateTest
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("commitStatusDelegate")
	private CommitStatusDelegate commitStatusDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("commitStatusTermService")
	private CommitStatusTermService commitStatusTermService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/**
	 * Tests commit status update.
	 */
	@Test
	public void testCommitStatusTermUpdate() throws DuplicateEntityFoundException{
		// Arrangements
		Offender originalOffender = this.offenderDelegate.createWithoutIdentity(
			"Obama", "Kevin", "Johns", "Mr.");
		CommitStatus originalStatus = this.commitStatusDelegate.create(
			"Status A", true);
		DateRange originalDateRange = new DateRange();
		Date orignalStartDate = new Date(105120000);
		Date originalEndDate = new Date(205120000);
		originalDateRange.setStartDate(orignalStartDate);
		originalDateRange.setEndDate(originalEndDate);
		
		CommitStatus newStatus = this.commitStatusDelegate.create(
			"Status B", true);
		DateRange newDateRange = new DateRange();
		Date newStartDate = new Date(5120000);
		Date newEndDate = new Date(105120000);
		newDateRange.setStartDate(newStartDate);
		newDateRange.setEndDate(newEndDate);
		
		// Action
		CommitStatusTerm term = this.commitStatusTermService.create(
			originalOffender, originalStatus, originalDateRange);
		this.commitStatusTermService.update(term, newStatus, newDateRange);
		
		// Assertions
		assert newStatus.equals(term.getStatus())
		: String.format("Wrong commit status: #%d expected; #%d found",
			newStatus.getName(), term.getStatus().getName());
		assert newStartDate.equals(term.getDateRange().getStartDate())
		: String.format("Wrong start date: #%s expected; #%s found",
			newStartDate, term.getDateRange().getStartDate());
		assert newEndDate.equals(term.getDateRange().getEndDate())
		: String.format("Wrong end date: #%s expected; #%s found",
			newEndDate, term.getDateRange().getEndDate());
	}
	
	/**
	 * Tests duplicate commit status term on update.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate term exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateCommitStatusTermUpdate() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"Obama", "Kevin", "Johns", "Mr.");
		CommitStatus status1 = this.commitStatusDelegate.create(
			"Status A", true);
		DateRange dateRange1 = new DateRange();
		Date startDate1 = new Date(105120000);
		Date endDate1 = new Date(205120000);
		dateRange1.setStartDate(startDate1);
		dateRange1.setEndDate(endDate1);
		CommitStatusTerm term1 = this.commitStatusTermService.create(offender, 
			status1, dateRange1);
		
		CommitStatus status2 = this.commitStatusDelegate.create(
			"Status B", true);
		DateRange dateRange2 = new DateRange();
		Date startDate2 = new Date(5120000);
		Date endDate2 = new Date(105120000);
		dateRange2.setStartDate(startDate2);
		dateRange2.setEndDate(endDate2);
		this.commitStatusTermService.create(offender, 
			status2, dateRange2);
		
		// Action
		this.commitStatusTermService.update(term1, status2, dateRange2);
	}	
	
	/**
	 * Tests commit status term exist after exception on update.
	 * 
	 * @throws CommitStatusTermExistsAfterException if term exists after
	 */
	@Test()
	public void testExistsAfterExceptionCommitStatusTermUpdate() 
		throws DuplicateEntityFoundException, CommitStatusTermExistsAfterException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
			"Obama", "Kevin", "Johns", "Mr.");
		CommitStatus status = this.commitStatusDelegate.create(
			"Status A", true);
		DateRange dateRange = new DateRange();
		Date startDate = new Date(105120000);
		Date endDate = new Date(205120000);
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);
		CommitStatusTerm term = this.commitStatusTermService.create(offender, 
			status, dateRange);
		
		// Action
		this.commitStatusTermService.update(term, status, dateRange);
	}	
}