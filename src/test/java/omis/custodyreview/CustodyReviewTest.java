package omis.custodyreview;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.custody.domain.CustodyChangeReason;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyReview;
import omis.custody.service.CustodyReviewService;
import omis.custody.service.delegate.CustodyChangeReasonDelegate;
import omis.custody.service.delegate.CustodyLevelDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.UserAccountService;

/**
 * Custody review test (TestNG).
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr. 04, 2013)
 * @since OMIS 3.0
 *
 */
@Test(groups = { "CustodyReview" })
public class CustodyReviewTest 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("custodyReviewService")
	private CustodyReviewService custodyReviewService;

	@Autowired
	@Qualifier("custodyReviewInstanceFactory")
	private InstanceFactory<CustodyReview> custodyReviewInstanceFactory;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("userAccountService")
	private UserAccountService userAccountService;
	
	@Autowired
	@Qualifier("custodyLevelDelegate")
	private CustodyLevelDelegate custodyLevelDelegate;
	
	@Autowired
	@Qualifier("custodyChangeReasonDelegate")
	private CustodyChangeReasonDelegate custodyChangeReasonDelegate;
	
	/**
	 * Persists and deletes a custody review.
	 *  
	 * @throws DuplicateEntityFoundException duplicate entity exception
	 * */
	public void persistAndDeleteCustodyReview() 
		throws DuplicateEntityFoundException {
		//final Integer offenderNumber = 34999;
		final long startDateNumber = 111111;
		final long endDateNumber = 999999;
		Offender offender = 
				this.offenderDelegate.createWithoutIdentity("Doe", "John", "Jay", null);
		CustodyLevel custodyLevel = this.custodyLevelDelegate.create("Level");
		CustodyChangeReason custodyChangeReason = this
				.custodyChangeReasonDelegate.create("Reason");
		Date startDate = new Date(startDateNumber);
		Date endDate = new Date(endDateNumber);
		CustodyReview custodyReview = this.custodyReviewService.create(offender,
				custodyLevel, custodyChangeReason, startDate, endDate);
		this.sessionFactory.getCurrentSession().flush();
		this.sessionFactory.getCurrentSession().clear();
		
		assertValues(offender, custodyLevel, custodyChangeReason, startDate, 
				endDate, custodyReview);
	}
	
	private void assertValues(Offender offender, CustodyLevel custodyLevel, 
			CustodyChangeReason custodyChangeReason, Date startDate, 
			Date endDate, CustodyReview custodyReview) {
		assert offender.equals(custodyReview.getOffender()) : String
			.format("Wrong offender: #%d found; #%d expected",
					custodyReview.getOffender().getOffenderNumber(),
					offender.getOffenderNumber());
		assert custodyLevel.equals(custodyReview.getCustodyLevel()) : String
			.format("Wrong custody level: %s found; %s expected", 
					custodyReview.getCustodyLevel().getName(), 
					custodyLevel.getName());
		assert custodyChangeReason.equals(custodyReview.getChangeReason()) :
			String.format("Wrong change reason: %s found; %s expected", 
					custodyReview.getChangeReason().getName(),
					custodyChangeReason.getName());
		assert startDate.equals(custodyReview.getActionDate()) : String
			.format("Wrong action date: %1$tm/%1$td/%1$ty found; "
					+ "%2$tm/%2$td/%2$ty expected", 
					custodyReview.getActionDate(), startDate);
		assert endDate.equals(custodyReview.getNextReviewDate()) : String
		.format("Wrong next review date: %1$tm/%1$td/%1$ty found; "
				+ "%2$tm/%2$td/%2$ty expected", 
				custodyReview.getNextReviewDate(), endDate);	
	}
}