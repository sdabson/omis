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
 * Tests commit status term removal
 * 
 *@author Yidong Li 
 *@version 0.1.0 (June 20, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"commitstatus"})
public class CommitStatusTermServiceCommitStatusTermRemoveTest
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
	@Autowired
	@Qualifier("commitStatusDelegate")
	private CommitStatusDelegate commitStatusDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("commitStatusTermDelegate")
	private CommitStatusTermDelegate commitStatusTermDelegate;
	
	/* Service */
	@Autowired
	@Qualifier("commitStatusTermService")
	private CommitStatusTermService commitStatusTermService;
	
	@Test
	public void testCommitStatusTermRemove() throws DuplicateEntityFoundException{
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Li",
			"Kevin", "asga", "Mr.");
		CommitStatus status = this.commitStatusDelegate.create("zdxb", true);
		DateRange dateRange = new DateRange();
		Date startDate = new Date(105120000);
		Date endDate = new Date(205120000);
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);
		CommitStatusTerm term = this.commitStatusTermService.create(offender, 
			status, dateRange);
		
		// Action
		this.commitStatusTermService.remove(term);
		
		// Assertion
		assert !(this.commitStatusTermDelegate.findExists(offender, status, 
			startDate)!=null): "Commit status term was not removed!";
	}
}
